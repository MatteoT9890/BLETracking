package mat.beacon.filter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyPacketFilter {
	
	private final String Eventmatch=".*(HCI\\sEvent).*";
	private final String MACmatch = "^\\s*\\w+\\s?([0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2}:[0-9A-F]{2})(.*)$";
	private final String RSSImatch = "^\\s*(RSSI:)\\s*([-0-9]+)";
	private ArrayList<String> CostruttoreMatch;
	private ArrayList<String> result;
	
	public MyPacketFilter(){
		result = new ArrayList<>();
		CostruttoreMatch = new ArrayList<>();
		CostruttoreMatch.add("^\\s*(Unknown).*$");
		CostruttoreMatch.add("^\\s*.*(iBLio).*$");
	}

	/**
	 * Confermato il pacchetto di hcidump dalla classe ResponseFilter, ne ricavo le informazioni utili per generare il mio pacchetto customizzato. 
	 * 
	 * @author Matteo Tarantino
	 * @param packet
	 * @return Timestamp, MAC, e RSSI del pacchetto contenuti in una lista di stringhe. <br/> I parametri elencati sono inseriti in ordine nella lista.
	 */
	
	public ArrayList<String> getResults(ArrayList<String> packet){
		result.clear();
		Pattern pEvent=Pattern.compile(Eventmatch);
		Pattern pMAC=Pattern.compile(MACmatch);
		Pattern pRSSI=Pattern.compile(RSSImatch);
		Matcher mEvent,mMAC,mRSSI;
				
		for (String p : packet){
			mEvent=pEvent.matcher(p);
			mMAC = pMAC.matcher(p);
			mRSSI=pRSSI.matcher(p);
			
			if (mEvent.matches()){
				String[] splitted = p.split(" ");
				result.add(splitted[0] + " " + splitted[1]);
				continue;
			}
			
			if (mMAC.matches()){
				result.add(mMAC.group(1));
				continue;
			}
			
			if (mRSSI.matches()){
				result.add(mRSSI.group(2));
				continue;
			}
			
			
		}
		
		return result;
		
	}
	

}
