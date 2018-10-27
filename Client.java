import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
	


public class Client {
	static String userName; //Clients username
	static String hostName = "localhost"; //Host IP address
	static int port = 8000; //Host port number
	public static ObjectInputStream objectInput; //Stream for receiving information
	public static ObjectOutputStream objectOutput; //Stream for sending information
	public static Socket socket; //Socket
	static ArrayList<String> Input = null; //ArrayList to handle incoming information
	public final static Lock lock = new ReentrantLock(); //To help synchronize the information comming in
	public static LobbyUI lobby = null; //LobbyUI object
	
	public static ArrayList<String> ReadServer() {
		synchronized (lock) {
			try {
				if(objectInput != null)
				{
				ArrayList<String> _input = (ArrayList<String>) Client.objectInput.readObject();
				System.out.println("Command: " + _input);
				return Input = _input;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("Nothing returned");
			return null;
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LoginUI login = new LoginUI(); //Starts Login screen
		
		new Thread(() -> {  //Thread that will handle information from server

	        while (true){
	        	
	        	ArrayList<String> Input = null;
                System.out.println("Chatroom reading from server");
                Input = Client.ReadServer(); //Continuesly waits for new information from server
	        	
                if (Input == null) //When there is no information received
                {
                    continue;
                }
	        	
                else if (Input.get(0).equals("ONLINE USERS")) { //When information about new users in the lobby is received
			        	System.out.println("server sending online user list");
			        	LobbyUI.PeopleOnline(Input);
			        	lobby.frame.revalidate();
			        	lobby.frame.repaint();
	        		}
                else if (Input.get(0).equals("UPDATE CHATROOMS")) { //When information about chatrooms is received
			        	System.out.println("server sending chatroom list");
			        	LobbyUI.chatOnline(Input);
			        	lobby.frame.revalidate();
			        	lobby.frame.repaint();
		        	}
                else if (Input.get(0).equals("CHATROOM USERS")) { //When information about chatroom users is received
		        	System.out.println("server sending chatroom user list");
		        	ChatUI.updateUserlist(Input);
		        	
	        	}
   
                else if (Input.get(0).equals("NEW MESSAGE")) { //When a new message is received in the chatroom
	             	
		 					try {
								ChatUI.getMessages(Input);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} //runs the method for printing the new message		                
	                	}	                            	
	                	try {
	    					Thread.sleep(20);
	    				} catch (InterruptedException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
	        		}    
		}).start(); //starts the thread
	}//end of main
}
