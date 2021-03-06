import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.Naming;

public class MessengerClient {
	final static String address = "rmi://localhost/MessengerService";
	final static String clientFolder = "clientFolder";
	
	public static void main(String[] args) {
		try {
			Messenger m = (Messenger) Naming.lookup(address);
			
			// Checking if client is connected to server
			System.out.println(m.readMessage());
			
			// Sending a message to server
			m.sendMessage("Client has been connected");

			System.out.println(m.listFiles());

			// Receive a file from the server
			String filename = "TestFile01.txt";
			downloadFile(m.uploadFile(filename), filename);

			// Send a file to the server
			filename = "cat.jpg";
			m.downloadFile(uploadFile(filename), filename);
			
		} catch(Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public static byte[] uploadFile(String filename) throws IOException {
		// Send a file to server
		File file = new File(clientFolder + "/" + filename);
		byte[] fileByte = Files.readAllBytes(file.toPath());
		System.out.println("File '" + filename + "' has been transfered");
		return fileByte;
	}

	public static void downloadFile(byte[] bs, String newFileName) throws IOException {
		// Receive a file from server
		FileOutputStream fos = new FileOutputStream(clientFolder + "/" + newFileName);
		fos.write(bs);
		fos.close();
		System.out.println("File '" + newFileName + "' has been received and saved");
	}

}