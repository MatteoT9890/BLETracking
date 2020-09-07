package org.thingml.bglib.gio;

/**
 * ADStruct specifica per i beacon Eddystone
 * @author Giorgio Avalle
 */
public abstract class ADStructES extends ADStruct {
	
	//campi specifici
	protected String frameTypeCode;
	protected String frameType;
	

	public ADStructES(byte type, byte[] AD, String mac) {
		super(type, AD, mac);
		
		//salto 0xAA 0xFE ripetuto...
		this.frameType = BleCodes.ES_FrameType(BleUtil.toHex(AD[2]));
	}
	
	
	@Override
	public void print() {
		System.out.println("Frame Type: " + getFrameType());
		printOver();
	}

	/** Stampa le informazioni personalizzate della struct Eddystone */
	public abstract void printOver();
	
	
	
	// GETTERs
	public String getFrameTypeCode(){ return this.frameTypeCode; }
	public String getFrameType(){ return this.frameType; }

}
