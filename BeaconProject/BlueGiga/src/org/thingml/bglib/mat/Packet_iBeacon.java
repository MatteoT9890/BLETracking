package org.thingml.bglib.mat;

import java.sql.Timestamp;
import java.util.HashMap;


public class Packet_iBeacon {
	
	private String type;
	private int RSSI;
	private int length;
	private iBeacon ibeacon;
	private Timestamp timestamp;
	private boolean anomaly;
	private double ratio;
	
	public Packet_iBeacon(int RSSI, int length, iBeacon iB,String type,Timestamp timestamp) {
		this.RSSI=RSSI;
		this.length=length;
		this.ibeacon=iB;
		this.type=type;
		this.timestamp = timestamp;
		this.anomaly=false;
		this.ratio=this.RSSI/iB.getPower();
	}
	
	public double getRatio() {
		return this.ratio;
	}
	
	public Timestamp getTimestamp() {
		return this.timestamp;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getRSSI() {
		return RSSI;
	}
	public int getLength() {
		return length;
	}

	public iBeacon getiBeacon() {
		return ibeacon;
	}
	
	public String Timestamp2key() {
		return timestamp.toString();
	}
	
	
	/**
	 *  Stampa il timestamp del pacchetto e se è stata rilevata l'anomalia.
	 *  @author Matteo Tarantino
	 *  @return void
	 */
	
	public void print(HashMap<String,iBeaconToMonitorize> iBeaconToMonitorizeMap) {
		System.out.println("\nMONITORAGGIO");
		System.out.println("\tPacchetto ricevuto:" + timestamp);
		
		if (this.anomaly==true)
			System.out.println("\tAnomalia rilevata! L'oggetto con UUID: " + this.ibeacon.getUUID() + " ha un RSSI di: " + this.RSSI + 
					" quando il suo limite è: " + iBeaconToMonitorizeMap.get(this.ibeacon.detectionKey()).getLimit());
		else 
			System.out.println("\tNessun anomalia rilevata. L'oggetto ha infatti un RSSI di: " + this.RSSI +
					"quando il suo limite è: " +iBeaconToMonitorizeMap.get(this.ibeacon.detectionKey()).getLimit());
	}

	/**
	 * Generato il pacchetto, lo monitoro verificando se ho riscontrato un'anomalia.
	 * 
	 * @author Matteo Tarantino
	 * @param iBeaconToMonitorizeMap
	 * @return boolean
	 */
	
public boolean AnomalyDetection(HashMap<String,iBeaconToMonitorize> iBeaconToMonitorizeMap){
		
	
	iBeaconToMonitorize iB = iBeaconToMonitorizeMap.get(this.ibeacon.detectionKey());
	if (iB!=null) {
		if (this.RSSI < iB.getLimit()) {
			this.anomaly=true;
			this.print(iBeaconToMonitorizeMap);
		}
	}	
	else {
		System.out.println("Corrispondenza non rilevata tra: " + this.ibeacon.detectionKey() + " e la coppia "
				+ "majorMminor presente in iBeaconToMonitorizeMap");
	}
		return this.anomaly;			
	}
	
	public void print_InfoPacket() {
		System.out.println(
				this.ibeacon.getInfo() + "\n\n" +
				"Packet INFO:\n" +
				"\tLUNGHEZZA PACCHETTO:" + this.length + "\n" + 
				"\tTIPO PACCHETTO:" + this.type + "\n" + 
				"\tRSSI:" + this.RSSI + "\n");

	}
}

