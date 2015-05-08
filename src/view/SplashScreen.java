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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.InventoryItemLog;
import view.panels.SplashPanel;

public class SplashScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField textUsername;
	private JPasswordField textPassword;
	private JLabel labelError;

	public SplashScreen(String flag) {
		try {
			setIconImage(ImageIO.read(new File("src/res/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		labelError = new JLabel("Error!");
		labelError.setVisible(false);
		panel = new SplashPanel(flag);
		panel.setBackground(Color.DARK_GRAY);
		add(panel);
	}

	public void loginPage() {
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// error label
		labelError.setForeground(Color.RED);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(labelError, c);

		// login label
		JLabel labelLogin = new JLabel("Login");
		labelLogin.setForeground(Color.WHITE);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		panel.add(labelLogin, c);

		// email label
		JLabel labelEmail = new JLabel("Email: ");
		labelEmail.setForeground(Color.WHITE);
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		panel.add(labelEmail, c);

		// Text Field for username
		textUsername = new JTextField();
		c.gridx = 1;
		c.gridy = 2;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(textUsername, c);

		// password label
		JLabel labelPassword = new JLabel("Password: ");
		labelPassword.setForeground(Color.WHITE);
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0;
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
		panel.add(labelPassword, c);

		// Text Field for password
		textPassword = new JPasswordField();
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(textPassword, c);

		// login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.login(textUsername.getText(), textPassword.getPassword());
			}
		});

		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		panel.add(loginButton, c);

		// close button
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (InventoryItemLog.itemLogGateway != null && InventoryItemLog.stateObserver != null) {
					InventoryItemLog.itemLogGateway.unregisterObserver(InventoryItemLog.stateObserver);
					System.out.println("Unregistered client as InventoryItemLog observer.");
				}
				System.out.println("Good bye!");
				System.exit(0);
			}
		});
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		panel.add(closeButton, c);
	}

	public void resetForm() {
		hideError();
		textUsername.setText("");
		textPassword.setText("");
	}

	public void showError(String message) {
		labelError.setText(message);
		labelError.setVisible(true);
	}

	public void hideError() {
		labelError.setText("");
		labelError.setVisible(false);
	}
}
