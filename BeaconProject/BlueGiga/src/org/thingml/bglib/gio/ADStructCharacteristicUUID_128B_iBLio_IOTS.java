package org.thingml.bglib.gio;

/**
 * Questo tipo di struct AD trasporta UUID di caratteristiche a 128 bit per il dispositivo iBLio IOTS
 * @author Giorgio Avalle
 */
public class ADStructCharacteristicUUID_128B_iBLio_IOTS extends ADStructCharacteristicUUID_128B_iBLio {

	public ADStructCharacteristicUUID_128B_iBLio_IOTS(byte type, byte[] aD, String mac) {
		super(type, aD, mac);
	}

	@Override
	protected String getExtendedUUIDOver2(String uuid) {
		switch(uuid) {
			case "EE0C1010878640BAAB9699B91AC981D8": return new String("DigitalOUT modes");
			case "EE0C1011878640BAAB9699B91AC981D8": return new String("Relay");
			case "EE0C1012878640BAAB9699B91AC981D8": return new String("LED_1");
			case "EE0C1013878640BAAB9699B91AC981D8": return new String("LED_2");
			case "EE0C1014878640BAAB9699B91AC981D8": return new String("Out_0");
			case "EE0C1015878640BAAB9699B91AC981D8": return new String("Out_1");
			case "EE0C1016878640BAAB9699B91AC981D8": return new String("Out_2");
			case "EE0C1017878640BAAB9699B91AC981D8": return new String("Out_3");
			case "EE0C1090878640BAAB9699B91AC981D8": return new String("ReadI2C");
			case "EE0C1091878640BAAB9699B91AC981D8": return new String("DataReadedOnI2C");
			case "EE0C1092878640BAAB9699B91AC981D8": return new String("WriteI2C");
			case "EE0C1093878640BAAB9699B91AC981D8": return new String("DataToBeWrittenOnI2C");
			default:
				return new String("UUID not recognized (" + uuid + ")");
		}
	}

}
