package org.thingml.bglib.gio;

import org.thingml.bglib.ByteUtils;;


/**
 * Questa classe viene creata come "copertura" per quelle ADStruct conformi alle specifiche BLE che vengono rilevate ma che non hanno una vera e propria implementazione
 * @author Giorgio Avalle
 */
public class ADStructGeneric extends ADStruct {
	
	byte[] content = null;

	public ADStructGeneric(byte t, byte[] content, String mac) {
		super(t, content, mac);
		
		//salva il contenuto grezzo
		this.content = content;
	}

	@Override
	public void print() {
		System.out.println("Contenuto non decifrabile: ");
		ByteUtils.bytesToString(this.content);
	}

}
