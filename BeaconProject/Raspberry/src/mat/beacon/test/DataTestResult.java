package mat.beacon.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import mat.beacon.classes.*;

public class DataTestResult {
	
	HashMap<String,BeaconTesting> tests;
	HashMap<String,ArrayList<TestResult>> testResults;
	
	/**
	 * Crea l'interfaccia per gestire i test relativi ai pacchetti dei beacon. <br/>
	 * 
	 * @param -beacons(Collection): La lista dei beacon presenti nel database. <br/>
	 * @param -stopTest(int): Il numero di pacchetti da ricevere prima di essere testati.
	 */
	public DataTestResult(Collection<Beacon> beacons,int stopTest){
	
	tests = new HashMap<>();
	testResults = new HashMap<>();
	
		for (Beacon beacon : beacons){
			BeaconTesting bt = new BeaconTesting(stopTest);
			tests.put(beacon.getMAC(),bt);
		}
	}
	
	/**
	 * Testa la RSSI di ogni beacon in base ad una certa distanza. In particolare stampa per ogni beacon: <br/>
	 * -La media della RSSI di N pacchetti ricevuti, laddove N è definito in fase di creazione di interfaceTest come secondo parametro.<br/> 
	 * -I secondi impiegati per elaborare testEseguiti*N*mutiple pacchetti <br/>
	 * -Lo skew ( differenza tra RSSI massimo e RSSI minima ) <br/>
	 * -La distanza <br/>
	 * -Il numero totale di pacchetti analizzati
	 * 
	 * @author Matteo Tarantino
	 * @param pack: Pacchetto da testare
	 * @param multiple: stampa (M%multiple==0) testResult laddove M è il numero di testResult elaborati
	 */
	public void test(Packet pack,int multiple){
		
		TestResult testResult;
		BeaconTesting beaconTesting = tests.get(pack.getBeacon().getMAC());
		
		if(beaconTesting.test(pack)==true){
			testResult = new TestResult(pack.getBeacon(),beaconTesting);
			beaconTesting.resetting();
			addTestResult(testResult);
			checkPrint(pack.getBeacon().getMAC(),multiple);
		}
	}

	private void checkPrint(String mac,int multiple) {
		// TODO Auto-generated method stub
		ArrayList<TestResult> testResultList = this.testResults.get(mac);
		TestResult testresult = testResultList.get(0);
		if (testResultList.size()%multiple==0){
			System.out.println("\nTEST RESULT OF BEACON ----> MAC:" + testresult.getBeacon().getMAC() + "\tCostruttore:" + testresult.getBeacon().getCostruttore() 
					+ "\tCaratteristica:" + testresult.getBeacon().getFeature() + "\n");
			
			double secondi=0.0;
			for (TestResult testResult : testResultList){
				System.out.println(testResult.getInfoTestResult());
				secondi+= testResult.getSeconds();
			}
			
			System.out.println("\n\tPacchetti totali analizzati:" + tests.get(testresult.getBeacon().getMAC()).getPacketAnalyzed() 
					+ "\tSecondi impiegati:" + secondi + "\tDistanza:" + testresult.getDistance());
		}
	}

	private void addTestResult(TestResult testResult) {
		// TODO Auto-generated method stub
		String MAC = testResult.getBeacon().getMAC();
		
		if (!testResults.containsKey(MAC)){
			testResults.put(MAC, new ArrayList<TestResult>());
			testResults.get(MAC).add(testResult);
			return;
		}
		
		testResults.get(MAC).add(testResult);
		return;					
	}

}
