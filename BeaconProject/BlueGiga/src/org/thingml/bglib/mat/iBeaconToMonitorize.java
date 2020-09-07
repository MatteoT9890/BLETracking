package org.thingml.bglib.mat;

public class iBeaconToMonitorize {
	
	private String key;
	private int limit;
	private String UUID;
	
	public iBeaconToMonitorize(String key, int limit,String uuid) {
		this.key=key;
		this.limit=limit;
		this.UUID=uuid;
	}

	public String getKey() {
		return this.key;
	}
	public int getLimit() {
		return limit;
	}
	
	public String getUUID() {
		return this.UUID;
	}
	
	
	

}
