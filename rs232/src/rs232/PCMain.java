package rs232;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;


public class PCMain {

	public static void main(String[] args) throws IOException, InterruptedException  {
		int SerialChoose = -1;
		
		// Serielport
		SerialPort Serial = null;
		
		// Serielporte.
		SerialPort[] ports = SerialPort.getCommPorts();
		
		// Vælg USB fra liste.
		SerialChoose = Functions.ChooseUSB();
		
		// Hvis der er valgt en serielport fra listen.
		if (SerialChoose != -1) {
			Serial = ports[SerialChoose];
		}
		
		// Åben serielport.
	    if (Serial.openPort()) {
	        System.out.println("Serielport er åben.");
	      } 
	    else {
	        System.out.println("Fejl under åbning af serielport.");
	        return;
	      }   
	    
	    
	      if (Serial.closePort()) {
		        System.out.println("Skrive-serielport er lukket.");
		  } 
		  else {
		        System.out.println("Fejl under lukning af skrive-serielport.");
		  }
	    
		
	}

}
