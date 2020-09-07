package org.thingml.bglib.gio;

/**
 * Specifica per i dispositivi iBLio, permette di riconoscere Characteristic UUID a 128 bit non-standard tipici di tali dispositivi
 * @author Giorgio Avalle
 */
public abstract class ADStructCharacteristicUUID_128B_iBLio extends ADStructCharacteristicUUID_128B {

	public ADStructCharacteristicUUID_128B_iBLio(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
	}

	protected String getExtendedUUIDOver(String uuid){
		switch(uuid){
		case "EE0C1001878640BAAB9699B91AC981D8": return new String("Node_NetID");
		case "EE0C1002878640BAAB9699B91AC981D8": return new String("Node_ID");
		case "EE0C1003878640BAAB9699B91AC981D8": return new String("Node_Type");
		default: 
			return this.getExtendedUUIDOver(uuid);
		}
	}
	
	protected abstract String getExtendedUUIDOver2(String uuid);

}
