package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.InventoryGateway;
import model.InventoryItem;
import model.InventoryModel;
import model.Part;
import model.PartGateway;
import model.PartsModel;
import model.ProductTemplate;
import model.ProductTemplateGateway;
import model.ProductTemplatesModel;
import session.AuthenticatorRemote;
import session.Session;
import session.User;
import controller.InventoryController;
import controller.InventoryItemController;
import controller.PartController;
import controller.ProductTemplateController;
import controller.SessionController;

/**
 * 
 * CS 4743 Cabinetron by Steven Petroff, Rodney Rodriguez Assignment 6 by Rodney Rodriguez
 * 
 */

public class Main {
	public static boolean DEBUG_MODE = true;

	// user session variables
	public static Session userSession;
	public static User user;

	public static PartsModel partsModel = new PartsModel();
	public static ProductTemplatesModel templatesModel = new ProductTemplatesModel();
	public static InventoryModel inventoryModel = new InventoryModel();
	public static PartGateway partGateway;
	public static InventoryGateway inventoryGateway;
	public static ProductTemplateGateway productTemplateGateway;
	private static SplashScreen splashScreen;
	public static SessionController sessionController = new SessionController();

	private static AuthenticatorRemote authenticator;

	private static boolean loaded = false;

	public static void main(String[] args) {
		// if (!Main.DEBUG_MODE)
		initSession();

		new Runnable() {
			@Override
			public void run() {
				Main.showSplashScreen("load");
			}
		}.run();

	}

	private static void initSession() {
		System.out.println("Connecting to bean...");
		try {
			Properties props = new Properties();
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			props.put("org.omg.CORBA.ORBInitialPort", "3700");

			InitialContext itx = new InitialContext(props);
			authenticator = (AuthenticatorRemote) itx.lookup("java:global/cabinetron_bean/Authenticator!session.AuthenticatorRemote");
			System.out.println("Successfully connected!");
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
	}

	private static void startClient() {
		if (partGateway != null && inventoryGateway != null && productTemplateGateway != null) {

			final MainView mainView = new MainView(inventoryModel, partsModel, templatesModel);
			InventoryController inventoryController = new InventoryController(inventoryModel, partsModel, templatesModel, mainView);
			InventoryItemView.inventoryItemController = new InventoryItemController(inventoryController);
			InventoryItemView.inventoryController = inventoryController;
			ProductTemplateView.templateController = new ProductTemplateController(templatesModel);
			PartView.partController = new PartController(partsModel, inventoryController);

			mainView.registerListener(inventoryController);
			mainView.setSize(960, 540);
			mainView.setResizable(true);
			mainView.setLocationRelativeTo(null);
			mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainView.setVisible(true);

			// set the menu bar
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("Session");
			JMenuItem itemLogout = new JMenuItem("Logout");
			itemLogout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(userSession.getUser().getName() + " logged out.");
					userSession = null;
					sessionController.logout();
					Main.showSplashScreen("load");
				}
			});

			menu.add(itemLogout);
			menuBar.add(menu);
			mainView.setJMenuBar(menuBar);

		} else {
			new Runnable() {
				@Override
				public void run() {
					Main.showSplashScreen("noConnection");
				}
			}.run();
			hideSplashScreen();
		}
	}

	private static void fetchFromDB() {
		DefaultListModel<Part> parts;
		DefaultListModel<InventoryItem> inv;
		DefaultListModel<ProductTemplate> templates;
		InventoryItemView.inventoryModel = inventoryModel;
		InventoryItemView.partsModel = partsModel;

		try {
			partGateway = new PartGateway();
			inventoryGateway = new InventoryGateway();
			productTemplateGateway = new ProductTemplateGateway();

			partGateway.getDataSource();
			parts = partGateway.getParts();
			partsModel.setPartsFromDB(parts);

			templates = productTemplateGateway.getProductTemplates();
			templatesModel.setTemplatesFromDB(templates);

			inv = inventoryGateway.getInvItems();
			inventoryModel.setInventoryFromDB(inv);

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	public static void login(String email, char[] passwordArray) {
		if (Main.DEBUG_MODE) {
			userSession = authenticator.authenticateUser("ragnarnelson@email.com", "password");
			System.out.println("User authenticated: " + userSession.getUser().getName());
			hideSplashScreen();
			startClient();
			return;
		}

		String password = new String(passwordArray);
		// authenticate new user
		try {
			userSession = authenticator.authenticateUser(email, password);
			if (userSession == null)
				throw new Exception("Your login information is incorrect!");
			System.out.println("User authenticated: " + userSession.getUser().getName());
			hideSplashScreen();
			startClient();
		} catch (Exception e) {
			splashScreen.showError(e.getMessage());
		}
	}

	public static void showSplashScreen(String flag) {
		if (loaded) {
			splashScreen.setVisible(true);
			return;
		}

		splashScreen = new SplashScreen(flag);
		splashScreen.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		splashScreen.setBackground(Color.BLACK);
		splashScreen.setSize(800, 600);
		splashScreen.setResizable(true);
		splashScreen.setLocationRelativeTo(null);
		splashScreen.setUndecorated(true);
		splashScreen.setVisible(true);
		if (flag.equals("load")) {
			Main.fetchFromDB();
			loaded = true;
		}
		splashScreen.loginPage();
		splashScreen.revalidate();
	}

	public static void hideSplashScreen() {
		splashScreen.setVisible(false);
		splashScreen.resetForm();
	}

	private static final JPanel ERROR_DIALOG_PANEL = new JPanel();

	public static void errorDialog(String message) {
		JOptionPane.showMessageDialog(ERROR_DIALOG_PANEL, message);
	}

}
