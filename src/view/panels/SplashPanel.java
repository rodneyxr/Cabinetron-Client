package view.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SplashPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private String flag;
	private BufferedImage img;
	// private String loading = "src/res/splashscreen.gif";
	private String noConnection = "src/res/splashnoConnection.png";

	public SplashPanel(String flag) {
		this.flag = flag;
		initComponents();
	}

	@Override
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		if (flag.equals("noConnection"))
			page.drawImage(img, 0, 0, null);
		else
			super.setBackground(Color.DARK_GRAY);
	}

	private void initComponents() {
		String urlString = "";
		if (flag.equals("load")) {
			return;
		}
		if (flag.equals("noConnection")) {
			urlString = noConnection;
		}

		try {
			img = ImageIO.read(new File(urlString));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
