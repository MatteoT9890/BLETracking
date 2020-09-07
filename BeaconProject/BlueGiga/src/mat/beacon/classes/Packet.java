package mat.beacon.classes;



public class Packet{
	
	private int RSSI;
	private Beacon beacon;
	private String timestamp;
	
	
	public Packet(String timestamp, Beacon B,int RSSI) {
		this.RSSI=RSSI;
		this.beacon=B;
		this.timestamp = timestamp;
	}
	
	
	public int getRSSI() {
		return RSSI;
	}


	public Beacon getBeacon() {
		return beacon;
	}


	public String getTimestamp() {
		return timestamp;
	}

	public String getInfoPacket() {
		return
				"Packet INFO:\n" +
				"\tTimestamp:" + this.timestamp + "\n"	+
				"\tMAC:" + this.beacon.getMAC() + "\n" + 
				"\tCostruttore:" + this.beacon.getCostruttore() + "\n" + 
				"\tCaratteristica Beacon:" + this.beacon.getFeature() + "\n" +
				"\tRSSI:" + this.RSSI + "\n";
		
	}

//	public String Timestamp2key() {
//		return timestamp.toString();
//	}
//	
//	
//	/**
//	 *  Stampa il timestamp del pacchetto e se è stata rilevata l'anomalia.
//	 *  @author Matteo Tarantino
//	 *  @return void
//	 */
//	
//	public void print(HashMap<String,iBeaconToMonitorize> iBeaconToMonitorizeMap) {
//		System.out.println("\nMONITORAGGIO");
//		System.out.println("\tPacchetto ricevuto:" + timestamp);
//		
//		if (this.anomaly==true)
//			System.out.println("\tAnomalia rilevata! L'oggetto con UUID: " + this.ibeacon.getUUID() + " ha un RSSI di: " + this.RSSI + 
//					" quando il suo limite è: " + iBeaconToMonitorizeMap.get(this.ibeacon.detectionKey()).getLimit());
//		else 
//			System.out.println("\tNessun anomalia rilevata. L'oggetto ha infatti un RSSI di: " + this.RSSI +
//					"quando il suo limite è: " +iBeaconToMonitorizeMap.get(this.ibeacon.detectionKey()).getLimit());
//	}
//
//	/**
//	 * Generato il pacchetto, lo monitoro verificando se ho riscontrato un'anomalia.
//	 * 
//	 * @author Matteo Tarantino
//	 * @param iBeaconToMonitorizeMap
//	 * @return boolean
//	 */
//	
//public boolean AnomalyDetection(HashMap<String,iBeaconToMonitorize> iBeaconToMonitorizeMap){
//		
//	
//	iBeaconToMonitorize iB = iBeaconToMonitorizeMap.get(this.ibeacon.detectionKey());
//	if (iB!=null) {
//		if (this.RSSI < iB.getLimit()) {
//			this.anomaly=true;
//			this.print(iBeaconToMonitorizeMap);
//		}
//	}	
//	else {
//		System.out.println("Corrispondenza non rilevata tra: " + this.ibeacon.detectionKey() + " e la coppia "
//				+ "majorMminor presente in iBeaconToMonitorizeMap");
//	}
//		return this.anomaly;			
//	}
//	
}


