import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatUI {
    //private static A_Chat_Client ChatCliet;
    public static JList JL_ONLINE = new JList();

    String      username;
    String      appName     = "Chat room";
    JFrame      newFrame    = new JFrame(appName);
    JButton     sendMessage;
    JTextField  messageBox;
    JTextArea   chatBox;
    JFrame      preFrame;


    JScrollPane SP_ONLINE = new JScrollPane();




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager
                            .getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ChatUI mainGUI = new ChatUI();
                mainGUI.chatUI();
            }
        });
    }


    //This is the GUI for the Chat (missing inpud from the other user)
    public void chatUI() {
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
        String [] TestNames = {"Jonas","Peter","Timy","Starling"}; // Needs to be svobt with an array from the server
        JL_ONLINE.setForeground(new java.awt.Color(0,0,0));
        JL_ONLINE.setListData(TestNames);

        //The visibal lest of users
        SP_ONLINE.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        SP_ONLINE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SP_ONLINE.setViewportView(JL_ONLINE);
        newFrame.getContentPane().add(SP_ONLINE);
        SP_ONLINE.setBounds(0, 34, 66, 390);


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
            preFrame.setVisible(true);


        }
    }
}
