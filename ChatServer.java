import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;
import java.lang.ClassNotFoundException;

public class ChatServer
{
	ServerSocket serverSok = new ServerSocket(1200);
	
	public ChatServer() throws IOException, InterruptedException, ClassNotFoundException {}
	
	public void go() throws IOException, InterruptedException, ClassNotFoundException
	{
		Socket client = serverSok.accept();
		ObjectInputStream inStream = new ObjectInputStream(client.getInputStream());
		String message = null;
		
		String username = (String) inStream.readObject();
		
		while(true)
		{
			message = (String) inStream.readObject();
			System.out.println("[" + username + "] " + message);
		}
		serverSok.close();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException
	{
		ChatServer server = new ChatServer();
		server.go();
	}
}