import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import net.miginfocom.swing.MigLayout;

public class enrolWindow extends JFrame{
	connectionWindow db;
	
	ImageIcon finalicDp;
	int id;
	JLabel lblDp;
	JTextField txtFN, txtMD, txtLN, txtA, txtPN, txtLRN;
	JComboBox cbS, cbStr;
	JDateChooser jcBd;
	ImageIcon icFinalDp;
	File selectedFile;
	public enrolWindow() {
		setLayout(new MigLayout("fill"));
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("center, fill", "push", "grow"));
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(1100, 720));
		panel.setVisible(true);
		
		Font f = new Font("Tahoma", Font.PLAIN, 20);
		
		JLabel lblE = new JLabel("ENROL STUDENT");
		lblE.setBackground(Color.white);
		lblE.setFont(new Font("Helvetica",Font.BOLD,30));

		
		JLabel lblFN = new JLabel("First Name: ");
		lblFN.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		txtFN = new JTextField(15);
		txtFN.setFont(f);
		
		JLabel lblMD = new JLabel("Middle Name: ");
		lblMD.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		txtMD = new JTextField(15);
		txtMD.setFont(f);
		
		JLabel lblLN = new JLabel("Last Name: ");
		lblLN.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		txtLN = new JTextField(15);
		txtLN.setFont(f);
		
		JLabel lblS = new JLabel("Sex: ");
		lblS.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		String sex[] = {"Male", "Female"};
		cbS = new JComboBox(sex);
		cbS.setSelectedIndex(-1);
		cbS.setFont(f);

		JLabel lblBd = new JLabel("Birthday: ");
		lblBd.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		jcBd = new JDateChooser();
		jcBd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jcBd.setDateFormatString("yyyy-MM-dd");
		
		JLabel lblA = new JLabel("Address: ");
		lblA.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		txtA = new JTextField(15);
		txtA.setFont(f);
		
		JLabel lblPN = new JLabel("Phone Number: ");
		lblPN.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		txtPN = new JTextField(15);
		txtPN.setFont(f);

		JLabel lblStr = new JLabel("Strand: ");
		lblStr.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		String strands[] = {"ICT", "Industrial Arts", "Home Economics", "Carpentry"};
		cbStr = new JComboBox(strands);
		cbStr.setSelectedIndex(-1);
		cbStr.setFont(f);
		
		JLabel lblLRN = new JLabel("Learner Reference Number (LRN)");
		lblLRN.setFont(new Font("Tahoma",Font.PLAIN,20));
		
		txtLRN = txtLRN = new JTextField(15);
		txtLRN.setFont(f);
		
		
		lblDp = new JLabel();
		ImageIcon icDp = new ImageIcon(getClass().getClassLoader().getResource("blank-profile.jpg"));
		java.awt.Image imgDp = icDp.getImage();
		java.awt.Image newimgDp = imgDp.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
		icFinalDp = new ImageIcon(newimgDp);
		lblDp.setIcon(icFinalDp);
		lblDp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		lblDp.setBackground(Color.blue);
		
		
		JButton btnIP = new JButton("Insert Picture");
		btnIP.setFont(new Font("Helvetica",Font.PLAIN, 15));
		btnIP.setForeground(Color.white);
		btnIP.setBackground(Color.darkGray);
		btnIP.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				JFileChooser file = new JFileChooser();
				file.setDialogTitle("Select Picture");
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
				
				file.addChoosableFileFilter(filter);
				int result = file.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = file.getSelectedFile();
					String path = selectedFile.getAbsolutePath();
					lblDp.setIcon(ResizeImage(path));
					if(selectedFile == null) {
						System.out.println("no pic");
						JOptionPane.showMessageDialog(null, "PLEASE SELECT A FILE!", "Error", JOptionPane.ERROR_MESSAGE); 
					}
				}
			}
			public ImageIcon ResizeImage(String ImagePath) {
				
				ImageIcon icDp = new ImageIcon(ImagePath);
				java.awt.Image imgDp = icDp.getImage();
				java.awt.Image newImgDp = imgDp.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
				finalicDp = new ImageIcon(newImgDp);
				return finalicDp;
			}
		});
		
		
		JButton btnEL = new JButton("Enrol");
		btnEL.setFont(new Font("Helvetica",Font.PLAIN, 15));
		btnEL.setForeground(Color.white);
		btnEL.setBackground(Color.darkGray);
		btnEL.setBorderPainted(false);
		btnEL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String value = "";
				int num = 0;
				if(txtFN.getText().equals("") || (txtLN.getText().equals("")) || (cbS.getSelectedItem().equals("")) || 
						(jcBd.getDate()==null) || (txtA.getText().equals("")) || (txtPN.getText().equals("")) || 
						cbStr.getSelectedItem().equals("")){
			
					//JOptionPane opWarn = new JOptionPane();
					JOptionPane.showMessageDialog(null, "Blank field/s", "Cannot Enrol", JOptionPane.WARNING_MESSAGE);
					
				}else if((txtMD.getText().equals(""))) {
					txtMD.setText(value);
					finalEnrolOutput();
					
				}else if((txtLRN.getText().equals(""))) {
					txtLRN.setText("" + num);
					finalEnrolOutput();
					
				}else if((txtMD.getText().equals("")) && txtLRN.getText().equals("")) {
					txtLRN.setText("" + num);
					txtMD.setText(value);
					finalEnrolOutput();
					
				}else{
					finalEnrolOutput();
					
				}
			}
		});
		JButton btnCl = new JButton("Clear");
		btnCl.setFont(new Font("Helvetica",Font.PLAIN,15));
		btnCl.setForeground(Color.white);
		btnCl.setBackground(Color.darkGray);
		btnCl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtFN.setText(""); txtLN.setText(""); txtMD.setText(""); cbS.setSelectedIndex(-1); jcBd.setCalendar(null); 
				txtA.setText(""); txtPN.setText(""); cbStr.setSelectedIndex(-1); txtLRN.setText(""); lblDp.setIcon(icFinalDp);
				
			}
		});
		ImageIcon ic = new ImageIcon(getClass().getClassLoader().getResource("system_icon.png")); 
		Image img = ic.getImage();
		
		this.setIconImage(img);
		this.getContentPane().setBackground(Color.WHITE);
		
		add(panel);
		
		panel.add(lblE, "cell 0 0, LEFT");
		panel.add(lblFN, "cell 0 1, gapy 20, width 150px, height 1px");
		panel.add(txtFN, "cell 0 1, growx, spanx, height 35px");
		panel.add(lblMD,"cell 0 2, width 150px, height 1px");
		panel.add(txtMD, "cell 0 2, growx, spanx, height 35px");
		panel.add(lblLN, "cell 0 3, width 150px, height 1px");
		panel.add(txtLN, "cell 0 3, growx, spanx, height 35px");
		panel.add(lblS, "cell 0 4, width 150px, height 1px");
		panel.add(cbS, "cell 0 4, growx, spanx, height 35px");
		panel.add(lblBd, "cell 0 5, width 150px, height 1px");
		panel.add(jcBd, "cell 0 5, growx, spanx, height 35px");
		panel.add(lblA, "cell 0 6, width 150px, height 1px");
		panel.add(txtA, "cell 0 6, growx, spanx, height 35px");
		panel.add(lblPN, "cell 0 7, width 150px, height 1px");
		panel.add(txtPN, "cell 0 7, growx, spanx, height 35px");
		panel.add(lblStr, "cell 0 8, width 150px, height 1px");
		panel.add(cbStr, "cell 0 8, growx, spanx, height 35px");
		panel.add(lblDp, "cell 1 1, center, span 0 4");
		panel.add(btnIP, "cell 1 5, center, width 200px");
		panel.add(lblLRN, "cell 1 6, center");
		panel.add(txtLRN, "cell 1 7, center");
		
		
		panel.add(btnEL, "cell 1 9, right, width 80px, height 30px");
		panel.add(btnCl, "cell 1 9, right, gapx 20 , width 80px, height 30px");
		
	}
	private void enrolButtonActionPerformed(String first_name, String last_name, String middle_name, 
			Object sex, java.util.Date birth_date, String address, int phone_number, Object strand, int lrn) {
	String query = "INSERT INTO students_info(first_name, last_name, middle_name, sex, birth_date, "
			+ "address, phone_number, strand, lrn) VALUES (?,?,?,?,?,?,?,?,?)";
	connectionWindow db = new connectionWindow();
	
	try {
		db.ps = db.conn.prepareStatement(query);
		db.ps.setString(1,  first_name);
		db.ps.setString(2,  last_name);
		db.ps.setString(3,  middle_name);
		db.ps.setString(4,  (String) sex);
		db.ps.setDate(5, (Date) birth_date);
		db.ps.setString(6,  address);
		db.ps.setInt(7,  phone_number);
		db.ps.setString(8, (String) strand);
		db.ps.setInt(9, lrn);

		db.ps.execute();
		
		JOptionPane op = new JOptionPane();
		op.showMessageDialog(this, "Enrolled");
		
		} catch(Exception e) {
		System.out.println(e);
		}
    }
	private void getLastId() {		
		String query = "Select MAX(students_id) from students_info";
		db = new connectionWindow();
		
		try {
			db.statement = db.conn.createStatement();
			db.rs = db.statement.executeQuery(query);
			
			if(db.rs.next()) {
				id = db.rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void imageSave() throws IOException {
		if(selectedFile == null) {
			System.out.println("no pic");
			//JOptionPane.showMessageDialog(null, "PLEASE SELECT 1 FILE!", "Error", JOptionPane.ERROR_MESSAGE); 
		}else {
			Image img = finalicDp.getImage();

			BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(img, 0, 0, null);
			g2.dispose();
			ImageIO.write(bi, "jpg", new File("D:\\retrieve_pictures\\student_pictures" + id + ".jpg"));
	}
		//System.out.println(id);
	}
	private void finalEnrolOutput() {
		enrolButtonActionPerformed(txtFN.getText(), txtLN.getText(), txtMD.getText(), cbS.getSelectedItem(), 
				(new java.sql.Date(jcBd.getDate().getTime())), txtA.getText(), Integer.parseInt(txtPN.getText()), 
				cbStr.getSelectedItem(), Integer.parseInt(txtLRN.getText()));
		txtFN.setText(""); txtLN.setText(""); txtMD.setText(""); cbS.setSelectedIndex(-1); jcBd.setCalendar(null);
		txtA.setText(""); txtPN.setText(""); cbStr.setSelectedIndex(-1); txtLRN.setText("");
		
		getLastId();

			try {
				imageSave();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			lblDp.setIcon(icFinalDp);

	}
}
