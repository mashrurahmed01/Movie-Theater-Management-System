package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import EntityList.*;
import Entity.*;
import File.*;

public class Homepage extends JFrame implements ActionListener {

    private JPanel panel;
    private JButton movieManagementBtn, customerManagementBtn, backBtn, employeManagementBtn;
    private Font buttonFont = new Font("Arial", Font.BOLD, 25);
    private Login login;
    private adminLogin adlogin;
    private MovieList movies;
    private CustomerList customers;
    private EmployeList employes;

    public Homepage(adminLogin adlogin) {
        super("Home Page");
        this.adlogin = adlogin;
        initializeUI();
        createButtons();
        this.setVisible(true);
    }

    public void initializeUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setIconImage(new ImageIcon("./Assets/p.jpg").getImage());

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(700, 500);
        panel.setOpaque(false);
        this.add(panel);

        ImageIcon image = new ImageIcon("./Assets/Show.jpg");
        JLabel background = new JLabel();
        background.setBounds(0, 0, 700, 500);
        background.setIcon(image);
        this.add(background);
    }

    public void createButtons() {
        movieManagementBtn = createButton(200, 150, 350, 40, "Movie Management");
		employeManagementBtn = createButton(200, 200, 350, 40, "Employee Management");
        customerManagementBtn = createButton(200, 250, 350, 40, "Customer Management");
        backBtn = createButton(450, 390, 150, 40, "<- Back");
    }

    public JButton createButton(int x, int y, int width, int height, String text) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, width, height);
        btn.setFont(buttonFont);
        btn.addActionListener(this);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        panel.add(btn);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == movieManagementBtn) {
            openMovieManagement();
        }
		if (e.getSource() == employeManagementBtn) {
            openEmployeManagement();
        }if (e.getSource() == customerManagementBtn) {
            openCustomerManagement();
        }
        if (e.getSource() == backBtn) {
            adlogin.setVisible(true);
            this.dispose();
        }
    }

    public void openMovieManagement() {
        
            movies = new MovieList();
            FileIO.loadMoviesFromFile(movies, "./File/movies.txt");
            System.out.println("Movies Data Loaded");
            MovieManagement mm = new MovieManagement(this, movies);
            this.setVisible(false);
        
        
    }
    public void openCustomerManagement() {

        customers = new CustomerList();
        FileIO.saveCustomersToFile(customers, "./File/customers.txt");
        System.out.println("Customers Data Loaded");
        CustomerManagement mm = new CustomerManagement(this, customers);
        this.setVisible(false);


    }
	public void openEmployeManagement() {
        
            employes = new EmployeList();
            FileIO.loadEmployeesFromFile(employes, "./File/employes.txt");
            System.out.println("Employees Data Loaded");
            EmployeManagement mm = new EmployeManagement(this, employes);
            this.setVisible(false);
        
        
    }
    public void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
