package org.thingml.bglib.gio;

/**
 * Questa AD trasporta, se impostati, i valori minimo e massimo per l'intervallo di connessione 
 * @author Giorgio Avalle
 */
public class ADStructSecuritySlaveConnectionIntervalRange extends ADStruct {
	
	//campi specifici
	private int minRange;	//2 B, 0xFFFF = non settato
	private int maxRange;	//2 B, 0xFFFF = non settato

	
	
	public ADStructSecuritySlaveConnectionIntervalRange(byte t, byte[] content, String mac) {
		super(t, content, mac);
		
		this.minRange = BleUtil.uint16(content[0], content[1]);
		this.maxRange = BleUtil.uint16(content[2], content[3]);
	}

	
	@Override
	public void print() {
		System.out.println("Intervallo di connessione minimo: " + minRange());
		System.out.println("Intervallo di connessione massimo: " + maxRange());
	}

	
	/** Stampa il valore di connessione minimo */
	private String minRange() {
		if(minRange == 65536)
			return new String("non impostato");
		return new String("" + new Double(minRange * 1.25) + " ms");
	}

	/** Stampa il valore di connessione massimo */
	private String maxRange() {
		if(maxRange == 65536)
			return new String("non impostato");
		return new String("" + new Double(maxRange * 1.25) + " ms");
	}

	

	// GETTERs
	public int getMinRange() { return minRange; }
	public int getMaxRange() { return maxRange; }

}
