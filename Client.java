import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
	static boolean chatroom = false;
	static boolean loggedIn = false;
	static boolean lobbyOpen = false;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(!loggedIn)
		{
			LoginUI login = new LoginUI();
		}
		else if(lobbyOpen)
		{
			LobbyUI Lobby = new LobbyUI();
		}
		else
		{
			ChatUI chatroom = new ChatUI(LobbyUI.newChatroomName);
		}
		
    	new Thread(() -> { //thread to recieve information from server

	        while (true){
	        	           

                //Get the input from the client
                ArrayList<String> Input = null;

                try {
                    Input = (ArrayList<String>) Client.objectInput.readObject();
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                    continue;
                }

                //If null skip checking for stuff
                if (Input == null)
                    continue;
                if(chatroom)
                {
                if (Input.get(0).equals("NEW MESSAGE")) {

                	
	 					try {
							ChatUI.getMessages(Input);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} //runs the method for printing the new message
	 					
	 					//Input = null; //empty input after message is recieved
	                
                	} 
                }
                else if(lobbyOpen)
                {
                		LobbyUI.PeopleOnline();
                		LobbyUI.chatOnline();
                		LobbyUI.frame.revalidate();
        	        	LobbyUI.frame.repaint();
                }
	        	//getOnlineUsers(); //add to UI of chatroom
                	
                	try {
    					Thread.sleep(50);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
}

		}).start();
		// login vindue skal lukkes når lobby åbnes
		// lobby vindue skal lukkes når chatroom åbnes
		// når chatroom lukkes åbnes skal lobby åbnes
		
	}//end of main
	

	
	//Communicate with server send / receive messages
	

	

	
	
}
