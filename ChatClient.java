import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatClient
{
	Socket clientSok = new Socket("localhost", 1200);
	ObjectInputStream input;
	ObjectOutputStream output;
	
	public ChatClient() throws IOException {}
	
	public String readInput() throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		return input;
	}
	
	public void go() throws IOException
	{
		//input = new ObjectInputStream(clientSok.getInputStream());
		output = new ObjectOutputStream(clientSok.getOutputStream());
		String message;
		
		System.out.println("Enter a username:");
		message = readInput();
		output.writeObject(message);
		
		while(true)
		{
			System.out.println("Send a message:");
			message = readInput();
			output.writeObject("" + message);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		ChatClient client = new ChatClient();
		client.go();
	}
}