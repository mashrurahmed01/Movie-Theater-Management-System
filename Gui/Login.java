package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import File.*;
import Gui.*;
import EntityList.*;
import Entity.*;
import java.util.Arrays;
import java.util.ArrayList;
import File.*;

public class Login extends JFrame implements ActionListener {
    JLabel userNameLabel, userPassLabel;
    JTextField userName;
    JPasswordField password;
    JButton btnLogin, btnRegister, adminLogin;
	private MovieList movies;
    Font font = new Font("Segoe UI Semibold", Font.PLAIN, 20);



    public Login() {



        super("Login");
        setSize(564, 376);
        setLocation(550, 280);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(564, 375);
        panel.setOpaque(false);

        this.add(panel);
        ImageIcon image = new ImageIcon("./Assets/login.jpg");
        JLabel background = new JLabel();
        background.setBounds(0, 0, 564, 375);
        background.setIcon(image);
        this.add(background);


        userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(110, 50, 110, 30);
        userNameLabel.setFont(font);
        userNameLabel.setForeground(Color.BLACK);
        add(userNameLabel);
        this.add(background);


        userName = new JTextField("Mashrur");
        userName.setBounds(230, 50, 150, 30);
        userName.setFont(font);
        add(userName);
        this.add(background);

        userPassLabel = new JLabel("Password");
        userPassLabel.setBounds(110, 100, 110, 30);
        userPassLabel.setFont(font);
        add(userPassLabel);
        this.add(background);

        password = new JPasswordField("1234");
        password.setBounds(230, 100, 150, 30);
        password.setEchoChar('*');
        password.setFont(font);
        add(password);
        this.add(background);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(130, 150, 110, 30);
        btnLogin.setFont(font);
        btnLogin.addActionListener(this);
        add(btnLogin);
        this.add(background);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(260, 150, 110, 30);
        btnRegister.setFont(font);
        btnRegister.setBackground(Color.GREEN);
        btnRegister.setForeground(Color.BLACK);
        btnRegister.addActionListener(this);
        add(btnRegister);
        this.add(background);

        adminLogin = new JButton("Admin Login");
        adminLogin.setBounds(0, 0, 160, 30);
        adminLogin.setLocation(170, 190);
        adminLogin.setFont(font);
        adminLogin.setBackground(Color.RED);
        adminLogin.setForeground(Color.WHITE);
        adminLogin.addActionListener(this);
        add(adminLogin);
        this.add(background);

        setVisible(true);
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
				movies = new MovieList();
                FileIO.loadMoviesFromFile(movies, "./File/movies.txt");
                customerHomepage hp = new customerHomepage(this, movies);
                userName.setText("");
                password.setText("");
                setVisible(false);
            } else if (status == 2) {
                JOptionPane.showMessageDialog(this, "Invalid Username/Password",
                        "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "User not registered",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (btnRegister == e.getSource()) {
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
        if(e.getSource()==adminLogin){
            adminLogin al = new adminLogin();
            this.setVisible(false);

        }
    }
}




