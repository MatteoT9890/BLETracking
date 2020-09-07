package org.thingml.bglib.mat.data;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;


import org.thingml.bglib.mat.iBeacon;

public class Data_iBeacon {
	
	private static final String file="Data_iBeacon.txt";

	/**
	 * Popolo la base dati relativi agli iBeacon.
	 * I dati sono prelevati dal file "Data_iBeacon.txt"
	 * @author Matteo Tarantino
	 * @param iBeaconMap
	 * 
	 * @return void
	 * 
	 */
	
	public static void popola(HashMap<String,iBeacon> iBeaconMap) {
		
		try {
			
			BufferedReader b = new BufferedReader(new FileReader(file));
			String rigafile;
			String[] data_beacon;
			while((rigafile=b.readLine())!=null){
				
				data_beacon=rigafile.split(",");
			    //validità del MAC
			    iBeacon iB = createiBeacon(data_beacon[0], data_beacon[1], data_beacon[2], Integer.parseInt(data_beacon[3]), 
			    		Integer.parseInt(data_beacon[4]), Integer.parseInt(data_beacon[5]));
			    iBeaconMap.put(iB.getUUID(), iB);
		
			    
		}
			b.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
    public static iBeacon createiBeacon(String MAC, String UUID, String type, int major, int minor, int power) {
    	iBeacon iB = new iBeacon(MAC,UUID,
    			type,major,minor,power);
    return iB;
    }
}
