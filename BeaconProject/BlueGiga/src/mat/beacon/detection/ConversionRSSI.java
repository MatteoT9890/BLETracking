package mat.beacon.detection;

import java.util.ArrayList;

import mat.beacon.classes.Packet;

public class ConversionRSSI {

	private int stopConversion;
	private int counter;
	private int RSSITotal;
	private int RSSITotalConverted;
	private boolean isCalibrato;
	private final int calibramento;
	private int raggiungiCalibramento;
	private int countForResponse;
	private int countForChecking;
	private int response;
	private int checking;
	private ResponseStatus responseStatus;
	private String position;
	
	static enum ResponseStatus{
		CALCULATING,
		CHECKING,
	}
	
	public ConversionRSSI(int stopConversion){
		this.stopConversion=stopConversion;
		counter=0;
		RSSITotal=0;
		isCalibrato=false;
		calibramento=5;
		raggiungiCalibramento=0;
		RSSITotalConverted=0;
		this.countForResponse=0;
		this.countForChecking=0;
		this.response=5;
		this.checking=2;
		this.responseStatus = ResponseStatus.CALCULATING;
		
	}
	
	public int convertiRSSI(Packet pack){
		counter++;
		RSSITotal += pack.getRSSI();
		
		if (counter==stopConversion){
			int calculatedRSSI=RSSITotal/stopConversion;
			resetting();
			return calculatedRSSI;
		}
		
		return 100;
	}
	private void resetting() {
		// TODO Auto-generated method stub
		counter=0;
		RSSITotal=0;
	}

	public int getStopConversion() {
		return stopConversion;
	}

	public int getCounter() {
		return counter;
	}

	public int getRSSITotal() {
		return RSSITotal;
	}

	public boolean isCalibrato() {
		return isCalibrato;
	}

	public int getCalibramento() {
		return calibramento;
	}

	public int getRaggiungiCalibramento() {
		return raggiungiCalibramento;
	}

	public int calibra(Packet pack) {
		// TODO Auto-generated method stub
	int rssi=0;	
	
	if (this.raggiungiCalibramento<this.calibramento){
		rssi=this.convertiRSSI(pack);
		if (rssi!=100){
			this.RSSITotalConverted+=rssi;
			this.raggiungiCalibramento++;
			return 100;
		}			
	}
	else{
		rssi= this.RSSITotalConverted/this.calibramento;
		this.isCalibrato=true;
	}
	
	return rssi;
				
	}
	
	
	public String convert(Packet pack,ArrayList<RssiToMeters> rssi2m){
		
		int rssi;
		String position2check;
		
		if (this.responseStatus == ResponseStatus.CALCULATING)
			if ((rssi = convertiRSSI(pack))!=100){
				this.responseStatus=ResponseStatus.CHECKING;
				position = getPosition(rssi2m,rssi);
				this.countForResponse++;
				return null;
			}
		
		if (this.responseStatus == ResponseStatus.CHECKING){
			if ((rssi = convertiRSSI(pack))!=100){
				position2check = getPosition(rssi2m,rssi);
				
				if (!position2check.equals(position)){
					
					this.countForChecking++;
					
					if (this.countForChecking==2){
						this.countForChecking=0;
						this.countForResponse=0;
						this.responseStatus=ResponseStatus.CALCULATING;
						position = position2check;
						return position;
					}
					
				}
				else{
					this.countForChecking=0;
					this.countForResponse++;
					
					if (this.countForResponse==5){
						this.countForResponse=0;
						this.responseStatus=ResponseStatus.CALCULATING;
						return position;
					}
				}
			}
		}
		
		return null;
	}
	
	private String getPosition(ArrayList<RssiToMeters> rssiToMeters,int rssi) {
		// TODO Auto-generated method stub
		
		
		String position = null;
		for (RssiToMeters rssi2m : rssiToMeters){
			position = rssi2m.getPosition(rssi);
			if (position!=null){
				return position;
			}			
		}
		
	return position;	
	}
	public String getPosition(){
		return position;
	}
	public void setPosition(String p){
		position = p;
	}
	
	public int getRSSITotalCalibrated() {
		return RSSITotalConverted;
	}

	public int getCountForResponse() {
		return countForResponse;
	}

	public int getCountForChecking() {
		return countForChecking;
	}

	public int getResponse() {
		return response;
	}

	public int getChecking() {
		return checking;
	}

	
	
}
