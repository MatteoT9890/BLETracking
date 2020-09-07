package org.thingml.bglib.gio;

/**
 * Questo tipo di struct AD trasporta UUID di servizi a 128 bit
 * @author Giorgio Avalle
 */
public class ADStructServiceUUID_128B extends ADStructServiceUUID {

	public ADStructServiceUUID_128B(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
	}

	@Override
	public void setNByte() {
		this.nByte = 16;
	}

}
