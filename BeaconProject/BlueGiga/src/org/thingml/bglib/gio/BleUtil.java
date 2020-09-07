package org.thingml.bglib.gio;

/**
 * Questa classe contiene diversi metodi, usati per la conversione tra tipi di dato BLE
 * @author Giorgio Avalle
 */
public class BleUtil {
	
	/** Intero senza segno, a 8 bit */
	public static int uint8(byte b){
		int res = (int)(b & 0xFF);
		return res;
	}
	
	/** Intero con segno, a 8 bit */
	public static int int8(byte b){
		int res = b;
		return res;
	}
	
	/** Intero senza segno, a 16 bit */
	public static int uint16(byte b1, byte b2){
		int n1 = BleUtil.uint8(b1);
		int n2 = BleUtil.uint8(b2);
		
		return n2*256 + n1;
	}
	
	/** Intero con segno, a 16 bit */
	public static int int16(byte b1, byte b2){
		//caso 1: numero positivo
		if(uint8(b2) >= 128)
			return uint16(b1, b2);
		
		//caso 2: numero negativo
		int n1 = int8(b1);
		int n2 = ~b2;
		return (n2*256 + n1) * (-1);
	}
	
	/** Intero senza segno, a 32 bit */
	public static int uint32(byte b1, byte b2, byte b3, byte b4){
		int x = BleUtil.uint16(b3, b4);
		int y = BleUtil.uint16(b1, b2);
		
		return x*256*256 + y;
	}
	
	/** Intero senza segno, a 32 bit */
	public static int int32(byte b1, byte b2, byte b3, byte b4){
		//caso 1: numero positivo
		if(uint8(b4) >= 128)
			return uint32(b1, b2, b3, b4);
		
		//caso 2: numero negativo
		int n1 = int8(b1);
		int n2 = ~b2;
		int n3 = ~b3;
		int n4 = ~b4;
		return (((n4*256 + n3)*256 + n2)*256 + n1) * (-1);
	}
	
	
	
	/**
	 * Trasforma il byte in esadecimale maiuscolo
	 * @param b è il byte da convertire
	 * @return String
	 */
	public static String toHex(byte b) {
		int x = uint8(b);
		String res = Integer.toHexString(x).toUpperCase();
		
		if(res.length() < 2)
			return new String("0" + res);
		return res;
	}

	/**
	 * Trasforma i byte (NO little endian!) in esadecimale maiuscolo
	 * @param bytes è l'insieme di byte da convertire, nell'ordine
	 * @return String
	 */
	public static String toHex(byte ... bytes) {
		StringBuffer tmp = new StringBuffer("");
		
		//converto i bytes in hex, nell'ordine
		for(byte b : bytes)
			tmp.append(toHex(b));
		
		return tmp.toString();
	}
	
	/**
	 * Trasforma la stringa in un vettore di byte  (NO little-endian)
	 * @param str è una stringa
	 * @return byte[]
	 */
	public static byte[] uint8array(String str) {
		return uint8array(str, str.length());
	}
	
	/**
	 * Trasforma la stringa in un vettore di byte, di dimensione fissa 'dim'  (NO little-endian)
	 * @param string è la stringa esadecimale da convertire
	 * @param dim è la dimensione dell'array risultato
	 * @return byte[]
	 */
	public static byte[] uint8array(String str, int dim) {
		int b=0;
		int i_max;
		byte[] res = new byte[dim];
		
		//lettura della stringa, eventualmente troncata a 'dim'
		i_max = Math.min(str.length(), dim);
		for(int i = 0; i < i_max; i++)
			res[dim-1 - b++] = (byte) str.charAt(i);
		
		return res;
	}
	

	/**
	 * Trasforma un numero intero in un vettore di byte
	 * @param n è il numero da convertire in array di uint8
	 * @return byte[]
	 */
	public static byte[] uint8array(int n) {
		int y = n;
		int i = 0;
		byte[] res;
		
		//caso limite
		if(n == 0){
			res = new byte[1];
			res[0] = (byte) 0;
			return res;
		}
		
		//caso generico
		while(y > 0){
			y = y/256;
			i++;
		}
		res = new byte[i];
		
		//little-endian: costruisco res, partendo dal fondo
		for(y=0; y<i; y++){
			res[i-1 - y] = (byte) (n % 256);
			n = n/256;
		}
		return res;
	}
	
	/**
	 * Trasforma un numero intero in un vettore di byte, a dimensione fissa 'dim'  (eventualmente, il risultato viene troncato)
	 * @param n è il numero da convertire in array di uint8
	 * @param dim è la dimensione dell'array di uint8
	 * @return byte[]
	 */
	public static byte[] uint8array(int n, int dim) {
		int y = n;
		int i = 0;	//numero di celle necessarie
		byte[] res = new byte[dim];
		
		//caso limite
		if(n == 0)
			return res;
		
		//caso generico
		while(y > 0){
			y = y/256;
			i++;
		}
		
		//controllo per troncamento
		if(i > dim)
			i = dim;
		
		//costruisco res, partendo dal fondo (posizione i, non dim!)
		for(y=0; y<i; y++){
			res[i-1 - y] = (byte) (n % 256);
			n = n/256;
		}
		return res;
	}
	
	/**
	 * Trasforma una serie di byte in stringa
	 * @param b è il vettore di byte
	 * @return String
	 */
	public static String getString(byte[] content){
		StringBuffer tmp = new StringBuffer("");
		int x;
		
		for(byte b : content) {
			x = BleUtil.uint8(b);
			if(x >= 32)
				tmp.append((char) x);
		}
		
		return tmp.toString();
	}
	
	
	/**
	 * Crea l'indirizzo MAC in esadecimale, usando come separatore ":"
	 * @param mac 
	 * @return String
	 */
	public static String createHexMAC(byte[] mac) {
		StringBuffer res = new StringBuffer("");
		int i;
		
		for(i=0; i<6; i++) {
			res.append(BleUtil.toHex(mac[i]));
			if(i != 5)
				res.append(':');
		}
		return res.toString();
	}

	/**
	 * Mi dice se una stringa corrisponde ad un indirizzo MAC valido (case insensitive)
	 * es  ->  AA:BB:cc:dd:00:11
	 */
	public static boolean isCorrectMAC(String mac) {
		String splittata[] = mac.toUpperCase().split(":"); 
		
		//se non ho sei elementi, l'indirizzo sicuramente non va bene
		if(splittata.length != 6)
			return false;
		
		//ora guardo il singolo elemento
		for(int i = 0; i<6; i++){
			//controllo dimensioni
			if(splittata[i].length() != 2)
				return false;
			
			//controllo caratteri
			if(!splittata[i].matches("[0-9A-F]{2}"))
				return false;
		}
		
		//tutto ok, ha superato i test
		return true;
	}	

}
