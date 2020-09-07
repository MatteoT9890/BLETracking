package mat.beacon.test;

import mat.beacon.classes.Beacon;

public class TestResult {
		
	private Beacon beacon;
	private double seconds;
	private int RSSI;
	private int skewRSSI;
	private int maxRSSI;
	private int minRSSI;
	private double distance;
	private String valoriRSSI;
	
	public TestResult(Beacon beacon, BeaconTesting beaconTesting){
		this.beacon=beacon;
		seconds = ((double)(beaconTesting.getTimeEndTest() - beaconTesting.getTimeStartTest()))/1000;
		RSSI= beaconTesting.getRSSITotal()/beaconTesting.getStopTest();
		maxRSSI = beaconTesting.getRSSImax();
		minRSSI= beaconTesting.getRSSImin();
		skewRSSI = beaconTesting.getRSSImax() - beaconTesting.getRSSImin();
		valoriRSSI = beaconTesting.getRSSIList();
		beaconTesting.clearRSSIList();
		this.distance=beacon.getDistanceTest();
	}

	public Beacon getBeacon() {
		return beacon;
	}

	public double getSeconds() {
		return seconds;
	}

	public int getRSSI() {
		return RSSI;
	}

	public int getSkewRSSI() {
		return skewRSSI;
	}

	public double getDistance() {
		return distance;
	}
	
	public String getInfoTestResult(){
		return 
				"\tSecondi:" + this.seconds +
				"\tRSSI:" + this.RSSI +
				"\tSkew RSSI(Misura di imprecisione):" + this.skewRSSI +
				"\tValori RSSI:" + this.valoriRSSI;
	}

}
