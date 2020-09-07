/**
 * Copyright (C) 2012 SINTEF <franck.fleurey@sintef.no>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.bglib.gio;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.thingml.bglib.BGAPIPacket;
import org.thingml.bglib.BGAPITransportListener;


/**
 * Versione modificata ad hoc della classe "BGAPIPacketLogger", di Franck FLEUREY (SINTEF)
 * @author Giorgio Avalle
 */

public class BGAPIPacketLogger_Gio implements BGAPITransportListener {

	//elenco di dispositivi rilevati: le chiavi sono indirizzi MAC esadecimali
	private static Map<String, BleDisp> dispositivi = new TreeMap<>();
	
	private long start;
    
	
    
    public BGAPIPacketLogger_Gio() {
        start = System.currentTimeMillis();
    }

    private String time() {
        int millis_since_start = (int)(System.currentTimeMillis() - start);
        String ret = String.format("%d.%03d ", millis_since_start / 1000, millis_since_start % 1000);
        
        return ret;
    }
    
    public void packetSent(BGAPIPacket packet) {
         Logger.getLogger(BGAPIPacketLogger_Gio.class.getName()).log(Level.INFO, time() + "SND " + packet.toString());
    }

    public void packetReceived(BGAPIPacket packet) {
    	//Qui, si vuole utilizzare e modificare quanto ricevuto in input dal sensore
        Logger.getLogger(BGAPIPacketLogger_Gio.class.getName()).log(Level.INFO, time() + "RCV " + packet.toString());
    }
    
    /** 
     * Aggiungi un nuovo dispositivo, se non ancora rilevato
     * @param mac è un indirizzo MAC esadecimale
     */
    public static void addDisp(String mac) {
    	//il dispositivo non deve già essere stato rilevato
    	if(dispositivi.containsKey(mac))
    		return;
    	
    	//crea associazione
    	dispositivi.put(mac, new BleDisp(mac));
    }
    
    /**
     * Ottieni un dispositivo esistente (altrimenti 'null') a partire dal suo indirizzo MAC
     * @param mac è un indirizzo MAC esadecimale
     * @return DispositivoBLE
     */
    public static BleDisp getDisp(String mac) {
    	if(dispositivi.containsKey(mac))
    		return dispositivi.get(mac);
    	
    	//se non esiste l'associazione mac-dispositivo, ritorno null
    	return null;
    }
}
