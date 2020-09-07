package mat.beacon.classes;

public class Beacon {
	
	private String MAC;
	private String costruttore;
	private String feature;
	
	private double distanceTest;
		
	
	public Beacon(String MAC, String costruttore,String feature) {
		// TODO Auto-generated constructor stub
		this.MAC=MAC;
		this.costruttore=costruttore;
		this.feature=feature;
	}
	
	
	public String getMAC() {
		return MAC;
	}


	public String getCostruttore() {
		return costruttore;
	}


	public String getFeature() {
		return feature;
	}


	public double getDistanceTest() {
		return distanceTest;
	}


	public void setDistanceTest(double distanceTest) {
		this.distanceTest = distanceTest;
	}
	
	
	

	
}
