import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Messenger extends Remote {
	public void sendMessage(String msg) throws RemoteException;
	public String readMessage() throws RemoteException;
	public String listFiles() throws RemoteException;
	public byte[] uploadFile(String filename)  throws RemoteException, IOException;
	public boolean downloadFile(byte[] uploadFile, String filename) throws RemoteException, IOException;
}
