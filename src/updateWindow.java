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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import net.miginfocom.swing.MigLayout;

public class updateWindow extends JFrame{
	
	JPanel pnlHead, pnlCen;
	JTextField txtFN = new JTextField(15);
	JTextField txtMD = new JTextField(15);
	JTextField txtLN = new JTextField(15);
	JComboBox cbS;
	JDateChooser jcBd = new JDateChooser();
	JTextField txtA = new JTextField(15);
	JTextField txtPN = new JTextField(15);
	JComboBox cbStr;
	JTextField txtId = new JTextField(15);
	JTextField txtLRN;
	JLabel lblDp;
	File selectedFile;
	ImageIcon finalicDp;
	static String idw;
	
	public updateWindow() {
		setLayout(new MigLayout("center"));
		
		pnlHead = new JPanel(new MigLayout("left"));
		pnlHead.setBackground(Color.WHITE);
		pnlHead.setVisible(true);
		pnlHead.setPreferredSize(new Dimension(500,0));
		
		pnlCen = new JPanel(new MigLayout("center, fill, insets 20 20 20 20"));
		pnlCen.setBackground(Color.DARK_GRAY.darker());
		pnlCen.setVisible(true);
		pnlCen.setPreferredSize(new Dimension(900,450));
		pnlCen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
		Font f = new Font("Tahoma", Font.PLAIN, 20);
		
		
		JLabel lblE = new JLabel("UPDATE INFO");
		lblE.setForeground(Color.DARK_GRAY.darker());
		lblE.setFont(new Font("Tahoma",Font.BOLD,25));

		
		JLabel lblFN = new JLabel("First Name: ");
		lblFN.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblFN.setForeground(Color.WHITE);
		
		
		txtFN.setFont(f);
		
		JLabel lblMD = new JLabel("Middle Name: ");
		lblMD.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblMD.setForeground(Color.WHITE);
		
		
		txtMD.setFont(f);
		
		JLabel lblLN = new JLabel("Last Name: ");
		lblLN.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblLN.setForeground(Color.white);
		
	
		txtLN.setFont(f);
		
		JLabel lblS = new JLabel("Sex: ");
		lblS.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblS.setForeground(Color.white);
		
		String sex[] = {"Male", "Female"};
		cbS = new JComboBox(sex);
		cbS.setFont(f);
		cbS.setSelectedIndex(-1);


		JLabel lblBd = new JLabel("Birthday: ");
		lblBd.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblBd.setForeground(Color.white);
		
		jcBd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jcBd.setDateFormatString("yyyy-MM-dd");
		jcBd.setFont(f);
		
		JLabel lblA = new JLabel("Address: ");
		lblA.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblA.setForeground(Color.WHITE);
		
		
		txtA.setFont(f);
		
		JLabel lblPN = new JLabel("Phone Number: ");
		lblPN.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblPN.setForeground(Color.WHITE);
		
		
		txtPN.setFont(f);

		JLabel lblStr = new JLabel("Strand: ");
		lblStr.setFont(new Font("Tahoma",Font.PLAIN,20));
		lblStr.setForeground(Color.WHITE);
		
		String strands[] = {"ICT", "Industrial Arts", "Home Economics", "Carpentry"};
		cbStr = new JComboBox(strands);
		cbStr.setFont(f);
		cbStr.setSelectedIndex(-1);
		
		JLabel lblLRN = new JLabel("Learner Reference Number (LRN)");
		lblLRN.setFont(new Font("Tahoma",Font.PLAIN,15));
		lblLRN.setForeground(Color.white);
		
		txtLRN = new JTextField(15);
		txtLRN.setFont(f);
		
		JButton btnEdPic = new JButton("Edit Picture");
		btnEdPic.setForeground(Color.WHITE);
		btnEdPic.setBackground(Color.DARK_GRAY);
		btnEdPic.addActionListener(new ActionListener() {
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
						JOptionPane.showMessageDialog(null, "PLEASE SELECT A FILE!", "Error", JOptionPane.ERROR_MESSAGE); 
					}
				}
			}
			public ImageIcon ResizeImage(String ImagePath) {
				
				ImageIcon icDp = new ImageIcon(ImagePath);
				java.awt.Image imgDp = icDp.getImage();
				java.awt.Image newImgDp = imgDp.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
				finalicDp = new ImageIcon(newImgDp);
				return finalicDp;
			}
		});
				
		
			
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String value = "";
				int num = 0;
				if(txtFN.getText().equals("") || (txtLN.getText().equals("")) || (cbS.getSelectedItem().equals("")) || 
						(jcBd.getDate()==null) || (txtA.getText().equals("")) || (txtPN.getText().equals("")) || 
						cbStr.getSelectedItem().equals("")){
			
					JOptionPane.showMessageDialog(null, "Blank field/s", "Cannot Enrol", JOptionPane.WARNING_MESSAGE);
					
				}else if((txtMD.getText().equals(""))) {
					txtMD.setText(value);
					finalUpdateOutput();
					
				}else if((txtLRN.getText().equals(""))) {
					txtLRN.setText("" + num);
					finalUpdateOutput();
					
				}else if((txtMD.getText().equals("")) && txtLRN.getText().equals("")) {
					txtLRN.setText("" + num);
					txtMD.setText(value);
					finalUpdateOutput();
					
				}else{
					finalUpdateOutput();
					
				}
			}
		});

		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(Color.DARK_GRAY);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});		
		ImageIcon ic = new ImageIcon(getClass().getClassLoader().getResource("system_icon.png")); 
		Image img = ic.getImage();
		
		this.setIconImage(img);
		this.getContentPane().setBackground(Color.WHITE);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.setUndecorated(true);
		this.setSize(900,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		add(pnlHead, "wrap");
		add(new JScrollPane(pnlCen), "wrap");
		//add(pnlCen, "wrap");
		
		pnlHead.add(lblE, "wrap");
		
		
		//add(lblE, "cell 0 0, LEFT");
		
		pnlCen.add(btnEdPic, "cell 0 6, width 150px, center");
		pnlCen.add(lblLRN, "cell 0 7, gapy 20, width 150px, height 1px, center");
		pnlCen.add(txtLRN, "cell 0 8, center");
		pnlCen.add(lblFN, "cell 1 0, gapy 10, width 150px, height 1px");
		pnlCen.add(txtFN, "cell 1 0, gapy 10, growx, spanx, height 35px");
		pnlCen.add(lblMD,"cell 1 1, gapy 10, width 150px, height 1px");
		pnlCen.add(txtMD, " cell 1 1, gapy 10, growx, spanx, height 35px");
		pnlCen.add(lblLN, "cell 1 2, gapy 10, width 150px, height 1px");
		pnlCen.add(txtLN, "cell 1 2, gapy 10, growx, spanx, height 35px");
		pnlCen.add(lblS, "cell 1 3, gapy 10, width 150px, height 1px");
		pnlCen.add(cbS, "cell 1 3, gapy 10, growx, spanx, height 35px");
		pnlCen.add(lblBd, "cell 1 4, gapy 10, width 150px, height 1px");
		pnlCen.add(jcBd, "cell 1 4, gapy 10, growx, spanx, height 35px");
		pnlCen.add(lblA, "cell 1 5, gapy 10, width 150px, height 1px");
		pnlCen.add(txtA, "cell 1 5, gapy 10, growx, spanx, height 35px");
		pnlCen.add(lblPN, "cell 1 6, gapy 10, width 150px, height 1px");
		pnlCen.add(txtPN, "cell 1 6, gapy 10, growx, spanx, height 35px");
		pnlCen.add(lblStr, "cell 1 7, gapy 10, width 150px, height 1px");
		pnlCen.add(cbStr, "cell 1 7, gapy 10, growx, spanx, height 35px");
		
		
		pnlCen.add(btnUpdate, "cell 1 9, right");
		pnlCen.add(btnCancel, "cell 1 9, right");		
	}
	private void imageSave() throws IOException {
		if(selectedFile == null) {
			JOptionPane.showMessageDialog(null, "No new image chosen!", "Error", JOptionPane.ERROR_MESSAGE); 
		}else {
			Image img = finalicDp.getImage();

			BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(img, 0, 0, null);
			g2.dispose();
			ImageIO.write(bi, "jpg", new File("D:\\retrieve_pictures\\student_pictures\\" + txtId.getText() + ".jpg"));
			String id1 = txtId.getText();
			System.out.println(id1);
	}
		//System.out.println(id);
	}
	public void updateStudent(String first_name, String middle_name, String last_name, Object sex, 
			java.util.Date birth_date, String address, int phone_number, Object strand, int lrn, String students_id) {		
		String query = "UPDATE students_info SET first_name = ?, last_name = ?, middle_name = ?, "
				+ "sex = ?, birth_date = ?, address = ?, phone_number = ?, strand = ?, lrn = ? WHERE students_id = ?" ;
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
			db.ps.setString(10,  students_id);
			
			db.ps.executeUpdate();
			
			dashboardWindow dw = new dashboardWindow();
			dw.jpcon.remove(dw);
			dw.jpcon.revalidate();
			dw.jpcon.repaint();
			
			dispose();
			
			JOptionPane.showMessageDialog(this, "Updated", "Message", JOptionPane.WARNING_MESSAGE);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void existingData(String first_name, String middle_name, String last_name, Object sex, 
			java.util.Date birthDate, String address, int phone_number, Object strand, int lrn, String students_id) {		
		txtFN.setText(first_name);
		txtLN.setText(last_name);
		txtMD.setText(middle_name);
		cbS.setSelectedItem((String) sex);
		jcBd.setDate(birthDate);		
		txtA.setText(address);
		txtPN.setText("" + phone_number);
		cbStr.setSelectedItem((String) strand);
		txtLRN.setText("" + lrn);
		txtId.setText(students_id);
		//idw = students_id;
		showImage();
	}
	private void finalUpdateOutput() {
		updateStudent(txtFN.getText(), txtLN.getText(), txtMD.getText(), cbS.getSelectedItem(), 
				(new java.sql.Date(jcBd.getDate().getTime())), txtA.getText(), Integer.parseInt(txtPN.getText()), 
				cbStr.getSelectedItem(), Integer.parseInt(txtLRN.getText()), txtId.getText());				
		
		//getLastId();

			try {
				imageSave();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			lblDp.setIcon(finalicDp);

	}
	public void showImage() {
		String idPath = "D:\\retrieve_pictures\\student_pictures\\" + txtId.getText() + ".jpg";
		Path path = Paths.get(idPath);
		
		if(Files.exists(path)) {
			ImageIcon icDp = new ImageIcon(idPath);
			Image imgDp = icDp.getImage();
			Image newimgDp = imgDp.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			ImageIcon icFinalDp = new ImageIcon(newimgDp);
			lblDp = new JLabel(icFinalDp);
			lblDp.setIcon(icFinalDp);
			lblDp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			lblDp.setBackground(Color.blue);
			
			pnlCen.add(lblDp, "cell 0 1, span 0 5, center");
			
		}else {
			ImageIcon icDp = new ImageIcon(getClass().getClassLoader().getResource("blank-profile.jpg"));
			Image imgDp = icDp.getImage();
			Image newimgDp = imgDp.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			ImageIcon icFinalDp = new ImageIcon(newimgDp);
			lblDp = new JLabel(icFinalDp);
			lblDp.setIcon(icFinalDp);
			lblDp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			lblDp.setBackground(Color.blue);
			
			pnlCen.add(lblDp, "cell 0 1, span 0 5, center");
			
		}	
	}
}