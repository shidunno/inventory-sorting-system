import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.sql.Connection;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;

public class frame extends JFrame implements ActionListener {
	JTextField searchbox, searchbox1, searchbox2;
	JComboBox variety;
	JTextArea terminal;
	JButton save, panel1, panel2, panel3;
	Connection con;

	frame(Connection con) {
		this.con = con;
		FlatLightLaf.setup();
		
		this.setTitle("INVENTORY SORTING SYSTEM");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800, 600);
		
		ImageIcon logo = new ImageIcon("new.jpg");
		this.setIconImage(logo.getImage());
		this.getContentPane().setBackground(new Color(0x7A7D7D));
		
		title();
		panels();
		name();
		price();
		stock();		
		category();
		save();
		terminal();
		
		JPanel MainPanel = new JPanel();
		MainPanel.setBackground(new Color(0xD0CFCF));		
		MainPanel.setBounds(200, 0, 600, 600);
		this.add(MainPanel);
				
		this.setLayout(null);
		this.setVisible(true);
	}
	
	void panels() {
		panel1 = new JButton("REGISTER");
		panel2 = new JButton("UPDATE");
		panel3 = new JButton("CREDITS");
		
		panel1.setBounds(19, 80, 160, 40);
		panel1.setFont(new Font("SansSerif", Font.BOLD, 16));
		panel1.setBackground(new Color(0x294C60));
		panel1.setForeground(Color.WHITE);
		panel1.setFocusPainted(false);
		panel1.setBorderPainted(false);
		panel1.setOpaque(true);
		panel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel1.addActionListener(this);
		this.add(panel1);	
		
		panel2.setBounds(19, 160, 160, 40);
		panel2.setFont(new Font("SansSerif", Font.BOLD, 16));
		panel2.setBackground(new Color(0x294C60));
		panel2.setForeground(Color.WHITE);
		panel2.setFocusPainted(false);
		panel2.setBorderPainted(false);
		panel2.setOpaque(true);
		panel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel2.addActionListener(this);
		this.add(panel2);	
		
		panel3.setBounds(19, 240, 160, 40);
		panel3.setFont(new Font("SansSerif", Font.BOLD, 16));
		panel3.setBackground(new Color(0x294C60));
		panel3.setForeground(Color.WHITE);
		panel3.setFocusPainted(false);
		panel3.setBorderPainted(false);
		panel3.setOpaque(true);
		panel3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel3.addActionListener(this);
		this.add(panel3);	
	}
	
	void title() {
		JLabel title = new JLabel("REGISTER PRODUCT ");
		title.setBounds(225, 20, 500, 100);
		this.add(title);
		title.setFont(new Font("Segoe UI",Font.BOLD,38));
		title.setForeground(new Color(0x294C60));
	}
	
	void name() {
		JLabel name = new JLabel("Name: ");
		name.setBounds(225, 103, 300, 40);
		this.add(name);
		name.setFont(new Font("SansSerif",Font.BOLD,25));
		name.setForeground(new Color(0x294C60));
		
		searchbox = new JTextField();
		searchbox.setBounds(420, 113, 300, 25);
		searchbox.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		searchbox.addActionListener(this);
		this.add(searchbox);
	}
	
	void price() {
		JLabel price = new JLabel("Price: ");
		price.setBounds(225, 135, 300, 40);
		this.add(price);
		price.setFont(new Font("SansSerif",Font.BOLD,25));
		price.setForeground(new Color(0x294C60));
		
		searchbox1 = new JTextField();
		searchbox1.setBounds(420, 146, 300, 25);
		searchbox1.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		searchbox1.addActionListener(this);
		this.add(searchbox1);
	}
	
	void stock() {
		JLabel stock = new JLabel("Starting Stock: ");	
		stock.setBounds(225, 165, 300, 40);
		this.add(stock);
		stock.setFont(new Font("SansSerif",Font.BOLD,25));
		stock.setForeground(new Color(0x294C60));
		
		searchbox2 = new JTextField();
		searchbox2.setBounds(420, 176, 300, 25);
		searchbox2.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		searchbox2.addActionListener(this);
		this.add(searchbox2);
	}
	
	void category() {
		JLabel category = new JLabel("Category: ");	
		category.setBounds(225, 196, 300, 40);
		this.add(category);
		category.setFont(new Font("SansSerif",Font.BOLD,25));
		category.setForeground(new Color(0x294C60));
		
		String[] varieties = {"Laptop", "Mobile Phone", "Appliance"};
		variety = new JComboBox(varieties);	
		variety.setBounds(420, 210, 300, 25);
		variety.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		this.add(variety);		
	}
	
	void save() {
		save = new JButton("Register Product");
		save.setBounds(460, 260, 200, 40);
		save.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		save.setFont(new Font("Segoe UI", Font.BOLD, 18));
		save.setBackground(new Color(0x294C60));
		save.setForeground(Color.WHITE);
		save.setFocusPainted(false);
		save.setBorderPainted(false);
		save.setOpaque(false);
		save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		save.addActionListener(this);
		this.add(save);	
	}
	
	void terminal() {
		terminal = new JTextArea();
		terminal.setEditable(false);
		terminal.setFont(new Font("Monospaced", Font.BOLD, 15));
		
		JScrollPane bar = new JScrollPane(terminal);
		bar.setBounds(225, 320, 500, 200);
		this.add(bar);		
		bar.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		PrintStream printStream = new PrintStream(new OutputStream() {
			
		    @Override
		    public void write(int b) {
		        
		        terminal.append(String.valueOf((char) b));
		        terminal.setCaretPosition(terminal.getDocument().getLength()); 
		    }
		});
		System.setOut(printStream);
		System.setErr(printStream); 
	}
	
	 public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == save) {
	        	
	            try {
	                String name = searchbox.getText();
	                String price = searchbox1.getText();
	                String stock = searchbox2.getText();
	                String category = (String) variety.getSelectedItem();

	                String query = "INSERT INTO products (name, price, stock, category) VALUES (?, ?, ?, ?)";
	                PreparedStatement pst = con.prepareStatement(query);

	                pst.setString(1, name);
	                pst.setString(2, price);
	                pst.setString(3, stock);
	                pst.setString(4, category);

	                int rowsAffected = pst.executeUpdate();
	                System.out.println("Product registered successfully!!!\n\nName: "+name+"\nPrice: "+price+"\nStarting Stock: "+stock+"\nCategory: "+category);
	                searchbox.setText("");
		            searchbox1.setText("");
		            searchbox2.setText("");
	                
	            } catch (SQLException ex) {
		             System.out.println("\nError please enter valid data...");
		             searchbox.setText("");
			         searchbox1.setText("");
			         searchbox2.setText("");
	            }
	        }    
	        
	        if (e.getSource() == panel2) {
		 		this.setVisible(false);
		 		frame1 tab2 = new frame1(con);
	        	}
	        
	        if (e.getSource() == panel3) {
	        	this.setVisible(false);
	        	frame2 tab3 = new frame2(con);
	        }
	        		    
	        		   
	     }
	        
	    }
	

