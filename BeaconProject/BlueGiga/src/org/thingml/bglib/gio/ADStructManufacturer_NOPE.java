package org.thingml.bglib.gio;

/**
 * AD Produttore generica, da convertire poi in qualcosa di più specifico nel momento dell'inizializzazione
 * @author Giorgio Avalle
 */
public class ADStructManufacturer_NOPE extends ADStructManufacturer {
	
	//questi campi servono per poterla convertire in altre forme di AD
	byte type;
	byte[] aD;
	BleDisp disp;
	
	public ADStructManufacturer_NOPE(byte type, byte[] aD, BleDisp disp, String mac) {
		super(type, aD, mac);
		this.type = type;
		this.aD = aD;
		this.disp = disp;
	}


	@Override
	public void printOver() {
		//Occorre convertire la struct in qualcosa di più specifico
		ADStructManufacturer struct = null;  //struct convertita
		
		switch(disp.getType()) {
		case iBLio_IOTS: 
			struct = new ADStructManufacturer_iBLio_IOTS(type, aD, mac);
			break;
		case iBLio_N05:
			struct = new ADStructManufacturer_iBLio_N05(type, aD, mac);
			break;
		case ES: 
			struct = new ADStructManufacturer_ES(type, aD, mac);
			break;
		case iBeacon: 
			//non necessita di conversione: evito il problema della conversione perchè l'iBeacon manufacturer, in quanto pseudo-standard, viene riconosciuto sempre...
//			struct = new ADStructManufacturer_iBeacon(type, aD);
			break;
			
		default:
			System.out.println("Non riconosco il tipo di questa AD Struct Produttore generica! " + disp.getType());
			return;
		}
		
		//stampo il contenuto della struct convertita
		struct.printOver();
	}

}
