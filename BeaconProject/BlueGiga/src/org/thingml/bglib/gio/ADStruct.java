package org.thingml.bglib.gio;

/**
 * Nella PDU di un pacchetto BLE si trovano più strutture AD: il primo byte specifica la lunghezza della 
 * parte seguente, la quale comprende tipo di dati trasportati e contenuto vero e proprio
 * 
 * @author Giorgio Avalle
 */

public abstract class ADStruct {
	
	//campi comuni
	protected String type;
	protected String extendedType;
	protected String mac;
	
	
	//constructor per i campi comuni
	public ADStruct(byte t, byte[] content, String mac){
		type = BleUtil.toHex(t);
		this.extendedType = BleCodes.createExtendedType(this.type);
		this.mac = mac;
	}
	
	/**
	 * Visualizza tipo e contenuto della struct AD
	 */
	public void elabora(){
		System.out.println("** AD STRUCT: " + getExtendedType() + " **");
		print();
	}
	
	/**
	 * Stampa informazioni sul contenuto della struct AD
	 */
	public abstract void print();
	

	//L'assegnazione viene fatta in: BleData
	

	
	// GETTERs
	public String getExtendedType() { return this.extendedType; }
	public String getType() { return this.type; }
		
}
