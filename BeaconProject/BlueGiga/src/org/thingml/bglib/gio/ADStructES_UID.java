package org.thingml.bglib.gio;

/**
 * AD specifica per dispositivi Eddystone: trasporta l'UID del dispositivo (UID)
 * @author Giorgio Avalle
 */
public class ADStructES_UID extends ADStructES {

	//campi specifici
	private int txPower;
	private String NID;		//TODO: convertire in human-readable
	private String BID;
	private String RFU;
	

	public ADStructES_UID(byte type, byte[] content, String mac) {
		super(type, content, mac);
		
		this.txPower = content[3];
		createNID(content);
		createBID(content);
		createRFU(content);
	}

	
	@Override
	public void printOver() {
		System.out.println("TX power: " + getTxPower());
		System.out.println("NID: " + getNID());
		System.out.println("BID: " + getBID());
		System.out.println("RFU: " + getRFU());
	}


	/**
	 * Crea il campo NID (10 byte)
	 * @param content è la pdu della ADStruct
	 */
	private void createNID(byte[] content) {
		StringBuffer s = new StringBuffer();
		int i;
		
		//TODO non c'è il little-endian, perchè dovrebbe essere una stringa
		for(i=4; i<14; i++)
			s.append(BleUtil.toHex(content[i]));
		
		this.NID = s.toString();
	}
	
	/**
	 * Crea il campo BID (6 byte)
	 * @param content è la pdu della ADStruct
	 */
	private void createBID(byte[] content) {
		StringBuffer s = new StringBuffer();
		int i;
		
		//TODO non c'è il little-endian, perchè dovrebbe essere una stringa
		for(i=14; i<20; i++)
			s.append(BleUtil.toHex(content[i]));
		
		this.BID = s.toString();
	}
	
	/**
	 * Crea il campo RFU (2 byte)
	 * @param content è la pdu della ADStruct
	 */
	private void createRFU(byte[] content) {
		StringBuffer s = new StringBuffer();
		int i;
		
		//TODO non c'è il little-endian, perchè dovrebbe essere una stringa
		for(i=20; i<22; i++)
			s.append(BleUtil.toHex(content[i]));
		
		this.RFU = s.toString();
	}


	
	// GETTERs
	public int getTxPower() { return txPower; }
	public String getNID() { return NID; }
	public String getBID() { return BID; }
	public String getRFU() { return RFU; }
	
}
