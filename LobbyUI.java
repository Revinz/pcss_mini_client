import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
    	String[] RequestPeople = {"People"};
    	String[] peopleOnlines = {"TestP1", "TestP2", "TestP3"};
    	for (int i=0; i<peopleOnlines.length;i++) {
    		JLabel Person = new JLabel(peopleOnlines[i]); //Create a button for each person online
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
//				try {
//					if () { //If JTextField is empty or null
//						
//					}
//				} catch (IOException ex) {
//                }
			}          
	    });
    }
  
    public void chatOnline() {
    	//Get array of the chat rooms from the server
    	String[] RequestChat = {"Chatrooms"};
    	String[] ChatesOnline = {"TestS1", "TestS2", "TestS3"};
    	for (int i=0; i<ChatesOnline.length;i++) {
    		JLabel ServerName = new JLabel(ChatesOnline[i]);
    		JButton Server = new JButton("Join");//Create a button for each chatroom online
    		SOnline.add(ServerName);
    		SOnline.add(Server); //Add the button to the chatrooms online frame
    		//Create a listener for the button
    		Server.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
 //   					String[] RequestJoin = {"Join",ChatesOnline[i]};
    			}          
    	    });
    	}
    }
    
//    public void connectToServer() throws IOException {
//
//        // Get the server address from a dialog box.
//        String serverAddress = JOptionPane.showInputDialog(
//            frame,
//            "Enter IP Address of the Server:",
//            JOptionPane.QUESTION_MESSAGE);
//
//        // Make connection and initialize streams
//        Socket socket = new Socket(serverAddress, 9898);
//        in = new BufferedReader(
//                new InputStreamReader(socket.getInputStream()));
//        out = new PrintWriter(socket.getOutputStream(), true);
//
//        
//    }
}