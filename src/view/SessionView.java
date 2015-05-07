package view;

import javax.swing.JFrame;

import controller.SessionController;

public abstract class SessionView extends JFrame {
	private static final long serialVersionUID = 1L;

	public SessionView(String title) {
		super(title);
		SessionController.registerView(this);
	}

	public void logout() {
		// this.setVisible(false);
		this.dispose();
	}
}
