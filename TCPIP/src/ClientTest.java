import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {
	
    private Socket socket;
    private Scanner scanner;
    private ClientTest(InetAddress serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }
    private void start() throws IOException {
        String input;
        while (true) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();
        }
    }
    
    public static void main(String[] args) throws Exception {
    	ClientTest client = new ClientTest(
                InetAddress.getByName("192.168.1.6"), 
                Integer.parseInt("52994"));
        
        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();                
    }

}
