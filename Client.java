import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
	


public class Client {
	//Username in a String
	//Scanner to receive input
	static String userName;
	static String hostName = "localhost";
	static int port = 8000;
	public static ObjectInputStream objectInput;
	public static ObjectOutputStream objectOutput;
	public static Socket socket;
	static Scanner scan = new Scanner(System.in);
	static boolean chatroom;
	static boolean loggedIn;
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Connect to server
		try {
		socket = new Socket(hostName, port);
		objectInput = new ObjectInputStream(socket.getInputStream());
		objectOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to:" + hostName);
        }

		LobbyUI lobby = new LobbyUI();
	
	}//end of main
	

	
	//Communicate with server send / receive messages
	
	public void createRoom(String roomName)
	{
		//Create chatroom method
		
		
	}
	
	public void joinRoom(String roomName)
	{
	//Join chatroom method
	
	}
	
	public void leaveRoom()
	{
	//Leave chatroom method
	
	}
	
	
	
}
