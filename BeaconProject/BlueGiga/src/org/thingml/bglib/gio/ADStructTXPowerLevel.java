package org.thingml.bglib.gio;

/**
 * Questa AD trasporta il livello di potenza in trasmissione (espresso in dBm)
 * @author Giorgio Avalle
 */
public class ADStructTXPowerLevel extends ADStruct {

	//campi specifici
	int powerLevel;
	
	
	public ADStructTXPowerLevel(byte t, byte[] content, String mac) {
		super(t, content, mac);
		this.powerLevel = BleUtil.int8(content[0]);
	}
	

	@Override
	public void print() {
		System.out.println("Livello di potenza in trasmissione: " + getPowerLevel() + " dBm");
	}
	
	
	// GETTERs
	public int getPowerLevel() { return this.powerLevel; }

}
