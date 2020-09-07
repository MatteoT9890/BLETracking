package org.thingml.bglib.gio;

/**
 * Questa classe definisce il contenuto di dispositivi iBeacon il cui tipo non viene riconosciuto, evitando il crash dell'applicativo
 * @author Giorgio Avalle
 *
 */
public class ADStructManufacturer_iBeacon_Unknown extends ADStructManufacturer_iBeacon {

	public ADStructManufacturer_iBeacon_Unknown(byte type, byte[] content, String mac) {
		super(type, content, mac);
	}

	@Override
	protected String createTipoBeacon() {
		return new String("non riconosciuto");
	}

	@Override
	protected void printOver2() {}

}
