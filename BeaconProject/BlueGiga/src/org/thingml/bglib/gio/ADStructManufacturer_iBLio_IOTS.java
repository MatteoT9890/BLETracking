package org.thingml.bglib.gio;

/**
 * AD Struct Produttore per i dispositivi iBLio IOTS
 * @author Giorgio Avalle
 */
public class ADStructManufacturer_iBLio_IOTS extends ADStructManufacturer_iBLio {
	
	//campi specifici per l'IoT Semplice
	private String P0;
	private String P1;
	private String P2;
	
	private int A1;
	private int A2;
	private int A3;
	private int A4;
	
	private int D1;
	private int D2;
	private int D3;
	private int D4;
	
	
	
	
	public ADStructManufacturer_iBLio_IOTS(byte t, byte[] content, String mac) {
		super(t, content, mac);
		
		//stato delle porte 0-2  (7-9 B)
		this.createPorte(content);
		
		//Analog IN (10-17 B)
		this.createAnalogIN(content);
		
		//Digital IN (18-25 B)
		this.createDigitalIN(content);
	}
	
	
	@Override
	public void printOver2() {
		System.out.println("Porta 0: " + this.P0);
		System.out.println("Porta 1: " + this.P1);
		System.out.println("Porta 2: " + this.P2);
		System.out.println("Ingresso A1: " + this.A1);
		System.out.println("Ingresso A2: " + this.A2);
		System.out.println("Ingresso A3: " + this.A3);
		System.out.println("Ingresso A4: " + this.A4);
		System.out.println("Ingresso D1: " + this.D1);
		System.out.println("Ingresso D2: " + this.D2);
		System.out.println("Ingresso D3: " + this.D3);
		System.out.println("Ingresso D4: " + this.D4);
	}
	

	
	
	/** Crea i campi D1-D4 (input digitali del device)
	 * @param content è il centenuto della AD
	 */
	private void createDigitalIN(byte[] content) {
		this.D1 = BleUtil.uint16(content[17], content[18]);
		this.D2 = BleUtil.uint16(content[19], content[20]);
		this.D3 = BleUtil.uint16(content[21], content[22]);
		this.D4 = BleUtil.uint16(content[23], content[24]);
	}
	
	/** Crea i campi A1-A4 (input analogici del device)
	 * @param content è il contenuto della AD
	 */
	private void createAnalogIN(byte[] content) {
		this.A1 = BleUtil.uint16(content[9], content[10]);
		this.A2 = BleUtil.uint16(content[11], content[12]);
		this.A3 = BleUtil.uint16(content[13], content[14]);
		this.A4 = BleUtil.uint16(content[15], content[16]);
	}

	/**
	 * Legge lo stato delle porte 0, 1, 2
	 * @param content
	 */
	private void createPorte(byte[] content) {
		this.P0 = BleUtil.toHex(content[6]);
		this.P1 = BleUtil.toHex(content[7]);
		this.P2 = BleUtil.toHex(content[8]);
	}
	
	
	
	
	
	// GETTERs
	
	public String getP0() { return P0; }
	public String getP1() { return P1; }
	public String getP2() { return P2; }
	public int getA1() { return A1; }
	public int getA2() { return A2; }
	public int getA3() { return A3; }
	public int getA4() { return A4; }
	public int getD1() { return D1; }
	public int getD2() { return D2; }
	public int getD3() { return D3; }
	public int getD4() { return D4; }
	
}
