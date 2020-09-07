package org.thingml.bglib.gio;

/**
 * Questo tipo di struct AD trasporta UUID di caratteristiche a 16 bit
 * @author Giorgio Avalle
 */
public class ADStructCharacteristicUUID_16B extends ADStructCharacteristicUUID {
	

	public ADStructCharacteristicUUID_16B(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
	}
	

	@Override
	public void setNByte() {
		this.nByte = 2;		//16 bit
	}

}
