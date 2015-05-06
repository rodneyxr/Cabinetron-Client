package view;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import view.panels.ItemLogPanel;

public class ItemLogView extends SessionView {
	private static final long serialVersionUID = 1L;

	private ItemLogPanel panel;

	public ItemLogView() {
		super("Cabinetron Inventory: Logged in as " + Main.userSession.getUser().getName());

		try {
			setIconImage(ImageIO.read(new File("src/res/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(480, 420);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// create the main view
		panel = new ItemLogPanel();
		add(panel, BorderLayout.CENTER);

		// link the lists to their respective models
		// TODO: set the model item.getLog()
		// panel.listInventoryItems.setModel(inventoryModel.getInventoryItemList());

	}

	public int getSelectedLogEntryIndex() {
		return panel.listLogEntries.getSelectedIndex();
	}

	public void updateLogEntryList() {
		panel.listLogEntries.updateUI();
	}

	// public void registerListener(InventoryController inventoryController) {
	// // inventory
	// panel.listInventoryItems.addMouseListener(inventoryController);
	// panel.buttonAddInventoryItem.addActionListener(inventoryController);
	// panel.buttonDeleteInventoryItem.addActionListener(inventoryController);
	// }

}
