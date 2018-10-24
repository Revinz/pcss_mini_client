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
	//Username in a String
	//Scanner to receive input
	static String userName;
	static String hostName = "localhost";
	static int port = 8000;
	public static ObjectInputStream objectInput;
	public static ObjectOutputStream objectOutput;
	public static Socket socket;
	static Scanner scan = new Scanner(System.in);
	static ArrayList<String> Input = null;
	public final static Lock lock = new ReentrantLock();
	
	enum State {
		chatroom,
		login,
		lobby
	}
	
	public static State state = null;
	
	public static ArrayList<String> ReadServer() {

		synchronized (lock) {
			try {
				ArrayList<String> _input = (ArrayList<String>) Client.objectInput.readObject();
				System.out.println("Command: " + _input);
				return Input = _input;
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
		LoginUI login = new LoginUI();
		
	}//end of main
	
	public static void InterpretResponse(ArrayList<String> _Input) {
		
		
				
	}

	
	//Communicate with server send / receive messages
	

	

	
	
}
