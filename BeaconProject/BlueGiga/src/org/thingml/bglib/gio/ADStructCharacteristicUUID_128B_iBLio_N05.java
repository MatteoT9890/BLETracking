package org.thingml.bglib.gio;

/**
 * Questo tipo di struct AD trasporta UUID di caratteristiche a 128 bit per un dispositivo iBLio generico
 * @author Giorgio Avalle
 */
public class ADStructCharacteristicUUID_128B_iBLio_N05 extends ADStructCharacteristicUUID_128B_iBLio {

	public ADStructCharacteristicUUID_128B_iBLio_N05(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
	}

	@Override
	protected String getExtendedUUIDOver2(String uuid) {
		switch(uuid) {
			case "EE0C1010878640BAAB9699B91AC981D8": return new String("SensorTemp_Celsius");
			case "EE0C1020878640BAAB9699B91AC981D8": return new String("SensorHumidity_Percent");
			default:
				return new String("UUID not recognized (" + uuid + ")");
		}
	}

}
