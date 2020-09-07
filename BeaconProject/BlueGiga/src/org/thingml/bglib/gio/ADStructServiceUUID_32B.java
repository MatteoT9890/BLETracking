package org.thingml.bglib.gio;

/**
 * Questo tipo di struct AD trasporta UUID di servizi a 32 bit
 * @author Giorgio Avalle
 */
public class ADStructServiceUUID_32B extends ADStructServiceUUID {

	public ADStructServiceUUID_32B(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
	}

	@Override
	public void setNByte() {
		this.nByte = 4;
	}

}
