package org.thingml.bglib.gio;

/**
 * Questa classe contiene diversi metodi, usati per ottenere il significato dei vari codici (es: codici errore) del protocollo BGAPI
 * @author Giorgio Avalle
 */
public class BleCodes {
	
	//stampa a schermo
	private static void _line(String msg){
		System.out.println(msg);
	}
	
	
	
	//Le varie enum seguono, nell'ordine, quelle delle specifiche BGAPI
	
	public static String AttributeValueType(int t){
		switch(t){
			case 0: return new String("read");
			case 1: return new String("notify");
			case 2: return new String("indicate");
			case 3: return new String("read_by_type");
			case 4: return new String("read_blob");
			case 5: return new String("indicate_rsp_req");
			default:
				return new String("unknown (" + t + ")");
		}
	}
	
	public static String AttributeChangeReason(int t){
		switch(t){
			case 0: return new String("​write_request");
			case 1: return new String("​write_command");
			case 2: return new String("​write_request_user");
			default:
				return new String("unknown (" + t + ")");
		}
	}
	
	public static String AttributeStatusFlag(int t){
		switch(t){
		case 1: return new String("​flag_notify");
		case 2: return new String("​flag_indicate");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static void ConnectionStatusFlag(int i){
		//considero solo i bit 0 - 3
		boolean connected = (i/128) == 1;
		boolean encrypted = ((i/64)%2) == 1;
		boolean completed = ((i/32)%2) == 1;
		boolean parametersChange = ((i/16)%2) == 1;
		
		_line("Connesso: " + connected);
		_line("Cifratura: " + encrypted);
		_line("Completata: " + completed);
		_line("Parametri modificati: " + parametersChange);
	}
	
	public static String ADFlag(int t){
		switch(t){
		case 1: return new String("​LIMITED_DISCOVERABLE");
		case 2: return new String("​GENERAL_DISCOVERABLE");
		case 4: return new String("​BREDR_NOT_SUPPORTED");
		case 0x10: return new String("SIMULTANEOUS_LEBREDR_CTRL​");
		case 0x20: return new String("SIMULTANEOUS_LEBREDR_HOST​");
		case 0x1F: return new String("​MASK");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String ADTypeFlag(int t){
		switch(t){
		case 0: return new String("​none");
		case 1: return new String("​flags");
		case 2: return new String("​services_16bit_more");
		case 3: return new String("​services_16bit_all");
		case 4: return new String("​services_32bit_more");
		case 5: return new String("​services_32bit_all");
		case 6: return new String("​services_128bit_more");
		case 7: return new String("​services_128bit_all");
		case 8: return new String("​localname_short");
		case 9: return new String("​localname_complete");
		case 10: return new String("​txpower");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String AdvertisingPolicy(int t){
		switch(t){
		case 0: return new String("​all");
		case 1: return new String("​whitelist_scan");
		case 2: return new String("​whitelist_connect");
		case 3: return new String("​whitelist_all");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String BluetoothAddressTypes(int t){
		switch(t){
		case 0: return new String("​public");
		case 1: return new String("​random");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String GAPConnectableMode(int t){
		switch(t){
		case 0: return new String("​non_connectable");
		case 1: return new String("​directed_connectable");
		case 2: return new String("​undirected_connectable");
		case 3: return new String("​scannable_non_connectable");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String GAPDiscoverableMode(int t){
		switch(t){
		case 0: return new String("​non_discoverable");
		case 1: return new String("​limited_discoverable");
		case 2: return new String("​general_discoverable");
		case 3: return new String("​broadcast");
		case 4: return new String("​user_data");
		case 0x80: return new String("​enhanced_broadcasting");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String GAPDiscoverMode(int t){
		switch(t){
		case 0: return new String("​limited");
		case 1: return new String("​generic");
		case 2: return new String("​observation");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String SCAN_HEADER_FLAGS(int t){
		switch(t){
		case 0: return new String("​ADV_IND");
		case 1: return new String("​ADV_DIRECT_IND");
		case 2: return new String("​ADV_NONCONN_IND");
		case 3: return new String("​SCAN_REQ");
		case 4: return new String("​SCAN_RSP");
		case 5: return new String("​CONNECT_REQ");
		case 6: return new String("​ADV_DISCOVER_IND");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String ScanPolicy(int t){
		switch(t){
		case 0: return new String("​all");
		case 1: return new String("​whitelist");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String BondingKeys(int t){
		switch(t){
		case 1: return new String("​ltk");
		case 2: return new String("​addr_public");
		case 4: return new String("​addr_static");
		case 8: return new String("​irk");
		case 0x10: return new String("​edivrand");
		case 0x20: return new String("​csrk");
		case 0x40: return new String("​masterid");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String SMPIOCapabilities(int t){
		switch(t){
		case 0: return new String("​displayonly");
		case 1: return new String("​displayyesno");
		case 2: return new String("​keyboardonly");
		case 3: return new String("​noinputnooutput");
		case 4: return new String("​keyboarddisplay");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	public static String Endpoints(int t){
		switch(t){
		case 0: return new String("​api");
		case 1: return new String("​test");
		case 2: return new String("​script");
		case 3: return new String("​usb");
		case 4: return new String("​uart0");
		case 5: return new String("​uart1");
		default:
			return new String("unknown (" + t + ")");
		}
	}
	
	/**
	 * Ritorna il significato di un codice errore
	 * @param code è il codice errore (intero)
	 */
	public static String error(int code) {
		String name;
		
		//codice errore esadecimale
		String s = BleUtil.toHex((byte) (code/256), (byte) (code % 256));
		
		switch(s){
			case "0180": name = new String("Invalid parameter"); break;
			case "0181": name = new String("Device in wrong state"); break;
			case "0182": name = new String("Out of memory"); break;
			case "0183": name = new String("Feature not implemented"); break;
			case "0184": name = new String("Command not recognized"); break;
			case "0185": name = new String("Timeout"); break;
			case "0186": name = new String("Not connected"); break;
			case "0187": name = new String("Flow"); break;
			case "0188": name = new String("User attribute"); break;
			case "0189": name = new String("Invalid License Key"); break;
			case "018A": name = new String("Command too long"); break;
			case "018B": name = new String("Out of bonds"); break;
			case "0205": name = new String("Authentication failure"); break;
			case "0206": name = new String("Pin or Key missing"); break;
			case "0207": name = new String("Memory Capacity exceeded"); break;
			case "0208": name = new String("Connection Timeout"); break;
			case "0209": name = new String("Connection Limit Exceeded"); break;
			case "020C": name = new String("Command Disallowed"); break;
			case "0212": name = new String("Invalid command parameter"); break;
			case "0213": name = new String("Remote user terminated connection"); break;
			case "0216": name = new String("Connection terminated by local host"); break;
			case "0222": name = new String("LL Response Timeout"); break;
			case "0228": name = new String("LL Instant Passed"); break;
			case "023A": name = new String("Controller Busy"); break;
			case "023B": name = new String("Unaccettable connection interval"); break;
			case "023C": name = new String("Directed Advertising Timeout"); break;
			case "023D": name = new String("MIC Failure"); break;
			case "023E": name = new String("Connection failed to be established"); break;
			case "0301": name = new String("Passkey Entry Failed"); break;
			case "0302": name = new String("OOB Data is not available"); break;
			case "0303": name = new String("Authentication Requirements"); break;
			case "0304": name = new String("Confirm Value Failed"); break;
			case "0305": name = new String("Pairing not supported"); break;
			case "0306": name = new String("Encryption key size"); break;
			case "0307": name = new String("Command not supported"); break;
			case "0308": name = new String("Unspecified Reason"); break;
			case "0309": name = new String("Repeated Attempts"); break;
			case "030A": name = new String("Invalid parameter"); break;
			case "0401": name = new String("Invalid Handle"); break;
			case "0402": name = new String("Read not permitted"); break;
			case "0403": name = new String("Write not permitted"); break;
			case "0404": name = new String("Invalid PDU"); break;
			case "0405": name = new String("Insufficient Authentication"); break;
			case "0406": name = new String("Request not supported"); break;
			case "0407": name = new String("Invalid offset"); break;
			case "0408": name = new String("Insufficient authorization"); break;
			case "0409": name = new String("Prepare Queue Full"); break;
			case "040A": name = new String("Attribute Not Found"); break;
			case "040B": name = new String("Attribute Not Long"); break;
			case "040C": name = new String("Insufficient Encryption Key Size"); break;
			case "040D": name = new String("Invalid Attribute Value Length"); break;
			case "040E": name = new String("Unlikely Error"); break;
			case "040F": name = new String("Insufficient Encryption"); break;
			case "0410": name = new String("Unsupported group type"); break;
			case "0411": name = new String("Insufficient resources"); break;
			case "0480": name = new String("Application Error Codes"); break;
			
			default: 
				name = new String("Descrizione dell'errore non disponibile (codice: " + code + ")");
		}
		
		return name;
	}



	/** Crea il nome esteso del tipo di AD Struct
	 * <br> <a target="_parent" href="https://www.bluetooth.org/en-us/specification/assigned-numbers/generic-access-profile">Riferimento web</a>
	 * 
	 * @return String
	 */
	public static String createExtendedType(String type) {
		switch(type) {
		case "01":
			return "Flags";
		case "02":
			return "Incomplete List of 16-bit Service Class UUIDs";
		case "03":
			return "Complete List of 16-bit Service Class UUIDs";
		case "04": 
			return "Incomplete List of 32-bit Service Class UUIDs";
		case "05":
			return "Complete List of 32-bit Service Class UUIDs";
		case "06":
			return "Incomplete List of 128-bit Service Class UUIDs";
		case "07":
			return "Complete List of 128-bit Service Class UUIDs";
		case "08":
			return "Shortened Local Name";
		case "09":
			return "Complete Local Name";
		case "0A":
			return "Tx Power Level";
		case "0D":
			return "Class of Device";
		case "0E":
			return "Simple Pairing Hash C";
		case "0F":
			return "Simple Pairing Randomizer R";
		case "10":
			return "Security Manager TK Value";
		case "11":
			return "Security Manager Out of Band Flags";
		case "12":
			return "Slave Connection Interval Range";
		case "14":
			return "List of 16-bit Service Solicitation UUIDs";
		case "1F":
			return "List of 32-bit Service Solicitation UUIDs";
		case "15":
			return "List of 128-bit Service Solicitation UUIDs";
		case "16":
			return "Service Data - 16-bit UUID";
		case "20":
			return "Service Data - 32-bit UUID";
		case "21":
			return "Service Data - 128-bit UUID";
		case "22":
			return "​LE Secure Connections Confirmation Value";
		case "23":
			return "​LE Secure Connections Random Value";
		case "24":
			return "URI";
		case "25":
			return "Indoor Positioning";
		case "26":
			return "Transport Discovery Data";
		case "17":
			return "Public Target Address";
		case "18":
			return "Random Target Address";
		case "19":
			return "Appearance";
		case "1A":
			return "Advertising Interval";
		case "1B":
			return "​LE Bluetooth Device Address";
		case "1C":
			return "​LE Role";
		case "1D":
			return "​Simple Pairing Hash C-256";
		case "1E":
			return "​Simple Pairing Randomizer R-256";
		case "3D":
			return "3D Information Data";
		case "FF":
			return "Manufacturer Specific Data";
		default :
			return new String("non riconosciuto (" + type + ")");
		}
	}


	/**
	 * Crea il dominio dell'URL di un dispositivo Eddystone, a partire dal codice esadecimale
	 * @param code è il codice esadecimale del dominio
	 * @return
	 */
	public static String ES_UrlDomain(String code) {
		switch(code) {
			case "00": return new String(".com/");
			case "01": return new String(".org/");
			case "02": return new String(".edu/");
			case "03": return new String(".net/");
			case "04": return new String(".info/");
			case "05": return new String(".biz/");
			case "06": return new String(".gov/");
			case "07": return new String(".com");
			case "08": return new String(".org");
			case "09": return new String(".edu");
			case "0A": return new String(".net");
			case "0B": return new String(".info");
			case "0C": return new String(".biz");
			case "0D": return new String(".gov");
			default: 
				return new String("[Reserved character for future use]");
	}
	}


	/**
	 * Crea lo schema URL per un dispositivo Eddystone, a partire dal codice esadecimale
	 * @param code è il codice esadecimale dello schema
	 * @return
	 */
	public static String ES_UrlScheme(String code) {
		switch(code){
		case "00": return new String("http://www.");
		case "01": return new String("https://www.");
		case "02": return new String("http://");
		case "03": return new String("https://");
		default: 
			return new String("[Not recognized URL Scheme (" + code + ")]");
	}
	}


	/**
	 * Crea il tipo di frame Eddystone, partendo dal codice esadecimale
	 * @param code
	 * @return
	 */
	public static String ES_FrameType(String code) {
		switch(code){
			case "00": return new String("Eddystone-UID"); 
			case "10": return new String("Eddystone-URL");
			case "20": return new String("Eddystone-TLM");
			default: 
				return new String("Unrecognized frame type ("+ code + ")");
		}
	}


	/**
	 * Ritorna il significato di una flag (ADStruct = Flag), a partire dal codice esadecimale
	 * @param code è il codice esadecimale della flag
	 * @return
	 */
	public static String flag(String code) {
		switch(code) {
			case "00": return new String("​LE Limited Discoverable Mode");
			case "01": return new String("​LE General Discoverable Mode");
			case "02": return new String("​BR/EDR not Supported");
			case "03": return new String("​Simultaneous LE adn BR/EDR (Controller)");
			case "04": return new String("​Simultaneous LE adn BR/EDR (Host)");
			case "06": return new String("Non-connectable, undirected advertising, single-mode device");    //TODO: sicuro?
			default: 
				return new String("​Not recognized (" + code + ")");
		}
	}


	/**
	 * Ritorna il ruolo del dispositivo, a partire dal suo codice esadecimale
	 * @param code è il codice esadecimale del ruolo
	 * @return
	 */
	public static String role(String code) {
		switch(code){
			case "00": return new String("Only Peripheral");
			case "01": return new String("Only Central");
			case "02": return new String("Peripheral preferred");
			case "03": return new String("Central preferred");
			default: 
				return new String("Non riconosciuto (" + code + ")");
		}
	}
	
	
	/**
	 * Crea il campo "manufacturer", contenente il nome del produttore del dispositivo
	 * <br> <a target="_parent" href="https://www.bluetooth.com/specifications/assigned-numbers/company-Identifiers">Riferimento web</a>
	 */
	public static String manufacturer(String code) {
		switch(code) {
			case "0000": return new String("​​Ericsson Technology Licensing"); 
			case "0001": return new String("​Nokia Mobile Phones"); 
			case "0002": return new String("​Intel Corp."); 
			case "0003": return new String("​IBM Corp."); 
			case "0004": return new String("​Toshiba Corp."); 
			case "0005": return new String("​3Com"); 
			case "0006": return new String("​Microsoft"); 
			case "0007": return new String("​Lucent"); 
			case "0008": return new String("​Motorola"); 
			case "0009": return new String("​Infineon Technologies AG"); 
			case "000A": return new String("​Cambridge Silicon Radio"); 
			case "000B": return new String("​Silicon Wave"); 
			case "000C": return new String("​Digianswer A/S"); 
			case "000D": return new String("​Texas Instruments Inc."); 
			case "000E": return new String("​Ceva, Inc. (formerly Parthus Technologies, Inc.)"); 
			case "000F": return new String("​Broadcom Corporation"); 
			case "0010": return new String("​Mitel Semiconductor"); 
			case "0011": return new String("​Widcomm, Inc"); 
			case "0012": return new String("​Zeevo, Inc."); 
			case "0013": return new String("​Atmel Corporation"); 
			case "0014": return new String("​Mitsubishi Electric Corporation"); 
			case "0015": return new String("​RTX Telecom A/S"); 
			case "0016": return new String("​KC Technology Inc."); 
			case "0017": return new String("​NewLogic"); 
			case "0018": return new String("​Transilica, Inc."); 
			case "0019": return new String("​Rohde & Schwarz GmbH & Co. KG"); 
			case "001A": return new String("​TTPCom Limited"); 
			case "001B": return new String("​Signia Technologies, Inc."); 
			case "001C": return new String("​Conexant Systems Inc."); 
			case "001D": return new String("​Qualcomm"); 
			case "001E": return new String("​Inventel"); 
			case "001F": return new String("​AVM Berlin"); 
			case "0020": return new String("​BandSpeed, Inc."); 
			case "0021": return new String("​Mansella Ltd"); 
			case "0022": return new String("​NEC Corporation"); 
			case "0023": return new String("​WavePlus Technology Co., Ltd."); 
			case "0024": return new String("​Alcatel"); 
			case "0025": return new String("​NXP Semiconductors (formerly Philips Semiconductors)"); 
			case "0026": return new String("​C Technologies"); 
			case "0027": return new String("​Open Interface"); 
			case "0028": return new String("​R F Micro Devices"); 
			case "0029": return new String("​Hitachi Ltd"); 
			case "002A": return new String("​Symbol Technologies, Inc."); 
			case "002B": return new String("​Tenovis"); 
			case "002C": return new String("​Macronix International Co. Ltd."); 
			case "002D": return new String("​GCT Semiconductor"); 
			case "002E": return new String("​Norwood Systems"); 
			case "002F": return new String("​MewTel Technology Inc."); 
			case "0030": return new String("​ST Microelectronics"); 
			case "0031": return new String("​Synopsis"); 
			case "0032": return new String("​Red-M (Communications) Ltd"); 
			case "0033": return new String("​Commil Ltd"); 
			case "0034": return new String("​Computer Access Technology Corporation (CATC)"); 
			case "0035": return new String("​Eclipse (HQ Espana) S.L."); 
			case "0036": return new String("​Renesas Electronics Corporation"); 
			case "0037": return new String("​Mobilian Corporation"); 
			case "0038": return new String("​Terax"); 
			case "0039": return new String("​Integrated System Solution Corp."); 
			case "003A": return new String("​Matsushita Electric Industrial Co., Ltd."); 
			case "003B": return new String("​Gennum Corporation"); 
			case "003C": return new String("​BlackBerry Limited (formerly Research In Motion)"); 
			case "003D": return new String("​IPextreme, Inc."); 
			case "003E": return new String("​Systems and Chips, Inc."); 
			case "003F": return new String("​Bluetooth SIG, Inc."); 
			case "0040": return new String("​Seiko Epson Corporation"); 
			case "0041": return new String("​Integrated Silicon Solution Taiwan, Inc."); 
			case "0042": return new String("​CONWISE Technology Corporation Ltd"); 
			case "0043": return new String("​PARROT SA"); 
			case "0044": return new String("​Socket Mobile"); 
			case "0045": return new String("​Atheros Communications, Inc."); 
			case "0046": return new String("​MediaTek, Inc."); 
			case "0047": return new String("​Bluegiga"); 
			case "0048": return new String("​Marvell Technology Group Ltd."); 
			case "0049": return new String("​3DSP Corporation"); 
			case "004A": return new String("​Accel Semiconductor Ltd."); 
			case "004B": return new String("​Continental Automotive Systems"); 
			case "004C": return new String("​Apple, Inc."); 
			case "004D": return new String("​Staccato Communications, Inc."); 
			case "004E": return new String("​Avago Technologies"); 
			case "004F": return new String("​APT Licensing Ltd."); 
			case "0050": return new String("​SiRF Technology"); 
			case "0051": return new String("​Tzero Technologies, Inc."); 
			case "0052": return new String("​J&M Corporation"); 
			case "0053": return new String("​Free2move AB"); 
			case "0054": return new String("​3DiJoy Corporation"); 
			case "0055": return new String("​Plantronics, Inc."); 
			case "0056": return new String("​Sony Ericsson Mobile Communications"); 
			case "0057": return new String("​Harman International Industries, Inc."); 
			case "0058": return new String("​Vizio, Inc."); 
			case "0059": return new String("​Nordic Semiconductor ASA"); 
			case "005A": return new String("​EM Microelectronic-Marin SA"); 
			case "005B": return new String("​Ralink Technology Corporation"); 
			case "005C": return new String("​Belkin International, Inc."); 
			case "005D": return new String("​Realtek Semiconductor Corporation"); 
			case "005E": return new String("​Stonestreet One, LLC"); 
			case "005F": return new String("​Wicentric, Inc."); 
			case "0060": return new String("​RivieraWaves S.A.S"); 
			case "0061": return new String("​RDA Microelectronics"); 
			case "0062": return new String("​Gibson Guitars"); 
			case "0063": return new String("​MiCommand Inc."); 
			case "0064": return new String("​Band XI International, LLC"); 
			case "0065": return new String("​Hewlett-Packard Company"); 
			case "0066": return new String("​9Solutions Oy"); 
			case "0067": return new String("​GN Netcom A/S"); 
			case "0068": return new String("​General Motors"); 
			case "0069": return new String("​A&D Engineering, Inc."); 
			case "006A": return new String("​MindTree Ltd."); 
			case "006B": return new String("​Polar Electro OY"); 
			case "006C": return new String("​Beautiful Enterprise Co., Ltd."); 
			case "006D": return new String("​BriarTek, Inc."); 
			case "006E": return new String("​Summit Data Communications, Inc."); 
			case "006F": return new String("​Sound ID"); 
			case "0070": return new String("​Monster, LLC"); 
			case "0071": return new String("​connectBlue AB"); 
			case "0072": return new String("​ShangHai Super Smart Electronics Co. Ltd."); 
			case "0073": return new String("​Group Sense Ltd."); 
			case "0074": return new String("​Zomm, LLC"); 
			case "0075": return new String("​Samsung Electronics Co. Ltd."); 
			case "0076": return new String("​Creative Technology Ltd."); 
			case "0077": return new String("​Laird Technologies"); 
			case "0078": return new String("​Nike, Inc."); 
			case "0079": return new String("​lesswire AG"); 
			case "007A": return new String("​MStar Semiconductor, Inc."); 
			case "007B": return new String("​Hanlynn Technologies"); 
			case "007C": return new String("​A & R Cambridge"); 
			case "007D": return new String("​Seers Technology Co. Ltd"); 
			case "007E": return new String("​Sports Tracking Technologies Ltd."); 
			case "007F": return new String("​Autonet Mobile"); 
			case "0080": return new String("​DeLorme Publishing Company, Inc."); 
			case "0081": return new String("​WuXi Vimicro"); 
			case "0082": return new String("​Sennheiser Communications A/S"); 
			case "0083": return new String("​TimeKeeping Systems, Inc."); 
			case "0084": return new String("​Ludus Helsinki Ltd."); 
			case "0085": return new String("​BlueRadios, Inc."); 
			case "0086": return new String("​equinox AG"); 
			case "0087": return new String("​Garmin International, Inc."); 
			case "0088": return new String("​Ecotest"); 
			case "0089": return new String("​GN ReSound A/S"); 
			case "008A": return new String("​Jawbone"); 
			case "008B": return new String("​Topcorn Positioning Systems, LLC"); 
			case "008C": return new String("​Gimbal Inc. (formerly Qualcomm Labs, Inc. and Qualcomm Retail Solutions, Inc.)​​"); 
			case "008D": return new String("​Zscan Software"); 
			case "008E": return new String("​Quintic Corp."); 
			case "008F": return new String("​Stollman E+V GmbH"); 
			case "0090": return new String("​Funai Electric Co., Ltd."); 
			case "0091": return new String("​Advanced PANMOBIL Systems GmbH & Co. KG"); 
			case "0092": return new String("​ThinkOptics, Inc."); 
			case "0093": return new String("​Universal Electronics, Inc."); 
			case "0094": return new String("​Airoha Technology Corp."); 
			case "0095": return new String("​NEC Lighting, Ltd."); 
			case "0096": return new String("​ODM Technology, Inc."); 
			case "0097": return new String("​ConnecteDevice Ltd."); 
			case "0098": return new String("​zer01.tv GmbH"); 
			case "0099": return new String("​i.Tech Dynamic Global Distribution Ltd."); 
			case "009A": return new String("​Alpwise"); 
			case "009B": return new String("​Jiangsu Toppower Automotive Electronics Co., Ltd."); 
			case "009C": return new String("​Colorfy, Inc."); 
			case "009D": return new String("​Geoforce Inc."); 
			case "009E": return new String("​Bose Corporation"); 
			case "009F": return new String("​Suunto Oy"); 
			case "00A0": return new String("​Kensington Computer Products Group"); 
			case "00A1": return new String("​SR-Medizinelektronik"); 
			case "00A2": return new String("​Vertu Corporation Limited"); 
			case "00A3": return new String("​Meta Watch Ltd."); 
			case "00A4": return new String("​LINAK A/S"); 
			case "00A5": return new String("​OTL Dynamics LLC"); 
			case "00A6": return new String("​Panda Ocean Inc."); 
			case "00A7": return new String("​Visteon Corporation"); 
			case "00A8": return new String("​ARP Devices Limited"); 
			case "00A9": return new String("​Magneti Marelli S.p.A"); 
			case "00AA": return new String("​CAEN RFID srl"); 
			case "00AB": return new String("​Ingenieur-Systemgruppe Zahn GmbH"); 
			case "00AC": return new String("​Green Throttle Games"); 
			case "00AD": return new String("​Peter Systemtechnik GmbH"); 
			case "00AE": return new String("​Omegawave Oy"); 
			case "00AF": return new String("​Cinetix"); 
			case "00B0": return new String("​Passif Semiconductor Corp"); 
			case "00B1": return new String("​Saris Cycling Group, Inc"); 
			case "00B2": return new String("​​Bekey A/S"); 
			case "00B3": return new String("​​Clarinox Technologies Pty. Ltd."); 
			case "00B4": return new String("​​BDE Technology Co., Ltd."); 
			case "00B5": return new String("​Swirl Networks"); 
			case "00B6": return new String("​​Meso international"); 
			case "00B7": return new String("​​TreLab Ltd"); 
			case "00B8": return new String("​​Qualcomm Innovation Center, Inc. (QuIC)"); 
			case "00B9": return new String("​​​Johnson Controls, Inc."); 
			case "00BA": return new String("​​Starkey Laboratories Inc."); 
			case "00BB": return new String("​​​S-Power Electronics Limited"); 
			case "00BC": return new String("​​​Ace Sensor Inc"); 
			case "00BD": return new String("​​​Aplix Corporation"); 
			case "00BE": return new String("​​​AAMP of America"); 
			case "00BF": return new String("​​​Stalmart Technology Limited"); 
			case "00C0": return new String("​​​AMICCOM Electronics Corporation"); 
			case "00C1": return new String("​​​Shenzhen Excelsecu Data Technology Co.,Ltd"); 
			case "00C2": return new String("​​​Geneq Inc."); 
			case "00C3": return new String("​​​adidas AG"); 
			case "00C4": return new String("​​​LG Electronics​"); 
			case "00C5": return new String("​​Onset Computer Corporation"); 
			case "00C6": return new String("​​Selfly BV"); 
			case "00C​7": return new String("​​Quuppa Oy."); 
			case "00C8": return new String("​GeLo Inc"); 
			case "00C9": return new String("​Evluma"); 
			case "00CA": return new String("MC10"); 
			case "00CB": return new String("​Binauric SE"); 
			case "00CC": return new String("​Beats Electronics"); 
			case "00CD": return new String("​Microchip Technology Inc."); 
			case "00CE": return new String("​Elgato Systems GmbH"); 
			case "00CF": return new String("​ARCHOS SA"); 
			case "00D0": return new String("​Dexcom, Inc."); 
			case "00D1": return new String("​Polar Electro Europe B.V."); 
			case "00D2": return new String("​Dialog Semiconductor B.V."); 
			case "00D3": return new String("​Taixingbang Technology (HK) Co,. LTD."); 
			case "00D4": return new String("​Kawantech"); 
			case "00D5": return new String("​Austco Communication Systems"); 
			case "00D6": return new String("​Timex Group USA, Inc."); 
			case "00D7": return new String("​Qualcomm Technologies, Inc."); 
			case "00D8": return new String("​Qualcomm Connected Experiences, Inc."); 
			case "00D9": return new String("​Voyetra Turtle Beach"); 
			case "00DA": return new String("​txtr GmbH"); 
			case "00DB": return new String("​Biosentronics"); 
			case "00DC": return new String("​Procter & Gamble"); 
			case "00DD": return new String("​Hosiden Corporation"); 
			case "00DE": return new String("​Muzik LLC"); 
			case "00DF": return new String("​Misfit Wearables Corp"); 
			case "00E0": return new String("​Google"); 
			case "00E1": return new String("​Danlers Ltd"); 
			case "00E2": return new String("​Semilink Inc"); 
			case "00E3": return new String("​inMusic Brands, Inc"); 
			case "00E4": return new String("​L.S. Research Inc."); 
			case "00E5": return new String("​Eden Software Consultants Ltd."); 
			case "00E6": return new String("​Freshtemp"); 
			case "00E7": return new String("​​KS Technologies"); 
			case "00E8": return new String("​​ACTS Technologies"); 
			case "00E9": return new String("​​Vtrack Systems"); 
			case "00EA": return new String("​​Nielsen-Kellerman Company"); 
			case "00EB": return new String("​​Server Technology, Inc."); 
			case "00EC": return new String("​​BioResearch Associates"); 
			case "00ED": return new String("​​Jolly Logic, LLC"); 
			case "00EE": return new String("​​Above Average Outcomes, Inc."); 
			case "00EF": return new String("​​Bitsplitters GmbH"); 
			case "00F0": return new String("​​PayPal, Inc."); 
			case "00F1": return new String("​​Witron Technology Limited"); 
			case "00F2": return new String("​​Aether Things Inc. (formerly Morse Project Inc.)"); 
			case "00F3": return new String("​​Kent Displays Inc."); 
			case "00F4": return new String("​Nautilus Inc​."); 
			case "00F5": return new String("​​Smartifier Oy"); 
			case "00F6": return new String("​​Elcometer Limited"); 
			case "00F7": return new String("​​VSN Technologies Inc."); 
			case "00F8": return new String("​​AceUni Corp., Ltd."); 
			case "00F9": return new String("​​StickNFind"); 
			case "00FA": return new String("​​Crystal Code AB"); 
			case "00FB": return new String("​​KOUKAAM a.s."); 
			case "00FC": return new String("​Delphi Corporation"); 
			case "00FD": return new String("​​ValenceTech Limited"); 
			case "00FE": return new String("​Reserved"); 
			case "00FF": return new String("​​Typo Products, LLC"); 
			case "0100": return new String("​​TomTom International BV"); 
			case "0101": return new String("​​Fugoo, Inc"); 
			case "0102": return new String("​​Keiser Corporation"); 
			case "0103": return new String("​​Bang & Olufsen A/S"); 
			case "0104": return new String("​​PLUS Locations Systems Pty Ltd"); 
			case "0105": return new String("​​Ubiquitous Computing Technology Corporation"); 
			case "0106": return new String("​​Innovative Yachtter Solutions"); 
			case "0107": return new String("​​William Demant Holding A/S"); 
			case "0108": return new String("​​Chicony Electronics Co., Ltd."); 
			case "0109": return new String("​​Atus BV"); 
			case "010A": return new String("​​Codegate Ltd."); 
			case "010B": return new String("​ERi, Inc."); 
			case "010C": return new String("​​Transducers Direct, LLC"); 
			case "010D": return new String("​​Fujitsu Ten Limited"); 
			case "010E": return new String("​​Audi AG"); 
			case "010F": return new String("​​HiSilicon Technologies Co., Ltd."); 
			case "0110": return new String("​​Nippon Seiki Co., Ltd."); 
			case "0111": return new String("​​Steelseries ApS"); 
			case "0112": return new String("​Visybl Inc."); 
			case "0113": return new String("​​Openbrain Technologies, Co., Ltd."); 
			case "0114": return new String("​​Xensr"); 
			case "0115": return new String("​e.solutions"); 
			case "0116": return new String("​​1OAK Technologies"); 
			case "0117": return new String("​​Wimoto Technologies Inc"); 
			case "0118": return new String("​​Radius Networks, Inc."); 
			case "0119": return new String("​​Wize Technology Co., Ltd."); 
			case "011A": return new String("​​Qualcomm Labs, Inc."); 
			case "011B": return new String("​​Aruba Networks"); 
			case "011C": return new String("​​Baidu"); 
			case "011D": return new String("​​Arendi AG"); 
			case "011E": return new String("​​Skoda Auto a.s."); 
			case "011F": return new String("​​Volkswagon AG"); 
			case "0120": return new String("​​Porsche AG"); 
			case "0121": return new String("​​Sino Wealth Electronic Ltd."); 
			case "0122": return new String("​​AirTurn, Inc."); 
			case "0123": return new String("​​Kinsa, Inc."); 
			case "0124": return new String("​​HID Global"); 
			case "0125": return new String("​​SEAT es"); 
			case "0126": return new String("​​Promethean Ltd."); 
			case "0127": return new String("​​Salutica Allied Solutions"); 
			case "0128": return new String("​​GPSI Group Pty Ltd"); 
			case "0129": return new String("​​Nimble Devices Oy"); 
			case "012A": return new String("​​Changzhou Yongse Infotech Co., Ltd"); 
			case "012B": return new String("​​SportIQ"); 
			case "012C": return new String("​​TEMEC Instruments B.V."); 
			case "012D": return new String("​​Sony Corporation"); 
			case "012E": return new String("​​ASSA ABLOY"); 
			case "012F": return new String("​​Clarion Co., Ltd."); 
			case "0130": return new String("​​Warehouse Innovations"); 
			case "0131": return new String("​​Cypress Semiconductor Corporation"); 
			case "0132": return new String("​​MADS Inc"); 
			case "0133": return new String("​​Blue Maestro Limited"); 
			case "0134": return new String("​​Resolution Products, Inc."); 
			case "0135": return new String("​​Airewear LLC"); 
			case "0136": return new String("​Seed Labs, Inc. (formerly ETC sp. z.o.o.)​"); 
			case "0137": return new String("​​Prestigio Plaza Ltd."); 
			case "0138": return new String("​​NTEO Inc."); 
			case "0139": return new String("​​Focus Systems Corporation"); 
			case "013A": return new String("​​Tencent Holdings Limited"); 
			case "013B": return new String("​​Allegion"); 
			case "013C": return new String("​​Murata Manufacuring Co., Ltd."); 
			case "013E": return new String("​​Nod, Inc."); 
			case "013F": return new String("​​B&B Manufacturing Company"); 
			case "0140": return new String("​​Alpine Electronics (China) Co., Ltd"); 
			case "0141": return new String("​​FedEx Services"); 
			case "0142": return new String("​​Grape Systems Inc."); 
			case "0143": return new String("​​Bkon Connect"); 
			case "0144": return new String("​​Lintech GmbH"); 
			case "0145": return new String("​​Novatel Wireless"); 
			case "0146": return new String("​​Ciright"); 
			case "0147": return new String("​​Mighty Cast, Inc."); 
			case "0148": return new String("​​Ambimat Electronics"); 
			case "0149": return new String("​​Perytons Ltd."); 
			case "014A": return new String("​​Tivoli Audio, LLC"); 
			case "014B": return new String("​​Master Lock"); 
			case "014C": return new String("​​Mesh-Net Ltd"); 
			case "014D": return new String("​​Huizhou Desay SV Automotive CO., LTD."); 
			case "014E": return new String("​Tangerine, Inc."); 
			case "014F": return new String("​B&W Group Ltd."); 
			case "0150": return new String("​​Pioneer Corporation"); 
			case "0151": return new String("​​OnBeep"); 
			case "0152": return new String("​​Vernier Software & Technology"); 
			case "0153": return new String("​​ROL Ergo"); 
			case "0154": return new String("​​Pebble Technology"); 
			case "0155": return new String("​​NETATMO"); 
			case "0156": return new String("​​Accumulate AB"); 
			case "0157": return new String("​​Anhui Huami Information Technology Co., Ltd."); 
			case "0158": return new String("​​Inmite s.r.o."); 
			case "0159": return new String("​​ChefSteps, Inc."); 
			case "015A": return new String("​​micas AG"); 
			case "015B": return new String("​​Biomedical Research Ltd."); 
			case "015C": return new String("​Pitius Tec S.L."); 
			case "015D": return new String("​Estimote, Inc."); 
			case "015E": return new String("​Unikey Technologies, Inc."); 
			case "015F": return new String("​Timer Cap Co."); 
			case "0160": return new String("​AwoX"); 
			case "0161": return new String("​yikes"); 
			case "0162": return new String("​MADSGlobal NZ Ltd."); 
			case "0163": return new String("​PCH International"); 
			case "0164": return new String("​Qingdao Yeelink Information Technology Co., Ltd."); 
			case "0165": return new String("​Milwaukee Tool (formerly Milwaukee Electric Tools)"); 
			case "0166": return new String("​MISHIK Pte Ltd"); 
			case "0167": return new String("​Bayer HealthCare"); 
			case "0168": return new String("​Spicebox LLC"); 
			case "0169": return new String("​emberlight"); 
			case "016A": return new String("​Cooper-Atkins Corporation"); 
			case "016B": return new String("​Qblinks"); 
			case "016C": return new String("​MYSPHERA"); 
			case "016D": return new String("​LifeScan Inc"); 
			case "016E": return new String("​Volantic AB"); 
			case "016F": return new String("​Podo Labs, Inc"); 
			case "0170": return new String("​F. Hoffmann-La Roche AG"); 
			case "0171": return new String("​Amazon Fulfillment Service"); 
			case "0172": return new String("​Connovate Technology Private Limited"); 
			case "0173": return new String("​Kocomojo, LLC"); 
			case "0174": return new String("​Everykey LLC"); 
			case "0175": return new String("​Dynamic Controls"); 
			case "0176": return new String("​SentriLock"); 
			case "0177": return new String("​I-SYST inc."); 
			case "0178": return new String("​CASIO COMPUTER CO., LTD."); 
			case "0179": return new String("​LAPIS Semiconductor Co., Ltd."); 
			case "017A": return new String("​Telemonitor, Inc."); 
			case "017B": return new String("​taskit GmbH"); 
			case "017C": return new String("​Daimler AG"); 
			case "017D": return new String("​BatAndCat"); 
			case "017E": return new String("​BluDotz Ltd"); 
			case "017F": return new String("​XTel ApS"); 
			case "0180": return new String("​Gigaset Communications GmbH"); 
			case "0181": return new String("​Gecko Health Innovations, Inc."); 
			case "0182": return new String("​HOP Ubiquitous"); 
			case "0183": return new String("​To Be Assigned"); 
			case "0184": return new String("​Nectar"); 
			case "0185": return new String("​bel'apps LLC"); 
			case "0186": return new String("​CORE Lighting Ltd"); 
			case "0187": return new String("​Seraphim Sense Ltd"); 
			case "0188": return new String("​Unico RBC"); 
			case "0189": return new String("​Physical Enterprises Inc."); 
			case "018A": return new String("​Able Trend Technology Limited"); 
			case "018B": return new String("​Konica Minolta, Inc."); 
			case "018C": return new String("​Wilo SE"); 
			case "018D": return new String("​Extron Design Services"); 
			case "018E": return new String("​Fitbit, Inc."); 
			case "018F": return new String("​Fireflies Systems"); 
			case "0190": return new String("​Intelletto Technologies Inc."); 
			case "0191": return new String("​FDK CORPORATION"); 
			case "0192": return new String("​Cloudleaf, Inc"); 
			case "0193": return new String("​Maveric Automation LLC"); 
			case "0194": return new String("​Acoustic Stream Corporation"); 
			case "0195": return new String("​Zuli"); 
			case "0196": return new String("​Paxton Access Ltd"); 
			case "0197": return new String("​WiSilica Inc"); 
			case "0198": return new String("​Vengit Limited"); 
			case "0199": return new String("​SALTO SYSTEMS S.L."); 
			case "019A": return new String("​TRON Forum (formerly T-Engine Forum)"); 
			case "019B": return new String("​CUBETECH s.r.o."); 
			case "019C": return new String("​Cokiya Incorporated"); 
			case "019D": return new String("​CVS Health"); 
			case "019E": return new String("​Ceruus"); 
			case "019F": return new String("​Strainstall Ltd"); 
			case "01A0": return new String("​Channel Enterprises (HK) Ltd."); 
			case "01A1": return new String("​FIAMM"); 
			case "01A2": return new String("​GIGALANE.CO.,LTD"); 
			case "01A3": return new String("​EROAD"); 
			case "01A4": return new String("​Mine Safety Appliances"); 
			case "01A5": return new String("​Icon Health and Fitness"); 
			case "01A6": return new String("​Asandoo GmbH"); 
			case "01A7": return new String("​ENERGOUS CORPORATION"); 
			case "01A8": return new String("​Taobao"); 
			case "01A9": return new String("​Canon Inc."); 
			case "01AA": return new String("​Geophysical Technology Inc."); 
			case "01AB": return new String("​Facebook, Inc."); 
			case "01AC": return new String("​Nipro Diagnostics, Inc."); 
			case "01AD": return new String("​FlightSafety International"); 
			case "01AE": return new String("​Earlens Corporation"); 
			case "01AF": return new String("​Sunrise Micro Devices, Inc."); 
			case "01B0": return new String("​Star Micronics Co., Ltd."); 
			case "01B1": return new String("​Netizens Sp. z o.o."); 
			case "01B2": return new String("​Nymi Inc."); 
			case "01B3": return new String("​Nytec, Inc."); 
			case "01B4": return new String("​Trineo Sp. z o.o."); 
			case "01B5": return new String("​Nest Labs Inc."); 
			case "01B6": return new String("​LM Technologies Ltd"); 
			case "01B7": return new String("​General Electric Company"); 
			case "01B8": return new String("​i+D3 S.L."); 
			case "01B9": return new String("​HANA Micron"); 
			case "01BA": return new String("​Stages Cycling LLC"); 
			case "01BB": return new String("​Cochlear Bone Anchored Solutions AB"); 
			case "01BC": return new String("​SenionLab AB"); 
			case "01BD": return new String("​Syszone Co., Ltd"); 
			case "01BE": return new String("​Pulsate Mobile Ltd."); 
			case "01BF": return new String("​Hong Kong HunterSun Electronic Limited"); 
			case "01C0": return new String("​pironex GmbH"); 
			case "01C1": return new String("​BRADATECH Corp."); 
			case "01C2": return new String("​Transenergooil AG"); 
			case "01C3": return new String("​Bunch"); 
			case "01C4": return new String("​DME Microelectronics"); 
			case "01C5": return new String("​Bitcraze AB"); 
			case "01C6": return new String("​HASWARE Inc."); 
			case "01C7": return new String("​Abiogenix Inc."); 
			case "01C8": return new String("​Poly-Control ApS"); 
			case "01C9": return new String("​Avi-on"); 
			case "01CA": return new String("​Laerdal Medical AS"); 
			case "01CB": return new String("​Fetch My Pet"); 
			case "01CC": return new String("​Sam Labs Ltd."); 
			case "01CD": return new String("​Chengdu Synwing Technology Ltd"); 
			case "01CE": return new String("​HOUWA SYSTEM DESIGN, k.k."); 
			case "01CF": return new String("​BSH"); 
			case "01D0": return new String("​Primus Inter Pares Ltd"); 
			case "01D1": return new String("​August"); 
			case "01D2": return new String("​Gill Electronics"); 
			case "01D3": return new String("​Sky Wave Design"); 
			case "01D4": return new String("​Newlab S.r.l."); 
			case "01D5": return new String("​ELAD srl​"); 
			case "01D6": return new String("​​G-wearables inc."); 
			case "01D7": return new String("​​Squadrone Systems Inc."); 
			case "01D8": return new String("​​Code Corporation"); 
			case "01D9": return new String("​​Savant Systems LLC"); 
			case "01DA": return new String("​​Logitech International SA"); 
			case "01DB": return new String("​​Innblue Consulting"); 
			case "01DC": return new String("​​iParking Ltd."); 
			case "01DD": return new String("​​Koninklijke Philips Electronics N.V."); 
			case "01DE": return new String("​​Minelab Electronics Pty Limited"); 
			case "01DF": return new String("​​Bison Group Ltd."); 
			case "01E0": return new String("​​Widex A/S"); 
			case "01E1": return new String("​​Jolla Ltd"); 
			case "01E2": return new String("​​Lectronix, Inc."); 
			case "01E3": return new String("​​Caterpillar Inc"); 
			case "01E4": return new String("​​Freedom Innovations"); 
			case "01E5": return new String("​​Dynamic Devices Ltd"); 
			case "01E6": return new String("​​Technology Solutions (UK) Ltd"); 
			case "01E7": return new String("​​IPS Group Inc."); 
			case "01E8": return new String("​​STIR"); 
			case "01E9": return new String("​Sano, Inc​"); 
			case "01EA": return new String("​Advanced Application Design, Inc.​"); 
			case "01EB": return new String("AutoMap LLC​"); 
			case "01EC": return new String("​​Spreadtrum Communications Shanghai Ltd"); 
			case "01ED": return new String("​​CuteCircuit LTD"); 
			case "01EE": return new String("​​Valeo Service"); 
			case "01EF": return new String("​​Fullpower Technologies, Inc."); 
			case "01F0": return new String("​​KloudNation"); 
			case "01F1": return new String("​​Zebra Technologies Corporation"); 
			case "01F2": return new String("​​Itron, Inc."); 
			case "01F3": return new String("​​The University of Tokyo"); 
			case "01F4": return new String("​​UTC Fire and Security"); 
			case "01F5": return new String("​​Cool Webthings Limited"); 
			case "01F6": return new String("​​DJO Global"); 
			case "01F7": return new String("​​Gelliner Limited"); 
			case "01F8": return new String("​​Anyka (Guangzhou) Microelectronics Technology Co, LTD"); 
			case "01F9": return new String("​​Medtronic, Inc."); 
			case "01FA": return new String("​​Gozio, Inc."); 
			case "01FB": return new String("​​Form Lifting, LLC"); 
			case "01FC": return new String("​​Wahoo Fitness, LLC"); 
			case "01FD": return new String("​​Kontakt Micro-Location Sp. z o.o."); 
			case "01FE": return new String("​​Radio System Corporation"); 
			case "01FF": return new String("​​Freescale Semiconductor, Inc."); 
			case "0200": return new String("​​Verifone Systems PTe Ltd. Taiwan Branch"); 
			case "0201": return new String("​​AR Timing"); 
			case "0202": return new String("​​Rigado LLC"); 
			case "0203": return new String("​​Kemppi Oy"); 
			case "0204": return new String("​Tapcentive Inc."); 
			case "0205": return new String("Smartbotics Inc.​"); 
			case "0206": return new String("​Otter Products, LLC​"); 
			case "0207": return new String("​STEMP Inc."); 
			case "0208": return new String("​​LumiGeek LLC"); 
			case "0209": return new String("​InvisionHeart Inc."); 
			case "020A": return new String("​Macnica Inc. ​"); 
			case "020B": return new String("​​Jaguar Land Rover Limited"); 
			case "020C": return new String("​​CoroWare Technologies, Inc"); 
			case "020D": return new String("​​Simplo Technology Co., LTD"); 
			case "020E": return new String("​​Omron Healthcare Co., LTD"); 
			case "020F": return new String("​​Comodule GMBH"); 
			case "0210": return new String("​​ikeGPS"); 
			case "0211": return new String("​​Telink Semiconductor Co. Ltd"); 
			case "0212": return new String("​​Interplan Co., Ltd"); 
			case "0213": return new String("​​Wyler AG"); 
			case "0214": return new String("​​IK Multimedia Production srl"); 
			case "0215": return new String("​​Lukoton Experience Oy"); 
			case "0216": return new String("​​MTI Ltd"); 
			case "0217": return new String("​​Tech4home, Lda"); 
			case "0218": return new String("​​Hiotech AB"); 
			case "0219": return new String("​​DOTT Limited"); 
			case "021A": return new String("​​Blue Speck Labs, LLC"); 
			case "021B": return new String("​​Cisco Systems Inc"); 
			case "021C": return new String("​​Mobicomm Inc"); 
			case "021D": return new String("​​Edamic"); 
			case "021E": return new String("​​Goodnet Ltd"); 
			case "021F": return new String("​​Luster Leaf Products Inc"); 
			case "0220": return new String("​​Manus Machina BV"); 
			case "0221": return new String("​​Mobiquity Networks Inc"); 
			case "0222": return new String("​​Praxis Dynamics"); 
			case "0223": return new String("​​Philip Morris Products S.A."); 
			case "0224": return new String("​Comarch SA"); 
			case "0225": return new String("​Nestlé Nespresso S.A."); 
			case "0226": return new String("​​Merlinia A/S"); 
			case "0227": return new String("​LifeBEAM Technologies"); 
			case "0228": return new String("​​Twocanoes Labs, LLC"); 
			case "0229": return new String("​​Muoverti Limited"); 
			case "022A": return new String("​Stamer Musikanlagen GMBH"); 
			case "022B": return new String("​​Tesla Motors"); 
			case "022C": return new String("​​Pharynks Corporation"); 
			case "022D": return new String("​​Lupine"); 
			case "022E": return new String("​​Siemens AG"); 
			case "022F": return new String("​​Huami (Shanghai) Culture Communication CO., LTD"); 
			case "0230": return new String("​​Foster Electric Company, Ltd"); 
			case "0231": return new String("​ETA SA"); 
			case "0232": return new String("​x-Senso Solutions Kft"); 
			case "0233": return new String("​​Shenzhen SuLong Communication Ltd"); 
			case "0234": return new String("​​FengFan (BeiJing) Technology Co, Ltd"); 
			case "0235": return new String("​​Qrio Inc"); 
			case "0236": return new String("​​Pitpatpet Ltd"); 
			case "0237": return new String("​​MSHeli s.r.l."); 
			case "0238": return new String("​​Trakm8 Ltd"); 
			case "0239": return new String("​​JIN CO, Ltd"); 
			case "023A": return new String("​​Alatech Technology"); 
			case "023B": return new String("​​Beijing CarePulse Electronic Technology Co, Ltd"); 
			case "023C": return new String("​​Awarepoint"); 
			case "023D": return new String("​​ViCentra B.V."); 
			case "023E": return new String("​​Raven Industries"); 
			case "023F": return new String("​​WaveWare Technologies"); 
			case "0240": return new String("​​Argenox Technologies"); 
			case "0241": return new String("​​Bragi GmbH"); 
			case "0242": return new String("​​16Lab Inc"); 
			case "0243": return new String("​​Masimo Corp"); 
			case "0244": return new String("​​Iotera Inc."); 
			case "0245": return new String("​​Endress+Hauser"); 
			case "0246": return new String("​​ACKme Networks, Inc."); 
			case "0247": return new String("​​FiftyThree Inc."); 
			case "0248": return new String("​Parker Hannifin Corp​"); 
			case "0249": return new String("​​Transcranial Ltd"); 
			case "024A": return new String("​​Uwatec AG"); 
			case "024B": return new String("​​Orlan LLC"); 
			case "024C": return new String("​​Blue Clover Devices"); 
			case "024D": return new String("​​M-Way Solutions GmbH"); 
			case "024E": return new String("​​Microtronics Engineering GmbH"); 
			case "024F": return new String("​​Schneider Schreibgeräte GmbH"); 
			case "0250": return new String("​​Sapphire Circuits LLC"); 
			case "0251": return new String("​​Lumo Bodytech Inc."); 
			case "0252": return new String("​​UKC Technosolution"); 
			case "0253": return new String("​​Xicato Inc."); 
			case "0254": return new String("​​Playbrush"); 
			case "0255": return new String("​​Dai Nippon Printing Co., Ltd."); 
			case "0256": return new String("​​G24 Power Limited"); 
			case "0257": return new String("​​AdBabble Local Commerce Inc."); 
			case "0258": return new String("​​Devialet SA"); 
			case "0259": return new String("​​ALTYOR"); 
			case "025A": return new String("​​University of Applied Sciences Valais/Haute Ecole Valaisanne"); 
			case "025B": return new String("​​Five Interactive, LLC dba Zendo"); 
			case "025C": return new String("​​NetEase (Hangzhou) Network co.Ltd."); 
			case "025D": return new String("​​Lexmark International Inc."); 
			case "025E": return new String("​​Fluke Corporation"); 
			case "025F": return new String("​Yardarm Technologies​"); 
			case "0260": return new String("​​SensaRx"); 
			case "0261": return new String("​​SECVRE GmbH"); 
			case "0262": return new String("​​Glacial Ridge Technologies"); 
			case "0263": return new String("​​Identiv, Inc."); 
			case "0264": return new String("DDS, Inc."); 
			case "0265": return new String("​​SMK Corporation"); 
			case "0266": return new String("​​Schawbel Technologies LLC"); 
			case "0267": return new String("​​XMI Systems SA"); 
			case "0268": return new String("​​Cerevo"); 
			case "0269": return new String("​​​Torrox GmbH & Co KG"); 
			case "026A": return new String("​​​Gemalto"); 
			case "026B": return new String("​​DEKA Research & Development Corp.​"); 
			case "026C": return new String("​​Domster Tadeusz Szydlowski"); 
			case "026D": return new String("​​Technogym SPA​"); 
			case "026E": return new String("​​FLEURBAEY BVBA"); 
			case "026F": return new String("​​Aptcode Solutions​"); 
			case "0270": return new String("​​LSI ADL Technology​"); 
			case "0271": return new String("​​Animas Corp"); 
			case "0272": return new String("​​Alps Electric Co., Ltd."); 
			case "0273": return new String("​​OCEASOFT"); 
			case "0274": return new String("​​Motsai Research"); 
			case "0275": return new String("​​Geotab"); 
			case "0276": return new String("​​E.G.O. Elektro-Gerätebau GmbH"); 
			case "0277": return new String("​​​bewhere inc"); 
			case "0278": return new String("​​Johnson Outdoors Inc"); 
			case "0279": return new String("​​steute Schaltgerate GmbH & Co. KG"); 
			case "027A": return new String("​​Ekomini in​c.​"); 
			case "027B": return new String("​DEFA AS"); 
			case "027C": return new String("​Aseptika Ltd​"); 
			case "027D": return new String("​​HUAWEI Technologies Co., Ltd. ( 华为技术有限公司 )​"); 
			case "027E": return new String("​HabitAware, LLC"); 
			case "027F": return new String("​​ruwido austria gmbh​"); 
			case "0280": return new String("​​ITEC corporation"); 
			case "0281": return new String("​​StoneL​"); 
			case "0282": return new String("​Sonova AG"); 
			case "0283": return new String("​​Maven Machines, Inc."); 
			case "0284": return new String("​​Synapse Electronics"); 
			case "0285": return new String("​​Standard Innovation Inc."); 
			case "0286": return new String("​​RF Code, Inc."); 
			case "0287": return new String("​​Wally Ventures S.L."); 
			case "0288": return new String("​​Willowbank Electronics Ltd"); 
			case "0289": return new String("​​SK Telecom"); 
			case "028A": return new String("​​Jetro AS"); 
			case "028B": return new String("​​Code Gears LTD"); 
			case "028C": return new String("​​NANOLINK APS"); 
			case "028D": return new String("​​​IF, LLC"); 
			case "028E": return new String("​​RF Digital Corp"); 
			case "028F": return new String("​​Church & Dwight Co., Inc"); 
			case "0290": return new String("​​Multibit Oy​"); 
			case "0291": return new String("​​CliniCloud Inc​"); 
			case "0292": return new String("​​​SwiftSensors"); 
			case "0293": return new String("​Blue Bite​​"); 
			case "0294": return new String("​​​ELIAS GmbH"); 
			case "0295": return new String("​​Sivantos GmbH"); 
			case "0296": return new String("​​Petzl​"); 
			case "0297": return new String("​​storm power ltd"); 
			case "0298": return new String("​​EISST Ltd​"); 
			case "0299": return new String("​​Inexess Technology Simma KG​"); 
			case "029A": return new String("​​​Currant, Inc."); 
			case "029B": return new String("​​C2 Development, Inc.​"); 
			case "029C": return new String("​​Blue Sky Scientific, LLC"); 
			case "029D": return new String("​​ALOTTAZS LABS, LLC"); 
			case "029E": return new String("​​Kupson spol. s r.o."); 
			case "029F": return new String("​​Areus Engineering GmbH​"); 
			case "02A0": return new String("​​Impossible Camera GmbH"); 
			case "02A1": return new String("​​InventureTrack Systems​"); 
			case "02A2": return new String("​​​LockedUp"); 
			case "02A3": return new String("​​Itude"); 
			case "02A4": return new String("​​Pacific Lock Company"); 
			case "02A5": return new String("​​Tendyron Corporation ( 天地融科技股份有限公司 )​"); 
			case "02A6": return new String("​​Robert Bosch GmbH"); 
			case "02A7": return new String("​​Illuxtron international B.V."); 
			case "02A8": return new String("​​miSport Ltd."); 
			case "02A9": return new String("​​Chargelib"); 
			case "02AA": return new String("​​​Doppler Lab"); 
			case "02AB": return new String("​​BBPOS Limited"); 
			case "02AC": return new String("​​RTB Elektronik GmbH & Co. KG​​"); 
			case "02AD": return new String("​​Rx Networks, Inc."); 
			case "02AE": return new String("​​​WeatherFlow, Inc."); 
			case "02AF": return new String("​Technicolor USA Inc.​"); 
			case "02B0": return new String("​​Bestechnic(Shanghai),Ltd"); 
			case "02B1": return new String("​​​Raden Inc"); 
			case "02B2": return new String("​​JouZen Oy"); 
			case "02B3": return new String("​CLABER S.P.A."); 
			case "02B4": return new String("​​Hyginex, Inc."); 
			case "02B5": return new String("​​HANSHIN ELECTRIC RAILWAY CO.,LTD.​"); 
			case "02B6": return new String("​​Schneider Electric"); 
			case "02B7": return new String("​​Oort Technologies LLC​"); 
			case "02B8": return new String("​​Chrono Therapeutics"); 
			case "02B9": return new String("​Rinnai Corporation"); 
			case "02BA": return new String("​​​Swissprime Technologies AG"); 
			case "02BB": return new String("​​Koha.,Co.Ltd"); 
			case "02BC": return new String("​​Genevac Ltd​"); 
			case "02BD": return new String("​​​Chemtronics"); 
			case "02BE": return new String("​​Seguro Technology Sp. z o.o."); 
			case "02BF": return new String("​​Redbird Flight Simulations"); 
			case "02C0": return new String("​​Dash Robotics"); 
			case "02C1": return new String("​​LINE Corporation​"); 
			case "02C2": return new String("​​​Guillemot Corporation"); 
			case "02C3": return new String("​​Techtronic Power Tools Technology Limited"); 
			case "02C4": return new String("​​Wilson Sporting Goods"); 
			case "02C5": return new String("​​Lenovo (Singapore) Pte Ltd. ( 联想（新加坡） )​"); 
			case "02C6": return new String("​​Ayatan Sensors"); 
			case "02C7": return new String("​​Electronics Tomorrow Limited"); 
			case "02C8": return new String("​​VASCO Data Security International, Inc.​"); 
			case "02C9": return new String("​​PayRange Inc.​"); 
			case "02CA": return new String("​​ABOV ​Semiconductor"); 
			case "02CB": return new String("​​​AINA-Wireless Inc.​"); 
			case "02CC": return new String("​​​Eijkelkamp Soil & Water"); 
			case "02CD": return new String("​​BMA ergonomics b.v."); 
			case "02CE": return new String("​​​Teva Branded Pharmaceutical Products R&D, Inc.​"); 
			case "02CF": return new String("​​Anima"); 
			case "02D0": return new String("​​3M"); 
			case "02D1": return new String("​​​Empatica Srl"); 
			case "02D2": return new String("​​​​Afero, Inc."); 
			case "02D3": return new String("​​Powercast Corporation"); 
			case "02D4": return new String("​​Secuyou ApS"); 
			case "02D5": return new String("​​​OMRON Corporation"); 
			case "02D6": return new String("​​Send Solutions"); 
			case "02D7": return new String("​​​NIPPON SYSTEMWARE CO.,LTD."); 
			case "02D8": return new String("​​Neosfar"); 
			case "02D9": return new String("​​Fliegl Agrartechnik GmbH"); 
			case "02DA": return new String("​​​Gilvader​"); 
			case "02DB": return new String("​​Digi International Inc (R)"); 
			case "02DC": return new String("​​DeWalch Technologies, Inc.​"); 
			case "02DD": return new String("​​Flint Rehabilitation Devices, LLC"); 
			case "02DE": return new String("​​​Samsung SDS Co., Ltd."); 
			case "02DF": return new String("​​Blur Product Development​"); 
			case "02E0": return new String("​​University of Michigan"); 
			case "02E1": return new String("​​Victron Energy BV​"); 
			case "02E2": return new String("​​​​NTT docomo"); 
			case "02E3": return new String("​​Carmanah Technologies Corp.​"); 
			case "02E4": return new String("​​​Bytestorm Ltd."); 
			case "02E5": return new String("​​Espressif Incorporated ( 乐鑫信息科技(上海)有限公司 )"); 
			case "02E6": return new String("​​Unwire​"); 
			case "02E7": return new String("​​Connected Yard, Inc."); 
			case "02E8": return new String("​​American Music Environments"); 
			case "02E9": return new String("​​Sensogram Technologies, Inc."); 
			case "02EA": return new String("​​Fujitsu Limited"); 
			case "02EB": return new String("​​Ardic Technology"); 
			case "02EC": return new String("​​Delta Systems, Inc"); 
			case "02ED": return new String("​​HTC Corporation"); 
			case "02EE": return new String("​​Citizen Holdings Co., Ltd."); 
			case "02EF": return new String("​​SMART-INNOVATION.inc"); 
			case "02F0": return new String("​​Blackrat Software"); 
			case "02F1": return new String("​The Idea Cave, LLC"); 
			case "02F2": return new String("​GoPro, Inc."); 
			case "02F3": return new String("​AuthAir, Inc"); 
			case "02F4": return new String("​Vensi, Inc."); 
			case "02F5": return new String("​Indagem Tech LLC"); 
			case "02F6": return new String("​Intemo Technologies"); 
			case "02F7": return new String("​DreamVisions co., Ltd."); 
			case "02F8": return new String("​Runteq Oy Ltd "); 
			case "02F9": return new String("​IMAGINATION TECHNOLOGIES LTD "); 
			case "02FA": return new String("​CoSTAR Technologies "); 
			case "02FB": return new String("​Clarius Mobile Health Corp. "); 
			case "02FC": return new String("​Shanghai Frequen Microelectronics Co., Ltd. "); 
			case "02FD": return new String("​Uwanna, Inc. "); 
			case "02FE": return new String("​Lierda Science & Technology Group Co., Ltd."); 
			case "02FF": return new String("​Silicon Laboratories"); 
			case "0300": return new String("​World Moto Inc."); 
			case "0301": return new String("​Giatec Scientific Inc."); 
			case "0302": return new String("​Loop Devices, Inc"); 
			case "0303": return new String("​IACA electronique "); 
			case "0304": return new String("​Martians Inc "); 
			case "0305": return new String("​Swipp ApS "); 
			case "0306": return new String("​Life Laboratory Inc. "); 
			case "0307": return new String("​FUJI INDUSTRIAL CO.,LTD."); 
			case "0308": return new String("​Surefire, LLC "); 
			case "0309": return new String("​Dolby Labs "); 
			case "030A": return new String("​Ellisys "); 
			case "030B": return new String("​Magnitude Lighting Converters "); 
			case "030C": return new String("​Hilti AG "); 
			case "030D": return new String("​Devdata S.r.l. "); 
			case "030E": return new String("​Deviceworx "); 
			case "030F": return new String("​Shortcut Labs "); 
			case "0310": return new String("​SGL Italia S.r.l."); 
			case "0311": return new String("​PEEQ DATA"); 
			case "0312": return new String("​Ducere Technologies Pvt Ltd"); 
			case "0313": return new String("​DiveNav, Inc."); 
			case "0314": return new String("​RIIG AI Sp. z o.o."); 
			case "0315": return new String("​Thermo Fisher Scientific"); 
			case "0316": return new String("​AG Measurematics Pvt. Ltd. "); 
			case "0317": return new String("​CHUO Electronics CO., LTD. "); 
			case "0318": return new String("​Aspenta International "); 
			case "0319": return new String("​Eugster Frismag AG "); 
			case "031A": return new String("​Amber wireless GmbH"); 
			case "031B": return new String("​HQ Inc"); 
			case "031C": return new String("​Lab Sensor Solutions"); 
			case "031D": return new String("​Enterlab ApS"); 
			case "031E": return new String("​Eyefi, Inc."); 
			case "031F": return new String("​MetaSystem S.p.A"); 
			case "0320": return new String("​SONO ELECTRONICS. CO., LTD"); 
			case "0321": return new String("​Jewelbots"); 
			case "0322": return new String("​Compumedics Limited "); 
			case "0323": return new String("​Rotor Bike Components "); 
			case "0324": return new String("​Astro, Inc."); 
			case "0325": return new String("​Amotus Solutions "); 
			case "0326": return new String("​Healthwear Technologies (Changzhou)Ltd "); 
			case "0327": return new String("​Essex Electronics "); 
			case "0328": return new String("​Grundfos A/S "); 
			case "0329": return new String("​Eargo, Inc. "); 
			case "032A": return new String("​Electronic Design Lab "); 
			case "032B": return new String("​ESYLUX "); 
			case "032C": return new String("​NIPPON SMT.CO.,Ltd "); 
			case "032D": return new String("​BM innovations GmbH"); 
			case "032E": return new String("​indoormap "); 
			case "032F": return new String("​OttoQ Inc "); 
			case "0330": return new String("​North Pole Engineering "); 
			case "0331": return new String("​3flares Technologies Inc. "); 
			case "0332": return new String("​Electrocompaniet A.S. "); 
			case "0333": return new String("​Mul-T-Lock "); 
			case "0334": return new String("​Corentium AS "); 
			case "0335": return new String("​Enlighted Inc "); 
			case "0336": return new String("​GISTIC "); 
			case "0337": return new String("​AJP2 Holdings, LLC "); 
			case "0338": return new String("​COBI GmbH "); 
			case "0339": return new String("​Blue Sky Scientific, LLC "); 
			case "033A": return new String("​Appception, Inc. "); 
			case "033B": return new String("​Courtney Thorne Limited "); 
			case "033C": return new String("​Virtuosys "); 
			case "033D": return new String("​TPV Technology Limited "); 
			case "033E": return new String("​Monitra SA "); 
			case "033F": return new String("​Automation Components, Inc. "); 
			case "0340": return new String("​Letsense s.r.l. "); 
			case "0341": return new String("​Etesian Technologies LLC "); 
			case "0342": return new String("​GERTEC BRASIL LTDA."); 
			case "0343": return new String("​Drekker Development Pty. Ltd. "); 
			case "0344": return new String("​Whirl Inc "); 
			case "0345": return new String("​Locus Positioning "); 
			case "0346": return new String("​Acuity Brands Lighting, Inc "); 
			case "0347": return new String("​Prevent Biometrics "); 
			case "0348": return new String("​Arioneo "); 
			case "0349": return new String("​VersaMe "); 
			case "034A": return new String("​Vaddio "); 
			case "034B": return new String("​Libratone A/S "); 
			case "034C": return new String("​HM Electronics, Inc. "); 
			case "034D": return new String("​TASER International, Inc. "); 
			case "034E": return new String("​Safe Trust Inc."); 
			case "034F": return new String("​Heartland Payment Systems "); 
			case "0350": return new String("​Bitstrata Systems Inc. "); 
			case "0351": return new String("​Pieps GmbH "); 
			case "0352": return new String("​iRiding(Xiamen)Technology Co.,Ltd. "); 
			case "0353": return new String("​Alpha Audiotronics, Inc. "); 
			case "0354": return new String("​TOPPAN FORMS CO.,LTD. "); 
			case "FFFF": return new String("​​Generic"); 
			default: return new String("​​Not recognized (" + code + ")");
			}
	}


	/**
	 * Ritorna il nome esteso del codice di tipo pacchetto
	 * @param packetType è il codice (decimale) del tipo di pacchetto
	 * @return
	 */
	public static String packetType(int packetType) {
		switch(packetType) {
			case 0: return new String("Connectable Advertisement packet");
			case 2: return new String("Non Connectable Advertisement packet");
			case 4: return new String("Scan response packet");
			case 6: return new String("Discoverable advertisement packet");
			default: return new String("Not recognized (" + packetType + ")");
		}
	}
	
}
