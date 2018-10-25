import javax.swing.*;


import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyUI extends JPanel{
//	private BufferedReader in;
//    private PrintWriter out;
	//Setup of the program window
    public static JFrame frame = new JFrame("Chat Frame"); 
    private static JPanel POnline = new JPanel();
    private static JPanel COnline = new JPanel();
    private JPanel CreateChatPanel = new JPanel();
    public static ArrayList<String> chatRoomList = null;
    
	public LobbyUI() {
		Client.lobby = this;
		//Temporary username
		ArrayList<String> name = new ArrayList<String>();
		//name.add("TEST USER"); //only for testing
		/*try {
			Client.objectOutput.writeObject(name);
			Client.objectOutput.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Make the program close when the window is closed
        frame.setSize(500, 500); //Set the size of the window
		
		setLayout( new BorderLayout() );
		//The frame for the people online
		POnline.setLayout(new GridLayout(0, 1));
        JLabel POTitle = new JLabel("People Online");
        POnline.add(POTitle);
       
        //Get Online people from server
        //PeopleOnline();
        
        System.out.println("Online list created");
        
        //The frame for the chatrooms online
        COnline.setLayout(new GridLayout(0, 2));
        JLabel SOLabel = new JLabel("Chatrooms Online"); //Set the title of the frame
        JLabel Ghost = new JLabel("");
        COnline.add(SOLabel); //Create the frame
        COnline.add(Ghost);
        //Get Online servers from server
        
        System.out.println("chat online before");
        
        //chatOnline();
        
        System.out.println("Chat Online");
        
        createChatroom();
        
        System.out.println("Create chatroom");
            
		//Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.EAST, POnline);
        frame.getContentPane().add(BorderLayout.CENTER, COnline);
        frame.getContentPane().add(BorderLayout.SOUTH,CreateChatPanel);
        frame.setVisible(true);
        System.out.println("View created");
        
        Client.state = Client.State.lobby;
        
		//Run the client requesting on another thread.
		
       
	}
	
    
    public static void PeopleOnline() {
    	//Get array of the usernames from the server
    	
    	ArrayList<String> UserName = getOnlineUsers();
    	POnline.removeAll();
    	System.out.println("------ Users Online --------");
    	for (int j=0; j<UserName.size();j++) {
    		JLabel Person = new JLabel(UserName.get(j)); //Create a button for each person online
    		System.out.println("Username: " + UserName.get(j));
    		POnline.add(Person); //Add the Label for the people online frame
    	}             
    	
    }
    
    public void createChatroom() {
    	JTextField CreateChatroomTextField = new JTextField(40);
    	
    	JButton CreateChatroomButton = new JButton("Create Chatroom");//Create a button for each chatroom online
    	CreateChatPanel.add(CreateChatroomTextField);
    	CreateChatPanel.add(CreateChatroomButton);
    	CreateChatroomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (CreateChatroomMethod(CreateChatroomTextField.getText())) {
						
					ChatUI chatroom = new ChatUI(CreateChatroomTextField.getText());
					frame.setVisible(false);
    				
				}
			}          
	    });
    }
    public boolean CreateChatroomMethod(String TxtFieldText){
    	try {
    		if(TxtFieldText == "" && TxtFieldText != null) {
    			return false;
    		} else {
    			
    			for(String room : ChatroomNames ) {
    				
    				if (room.equals(TxtFieldText)) {
    					return false;
    				} else {
    		    		ArrayList<String> command = new ArrayList<String>();
    		    		command.add("CREATE CHATROOM");
    		    		command.add(TxtFieldText);
    		    		Client.objectOutput.writeObject(command);
    					Client.objectOutput.flush();
    					return true;
    				}
    			}
    		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return false;
    }
    
    public static ArrayList<String> getOnlineUsers() {
    	ArrayList<String> RequestOnlineUsers = Client.ReadServer();
		RequestOnlineUsers.remove(0);
		return RequestOnlineUsers;
    }
    
    
    
    public static void joinChatroom(String ChatroomName) {
    	try {
    		ArrayList<String> command = new ArrayList<String>();
    		command.add("JOIN CHATROOM");
    		command.add(ChatroomName);
    		Client.objectOutput.writeObject(command);
			Client.objectOutput.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    static int i = 0;
    static ArrayList<String> ChatroomNames = null;
    public static void chatOnline(ArrayList<String> In) {
    	//Get array of the chat rooms from the server
    	In.remove(0);
    	ChatroomNames = In;
    	COnline.removeAll();
    	for (i=0; i<ChatroomNames.size();i++) {
    		JLabel ChatName = new JLabel(ChatroomNames.get(i));
    		JButton ChatroomJoinButton = new JButton("Join");//Create a button for each chatroom online
    		COnline.add(ChatName);
    		COnline.add(ChatroomJoinButton); //Add the button to the chatrooms online frame
    		//Create a listener for the button
    		ChatroomJoinButton.addActionListener(new ActionListener() {
    			int roomID = i;
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				joinChatroom(ChatroomNames.get(roomID));
    				ChatUI chatroom = new ChatUI(ChatroomNames.get(roomID));
    				frame.setVisible(false);
    				
    			}          
    	    });
    	}
    }
}