package org.thingml.bglib.gio;

/**
 * Questa AD trasporta il Security Manager TK Value
 * @author Giorgio Avalle
 */
public class ADStructSecurityManagerTKValue extends ADStruct {
	
	//campi specifici
	private String tkValue;
	
	
	public ADStructSecurityManagerTKValue(byte t, byte[] content, String mac) {
		super(t, content, mac);
		this.tkValue = creaTK(content);
	}
	

	@Override
	public void print() {
		System.out.println("Security Manager TK Value: " + getTkValue());
	}

	
	/** Crea il valore Security Manager TX */
	private String creaTK(byte[] content) {
		StringBuffer tmp = new StringBuffer("");
		
		//lettura dati grezzi, in little endian
		for(int i = 15; i >= 0; i--)
			tmp.append(BleUtil.toHex(content[i]) + " ");
		
		return tmp.toString();
	}
	
	
	
	// GETTERs
	public String getTkValue() { return tkValue; }

}
