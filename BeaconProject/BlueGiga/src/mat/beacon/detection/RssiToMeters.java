package mat.beacon.detection;

public class RssiToMeters {
	
	private int minRSSI;
	private int maxRSSI;
	private String message;
	
	public RssiToMeters(int min, int max, String mess){
		this.minRSSI=min;
		this.maxRSSI=max;
		this.message=mess;
	}

	public int getMinRSSI() {
		return minRSSI;
	}

	public int getMaxRSSI() {
		return maxRSSI;
	}

	public String getMessage() {
		return message;
	}

	public String getPosition(int rssi) {
		// TODO Auto-generated method stub
		if (rssi >= minRSSI && rssi <= maxRSSI)
			return message;
		
		return null;
	}
	
	
	


}
