package org.thingml.bglib.gio;

/**
 * Questo tipo di struct AD trasporta UUID di caratteristiche (16, 32 o 128 bit)
 * @author Giorgio Avalle
 */
import java.util.ArrayList;

//TODO vedi questo scritto (il forse)

/**
 * Classe astratta per le AD struct contenenti voci di Characteristic UUID (sono quelle contenute dentro i servizi della GATT)..
 * Queste, a loro volta, (forse) potrebbero essere da 16, 32 o 128 byte.
 * 
 * @author Giorgio Avalle
 */

public abstract class ADStructCharacteristicUUID extends ADStruct {
	
	protected ArrayList<String> UUIDs = new ArrayList<>();
	protected ArrayList<String> UUIDsCodes = new ArrayList<>();
	
	//campi specifici
	int nByte;
	
	//TODO: può servire un riferimento al padre?  (UUID del servizio)

	
	public ADStructCharacteristicUUID(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
		
		//imposta la dimensione in Byte dell'UUID
		this.setNByte();
				
		this.routine(aD);
	}
	
	
	
	@Override
	public void print() {
		System.out.println("Elenco di Characteristic UUID: ");
		UUIDs.stream().forEach(x -> System.out.println(x));
	}
	
	/** Permette, in fase di creazione, di definire il numero di byte occupati dall'UUID */
	public abstract void setNByte();
	
	
	
	/**
	 * Permette la lettura degli UUID, la cui dimensione varia a seconda del tipo di struct (16, 32, 128 bit)
	 * @param content è la PDU contenente i codici
	 */
	public void routine(byte[] content) {
		int i = 0;
		int j;
		int b = this.getNByte();
		
		while(i < content.length) {
			//crea UUID
			StringBuffer tmp = new StringBuffer("");
			for(j = i + b - 1; j >= i; j--)
				tmp.append(BleUtil.toHex(content[j])); 	//little-endian
			
			this.addUUID(tmp.toString());
			this.UUIDsCodes.add(tmp.toString());
			
			//salta i byte occupati dall'UUID
			i = i + b;
		}
	}
	
	
	/**
	 * Aggiunge un UUID all'elenco di quelli contenuti nell'intera AD
	 * @param uuid è il Service Class UUID (formato stringa)
	 */
	public void addUUID(String uuid) {
		UUIDs.add(this.getExtendedUUID(uuid));
	}
	
	/**
	 * Ottieni il primo UUID dell'elenco
	 * @param uuid è il Service Class UUID (formato stringa)
	 */
	public String getFirstUUID() {
		return this.UUIDsCodes.get(0);
	}
	
	
	
	/**
	 * Ricava il nome esteso dell'UUID a partire dal suo codice. L'elenco comprende i soli codici ufficialmente adottati.
	 * <br><a href="https://developer.bluetooth.org/gatt/services/Pages/ServicesHome.aspx">Riferimento</a>
	 * @param uuid è il codice esadecimale
	 * @return String
	 */
	private String getExtendedUUID(String uuid) {
		//TODO: ce ne sono poche e non so nemmeno se servono...
		switch(uuid) {
			case "2A00": return new String("​Device Name");		//padre: 1800
			case "2A29": return new String("​Manufacturer Name");		//padre: 180A
			case "2A24": return new String("​Model number");		//padre: 180A
			
			//iBeacon: il padre è sempre EE0C2090878640BAAB9699B91AC981D8
			case "EE0C2091878640BAAB9699B91AC981D8": return new String("UUID");
			case "EE0C2092878640BAAB9699B91AC981D8": return new String("Major");
			case "EE0C2093878640BAAB9699B91AC981D8": return new String("Minor");
			case "EE0C2094878640BAAB9699B91AC981D8": return new String("Adv100mSec");
			case "EE0C2095878640BAAB9699B91AC981D8": return new String("TxPower");
			case "EE0C2096878640BAAB9699B91AC981D8": return new String("RSSIatOneMeter");
			
			//Eddystone: il padre è sempre EE0C2080878640BAAB9699B91AC981D8
			case "EE0C2081878640BAAB9699B91AC981D8": return new String("Lock State");
			case "EE0C2082878640BAAB9699B91AC981D8": return new String("Lock");
			case "EE0C2083878640BAAB9699B91AC981D8": return new String("Unlock");
			case "EE0C2084878640BAAB9699B91AC981D8": return new String("ES URL Data");
			case "EE0C2085878640BAAB9699B91AC981D8": return new String("ES Flags");
			case "EE0C2086878640BAAB9699B91AC981D8": return new String("Adv Tx Power Levels");
			case "EE0C2087878640BAAB9699B91AC981D8": return new String("TX power mode");
			case "EE0C2088878640BAAB9699B91AC981D8": return new String("Beacon period");
			case "EE0C2089878640BAAB9699B91AC981D8": return new String("Reset");
			case "EE0C208A878640BAAB9699B91AC981D8": return new String("Radio Tx Power Levels");
			
			default: 
				return this.getExtendedUUIDOver(uuid);
		}
	}
	
	/**
	 * Quando il codice UUID della characteristic non rientra tra quelli ufficialmente adottati, viene chiamata questa funzione per vedere, a seconda del dispositivo, se l'UUID rientra tra quelli personalizzati relativi a questo
	 * @param uuid è il codice UUID non-standard
	 * @return String
	 */
	protected String getExtendedUUIDOver(String uuid){
		return new String("UUID not recognized (" + uuid + ")");
	}
	
	
	
	// GETTERs
	public int getNByte() { return this.nByte; }

}
