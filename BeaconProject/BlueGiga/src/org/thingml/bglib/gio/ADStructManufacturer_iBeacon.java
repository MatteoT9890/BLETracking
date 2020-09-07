package org.thingml.bglib.gio;

/**
 * Tipo di AD-Struct Manufacturer specifica per gli iBeacon
 * @author Giorgio Avalle
 */
public abstract class ADStructManufacturer_iBeacon extends ADStructManufacturer {

	//campi specifici della struct
	protected String tipoBeacon;
	
	public ADStructManufacturer_iBeacon(byte type, byte[] content, String mac) {
		super(type, content, mac);
		
		//tipo di iBeacon
		this.tipoBeacon = createTipoBeacon();
	}

	
	@Override
	public void printOver() {
		System.out.println("Dispositivo iBeacon");
		System.out.println("Tipo di Beacon: " + getTipoBeacon());
		
		//info ulteriori
		printOver2();
	}
	
	
	/** Definisce la tipologia di dispositivo iBeacon */
	protected abstract String createTipoBeacon();
	
	/** Stampa informazioni aggiuntive specifiche per tipologia di iBeacon */
	protected abstract void printOver2();

	
	
	// GETTERs
	public String getTipoBeacon() { return tipoBeacon; }
	
}
