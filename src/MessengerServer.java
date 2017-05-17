import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MessengerServer {

	public static void main(String[] args) {
		
		try {
			System.setProperty("java.rmi.server.hostname","192.168.0.15");
			LocateRegistry.createRegistry(1099);
			
			Messenger m = new MessengerImpl();
			Naming.rebind("rmi://localhost/MessengerService", m);
		} catch(Exception e) {
			System.out.println("Error: " + e);
		} finally {
			System.out.println("Server started, waiting requests...");
		}

	}

}
