	import java.util.Scanner, java.io.DataInputStream, java.io.DataOutputStream, java.net.Socket, java.awt.BorderLayout, java.awt.Color, java.awt.Font, java.awt.GridBagConstraints, java.awt.GridBagLayout, java.awt.Insets, java.awt.event.ActionEvent, java.awt.event.ActionListener, javax.swing.JButton, javax.swing.JFrame, javax.swing.JLabel, javax.swing.JPanel, javax.swing.JScrollPane, javax.swing.JTextArea, javax.swing.JTextField, javax.swing.SwingUtilities, javax.swing.UIManager;


public class Client {
	//Username in a String
	//Scanner to receive input
	String userName;
	String hostName = "localhost";
	int port = 8000;
	DataInputStream input;
	DataOutputStream output;
	Socket socket;
	Scanner scan = new Scanner(System.in);
	boolean chatroom, loggedIn;
	
	// GUI for login and chatroom
	String      appName     = "Login you piece of shit";
    Client     mainGUI;
    JFrame      newFrame    = new JFrame(appName);
    JButton     sendMessage;
    JTextField  messageBox;
    JTextArea   chatBox;
    JTextField  usernameChooser;
    JTextField  passwordChooser;
    JFrame      preFrame;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Connect to server
		socket = new Socket(hostName, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		
		
		
		
		if(!loggedIn)
		{
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
		//Run Login UI
			
			loggedIn = true;//once a username is generated, and accepted by the server
		}
		
		//while loop starts here
		while(loggedIn);
		{
		
			
			if(!chatroom)
			{
		//Run Lobby UI
		
		//Enter a chatroom
			
				chatroom = true; //once server allows access into a chatroom, either by creating a new one or joining an existing one
			}
			else if(chatroom)
			{
			
		//This is the GUI for the Chat (missing inpud from the other user)
    public void display() {
        JButton back = new JButton("Back");
        back.addActionListener(new backButtonListener()); //This is the back butten from chatroom to lobby


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.pink);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener()); // This is the message that can be seen in the chat GUI

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);

        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

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
        southPanel.add(back, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(500, 500);
        newFrame.setVisible(true);
    }
		//Type msges - send them to server - retrieve msges from server
				
				scan.nextLine(); //send this to server
				
				
				System.out.println(input.readLine()); //get messages to print in the chatroom
				
				
				
		//Leave chatroom
				
				chatroom = false; //upon leaving chatroom
			}
			
			
			
		
		}//end of while loop
		
	}//end of main
	

	
	//Communicate with server send / receive messages
	
	public void createRoom(String roomName)
	{
		//Create chatroom method
		
		
	}
	
	public void joinRoom(String roomName)
	{
	//Join chatroom method
	
	}
	
	public void leaveRoom()
	{
	//Leave chatroom method
	
	}
	
	
	// This is the message that can be seen in the chat GUI
    class sendMessageButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing
                System.out.println("NOOOOOooooo.....");
            } else if (messageBox.getText().equals(".clear")) {     // this is popularly not necessary, but if you type ".clear" it will clear the message bord
                chatBox.setText("Cleared all messages\n");
                messageBox.setText("");
            } else {
                chatBox.append("<" + username + ">:\n " +"     "+ messageBox.getText()
                        + "\n");
                messageBox.setText("");
            }
            messageBox.requestFocusInWindow();
        }
    }
    //This is the back butten from chatroom to lobby
    class backButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (messageBox.getText().length() < 1) {
                // do nothing

            } else {
               // chatroom = false;
            }

        }
    }

    String  username;
    String  password;

    // This register if the username and/or IP works or not
    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            password = passwordChooser.getText();
            if ((password.length() < 1) || (username.length() < 1)) {
                System.out.println("Enter a valid username or a IP-address");
            } else {
                preFrame.setVisible(false);
                display();
                // loggedIn = true;
            }
        }

    }
}
