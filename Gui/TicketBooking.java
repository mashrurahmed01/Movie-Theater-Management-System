package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import EntityList.*;
import Entity.*;
import File.*;

public class TicketBooking extends JFrame implements ActionListener {
    private JPanel panel;
    private JLabel nameLabel, movieLabel, seatsLabel, idLabel;
    private JTextField nameField,movieField;
    private JComboBox<String> movieComboBox, seatsComboBox;
    private JTextField idField;
    private JButton bookBtn, backBtn;
    private Font font = new Font("Arial", Font.PLAIN, 20);
    private Homepage hp;
    private MovieList movies;
    private CustomerList customers;

public TicketBooking(Homepage hp, MovieList movies, CustomerList customers) {
    super("Ticket Booking");
    this.hp = hp;
    this.movies = movies; 
    this.customers = customers;
    initializeUI();
    createComponents();
    this.setVisible(true);
}


    private void initializeUI() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 500);
        this.setLocation(500,300);
        this.setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1000, 500);
        panel.setOpaque(false);
        this.add(panel);

        ImageIcon image = new ImageIcon("./Assets/Homepage.jpg");
        JLabel background = new JLabel();
        background.setBounds(0, 0, 1000, 500);
        background.setIcon(image);
        this.add(background);
    }

    private void createComponents() {
        nameLabel = new JLabel("Customer Name :");
        nameLabel.setBounds(50, 50, 180, 30);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(210, 50, 250, 30);
        nameField.setFont(font);
        panel.add(nameField);
		
        movieLabel = new JLabel("Movie Name :");
        movieLabel.setBounds(500, 50, 150, 30);
        movieLabel.setFont(font);
        movieLabel.setForeground(Color.WHITE);
        panel.add(movieLabel);

        movieField = new JTextField();
        movieField.setBounds(660, 50, 250, 30);
        movieField.setFont(font);
        panel.add(movieField);

        idLabel = new JLabel("Movie ID :");
        idLabel.setBounds(50, 100, 180, 30);
        idLabel.setFont(font);
        idLabel.setForeground(Color.WHITE);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(210, 100, 100, 30);
        idField.setFont(font);
        panel.add(idField);

        seatsLabel = new JLabel("Number of Seats:");
        seatsLabel.setBounds(50, 150, 160, 30);
        seatsLabel.setFont(font);
        seatsLabel.setForeground(Color.WHITE);
        panel.add(seatsLabel);

        seatsComboBox = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {
            seatsComboBox.addItem(String.valueOf(i));
        }
        seatsComboBox.setBounds(220, 150, 70, 30);
        seatsComboBox.setFont(font);
        panel.add(seatsComboBox);

        bookBtn = new JButton("Book Ticket");
        bookBtn.setBounds(300, 210, 180, 30);
        bookBtn.setFont(font);
        bookBtn.addActionListener(this);
        bookBtn.setBackground(Color.BLACK);
        bookBtn.setForeground(Color.WHITE);
        panel.add(bookBtn);

        backBtn = new JButton("<- Back");
        backBtn.setBounds(800, 390, 130, 30);
        backBtn.setFont(font);
        backBtn.addActionListener(this);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        panel.add(backBtn);
    }

public void actionPerformed(ActionEvent e) {
    if (e.getSource() == bookBtn) {
        String name = nameField.getText();
        String movieName = movieField.getText();
        String idText = idField.getText();
        String numSeatsText = (String) seatsComboBox.getSelectedItem();

        if (!movieName.isEmpty() && !movieName.isEmpty() && !idText.isEmpty() && !numSeatsText.isEmpty()) {
            try {
                int id = Integer.parseInt(idText);
                int numSeats = Integer.parseInt(numSeatsText);

                Movie movie = getMovieById(id);

                if (movie != null && numSeats <= movie.getSeatsAvailable()) {
                    double totalAmount = movie.getTicketPrice() * numSeats;

                    Customer customer = new Customer(id, movie.getMovieName(), numSeats, totalAmount);
                    customers.insert(customer);
                    movie.setSeatsAvailable(movie.getSeatsAvailable() - numSeats);

                    JOptionPane.showMessageDialog(this, "Ticket booked successfully!");
                    nameField.setText("");
                    movieField.setText("");
                    idField.setText("");
                    seatsComboBox.setSelectedIndex(0);

                    TicketDetails td = new TicketDetails(this, name, movie.getMovieName(), numSeats, totalAmount);

                    this.setVisible(false);
                    updateFiles();
                } else {
                    JOptionPane.showMessageDialog(this, "Seats not available or invalid data.", "Booking Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID or number of seats.", "Data Entry Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all details.", "Data Entry Warning", JOptionPane.WARNING_MESSAGE);
        }
    } else if (e.getSource() == backBtn) {
        hp.setVisible(true);
        this.dispose();
    }
}

    private Movie getMovieById(int movieId) {
        for (Movie movie : movies.getAll()) {
            if (movie.getID() == movieId) {
                return movie;
            }
        }
        return null;
    }

    private void updateFiles() {
        try {
            FileIO.loadMoviesFromFile(movies, "./File/movies.txt");
            FileIO.saveCustomersToFile(customers, "./File/customers.txt");
            System.out.println("Files updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Error updating files.");
        }
    }

    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
	

}