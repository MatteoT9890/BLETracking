package mat.beacon.data;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;

import mat.beacon.classes.Beacon;


public class Data_Beacon {
	
	private static final String file="/home/pi/BeaconProject/Data_Beacon.txt";

	/**
	 * Popolo la base dati relativi agli iBeacon.
	 * I dati sono prelevati dal file "Data_iBeacon.txt"
	 * @author Matteo Tarantino
	 * @param iBeaconMap
	 * 
	 * @return void
	 * 
	 */
	
	public static void popola(HashMap<String,Beacon> iBeaconMap) {
		
		try {
			
			BufferedReader b = new BufferedReader(new FileReader(file));
			String rigafile;
			String[] data_beacon;
			while((rigafile=b.readLine())!=null){
				
				data_beacon=rigafile.split(",");
			    //validità del MAC
			    Beacon B = createiBeacon(data_beacon[0], data_beacon[1], data_beacon[2]);
			    iBeaconMap.put(B.getMAC(), B);		
			    
		}
			b.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
    public static Beacon createiBeacon(String MAC, String Costruttore, String Feature) {
    	Beacon B = new Beacon(MAC,Costruttore,Feature);
    return B;
    }
}

