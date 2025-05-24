import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;

public class frame2 extends JFrame implements ActionListener {
	Connection con;
	JPanel MainPanel;
	JButton panel1, panel2, panel3;
	
	frame2(Connection con) {
		this.con = con;
		FlatLightLaf.setup();
		
		this.setTitle("INVENTORY SORTING SYSTEM");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800, 600);
		
		ImageIcon logo = new ImageIcon("new.jpg");
		this.setIconImage(logo.getImage());
		this.getContentPane().setBackground(new Color(0x7A7D7D));
		
		me();
		
		MainPanel = new JPanel();
		MainPanel.setBackground(new Color(0xD0CFCF)); 
		MainPanel.setBounds(200, 0, 600, 600);  
		this.add(MainPanel);  
		
		panels(); 
		
		this.setLayout(null);
		this.setVisible(true);
	}
	
	void panels() {
		panel1 = new JButton("REGISTER");
		panel2 = new JButton("UPDATE");
		panel3 = new JButton("CREDITS");
		 
		panel1.setBounds(19, 80, 160, 40);
		panel1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel1.setBackground(new Color(0x294C60));
		panel1.setForeground(Color.WHITE);
		panel1.setFocusPainted(false);
		panel1.setBorderPainted(false);
		panel1.setOpaque(true);
		panel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel1.addActionListener(this);
		this.add(panel1);    
		
		panel2.setBounds(19, 160, 160, 40);
		panel2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel2.setBackground(new Color(0x294C60));
		panel2.setForeground(Color.WHITE);
		panel2.setFocusPainted(false);
		panel2.setBorderPainted(false);
		panel2.setOpaque(true);
		panel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel2.addActionListener(this);
		this.add(panel2);    
		
		panel3.setBounds(19, 240, 160, 40);
		panel3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel3.setBackground(new Color(0x294C60));
		panel3.setForeground(Color.WHITE);
		panel3.setFocusPainted(false);
		panel3.setBorderPainted(false);
		panel3.setOpaque(true);
		panel3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel3.addActionListener(this);
		this.add(panel3);    
	}
	
	void me() {
		JLabel name = new JLabel("Created by: Salcedo, Justine D.");
		JLabel section = new JLabel("Section: BSIT 1-5");
		
		name.setBounds(220, 110, 500, 40);
		this.add(name);
		name.setFont(new Font("Segoe UI",Font.BOLD,28));
		name.setForeground(new Color(0x294C60));
		 
		section.setBounds(220, 145, 300, 40);
		this.add(section);
		section.setFont(new Font("Segoe UI",Font.BOLD,28));
		section.setForeground(new Color(0x294C60));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panel1) {
			this.setVisible(false);
			new frame(con);
		}
		if (e.getSource() == panel2) {
			this.setVisible(false);
			new frame1(con);
		}
	}
}