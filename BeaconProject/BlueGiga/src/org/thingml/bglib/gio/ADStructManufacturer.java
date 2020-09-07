package org.thingml.bglib.gio;

/**
 * Classe astratta per le AD Struct Produttore dei vari dispositivi IoT
 * @author Giorgio Avalle
 */
public abstract class ADStructManufacturer extends ADStruct {

	//campi specifici
	protected String manufacturer;
	
	
	
	public ADStructManufacturer(byte type, byte[] content, String mac){
		super(type, content, mac);
		
		//manufacturer ID (1-2 B)
		this.manufacturer = BleCodes.manufacturer(BleUtil.toHex(content[1], content[0]));
	}
	
	
	@Override
	public void print() {
		System.out.println("Costruttore: " + this.manufacturer);
		this.printOver();	//info personalizzate
	}
	
	/** Stampa le informazioni personalizzate della struct produttore */
	public abstract void printOver();
	
	
	
	// GETTERs
	public String getManufacturer() { return this.manufacturer; }
	
}
