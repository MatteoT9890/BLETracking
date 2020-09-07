package org.thingml.bglib.mat.detection;

import java.sql.Timestamp;

import java.util.HashMap;

public class RoomDetection {

		
		private HashMap<String, String> PositionMapping = new HashMap<String, String>();
		
		public void popola() {
			PositionMapping.put("-72,-30","Raggio <= 2 metri dall'antenna");
			PositionMapping.put("-80,-73", "Porta che separa stanza dal corridoio");
			PositionMapping.put("-84,-81", "Postazione Simone ( vicino la finestra ) OPPURE porta che separa la stanza dal main");
			PositionMapping.put("-1000,-85", "Fuori dalla stanza");
		}
		
		public void print (String uuid,int rssi,String position) {
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println("\nDETECTION INFO:\n" + 
					"\tUUID:" + uuid + 
					"\tTimestamp:" + timestamp + "\n" +
					"\tPosition:" + position + "\t\tRSSI associato:" + rssi);
			
		}
		
		
		/**
		 * Tale metodo ritorna la chiave di PositionMapping relativa alla posizione del sensore.
		 * Attraverso la chiave è possibile risalire alla posizione tramite la mappa PositionMapping.
		 * In caso di null significa che il sensore non è all'interno della stanza.
		 * @param RSSI
		 * @return String
		 */
		public void CheckPosition(String uuid,int RSSI) {
			String position="Sensore fuori dalla stanza";
			if (this.getKeyPosition(RSSI)==null)
				print(uuid,RSSI,position);
			else 
				print(uuid,RSSI,this.PositionMapping.get(this.getKeyPosition(RSSI)));
		}
		
		public String getKeyPosition(int RSSI) {
			
			return this.PositionMapping.keySet().stream().filter((String key) -> {
				String[] couple = key.split(",");
				return RSSI >= Integer.parseInt(couple[0]) && RSSI <= Integer.parseInt(couple[1]);
			}).findFirst().get();
			
		}
}
