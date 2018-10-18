import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LobbyUI {
//	private BufferedReader in;
//    private PrintWriter out;
	//Setup of the program window
    private JFrame frame = new JFrame("Chat Frame"); 
    private JPanel POnline = new JPanel();
    private JPanel SOnline = new JPanel();
    
	public LobbyUI() {
		//The frame for the people online
        JLabel POLabel = new JLabel("People Online"); //Set the title of the frame
        POnline.add(POLabel); //Create the frame
        //Get Online people from server
        PeopleOnline();
        
        //The frame for the chatrooms online
        JLabel SOLabel = new JLabel("Chatrooms Online"); //Set the title of the frame
        SOnline.add(SOLabel); //Create the frame
        //Get Online servers from server
        chatOnline();
        
		//Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, POnline);
        frame.getContentPane().add(BorderLayout.CENTER, SOnline);
	}
	
    public static void main(String args[]) {
    	LobbyUI client = new LobbyUI();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Make the program close when the window is closed
        client.frame.setSize(500, 500); //Set the size of the window
        client.frame.setVisible(true); //Make the window visible

    }
    
    public void PeopleOnline() {
    	//Get array of the usernames from the server
    	int[] peopleOnlines;
    	for (int i=0; i<peopleOnlines.length;i++) {
    		JButton Person = new JButton(peopleOnlines[i]); //Create a button for each person online
    		POnline.add(Person); //Add the button to the people online frame
    		//Create a listener for the button
    		Person.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    					//Start Chat with this person;				
    			}          
    	    });
    	}              
    }
  
    public void chatOnline() {
    	//Get array of the chat rooms from the server
    	int[] ChatesOnline;
    	for (int i=0; i<ChatesOnline.length;i++) {
    		JButton Server = new JButton(ChatesOnline[i]);//Create a button for each chatroom online
    		SOnline.add(Server); //Add the button to the chatrooms online frame
    		//Create a listener for the button
    		Server.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    					//Join Server;				
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