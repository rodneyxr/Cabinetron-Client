package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import model.InventoryItem;
import model.InventoryModel;
import model.Item;
import model.Location;
import model.PartsModel;
import view.panels.InventoryItemPanel;
import controller.InventoryController;
import controller.InventoryItemController;

public class InventoryItemView extends SessionView {
	private static final long serialVersionUID = 1L;

	// inventory model
	public static InventoryModel inventoryModel;
	public static PartsModel partsModel;
	public static InventoryController inventoryController;
	public static InventoryItemController inventoryItemController;
	private InventoryItem item;
	public Item selectedItem;

	// GUI items
	public InventoryItemPanel panel; // panel to hold all elements
	private ItemSelectorView itemSelectorView;

	public InventoryItemView() {
		super("Cabinetron: Inventory Item");
		this.itemSelectorView = new ItemSelectorView();

		try {
			setIconImage(ImageIO.read(new File("src/res/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(480, 520);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new InventoryItemPanel();
		add(panel, BorderLayout.CENTER);

		activateListeners();
	}

	public InventoryItem getInventoryItem() {
		return item;
	}

	public void setInventoryItem(InventoryItem item) {
		this.item = item;
		syncWithItem();
	}

	public void syncWithItem() {
		if (item == null)
			return;
		panel.textFieldItemID.setText(item.getItemID().toString());
		panel.buttonSelectedPart.setText(item.getItem().getItemName());
		panel.textFieldQuantity.setText(Double.toString(item.getQuantity()));
		panel.comboBoxLocation.setSelectedItem(item.getLocation());
	}

	public void showError(String message) {
		panel.labelError.setText(message);
		panel.labelError.setVisible(true);
	}

	public void hideError() {
		panel.labelError.setText("");
		panel.labelError.setVisible(false);
	}

	public Item getSelectedItem() {
		return selectedItem;
	}

	public double getQuantity() throws NumberFormatException {
		return Double.parseDouble(panel.textFieldQuantity.getText());
	}

	public Location getItemLocation() {
		return (Location) panel.comboBoxLocation.getSelectedItem();
	}

	private void activateListeners() {
		// open part picker button
		panel.buttonSelectedPart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InventoryItemView.this.itemSelectorView.setVisible(true);
			}
		});

		// add inventory item to inventory list button
		panel.buttonAddItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				switch (e.getActionCommand()) {
				case "Add Item":
//					System.out.println("InventoryItemView: Add Item button clicked!");
					try {
						inventoryItemController.addInventoryItem(InventoryItemView.this);
					} catch (Exception ex) {
						showError(ex.getMessage());
					}
					break;

				case "View Log":
//					System.out.println("InventoryItemView: View Log button clicked!");
					new ItemLogView(item).setVisible(true);
					break;
				}

			}
		});

		// update inventory item in inventory list button
		panel.buttonUpdateItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					inventoryItemController.updateInventoryItem(InventoryItemView.this);
				} catch (Exception ex) {
					showError(ex.getMessage());
				}
			}
		});

		ActionListener buttonSelectListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hideError();
				Item item = null;
				String errorItem = "item";
				switch (e.getActionCommand()) {
				case "Select Part":
					item = itemSelectorView.getSelectedPartInList();
					errorItem = "part";
					break;
				case "Select Template":
					item = itemSelectorView.getSelectedTemplateInList();
					errorItem = "template";
					break;
				}

				if (item == null) {
					itemSelectorView.hideView();
					toFront();
					showError("Error: No " + errorItem + " was selected");
					return;
				}

				selectedItem = item;
				panel.buttonSelectedPart.setText(item.getItemName());
				itemSelectorView.hideView();
			}
		};

		// select part (inside item picker) button
		itemSelectorView.getSelectPartButton().addActionListener(buttonSelectListener);
		itemSelectorView.getSelectTemplateButton().addActionListener(buttonSelectListener);
	}

	/**
	 * set this item as old and only allow updating
	 */
	public void setUpdateOnly() {
		panel.buttonSelectedPart.setEnabled(false);
		panel.buttonAddItem.setText("View Log");
	}

	public static void assignItemView(InventoryItem item) {
		InventoryItemView iv = new InventoryItemView();
		item.setItemView(iv);
	}

	@Override
	public void setVisible(boolean b) {
		syncWithItem();
		super.setVisible(b);
	}
}
