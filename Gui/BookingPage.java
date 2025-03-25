package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import EntityList.*;
import Entity.*;
import java.util.Arrays;
import java.util.ArrayList;
import File.*;

public class BookingPage extends JFrame implements ActionListener {

    private JPanel panel;
    private JButton bookingButton;
    private Font font = new Font("Segoe UI semibold", Font.PLAIN, 20);
    private customerHomepage ch;
    private CustomerList customers;
    private JComboBox<Integer> seatsComboBox;
    private MovieList movies;
    private Movie selectedMovie;
    private Customer selectedCustomer;


    public BookingPage(customerHomepage ch, CustomerList customers, MovieList movies, Movie selectedMovie) {
        super("Booking Page");
        this.customers = customers;
        this.ch = ch;
        this.movies = movies;
        this.selectedMovie = selectedMovie;

        initializeUI();
        components();
        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
	 
	     public int getSelectedSeats() {
        return (Integer) seatsComboBox.getSelectedItem();
    }

    public void initializeUI() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(500, 250);
        panel.setOpaque(false);
        this.add(panel);
        this.setVisible(true);
		
    }

    public void components() {
        JLabel seatsLabel = new JLabel("Select number of Tickets for Booking");
        seatsLabel.setBounds(70, 30, 500, 50);
        seatsLabel.setFont(font);
        panel.add(seatsLabel);

        seatsComboBox = new JComboBox<>();
        for (int i = 1; i <= selectedMovie.getSeatsAvailable(); i++) {
            seatsComboBox.addItem(i);
        }
        seatsComboBox.setBounds(220, 80, 70, 30);
        seatsComboBox.setFont(font);
        panel.add(seatsComboBox);

        bookingButton = new JButton("Confirm Booking");
        bookingButton.setBounds(145, 130, 200, 30);
        bookingButton.setFont(font);
        bookingButton.addActionListener(this);
        panel.add(bookingButton);
    }


    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookingButton) {
            int selectedSeats = (Integer) seatsComboBox.getSelectedItem();
            double totalPrice = selectedSeats * selectedMovie.getTicketPrice();
			
            int updatedSeats = selectedMovie.getSeatsAvailable() - selectedSeats;
            selectedMovie.setSeatsAvailable(updatedSeats);

            ch.updateSeatsLabel(selectedMovie);
		
            JOptionPane.showMessageDialog(
                    this,
                    "Booking Successful\nTotal Price: " + totalPrice,
                    "Booking Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
        }
    }
}


