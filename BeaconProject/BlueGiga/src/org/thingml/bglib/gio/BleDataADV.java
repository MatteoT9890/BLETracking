package org.thingml.bglib.gio;

import java.sql.Timestamp;



import java.util.HashMap;

import java.util.Map;

import org.thingml.bglib.BDAddr;
import org.thingml.bglib.mat.*;
import org.thingml.bglib.mat.data.InterfaceToDataBLE;
/**
 * La classe serve per formattare il payload di un pacchetto advertising BLE
 * @author Giorgio Avalle
 * 
 * 
 */



public class BleDataADV {
	private int RSSI;		//potenza del segnale
	private int packetType;
	private byte[] macAddr = new byte[6];		//Indirizzo MAC mittente
	private int addressType;
	private String bond;
	private int ADLength;
	
	public final String iBeaconMAC="00:07:80:20:01:19";
	 
	//nell'ordine, occupano 2 + 6 + 3 = 11 Byte
	
	private Map<Integer, ADStruct> ADs = new HashMap<Integer, ADStruct>();	 //contiene tutte le struct AD del pacchetto
	private String hexMac = new String();
	
	
	BleDisp d;
	BleMemory mem;
private String UUID;

	
	
	
	public BleDataADV(int rssi2, int packet_type, BDAddr sender, int address_type, int bond2, byte[] data, BleMemory mem) {
		this.RSSI = rssi2;
		this.packetType = packet_type;
		this.macAddr = createMac(sender.getByteAddr());
		this.addressType = address_type;
		this.bond = BleUtil.toHex((byte) bond2);
		this.ADLength = data.length;
		
		//Riferimento alla memoria dell'applicativo
		this.mem = mem;
		
		//Se la whitelist è abilitata, devo verificare che il pacchetto provenga da dispositivo abilitato.
		boolean daScartare = mem.isWhiteListEnabled() && !(mem.hasWL(this.hexMac));
		
		//Prendo in considerazione solo i pacchetti che non devo scartare
		if(!daScartare){
			//Gestisci la rilevazione del dispositivo
			this.mem.addDisp(this.hexMac);
			this.d = this.mem.getDisp(this.hexMac);
			
			createADs(data);
		}
	}

	/**
	 * Scompone il payload nelle varie AD Struct che lo compongono
	 * @param data è il payload
	 */
	private void createADs(byte[] data) {
		//Ora, c'è un numero imprecisato di AD struct (anche zero)
		int n = 0; 		//n° di AD struct totale
		int len;	//lunghezza AD struct
		byte type;	//tipo di contenuto trasportato
		byte[] AD;
		
		int i = 0;	  //scorro i byte di data
		int x;		  //scorro i byte della AD
		
		
		//Ogni struct è formata da: lunghezza (primo byte) e contenuto
		while(i < data.length) {
			//lunghezza
			len = BleUtil.uint8(data[i++]);
			//se nulla, chiude il pacchetto
			if(len == 0) break;
			
			//tipo
			type = data[i++];
			
			//contenuto
			AD = new byte[len-1];   //type rientra in len
			for(x = 0; x < len-1; x++) {	//type rientra in len
				AD[x] = data[i++];
			}
			
			//Creo la struct AD, a seconda del suo tipo...
			switch(type & 0xFF) {
				case 1:		//flag - 0x01
					ADs.put(new Integer(++n), new ADStructFlag(type, AD, hexMac));
					break;
					
				case 9:		//"Complete Local Name";
					ADStructCompleteLocalName struct = new ADStructCompleteLocalName(type, AD, hexMac);
					ADs.put(new Integer(++n), struct);
					
					//posso finalmente settare il tipo di dispositivo
					d.setType(struct.getLocalName());
					
					break;
				
				case 8: 	//"Shortened Local Name";
					ADStructShortLocalName struct2 = new ADStructShortLocalName(type, AD, hexMac);
					ADs.put(new Integer(++n), struct2);
					
					//posso finalmente settare il tipo di dispositivo
					d.setType(struct2.getLocalName());
					
					break;
				
				case 255:	//costruttore - 0xFF
					//Bisogna inserire la struct opportuna
					String codiceCostruttore = BleUtil.toHex(AD[1], AD[0]);
					
					switch(codiceCostruttore){
						case "004C": 	//Apple
							if(!d.isInit())
								//TODO occhio, perchè se cambi il pattern allora devi cambiare qui la scritta
								//TODO: occhio, anche i dispositivi iBLio inviano questi pacchetti!
								d.setType("Dispositivo iBeacon");
							
							//devo creare la struct in relazione al tipo di dispositivo iBeacon
							ADs.put(new Integer(++n), qualeIBeacon(type, AD));
							break;
							
						case "FFFF":
						default:
							switch(d.getType()) {
							case iBLio_IOTS: 
								ADs.put(new Integer(++n), new ADStructManufacturer_iBLio_IOTS(type, AD, hexMac));
								break;
							case iBLio_N05: 
								ADs.put(new Integer(++n), new ADStructManufacturer_iBLio_N05(type, AD, hexMac));
								break;
							case iBeacon:
								ADs.put(new Integer(++n), qualeIBeacon(type, AD));
								break;
							case ES:	
								ADs.put(new Integer(++n), new ADStructManufacturer_ES(type, AD, hexMac));
								break;
								
							case NOPE:
								//occorre passare anche il riferimento al dispositivo
								ADs.put(new Integer(++n), new ADStructManufacturer_NOPE(type, AD, d, hexMac));
								break;
								
							//case UNKNOWN:  -> compreso in default
							default: 
								System.out.println("ADStruct produttore non riconosciuta");
								break;
							}
					}
				break;
					
				//TODO: gli UUID non sono implementati in modo perfetto... L'istanza del servizio è infatti duplicata.
				
				case 2: 	//"Incomplete List of 16-bit Service Class UUIDs";
					ADs.put(new Integer(++n), new ADStructServiceUUID_16B(type, AD, hexMac));
					break;
				case 22:	//"Service Data - 16-bit UUID";
					//Verificare il caso Eddystone!
					switch(BleUtil.toHex(AD[2])){
						case "00": 
							ADs.put(new Integer(++n), new ADStructES_UID(type, AD, hexMac));
							break;
						case "10":
							if(len >= 19)
							ADs.put(new Integer(++n), new ADStructES_URL(type, AD, hexMac));
							break;
						case "20":
							if(len >= 16){
								ADs.put(new Integer(++n), new ADStructES_TLM(type, AD, hexMac));
								break;
							}
							
						default:
							//se è un Eddystone, piango  :,(
							if(d.getType() == BleDispType.ES)
								System.out.println("Eddystone AD Struct non riconosciuta (codice contenuto: " + BleUtil.toHex(AD[2]));
							else
								//caso più generico
								ADs.put(new Integer(++n), new ADStructServiceUUID_16B(type, AD, hexMac));
					}
						
					break;
	
				
				case 20:	//"List of 16-bit Service Solicitation UUIDs";
				case 3:		//"Complete List of 16-bit Service Class UUIDs";
					//verifico il caso di Eddystone a regime
					if(len == 3 && AD[0] == -86 && AD[1] == -2)		//AD = 0xAAFE
						if(!d.isInit()){
							//TODO occhio, perchè se cambi il pattern allora devi cambiare qui la scritta
							d.setType("ES Eddystone");
						}
					
					ADs.put(new Integer(++n), new ADStructServiceUUID_16B(type, AD, hexMac));
					break;
					
				case 4:		//"Incomplete List of 32-bit Service Class UUIDs";
				case 5:		//"Complete List of 32-bit Service Class UUIDs";
				case 31:	//"List of 32-bit Service Solicitation UUIDs";
				case 32:	//"Service Data - 32-bit UUID";
					ADs.put(new Integer(++n), new ADStructServiceUUID_32B(type, AD, hexMac));
					break;
					
				case 6:		//"Incomplete List of 128-bit Service Class UUIDs";
				case 7:		//"Complete List of 128-bit Service Class UUIDs";
				case 21:	//"List of 128-bit Service Solicitation UUIDs";
				case 33:	//"Service Data - 128-bit UUID";
					ADs.put(new Integer(++n), new ADStructServiceUUID_128B(type, AD, hexMac));
					break;
					
				case 10: 	//"Tx Power Level";
					ADs.put(new Integer(++n), new ADStructTXPowerLevel(type, AD, hexMac));
					break;
					
				case 16:	//"Security Manager TK Value";
					ADs.put(new Integer(++n), new ADStructSecurityManagerTKValue(type, AD, hexMac));
					break;
				case 17:	//"Security Manager Out of Band Flags";
					ADs.put(new Integer(++n), new ADStructSecurityManagerOOBFlags(type, AD, hexMac));
					break;
					
				case 18: 	//"Slave Connection Interval Range";
					ADs.put(new Integer(++n), new ADStructSecuritySlaveConnectionIntervalRange(type, AD, hexMac));
					break;
				case 26:	//"Advertising Interval";
					ADs.put(new Integer(++n), new ADStructAdvInterval(type, AD, hexMac));
					break;
					
				case 23:	//"Public Target Address";
				case 24:	//"Random Target Address";
					ADs.put(new Integer(++n), new ADStructTargetAddress(type, AD, hexMac));
					break;
					
				case 27:	//"​LE Bluetooth Device Address";
					ADs.put(new Integer(++n), new ADStructDeviceAddr(type, AD, hexMac));
					break;
				case 28:	//"​LE Role";
					ADs.put(new Integer(++n), new ADStructLERole(type, AD, hexMac));
					break;
					
					
					
				//TODO: trovare la documentazione relativa e completare: spostare poi il codice del case qui sopra...
				case 13:	//"Class of Device";
				case 14:	//"Simple Pairing Hash C";
				case 15:	//"Simple Pairing Randomizer R";
				case 34:	//"​LE Secure Connections Confirmation Value";
				case 35:	//"​LE Secure Connections Random Value";
				case 36:	//"URI";
				case 37:	//"Indoor Positioning";
				case 38:	//"Transport Discovery Data";
				case 25:	//"Appearance";
				case 29:	//"​Simple Pairing Hash C-256";
				case 30:	//"​Simple Pairing Randomizer R-256";
				case 61:	//"3D Information Data";  0x3D
					ADs.put(new Integer(++n), new ADStructGeneric(type, AD, hexMac));
					
				default:
					System.out.println("Struct AD di tipo non riconosciuto (" + (type & 0xFF) + ")");
			}
		}
		
		
	}


	/**
	 * Usato per decidere quale tipologia di iBeacon creare: prima di passare n, devi incrementarlo di uno.
	 * @param n è il numero con cui mappare la struct (prima di passarlo, incrementalo di 1)
	 * @param type è il tipo della struct
	 * @param AD è la PDU della struct
	 */
	private ADStructManufacturer_iBeacon qualeIBeacon(byte type, byte[] AD) {
		switch(BleUtil.toHex(AD[3], AD[2])){
		//TODO: trovare le specifiche di altre tipologie...
		case "1502": {
			ADStructManufacturer_iBeacon_Proximity iB= new ADStructManufacturer_iBeacon_Proximity(type, AD, hexMac);
			this.UUID= iB.getProximityUUID();
			return iB;
		}
		default:
			return new ADStructManufacturer_iBeacon_Unknown(type, AD, hexMac);
		}
	}

	/**
	 * Isola dal 3° all'8° byte della PDU (Indirizzo MAC dispositivo Bluetooth): 
	 * la numerazione parte da 1.
	 * @param m è il vettore in cui scrive il MAC (array di bytes)
	 * @return 
	 */
	private byte[] createMac(byte[] m) {
		byte[] mac = new byte[6];	//indirizzo corretto
		int i = 0;	//indice di m
		int j;		//indice MAC
		
		//little-endian
		for(j = 5; j >= 0; j--)
			mac[j] = m[i++];
		
		//traduci in formato esadecimale (con separatore ':')
		this.hexMac = BleUtil.createHexMAC(mac);
		
		return mac;
	}
	
	
	/**
	 * Ottieni l'indirizzo MAC (in byte)
	 * @return byte[]
	 */
	public byte[] getMAC() {
		return this.macAddr;
	}
	
	/**
	 * Ottieni l'indirizzo MAC esadecimale, formattato
	 * @return String
	 */
	public String getHexMAC() {
		return this.hexMac;
	}
	
	/**
	 * Ottieni tutte le strutture AD contenute nel payload del pacchetto
	 * @return Map<Integer, ADStruct>
	 */
	public Map<Integer, ADStruct> getADs() {
		return this.ADs;
	}

	/**
	 * Ottieni il livello di potenza del segnale (RSSI)
	 * @return int
	 */
	public int getRSSI() {
		return this.RSSI;
	}

	/**
	 * Ottieni il tipo di pacchetto
	 * @return int (unsigned)
	 */
	public int getPacketType() {
		return this.packetType;
	}
	
	/**
	 * Ottieni il tipo di indirizzo
	 * @return int (unsigned)
	 */
	public int getAddressType() {
		return this.addressType;
	}

	/**
	 * Ottieni informazioni sull'associazione (bond)
	 * @return String (esadecimale)
	 */
	public String getBond() {
		return this.bond;
	}
	
	
	/**
	 * Ottieni spiegazione estesa sull'associazione (bond)
	 * @return String
	 */
	public String getExtendedBond() {
		switch(this.bond) {
			case "FF": return new String("Non ci sono associazioni note per il dispositivo");
			default: return new String("Ci sono associazioni note per il dispositivo");
		}
	}
	
	/**
	 * Ottieni la lunghezza totale delle strutture AD trasportate nel pacchetto
	 * @return int (unsigned)
	 */
	public int getADLength() {
		return this.ADLength;
	}


	/**
	 * Stampa le informazioni raccolte nel pacchetto (eccetto il contenuto delle varie AD Struct)
	 */
	public void print() {
		System.out.println("RSSI: " + getRSSI() + " dBm");
        System.out.println("Packet type: " + BleCodes.packetType(this.packetType));
        System.out.println("MAC dispositivo: " + getHexMAC());
        System.out.println("Tipo di indirizzo MAC: " + BleCodes.BluetoothAddressTypes(this.addressType));
        System.out.println(getExtendedBond());
        System.out.println("La lunghezza totale (in byte) delle strutture �: " + getADLength());
	}
	
	public void elabora() {
		this.print();
		
		//Stampa contenuto delle AD
        this.getADs().values().stream()
        .forEach(ADStruct::elabora);
	}


	/**
	 * Genero il relativo pacchetto, in base al tipo di dispositivo. 
	 * Inoltre aggiungo l'informazione relativa al timestamp di generazione del pacchetto e lo memorizzo nella base dati.
	 * 
	 * @author Matte
	 * @return pack
	 */
	public Packet_iBeacon generaPacchetto(InterfaceToDataBLE DataBLE) {
		
		
		Packet_iBeacon pack = null;
		iBeacon iB;
		
		
		if (this.UUID!=null)
			if((iB = DataBLE.getiBeaconData().get(this.UUID))!=null) {
        	pack = this.createPacket_iBeacon(iB);
        	DataBLE.insertPacket_iBeacon(pack);
        }
       
        
        return pack;
        	
	}
	
	/**
	 * Avendo decodificato le informazioni relative al pacchetto, aggiungo alla mappa Packet_iBeaconMap le informazioni
	 * relative al pacchetto del beacon di tipo iBeacon.
	 *   
	 * @author Matteo Tarantino
	 * @return pack
	 */
	
	public Packet_iBeacon createPacket_iBeacon(iBeacon iB) {
		
    		
    		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    		
    			Packet_iBeacon pack = new Packet_iBeacon(this.getRSSI(),this.getADLength(),iB,
				BleCodes.packetType(this.packetType),timestamp);
		
		
		return pack; 
	
	}
	
	/**
	 * Dopo aver elaborato le informazioni sul pacchetto relativo al beacon di tipo iBeacon 
	 * lo monitoro segnalando eventuali anomalie.
	 * 
	 * @author Matteo Tarantino
	 * @param pack
	 * @return void
	 */
	
		
	
	
}

