package main;
import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


import mat.beacon.classes.*;
import mat.beacon.data.*;
import mat.beacon.detection.RoomDetection;
import mat.beacon.filter.*;



public class MotionDetection {
	
	private static DataInterfaceBLE interfaceData = new DataInterfaceBLE();
	private static InterfaceFilter interfaceFilter = new InterfaceFilter();
	private static RoomDetection interfaceMotionDetection = new RoomDetection(10);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Process writeScan=null;
		Process leScan=null;
		
		execCommand(leScan,"sudo hcitool lescan --duplicates"); // NECESSARIO PER FAR FUNZIONARE IL TOOL HCIDUMP
			
	    BufferedReader reader = execCommand(writeScan,"sudo hcidump -a -t");
	    AnalyseResponseAndCheckMotion(reader);
		     
	}
	
	/**
	 * Tale metodo analizza la risposta del tool hcidump, filtra i pacchetti ricevuti e li testa.
	 * 
	 * @author Matteo Tarantino
	 * 
	 * @param reader
	 */
	private static void AnalyseResponseAndCheckMotion(BufferedReader reader){
		
	     String line;
	     ArrayList<String> hcidump_response = new ArrayList<>();
	     ResponseFilter.Status responseStatus;
	     
	     System.out.println("Calibrazione beacon......");
	      try {
			while ((line = reader.readLine()) != null) {
				  
				  hcidump_response.add(line);
				  responseStatus = AnalyseResponseFilter(line);
				  
			    if (responseStatus== ResponseFilter.Status.CONFIRMED){
			    	Packet pack =CreatePacket(interfaceFilter.getMyPacketFilter().getResults(hcidump_response));
			    	interfaceMotionDetection.detect(pack);
			    	hcidump_response.clear();
			    	
			    	continue;
			    }
			    if (responseStatus== ResponseFilter.Status.REJECT) {
			    	hcidump_response.clear();
			    	continue;
			    }


}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Tale metodo esegue da terminale il comando e lo assegna al processo.
	 * 
	 * @author Matteo Tarantino
	 * @param process
	 * @param command
	 * @return BufferedReader: attraverso uno stream redirigo l'output del comando in un BufferedReader cosicchè posso elaborarlo. 
	 */
	private static BufferedReader execCommand(Process process,String command){
		 try {
			process=Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 InputStreamReader in= new InputStreamReader(process.getInputStream());
	     BufferedReader reader = new BufferedReader(in);
	     
	     return reader;
	}
	
	/**
	 * Ricevuto un qualsiasi pacchetto che rileva il tool hcidump, analizzo la risposta del filtro.
	 * 
	 * @author Matteo Tarantino
	 * @param line
	 * @return <br/>CONFIRMED se il pacchetto riguarda uno dei miei Beacon.<br/> REJECT se il pacchetto non riguarda uno dei miei beacon.
	 */
	private static ResponseFilter.Status AnalyseResponseFilter(String line){
		
	ResponseFilter.Status stat = interfaceFilter.getResponseFilter().isBeacon(line);
  	return stat;
  	  
		
	}
	
	/**
	 * Avendo ricevuto CONFIRMED dal ResponseFilter, genero il relativo pacchetto dalle informazioni ottenute dall'analyser.
	 * 
	 * @author Matteo Tarantino
	 * @param infoPacket
	 * @return void
	 */
	
	private static Packet CreatePacket(ArrayList<String> infoPacket){
		return interfaceData.generaPacchetto(infoPacket);
	}
}