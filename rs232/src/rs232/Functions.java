package rs232;

import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

public class Functions {
	
	
	public static Scanner inputScanner = new Scanner(System.in);
	
	// Læser input og returner dette.
	public static int GetUserNumberInput() {
		int Choice = -1;
		
		// Læs valg.
		//try {
	
			Choice = inputScanner.nextInt();
			//inputScanner.close();
			
	//	} catch (Exception e) {
		//	System.out.print("Fejl i læsning af indtastning. Applikationen lukker.");
			//System.exit(0);
		//}
		
		return Choice;
		
	};
	
	// Henter USB serielliste.
	public static void GetUSBList() {
		SerialPort[] ports = SerialPort.getCommPorts();
		
		
		System.out.println("Liste:");
		
		for (int i = 0; i < ports.length; ++i)
			System.out.println("   [" + i + "] " + ports[i].getSystemPortName() + ": " + ports[i].getDescriptivePortName() + " - " + ports[i].getPortDescription());
		
	};
	

}
