	import java.util.Scanner, java.io.DataInputStream, java.io.DataOutputStream, java.net.Socket;
	


public class Client {
	//Username in a String
	//Scanner to receive input
	String userName;
	String hostName = "localhost";
	int port = 8000;
	DataInputStream input;
	DataOutputStream output;
	Socket socket;
	Scanner scan = new Scanner(System.in);
	boolean chatroom, loggedIn;
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Connect to server
		socket = new Socket(hostName, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		
		
		
		
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
				
				
				System.out.println(input.readLine()); //get messages to print in the chatroom
				
				
				
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
