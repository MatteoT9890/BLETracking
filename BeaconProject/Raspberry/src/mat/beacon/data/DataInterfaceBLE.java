package mat.beacon.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import mat.beacon.classes.*;

public class DataInterfaceBLE {
	
	private HashMap<String,Beacon> BeaconData;
	private LinkedHashMap<String,Packet> PacketData;
	
	/**
	 * Crea l'interfaccia dati relativa ai Beacon e ai Pacchetti. <br/>
	 * In particolare i dati relativi ai Beacon sono presi dal file /home/pi/BeaconProject/Data_Beacon.txt
	 * 
	 *  @author Matteo Tarantino
	 */
	public DataInterfaceBLE() {
		this.BeaconData= new HashMap<>();
		this.PacketData=new LinkedHashMap<>();
		Data_Beacon.popola(BeaconData);
	}
	
	public void print() {
		System.out.println();
	}
	
	public HashMap<String,Beacon> getBeaconData() {
		return BeaconData;
	}

	public HashMap<String, Packet> getPacketData() {
		return PacketData;
	}

	public Packet generaPacchetto(ArrayList<String> infoPacket) {
		// TODO Auto-generated method stub
		Beacon B = this.BeaconData.get(infoPacket.get(1));
		Packet pack=null;
		
		if(B != null ){
			pack = new Packet(infoPacket.get(0),B,Integer.parseInt(infoPacket.get(2)));
			this.PacketData.put(pack.getTimestamp(),pack);
		}
		
		return pack;
	}


	
	
	
	

}

