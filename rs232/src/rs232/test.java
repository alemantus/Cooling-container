package rs232;
import java.io.InputStream;
import java.util.Scanner;
import java.util.*; 
import java.lang.*; 

import com.fazecast.jSerialComm.*;
import java.io.IOException;

import java.util.concurrent.ThreadLocalRandom;
public class test {
	public static Scanner inputScanner = new Scanner(System.in);
	// Læser input og returner dette.
	private static int GetInput() {
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
		
	}
	
	public static void main(String[] args) throws IOException, InterruptedException  {
		int Choice = -1;
		int USBChoice = -1;
		
		// Vælg 1. serielport.
		SerialPort ReadUSBCOM = null;
		
		// Vælg den 2. serielport.
		SerialPort WriteUSBCOM= null;
			
		//socat -d -d pty,raw,echo=0 pty,raw,echo=0
		
		
		System.out.print("Tryk 1 for at hente USB-listen. Tryk 2 for brug af virtual USB. ");

		// Læs input.
		Choice = GetInput();
	
		// Hvis valg 1.
		if (Choice == 1) {
			
			SerialPort[] ports = SerialPort.getCommPorts();
			
		
			System.out.println("Liste:");
			
			for (int i = 0; i < ports.length; ++i)
				System.out.println("   [" + i + "] " + ports[i].getSystemPortName() + ": " + ports[i].getDescriptivePortName() + " - " + ports[i].getPortDescription());
			
			System.out.print("Vælg skrive-USB: ");
			
			// Læs input.
			USBChoice = GetInput();
			WriteUSBCOM = ports[USBChoice];	
				
			System.out.println("Liste:");
			
			for (int i = 0; i < ports.length; ++i)
				System.out.println("   [" + i + "] " + ports[i].getSystemPortName() + ": " + ports[i].getDescriptivePortName() + " - " + ports[i].getPortDescription());
			
			System.out.print("Vælg læse-USB: ");
			
			// Læs input.
			USBChoice = GetInput();
		//	ReadUSBCOM = SerialPort.getCommPort("/dev/ttys002");
			ReadUSBCOM = ports[USBChoice];	
	
			
		}
		else if (Choice == 2) {
			
			// Vælg 1. serielport.
			ReadUSBCOM = SerialPort.getCommPort("/dev/ttys002");
			
			// Vælg den 2. serielport.
			WriteUSBCOM=SerialPort.getCommPort("/dev/ttys001");
			
		}
		//System.out.println("d");


		// Åben 2. serielport.
	    if (WriteUSBCOM.openPort()) {
	        System.out.println("Skrive-serielport er åben.");
	      } 
	    else {
	        System.out.println("Fejl under åbning af Skrive-serielport");
	      }   
	    
		// Åben 1. serielport.
	    if (ReadUSBCOM.openPort()) {
	        System.out.println("Læse-serielport er åben.");
	      } 
	    else {
	        System.out.println("Fejl under åbning af læse-serielport");
	     }   
		
	    ReadUSBCOM.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 1000, 0);
	    WriteUSBCOM.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 1000, 0);
	    ReadUSBCOM.setBaudRate(9600);
	    WriteUSBCOM.setBaudRate(9600);
	    

	    
		// Tilføj et event for 1. serilport når der er data på linjen.
		ReadUSBCOM.addDataListener(new SerialPortDataListener() {
			   @Override
			   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }
			   @Override
			   public void serialEvent(SerialPortEvent event) {
				  // Modtag data.
			      byte[] newData = event.getReceivedData();
			      
			      // Skriv hvor mange bytes som er modtaget.
			     // System.out.println(newData.length + " byte modtaget.");
			      
			      // Kør byte igennem.
			      for (int i = 0; i < newData.length; ++i) {
			         System.out.print("Modtaget tal: " + newData[i]);
			      }
			      
			      System.out.println("\n");
			   }
			});
		
		
	    // Hvis 2. serielport er åbent.
	    if (WriteUSBCOM.isOpen() && ReadUSBCOM.isOpen()) {
	    	
	    	
			WriteUSBCOM.setComPortParameters(9600, 8, 1, 0); 
			WriteUSBCOM.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); 
			
			ReadUSBCOM.setComPortParameters(9600, 8, 1, 0);
			ReadUSBCOM.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); 
			
			
		      // Kør fra 0 til 5.
		      for (Integer i = 0; i < 5; ++i) {     
		    	  Integer randomInt = ThreadLocalRandom.current().nextInt(0, 127);
		  		//System.out.println("Random number generated is : " + randomInt);
		    	// Konverter i-tal til byte, og send det.
		        WriteUSBCOM.getOutputStream().write(randomInt.byteValue());
		      
		        WriteUSBCOM.getOutputStream().flush();
		        
		        System.out.println("Sendt tal: " + randomInt + "");
		        
		        // Vent i 2 sek.
		        Thread.sleep(3000);
		      }
		
	    };
	    
	    // Hvis 2. serielport er lukket.
	      if (WriteUSBCOM.closePort()) {
	        System.out.println("Skrive-serielport er lukket.");
	      } 
	      else {
	        System.out.println("Fejl under lukning af skrive-serielport.");
	      }
		      
	      // Hvis 1. serielport er lukket. 
	      if (ReadUSBCOM.closePort()) {
		        System.out.println("Læse-serielport er lukket.");
		      } 
	      else {
		        System.out.println("Fejl under lukning af læse serielport.");
		      }
		    

	}


}
