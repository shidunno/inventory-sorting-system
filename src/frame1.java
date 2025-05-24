import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;

public class frame1 extends JFrame implements ActionListener {
	JComboBox variety;
	JTextField searchbox1, searchbox2, searchbox3, searchbox4;
	JTextArea terminal;
	Connection con;
	JButton save, panel1, panel2, panel3;
	JTable tablee;
	JScrollPane terminalbar, tablebar;	
	JPanel MainPanel;
	JRadioButton rb;
	
	frame1(Connection con) {
		this.con = con;
		FlatLightLaf.setup();
		
		this.setTitle("INVENTORY SORTING SYSTEM");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(800, 600);
		
		ImageIcon logo = new ImageIcon("new.jpg");
		this.setIconImage(logo.getImage());
		this.getContentPane().setBackground(new Color(0x7A7D7D));
		
		panels();
		id();
		quantity();
		editprice();
		terminal();
		save();
		delete();
		category();
		table();
		search();
		
		MainPanel = new JPanel();
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
	
	void category() {
		String[] varieties = {"Laptop", "Mobile Phone", "Appliance"};
		variety = new JComboBox(varieties);	
		variety.setBounds(230, 30, 150, 25);
		variety.addActionListener(this);
		variety.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		this.add(variety);		
	}
	
	void id() {
		JLabel id = new JLabel("Product ID: ");
		id.setBounds(230, 350, 300, 40);
		this.add(id);	
		id.setFont(new Font("Segoe UI",Font.BOLD,25));
		id.setForeground(new Color (0x294C60));
		
		searchbox1 = new JTextField();
		searchbox1.setBounds(425, 360, 300, 25);
		searchbox1.addActionListener(this);
		searchbox1.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		this.add(searchbox1);
	}
	
	void quantity() {
		JLabel quantity = new JLabel("Updated Stock: ");
		quantity.setBounds(230, 385, 300, 40);
		this.add(quantity);	
		quantity.setFont(new Font("Segoe UI",Font.BOLD,25));
		quantity.setForeground(new Color (0x294C60));
		
		searchbox2 = new JTextField();
		searchbox2.setBounds(425, 397, 150, 25);
		searchbox2.addActionListener(this);
		searchbox2.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		this.add(searchbox2);
	}
	
	void editprice() {
		JLabel delete = new JLabel("Edit Price: ");
		delete.setBounds(230, 420, 300, 40);
		this.add(delete);	
		delete.setFont(new Font("Segoe UI",Font.BOLD,25));
		delete.setForeground(new Color (0x294C60));
		
		searchbox3 = new JTextField();
		searchbox3.setBounds(425, 434, 300, 25);
		searchbox3.addActionListener(this);
		searchbox3.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		this.add(searchbox3);
	}
	
	void delete() {
		rb = new JRadioButton("Delete Item");
		rb.setBounds(585, 397, 150, 25); 
		rb.setBackground(new Color(0xD0CFCF));  
		rb.setFont(new Font("Segoe UI", Font.BOLD, 16));  
		rb.setForeground(new Color(0x294C60)); 
		rb.setFocusPainted(false);  
		rb.setBorderPainted(false);  
		rb.setOpaque(true);  
		this.add(rb);
		this.revalidate();
		this.repaint();
	}
	
	void terminal() {
		terminal = new JTextArea();
		terminal.setEditable(false);
		terminal.setFont(new Font("Monospaced", Font.BOLD, 15));
		
		terminalbar = new JScrollPane(terminal);
		terminalbar.setBounds(230, 470, 300, 76);
		terminalbar.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		this.add(terminalbar);		
		
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
	
	void save() {
		save = new JButton("Save Changes");
		save.setBounds(545, 480, 180, 60);
		save.setFont(new Font("Segoe UI", Font.BOLD, 20));
		save.setBackground(new Color(0x294C60));
		save.setForeground(Color.WHITE);
		save.setFocusPainted(false);
		save.setBorderPainted(false);
		save.setOpaque(false);
		save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		save.addActionListener(this);
		save.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		this.add(save);	
	}
	
	void table() {
	    String[] categories = {"ID", "Name", "Price", "Stock", "Category", "Created on"};

	    if (con == null) {
	        terminal.append("Error: No database connection\n");
	        return;
	    }

	    try {
	    	
	    	if (tablebar != null) {
	    	    this.remove(tablebar);
	    	    tablebar = null;
	    	}

	        String select = (String) variety.getSelectedItem();
	        String query = "SELECT * FROM products WHERE category = '" + select + "'";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        DefaultTableModel tables = new DefaultTableModel(categories, 0);
	        tables.setColumnIdentifiers(categories);
	        tablee = new JTable(tables);
	        
	        tablee.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
	            public void mouseMoved(java.awt.event.MouseEvent e) {
	                int row = tablee.rowAtPoint(e.getPoint());
	                int col = tablee.columnAtPoint(e.getPoint());

	                if (row >= 0 && col >= 0) {
	                    tablee.setToolTipText(String.valueOf(tablee.getValueAt(row, col)));
	                }
	            }
	        });
	        
	        tablebar = new JScrollPane(tablee);
	        tablebar.setBounds(230, 70, 540, 250);
	        tablebar.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
	        this.add(tablebar);
	        this.revalidate();
	        this.repaint();

	        while (rs.next()) {
	            Object data[] = {
	                    rs.getInt("id"),
	                    rs.getString("name"),
	                    rs.getString("price"),
	                    rs.getInt("stock"),
	                    rs.getString("category"),
	                    rs.getString("created_on")
	            };
	            tables.addRow(data);
	        }

	    } catch (SQLException e) {
	        terminal.append("Error: " + e.getMessage());
	    }
	}
	
	void search() {
		JLabel search = new JLabel("Search ");
		search.setBounds(460, 20, 80, 40);
		this.add(search);	
		search.setFont(new Font("Segoe UI",Font.BOLD,20));
		search.setForeground(new Color (0x294C60));
		
		searchbox4 = new JTextField();
		searchbox4.setBounds(530, 30, 200, 25);
		searchbox4.addActionListener(this);
		searchbox4.putClientProperty(FlatClientProperties.STYLE, "arc: 20");
		this.add(searchbox4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == variety) {
	        table();
	        this.add(MainPanel);
	    }
	    
	    if (e.getSource() == save) {
	        if (rb.isSelected()) {
	        	
	            try {  
	                int productId = Integer.parseInt(searchbox1.getText());

	                String query = "DELETE FROM products WHERE id = ?";
	                PreparedStatement stmt = con.prepareStatement(query);
	                stmt.setInt(1, productId);
	                int hit = stmt.executeUpdate();

	                if (hit > 0) {
	                    System.out.println("Item with ID " + productId + " was successfully deleted.");
	                    searchbox1.setText("");
                        searchbox2.setText("");
                        searchbox3.setText("");
	                } else {
	                    System.out.println("No item found with ID " + productId + ".");
	                    searchbox1.setText("");
                        searchbox2.setText("");
                        searchbox3.setText("");
	                }

	                table(); 

	            	} catch (NumberFormatException ex) {
	            		System.out.println("Invalid ID. Please enter a valid number.");
	            	} catch (SQLException ex) {
	            		ex.printStackTrace();
	            		System.out.println("Database error: " + ex.getMessage());
	            	}
	        	} else {
	        		
	        		try {
	        			int productId = Integer.parseInt(searchbox1.getText());
	        			int updatedstock = Integer.parseInt(searchbox2.getText());
	        			String updatedprice = searchbox3.getText();
	        			
	        			String query = "UPDATE products SET  stock = ?, price = ? WHERE id = ?";
	        			PreparedStatement stmt = con.prepareStatement(query);
	        			stmt.setInt(1, updatedstock);
	                    stmt.setString(2, updatedprice);
	                    stmt.setInt(3, productId);
	                    
	                    int rowsAffected = stmt.executeUpdate();
	                    
	                    if (rowsAffected > 0) {
	                        System.out.println("Product ID " + productId + " updated successfully!");
	                        System.out.println("New stock: " + updatedstock);
	                        System.out.println("New price: " + updatedprice);
	                        searchbox1.setText("");
	                        searchbox2.setText("");
	                        searchbox3.setText("");
	                    } else {
	                        System.out.println("No product found with ID " + productId);
	                        searchbox1.setText("");
	                        searchbox2.setText("");
	                        searchbox3.setText("");
	                    }
	        			
	                    table();

	            		} catch (NumberFormatException ex) {
                        System.out.println("Invalid input. Please enter valid numbers for ID and stock.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        System.out.println("Database error: " + ex.getMessage());
                    }//else end.......
	        	}
	    }
	    
	    if (e.getSource() == searchbox4) {
	    	String searchText = searchbox4.getText().trim();
	    	
	    	try {
				int productid = Integer.parseInt(searchbox4.getText());
				
				String query = "SELECT * FROM products WHERE id = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, productid);
				
				ResultSet rs = stmt.executeQuery();	
				DefaultTableModel model = (DefaultTableModel) tablee.getModel();
				model.setRowCount(0);  
			     
				 while (rs.next()) {
			            Object data[] = {
			                rs.getInt("id"),
			                rs.getString("name"),
			                rs.getString("price"),
			                rs.getInt("stock"),
			                rs.getString("category"),
			                rs.getString("created_on")
			            };
			            model.addRow(data); 
			        }
				 
				 if (model.getRowCount() == 0) {
			            System.out.println("No product found with ID " + productid);
			        }
				 
			 } catch (NumberFormatException e1) {
			        System.out.println("Please enter a valid product ID.");
			    } catch (SQLException e1) {
			        e1.printStackTrace();
			    }	 
	    	
	    }
	    
	    if (e.getSource() == panel1) {
	        this.setVisible(false);
	        frame tab1 = new frame(con);
	    }
	    
	    if (e.getSource() == panel3) {
	        this.setVisible(false);
	        frame2 tab3 = new frame2(con);
	    }
	}
}
