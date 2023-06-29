import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class loginWindow extends JFrame{
	String fn;
	int id;
	
	public loginWindow() {
		setLayout(new MigLayout("center"));
		
		JPanel pnlHead = new JPanel(new MigLayout("center"));
		pnlHead.setBackground(Color.DARK_GRAY.darker());
		pnlHead.setVisible(true);
		pnlHead.setPreferredSize(new Dimension(500,20));
		
		JPanel pnlCen = new JPanel(new MigLayout("center, fillx, insets 20 20 20 20"));
		pnlCen.setBackground(Color.WHITE.brighter());
		pnlCen.setVisible(true);
		pnlCen.setPreferredSize(new Dimension(350,440));
		pnlCen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			
		JLabel lblUserImg = new JLabel();
		ImageIcon icUser = new ImageIcon("icons/user.png");
		java.awt.Image imgUser = icUser.getImage();
		java.awt.Image newimgUser = imgUser.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
		icUser = new ImageIcon(newimgUser);
		lblUserImg.setIcon(icUser);
		
		
		Font f = new Font("Helvitica", Font.PLAIN, 15);
		Font f2 = new Font("Tahoma", Font.PLAIN, 15);
		
		JLabel lblTitle = new JLabel("Admin Login");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setForeground(Color.WHITE);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.DARK_GRAY);
		lblUsername.setFont(f2);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setForeground(Color.DARK_GRAY);
		lblPass.setFont(f2);
		
		JTextField txtUsername = new JTextField(15);
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setFont(f);
		txtUsername.setBorder(new LineBorder(Color.DARK_GRAY.darker(), 2));
		
		
		JPasswordField txtPassword= new JPasswordField(15);
		txtPassword.setBackground(Color.white);
		txtPassword.setBorder(null);
		txtPassword.setFont(f);
		txtPassword.setBorder(new LineBorder(Color.DARK_GRAY.darker(), 2));

		JCheckBox cbShow = new JCheckBox("Show Password");
		cbShow.setHorizontalTextPosition(JCheckBox.LEFT);
		cbShow.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbShow.setForeground(Color.DARK_GRAY);
		cbShow.addActionListener(ae -> {
			JCheckBox c = (JCheckBox) ae.getSource();
			 if (c.isSelected()) {
				 txtPassword.setEchoChar((char)0);
			   } else {
				   txtPassword.setEchoChar('*');
			   }
		});
		
		
		JButton btnLogin = new JButton("LOGIN");		
		btnLogin.setFont(new Font("Tahoma",Font.PLAIN,13));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				checkUser(txtUsername.getText() , txtPassword.getText());
				
		}});
		
		JLabel lblExt = new JLabel("Exit");
		lblExt.setFont(new Font("Tahoma",Font.PLAIN,13));
		lblExt.setForeground(Color.DARK_GRAY);
		lblExt.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {	
			}
			
			@Override
			public void mousePressed(MouseEvent e) {	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {	
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				
				
			}
		});
		
		JLabel lblCA = new JLabel("Create Admin Account");
		lblCA.setFont(new Font("Tahoma",Font.PLAIN,13));
		lblCA.setForeground(Color.DARK_GRAY);
		lblCA.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {	
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {	
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {	
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblCA.setForeground(Color.BLUE.brighter());
				
				adminCreateWindow acw = new adminCreateWindow();
				acw.setVisible(true);
				setVisible(false);
				
			}
		});
		ImageIcon ic = new ImageIcon(getClass().getClassLoader().getResource("system_icon.png")); 
		Image img = ic.getImage();
		
		this.setIconImage(img);
		this.getContentPane().setBackground(Color.DARK_GRAY.darker());
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.setUndecorated(true);
		this.setSize(350,440);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		

		add(pnlHead, "wrap");
		add(pnlCen, "wrap");
		pnlHead.add(lblTitle);
		
		pnlCen.add(lblUsername, "wrap, left, height 35px");
		pnlCen.add(txtUsername, "wrap, growx, spanx, height 35px");
		pnlCen.add(lblPass, "wrap, gapy 10, left, height 35px");
		pnlCen.add(txtPassword, "wrap, center, growx, spanx, height 35px");
		pnlCen.add(cbShow, "wrap, gapx 5, right");
		pnlCen.add(btnLogin, "wrap, gapy 20, center, grow, height 50px");
		pnlCen.add(lblCA, "wrap, gapy 5, center");
		pnlCen.add(lblExt, "center, gapy 60, bottom");

	}
	public void checkUser(String username, String password) {
		String query = "SELECT id, first_name FROM login WHERE username = ? AND password = ?";
		connectionWindow db = new connectionWindow();
		
		try {
			db.ps = db.conn.prepareStatement(query);
			db.ps.setString(1,  username);
			db.ps.setString(2,  password);
			db.rs = db.ps.executeQuery();
			
			if(db.rs.next()) {
				
				id = db.rs.getInt("id");
				fn = db.rs.getString("first_name");
				
				dashboardWindow dw = new dashboardWindow();
				dw.existingData(fn, id);
				dw.setVisible(true);
				//dw.pack();
				 setVisible(false);
				
			}else {
				JOptionPane.showMessageDialog(this, "Incorrect Username/Password", "Error", JOptionPane.ERROR_MESSAGE);
				id = 0;
				fn = "";
			}
		} catch(Exception e) {
			e.printStackTrace();
			//System.out.println(e);
		}
	}
	/*public void getAdminValue(String username, String password) {
		String query = "SELECT first_name, id FROM login WHERE username = ? AND password = ?";
		connectionWindow db = new connectionWindow();
		
			
			try {
				db.ps = db.conn.prepareStatement(query);
				db.ps.setString(1,  username);
				db.ps.setString(2,  password);
				db.rs = db.ps.executeQuery();
				
				
				if(db.rs.next()) {
					
					fn = db.rs.getString("first_name");
					id = db.rs.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}
	public void existingData() {		
		
		dashboardWindow dw = new dashboardWindow();
		
		dw.firstN = fn;
		//dw.lblGreet.setText(fn);
		dw.idAd = id;
		//System.out.println(dw.lblGreet.getText());
		System.out.println(dw.firstN + dw.idAd);
		//dw.existingData(fn, id);
	}*/
}
