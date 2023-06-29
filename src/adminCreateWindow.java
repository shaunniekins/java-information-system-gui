import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

public class adminCreateWindow extends JFrame{
	
	int id;
	File selectedFile;
	BufferedImage bi, newbi;
	JFileChooser chooseFile, saveFile;
	
	public adminCreateWindow() {
		setLayout(new MigLayout("center"));
		
		JPanel pnlHead = new JPanel(new MigLayout("left"));
		pnlHead.setBackground(Color.WHITE);
		pnlHead.setVisible(true);
		pnlHead.setPreferredSize(new Dimension(500,20));
		
		JPanel pnlCen = new JPanel(new MigLayout("center, fillx, insets 20 20 20 20"));
		pnlCen.setBackground(Color.DARK_GRAY.darker());
		pnlCen.setVisible(true);
		pnlCen.setPreferredSize(new Dimension(450,720));
		pnlCen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
		Font f = new Font("Tahoma", Font.PLAIN, 18);
		Font f2 = new Font("Tahoma", Font.PLAIN, 15);
		
		JLabel lblE = new JLabel("Create Admin Account");
		lblE.setFont(new Font("Tahoma", Font.PLAIN, 20));

		
		JLabel lblFN = new JLabel("First Name");
		lblFN.setForeground(Color.WHITE);
		lblFN.setFont(f2);
		
		JTextField txtFN = new JTextField();
		txtFN.setFont(f);
				
		JLabel lblLN = new JLabel("Last Name");
		lblLN.setForeground(Color.WHITE);
		lblLN.setFont(f2);
		
		JTextField txtLN = new JTextField();
		txtLN.setFont(f);
		
		JLabel lblA = new JLabel("Address");
		lblA.setForeground(Color.WHITE);
		lblA.setFont(f2);
		
		JTextField txtA = new JTextField();
		txtA.setFont(f);
		
		JLabel lblPN = new JLabel("Phone Number");
		lblPN.setForeground(Color.WHITE);
		lblPN.setFont(f2);
		
		JTextField txtPN = new JTextField();
		txtPN.setFont(f);
		
		JLabel lblUser = new JLabel("Create Username");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(f2);
		
		JTextField txtUser = new JTextField();
		txtUser.setFont(f);
		
		JLabel lblPass = new JLabel("Create Password");
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(f2);
		
		JTextField txtPass = new JTextField();
		txtPass.setFont(f);
		
		JLabel lblCheck = new JLabel("Authorization Code");
		lblCheck.setForeground(Color.WHITE);
		lblCheck.setFont(f2);
		
		JTextField txtCheck = new JTextField();
		txtCheck.setForeground(Color.BLUE.brighter());
		txtCheck.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtCheck.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JLabel lblImgName = new JLabel("No file selected");
		lblImgName.setForeground(Color.WHITE);
		lblImgName.setFont(f2);
		
		chooseFile = new JFileChooser();
		chooseFile.setDialogTitle("Select Picture");
		chooseFile.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
		chooseFile.addChoosableFileFilter(filter);
		
		saveFile = new JFileChooser();
		saveFile.setDialogTitle("Select Picture");
		saveFile.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter1 = new FileNameExtensionFilter("*.Images", "jpg", "png");
		saveFile.addChoosableFileFilter(filter1);
		
		JButton btnImg = new JButton("Choose Picture");
		btnImg.setFont(new Font("Helvetica",Font.PLAIN, 15));
		btnImg.setForeground(Color.white);
		btnImg.setBackground(Color.darkGray);
		btnImg.setBorderPainted(false);
		btnImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				
				int result = chooseFile.showOpenDialog(null); 
				
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						bi = ImageIO.read(chooseFile.getSelectedFile());
						lblImgName.setText(chooseFile.getSelectedFile().getName());
						lblImgName.setForeground(Color.CYAN.darker());
						
					}catch (IOException ioe) {
						JOptionPane.showMessageDialog(null, "Failed to load image file", "Error", JOptionPane.ERROR_MESSAGE); 
					}
				}else {
					JOptionPane.showMessageDialog(null, "NO FILE CHOSEN!", "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		
		
		JButton btnCrt = new JButton("Create");
		btnCrt.setFont(new Font("Helvetica",Font.PLAIN, 15));
		btnCrt.setForeground(Color.white);
		btnCrt.setBackground(Color.darkGray);
		btnCrt.setBorderPainted(false);
		btnCrt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtFN.getText().equals("") || (txtLN.getText().equals("")) || (txtA.getText().equals("")) || 
						(txtPN.getText().equals("")) || (txtUser.getText().equals("")) || (txtPass.getText().equals(""))){

					JOptionPane.showMessageDialog(null, "Blank field/s", null , JOptionPane.WARNING_MESSAGE);
					
				}else{
					checkAuthorization(txtCheck.getText());
					createAdminAccount(txtFN.getText(), txtLN.getText(), txtA.getText(), Integer.parseInt(txtPN.getText()), 
									txtUser.getText(), txtPass.getText());
					getLastId();
					
					try {
						saveFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		
		JButton btnCncl = new JButton("Cancel");
		btnCncl.setFont(new Font("Helvetica",Font.PLAIN, 15));
		btnCncl.setForeground(Color.white);
		btnCncl.setBackground(Color.darkGray);
		btnCncl.setBorderPainted(false);
		btnCncl.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loginWindow lw = new loginWindow();
				lw.setVisible(true);
				setVisible(false);
			}
		});
		ImageIcon ic = new ImageIcon(getClass().getClassLoader().getResource("system_icon.png")); 
		Image img = ic.getImage();
		
		this.setIconImage(img);
		this.getContentPane().setBackground(Color.WHITE);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.setUndecorated(true);
		this.setSize(450,720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		add(pnlHead, "wrap");
		add(pnlCen, "wrap");
		
		pnlHead.add(lblE, "LEFT");
		pnlCen.add(lblFN, "cell 0 0 , width 150px, height 1px");
		pnlCen.add(txtFN, "cell 0 1, growx, spanx, height 35px");
		pnlCen.add(lblLN, "cell 0 2, gapy 20, width 150px, height 1px");
		pnlCen.add(txtLN, "cell 0 3, growx, spanx, height 35px");
		pnlCen.add(lblA, "cell 0 4, gapy 20, width 150px, height 1px");
		pnlCen.add(txtA, "cell 0 5, growx, spanx, height 35px");
		pnlCen.add(lblPN, "cell 0 6, gapy20, width 150px, height 1px");
		pnlCen.add(txtPN, "cell 0 7, growx, spanx, height 35px");
		pnlCen.add(btnImg, "cell 0 8, gapy20, width 150px, height 1px");
		pnlCen.add(lblImgName, "cell 0 8, gapx 10, width 150px, height 1px");
		pnlCen.add(lblUser, "cell 0 9, gapy 50, width 150px, height 1px");
		pnlCen.add(txtUser, "cell 0 10, growx, spanx, height 35px");
		pnlCen.add(lblPass, "cell 0 11, gapy 20, width 150px, height 1px");
		pnlCen.add(txtPass, "cell 0 12, growx, spanx, height 35px");
		pnlCen.add(lblCheck, "cell 0 13, gapy 30, width 150px, height 1px");
		pnlCen.add(txtCheck, "cell 0 13, growx, spanx, height 35px");
		
		
		pnlCen.add(btnCrt, "cell 0 14, gapy 30, width 50px, right");
		pnlCen.add(btnCncl, "cell 0 14, gapx 5, width 50px, right");
	}
	public void checkAuthorization(String code) {
		String query = "SELECT * FROM authorized_code WHERE code = ?";
		connectionWindow db = new connectionWindow();
		
		try {
			db.ps = db.conn.prepareStatement(query);
			db.ps.setString(1,  code);
			db.rs = db.ps.executeQuery();
			
			if(db.rs.next()) {
				loginWindow lw = new loginWindow();
				lw.setVisible(true);
				setVisible(false);
				
			}else {
				JOptionPane.showMessageDialog(this, "You are not an authorized personnel", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	private void getLastId() {		
		String query = "Select MAX(id) from login";
		connectionWindow db = new connectionWindow();
		
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
	private void saveFile() throws IOException {
		//getLastId();

		ImageIO.write(bi, "jpg", new File("D:\\retrieve_pictures\\admin_pictures\\" + id + ".jpg"));
	}
	private void createAdminAccount(String first_name, String last_name, String address, int phone_number, String user, String password) {
		String query = "INSERT INTO login(first_name, last_name, address, phone_number, username, password) VALUES (?,?,?,?,?,?)";
		connectionWindow db = new connectionWindow();
		
		try {
			db.ps = db.conn.prepareStatement(query);
			db.ps.setString(1,  first_name);
			db.ps.setString(2,  last_name);
			db.ps.setString(3,  address);
			db.ps.setInt(4,  phone_number);
			db.ps.setString(5, user);
			db.ps.setString(6, password);

			db.ps.execute();
			
			dispose();
			JOptionPane.showMessageDialog(this, "Account Created");	 
			
			} catch(Exception e) {
			System.out.println(e);
		}
	}
}
