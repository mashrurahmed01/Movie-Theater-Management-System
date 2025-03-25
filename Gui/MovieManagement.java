package Gui;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import Entity.*;
import EntityList.*;
import java.util.Arrays;
import java.util.ArrayList;
import File.*;


public class MovieManagement extends JFrame implements ActionListener{
	JPanel panel;
	JButton addBtn,deleteBtn,backBtn;
	JLabel idLabel,movieLabel,priceLabel,seatsLabel;
	JTextField idField,movieField,priceField,seatsField;
	
	JTable table;
	DefaultTableModel model;

 	Font font = new Font("arial",Font.BOLD,25);
 	Font bfont = new Font("arial",Font.BOLD,25);
	Homepage hp;
	MovieList movies;
	CustomerList customers = new CustomerList();
	public MovieManagement(Homepage hp, MovieList movies){
		super("Movie Management Page");
		this.hp = hp;
		this.movies = movies;
		initialization();
		

		idLabel = new JLabel("ID");
		idLabel.setBounds(50,10,150,30);
		idLabel.setFont(font);
		idLabel.setForeground(Color.WHITE);
		panel.add(idLabel);
		
		idField = new JTextField();
		idField.setBounds(210,10,150,30);
		idField.setFont(font);
		panel.add(idField);

		movieLabel = new JLabel("Movie Name");
		movieLabel.setBounds(50,50,150,30);
		movieLabel.setFont(font);
		movieLabel.setForeground(Color.WHITE);
		panel.add(movieLabel);

		movieField = new JTextField();
		movieField.setBounds(210,50,150,30);
		movieField.setFont(font);
		panel.add(movieField);


		seatsLabel = new JLabel("Seats Available");
		seatsLabel.setBounds(390,10,190,30);
		seatsLabel.setFont(font);
		seatsLabel.setForeground(Color.WHITE);
		panel.add(seatsLabel);

		seatsField = new JTextField();
		seatsField.setBounds(590,10,150,30);
		seatsField.setFont(font);
		panel.add(seatsField);

		priceLabel = new JLabel("Ticket Price");
		priceLabel.setBounds(390,50,150,30);
		priceLabel.setFont(font);
		priceLabel.setForeground(Color.WHITE);
		panel.add(priceLabel);

		priceField = new JTextField();
		priceField.setBounds(590,50,150,30);
		priceField.setFont(font);
		panel.add(priceField);

		
		createTable();
		addBtn = createButton(770,10,150,25,"ADD");
		addBtn.setBackground(Color.GREEN);
		
		deleteBtn = createButton(770,45,150,25,"DELETE");
		deleteBtn.setBackground(Color.RED);
		

		
		
		backBtn = createButton(840,430,130,30,"<- Back");
		backBtn.setBackground(Color.BLACK);
		
		
		this.setVisible(true);
	}
	public void initialization(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000,500);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.setIconImage(new ImageIcon("./Assets/p.jpg").getImage());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(1000,500);
		panel.setOpaque(false);
		
		this.add(panel);
		
		ImageIcon image = new ImageIcon("./Assets/HomePage.jpg");
		JLabel background = new JLabel();
		background.setBounds(0,0,1000,500);
		background.setIcon(image);
		this.add(background);
		
	}

	public JButton createButton(int x,int y,int w,int h, String text){
		JButton btn = new JButton(text);
		btn.setBounds(x, y, w,h);
		btn.setFont(bfont);
		btn.addActionListener(this);
		btn.setBackground(Color.BLACK);
		btn.setForeground(Color.WHITE);
		panel.add(btn);
		return btn;
	}
	
	public void createTable(){
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Movie");
		model.addColumn("Seats Available");
		model.addColumn("Ticket Price");

		table = new JTable(model);
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setBounds(0, 0, 800, 400);
		table.setRowHeight(30);
		table.setBackground(new Color(174,247,255));
		table.setSelectionBackground(new Color(200, 200, 230));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);

		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(50,110,875,300);
		
		ArrayList <Movie> allMovies =  movies.getAll();
		for(int i=0;i<allMovies.size();i++){
			Movie c = allMovies.get(i);
			model.addRow(new Object[]{c.getID(),c.getMovieName(), c.getSeatsAvailable(),c.getTicketPrice()});
		}
		
		
		panel.add(scrollpane);
	}
		public void updateFile(){
		int rows = model.getRowCount();
			String allLines = "";
			for(int i= 0; i < rows; i++){
				String id = table.getModel().getValueAt(i,0).toString();
				String movieName = table.getModel().getValueAt(i,1).toString();
				String seatsAvailable = table.getModel().getValueAt(i,2).toString();
				String ticketPrice = table.getModel().getValueAt(i,3).toString();
				String line;
				if(i<rows-1){
					 line = id+";"+movieName+";"+seatsAvailable+";"+ticketPrice+"\n";
				}
				else{
					 line = id+";"+movieName+";"+seatsAvailable+";"+ticketPrice;
				}
				
				allLines += line;
			}
			FileIO.writeInFile(allLines,"./File/movies.txt",false);
	}

	public void actionPerformed(ActionEvent e){
		if(addBtn == e.getSource()){
			String id =  idField.getText();
			String movieName = movieField.getText();
			String seatsAvailable = seatsField.getText();
			String ticketPrice = priceField.getText();
			if(!id.isEmpty() && !movieName.isEmpty() && !seatsAvailable.isEmpty() && !ticketPrice.isEmpty()){
				Movie c = new Movie(Integer.parseInt(id),movieName,Integer.parseInt(seatsAvailable),Double.parseDouble(ticketPrice));
				movies.insert(c);
			model.addRow(new Object[]{c.getID(),c.getMovieName(), c.getSeatsAvailable(),c.getTicketPrice()});
				idField.setText("");
				movieField.setText("");
				seatsField.setText("");
				priceField.setText("");
				updateFile();
			}
			else{
				JOptionPane.showMessageDialog(this,"Please Enter All Details","Data Entry Warning",JOptionPane.WARNING_MESSAGE );
			}
		}
		else if(deleteBtn == e.getSource()){
			int rows[] = table.getSelectedRows();
			if(rows!=null){
				Arrays.sort(rows);
				for(int i= rows.length-1; i>=0;i--){
						movies.removeByID( Integer.parseInt(table.getModel().
									getValueAt(rows[i],0).toString() )  );
						model.removeRow(rows[i]);
						
				}
			}
			updateFile();
		}

		else if(backBtn == e.getSource()){
			hp.setVisible(true);
			this.dispose();
		}
		else{}
	}

}