import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import net.miginfocom.swing.MigLayout;

public class homeWindow extends JFrame{
	
	JTable tblinfo;
	String data[];
	DefaultTableModel model;
	connectionWindow db;
	String id, firstName, lastName, middleName, sex, address, strand, bdate;
	Date birthDate;
	int phoneNumber, lrn, j;
	JDateChooser jcBd; 
	
	public homeWindow() {
		//debug
		setLayout(new MigLayout("fill"));//fill, insets 20 20 20 20
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("center, fill", "push", "grow"));
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(1100, 720));
		panel.setVisible(true);
			
		JLabel lblheader = new JLabel("STUDENTS' INFORMATION");
		lblheader.setFont(new Font("Helvetica",Font.BOLD,30));
		
		///
		Font f = new Font("Tahoma", Font.PLAIN, 15);
		JTextField txtSearch = new JTextField(10);
		txtSearch.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtSearch.setFont(f);

		JLabel lblSrch = new JLabel("Search: ");
		lblSrch.setFont(f);
		
		///
		JButton btnEdit = new JButton();
		ImageIcon icEd = new ImageIcon(getClass().getClassLoader().getResource("edit.png"));
		java.awt.Image imgEd = icEd.getImage();
		java.awt.Image newimgEd = imgEd.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		icEd = new ImageIcon(newimgEd);
		btnEdit.setIcon(icEd);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setBorderPainted(false);
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				j = tblinfo.getSelectedRow();
				if (j >= 0) {
					lrn = Integer.parseInt(model.getValueAt(j, 0).toString());
					firstName = model.getValueAt(j, 1).toString();
					lastName = model.getValueAt(j, 2).toString();
					middleName = model.getValueAt(j, 3).toString();
					sex = model.getValueAt(j, 4).toString();
					bdate = model.getValueAt(j, 5).toString();
					try {
						birthDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(j, 5));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					address = model.getValueAt(j, 6).toString();
					phoneNumber = Integer.parseInt(model.getValueAt(j, 7).toString());
					strand = model.getValueAt(j, 8).toString();
					id = model.getValueAt(j, 9).toString();
					
					editStudents();
					} 				
				}
			});
		
		JButton btnDel = new JButton();
		ImageIcon icDel = new ImageIcon(getClass().getClassLoader().getResource("delete.png"));
		java.awt.Image imgDel = icDel.getImage();
		java.awt.Image newimgDel = imgDel.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH);
		icDel = new ImageIcon(newimgDel);
		btnDel.setIcon(icDel);
		btnDel.setContentAreaFilled(false);
		btnDel.setBorderPainted(false);
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int i = tblinfo.getSelectedRow();
				if (i >= 0) {
					int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						String ivalue = tblinfo.getModel().getValueAt(i, 9).toString();
						model.removeRow(i);
						delStudents(Integer.parseInt(ivalue));		
					}
			}
		}});	
		
		Font ft = new Font("Tahoma", Font.PLAIN, 18);
		
		String[] header = {"LRN","First Name", "Last Name", "Middle Name", "Sex", "Birthday", "Address", "Phone Number", "Strand", "ID #"};
		
		tblinfo = new JTable();
		tblinfo.setRowHeight(40);
		tblinfo.setFont(f);
		tblinfo.getTableHeader().setFont(ft);
		tblinfo.getTableHeader().setBackground(Color.DARK_GRAY);
		tblinfo.getTableHeader().setForeground(Color.WHITE);
		
		model = new DefaultTableModel(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tblinfo.setModel(model);
		model.setColumnIdentifiers(header);
		getStudents();
		
		TableRowSorter sorter = new TableRowSorter<>(model);
	     //table = new JTable(model);
		tblinfo.setRowSorter(sorter);
		
		tblinfo.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblinfo.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblinfo.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblinfo.getColumnModel().getColumn(3).setPreferredWidth(150);
		tblinfo.getColumnModel().getColumn(4).setPreferredWidth(80);
		tblinfo.getColumnModel().getColumn(5).setPreferredWidth(100);
		tblinfo.getColumnModel().getColumn(6).setPreferredWidth(300);
		tblinfo.getColumnModel().getColumn(7).setPreferredWidth(150);
		tblinfo.getColumnModel().getColumn(8).setPreferredWidth(150);
		tblinfo.getColumnModel().getColumn(9).setPreferredWidth(50);
		
		tblinfo.setAutoResizeMode(tblinfo.AUTO_RESIZE_OFF);
		
		JScrollPane pane = new JScrollPane(tblinfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = Color.DARK_GRAY;
		    }
		});
		pane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = Color.DARK_GRAY;
		    }
		});
		pane.getViewport().setBackground(Color.BLACK);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		tblinfo.setDefaultRenderer(Object.class, dtcr);
		((DefaultTableCellRenderer) tblinfo.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		tblinfo.setFillsViewportHeight(true);
		tblinfo.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent me) {
				 JTable table =(JTable) me.getSource();
				 	
				 	j = tblinfo.getSelectedRow();
					if (j >= 0) {
						lrn = Integer.parseInt(model.getValueAt(j, 0).toString());
						firstName = model.getValueAt(j, 1).toString();
						lastName = model.getValueAt(j, 2).toString();
						middleName = model.getValueAt(j, 3).toString();
						sex = model.getValueAt(j, 4).toString();
						bdate = model.getValueAt(j, 5).toString();
						try {
							birthDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(j, 5));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						address = model.getValueAt(j, 6).toString();
						phoneNumber = Integer.parseInt(model.getValueAt(j, 7).toString());
						strand = model.getValueAt(j, 8).toString();
						id = model.getValueAt(j, 9).toString();
						
						if (me.getClickCount() == 2 && table.getSelectedRow() != -1) {
							showInfo();
						}
						
					} 				
				}
			});
		
		
		
		
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txtSearch.getText().equals("")) {
					getStudents();
				}else {	
					Search(txtSearch.getText());
				} 
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txtSearch.getText().equals("")) {
					getStudents();
				}else {
					Search(txtSearch.getText());
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(txtSearch.getText().equals("")) {
					getStudents();
				}else {
					Search(txtSearch.getText());
				}
			}
		});
		ImageIcon ic = new ImageIcon(getClass().getClassLoader().getResource("system_icon.png")); 
		Image img = ic.getImage();
		
		this.setIconImage(img);
		this.getContentPane().setBackground(Color.WHITE);
		
		add(panel);
		
		panel.add(lblheader, "cell 0 0");
		panel.add(lblSrch, "cell 0 1, gapy 10, right");
		panel.add(txtSearch, "cell 0 1, gapy 10 , right, width 150px, height 30px");
		panel.add(btnEdit, "cell 0 2, gapy 20, right");
		panel.add(btnDel, "cell 0 2, gapy 20, right");
		panel.add(pane, "cell 0 3, gapy 10, center, grow, span");
		
		
	}
	 private void Search(String search) {
		model.getDataVector().clear();
		tblinfo.revalidate();
		tblinfo.repaint();
			
			String query = "Select * from students_info WHERE first_name = '" + search + "' " + 
			"OR last_name = '" + search + "' " + 
			"OR middle_name = '" + search + "' " + 
			"OR sex = '" + search + "' " + 
			"OR address = '" + search + "' " + 
			"OR phone_number = '" + search + "' " + 
			"OR strand = '" + search + "' " + 
			"OR lrn = '" + search + "' " + ";";
			
			db = new connectionWindow();
			try {
		
				db.rs = db.statement.executeQuery(query);
				
				
				while(db.rs.next()) {
				String[] data = {db.rs.getString("lrn"),db.rs.getString("first_name"),db.rs.getString("last_name"),
						db.rs.getString("middle_name"),db.rs.getString("sex"), db.rs.getString("birth_date"),db.rs.getString("address"), 
						db.rs.getString("phone_number"), db.rs.getString("strand"), db.rs.getString("students_id")};
				model.addRow(data);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	private void getStudents() {
		model.getDataVector().clear();
		tblinfo.revalidate();
		
		String query = "Select * from students_info";
		db = new connectionWindow();
		
		try {
			db.statement = db.conn.createStatement();
			db.rs = db.statement.executeQuery(query);
			
			while(db.rs.next()) {
			String[] data = {db.rs.getString("lrn"), db.rs.getString("first_name"),db.rs.getString("last_name"),
					db.rs.getString("middle_name"),db.rs.getString("sex"), db.rs.getString("birth_date"),db.rs.getString("address"), 
					db.rs.getString("phone_number"), db.rs.getString("strand"), db.rs.getString("students_id")};
			model.addRow(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void delStudents(int id) {		
		String query = "DELETE FROM students_info WHERE students_id = ? ";
		db = new connectionWindow();
		
		try {
			db.ps = db.conn.prepareStatement(query);
			db.ps.setInt(1,  id);
			
			db.ps.executeUpdate();
		
			JOptionPane.showMessageDialog(this, "Deleted");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void editStudents() {		
		
		updateWindow uw = new updateWindow();
		uw.existingData(firstName, lastName, middleName, sex, birthDate, address, phoneNumber, strand, lrn, id);
		uw.setVisible(true);
		uw.pack();
	}
	public void showInfo() {		
	
		showInfoWindow siw = new showInfoWindow();
		siw.existingData(firstName, lastName, middleName, sex, bdate, address, phoneNumber, strand, lrn, id);
		siw.setVisible(true);
		siw.pack();
}
}



