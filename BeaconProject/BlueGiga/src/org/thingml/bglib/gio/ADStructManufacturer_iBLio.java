package org.thingml.bglib.gio;

/**
 * AD Struct Produttore per i dispositivi iBLio
 * @author Giorgio Avalle
 */
public abstract class ADStructManufacturer_iBLio extends ADStructManufacturer {

	//campi specifici per i dispositivi iBLio
	protected String nodo;
	
	protected int rete;			//id della rete
	protected int tipoRete;		//id del nodo
	protected int numeroNodo;	//codice id del tipo di nodo, nella rete
	
	
	
	public ADStructManufacturer_iBLio(byte type, byte[] content, String mac) {
		super(type, content, mac);
		
		//informazioni nodo
		setNodo(BleUtil.toHex(content[2]));
		
		//informazioni rete  (unsigned integers)
		this.rete = BleUtil.uint8(content[3]);
		this.tipoRete = BleUtil.uint8(content[4]);
		this.numeroNodo = BleUtil.uint8(content[5]);
	}
	
	@Override
	public void printOver(){
		System.out.println("Messaggio di classe: " + this.nodo);
		System.out.println("ID rete: " + this.rete);
		System.out.println("ID nodo: " + this.tipoRete);
		System.out.println("ID tipo del nodo, nella rete: " + this.numeroNodo);
		
		//info personalizzate del modello
		this.printOver2();
	}
	
	
	/**
	 * Stampa ulteriori informazioni personalizzate della struct produttore, per i dispositivi di iBLio
	 */
	public abstract void printOver2();
	
	
	
	
	
	
	/**
	 * Crea il campo 'nodo'
	 * @param string 
	 */
	private void setNodo(String nodo) {
		switch(nodo) {
			case "5A": this.nodo = new String("Informazione Nodo"); break;
			case "4B": this.nodo = new String("Dati Security"); break;
			case "3C": this.nodo = new String("Dati Debug"); break;
			default: this.nodo = new String("Not recognized (" + nodo + ")");
		}
	}
	
	/**
	 * Ottieni il valore esteso del campo 'nodo'
	 * @return String
	 */
	public String getNodo() {
		return this.nodo;
	}
	
	/**
	 * Ottieni il codice ID della rete
	 * @return String
	 */
	public int getRete() {
		return this.rete;
	}
	
	/** Ottieni il tipo di rete
	 * @return String
	 */
	public int getTipoRete(){
		return this.tipoRete;
	}
	
	/** Ottieni il numero del nodo sulla rete
	 * @return String
	 */
	public int getNumeronodo() {
		return this.numeroNodo;
	}

}
