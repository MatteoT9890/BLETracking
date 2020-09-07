package org.thingml.bglib.gio;

/**
 * Questa AD trasporta l'indirizzo MAC di un dispositivo BLE, indicando inoltre se l'indirizzo è pubblico o privato.
 * I primi 7 bit del primo byte sono riservati.
 * 
 * @author Giorgio Avalle
 */
public class ADStructDeviceAddr extends ADStruct {
	
	//campi specifici
	private int typeAddr;		//ultimo bit del primo byte
	private String hexMac;		//6B: da 1 a 6
	

	public ADStructDeviceAddr(byte t, byte[] content, String mac) {
		super(t, content, mac);
		
		//tipo di indirizzo
		typeAddr = (content[0] & 0xFF) % 2;
		
		//indirizzo MAC
		creaMAC(content);
	}
	
	
	
	@Override
	public void print() {
		System.out.println("Indirizzo MAC: " + getHexMac());
		System.out.println("Tipo di indirizzo: " + BleCodes.BluetoothAddressTypes(typeAddr));
	}


	/** Ricava l'indirizzo MAC */
	private void creaMAC(byte[] content) {
		byte[] res = new byte[6];
		int j = 0;
		
		for(int i = 6; i > 0; i--)
			res[j++] = content[i];
		
		this.hexMac = BleUtil.createHexMAC(res);
	}

	
	// GETTERs
	public int getTypeAddr(){ return this.typeAddr; }
	public String getHexMac(){ return this.hexMac; }
	
}
