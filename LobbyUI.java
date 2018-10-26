import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyUI extends JPanel{
    public static JFrame frame = new JFrame("Lobby"); //Create the lobby window with the title Lobby
    private static JPanel POnline = new JPanel(); //Create a panel for people online
    private static JPanel COnline = new JPanel(); //Create a panel for chatrooms online
    private JPanel CreateChatPanel = new JPanel(); //Create a panel for chatroom creation
    
	public LobbyUI() {
		Client.lobby = this;
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Make the program close when the window is closed
        frame.setSize(500, 500); //Set the size of the window
		
		setLayout( new BorderLayout() ); //Set the layout style
		POnline.setLayout(new GridLayout(0, 1)); //Set the POnline panel style
        COnline.setLayout(new GridLayout(0, 2)); //Set the COnline panel style
        CreateChatPanel.setLayout(new GridLayout(1,2)); //Set the CreateChatPanel panel style
        createChatroom(); //Run the createChatroom method
            
        frame.getContentPane().add(BorderLayout.EAST, POnline); //Set the POnline panel to the east side of the window
        frame.getContentPane().add(BorderLayout.WEST, COnline); //Set the COnline panel to the west side of the window
        frame.getContentPane().add(BorderLayout.SOUTH,CreateChatPanel); //Set the CreateChatPanel panel to the south side of the window
        frame.setVisible(true); //Set the visibility of the window
        
        Client.state = Client.State.lobby; //Set the state of the client to lobby
	}
    
    public static void PeopleOnline(ArrayList<String> In) {
    	In.remove(0); //Remove the first item in the array
    	ArrayList<String> UserName = In;
    	POnline.removeAll(); //Remove everything from POnline panel
    	JLabel POTitle = new JLabel("People Online"); //Create a label
        POnline.add(POTitle); //Add the label to the panel
    	for (int j=0; j<UserName.size();j++) { //for-loop for creating the labels for people online
    		JLabel Person = new JLabel(UserName.get(j)); //Create a label for each person online
    		POnline.add(Person); //Add the Label for the people online frame
    	}   
    }
    
    public void createChatroom() {
    	JTextField CreateChatroomTextField = new JTextField(40); //Create the textfield for the chatroom names
    	JButton CreateChatroomButton = new JButton("Create Chatroom"); //Create a button
    	CreateChatPanel.add(CreateChatroomTextField); //Add the textfield to the panel
    	CreateChatPanel.add(CreateChatroomButton); //Add the button to the panel
    	CreateChatroomButton.addActionListener(new ActionListener() { //Add a listener to the button
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (CreateChatroomMethod(CreateChatroomTextField.getText())) { //Check if the CreateChatroomMethod is true			
					ChatUI chatroom = new ChatUI(CreateChatroomTextField.getText()); //Create a new ChatUI window
					frame.setVisible(false); //Set the visibility of the lobby to false
				}
			}          
	    });
    }
    
    public boolean CreateChatroomMethod(String TxtFieldText){
    	try {
    		if(TxtFieldText == "" && TxtFieldText != null) { //Check if something is written in the textfield
    			return false;
    		} else {
    			if (ChatroomNames.size() != 0) { //Check if something is in the array
    				for(String room : ChatroomNames) {
    					if (room.equals(TxtFieldText)) { //Check if a chatroom with the given name already exists
    					return false;
    					} else {
    		    		ArrayList<String> command = new ArrayList<String>(); //Create an arraylist
    		    		command.add("CREATE CHATROOM"); //Add String to the arraylist
    		    		command.add(TxtFieldText); //Add String to the arraylist
    		    		Client.objectOutput.writeObject(command); //Send the arraylist to the server
    					Client.objectOutput.flush(); //Flush the outputstream
    					return true;
    					}
    				}
    			} else { //If nothing is in the array
    				ArrayList<String> command = new ArrayList<String>(); //Create an arraylist
		    		command.add("CREATE CHATROOM"); //Add String to the arraylist
		    		command.add(TxtFieldText); //Add String to the arraylist
		    		Client.objectOutput.writeObject(command); //Send the arraylist to the server
					Client.objectOutput.flush(); //Flush the outputstream
					return true;
    			}
    		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    
    public static ArrayList<String> getOnlineUsers() {
    	ArrayList<String> RequestOnlineUsers = Client.ReadServer(); //Get arraylist from the server
		RequestOnlineUsers.remove(0); //Remove the first item in the arraylist
		return RequestOnlineUsers;
    }
    
    public static void joinChatroom(String ChatroomName) {
    	try {
    		ArrayList<String> command = new ArrayList<String>(); //Create an arraylist
    		command.add("JOIN CHATROOM"); //Add String to the arraylist
    		command.add(ChatroomName); //Add String to the arraylist
    		Client.objectOutput.writeObject(command); //Send the arraylist to the server
			Client.objectOutput.flush(); //Flush the outputstream
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    static int i = 0;
    static ArrayList<String> ChatroomNames;
    public static void chatOnline(ArrayList<String> In) {
    	In.remove(0); //Remove the first item in the arraylist
    	ChatroomNames = In; //Set ChatroomNames to In
    	COnline.removeAll(); //Remove everything in the panel
    	JLabel SOLabel = new JLabel("Chatrooms Online"); //Create a label for the COnline panel
        JLabel Ghost = new JLabel(""); //Create a label with nothing in it (Ghost panel)
        COnline.add(SOLabel); //Add the label to the panel
        COnline.add(Ghost); //Add the label to the panel
    	for (i=0; i<ChatroomNames.size();i++) { //for-loop for creating the labels and buttons for chatrooms online
    		JLabel ChatName = new JLabel(ChatroomNames.get(i)); //Create a label for the given chatroom name
    		JButton ChatroomJoinButton = new JButton("Join");//Create a button to join the chatroom
    		COnline.add(ChatName); //Add the label to the chatrooms online frame
    		COnline.add(ChatroomJoinButton); //Add the button to the chatrooms online frame
    		ChatroomJoinButton.addActionListener(new ActionListener() { //Create a listener for the button
    			int roomID = i;
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				joinChatroom(ChatroomNames.get(roomID)); //Cast the method joinChatroom for the given chatroom
    				ChatUI chatroom = new ChatUI(ChatroomNames.get(roomID)); //Create a new ChatUI window
					frame.setVisible(false); //Set the visibility of the lobby to false
    			}          
    	    });
    	}
    }
}