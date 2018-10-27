import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class LoginUI {
    String  username;                                   // A string that contains the username
    String  password;                                   // A string that contains the IP-address
    String      appName     = "Login";                  // The name of the window
    JFrame      newFrame    = new JFrame(appName);      //
    JTextField  usernameChooser;                        // The typed in username
    JTextField  passwordChooser;                        // The typed in IP-address
    JFrame      preFrame;                               // The window



    //This is the first GUI  "username and password"
    public LoginUI() {
        newFrame.setVisible(false);
        preFrame = new JFrame(appName);                                 // This add the window name to the window
        usernameChooser = new JTextField(15);                   // This is the username type in
        passwordChooser = new JTextField(15);                   // This is the IP-address type in
        JLabel chooseUsernameLabel = new JLabel("Username:");      // This is the label for the username type in
        JLabel choosePasswordLabel = new JLabel("IP-address:");    // This is the label for the IP-address type in

        // This cradt the button that sends the username & IP-address to the sover
        JButton acontinue = new JButton("Continue");
        acontinue.addActionListener(new enterServerButtonListener());
        JPanel prePanel = new JPanel(new GridBagLayout());

        //This set the locate of the username type in and the username label
        GridBagConstraints preRight = new GridBagConstraints();
        preRight.insets = new Insets(-25, 0, 25, 10);
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preLeft.insets = new Insets(-25, 10, 25, 10);

        //This set the locate of the "IP-address type in" and the "IP-address" label
        GridBagConstraints dreRight = new GridBagConstraints();
        dreRight.insets = new Insets(25, 0, -25, 10);
        dreRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints dreLeft = new GridBagConstraints();
        dreLeft.anchor = GridBagConstraints.WEST;
        dreLeft.insets = new Insets(25, 10, -25, 10);


        //This set the size of the of the type in bare of "username type in" & "IP-address type in"
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(chooseUsernameLabel, preLeft); // adds the "username" label to the location in line 60-61
        prePanel.add(usernameChooser, preRight);    // adds the "username type in" to the location in line 57-58

        prePanel.add(choosePasswordLabel, dreLeft); // adds the "IP-address" label to the location in line 68-69
        prePanel.add(passwordChooser, dreRight);    // adds the "IP-address type in" to the location in line 65-66


        preFrame.add(BorderLayout.CENTER, prePanel); // This import the "username" label, "username type in", "IP-address" label & "IP-address type in"
        preFrame.add(BorderLayout.SOUTH, acontinue); // This sets the location of the "Continue" button & import it the the window
        preFrame.setSize(500, 500);     // sets the size of the window
        preFrame.setVisible(true);                   // start the window

    }

    // This class contains what the button do
    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            password = passwordChooser.getText();
            if ((password.length() < 1) || (username.length() < 1)) {           // This ensures that at least 1 character needs to be in username & IP-address
                System.out.println("Enter a valid username or a IP-address");
            } else {
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

            }
        }

    }
}
