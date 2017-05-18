import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessengerImpl extends UnicastRemoteObject implements Messenger{
	private static final long serialVersionUID = 1L;
	final String serverFolder = "serverFolder";

	public MessengerImpl() throws RemoteException {
		super();
	}

	@Override
	public void sendMessage(String msg) throws RemoteException {
		// Server receive a message from the client
		System.out.println(msg);
	}

	@Override
	public String readMessage() throws RemoteException {
		// Just to check if is connected
		return "You are connected to RMI server";
	}

	@Override
	public String listFiles() throws RemoteException {
		// List all the files from the serverFolder
		File folder = new File(serverFolder);
		File[] listOfFiles = folder.listFiles();
		
		String response = "List of files in Server: \n";
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				response += listOfFiles[i].getName() + "\n";
			}
		}
		response += "\n";
		return response;
	}

	@Override
	public byte[] uploadFile(String filename) throws IOException {
		// Send a file to client
		File file = new File(serverFolder + "/" + filename);
		byte[] fileByte = Files.readAllBytes(file.toPath());
		System.out.println("File '" + filename + "' has been transfered");
		return fileByte;
	}

	@Override
	public boolean downloadFile(byte[] uploadFile, String newFileName) throws IOException {
		// Receive a file from client
		FileOutputStream fos = new FileOutputStream(serverFolder + "/" + newFileName);
		fos.write(uploadFile);
		fos.close();
		System.out.println("File '" + newFileName + "' has been received and saved");
		return true;
	}
	
	

}
