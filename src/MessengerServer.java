import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MessengerServer {

	public static void main(String[] args) {
		final String address = "rmi://localhost/MessengerService";
		
		try {
			// RMI setup
			System.setProperty("java.rmi.server.hostname","localhost");
			LocateRegistry.createRegistry(1099);
			Messenger m = new MessengerImpl();
			Naming.rebind(address, m);
		} catch(Exception e) {
			System.out.println("Error: " + e);
		} finally {
			System.out.println("Server started, waiting requests...");
		}

	}

}
