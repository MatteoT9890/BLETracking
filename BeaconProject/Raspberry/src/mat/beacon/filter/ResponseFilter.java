package mat.beacon.filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ResponseFilter {
	
	private final String event=".*(HCI\\sEvent).*";
	private final String rssi=".*(RSSI).*";
	private final String file="/home/pi/BeaconProject/Beacon_Filter.txt";
	private ArrayList<String> MACFilterList = new ArrayList<>();
	
	public static enum Status{
		TRIGGERED,
		WAITEVENT,
		WAITTRIGGER,
		REJECT,
		CONFIRMED
	};
	
	public ResponseFilter() {
		popolaFilterList();
		this.status= Status.WAITEVENT;
	}
	private void popolaFilterList() {
		// TODO Auto-generated method stub
		try {
			BufferedReader b = new BufferedReader(new FileReader(file));
			String mac;
			while((mac = b.readLine())!=null) {
				this.MACFilterList.add(".*" + mac + ".*");
			}
			
			b.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Status status;
	
	
	public boolean match (String regex, String input) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		
		if (m.matches())
			return true;
		return false;
	}
	
	public boolean match (ArrayList<String> regex_list, String input) {
		Pattern p;
		Matcher m;
		
		for (String regex : regex_list) {
		p = Pattern.compile(regex);
		m = p.matcher(input);		
		if (m.matches())
			return true;
		}
		return false;
	}
	
	public Status isBeacon(String line) {

		switch(this.status) {
		
		case WAITEVENT:
			if (match(event,line)) 
				this.status= Status.WAITTRIGGER; 			
			return this.status;
			
		case WAITTRIGGER:
			if (!match(rssi,line))
				if(match(this.MACFilterList,line)) {
				this.status=Status.TRIGGERED;
				return this.status;
			}
			if (match(rssi,line)) {
				this.status=Status.WAITEVENT;
				return Status.REJECT;
			}
			
		case TRIGGERED:
			if (match(rssi,line)) {
				this.status=Status.WAITEVENT;
				return Status.CONFIRMED;
			}
			
		default:
			return Status.WAITEVENT;
		}
		
		
	}
	
	
}
