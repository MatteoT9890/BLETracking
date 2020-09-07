package org.thingml.bglib.gio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Questa classe mantiene traccia in memoria di tutti i dati raccolti dai dispositivi BLE
 * @author Giorgio Avalle
 */
public class BleMemory {
	//tutti i pacchetti ricevuti
	private Map<Integer, BleDataADV> packets;
			
	//numero di pacchetti in memoria
	private int np;
	
	//elenco di dispositivi rilevati: le chiavi sono indirizzi MAC esadecimali
	private Map<String, BleDisp> dispositivi;
	
	//whitelist indirizzi MAC esadecimali
	private Set<String> whiteList;
	private boolean enableWL;	//whitelist MAC abilitata
	
	//nome del file di input per la whitelist
	private static final String FILE_IN = "WL_input.txt";
	
	/**
	 * Implementazione base, senza whitelist di indirizzi MAC
	 */
	public BleMemory() {
		this.packets = new HashMap<Integer, BleDataADV>();
		this.np = 0;
		this.dispositivi = new TreeMap<>();
		this.enableWL = false;
	}
	
	/**
	 * Implementazione avanzata, con possibilità di definire una whitelist di indirizzi MAC
	 * @param enable se true, abilita la whitelist
	 */
	public BleMemory(boolean enable) {
		this.packets = new HashMap<Integer, BleDataADV>();
		this.np = 0;
		this.dispositivi = new TreeMap<>();
		this.enableWL = enable;
		if(enable)
			this.whiteList = new TreeSet<>();
		populateWhiteList();
	}



	/**
     * Salva un pacchetto ricevuto (nello specifico, la PDU formattata secondo la classe BleData) 
     * dentro la struttura dati
     * 
     * @param data è il pacchetto (BleData)
     */
	public void savePacket(BleDataADV data) {
		packets.put(new Integer(++np), data);
	}

	//TODO: in realtà, non occorre accumulare i dati. Piuttosto, sbattili su CSV
	
	
	/** 
     * Aggiungi un nuovo dispositivo, se non ancora rilevato
     * @param mac è un indirizzo MAC esadecimale
     */
	public void addDisp(String mac) {
		//il dispositivo non deve già essere stato rilevato
    	if(dispositivi.containsKey(mac))
    		return;
    	
    	//crea associazione
    	dispositivi.put(mac, new BleDisp(mac));
	}
	
	/**
     * Ottieni un dispositivo esistente (altrimenti 'null') a partire dal suo indirizzo MAC
     * @param mac è un indirizzo MAC esadecimale
     * @return DispositivoBLE
     */
    public BleDisp getDisp(String mac) {
    	if(dispositivi.containsKey(mac))
    		return dispositivi.get(mac);
    	
    	//se non esiste l'associazione mac-dispositivo, ritorno null
    	System.err.println("Questo indirizzo MAC non è associato ad alcun dispositivo -> " + mac);
    	return null;
    }
    
    /**
     * Popola la whitelist di indirizzi MAC: se non ci sono indirizzi, allora la disabilita (ritornando un warning)
     */
    public void populateWhiteList(){
    	FileReader f = null;
    	BufferedReader b = null;
    	String s;
    	int n = 0;		//numero di indirizzi letti
    	
    	try {
			f = new FileReader(FILE_IN);
			b = new BufferedReader(f);
			
			while(true){
				s = b.readLine();	//leggo un indirizzo
			    if(s == null)
			      break;	//fine del file
			    
			    //validità del MAC
			    if(BleUtil.isCorrectMAC(s)){
				    this.whiteList.add(s);		//lo aggiungo alla whitelist
				    n++;
			    }
			    else
			    	System.err.println("ATTENZIONE - L'indirizzo MAC " + s + " letto da file non è valido. Pertanto, verrà ignorato");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	//se la whitelist è vuota, allora la disabilito
    	if (n == 0){
    		this.enableWL = false;
    		System.err.println("ATTENZIONE - Non ho rilevato indirizzi MAC validi. La whitelist viene pertanto disattivata");
    	}
    	
    	
    	//chiudo i file aperti
    	try {
			b.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }
    
    /**
     * Mi dice se la whitelist di indirizzi MAC è implementata
     * @return
     */
    public boolean isWhiteListEnabled(){
    	return this.enableWL;
    }

    /**
     * Mi dice se l'indirizzo MAC (esadecimale formattato) è un indirizzo contenuto nella whitelist
     * @param mac è l'indirizzo MAC da verificare
     * @return
     */
	public boolean hasWL(String mac) {
		return this.whiteList.contains(mac);
	}

}
