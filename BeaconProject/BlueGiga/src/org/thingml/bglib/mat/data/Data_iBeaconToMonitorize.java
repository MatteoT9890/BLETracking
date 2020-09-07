package org.thingml.bglib.mat.data;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


import org.thingml.bglib.mat.iBeaconToMonitorize;

public class Data_iBeaconToMonitorize {
	
private static final String file="Data_iBeaconToMonitorize.txt";

	/**
	 * Popolo la base dati relativa agli iBeacon da monitorare. 
	 * I dati sono prelevati dal file "Data_iBeaconToMonitorize.txt"
	 * 
	 * @author Matteo Tarantino
	 * @param iBeaconToMonitorizeMap
	 * @return void
	 */
	public static void popola(HashMap<String,iBeaconToMonitorize> iBeaconToMonitorizeMap) {
		
		try {
			
			BufferedReader b = new BufferedReader(new FileReader(file));
			String rigafile;
			String[] data_beacon;
			while((rigafile=b.readLine())!=null){
				
				data_beacon=rigafile.split(",");
			    //validità del MAC
			    iBeaconToMonitorize iB = createiBeaconToMonitorize(data_beacon[0], 
			    		Integer.parseInt(data_beacon[1]),data_beacon[2]);
			    
			    iBeaconToMonitorizeMap.put(iB.getKey(), iB);
			    
			    ;
		}
			b.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creo l'iBeacon da monitorare.
	 * 
	 * @author Matteo Tarantino
	 * @param major
	 * @param minor
	 * @param limit
	 * @param MAC
	 * @return
	 */
    public static iBeaconToMonitorize createiBeaconToMonitorize(String key, int limit,String MAC) {
    	
    	iBeaconToMonitorize iB = new iBeaconToMonitorize(key,limit,MAC);
    return iB;
    }

}
