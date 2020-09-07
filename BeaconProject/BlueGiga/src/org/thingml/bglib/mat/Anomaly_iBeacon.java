package org.thingml.bglib.mat;

import java.util.HashMap;

public class Anomaly_iBeacon {
	
	private HashMap<Integer,Integer> AnomalyMap = new HashMap<Integer,Integer>();
	
	public boolean setAnomaly(Integer minor,Integer RSSI) {
		if (this.AnomalyMap.get(minor)==null) {
			AnomalyMap.put(minor, RSSI);
			return true;
		}
		
		return false;
	}
	
	public HashMap<Integer,Integer> getAnomalyMap(){
		return this.AnomalyMap;
	}
	
	public boolean testAnomaly(int minor,int RSSI2test) {
		
		int RSSI= this.AnomalyMap.get(minor);
		if (RSSI2test > RSSI)
			return true;
		
		return false;
	}

}
