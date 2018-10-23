package com.company;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    LoginUI     mainGUI;
    JFrame      newFrame    = new JFrame(appName);
    JButton     sendMessage;
    JTextField  messageBox;
    JTextArea   chatBox;
    JTextField  usernameChooser;
    JTextField  passwordChooser;
    JFrame      preFrame;

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
                LoginUI mainGUI = new LoginUI();
                mainGUI.preDisplay();
            }
        });
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



    String  username;
    String  password; // This is the IP-adress

    // This register if the username and/or IP works or not
    class enterServerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            username = usernameChooser.getText();
            password = passwordChooser.getText();
            if ((password.length() < 1) || (username.length() < 1)) {
                System.out.println("Enter a valid username or a IP-address");
            } else {
                loggedIn = true;
            }
        }

    }
}
