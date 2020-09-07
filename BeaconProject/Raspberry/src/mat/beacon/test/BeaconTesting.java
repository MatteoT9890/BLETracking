package mat.beacon.test;


import java.util.ArrayList;

import mat.beacon.classes.Packet;

public class BeaconTesting {
	
	public BeaconTesting(int stopTest) {
		// TODO Auto-generated constructor stub
		this.stopTest = stopTest;
		this.packetAnalyzed=0;
		resetting();
		RSSIList = new ArrayList<>();
		
	}
	
	private int RSSImax;
	private int RSSImin;
	private int RSSITotal;
	private long TimeStartTest;
	private long TimeEndTest;
	private int counter;
	private int stopTest;
	private int packetAnalyzed;
	private ArrayList<Integer> RSSIList;
	

	
	public int getRSSImax() {
		return RSSImax;
	}

	public int getRSSImin() {
		return RSSImin;
	}

	public int getRSSITotal() {
		return RSSITotal;
	}

	public long getTimeStartTest() {
		return TimeStartTest;
	}

	public long getTimeEndTest() {
		return TimeEndTest;
	}

	public int getCounter() {
		return counter;
	}

	public int getStopTest() {
		return stopTest;
	}
	
	

	public int getPacketAnalyzed() {
		return packetAnalyzed;
	}

	public boolean test(Packet pack) {
		// TODO Auto-generated method stub
		if (counter==0)
			TimeStartTest = System.currentTimeMillis();
		
		counter++;
		this.RSSIList.add(pack.getRSSI());
		
		if (RSSImax < pack.getRSSI())
			RSSImax = pack.getRSSI();
		
		if (RSSImin > pack.getRSSI())
			RSSImin = pack.getRSSI();
		
		RSSITotal+=pack.getRSSI();
		
		if (counter==stopTest){
			packetAnalyzed+=stopTest;
			TimeEndTest = System.currentTimeMillis();
			return true;
		}
		
		return false;
	}
	
	public void resetting(){
		RSSImax=-1500;
		RSSImin=1500;
		RSSITotal=0;
		counter=0;
	}
	
	public String getRSSIList(){
		
		StringBuffer list = new StringBuffer();
		int i=0;
		list.append("[");
		for (Integer r : this.RSSIList){
			i++;
			if (i == this.RSSIList.size()){
				list.append(r.toString() + "]");
				continue;
			}
			list.append(r.toString() + ",");
			
		}
		
		return list.toString();
	}
	
	public void clearRSSIList(){
		this.RSSIList.clear();
	}

}
