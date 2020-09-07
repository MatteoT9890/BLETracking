




import java.util.logging.Level;



import java.util.logging.Logger;

import org.thingml.bglib.BDAddr;
import org.thingml.bglib.BGAPI;
import org.thingml.bglib.BGAPIDefaultListener;
import org.thingml.bglib.BGAPIPacketLogger;
import org.thingml.bglib.BGAPITransport;
import org.thingml.bglib.gio.BleDataADV;
import org.thingml.bglib.gio.BleMemory;
import org.thingml.bglib.mat.*;
import org.thingml.bglib.mat.data.InterfaceToDataBLE;
import org.thingml.bglib.gio.BleDisp;

/**
 * Applicativo da lanciare, per permettere al gateway di funzionare. I pacchetti ricevuti vengono
 * memorizzati in un apposita struttura dati, sulla quale è possibile operare al fine di rilevare
 * informazioni particolari o statistiche di carattere generale.
 */

public class Scanner extends BGAPIDefaultListener 
{
	private static BleMemory mem = new BleMemory(true);
	private static InterfaceToDataBLE DataBLE = new InterfaceToDataBLE();
			
	
	public static void main( String[] args ) throws Exception
    {	
		
		
	    //instauro la connessione
	    System.out.println( "Connecting BLED112 Dongle..." );
        
        //creo il livello trasporto
        BGAPITransport bgapi = BLED112.connectBLED112();
        //ci aggiungo il packet logger
        bgapi.addListener(new BGAPIPacketLogger());
        //creo l'implementazione, da lv trasporto
        BGAPI impl = new BGAPI(bgapi);
        //aggiungo questa classe come listener
        impl.addListener(new Scanner());
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Requesting Version Number...");
        //operazioni iniziali
        impl.send_system_get_info();
        impl.send_system_hello();
        impl.send_gap_set_scan_parameters(10, 250, 1);
        impl.send_gap_discover(1);
        
    }
	
	public void receive_system_get_info(Integer major, Integer minor, Integer patch, Integer build, Integer ll_version, Integer protocol_version, Integer hw) {
        System.out.println("get_info_rsp :" + major + "." + minor + "." + patch + " (" + build + ") " + "ll=" + ll_version + " hw=" + hw);
    }

//    public void receive_gap_scan_response(Integer rssi, Integer packet_type, BDAddr sender, Integer address_type, Integer bond, byte[] data) {
//        System.out.println("FOUND: " + sender.toString() + "["+ new String(data) + "] (rssi = " + rssi + ", packet type= " + packet_type + ")");
//    }
    
    
    @Override
    public void receive_system_hello() {
        System.out.println("GOT HELLO!");
    }
    
    
    
    @Override
    public void receive_gap_scan_response(int rssi, int packet_type, BDAddr sender, int address_type, int bond, byte[] data
    		){
    	//DataBLE.print();
    	BleDataADV p = new BleDataADV(rssi, packet_type, sender, address_type, bond, data, mem);
    	
    	
    	//se la whitelist Ã¨ abilitata, elaboro solo i pacchetti abilitati
    	if(mem.isWhiteListEnabled() && !(mem.hasWL(p.getHexMAC()))){
    		System.out.println("->  Ho ricevuto un pacchetto da dispositivo non-Whitelist. MAC = '" + p.getHexMAC() + "'");
    		return;
    	}
    	
    	//pacchetti abilitati! Posso elaborarli...
    	
    	//Se il dispositivo non è inizializzato, freeze del pacchetto ed eventuale chiamata a init()
    	
        BleDisp disp = mem.getDisp(p.getHexMAC());
        Packet_iBeacon p_iBeacon = null;
		if(!disp.isInit()) {
        	disp.freezePacket(p);
        	if(disp.isToInit())
        		disp.init();
        }
        else {
        	//stampo le info del pacchetto
        	
        	p.elabora();
        	
			
        	//p_iBeacon = p.generaPacchetto(DataBLE);
        	//if (p_iBeacon!=null) {
     		//p_iBeacon.AnomalyDetection(DataBLE.getiBeaconToMonitorizeMap());
        	//}
        	
        }
        //Salvo il pacchetto in memoria
        mem.savePacket(p);
        
        System.out.println("---------------------");
    }

}
