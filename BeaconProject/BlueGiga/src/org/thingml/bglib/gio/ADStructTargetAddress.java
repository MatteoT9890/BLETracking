package org.thingml.bglib.gio;

import java.util.ArrayList;
import java.util.List;

/**
 * AD Struct specifica per insiemi di target addresses (indirizzi MAC, pubblici o random)
 * @author Giorgio Avalle
 */
public class ADStructTargetAddress extends ADStruct {
	
	private List<String> addresses = new ArrayList<>();
	

	public ADStructTargetAddress(byte t, byte[] content, String mac) {
		super(t, content, mac);
		
		createAddresses(content);
	}
	
	
	@Override
	public void print() {
		System.out.println("Elenco di indirizzi MAC: ");
		this.addresses.stream().forEach(x -> System.out.println(x));
	}
	

	/**
	 * Crea gli indirizzi MAC, leggendo gruppi di 6 byte
	 * @param content è la PDU della AD
	 */
	private void createAddresses(byte[] content) {
		int i = 0;
		byte[] res = new byte[6];
		
		while(i < content.length){
			for(int j = 5; j >= 0; j--)
				res[j] = content[i++];
			addAddress(res);
		}
	}

	/**
	 * Formatta l'indirizzo MAC passato (non più in little endian) e lo aggiunge all'elenco
	 * @param res è un indirizzo MAC, non little endian, formato byte
	 */
	private void addAddress(byte[] res) {
		this.addresses.add(BleUtil.createHexMAC(res));
	}

}
