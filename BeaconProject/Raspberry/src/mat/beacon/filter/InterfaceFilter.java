package mat.beacon.filter;

public class InterfaceFilter {
	
	private ResponseFilter responseFilter;
	private MyPacketFilter myPacketFilter;
	
	/**
	 * Crea l'Interfaccia per gestire i filtraggi dei pacchetti. <br/>
	 * In particolare il filtraggio effettuato sull'output del comando hcidump è relativo ai MAC presenti nel file /home/pi/BeaconProject/Beacon_Filter.txt
	 * 
	 * @author Matteo Tarantino
	 */
	public InterfaceFilter(){
		responseFilter = new ResponseFilter();
		myPacketFilter = new MyPacketFilter();
	}

	public ResponseFilter getResponseFilter() {
		return responseFilter;
	}

	public MyPacketFilter getMyPacketFilter() {
		return myPacketFilter;
	}
	
	

}
