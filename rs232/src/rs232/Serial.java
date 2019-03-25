package rs232;

import com.fazecast.jSerialComm.SerialPort;

public class Serial {
	
	// Henter USB serielliste.
	public static void GetUSBList() {
		SerialPort[] ports = SerialPort.getCommPorts();
		
		
		System.out.println("Liste:");
		
		for (int i = 0; i < ports.length; ++i)
			System.out.println("   [" + i + "] " + ports[i].getSystemPortName() + ": " + ports[i].getDescriptivePortName() + " - " + ports[i].getPortDescription());
		
	};

}
