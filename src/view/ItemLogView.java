package view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import logging.StateObserver;
import model.InventoryItem;
import view.panels.ItemLogPanel;

public class ItemLogView extends SessionView implements ClientItemObserver {
	private static final long serialVersionUID = 1L;

	private ItemLogPanel panel;
	private InventoryItem inventoryItem;

	public ItemLogView(InventoryItem inventoryItem) {
		super("Cabinetron Inventory: Logged in as " + Main.userSession.getUser().getName());
		this.inventoryItem = inventoryItem;

		try {
			setIconImage(ImageIO.read(new File("src/res/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(900, 500);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				StateObserver.unregister(ItemLogView.this);
			}
		});

		// create the main view
		panel = new ItemLogPanel();
		add(panel, BorderLayout.CENTER);

		// link the lists to their respective models
		panel.listLogEntries.setModel(inventoryItem.getLog().getEntries());

		StateObserver.register(this);

	}

	public int getSelectedLogEntryIndex() {
		return panel.listLogEntries.getSelectedIndex();
	}

	public void updateLogEntryList() {
		panel.listLogEntries.updateUI();
	}

	@Override
	public void itemChanged(UUID item) {
		if (inventoryItem.getItemID().equals(item)) {
			inventoryItem.refreshLog();
			System.out.println("ItemLogView: refreshed log");
			// panel.listLogEntries.updateUI(); // TODO: may need this to update list
		} else {
			System.out.println("ItemLogView: ignoring item changed. not observing for this item");
		}
	}
}
