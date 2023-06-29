import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class dashboardWindow extends JFrame{
	
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jpcon = new JPanel();
	String firstN;
	JLabel lblGreet;
	JLabel lblUserImg;
	int idAd;
		
	public dashboardWindow() {
		setLayout(new MigLayout("center, fill"));
		this.getContentPane().setBackground(Color.WHITE);
		
		jpcon.setLayout(new MigLayout("center, fill", "push", "grow"));
		jpcon.setBackground(Color.white);
		jpcon.setPreferredSize(new Dimension(1100, 670)); //y720
		jpcon.setVisible(true);

		jp1.setLayout(new MigLayout("center, fill"));
		jp1.setBackground(Color.darkGray);
		jp1.setPreferredSize(new Dimension(500, 720));
		jp1.setVisible(true);
		
		jp2.setLayout(new MigLayout("right"));
		jp2.setBackground(Color.darkGray);
		jp2.setPreferredSize(new Dimension(600, 50)); //x1100
		jp2.setVisible(true);
		
		//AUTOMATIC HOME
		homeWindow hw = new homeWindow();
		jpcon.add(hw.getContentPane());
		jpcon.revalidate();
		jpcon.repaint();


		lblGreet = new JLabel();
		lblGreet.setFont(new Font("Calibri", Font.ITALIC, 20));
		lblGreet.setForeground(Color.WHITE);
		
		JButton btnHome = new JButton("HOME");	
		ImageIcon icHome = new ImageIcon(getClass().getClassLoader().getResource("home.png"));
		Image imgHome = icHome.getImage();
		Image newimgHome = imgHome.getScaledInstance(35, 35,  java.awt.Image.SCALE_SMOOTH);
		icHome = new ImageIcon(newimgHome);
		
		btnHome.setFont(new Font("Calibri",Font.BOLD,25));
		btnHome.setContentAreaFilled(false);
		btnHome.setBorderPainted(false);
		btnHome.setIcon(icHome);
		btnHome.setForeground(Color.white);
		btnHome.setIconTextGap(25);
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				removeComponents();
				
				homeWindow lw = new homeWindow();
				jpcon.add(lw.getContentPane());
				jpcon.revalidate();
				jpcon.repaint();

				
			}});
		
		JButton btnEnrol = new JButton("ENROL");		
		ImageIcon icEnr = new ImageIcon(getClass().getClassLoader().getResource("signature.png"));
		java.awt.Image imgEnr = icEnr.getImage();
		java.awt.Image newimgEnr = imgEnr.getScaledInstance(35, 35,  java.awt.Image.SCALE_SMOOTH);
		icEnr = new ImageIcon(newimgEnr);
		
		btnEnrol.setContentAreaFilled(false);
		btnEnrol.setBorderPainted(false);
		btnEnrol.setIcon(icEnr);
		btnEnrol.setFont(new Font("Calibri",Font.BOLD,25));
		btnEnrol.setForeground(Color.white);
		btnEnrol.setIconTextGap(25);
		btnEnrol.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				removeComponents();
				
				enrolWindow ew = new enrolWindow();
				jpcon.add(ew.getContentPane());
				jpcon.revalidate();
				jpcon.repaint();				
			}
		});
		
		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma",Font.BOLD,20));
		lblX.setForeground(Color.white);
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
				System.exit(0);

			}
		});
		
		JLabel lblMin = new JLabel("-");
		lblMin.setFont(new Font("Tahoma",Font.BOLD,30));
		lblMin.setForeground(Color.white);
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
			
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(Color.red);
		btnLogout.setBorderPainted(false);
		btnLogout.addActionListener(new ActionListener() {
			
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
		this.setSize(1100,720);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.DARK_GRAY.darker());
		this.setUndecorated(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(jpcon, "wrap, align left top");
		add(jp1, "wrap, dock west");
		add(jp2, "wrap, dock north");
		
		
		jp1.add(lblGreet, "gapy 15, cell 0 2, center");
		jp1.add(btnHome, "gapy 80, cell 0 3, center, width 250px, height 80px");
		jp1.add(btnEnrol, "gapy 10, cell 0 4, center, width 250px, height 80px");
		jp1.add(btnLogout, "cell 0 5, gapy 100, dock south, width 300px, height 40px");
		
		jp2.add(lblMin);
		jp2.add(lblX, "gapx 20");
		
	}
	public void removeComponents() {
		Component[] cList = jpcon.getComponents();
		
		for(Component c : cList) {
			jpcon.remove(c);
			
		}
		
	}
	public void existingData(String FN, int ID) {
		this.idAd = ID;
		this.firstN = FN;
		lblGreet.setText("Hey, " + firstN + "!");
		showRoundImage();
		
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
	public void showRoundImage() {
		lblUserImg = new JLabel();
		
		try {
			String idPath = "D:\\retrieve_pictures\\admin_pictures\\" + this.idAd + ".jpg";
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
							
				ImageIcon icUser = new ImageIcon(masked);
				Image imgUser = icUser.getImage();
				Image newimgUser = imgUser.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				ImageIcon icFinalDp = new ImageIcon(newimgUser);
				lblUserImg.setIcon(icFinalDp);
				
				jp1.add(lblUserImg, "gapy 60, cell 0 1, center");
			}else {
				ImageIcon icUser = new ImageIcon(getClass().getClassLoader().getResource("blank-profile.jpg"));
				Image imgUser = icUser.getImage();
				int diameter = Math.min(imgUser.getWidth(null), imgUser.getHeight(null));
				BufferedImage mask = new BufferedImage(imgUser.getWidth(null), imgUser.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				
				Graphics2D g2d = mask.createGraphics();
				applyQualityRenderingHints(g2d);
				g2d.fillOval(0, 0, diameter - 1, diameter - 1);
				g2d.dispose();
				
				BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
				g2d = masked.createGraphics();
				applyQualityRenderingHints(g2d);
				int x = (diameter - imgUser.getWidth(null)) / 2;
				int y = (diameter - imgUser.getHeight(null)) / 2;
				g2d.drawImage(imgUser, x, y, null);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
				g2d.drawImage(mask, 0, 0, null);
				g2d.dispose();
				
				ImageIcon icUser1 = new ImageIcon(masked);
				Image imgUser1 = icUser1.getImage();
				Image newimgUser = imgUser1.getScaledInstance(250, 250,  Image.SCALE_SMOOTH);
				ImageIcon icFinalUser = new ImageIcon(newimgUser);
				lblUserImg.setIcon(icFinalUser);
				
				jp1.add(lblUserImg, "gapy 60, cell 0 1, center");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
