package org.thingml.bglib.gio;

/**
 * Questo tipo di struct AD trasporta UUID di caratteristiche a 128 bit
 * @author Giorgio Avalle
 */
public class ADStructCharacteristicUUID_128B extends ADStructCharacteristicUUID {

	public ADStructCharacteristicUUID_128B(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
	}

	@Override
	public void setNByte() {
		this.nByte = 16;
	}

}
