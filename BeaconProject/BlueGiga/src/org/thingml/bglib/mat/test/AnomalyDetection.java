package org.thingml.bglib.mat.test;

import java.util.HashMap;

import org.thingml.bglib.mat.Packet_iBeacon;
import org.thingml.bglib.mat.iBeaconToMonitorize;

public class AnomalyDetection {
	
	Packet_iBeacon packet;
	
	public boolean Detection(HashMap<String,iBeaconToMonitorize> iBeaconToMonitorizeMap){
		
		if (packet.getRSSI() < iBeaconToMonitorizeMap.get(packet.getiBeacon().detectionKey()).getLimit())
			return true;
		
		return false;
			
			
	}

}
