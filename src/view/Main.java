package view;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Authenticator;
import model.InventoryGateway;
import model.InventoryItem;
import model.InventoryModel;
import model.Part;
import model.PartGateway;
import model.PartsModel;
import model.ProductTemplate;
import model.ProductTemplateGateway;
import model.ProductTemplatesModel;
import model.Session;
import model.User;
import controller.InventoryController;
import controller.InventoryItemController;
import controller.PartController;
import controller.ProductTemplateController;

/**
 * 
 * CS 4743 Assignment 4 by Steven Petroff, Rodney Rodriguez
 *
 */

public class Main {
	// user session variables
	public static Session userSession;
	public static User user;

	public static PartsModel partsModel = new PartsModel();
	public static ProductTemplatesModel templatesModel = new ProductTemplatesModel();
	public static InventoryModel inventoryModel = new InventoryModel();;
	public static PartGateway partGateway;
	public static InventoryGateway inventoryGateway;
	public static ProductTemplateGateway productTemplateGateway;
	private static SplashScreen splashScreen;

	public static void main(String[] args) {
		new Runnable() {
			@Override
			public void run() {
				Main.showSplashScreen("load");
			}
		}.run();

	}

	private static void startClient() {
		if (partGateway != null && inventoryGateway != null && productTemplateGateway != null) {

			MainView mainView = new MainView(inventoryModel, partsModel, templatesModel);
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

		} else {
			new Runnable() {
				@Override
				public void run() {
					Main.showSplashScreen("noConnection");
				}
			}.run();
			try {
				Thread.sleep(2000);
				hideSplashScreen();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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
			e.toString();
			e.getMessage();
		}
	}

	public static void login(String name) {
		String email = "";
		if (name.equals("Tom Jones"))
			email = "TomJones@email.com";
		if (name.equals("Sue Smith"))
			email = "SueSmith@email.com";
		if (name.equals("Ragnar Nelson"))
			email = "RagnarNelson@email.com";

		// create new user
		user = new User(name, email);
		// authenticate new user

		try {
			userSession = Authenticator.authenticateUser(user);
			System.out.println("User authenticated: " + userSession.getUser().getName());
			hideSplashScreen();
			startClient();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void showSplashScreen(String flag) {
		splashScreen = new SplashScreen(flag);
		splashScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		splashScreen.setBackground(Color.BLACK);
		splashScreen.setSize(800, 600);
		splashScreen.setResizable(true);
		splashScreen.setLocationRelativeTo(null);
		splashScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		splashScreen.setUndecorated(true);
		splashScreen.setVisible(true);
		if (flag.equals("load"))
			Main.fetchFromDB();
		splashScreen.loginPage();
		splashScreen.revalidate();
	}

	public static void hideSplashScreen() {
		splashScreen.dispose();
	}

	private static final JPanel ERROR_DIALOG_PANEL = new JPanel();

	public static void errorDialog(String message) {
		JOptionPane.showMessageDialog(ERROR_DIALOG_PANEL, message);
	}

}
