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
    String      appName     = "Login you piece of shit";
    JFrame      newFrame    = new JFrame(appName);
    JButton     sendMessage;
    JTextField  messageBox;
    JTextArea   chatBox;
    JTextField  usernameChooser;
    JTextField  passwordChooser;
    JFrame      preFrame;
    
    LoginUI() {
    	
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
        preFrame = new JFrame(appName);
        usernameChooser = new JTextField(15);
        passwordChooser = new JTextField(15); // This is the IP-address type in thingy
        JLabel chooseUsernameLabel = new JLabel("Username:");
        JLabel choosePasswordLabel = new JLabel("IP-address:");

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


        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, acontinue);
        preFrame.setSize(500, 500);
        preFrame.setVisible(true);

    }



    static String  username;
    String  password; // This is the IP-adress

    // This register if the username and/or IP works or not
    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	Client.state = Client.State.login;
            username = usernameChooser.getText();
            password = passwordChooser.getText();
            if ((password.length() < 1) || (username.length() < 1)) {
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
        			Client.notLoggedIn = false;
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
