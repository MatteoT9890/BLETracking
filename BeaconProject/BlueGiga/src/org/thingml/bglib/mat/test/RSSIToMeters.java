package org.thingml.bglib.mat.test;

import java.util.HashMap;

public class RSSIToMeters {
	
	private int calculatedRSSI;
	private static HashMap<String,UUIDToRSSI> RSSIMap = new HashMap<String,UUIDToRSSI>();
	
	/**
	 * Setto i parametri relativi a :
	 * -distanza tra Antenna e iBeacon
	 * -Altezza rispetto al pavimento di Antenna e iBeacon
	 * -Materiali su cui i beacon sono poggiati
	 * 
	 * @param distanza
	 * @param altezza antenna
	 * @param altezza iBeacon
	 * @param Descrizione posizione beacon
	 * @param UUID 
	 * @param boolean SeparatiDaParete
	 * 
	 *  @author Matteo Tarantino
	 *  @return void
	 */
	public void setParam(double d, double e, double f, String string,String UUID,boolean x) {
		RSSIMap.put(UUID, new UUIDToRSSI(d,e, f, string,x));
		calculatedRSSI=5000;
	}
	
	/**
	 * Dopo aver settato i parametri calcolo l'RSSI corrispondente a quei parametri.
	 * 
	 * @author Matteo Tarantino
	 * @param RSSI
	 * @param UUID
	 */
	
	public int associa(int RSSI,String UUID) {
		if(!RSSIMap.containsKey(UUID)) { 
			System.out.println("iBeacon non trovato. Settare prima i parametri per tale iBeacon attraverso il metodo"
					+ "setParam");
			return 1000;
		}
		
		if (RSSIMap.get(UUID).getCounter()==10) {
			this.calculatedRSSI= RSSIMap.get(UUID).getRSSI()/RSSIMap.get(UUID).getCounter();
			RSSIMap.get(UUID).setCounter(0);
			RSSIMap.get(UUID).setRSSI(0);
			//this.print_InfoTest(UUID);
			return this.calculatedRSSI;
		}
		
		if (RSSIMap.get(UUID).counter>=0 && RSSIMap.get(UUID).getCounter()<10) {
			RSSIMap.get(UUID).AddRSSI(RSSI);
			RSSIMap.get(UUID).incrementCounter();
			return 1000;
		}
		
		return 1000;
	}
	
	public void print_InfoTest(String uuid) {
		System.out.println("\nTESTING INFO:\n" + 
							"\tUUID:" + uuid );
		System.out.println( RSSIMap.get(uuid).infoMaterial() +
						    "\tRSSI associato:" + calculatedRSSI + "\n\n"
						     );
	}
	

	public double getCalculatedDRSSI() {
		return calculatedRSSI;
	}

	
	public class UUIDToRSSI {
		int RSSI;
		int counter;
		private double heightReceiver;
		private double heightTransmitter;
		private double distance;
		private String description;
		private boolean separatiDaParete;
		
		public UUIDToRSSI(double d, double e, double f, String string, boolean x) {
			RSSI=0;
			counter=0;
			heightReceiver=e;
			heightTransmitter=f;
			distance=d;
			description=string;
			separatiDaParete=x;
		}
		public int getRSSI() {
			return RSSI;
		}
		public void setRSSI(int rSSI) {
			RSSI = rSSI;
		}
		public int getCounter() {
			return counter;
		}
		public void setCounter(int counter) {
			this.counter = counter;
		}
		
		public void AddRSSI(int r) {
			RSSI+=r;
		}
		
		public void incrementCounter() {
			counter++;
		}
		
		public String getDescription() {
			return description;
		}

		public double getHeightReceiver() {
			return heightReceiver;
		}

		public double getHeightTransmitter() {
			return heightTransmitter;
		}

		public double getDistance() {
			return distance;
		}
		
		public boolean getSeparati() {
			return this.separatiDaParete;
		}
		
		public String infoMaterial() {
			return "\tAltezza trasmettitore:" + heightTransmitter + "\n" +
				    "\tAltezza ricevitore:" + heightReceiver + "\n" +
				    "\tDescrizione:" + description + "\n" +
				    "\tSeparati da parete:" + separatiDaParete + "\n\n" +
				    "\tDistanza(metri):" + distance;
		}
	}
}
