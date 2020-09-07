package org.thingml.bglib.gio;

/**
 * Tipo di AD-Struct Manufacturer specifica per gli iBeacon di tipo Proximity
 * @author Giorgio Avalle
 */
public class ADStructManufacturer_iBeacon_Proximity extends ADStructManufacturer_iBeacon {

	//campi specifici della struct
	private String proximityUUID;
	private int major;
	private int minor;
	private int measuredPower;
	
	
	public ADStructManufacturer_iBeacon_Proximity(byte type, byte[] content, String mac) {
		super(type, content, mac);
		
		//parte personalizzabile
		createProximity(content);
		
		//major e minor in little endian
		this.major = BleUtil.uint16(content[21], content[20]);
		this.minor = BleUtil.uint16(content[23], content[22]);
		
		//potenza rilevata: utile per triangolare la posizione del dispositivo
		this.measuredPower = BleUtil.int8(content[24]);
	}
	
	
	@Override
	protected String createTipoBeacon() {
		return new String("Proximity Beacon");
	}

	@Override
	protected void printOver2() {
		System.out.println("Contenuto (proximity UUID): " + getProximityUUID());		//TODO: quando hai capito cosa contiene, sistema
		System.out.println("Major: " + getMajorValue());
		System.out.println("Minor: " + getMinorValue());
		System.out.println("RSSI a 1 metro: " + getMeasuredPower());
	}

	
	
	/** Stampa il campo Major, se definito */
	private String getMajorValue() {
		int res = getMajor();
		//se vale 0, allora non è stato definito
		if(res == 0)
			return new String("non definito");
		return new String("" + res);
	}
	
	/** Stampa il campo Minor, se definito */
	private String getMinorValue() {
		int res = getMinor();
		//se vale 0, allora non è stato definito
		if(res == 0)
			return new String("non definito");
		return new String("" + res);
	}

	/** 
	 * Crea il campo proximity UUID
	 * @param content è la PDU da cui prelevare le informazioni
	 */
	private void createProximity(byte[] content) {
		// TODO capire cosa c'è dentro... 
		
		//consideriamo i byte da 4 a 19
		StringBuffer res = new StringBuffer();
		
		for(int i=4; i<=19; i++){
			res.append(BleUtil.toHex(content[i]) + " ");
		}
		
		this.proximityUUID = res.toString();
		//TODO: penso che questo UUID si possa convertire in human-readable
	}


	
	// GETTERs
	public String getProximityUUID() { return proximityUUID; }
	public int getMajor() { return major; }
	public int getMinor() { return minor; }
	public int getMeasuredPower() { return measuredPower; }

}
