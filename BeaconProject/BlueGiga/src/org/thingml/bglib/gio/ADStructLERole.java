package org.thingml.bglib.gio;

/**
 * Questa AD trasporta il ruolo che il dispositivo può assumere nella comunicazione BLE
 * @author Giorgio Avalle
 */
public class ADStructLERole extends ADStruct {

	//campi specifici
	private String role;
	
	
	public ADStructLERole(byte t, byte[] content, String mac) {
		super(t, content, mac);
		
		this.role = BleCodes.role(BleUtil.toHex(content[0]));
	}

	@Override
	public void print() {
		System.out.println("Ruolo del dispositivo: " + getRole());
	}
	

	
	// GETTERs
	public String getRole() { return role; }

}
