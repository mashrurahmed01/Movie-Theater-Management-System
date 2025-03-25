package Gui;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import File.*;

public class adminLogin extends JFrame implements ActionListener {
    JPanel panel;
    JLabel userNameLabel, userPassLabel;
    JTextField userName;
    JPasswordField password;
    JButton btnLogin, btnRegister, adminLogin, backButton;
    Font font = new Font("arial", Font.PLAIN, 20);
    Login login;
    public adminLogin() {
        super("Admin Login");
        setSize(564, 375);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(564, 375);
        panel.setOpaque(false);
        //panel.setBackground(Color.DARK_GRAY); /////for background color

        this.add(panel);
        ImageIcon image = new ImageIcon("./Assets/admin.jpg");
        JLabel background = new JLabel();
        background.setBounds(0, 0, 564, 564);
        background.setIcon(image);
        this.add(background);

        // User Name Label
        userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(110, 50, 110, 30);
        userNameLabel.setFont(font);
        userNameLabel.setForeground(Color.WHITE);
        add(userNameLabel);
        this.add(background);
        // User Name TextField
        userName = new JTextField("Mashrur");
        userName.setBounds(230, 50, 150, 30);
        userName.setFont(font);
        add(userName);
        this.add(background);
        // User Password Label
        userPassLabel = new JLabel("Password");
        userPassLabel.setBounds(110, 100, 110, 30);
        userPassLabel.setFont(font);
        userPassLabel.setForeground(Color.WHITE);
        add(userPassLabel);
        this.add(background);

        // User Password Password Field
        password = new JPasswordField("1234");
        password.setBounds(230, 100, 150, 30);
        password.setEchoChar('*');
        password.setFont(font);
        add(password);
        this.add(background);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(200, 150, 110, 30);
        btnLogin.setFont(font);
        btnLogin.addActionListener(this);
        add(btnLogin);
        this.add(background);

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setBounds(15, 15, 30, 30);
        panel.add(backButtonPanel);
        backButtonPanel.setLayout(null);

        backButton = new JButton("");
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setBounds(0, 0, 30, 30);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButtonPanel.add(backButton);
        backButton.addActionListener(this);
        this.add(background);

        JLabel backLabel = new JLabel("New label");
        backLabel.setIcon(new ImageIcon("Assets/back.png"));
        backLabel.setBounds(0, 0, 30, 30);
        backButtonPanel.add(backLabel);

        setVisible(true);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1000,500);
        panel.setOpaque(true);
        panel.setBackground(Color.BLACK);
        this.add(panel);

//        ImageIcon image = new ImageIcon("./Assets/HomePage.jpg");
//        JLabel background = new JLabel();
//        background.setBounds(0,0,1000,500);
//        background.setIcon(image);
//        this.add(background);
    }

    public void actionPerformed(ActionEvent e) {
        if (btnLogin == e.getSource()) {
            String name = userName.getText();
            String pass = String.valueOf(password.getPassword());

            if (name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int status = FileIO.checkUser(name, pass, "./File/users.txt");
            if (status == 1) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                Homepage hp = new Homepage(this);
                userName.setText("");
                password.setText("");
                setVisible(false);
            } else if (status == 2) {
                JOptionPane.showMessageDialog(this, "Invalid Password",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "User not registered",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } if (backButton == e.getSource()) {
            setVisible(false);
//            login.set.visible(true);
            Login login = new Login();
        }
        else if (btnRegister == e.getSource()) {
            String name = userName.getText();
            String pass = String.valueOf(password.getPassword());

            if (name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both username and password.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean registered = FileIO.registerUser(name, pass, "./File/users.txt");
            if (registered) {
                JOptionPane.showMessageDialog(this, "Registration Successful. You can now log in.");
            } else {
                JOptionPane.showMessageDialog(this, "User already exists. Please choose a different username.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }


}
