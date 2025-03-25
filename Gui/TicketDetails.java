package Gui;
import javax.swing.*;
import java.awt.*;
import Entity.*;
import EntityList.*;

public class TicketDetails extends JFrame {
    private JLabel nameLabel, movieLabel, seatsLabel, amountLabel;
    private JTextField nameField, movieField, seatsField, amountField;
	TicketBooking tb;
    private Font font = new Font("Arial", Font.PLAIN, 20);

    public TicketDetails(TicketBooking tb,String customerName, String movieName, int numSeats, double totalAmount) {
        super("Ticket Details");
        this.tb=tb;
        initializeUI();
        createComponents(customerName, movieName, numSeats, totalAmount);
        this.setVisible(true);
    }

    private void initializeUI() {
        this.setSize(1000, 500);
        this.setLocation(320,220);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(4, 2));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void createComponents(String customerName, String movieName, int numSeats, double totalAmount) {
        nameLabel = new JLabel("Customer Name:");
        nameLabel.setFont(font);
        add(nameLabel);
        nameField = new JTextField(customerName);
        nameField.setFont(font);
        nameField.setEditable(false);
        add(nameField);

        movieLabel = new JLabel("Movie Name:");
        movieLabel.setFont(font);
        add(movieLabel);
        movieField = new JTextField(movieName);
        movieField.setFont(font);
        movieField.setEditable(false);
        add(movieField);

        seatsLabel = new JLabel("Number of Seats:");
        seatsLabel.setFont(font);
        add(seatsLabel);
        seatsField = new JTextField(String.valueOf(numSeats));
        seatsField.setFont(font);
        seatsField.setEditable(false);
        add(seatsField);

        amountLabel = new JLabel("Total Amount:");
        amountLabel.setFont(font);
        add(amountLabel);
        amountField = new JTextField(String.valueOf(totalAmount));
        amountField.setFont(font);
        amountField.setEditable(false);
        add(amountField);
    }
}
