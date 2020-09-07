///**
// * Copyright (C) 2012 SINTEF <franck.fleurey@sintef.no>
// *
// * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * 	http://www.gnu.org/licenses/lgpl-3.0.txt
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package org.thingml.bglib.gio;
//
//import java.util.Map;
//
//import java.util.TreeMap;
//
//import org.thingml.bglib.BDAddr;
//import org.thingml.bglib.BGAPIDefaultListener;
//
//import Bluegiga_Gio.Bluegiga_Gio.AppGATT;
//import Bluegiga_Gio.Bluegiga_Gio.ByteUtils;
//
///**
// * Questo listener estende quello di default, fornendo un messaggio specifico (di errore o notifica) per ogni callback del protocollo BGAPI
// * @author Giorgio Avalle
// */
//public abstract class BGAPIDefaultListener_Gio extends BGAPIDefaultListener {
//	
//	
//	//struttura dati per la connessione
//	protected static Map<String, Integer> connectionHandles = new TreeMap<>();
//	protected static String connectionMAC;
//	
//	/**
//	 * Aggiungi una corrispondenza MAC - handle connessione
//	 * @param mac è l'indirizzo MAC del dispositivo BLE remoto, formattato
//	 * @param h è l'handle della connessione
//	 */
//	protected static void addConnection(String mac, int h){
//		if(!connectionHandles.containsKey(mac))
//			connectionHandles.put(mac, h);
//	}
//	
//	/**
//	 * Ricava l'handle di connessione, a partire da un indirizzo MAC formattato. Ritorna -1 in caso di errore
//	 * @param mac è l'indirizzo MAC del dispositivo BLE remoto
//	 * @return int
//	 */
//	protected static int getConnection(String mac){
//		if(!connectionHandles.containsKey(mac))
//			return -1;
//		return connectionHandles.get(mac);
//	}
//	
//	
//	//attribute handles
//	protected static Map<String, Integer> attributeHandles = new TreeMap<>();
//	
//	/**
//	 * Aggiungi una corrispondenza UUID - handle attributo
//	 * @param uuid è l'UUID della GATT dispositivo
//	 * @param handle è l'handle relativo all'UUID specificato
//	 */
//	protected static void addGATTHandle(String uuid, int handle){ 
//		if(!attributeHandles.containsKey(uuid)) 
//			attributeHandles.put(uuid, handle); 
//	}
//	
//	/**
//	 * Ricava l'handle di un attributo a partire dal codice UUID dello stesso
//	 * @param uuid è l'UUID dell'attributo GATT
//	 * @return int
//	 */
//	protected static int getGATTHandle(String uuid){
//		String str;
//		StringBuffer tmp = new StringBuffer("");
//		int i = uuid.length() - 1;
//
//		//uppercase hex
//		uuid = uuid.toUpperCase();
//		
//		//little endian
//		while(i >= 0){
//			tmp.append(uuid.charAt(i-1)).append(uuid.charAt(i));
//			i = i-2;
//		}
//		
//		str = tmp.toString();
//		if(attributeHandles.containsKey(str)) 
//			return attributeHandles.get(str);
//		
//		return -1; 
//	}
//	
//	
//	//blocco operazioni
//	protected static boolean lock = false;
//	
//	synchronized protected static boolean isLocked(){ return lock; }
//	synchronized protected static void lock(){ lock = true; }
//	synchronized protected static void unlock(){ lock = false; }
//	
//	
//	//error codes
//	protected void _perr(int result, String msgOk, String msgErr){
//		if(result == 0)
//    		System.out.println(msgOk);
//    	else
//    		System.err.println("ERR - " + msgErr + ": " + BleCodes.error(result));
//	}
//	
//	//specifico per la connessione alla GATT: non voglio errore con il codice 
//	private void _perrConn(int reason, String msgOk, String msgErr) {
//		if(reason == 0x0216)
//			reason = 0;
//		_perr(reason, msgOk, msgErr);
//	}
//	
//	
//	//stampa a schermo
//	private void _line(String msg){
//		System.out.println(msg);
//	}
//	private void _ric(String msg){
//		_line("Ricevuto: " + msg);
//	}
//	private void _lineDash() {
//		_line("--------------");
//	}
//	
//	
//	
//	
//	
//	
//	
//    
//	// Callbacks for class system (index = 0)
//	public void receive_system_reset() { _line("RESET"); }
//	public void receive_system_hello() { _line("GOT HELLO!"); }
//	public void receive_system_address_get(BDAddr address) { _line(new String("LOCAL ADDRESS: " + address.toString())); }
//	public void receive_system_reg_write(int result) { _ric("reg_write"); }
//	public void receive_system_reg_read(int address, int value) { _ric("reg_read"); }
//	public void receive_system_get_counters(int txok, int txretry, int rxok, int rxfail) { _line("Pacchetti trasmessi: OK = " + txok + ", Riprova = " + txretry); _line("Pacchetti ricevuti: OK = " + rxok + ", Falliti = " + rxfail); }
//	public void receive_system_get_connections(int maxconn) { _line("MAX CONN = " + maxconn); }
//	public void receive_system_read_memory(int address, byte[] data) { _line("Read_memory: Address = " + address + ", Data = " + ByteUtils.bytesToString(data)); }
//	public void receive_system_get_info(int major, int minor, int patch, int build, int ll_version, int protocol_version, int hw) { _line("GET_INFO_RSP: " + major + "." + minor + "." + patch + " (" + build + ") " + "ll=" + ll_version + " hw=" + hw); }
//	public void receive_system_endpoint_tx() { _ric("endpoint_tx"); }
//	public void receive_system_whitelist_append(int result) { _perr(result, "Elemento aggiunto correttamente alla whitelist", "Aggiunta elemento whitelist non riuscita"); }
//	public void receive_system_whitelist_remove(int result) { _perr(result, "Elemento rimosso correttamente dalla whitelist", "Rimozione elemento whitelist non riuscita"); }
//	public void receive_system_whitelist_clear() { _line("La whitelist ora è vuota"); }
//	public void receive_system_boot(int major, int minor, int patch, int build, int ll_version, int protocol_version, int hw) { _line("BOOT: " + major + "." + minor + "." + patch + " (" + build + ") " + "ll=" + ll_version + " hw=" + hw); }
//	public void receive_system_debug(byte[] data) { _line("Dati di debug: " + ByteUtils.bytesToString(data)); }
//	public void receive_system_endpoint_rx(int endpoint, byte[] data) { _perr(endpoint, "Ricevuto da endpoint: " + ByteUtils.bytesToString(data), "Ricezione di dati da endpoint"); }
//
//	// Callbacks for class flash (index = 1)
//	public void receive_flash_ps_defrag() { _line("Persistent Store deframmentato"); }
//	public void receive_flash_ps_dump() { _line("Dumping chiavi del Persistent Store"); }
//	public void receive_flash_ps_erase_all() { _line("Chiavi del Persistent Store cancellate"); }
//	public void receive_flash_ps_save(int result) { _perr(result, "Persistent Store key salvata su dispositivo", "Salvataggio della Persistent Key su dispositivo"); }
//	public void receive_flash_ps_load(int result, byte[] value) { _perr(result, "Persistent Store key letta: " + ByteUtils.bytesToString(value), "Lettura della Persistent Store key"); }
//	public void receive_flash_ps_erase() { _line("Persistent Store key cancellata"); }
//	public void receive_flash_erase_page(int result) { _perr(result, "Flash page cancellata correttamente", "Cancellazione flash page"); }
//	public void receive_flash_write_words() { _ric("write_words"); }
//	public void receive_flash_ps_key(int key, byte[] value) { _line("Persistent Store key dumped: Key = " + key + ", Valore = " + ByteUtils.bytesToString(value)); }
//
//	// Callbacks for class attributes (index = 2)
//	public void receive_attributes_write(int result) { _perr(result, "La scrittura su GATT client è andata a buon fine", "La scrittura su GATT del client non è andata a buon fine"); }
//	public void receive_attributes_read(int handle, int offset, int result, byte[] value) { _perr(result, "Attributo letto da GATT client: Handle = " + handle + ", Offset = " + offset + ", Valore = " + ByteUtils.bytesToString(value), "Lettura attributo (" + handle + ") da GATT client"); }
//	public void receive_attributes_read_type(int handle, int result, byte[] value) { _perr(result, new String("Attributo letto da GATT client: Handle = " + handle + ", Valore = " + ByteUtils.bytesToString(value)), "Lettura attributo (" + handle + ") da GATT client"); }
//	public void receive_attributes_user_response() { _ric("attributes_user_response"); }
//	public void receive_attributes_value(int connection, int reason, int handle, int offset, byte[] value) { _line("Modifica alla GATT da dispositivo remoto (conn " + connection + "): Attributo = " + handle + ", Offset = " + offset + ", Valore = " + ByteUtils.bytesToString(value) + ", Motivazione = " + BleCodes.AttributeChangeReason(reason)); }
//	public void receive_attributes_user_request(int connection, int handle, int offset) { _ric("attributes_user_request"); }
//
//	// Callbacks for class connection (index = 3)
//	public void receive_connection_disconnect(int connection, int result) { _perr(result, "Procedura di disconnessione inizializzata correttamente", "Tentativo di disconnessione del dispositivo non riuscito"); }
//	public void receive_connection_get_rssi(int connection, int rssi) { _line("RSSI della connessione " + connection + ": " + rssi); }
//	public void receive_connection_update(int connection, int result) { _perr(result, "Update della connessione " + connection + "correttamente eseguito", "Tentativo di update della connessione " + connection); }
//	public void receive_connection_version_update(int connection, int result) { _perr(result, "Version Update per la connessione " + connection + " correttamente eseguito", "Version Update per la connessione " + connection); }
//	public void receive_connection_channel_map_get(int connection, byte[] map) { _ric("connection_channel_map_get"); }
//	public void receive_connection_channel_map_set(int connection, int result) { _ric("connection_channel_map_set"); }
//	public void receive_connection_features_get(int connection, int result) { _perr(result, "Richiesta feature dalla connessione " + connection + " avvenuta con successo", "Richiesta feature dalla connessione " + connection); }
//	public void receive_connection_get_status(int connection) { _line("Richiesto lo stato per la connessione " + connection); }
//	public void receive_connection_raw_tx(int connection) { _line("Trasmissione di dati verso la connessione " + connection); }
//	public void receive_connection_status(int connection, int flags, BDAddr address, int address_type, int conn_interval, int timeout, int latency, int bonding) { 
//		//quando ricevo questo pacchetto (evento), la connessione è stabilita
//    	_line("STATUS connessione: ID = " + connection + ", Flags = " + flags + ", MAC = " + address.toString() + ", Address type = " + address_type + ", connection interval = " + conn_interval + ", timeout = " + timeout + ", latency = " + latency + ", bonding = " + bonding);
//    	_line("(" + connection + "): Connessione stabilita");
//
//    	addConnection(connectionMAC, connection);
//    	unlock();
//	}
//	public void receive_connection_version_ind(int connection, int vers_nr, int comp_id, int sub_vers_nr) {_line("Versione della connessione " + connection + ": " + vers_nr + "; costruttore = \"" + comp_id + "\", controller version = " + sub_vers_nr); }
//	public void receive_connection_feature_ind(int connection, byte[] features) { _line("Features del dispositivo remoto (conn " + connection + "): " + ByteUtils.bytesToString(features)); }
//	public void receive_connection_raw_rx(int connection, byte[] data) { _line("Ricezione di dati dalla connessione " + connection + ": " + ByteUtils.bytesToString(data)); }
//	public void receive_connection_disconnected(int connection, int reason) { _perrConn(reason, "Dispositivo disconnesso dall'utente (connessione = " + connection + ")", "Il dispositivo (connection = " + connection + ") si è disconnesso"); }
//
//	// Callbacks for class attclient (index = 4)
//	public void receive_attclient_find_by_type_value(int connection, int result) { _ric("attclient_find_by_type_value"); }
//	public void receive_attclient_read_by_group_type(int connection, int result) { _ric("attclient_read_by_group_type"); }
//	public void receive_attclient_read_by_type(int connection, int result) { _ric("attclient_read_by_type"); }
//	public void receive_attclient_find_information(int connection, int result) { _perr(result, new String("La richiesta di informazioni per la connessione " + connection + " è stata inoltrata"), new String("La ricerca di informazioni per la connessione " + connection + " non è andata a buon fine"));}
//	public void receive_attclient_read_by_handle(int connection, int result) { _perr(result, "Procedura di lettura attributo GATT inoltrata", "La lettura dell'attributo GATT (da handle) non è riuscita"); }
//	public void receive_attclient_attribute_write(int connection, int result) { _perr(result, "Richiesta di scrittura attributo su GATT dispositivo remoto inoltrata, per la connessione " + connection, "Richiesta di scrittura attributo su GATT dispositivo remoto, connessione " + connection); }
//	public void receive_attclient_write_command(int connection, int result) { _ric("attclient_write_command"); }
//	public void receive_attclient_reserved() { _ric("attclient_reserved"); }
//	public void receive_attclient_read_long(int connection, int result) { _ric("attclient_read_long"); }
//	public void receive_attclient_prepare_write(int connection, int result) { _ric("attclient_prepare-write"); }
//	public void receive_attclient_execute_write(int connection, int result) { _ric("attclient_execute_write"); }
//	public void receive_attclient_read_multiple(int connection, int result) { _ric("attclient_read_multiple"); }
//	public void receive_attclient_indicated(int connection, int attrhandle) { _ric("attclient_indicated"); }
//	public void receive_attclient_procedure_completed(int connection, int result, int chrhandle) { _perr(result, "Procedura completata per l'handle " + chrhandle + ", sulla connessione " + connection, "Operazione su GATT server"); unlock();}
//	public void receive_attclient_group_found(int connection, int start, int end, byte[] uuid) { _ric("attclient_group_found"); }
//	public void receive_attclient_attribute_found(int connection, int chrdecl, int value, int properties, byte[] uuid) { _ric("attclient_attribute_found"); }
//	public void receive_attclient_find_information_found(int connection, int chrhandle, byte[] uuid) {
//		String tmp = BleUtil.toHex(uuid);
//		_lineDash();
//		_line("INFORMATION FOUND");
//		_line("Connessione: " + connection);
//		_line("Handle: " + chrhandle);
//		_line("UUID (l-e): " + tmp);
//		_lineDash();
//		addGATTHandle(tmp, chrhandle);
//	}
//	public void receive_attclient_attribute_value(int connection, int atthandle, int type, byte[] value) {
//		_lineDash();
//		_line("ATTRIBUTE VALUE");
//		_line("Connessione: " + connection);
//		_line("Handle attributo: " + BleUtil.toHex((byte) (atthandle/256), (byte) (atthandle % 256)));
//		_line("Provenienza: " + BleCodes.AttributeValueType(type));
//		_line("Valore: '" + BleUtil.getString(value) + "'  =  [ " + ByteUtils.bytesToString(value) + "]");
//		_lineDash();
//	}
//	public void receive_attclient_read_multiple_response(int connection, byte[] handles) { _ric("attclient_read_multiple_response"); }
//
//	// Callbacks for class sm (index = 5)
//	public void receive_sm_encrypt_start(int handle, int result) { _ric("sm_encrypt_start"); }
//	public void receive_sm_set_bondable_mode() { _ric("sm_set_bondable_mode"); }
//	public void receive_sm_delete_bonding(int result) { _ric("sm_delete_bonding"); }
//	public void receive_sm_set_parameters() { _ric("sm_set_parameters"); }
//	public void receive_sm_passkey_entry(int result) { _ric("sm_passkey_entry"); }
//	public void receive_sm_get_bonds(int bonds) { _ric("sm_get_bonds"); }
//	public void receive_sm_set_oob_data() { _ric("sm_set_oob_data"); }
//	public void receive_sm_smp_data(int handle, int packet, byte[] data) { _ric("sm_smp_data"); }
//	public void receive_sm_bonding_fail(int handle, int result) { _ric("sm_bonding_fail"); }
//	public void receive_sm_passkey_display(int handle, int passkey) { _ric("sm_passkey_display"); }
//	public void receive_sm_passkey_request(int handle) { _ric("sm_passkey_request"); }
//	public void receive_sm_bond_status(int bond, int keysize, int mitm, int keys) { _ric("sm_bond_status"); }
//
//	// Callbacks for class gap (index = 6)
//	public void receive_gap_set_privacy_flags() { _ric("gap_set_privacy_flags"); }
//	public void receive_gap_set_mode(int result) { _perr(result, "Settaggio GAP corretto", "Settaggio GAP"); }
//	public void receive_gap_discover(int result) { _perr(result, "Settaggio DISCOVER corretto", "Settaggio DISCOVER"); }
//	public void receive_gap_connect_direct(int result, int connection_handle) { _perr(result, new String("Procedura di connessione diretta inizializzata per " + AppGATT.connectionMAC + ", ID = " + connection_handle), new String("Connessione diretta non riuscita"));}
//	public void receive_gap_end_procedure(int result) { _ric("gap_end_procedure"); }
//	public void receive_gap_connect_selective(int result, int connection_handle) { _perr(result, "Connessione ad elemento whitelist riuscita", "Connessione ad elemento whitelist non riuscita"); }
//	public void receive_gap_set_filtering(int result) { _perr(result, "Impostazioni filtro whitelist settate correttamente", "Settaggio impostazioni filtro whitelist");}
//	public void receive_gap_set_scan_parameters(int result) { _perr(result, "Settaggio parametri SCAN corretto", "Settaggio parametri SCAN non riuscito"); }
//	public void receive_gap_set_adv_parameters(int result) { _ric("gap_adv_parameters"); }
//	public void receive_gap_set_adv_data(int result) { _ric("gap_adv_data"); }
//	public void receive_gap_set_directed_connectable_mode(int result) { _perr(result, "Impostazioni connessione diretta del dispositivo configurate", "Settaggio impostazioni di connessione diretta con il dispositivo remoto"); }
//	public void receive_gap_scan_response(int rssi, int packet_type, BDAddr sender, int address_type, int bond, byte[] data) {
//		_lineDash();
//		_line("SCAN RESPONSE");
//		_line("RSSI: " + rssi + " dBm");
//		_line("Tipo di pacchetto: " + packet_type);
//		_line("Inviato da: " + sender.toString() + " (" + BleCodes.BluetoothAddressTypes(address_type) + ")");
//		_line("Bond: " + bond);
//		_line("Contenuto: " + ByteUtils.bytesToString(data));
//		_lineDash();
//	}
//	public void receive_gap_mode_changed(int discover, int connect) { _ric("gap_mode_changed"); }
//
//	// Callbacks for class hardware (index = 7)
//	public void receive_hardware_io_port_config_irq(int result) { _ric("hardware_io_port_config_irq"); }
//	public void receive_hardware_set_soft_timer(int result) { _ric("hardware_set_soft_timer"); }
//	public void receive_hardware_adc_read(int result) { _ric("hardware_adc_read"); }
//	public void receive_hardware_io_port_config_direction(int result) { _ric("hardware_io_port_config_direction"); }
//	public void receive_hardware_io_port_config_function(int result) { _ric("hardware_io_port_config_function"); }
//	public void receive_hardware_io_port_config_pull(int result) { _ric("hardware_io_port_config_pull"); }
//	public void receive_hardware_io_port_write(int result) { _ric("hardware_io_port_write"); }
//	public void receive_hardware_io_port_read(int result, int port, int data) { _ric("hardware_io_port_read"); }
//	public void receive_hardware_spi_config(int result) { _ric("hardware_spi_config"); }
//	public void receive_hardware_spi_transfer(int result, int channel, byte[] data) { _ric("hardware_spi_transfer"); }
//	public void receive_hardware_i2c_read(int result, byte[] data) { _ric("hardware_i2c_read"); }
//	public void receive_hardware_i2c_write(int written) { _ric("hardware_i2c_write"); }
//	public void receive_hardware_set_txpower() { _ric("hardware_set_txpower"); }
//	public void receive_hardware_io_port_status(int timestamp, int port, int irq, int state) { _ric("hardware_io_port_status"); }
//	public void receive_hardware_soft_timer(int handle) { _ric("hardware_soft_timer"); }
//	public void receive_hardware_adc_result(int input, int value) { _ric("hardware_adc_result"); }
//
//	// Callbacks for class test (index = 8)
//	public void receive_test_phy_tx() { _ric("test_phy_tx"); }
//	public void receive_test_phy_rx() { _ric("test_phy_rx"); }
//	public void receive_test_phy_end(int counter) { _ric("test_phy_end"); }
//	public void receive_test_phy_reset() { _ric("test_phy_reset"); }
//	public void receive_test_get_channel_map(byte[] channel_map) { _ric("test_get_channel_map"); }
//    
//}