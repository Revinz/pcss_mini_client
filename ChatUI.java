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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
    JTextArea   chatBox;
    List<String> onlineUsers = new ArrayList<String>();
    List<String> recievedMessages = new ArrayList<String>();


    public ChatUI(String chatroomName) {
    	roomName = chatroomName;
		// TODO Auto-generated constructor stub
    	chatDisplay();
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

                if (Input.get(0).equals("NEW MESSAGE")) {

                	
	 					try {
							getMessages(Input);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} //runs the method for printing the new message
	 					
	 					Input = null; //empty input after message is recieved
	                
                	} 
                
                	getOnlineUsers(); //add to UI of chatroom
                	
                	try {
    					Thread.sleep(50);
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
}

		}).start();
       
	}
    	// TODO Get chatroom log
    
    public ArrayList<String> getMessages(ArrayList<String> Incoming) throws ClassNotFoundException, IOException {
   	for( int i = 0; i < Incoming.size(); i++)
	 {
			 recievedMessages.add(Incoming.get(i));
	 }
	 
	 chatBox.append(recievedMessages.get(1)+ " says: " + recievedMessages.get(2));
   	return null;
   }
    
    	// TODO Add messages to the chat
    public void sendMessage() //activate on send message
    {
    	try {
    		ArrayList<String> command = new ArrayList<String>();
    		command.add("SEND MESSAGE");
    		command.add(LoginUI.username);
    		command.add(messageBox.getText());
    		command.add(roomName);
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
    		Client.objectOutput.writeObject(command);
    		Client.objectOutput.flush();
    		ArrayList<String> onlineUsers = (ArrayList<String>) Client.objectInput.readObject();
    		return onlineUsers;
    	} catch (ClassNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;
    }
	
	
	// TODO Show chatroom users in list
	
	public void printOnlineUsers()
	{
	 for(int i = 0; i < onlineUsers.size(); i++)
	    {
		 //window for usernames needed?
	    	//print in window: onlineUsers.get(i);
	    }
	}
    //This is the GUI for the Chat (missing inpud from the other user)
    public void chatDisplay() {
        JButton back = new JButton("Back");
        back.addActionListener(new backButtonListener()); //This is the back butten from chatroom to lobby



        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());



        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.getHSBColor(0, 0, 255));
        southPanel.setLayout(new GridBagLayout());

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridBagLayout());
        playerPanel.setBackground(Color.getHSBColor(55, 55, 55));

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
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);


        playerPanel.add(back, right);

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

            boolean chatroom = false;

        }
    }


}