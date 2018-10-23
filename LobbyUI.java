import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyUI extends JPanel{
//	private BufferedReader in;
//    private PrintWriter out;
	//Setup of the program window
    private JFrame frame = new JFrame("Chat Frame"); 
    private JPanel POnline = new JPanel();
    private JPanel COnline = new JPanel();
    private JPanel CreateChatPanel = new JPanel();
    
	public LobbyUI() {
		
		//Temporary username
		ArrayList<String> name = new ArrayList<String>();
		name.add("TEST USER");
		try {
			Client.objectOutput.writeObject(name);
			Client.objectOutput.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Make the program close when the window is closed
        frame.setSize(500, 500); //Set the size of the window
		
		setLayout( new BorderLayout() );
		//The frame for the people online
		POnline.setLayout(new GridLayout(0, 1));
        JLabel POTitle = new JLabel("People Online");
        POnline.add(POTitle);
       
        //Get Online people from server
        PeopleOnline();
        
        System.out.println("Online list created");
        
        //The frame for the chatrooms online
        COnline.setLayout(new GridLayout(0, 2));
        JLabel SOLabel = new JLabel("Chatrooms Online"); //Set the title of the frame
        JLabel Ghost = new JLabel("");
        COnline.add(SOLabel); //Create the frame
        COnline.add(Ghost);
        //Get Online servers from server
        
        System.out.println("chat online before");
        
        chatOnline();
        
        System.out.println("Chat Online");
        
        createChatroom();
        
        System.out.println("Create chatroom");
            
		//Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.EAST, POnline);
        frame.getContentPane().add(BorderLayout.CENTER, COnline);
        frame.getContentPane().add(BorderLayout.SOUTH,CreateChatPanel);
        frame.setVisible(true);
        System.out.println("View created");
	}
	
    
    public void PeopleOnline() {
    	//Get array of the usernames from the server
    	ArrayList<String> UserName = getOnlineUsers();
    	for (int i=0; i<UserName.size();i++) {
    		JLabel Person = new JLabel(UserName.get(i)); //Create a button for each person online
    		System.out.println("Username: " + UserName.get(i));
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
    
    public ArrayList<String> getOnlineUsers() {
    	try {
    		ArrayList<String> command = new ArrayList<String>();
    		command.add("GET ONLINE USERS");
    		Client.objectOutput.writeObject(command);
			Client.objectOutput.flush();
			ArrayList<String> RequestOnlineUsers = (ArrayList<String>) Client.objectInput.readObject();
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
    
    public ArrayList<String> getChatrooms() {
    	try {
    		ArrayList<String> command = new ArrayList<String>();
    		command.add("GET CHATROOMS");
    		Client.objectOutput.writeObject(command);
			Client.objectOutput.flush();
    		ArrayList<String> RequestChat = (ArrayList<String>) Client.objectInput.readObject();
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
    
    public void joinChatroom(String ChatroomName) {
    	try {
    		ArrayList<String> command = new ArrayList<String>();
    		command.add("JOIN CHATROOM");
    		command.add(ChatroomName);
    		Client.objectOutput.writeObject(command);
			Client.objectOutput.flush();
			ArrayList<String> RequestJoin = (ArrayList<String>) Client.objectInput.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    static int i = 0;
    ArrayList<String> ChatroomNames = null;
    public void chatOnline() {
    	//Get array of the chat rooms from the server
    	ChatroomNames = getChatrooms();
    	for (i=0; i<ChatroomNames.size();i++) {
    		JLabel ChatName = new JLabel(ChatroomNames.get(i));
    		JButton ChatroomJoinButton = new JButton("Join");//Create a button for each chatroom online
    		COnline.add(ChatName);
    		COnline.add(ChatroomJoinButton); //Add the button to the chatrooms online frame
    		//Create a listener for the button
    		ChatroomJoinButton.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				joinChatroom(ChatroomNames.get(i));
    				ChatUI chatroom = new ChatUI(ChatroomNames.get(i));
    			}          
    	    });
    	}
    }
}