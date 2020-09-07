package org.thingml.bglib.gio;

import java.util.ArrayList;

/**
 * Classe astratta per le AD struct contenenti l'elenco (completo oppure no) di Service Class UUID.
 * Questi, a loro volta, potrebbero essere da 16, 32 o 128 byte.
 * 
 * @author Giorgio Avalle
 */
public abstract class ADStructServiceUUID extends ADStruct {
	
	protected ArrayList<String> UUIDs = new ArrayList<>();
	protected ArrayList<String> UUIDsCodes = new ArrayList<>();
	
	//campi specifici
	int nByte;

	
	public ADStructServiceUUID(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
		
		//imposta la dimensione in Byte dell'UUID
		this.setNByte();
				
		this.routine(aD);
	}
	
	
	@Override
	public void print() {
		System.out.println("Elenco di Service Class UUID: ");
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
		//TODO: l'elenco comprende i soli codici ufficialmente adottati
		switch(uuid) {
			case "1811": return new String("​Alert Notification Service");
			case "1815": return new String("​Automation IO");
			case "180F": return new String("​Battery Service");
			case "1810": return new String("​Blood Pressure");
			case "181B": return new String("​Body Composition");
			case "181E": return new String("​Bond Management");
			case "181F": return new String("​Continuous Glucose Monitoring");
			case "1805": return new String("​Current Time Service");
			case "1818": return new String("​Cycling Power");
			case "1816": return new String("​Cycling Speed and Cadence");
			case "180A": return new String("​Device Information");
			case "181A": return new String("​Environmental Sensing");
			case "1800": return new String("​Generic Access");
			case "1801": return new String("​Generic Attribute");
			case "1808": return new String("​Glucose");
			case "1809": return new String("​Health Thermometer");
			case "180D": return new String("​Heart Rate");
			case "1823": return new String("​HTTP Proxy");
			case "1812": return new String("​Human Interface Device");
			case "1802": return new String("​Immediate Alert");
			case "1821": return new String("​Indoor Positioning");
			case "1820": return new String("​Internet Protocol Support");
			case "1803": return new String("​Link Loss");
			case "1819": return new String("​Location and Navigation");
			case "1807": return new String("​Next DST Change Service");
			case "1825": return new String("​Object Transfer");
			case "180E": return new String("​Phone Alert Status Service");
			case "1822": return new String("​Pulse Oximeter");
			case "1806": return new String("​Reference Time Update Service");
			case "1814": return new String("​Running Speed and Cadence");
			case "1813": return new String("​Scan Parameters");
			case "1824": return new String("​Transport Discovery");
			case "1804": return new String("​Tx Power");
			case "181C": return new String("​User Data");
			case "181D": return new String("​Weight Scale");
			
			//Eddystone identifier, "non ufficiale"
			case "FEAA": return new String("Dispositivo Eddystone");
			
			//Eddystone
			case "EE0C2080878640BAAB9699B91AC981D8": return new String("Eddystone-URL Beacon Configuration");
			
			//iBeacon
			case "EE0C2090878640BAAB9699B91AC981D8": return new String("iBeacon Configuration");
			
			default: 
				return this.getExtendedUUIDOver(uuid);
		}
	}

	/**
	 * Quando il codice UUID del servizio non rientra tra quelli ufficialmente adottati, viene chiamata questa funzione per vedere, a seconda del dispositivo, se l'UUID rientra tra quelli personalizzati relativi a questo
	 * @param uuid è il codice UUID non-standard
	 * @return String
	 */
	protected String getExtendedUUIDOver(String uuid) {
		return new String("UUID not recognized (" + uuid + ")");
	}
	
	
	
	// GETTERs
	public int getNByte() { return this.nByte; }

}
