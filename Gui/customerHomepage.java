package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import EntityList.*;
import Entity.*;
import java.util.Arrays;
import java.util.ArrayList;
import File.*;

public class customerHomepage extends JFrame implements ActionListener {

    private JPanel panel, gridPanel;
    private JButton button, backButton;
    private Font buttonFont = new Font("Arial", Font.BOLD, 25);
    private Login login;
    private adminLogin adlogin;
    private JScrollPane scrollPane;
    private MovieList movies;
    private JButton[] movieButtons;
    private Movie[] moviesArray;
    private BookingPage bp;
    private CustomerList customers;
    private Movie selectedMovie;


    public customerHomepage(Login login, MovieList movies) {
        super("Home Page");
        this.login = login;
        this.movies = movies;
        initializeUI();
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        createMovieButtons();

    }

    public void initializeUI() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(10, 15, 10, 35));
        panel.setBackground(Color.WHITE);
        ArrayList<Movie> allMovies = movies.getAll();

        scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.add(scrollPane);

        JPanel backpanel = new JPanel();
        backpanel.setBounds(20, 50, 1000, 500);
        panel.add(backpanel);
        backpanel.setLayout(null);

        backButton = new JButton("      BACK");
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setBounds(15, 20, 90, 40);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backpanel.add(backButton);
        backButton.addActionListener(this);

        JLabel backLabel = new JLabel("");
        backLabel.setIcon(new ImageIcon("Assets/back.png"));
        backLabel.setBounds(18, 20, 30, 40);
        backpanel.add(backLabel);

    }

    public void createMovieButtons() {
        ArrayList<Movie> allMovies = movies.getAll();
        moviesArray = allMovies.toArray(new Movie[0]);
        movieButtons = new JButton[allMovies.size()];
        JPanel emptyPanel = new JPanel();
        panel.add(emptyPanel);


        for (int i = 0; i < allMovies.size(); i++) {
            Movie movie = allMovies.get(i);

            JLabel movieLabel = new JLabel("" + movie.getMovieName());
            movieLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
            movieLabel.setHorizontalAlignment(JLabel.CENTER);

            JLabel seatsLabel = new JLabel("Seats Available: " + movie.getSeatsAvailable());
            seatsLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
            seatsLabel.setHorizontalAlignment(JLabel.CENTER);

            JLabel priceLabel = new JLabel("Price: " + movie.getTicketPrice());
            priceLabel.setHorizontalAlignment(JLabel.CENTER);
            priceLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));

            JPanel moviePanel = new JPanel();
            moviePanel.setLayout(new BorderLayout());
            moviePanel.add(movieLabel, BorderLayout.NORTH);
            moviePanel.add(seatsLabel, BorderLayout.CENTER);
            moviePanel.add(priceLabel, BorderLayout.SOUTH);


            movieButtons[i] = new JButton("");
            movieButtons[i].setFont(buttonFont);
            movieButtons[i].addActionListener(this);
            movieButtons[i].add(moviePanel, BorderLayout.CENTER);

            panel.add(movieButtons[i]);
        }
    }

    public void updateSeatsLabel(Movie updatedMovie) {
        for (int i = 0; i < moviesArray.length; i++) {
            if (moviesArray[i].equals(updatedMovie)) {
                JPanel moviePanel = (JPanel) movieButtons[i].getComponent(0);
                JLabel seatsLabel = (JLabel) moviePanel.getComponent(1);

                seatsLabel.setText("Seats Available: " + updatedMovie.getSeatsAvailable());
                break;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < movieButtons.length; i++) {
            if (e.getSource() == movieButtons[i]) {
                Movie selectedMovie = moviesArray[i];
                BookingPage bp = new BookingPage(this, customers, movies, selectedMovie);
                bp.setVisible(true);
                break;
            }
        }
        if (backButton == e.getSource()) {
            this.dispose();
            login.setVisible(true);
        }
    }
}



