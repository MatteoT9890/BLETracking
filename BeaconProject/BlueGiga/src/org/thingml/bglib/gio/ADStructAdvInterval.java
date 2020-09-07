package org.thingml.bglib.gio;

/**
 * Questa classe modellizza le AD che trasportano il valore per l'intervallo di connessione dello operazioni advertising
 * @author Giorgio Avalle
 */
public class ADStructAdvInterval extends ADStruct {

	//campi specifici
	private int interval;		//2 B, intervallo di 0.625 ms
	
	
	public ADStructAdvInterval(byte t, byte[] content, String mac) {
		super(t, content, mac);
		
		this.interval = BleUtil.uint16(content[0], content[1]);
	}

	
	@Override
	public void print() {
		System.out.println("Intervallo di advertising: " + interval() + " ms");
	}


	/** Stampa il valore dell'intervallo di advertising in millisecondi */
	private String interval() {
		double res = new Double(this.interval * 0.625);
		return new String("" + res);
	}
	
	
	
	// GETTERs
	public int getInterval(){ return this.interval; }

}
