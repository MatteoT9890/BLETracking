package org.thingml.bglib.gio;

/**
 * AD Struct Produttore per i dispositivi iBLio (eccetto IOTS)
 * @author Giorgio Avalle
 */
public class ADStructManufacturer_iBLio_N05 extends ADStructManufacturer_iBLio {

	//campi specifici per l'iBLio N05
	private int batteryLevel;	//2 Byte
	private int moduleTemp100;	//2B: temp (Celsius) x 100
	private int sensorTemp100;	//2B: temp (Celsius) x 100
	private int sensorHum100;	//2B: umidità x 100
	private int oneMinuteSC;	//4B: one minute cnt scan requests
	
	
	public ADStructManufacturer_iBLio_N05(byte type, byte[] content, String mac) {
		super(type, content, mac);
		
		this.batteryLevel = BleUtil.uint16(content[6], content[7]);
		createMTemp(content[8], content[9]);
		createSTemp(content[10], content[11]);
		this.sensorHum100 = BleUtil.uint16(content[12], content[13]);
		this.oneMinuteSC = BleUtil.uint32(content[14], content[15], content[16], content[17]);
	}
	
	
	@Override
	public void printOver2() {
		System.out.println("Livello della batteria: " + getBatteryLevel() + " mV");
		System.out.println("Temperatura interna del modulo: " + (new Float(getModuleTemp100()))/100 + " °C");
		System.out.println("Temperatura rilevata dal sensore: " + (new Float(getSensorTemp100()))/100 + " °C");
		System.out.println("Umidità rilevata dal sensore: " + (new Float(getSensorHum100()))/100 + " %");
		System.out.println("One-minute cnt scan requests: " + getOneMinuteSC());
	}

	
	/**
	 * Crea il campo "Sensor Temperature x 100", da little endian
	 * @param b è il primo byte
	 * @param c è il secondo byte
	 */
	private void createSTemp(byte b, byte c) {
		//TODO: trovato errore... manca il caso temp con segno!
		this.sensorTemp100 = BleUtil.uint16(b, c);
	}


	/**
	 * Crea il campo "Module Temperature x 100", da little endian
	 * @param b è il primo byte
	 * @param c è il secondo byte
	 */
	private void createMTemp(byte b, byte c) {
		//TODO: trovato errore... manca il caso temp con segno!
		this.moduleTemp100 = BleUtil.uint16(b,  c);
	}


	
	// GETTERs
	public int getBatteryLevel() { return batteryLevel; }
	public int getModuleTemp100() { return moduleTemp100; }
	public int getSensorTemp100() { return sensorTemp100; }
	public int getSensorHum100() { return sensorHum100; }
	public int getOneMinuteSC() { return oneMinuteSC; }
	
}
