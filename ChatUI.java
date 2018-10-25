import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

class ChatUI {

    boolean chatroom = true;
    String roomName;
    String  username;
    String      appName     = "Login you piece of shit";
    ChatUI     mainGUI;
    JFrame      newFrame    = new JFrame(appName);
    JButton     sendMessage;
    JTextField  messageBox;
    static JTextArea   chatBox;
    List<String> onlineUsers = new ArrayList<String>();
    JScrollPane SP_ONLINE = new JScrollPane();
    public static JList JL_ONLINE = new JList();
    
    String [] ChatRoomNames = { };

    public ChatUI(String chatroomName) {
    	
    	roomName = chatroomName;
		// TODO Auto-generated constructor stub
    	chatDisplay();
    	
    	Client.state = Client.State.chatroom;
    	
       
	}
    	// TODO Get chatroom log
    
    public static ArrayList<String> getMessages(ArrayList<String> Incoming) throws ClassNotFoundException, IOException {
	 chatBox.append(Incoming.get(1)+ " says: " + Incoming.get(2) +"\n");
   	return null;
   }
    
    	// TODO Add messages to the chat
    public void sendMessage() //activate on send message
    {
    	try {
    		ArrayList<String> command = new ArrayList<String>();
    		command.add("SEND MESSAGE");
    		command.add(Client.userName);
    		command.add(messageBox.getText());
    		command.add(roomName);
    		Client.objectOutput.writeObject(command);
    		Client.objectOutput.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void leaveRoom() //activate on send message
    {
    	try {
    		ArrayList<String> command = new ArrayList<String>();
    		command.add("LEAVE CHATROOM");
    		command.add(roomName);
    		System.out.println("You're leaving a chatroom with name: " +roomName);
    		Client.objectOutput.writeObject(command);
    		Client.objectOutput.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
   
    
    	// TODO Get chatroom users
    public ArrayList<String> getOnlineUsers() {
    	try {
    		ArrayList<String> command = new ArrayList<String>();
    		command.add("GET CHATROOM USERS");
    		command.add(roomName);
    		Client.objectOutput.writeObject(command);
    		Client.objectOutput.flush();
    		ArrayList<String> onlineUsers = Client.ReadServer();
    		boolean chatroom = false;
            Client.lobby.frame.setVisible(true);
            Client.state = Client.state.lobby;
            newFrame.dispose();
    		//type list of users into window:
    		/*for(int i = 0; i < onlineUsers.size(); i++)
    		  {
    		  		print into window: onlineUsers.get(i);														<<<<<<<HERE IS SOMETHING FOR JONAS>>>>>>>>>>>>>
    		  }
    		  */
    		 
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;
    }
	
	
	// TODO Show chatroom users in list
	
	
    //This is the GUI for the Chat (missing inpud from the other user)
    public void chatDisplay() {
    	JButton back = new JButton("Back");
        back.addActionListener(new backButtonListener()); //This is the back butten from chatroom to lobby

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.getHSBColor(25, 55, 10));
        southPanel.setLayout(new GridBagLayout());

        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(Color.getHSBColor(100, 10, 10));
        playerPanel.setLayout(new GridBagLayout());


        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener()); // This is the message that can be seen in the chat GUI

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
        //playerPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 0, 0, 0);
        right.anchor = GridBagConstraints.NORTH;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;



        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);


        playerPanel.add(back, right);


        //Lest of of users as a string array
        JL_ONLINE.setForeground(new java.awt.Color(0,0,0));
        JL_ONLINE.setListData(ChatRoomNames);

        //The visibal lest of users
        SP_ONLINE.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_ONLINE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        SP_ONLINE.setViewportView(JL_ONLINE);
        newFrame.getContentPane().add(SP_ONLINE);
        SP_ONLINE.setBounds(0, 34, 55, 390);



        mainPanel.add(BorderLayout.SOUTH, southPanel);
        mainPanel.add(BorderLayout.WEST, playerPanel);
        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(500, 500);
        newFrame.setVisible(true);
    }

    // This is the message that can be seen in the chat GUI
    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
                System.out.println("Nothing has been written");
            } else if (messageBox.getText().equals(".clear")) {     // this is popularly not necessary, but if you type ".clear" it will clear the message bord
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {

            	sendMessage();
            	messageBox.setText("");
            }
            messageBox.requestFocusInWindow();
        }
    }
    //This is the back butten from chatroom to lobby
    class backButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	
        	
        	leaveRoom();
            
            
            

        }
    }


}