package org.thingml.bglib.gio;

/**
 * La classe modellizza le AD che trasportano informazioni riguardanti il Security Manager Out Of Band: possono anche non contenere informazioni utili.
 * Gli ultimi 4 bit del contenuto sono riservati.
 * 
 * @author Giorgio Avalle
 */
public class ADStructSecurityManagerOOBFlags extends ADStruct {

	private boolean dataPresent;
	private boolean LESupported;
	private boolean simultaneous;
	private boolean randomAddr;
	
	public ADStructSecurityManagerOOBFlags(byte t, byte[] content, String mac) {
		super(t, content, mac);
		
		int x = BleUtil.uint8(content[0]);
		x /= 16;	//scarto i bit 4-7
		
		//bit 3
		randomAddr = (x % 2 == 1);
		x /= 2;
		
		//bit 2
		simultaneous = (x % 2 == 1);
		x /= 2;
		
		//bit 1
		LESupported = (x % 2 == 1);
		x /= 2;
		
		//bit 0
		dataPresent = (x % 2 == 1);
	}


	@Override
	public void print() {
		if(isDataPresent()){
			System.out.println("LE: " + LESupported());
			System.out.println("Simultaneous LE and BR/EDR to Same Device Capable (Host): " + simultaneous());
			System.out.println("Indirizzo: " + randomAddr());
		}
		else
			System.out.println("Dati OOB non presenti");
	}


	/** Stabilisce se LE è supportato */
	private String LESupported() {
		if(isLESupported())
			return new String("supportato");
		else
			return new String("non supportato");
	}

	/** Stabilisce se simultaneous LE e BR/EDR sono disponibili */
	private String simultaneous() {
		if(isSimultaneous())
			return new String("disponibile");
		else
			return new String("non disponibile");
	}

	/** Stabilisce il tipo di indirizzo */
	private String randomAddr() {
		if(isRandomAddr())
			return new String("random");
		else
			return new String("pubblico");
	}


	// GETTERs
	public boolean isDataPresent() { return dataPresent; }
	public boolean isLESupported() { return LESupported; }
	public boolean isSimultaneous() { return simultaneous; }
	public boolean isRandomAddr() { return randomAddr; }
	
}
