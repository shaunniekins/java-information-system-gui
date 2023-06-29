import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class showInfoWindow extends JFrame{
	
	JPanel pnlHead, pnlCen;
	JTextField txtFN = new JTextField(15);
	JTextField txtMD = new JTextField(15);
	JTextField txtLN = new JTextField(15);
	JTextField cbS = new JTextField(15);
	JTextField jcBd = new JTextField(15);
	JTextField txtA = new JTextField(15);
	JTextField txtPN = new JTextField(15);
	JTextField cbStr = new JTextField(15);
	JTextField txtId = new JTextField(15);
	JTextField txtLRN = new JTextField(15);
	JLabel lblDp;
	
	public showInfoWindow() {
		setLayout(new MigLayout("center"));
		
		pnlHead = new JPanel(new MigLayout("left"));
		pnlHead.setBackground(Color.WHITE);
		pnlHead.setVisible(true);
		pnlHead.setPreferredSize(new Dimension(350,0));
		
		pnlCen = new JPanel(new MigLayout("center, fillx, insets 20 20 20 20"));
		pnlCen.setBackground(Color.DARK_GRAY.darker());
		pnlCen.setVisible(true);
		pnlCen.setPreferredSize(new Dimension(350,900));
		pnlCen.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		
		Font f = new Font("Tahoma", Font.PLAIN, 15);
		Font f2 = new Font("Tahoma", Font.PLAIN, 15);
		
		
		JLabel lblE = new JLabel("Student");
		lblE.setForeground(Color.DARK_GRAY.darker());
		lblE.setFont(new Font("Helvetica",Font.PLAIN,25));

		JLabel lblFN = new JLabel("First Name");
		lblFN.setFont(f2);
		lblFN.setForeground(Color.WHITE);

		txtFN.setFont(f);
		
		JLabel lblMD = new JLabel("Middle Name");
		lblMD.setFont(f2);
		lblMD.setForeground(Color.WHITE);
		
		txtMD.setFont(f);
		
		JLabel lblLN = new JLabel("Last Name");
		lblLN.setFont(f2);
		lblLN.setForeground(Color.white);
		
		txtLN.setFont(f);
		
		JLabel lblS = new JLabel("Sex");
		lblS.setFont(f2);
		lblS.setForeground(Color.white);
		
		cbS.setFont(f);

		JLabel lblBd = new JLabel("Birthday");
		lblBd.setFont(f2);
		lblBd.setForeground(Color.white);
		
		jcBd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jcBd.setFont(f);
		
		JLabel lblA = new JLabel("Address");
		lblA.setFont(f2);
		lblA.setForeground(Color.WHITE);
		
		
		txtA.setFont(f);
		
		JLabel lblPN = new JLabel("Phone Number");
		lblPN.setFont(f2);
		lblPN.setForeground(Color.WHITE);
		
		
		txtPN.setFont(f);

		JLabel lblStr = new JLabel("Strand: ");
		lblStr.setFont(f2);
		lblStr.setForeground(Color.WHITE);
		
		cbStr.setFont(f);
		
		JLabel lblLRN = new JLabel("LRN");
		lblLRN.setFont(f2);
		lblLRN.setForeground(Color.white);
		
		txtLRN.setFont(f);
				
		lblDp = new JLabel();
		
		
		
		JButton btnOk = new JButton("OK");
		btnOk.setForeground(Color.WHITE);
		btnOk.setBackground(Color.DARK_GRAY);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});	
		
		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma",Font.BOLD,20));
		lblX.setForeground(Color.DARK_GRAY.darker());
		lblX.addMouseListener(new MouseListener() {
			
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
				dispose();
			}
		});
		
		JLabel lblMin = new JLabel("-");
		lblMin.setFont(new Font("Tahoma",Font.BOLD,30));
		lblMin.setForeground(Color.DARK_GRAY.darker());
		lblMin.addMouseListener(new MouseListener() {
			
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
				setState(JFrame.ICONIFIED);	
			}
		});
		ImageIcon ic = new ImageIcon(getClass().getClassLoader().getResource("system_icon.png")); 
		Image img = ic.getImage();
		
		this.setIconImage(img);
		this.getContentPane().setBackground(Color.WHITE);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		this.setUndecorated(true);
		this.setSize(350,750);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);

		add(pnlHead, "wrap");

		pnlHead.add(lblE, "cell 0 1");
		pnlHead.add(lblMin, "cell 0 1, gapx 250");
		pnlHead.add(lblX, "cell 0 1, gapx 10");
		
		JScrollPane scrollFrame = new JScrollPane(pnlCen);
		pnlCen.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(400,700));
		this.add(scrollFrame, "cell 0 16");
		
		//add(lblE, "cell 0 0, LEFT");
		pnlCen.add(lblDp, "cell 0 1, center");
		pnlCen.add(lblLRN, "cell 0 7, gapy 55, width 100px");
		pnlCen.add(txtLRN, "cell 0 7, gapy 55, growx, spanx, height 30px");
		pnlCen.add(lblFN, "cell 0 8, gapy 25, width 100px");
		pnlCen.add(txtFN, "cell 0 8, gapy 25, growx, spanx, height 30px");
		pnlCen.add(lblMD,"cell 0 9, gapy 25, width 100px");
		pnlCen.add(txtMD, " cell 0 9, gapy 25, growx, spanx, height 30px");
		pnlCen.add(lblLN, "cell 0 10, gapy 25, width 100px");
		pnlCen.add(txtLN, "cell 0 10, gapy 25, growx, spanx, height 30px");
		pnlCen.add(lblS, "cell 0 11, gapy 25, width 100px");
		pnlCen.add(cbS, "cell 0 11, gapy 25, growx, spanx, height 30px");
		pnlCen.add(lblBd, "cell 0 12, gapy 25, width 100px");
		pnlCen.add(jcBd, "cell 0 12, gapy 25, growx, spanx, height 30px");
		pnlCen.add(lblA, "cell 0 13, gapy 25, width 100px");
		pnlCen.add(txtA, "cell 0 13, gapy 25, growx, spanx, height 30px");
		pnlCen.add(lblPN, "cell 0 14, gapy 25, width 100px");
		pnlCen.add(txtPN, "cell 0 14, gapy 25, growx, spanx, height 30px");
		pnlCen.add(lblStr, "cell 0 15, gapy 25, width 100px");
		pnlCen.add(cbStr, "cell 0 15, gapy 25, growx, spanx, height 30px");
		
		pnlCen.add(btnOk, "cell 0 16, gapy 50, right");		
	}
	public void existingData(String first_name, String middle_name, String last_name, String sex, 
			String birthDate, String address, int phone_number, String strand, int lrn, String students_id) {		
		txtFN.setText(first_name);
		txtLN.setText(last_name);
		txtMD.setText(middle_name);
		cbS.setText((String) sex);
		jcBd.setText(birthDate);		
		txtA.setText(address);
		txtPN.setText("" + phone_number);
		cbStr.setText((String) strand);
		txtLRN.setText("" + lrn);
		txtId.setText(students_id);
		
		showImage();
	}
	public static void applyQualityRenderingHints(Graphics2D g2d) {

	    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	}
	public void showImage() {
		try {
			String idPath = "D:\\retrieve_pictures\\student_pictures\\" + txtId.getText() + ".jpg";
			Path path = Paths.get(idPath);
			if(Files.exists(path)) {
				BufferedImage master;
				master = ImageIO.read(new File(idPath));
				int diameter = Math.min(master.getWidth(), master.getHeight());
				BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);
				
				Graphics2D g2d = mask.createGraphics();
				applyQualityRenderingHints(g2d);
				g2d.fillOval(0, 0, diameter - 1, diameter - 1);
				g2d.dispose();
				
				BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
				g2d = masked.createGraphics();
				applyQualityRenderingHints(g2d);
				int x = (diameter - master.getWidth()) / 2;
				int y = (diameter - master.getHeight()) / 2;
				g2d.drawImage(master, x, y, null);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
				g2d.drawImage(mask, 0, 0, null);
				g2d.dispose();
				
				ImageIcon icDp = new ImageIcon(masked);
				Image imgDp = icDp.getImage();
				Image newimgDp = imgDp.getScaledInstance(250, 250,  Image.SCALE_SMOOTH);
				ImageIcon icFinalDp = new ImageIcon(newimgDp);
				lblDp.setIcon(icFinalDp);
			}else {
				ImageIcon icDp = new ImageIcon(getClass().getClassLoader().getResource("blank-profile.jpg"));
				Image imgDp = icDp.getImage();
				int diameter = Math.min(imgDp.getWidth(null), imgDp.getHeight(null));
				BufferedImage mask = new BufferedImage(imgDp.getWidth(null), imgDp.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				
				Graphics2D g2d = mask.createGraphics();
				applyQualityRenderingHints(g2d);
				g2d.fillOval(0, 0, diameter - 1, diameter - 1);
				g2d.dispose();
				
				BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
				g2d = masked.createGraphics();
				applyQualityRenderingHints(g2d);
				int x = (diameter - imgDp.getWidth(null)) / 2;
				int y = (diameter - imgDp.getHeight(null)) / 2;
				g2d.drawImage(imgDp, x, y, null);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
				g2d.drawImage(mask, 0, 0, null);
				g2d.dispose();
				
				ImageIcon icDp1 = new ImageIcon(masked);
				Image imgDp1 = icDp1.getImage();
				Image newimgDp = imgDp1.getScaledInstance(250, 250,  Image.SCALE_SMOOTH);
				ImageIcon icFinalDp = new ImageIcon(newimgDp);
				lblDp.setIcon(icFinalDp);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
	}
}
