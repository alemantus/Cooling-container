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
	
	public static int ChooseUSB() {
		
		// Hent alle den komplete USB-liste.
		Serial.GetUSBList();
		
		// Skriv output.
		System.out.print("Vælg nummer: ");
		
		return Functions.GetUserNumberInput();
		
	}
	

}
