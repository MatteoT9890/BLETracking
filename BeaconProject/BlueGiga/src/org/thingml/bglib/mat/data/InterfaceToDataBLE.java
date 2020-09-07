package org.thingml.bglib.mat.data;

import java.util.HashMap;

import org.thingml.bglib.mat.Packet_iBeacon;
import org.thingml.bglib.mat.iBeacon;
import org.thingml.bglib.mat.iBeaconToMonitorize;

public class InterfaceToDataBLE {
	
	private HashMap<String,iBeacon> iBeaconData = new HashMap<String,iBeacon>();
	private HashMap<String,iBeaconToMonitorize> iBeaconToMonitorizeMap=new HashMap<String,iBeaconToMonitorize>();
	private HashMap<String,Packet_iBeacon> Packet_iBeaconMap = new HashMap<String,Packet_iBeacon>();
	
	public InterfaceToDataBLE() {
		Data_iBeacon.popola(iBeaconData);
		Data_iBeaconToMonitorize.popola(iBeaconToMonitorizeMap);
	}
	
	public void print() {
		System.out.println();
	}

	public HashMap<String, iBeacon> getiBeaconData() {
		return iBeaconData;
	}

	public HashMap<String, iBeaconToMonitorize> getiBeaconToMonitorizeMap() {
		return iBeaconToMonitorizeMap;
	}

	public HashMap<String, Packet_iBeacon> getPacket_iBeaconMap() {
		return Packet_iBeaconMap;
	}
	
	/**
	 * Inserisco il pacchetto generato nel database che ne tiene traccia.
	 * 
	 * @author Matteo Tarantino
	 * @param pack
	 * @return
	 */
	public boolean insertPacket_iBeacon (Packet_iBeacon pack) {
		
		this.Packet_iBeaconMap.put(pack.Timestamp2key(), pack);
		if (this.Packet_iBeaconMap.get(pack.Timestamp2key())!=null)
			return true;
		
		return false;
	}
	
	
	
	

}
