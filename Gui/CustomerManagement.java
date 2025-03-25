package Gui;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import Entity.*;
import EntityList.*;
import java.util.Arrays;
import java.util.ArrayList;
import File.FileIO;

public class CustomerManagement extends JFrame implements ActionListener{
    JPanel panel;
    JButton addBtn,deleteBtn,backBtn,updateBtn;
    JLabel idLabel,nameLabel,ticketsCountLabel,totalSpentLabel;
    JTextField idField,nameField,ticketsCountField,totalSpentField;

    JTable table;
    DefaultTableModel model;

    Font font = new Font("arial",Font.BOLD,25);
    Font bfont = new Font("arial",Font.BOLD,25);
    Homepage hp;
    CustomerList customers;
    public CustomerManagement(Homepage hp, CustomerList customers){
        super("Customer Management Page");
        this.hp = hp;
        this.customers = customers;
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

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(50,50,150,30);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(210,50,150,30);
        nameField.setFont(font);
        panel.add(nameField);

        totalSpentLabel = new JLabel("Ticket Price");
        totalSpentLabel.setBounds(390,50,190,30);
        totalSpentLabel.setFont(font);
        totalSpentLabel.setForeground(Color.WHITE);
        panel.add(totalSpentLabel);

        totalSpentField = new JTextField();
        totalSpentField.setBounds(590,50,150,30);
        totalSpentField.setFont(font);
        panel.add(totalSpentField);

        ticketsCountLabel = new JLabel("Tickets Count");
        ticketsCountLabel.setBounds(390,10,165,35);
        ticketsCountLabel.setFont(font);
        ticketsCountLabel.setForeground(Color.WHITE);
        panel.add(ticketsCountLabel);

        ticketsCountField = new JTextField();
        ticketsCountField.setBounds(590,10,150,30);
        ticketsCountField.setFont(font);
        panel.add(ticketsCountField);


        createTable();
        addBtn = createButton(770,10,150,25,"ADD");
        addBtn.setBackground(Color.GREEN);

        deleteBtn = createButton(770,45,150,25,"DELETE");
        deleteBtn.setBackground(Color.RED);

        updateBtn = createButton(770,80,150,25,"UPDATE");
        updateBtn.setBackground(Color.PINK);

        backBtn = createButton(840,420,130,30,"<- Back");
        backBtn.setBackground(Color.BLACK);


        this.setVisible(true);
    }
    public void initialization(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,500);
        this.setLocation(500,250);
        this.setLayout(null);

        this.setIconImage(new ImageIcon("./Assets/Icon.jpg").getImage());

        panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(1000,500);
        panel.setOpaque(false);

        this.add(panel);

        ImageIcon image = new ImageIcon("./Assets/background32.jpg");
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
        model.addColumn("Name");
        model.addColumn("Tickets Count");
        model.addColumn("Total Spent");

        table = new JTable(model);
        table.setFont(font);
        table.getTableHeader().setFont(font);
        table.setBounds(0, 0, 800, 400);
        table.setRowHeight(30);
        table.setBackground(new Color(199, 22, 22));
        table.setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(200, 200, 230));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);

        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setBounds(50,110,875,300);

        ArrayList <Customer> allCustomers =  customers.getAll();
        for(int i=0;i<allCustomers.size();i++){
            Customer c = allCustomers.get(i);
            model.addRow(new Object[]{c.getID(),c.getName(), c.getTicketsBought(),c.getTotalSpent()});
        }


        panel.add(scrollpane);
    }

    public void updateCustomerFile(){
        int rows = model.getRowCount();
        String allLines = "";
        for(int i= 0; i < rows; i++){
            String id = table.getModel().getValueAt(i,0).toString();
            String customerName = table.getModel().getValueAt(i,1).toString();
            String ticketsBought = table.getModel().getValueAt(i,2).toString();
            String totalPrice = table.getModel().getValueAt(i,3).toString();
            String line;
            if(i<rows-1){
                line = id+";"+customerName+";"+ticketsBought+";"+totalPrice+"\n";
            }
            else{
                line = id+";"+customerName+";"+ticketsBought+";"+totalPrice;
            }

            allLines += line;
        }
        FileIO.writeInFile(allLines,"./File/customers.txt",false);
    }

    public void actionPerformed(ActionEvent e){
        if(addBtn == e.getSource()){
            String id =  idField.getText();
            String customerName = nameField.getText();
            String ticketsBought = ticketsCountField.getText();
            String totalSpent = totalSpentField.getText();

            if(!id.isEmpty() && !customerName.isEmpty() && !totalSpent.isEmpty() && !ticketsBought.isEmpty()){
                double multipliedTotal = Double.parseDouble(totalSpent) * Double.parseDouble(ticketsBought);
                Customer c = new Customer(Integer.parseInt(id),customerName,Integer.parseInt(ticketsBought),multipliedTotal);
                customers.insert(c);
                model.addRow(new Object[]{c.getID(),c.getName(), c.getTicketsBought(),c.getTotalSpent()});
                idField.setText("");
                nameField.setText("");
                ticketsCountField.setText("");
                totalSpentField.setText("");
                updateCustomerFile();
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
                    customers.removeByID( Integer.parseInt(table.getModel().
                            getValueAt(rows[i],0).toString() )  );
                    model.removeRow(rows[i]);
                }
            }
            updateCustomerFile();
        }
        else if(backBtn == e.getSource()){
            hp.setVisible(true);
            this.dispose();
        }if(updateBtn == e.getSource()){
            updateCustomerFile();
        }

    }


}