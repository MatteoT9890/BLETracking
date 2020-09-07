package mat.beacon.detection;


import java.util.ArrayList;
import java.util.HashMap;

import mat.beacon.classes.Packet;



public class RoomDetection {

		private HashMap<String, ArrayList<RssiToMeters>> PositionMapping = new HashMap<String, ArrayList<RssiToMeters>>();
		private HashMap<String,ConversionRSSI> ConversionRSSIMap = new HashMap<String,ConversionRSSI>();
		String position;
		
		
		
		public RoomDetection(int stopConversion){
			
			
			
			ArrayList<RssiToMeters> list= new ArrayList<>();
			list.add((new RssiToMeters(-64,0,"Distanza radiale compresa tra 0 e 50cm")));
			list.add((new RssiToMeters(-76,-65,"Distanza radiale compresa tra 50 e 200cm")));
			list.add((new RssiToMeters(-10000,-77,"Distanza radiale oltre i 200cm")));
			PositionMapping.put("00:07:80:37:8A:66",list);
			
			ArrayList<RssiToMeters> list2= new ArrayList<>();
			list2.add((new RssiToMeters(-60,0,"Distanza radiale compresa tra 0 e 50cm")));
			list2.add((new RssiToMeters(-77,-61,"Distanza radiale compresa tra 50 e 300cm")));
			list2.add((new RssiToMeters(-10000,-78,"Distanza radiale oltre i 300cm")));
			PositionMapping.put("00:07:80:37:8A:6A", list2);
			
			this.ConversionRSSIMap.put("00:07:80:37:8A:6A", new ConversionRSSI(stopConversion));
			this.ConversionRSSIMap.put("00:07:80:37:8A:66", new ConversionRSSI(stopConversion));
			
		}
	
		public void detect(Packet pack) {
			// TODO Auto-generated method stub
			ConversionRSSI conversion=this.ConversionRSSIMap.get(pack.getBeacon().getMAC());
			String position2compare;
			
			int rssiCalculated;
			
			if (!conversion.isCalibrato()){
				rssiCalculated=conversion.calibra(pack);
				if (rssiCalculated!=100){
					position = convert(rssiCalculated,pack);
					printPosition(pack,position);
				}								
			}
			else{
			position2compare = conversion.convert(pack,PositionMapping.get(pack.getBeacon().getMAC()));
			if(position2compare!=null){
			if (!position.equals(position2compare))	{
				System.out.println("MOVIMENTO RILEVATO! Calcolo nuova posizione del" + pack.getBeacon().getFeature());
				position = position2compare;
				printPosition(pack,position);
			}
			}
			}
			
			
		}

		private String convert(int rssi,Packet pack) {
			// TODO Auto-generated method stub
			
			ArrayList<RssiToMeters> rssiToMeters=this.PositionMapping.get(pack.getBeacon().getMAC());
			String position = null;
			for (RssiToMeters rssi2m : rssiToMeters){
				position = rssi2m.getPosition(rssi);
				if (position!=null){
					return position;
				}			
			}
			
		return position;	
		}

		private void printPosition(Packet pack,String position) {
			// TODO Auto-generated method stub
			
			System.out.println("Il " + pack.getBeacon().getFeature() + " si trova a una " + position );
		}
		

}

