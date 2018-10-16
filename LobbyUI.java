//Usually you will require both swing and awt packages
// even if you are working with just swings.
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
    private JFrame frame = new JFrame("Chat Frame"); 
    private JPanel POnline = new JPanel(); // the panel is not visible in output
    private JPanel SOnline = new JPanel(); // the panel is not visible in output
    
	public LobbyUI() {
        JLabel POLabel = new JLabel("People Online");
        POnline.add(POLabel); // Components Added using Flow Layout
        //Get Online people from server
        
        JLabel SOLabel = new JLabel("Servers Online");
        SOnline.add(SOLabel); // Components Added using Flow Layout
        //Get Online servers from server
        
		//Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.WEST, POnline);
        frame.getContentPane().add(BorderLayout.CENTER, SOnline);
	}
	
    public static void main(String args[]) {
    	LobbyUI client = new LobbyUI();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setSize(500, 500);
        client.frame.setVisible(true);

//        //Creating the panel at bottom and adding components
//        JPanel panel = new JPanel(); // the panel is not visible in output
//        JLabel label = new JLabel("Enter Text");
//        JTextField tf = new JTextField(10); // accepts upto 10 characters
//        JButton send = new JButton("Send");
//        JButton reset = new JButton("Reset");
//        panel.add(label); // Components Added using Flow Layout
//        panel.add(tf);
//        panel.add(send);
//        panel.add(reset);

    }
    
//    public void PeopleOnline() {
//    	//Get how many people are online from server and their username
//    	int peopleOnlines=5;
//    	String Username = "Bill";
//    	for (int i=0; i<peopleOnlines;i++) {
//    		JButton Person = new JButton(Username);
//    		POnline.add(Person);
//    		Person.addActionListener(new ActionListener() {
//    	        
//    			@Override
//    			public void actionPerformed(ActionEvent arg0) {
//    					//Start Chat with this person;				
//    			}          
//    	    });
//    	}              
//    }
//    
//    
//    public void ServerOnline() {
//    	//Get how many servers are online from server
//    	int ServersOnlies=5;
//    	for (int i=0; i<ServersOnlies;i++) {
//    		JButton Server = new JButton("Server: "+i);
//    		SOnline.add(Server);
//    		Server.addActionListener(new ActionListener() {
//    	        
//    			@Override
//    			public void actionPerformed(ActionEvent arg0) {
//    					//Join Server;				
//    			}          
//    	    });
//    	}
//    }
    
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