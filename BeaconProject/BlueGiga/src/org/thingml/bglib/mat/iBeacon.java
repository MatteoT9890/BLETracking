package org.thingml.bglib.mat;

public class iBeacon {
	
	private String MAC;
	private String UUID;
	private String type;
	private int major;
	private int minor;
	private int power;
		
	
	public iBeacon(String MAC, String UUID, String type, int major, int minor, int power) {
		// TODO Auto-generated constructor stub
		this.MAC=MAC;
		this.UUID=UUID;
		this.type=type;
		this.major=major;
		this.minor=minor;
		this.power=power;
	}

	public String getMAC() {
		return this.MAC;
	}
	
	public String getUUID() {
		return UUID;
	}
	
	public String getType() {
		return type;
	}
	
	public int getMajor() {
		return major;
	}
	
	public int getMinor() {
		return minor;
	}
	
	public int getPower() {
		return power;
	}
	
	public String detectionKey() {
		return Integer.toString(major) + "M" + Integer.toString(minor);
	}
	
	public void iBeacon_infoPrint() {
		System.out.println("\niBeacon INFO:\n" + 
						"\tUUID:" + this.UUID + "\n" + 
						"\tMAC:" + this.MAC + "\n" + 
						"\tMAJOR:" + this.major + "\n" + 
						"\tMINOR:" + this.minor + "\n" + 
						"\tTYPE:" + this.type + "\n" + 
						"\tRSSI AL METRO:" + this.power);
		
	}
	
	public String getInfo() {
		return "\niBeacon INFO:\n" + 
				"\tUUID:" + this.UUID + "\n" + 
				"\tMAC:" + this.MAC + "\n" + 
				"\tMAJOR:" + this.major + "\n" + 
				"\tMINOR:" + this.minor + "\n" + 
				"\tTYPE:" + this.type + "\n" + 
				"\tRSSI AL METRO:" + this.power;
	}
	
}
