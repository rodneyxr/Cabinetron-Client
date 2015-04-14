package view;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import model.InventoryModel;
import model.PartsModel;
import model.ProductTemplatesModel;
import view.panels.MainPanel;
import controller.InventoryController;

public class MainView extends JFrame {
	private static final long serialVersionUID = 1L;

	private MainPanel panel;

	/**
	 * 
	 * @param inventory
	 */
	public MainView(InventoryModel inventoryModel, PartsModel partsModel, ProductTemplatesModel templatesModel) {
		super("Cabinetron Inventory: Logged in as " + Main.userSession.getUser().getName());

		try {
	        setIconImage(ImageIO.read(new File("src/res/icon.png")));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		// create the main view
		panel = new MainPanel();
		add(panel, BorderLayout.CENTER);

		// link the lists to their respective models
		panel.listInventoryItems.setModel(inventoryModel.getInventoryItemList());
		panel.listParts.setModel(partsModel.getPartList());
		panel.listTemplates.setModel(templatesModel.getTemplateList());

		if (!Main.userSession.canViewInventory()) {
			panel.tabbedPanel.remove(panel.panelInventory);
		}
		if (!Main.userSession.canViewParts()) {
			panel.tabbedPanel.remove(panel.panelParts);
		}
		if (!Main.userSession.canViewProductTemplates()) {
			panel.tabbedPanel.remove(panel.panelTemplates);
		}
	}

	public int getSelectedInventoryItemIndex() {
		return panel.listInventoryItems.getSelectedIndex();
	}

	public int getSelectedPartIndex() {
		return panel.listParts.getSelectedIndex();
	}

	public int getSelectedProductTemplateIndex() {
		return panel.listTemplates.getSelectedIndex();
	}

	public void updateInventoryList() {
		panel.listInventoryItems.updateUI();
	}

	public void updatePartsList() {
		panel.listParts.updateUI();
	}

	public void updateProductTemplateList() {
		panel.listParts.updateUI();
	}

	/**
	 * 
	 * @param inventoryController
	 */
	public void registerListener(InventoryController inventoryController) {
		// inventory
		panel.listInventoryItems.addMouseListener(inventoryController);
		panel.buttonAddInventoryItem.addActionListener(inventoryController);
		panel.buttonDeleteInventoryItem.addActionListener(inventoryController);

		// parts
		panel.listParts.addMouseListener(inventoryController);
		panel.buttonAddPart.addActionListener(inventoryController);
		panel.buttonDeletePart.addActionListener(inventoryController);

		// product templates
		panel.listTemplates.addMouseListener(inventoryController);
		panel.buttonAddTemplate.addActionListener(inventoryController);
		panel.buttonDeleteTemplate.addActionListener(inventoryController);
	}

}
