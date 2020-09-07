package org.thingml.bglib.gio;

/**
 * Specifica per i dispositivi iBLio, permette di riconoscere service UUID a 128 bit non-standard tipici di tali dispositivi
 * @author Giorgio Avalle
 */
public class ADStructServiceUUID_128B_iBLio extends ADStructServiceUUID_128B {

	public ADStructServiceUUID_128B_iBLio(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
	}

	protected String getExtendedUUIDOver(String uuid){
		switch(uuid){
		case "EE0C1000878640BAAB9699B91AC981D8": return new String("NODE Configuration");
		default:
			return new String("UUID not recognized (" + uuid + ")");
		}
	}

}
