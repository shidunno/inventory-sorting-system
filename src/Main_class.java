import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.ui.FlatDropShadowBorder;
	
	public class Main_class extends JFrame implements ActionListener {
		
		JTextField un, pass;
		JButton loginn, panel1, panel2, panel3;
		String origusername = "user";
		String origpasword = "admin";
		JTextArea terminal;
		JLabel login, password, signin, error;
		JScrollPane bar;
		Connection con;
		JPanel SecondPanel, MainPanel;
		
		 public Main_class (Connection con) {
			 	this.con = con;
			 	FlatLightLaf.setup();
		        this.setTitle("INVENTORY SORTING SYSTEM");
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setResizable(false);
				this.setSize(800, 600);
				
				ImageIcon logo = new ImageIcon("new.jpg");
				this.setIconImage(logo.getImage());
				this.getContentPane().setBackground(new Color(0x7A7D7D));
				
				/*JLabel icon = new JLabel(logo);
				icon.setBounds(21, 340, 150, 150);
				this.add(icon);*/				
				

				login();
				panels();
				error();
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(false);
				
				SecondPanel = new JPanel();
				SecondPanel.setBackground(new Color(255, 255, 255));
				SecondPanel.setBounds(290, 80, 400, 400);
				SecondPanel.setOpaque(false);
				SecondPanel.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
				this.add(SecondPanel);
					
					
				
				JPanel MainPanel = new JPanel();
				MainPanel.setBackground(new Color(0xD0CFCF));		
				MainPanel.setBounds(200, 0, 600, 600);
				this.add(MainPanel);
				
				this.setLayout(null);
				this.setVisible(true);
		    }
		 
		 void login () {
			 signin = new JLabel("Sign In");
			 login = new JLabel("Username ");
			 password = new JLabel("Password ");
			 
			 signin.setBounds(428, 80, 200, 100);
			 this.add(signin);
			 signin.setFont(new Font("Segoe UI",Font.BOLD,36));
			 signin.setForeground(new Color (0x294C60));
			 
			 login.setBounds(330, 180, 300, 40);
			 this.add(login);
			 login.setFont(new Font("Segoe UI",Font.BOLD,20));
			 login.setForeground(new Color (0x294C60));
			 
			 password.setBounds(330, 250, 300, 40);
			 this.add(password);
			 password.setFont(new Font("Segoe UI",Font.BOLD,20));
			 password.setForeground(new Color (0x294C60));
			 
			 un = new JTextField();
			 un.setBounds(330, 220, 300, 25);
			 un.addActionListener(this);
			 un.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
			 this.add(un);
			 
			 pass = new JTextField();
			 pass.setBounds(330, 290, 300, 25);
			 pass.addActionListener(this);
			 pass.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
			 this.add(pass);
			 
			 
			 loginn = new JButton("Login");
			 loginn.setBounds(387, 350, 200, 40);
			 loginn.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
			 loginn.setFont(new Font("Segoe UI", Font.BOLD, 18));
			 loginn.setBackground(new Color(0x294C60));
			 loginn.setForeground(Color.WHITE);
			 loginn.setFocusPainted(false);
			 loginn.setBorderPainted(false);
			 loginn.setOpaque(false);
			 loginn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			 loginn.addActionListener(this);
			 this.add(loginn);	
		 }
		 
		 void panels () {
			 panel1 = new JButton("REGISTER");
			 panel2 = new JButton ("UPDATE");
			 panel3 = new JButton ("CREDITS");
			 
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
		 
		 void error () {
			error = new JLabel("Wrong username & password!");
 	 		error.setBounds(480, 310, 300, 25);
 	 		this.add(error);
 	 		error.setFont(new Font("Segoe UI",Font.BOLD,10));
 	 		error.setForeground(new Color (0x660000));
 	 		error.setVisible(false);
 	 		this.add(error);
		 }
		 
		 public void actionPerformed(ActionEvent e) {
		     if (e.getSource() == loginn) {
		    	 String usernames = un.getText();
				 String passwords = pass.getText();

		    	 	if (!usernames.equals(origusername) || !passwords.equals(origpasword)) {
		    	 		error.setVisible(true);
		    	 		un.setText("");
		    	 		pass.setText("");
		    	 	} else {
		    	 		panel1.setVisible(true);
						panel2.setVisible(true);
						panel3.setVisible(true);
						login.setVisible(false);
						password.setVisible(false);
						un.setVisible(false);
						pass.setVisible(false);
						loginn.setVisible(false);
						signin.setVisible(false);
						SecondPanel.setVisible(false);
						error.setVisible(false);
		    	 	}
		     }
		     
		     if (e.getSource() == panel1) {
		    	this.setVisible(false);
		    	frame tab1 = new frame(con);
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
		 
		 
	    public static void main(String[] args) throws ClassNotFoundException, SQLException {
	    	
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      
	      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_sorting_system", "root", "root");
	      System.out.print("connected........");
	      new Main_class(con);
	    }
	    
	   
	}