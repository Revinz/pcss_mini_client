import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
		
		catch (Exception e)  {
			
			
		}
		
		if(!loggedIn)
		{
		
		//Run Login UI
			
			loggedIn = true;//once a username is generated, and accepted by the server
		}
		
		//while loop starts here
		while(loggedIn);
		{
		
			
			if(!chatroom)
			{
		//Run Lobby UI
		
		//Enter a chatroom
			
				chatroom = true; //once server allows access into a chatroom, either by creating a new one or joining an existing one
			}
			else if(chatroom)
			{
			
		
		//Type msges - send them to server - retrieve msges from server
				
				scan.nextLine(); //send this to server
				
		//Leave chatroom
				
				chatroom = false; //upon leaving chatroom
			}
			
			
			
		
		}//end of while loop
		
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
