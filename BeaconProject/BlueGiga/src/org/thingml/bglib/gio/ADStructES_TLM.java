package org.thingml.bglib.gio;

/**
 * AD specifica per dispositivi Eddystone: informazione di telemetria (TLM)
 * @author Giorgio Avalle
 */
public class ADStructES_TLM extends ADStructES {
	
	//campi specifici
	private int version;		// 1B
	private int batteryLevel;	// 2B: 0x0000 = not supported
	private int temp;			// 2B: 0x8000 = not supported
	private int adv_cnt;		// 4B: contatore eventi advertisement
	private int sec_cnt;		// 4B: tempo dal primo avvio, in unità di 0.1 secondi
	

	public ADStructES_TLM(byte type, byte[] content, String mac) {
		super(type, content, mac);
		
		version = BleUtil.uint8(content[3]);
		batteryLevel = BleUtil.uint16(content[4], content[5]);
		temp = createTemp(content[6], content[7]);
		adv_cnt = BleUtil.uint32(content[8], content[9], content[10], content[11]);
		sec_cnt = BleUtil.uint32(content[12], content[13], content[14], content[15]);
	}

	
	/**
	 * Crea il valore di temperatura, in Celsius
	 * @param integ è la parte intera, con segno
	 * @param decim è la parte decimale
	 * @return la temperatura in Celsius
	 */
	private int createTemp(byte integ, byte decim) {
		return (BleUtil.int8(integ)*100 + BleUtil.uint8(decim));
	}

	@Override
	public void printOver() {
		System.out.println("Versione TLM: " + getVersion());
		System.out.println("livello batteria: " + getBattery());
		System.err.println("Temperatura: " + getTempCelsius());
		System.out.println("Contatore ADV: " + getAdv_cnt());
		System.out.println("Timer: " + getTime());
	}

	/**
	 * Ritorna il livello della batteria, se supportato
	 * @return
	 */
	private String getBattery() {
		if(getBatteryLevel() == 0)
			//0x0000 = not supported
			return new String("funzionalità non supportata");
		
		return new String(getBatteryLevel() + " mV");
	}

	/**
	 * Ritorna la temperatura misurata in gradi Celsius, se supportata
	 * @return String
	 */
	private String getTempCelsius() {
		Double res = new Double(getTemp() / 100);
		
		if(this.temp == 32768)
			//0x8000 = not supported
			return new String("funzionalità non supportata");
		
		return new String(res + " °C");
	}

	/**
	 * Ricava il tempo trascorso, scritto per esteso
	 * @return String
	 */
	private String getTime() {
		//contatore in secondi
		int x = getSec_cnt() / 10;
		
		double sec = (new Double(sec_cnt/10)) % 60;
		
		//contatore in minuti
		x /= 60;
		int minuti = x % 60;
		
		//contatore in ore
		x /= 60;
		int ore = x % 60;
		
		//contatore in giorni
		x /= 24;
		int giorni = x;
		
		
		StringBuffer res = new StringBuffer("");
		if(giorni != 0)
			res.append(giorni + " d, ");
		if(ore != 0)
			res.append(ore + " h, ");
		if(minuti != 0)
			res.append(minuti + " m, ");
		res.append(sec + " s");
		
		return res.toString();
	}


	

	// GETTERs
	public int getVersion() { return version; }
	public int getTemp() {return temp; }
	public int getBatteryLevel() { return batteryLevel; }
	public int getAdv_cnt() { return adv_cnt; }
	public int getSec_cnt() { return sec_cnt; }	

}
