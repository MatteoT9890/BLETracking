package org.thingml.bglib.gio;

/**
 * AD Struct produttore specifica per i dispositivi Eddystone (Google)
 * @author Giorgio Avalle
 */

public class ADStructManufacturer_ES extends ADStructManufacturer {
	
	//TODO: campi specifici
	

	public ADStructManufacturer_ES(byte type, byte[] content, String mac) {
		super(type, content, mac);
	}

	@Override
	public void printOver() {
		// TODO Auto-generated method stub
		System.out.println("Ciao, sono una AD Produttore Eddystone");
	}

}
