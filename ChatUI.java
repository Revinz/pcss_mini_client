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
    String      appName     = "Chatroom";
    ChatUI     mainGUI;
    JFrame      newFrame    = new JFrame(appName);
    JButton     sendMessage;
    JTextField  messageBox;
    static JTextArea   chatBox;
    static List<String> onlineUsers = new ArrayList<String>();
    JScrollPane SP_ONLINE = new JScrollPane();
    public static JList JL_ONLINE = new JList(); // <<<<<<<<<<<<<<<<

    public static String [] ChatRoomNames = { };

    public ChatUI(String chatroomName) {
        roomName = chatroomName;
        // TODO Auto-generated constructor stub
        chatDisplay();
    }

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

    public static void updateUserlist(ArrayList<String> In) {
        //Get array of the usernames from the server
        System.out.println("WE RECIEVED A LIST");
        In.remove(0);
        for(int i = 0; i< onlineUsers.size(); i++)
        {
            onlineUsers.remove(i);
        }

        onlineUsers = In;

        ChatRoomNames = new String[onlineUsers.size()];
        System.out.println("------ Chatroom Users --------");
        for (int j=0; j<onlineUsers.size();j++) {
            ChatRoomNames[j] = onlineUsers.get(j);


            System.out.println("Username: " + onlineUsers.get(j));

        }

        JL_ONLINE.setListData(ChatRoomNames);

    }

    public void leaveRoom() //activate in back button
    {
        try {
            ArrayList<String> command = new ArrayList<String>();
            command.add("LEAVE CHATROOM");
            command.add(roomName);
            System.out.println("You're leaving a chatroom with name: " +roomName);
            Client.objectOutput.writeObject(command);
            Client.objectOutput.flush();
        } catch (IOException e) {
            
            e.printStackTrace();
        }

    }
    


    //This is the GUI for the Chat
    public void chatDisplay() {
        JButton back = new JButton("Back");
        back.addActionListener(new backButtonListener()); //This is the back button from chatroom to lobby

        // The mainPanel is were the chat is visebol
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // The southPanel is were the "Send message" button & the type in message bare is going to be
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.getHSBColor(25, 55, 10));
        southPanel.setLayout(new GridBagLayout());

        // The playerPanel is were the active users are visbol & the back button is going to be
        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(Color.getHSBColor(100, 10, 10));
        playerPanel.setLayout(new GridBagLayout());

        // The type in bare for the message
        messageBox = new JTextField(30);        
        messageBox.requestFocusInWindow();

        // The send message button
        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener()); // This is the message that can be seen in the chat

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);
        //playerPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        //This set the locate of the type in message box and back button
        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        //This set the locate of the send message button
        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 0, 0, 0);
        right.anchor = GridBagConstraints.NORTH;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;
        
        southPanel.add(messageBox, left);       //adds the message box to southPanel
        southPanel.add(sendMessage, right);     //adds the send message button to southPanel
        
        playerPanel.add(back, right);           //adds the back button to playerPanel
        
        JL_ONLINE.setForeground(new java.awt.Color(0,0,0));
        JL_ONLINE.setListData(ChatRoomNames); // The lise of users in imported to JL 

        //This set the locate of the lest of users
        SP_ONLINE.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_ONLINE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        SP_ONLINE.setViewportView(JL_ONLINE);
        newFrame.getContentPane().add(SP_ONLINE);
        SP_ONLINE.setBounds(0, 34, 55, 390);



        mainPanel.add(BorderLayout.SOUTH, southPanel);              // Imports the southPanel (& all in it) to the mainPanel
        mainPanel.add(BorderLayout.WEST, playerPanel);              // Imports the playerPanel (& all in it) to to mainPanel
        newFrame.add(mainPanel);                                    // adds the mainPanel to the window
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(500, 500);                     // sets the size of the window
        newFrame.setVisible(true);                                   // start the window
    }

    // This is the message that can be seen in the chat GUI
    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {                // This ensures that at least 1 character needs to be in (..) befor a message can be send
                System.out.println("Nothing has been written");
            } else if (messageBox.getText().equals(".clear")) {     // If you type ".clear" it will clear the message bord
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {

                sendMessage();    // send message
                messageBox.setText("");  // clear the message box
            }
            messageBox.requestFocusInWindow();
        }
    }
    //This is the back butten from chatroom to lobby
    class backButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            leaveRoom();
            boolean chatroom = false;
            Client.lobby.frame.setVisible(true);
            newFrame.dispose();
        }
    }


}
