import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
<<<<<<< HEAD

public class LoginUI {
    String  username;                                   // A string that contains the username
    String  password;                                   // A string that contains the IP-address
    String      appName     = "Login";                  // The name of the window
    JFrame      newFrame    = new JFrame(appName);      //
    JTextField  usernameChooser;                        // The typed in username
    JTextField  passwordChooser;                        // The typed in IP-address
    JFrame      preFrame;                               // The window


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
=======
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

class LoginUI {
    boolean     loggedIn = false;
    String      appName     = "Login";
    JFrame      newFrame    = new JFrame(appName);
    JButton     sendMessage;
    JTextField  messageBox;
    JTextArea   chatBox;
    JTextField  usernameChooser;
    JTextField  passwordChooser;
    JFrame      preFrame;
    
    LoginUI() {
    	
>>>>>>> parent of ff4ec82... Update LoginUI.java
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                preDisplay();
    }



    //this is the first GUI  "username and password"
    public void preDisplay() {
        newFrame.setVisible(false);
<<<<<<< HEAD
        preFrame = new JFrame(appName);                                 // This add the window name to the window
        usernameChooser = new JTextField(15);                   // This is the username type in
        passwordChooser = new JTextField(15);                   // This is the IP-address type in
        JLabel chooseUsernameLabel = new JLabel("Username:");      // This is the label for the username type in
        JLabel choosePasswordLabel = new JLabel("IP-address:");    // This is the label for the IP-address type in
=======
        preFrame = new JFrame(appName);
        usernameChooser = new JTextField(15);
        passwordChooser = new JTextField(15); // This is the IP-address type in thingy
        JLabel chooseUsernameLabel = new JLabel("Username:");
        JLabel choosePasswordLabel = new JLabel("IP-address:");
>>>>>>> parent of ff4ec82... Update LoginUI.java

        JButton acontinue = new JButton("Continue");
        acontinue.addActionListener(new enterServerButtonListener());
        JPanel prePanel = new JPanel(new GridBagLayout());


        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(-25, 0, 25, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(-25, 10, 25, 10);
        // preRight.weightx = 2.0;
        GridBagConstraints dreRight = new GridBagConstraints();
        dreRight.insets = new Insets(25, 0, -25, 10);
        dreRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints dreLeft = new GridBagConstraints();
        dreLeft.anchor = GridBagConstraints.WEST;
        dreLeft.insets = new Insets(25, 10, -25, 10);
        // dreRight.weightx = 2.0;


        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(chooseUsernameLabel, preLeft);
        prePanel.add(usernameChooser, preRight);

        prePanel.add(choosePasswordLabel, dreLeft);
        prePanel.add(passwordChooser, dreRight);


<<<<<<< HEAD
        preFrame.add(BorderLayout.CENTER, prePanel); // This import the "username" label, "username type in", "IP-address" label & "IP-address type in"
        preFrame.add(BorderLayout.SOUTH, acontinue); // This sets the location of the "Continue" button & import it the the window
        preFrame.setSize(500, 500);     // sets the size of the window
        preFrame.setVisible(true);                   // start the window

    }

    // This class contains what the button do
=======
        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, acontinue);
        preFrame.setSize(500, 500);
        preFrame.setVisible(true);

    }



    static String  username;
    String  password; // This is the IP-adress

    // This register if the username and/or IP works or not
>>>>>>> parent of ff4ec82... Update LoginUI.java
    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            username = usernameChooser.getText();
            password = passwordChooser.getText();
            if ((password.length() < 1) || (username.length() < 1)) {           // This ensures that at least 1 character needs to be in username & IP-address
                System.out.println("Enter a valid username or a IP-address");
            } else {
<<<<<<< HEAD
                // THe IP-address for the server
                String serverAddress = JOptionPane.showInputDialog(
                        preFrame,
                        "Enter IP Address of the Server:",
                        JOptionPane.QUESTION_MESSAGE);

                Socket usernameSocket = null;
                //These lines establish the socket connection between the client and the server
                try {
                    usernameSocket = new Socket(serverAddress, 8000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PrintStream OUT = null;
                try {
                    OUT = new PrintStream(usernameSocket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                OUT.println(username);

                //end the display
                preFrame.setVisible(false);

=======
                   	
            	Client.hostName = password;
            	try {
            		Client.socket = new Socket(Client.hostName, Client.port);
            		Client.objectInput = new ObjectInputStream(Client.socket.getInputStream());
            		Client.objectOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            		ArrayList<String> command = new ArrayList<String>();
            		command.add(username);
            		Client.userName = username;
            		Client.objectOutput.writeObject(command);
        			Client.objectOutput.flush();
        			LobbyUI Lobby = new LobbyUI();
        			preFrame.setVisible(false);
        			
            		}
            		catch (UnknownHostException e) {
                        System.err.println("Don't know about host: " + Client.hostName);
                    } catch (IOException e) {
                        System.err.println("Couldn't get I/O for the connection to:" + Client.hostName);
                    }
>>>>>>> parent of ff4ec82... Update LoginUI.java
            }
        }

    }
}
