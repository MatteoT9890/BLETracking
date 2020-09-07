package org.thingml.bglib.gio;

import java.util.ArrayList;
import java.util.regex.Pattern;


/**
 * Questa classe permette di identificare un dispositivo IoT partendo dal suo codice MAC.
 * Utile per l'analisi dei dati trasportati in advertising sulle strutture AD a contenuto personalizzabile (es: ADStructManufacturer)
 * @author Giorgio Avalle
 */
public class BleDisp {
	private String mac;		//esadecimale formattato
	private boolean init = false;
	private boolean toInit = false;
	private BleDispType type = BleDispType.NOPE;
	private ArrayList<BleDataADV> coda = new ArrayList<>();
	
	
	public BleDisp(String mac) {
		this.mac = mac;
	}
	
	
	
	/**
	 * Tutti i pacchetti ricevuti prima di un pacchetto "Complete Local Name" vengono messi in coda ed eseguiti 
	 * non appena questo viene catturato
	 * @param data è il pacchetto BLE da accodare
	 */
	public void freezePacket(BleDataADV data) {
		this.coda.add(data);
	}
	
	/**
	 * Permette di inizializzare il dispositivo, elaborando tutti i pacchetti finora messi in coda
	 */
	public void init() {
		//elabora tutti i pacchetti attualmente in coda
		this.coda.stream().forEach(BleDataADV::elabora);
		
		//svuota la coda
		this.coda.clear();
		
		//setta come dispositivo inizializzato
		this.init = true;
		this.toInit = false;
	}
	
	/**
	 * Indica se è già stato ricevuto un pacchetto un pacchetto "Complete Local Name" (oppure "3 3 AA FE", per l'Eddystone)
	 * @return boolean
	 */
	public boolean isInit() {
		return this.init;
	}

	/**
	 * Indica se il dispositivo è da inizializzare
	 * @return boolean
	 */
	public boolean isToInit() {
		return this.toInit;
	}
	
	/**
	 * Ritorna l'indirizzo MAC esadecimale del dispositivo
	 * @return String
	 */
	public String getMac() {
		return this.mac;
	}
	
	/**
	 * Permette di definire il tipo di dispositivo a partire dal suo "Complete Local Name"
	 * @param cln è il "Complete Local Name"
	 */
	public void setType(String cln) {
		//il dispositivo andrà inizializzato
		this.toInit();
		
		if(Pattern.matches("iBLio i6216 \\[.*\\]", cln)) {
			this.type = BleDispType.iBLio_IOTS;
			return;
		}
		if(Pattern.matches("iBLio i5915 \\[.*\\]", cln)) {
			this.type = BleDispType.iBLio_N05;
			return;
		}
		if(Pattern.matches(".*iBeacon.*", cln)) {
			this.type = BleDispType.iBeacon;
			return;
		}
		if(Pattern.matches("ES .*", cln)) {
			//ATTENTO: se modifichi il pattern, allora devi modificarlo anche in BleData, quando imposti il tipo "ES" per un pacchetto "3 3 AA FE"
			this.type = BleDispType.ES;
			return;
		}
		
		//pattern non riconosciuto
		this.type = BleDispType.UNKNOWN;
	}
	
	/**
	 * Ottieni il tipo di dispositivo IoT (UNKNOWN se non riconosciuto, NOPE se non ancora definito)
	 * @return DispType
	 */
	public BleDispType getType() {
		return this.type;
	}

	/**
	 * Indica che il dispositivo è da inizializzare con chiamata init()
	 */
	public void toInit() {
		this.toInit = true;
	}
}
