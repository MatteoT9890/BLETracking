package org.thingml.bglib.gio;

/**
 * AD Struct specifica per le flag: di solito, è formata dai byte 0x020106  (2 1 6)
 * @author Giorgio Avalle
 */
public class ADStructFlag extends ADStruct {
	
	//campi specifici
	private String flag;
	
	
	public ADStructFlag(byte t, byte[] content, String mac) {
		super(t, content, mac);
		this.flag = BleCodes.flag(BleUtil.toHex(content[0]));
	}
	
	@Override
	public void print() {
		System.out.println("Significato della flag: " + getFlag());
	}
	

	
	
	// GETTERS
	public String getFlag() { return this.flag; }

}
