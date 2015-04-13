package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.panels.SplashPanel;

public class SplashScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private final JComboBox<String> loginBox = new JComboBox<String>();;
	private JLabel txt_Login = new JLabel("Login");

	public SplashScreen(String flag) {
		try {
	        setIconImage(ImageIO.read(new File("src/res/icon.png")));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		panel = new SplashPanel(flag);
		add(panel);
	}

	public void loginPage(){
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// login label
		JLabel l = new JLabel("Login");
		l.setForeground(Color.white);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(txt_Login, c);

		// combo box
		loginBox.addItem("Tom Jones");
		loginBox.addItem("Sue Smith");
		loginBox.addItem("Ragnar Nelson");
		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(loginBox, c);

		// login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.login(loginBox.getSelectedItem().toString());
			}

		});
		c.gridx = 2;
		c.gridy = 0;
		panel.add(loginButton, c);
	}
}
