package org.thingml.bglib.gio;

/**
 * Elenco dei tipi di dispositivo IoT correttamente gestiti dall'applicativo.
 * Per tipi non riconosciuti, il valore da attribuire al tipo di dispositivo è "UNKNOWN".
 * 
 * @author Giorgio Avalle
 */
public enum BleDispType {
	NOPE,	//ancora da definire
	
	iBLio_IOTS,		//iBLio IoT semplice
	iBLio_N05,		//iBLio temperatura
	iBeacon,		//iBeacon
	ES,				//Eddystone
	
	UNKNOWN			//Dispositivo non riconosciuto
}



//L'assegnazione viene fatta in: DispositivoBLE