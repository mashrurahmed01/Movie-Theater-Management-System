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



public class EmployeManagement extends JFrame implements ActionListener{
	JPanel panel;
	JButton addBtn,deleteBtn,backBtn,updateBtn;
	JLabel idLabel,employename,designationLabel,salaryLabel;
	JTextField idField,employeField,designationField,salaryField;
	
	JTable table;
	DefaultTableModel model;

 	Font font = new Font("arial",Font.BOLD,25);
 	Font bfont = new Font("arial",Font.BOLD,25);
	Homepage hp;
	EmployeList employes;

	public EmployeManagement(Homepage hp,EmployeList employes){
		super("Employe Management Page");
		this.hp = hp;
		this.employes = employes;
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

		employename = new JLabel("Name");
		employename.setBounds(50,50,150,30);
		employename.setFont(font);
		employename.setForeground(Color.WHITE);
		panel.add(employename);

		employeField = new JTextField();
		employeField.setBounds(210,50,150,30);
		employeField.setFont(font);
		panel.add(employeField);


		salaryLabel = new JLabel("Salary");
		salaryLabel.setBounds(390,10,190,30);
		salaryLabel.setFont(font);
		salaryLabel.setForeground(Color.WHITE);
		panel.add(salaryLabel);

		salaryField = new JTextField();
		salaryField.setBounds(590,10,150,30);
		salaryField.setFont(font);
		panel.add(salaryField);

		designationLabel = new JLabel("Designation");
		designationLabel.setBounds(390,50,150,30);
		designationLabel.setFont(font);
		designationLabel.setForeground(Color.WHITE);
		panel.add(designationLabel);

		designationField = new JTextField();
		designationField.setBounds(590,50,150,30);
		designationField.setFont(font);
		panel.add(designationField);

		
		createTable();
		addBtn = createButton(770,10,150,25,"ADD");
		addBtn.setBackground(Color.GREEN);
		
		deleteBtn = createButton(770,45,150,25,"DELETE");
		deleteBtn.setBackground(Color.RED);

		updateBtn = createButton(770,80,150,25,"UPDATE");
		updateBtn.setBackground(Color.BLUE);

		
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
		
		ImageIcon image = new ImageIcon("./Assets/Employe.jpg");
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
		model.addColumn("Designation");
		model.addColumn("Salary");

		table = new JTable(model);
		table.setFont(font);
		table.getTableHeader().setFont(font);
		table.setBounds(0, 0, 800, 400);
		table.setRowHeight(30);
		table.setBackground(new Color(242, 200, 75));
		table.setSelectionBackground(new Color(200, 200, 230));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);

		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setBounds(50,110,875,300);
		
		ArrayList <Employe> allemployes =  employes.getAll();
		for(int i=0;i<allemployes.size();i++){
			Employe c = allemployes.get(i);
			model.addRow(new Object[]{c.getID(),c.getName(),c.getdesignation(), c.getsalary()});
		}
		
		
		panel.add(scrollpane);
	}
		public void updateFile(){
		int rows = model.getRowCount();
			String allLines = "";
			for(int i= 0; i < rows; i++){
				String id = table.getModel().getValueAt(i,0).toString();
				String employeName = table.getModel().getValueAt(i,1).toString();
				String ticketdesignation = table.getModel().getValueAt(i,2).toString();
				String salaryAvailable = table.getModel().getValueAt(i,3).toString();
				String line;
				if(i<rows-1){
					 line = id+";"+employeName+";"+ticketdesignation+";"+salaryAvailable+"\n";
				}
				else{
					line = id+";"+employeName+";"+ticketdesignation+";"+salaryAvailable;
				}

				allLines += line;
			}
			FileIO.writeInFile(allLines,"./File/employes.txt",false);
	}

	public void actionPerformed(ActionEvent e){
		if(addBtn == e.getSource()){
			String id =  idField.getText();
			String employeName = employeField.getText();
			String ticketdesignation = designationField.getText();
			String salaryAvailable = salaryField.getText();
			if(!id.isEmpty() && !employeName.isEmpty() && !ticketdesignation.isEmpty()&& !salaryAvailable.isEmpty()){
				Employe c = new Employe(Integer.parseInt(id),employeName,ticketdesignation,Double.parseDouble(salaryAvailable));
				employes.insert(c);
			model.addRow(new Object[]{c.getID(),c.getName(),c.getdesignation(),c.getsalary()});
				idField.setText("");
				employeField.setText("");
				designationField.setText("");
				salaryField.setText("");
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
						employes.removeByID( Integer.parseInt(table.getModel().
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
		if(updateBtn == e.getSource()){
			updateFile();
		}
	}

}