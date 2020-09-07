package org.thingml.bglib.gio;

/**
 * Struct specifica per i "Shortened Local Name": contengono il nome ASCII del dispositivo.
 * I caratteri non stampabili vengono ignorati
 * 
 * @author Giorgio Avalle
 */

public class ADStructShortLocalName extends ADStruct {
	
	//campi specifici	
	private String localName;

	public ADStructShortLocalName(byte t, byte[] content, String mac) {
		super(t, content, mac);
		this.localName = BleUtil.getString(content);
	}

	@Override
	public void print() {
		System.out.println("Nome Locale Abbreviato del nodo: " + getLocalName());
	}
	
	
	
	// GETTERs
	public String getLocalName() { return this.localName; }

}
