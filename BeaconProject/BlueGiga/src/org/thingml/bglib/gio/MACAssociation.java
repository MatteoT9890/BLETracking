package org.thingml.bglib.gio;

/** La classe associa ad un indirizzo MAC il corrispettivo tipo di ADStructManufacturer richiesto dal dispositivo
 * @author Giorgio Avalle
 */
public class MACAssociation {
	private String mac;		//esadecimale formattato
	private String struct;	//ADStructManufacturer richiesta dal dispositivo
	
	
	public MACAssociation(String mac, String struct) {
		this.mac = mac;
		this.struct = struct;
	}
	

	/** Ottieni indirizzo MAC esadecimale
	 * @return String
	 */
	public String getMac() {
		return mac;
	}

	/** Ottieni tipo di AD struct (manufacturer) richiesta
	 * @return String
	 */
	public String getStruct() {
		return struct;
	}
	
}
