package org.thingml.bglib.gio;

/**
 * AD specifica per dispositivi Eddystone: trasporta l'URL del dispoditivo (URL)
 * @author Giorgio Avalle
 */
public class ADStructES_URL extends ADStructES {
	
	//campi specifici
	private int txPower;
	private String URLScheme;
	private String URL;

	
	
	public ADStructES_URL(byte type, byte[] content, String mac) {
		super(type, content, mac);
		
		this.txPower = content[3];
		this.URLScheme = BleCodes.ES_UrlScheme(BleUtil.toHex(content[4]));
		createURL(content);
	}

	
	@Override
	public void printOver() {
		System.out.println("TX power: " + getTxPower());
		System.out.println("URL: " + getURLScheme() + getURL());
	}
	
	/**
	 * Crea il campo URL
	 * @param content è la PDU della struct AD
	 */
	private void createURL(byte[] content) {
		int i = 0;
		StringBuffer s = new StringBuffer();
		String str;
		
		for(byte b : content){
			//l'url comincia al byte 5
			if(i < 5){
				i++;
				continue;
			}
			
			//accoda i singoli caratteri
			str = ADStructES_URL.URLChar(b);
			s.append(str);
		}
		
		this.URL = s.toString();
	}
	
	/**
	 * Crea il singolo carattere che compone l'URL del dispositivo
	 * @param b	è il byte da convertire in human-readable
	 * @return String
	 */
	private static String URLChar(byte b) {
		String s = BleUtil.toHex(b);
		Character c = new Character((char) b);
		int x = b & 0xFF;	//unsigned!
		
		//caratteri (stringhe) speciali
		if(x >= 127)
			return new String("[Reserved character for future use]");
		if(x <= 32)
			return BleCodes.ES_UrlDomain(s);
		
		//altrimenti, ritorna il singolo carattere ASCII
		return (new StringBuffer()).append(c).toString();
	}



	// GETTERs
	public int getTxPower() { return txPower; }
	public String getURLScheme() { return URLScheme; }
	public String getURL() { return URL; }

}
