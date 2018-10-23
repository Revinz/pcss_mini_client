import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyUI extends JPanel{
//	private BufferedReader in;
//    private PrintWriter out;
	//Setup of the program window
    private JFrame frame = new JFrame("Chat Frame"); 
    private JPanel POnline = new JPanel();
    private JPanel SOnline = new JPanel();
    private JPanel CreateServerPanel = new JPanel();
    
	public LobbyUI() {
		setLayout( new BorderLayout() );
		//The frame for the people online
		POnline.setLayout(new GridLayout(0, 1));
        JLabel POTitle = new JLabel("People Online");
        POnline.add(POTitle);
        //Get Online people from server
        PeopleOnline();
        //The frame for the chatrooms online
        SOnline.setLayout(new GridLayout(0, 2));
        JLabel SOLabel = new JLabel("Chatrooms Online"); //Set the title of the frame
        JLabel Ghost = new JLabel("");
        SOnline.add(SOLabel); //Create the frame
        SOnline.add(Ghost);
        //Get Online servers from server
        chatOnline();
        createServerUI();
        
//        
		//Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.EAST, POnline);
        frame.getContentPane().add(BorderLayout.CENTER, SOnline);
        frame.getContentPane().add(BorderLayout.SOUTH,CreateServerPanel);
	}
	
    public static void main(String args[]) {
    	LobbyUI client = new LobbyUI();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Make the program close when the window is closed
        client.frame.setSize(500, 500); //Set the size of the window
        client.frame.setVisible(true); //Make the window visible

    }
    
    public void PeopleOnline() {
    	//Get array of the usernames from the server
    	List UserName = getOnlineUsers();
    	for (int i=0; i<UserName.size();i++) {
    		JLabel Person = new JLabel((String) UserName.get(i)); //Create a button for each person online
    		POnline.add(Person); //Add the Label for the people online frame
    	}              
    }
    public void createServerUI() {
    	JTextField CreateServerTextField = new JTextField();
    	JButton CreateChatroom = new JButton("Create");//Create a button for each chatroom online
    	CreateServerPanel.add(CreateServerTextField);
    	CreateServerPanel.add(CreateChatroom);
    	CreateChatroom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] RequestCreate;
			}          
	    });
    }
    public List getOnlineUsers() {
    	try {
    		List<String> command = new ArrayList<String>();
    		command.add("GET ONLINE USERS");
    		Client.objectOutput.writeObject(command);
			List<String> RequestOnlineUsers = (List<String>) Client.objectInput.readObject();
			return RequestOnlineUsers;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    public List getChatrooms() {
    	try {
    		List<String> command = new ArrayList<String>();
    		command.add("GET CHATROOMS");
    		Client.objectOutput.writeObject(command);
			List<String> RequestChat = (List<String>) Client.objectInput.readObject();
			return RequestChat;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public List joinChatroom(String ChatroomName) {
    	try {
    		List<String> command = new ArrayList<String>();
    		command.add("JOIN CHATROOM");
    		command.add(ChatroomName);
    		Client.objectOutput.writeObject(command);
			List<String> RequestJoin = (List<String>) Client.objectInput.readObject();
			return RequestJoin;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
   
    static int i = 0;
    public void chatOnline() {
    	//Get array of the chat rooms from the server
    	List ChatroomNames = getChatrooms();
    	for (i=0; i<ChatroomNames.size();i++) {
    		JLabel ServerName = new JLabel((String) ChatroomNames.get(i));
    		JButton Server = new JButton("Join");//Create a button for each chatroom online
    		SOnline.add(ServerName);
    		SOnline.add(Server); //Add the button to the chatrooms online frame
    		//Create a listener for the button
    		Server.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				List ChatJoin = joinChatroom((String)ChatroomNames.get(i));
    				
    				
    			}          
    	    });
    	}
    }
}