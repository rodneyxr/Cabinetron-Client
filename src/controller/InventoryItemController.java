package controller;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import model.InventoryItem;
import model.Item;
import model.Part;
import model.PartInventoryItem;
import model.ProductInventoryItem;
import model.ProductTemplate;
import model.ProductTemplatePart;
import view.InventoryItemView;
import view.Main;

public class InventoryItemController {

	private InventoryController inventoryController;

	public InventoryItemController(InventoryController ic) {
		this.inventoryController = ic;
	}

	private void templateToProductQuantity(ProductTemplate pt, String locationString, double multiplier) throws Exception {
		// cache lists for better performance
		DefaultListModel<ProductTemplatePart> ptParts = pt.getTemplateParts();
		DefaultListModel<InventoryItem> invItems = Main.inventoryModel.getInventoryItemList();
		// array to hold all quantity needed for the template parts
		int counter = 0;
		double[][] indexCost = new double[ptParts.size()][2];
		for (int i = 0; i < ptParts.size(); i++) {
			ProductTemplatePart templatePart = ptParts.get(i);

			for (int k = 0; k < invItems.size(); k++) {
				InventoryItem temp = invItems.get(k);
				if (templatePart.getPartID().toString().equals(temp.getItem().getItemID().toString())) {
					if (temp.getQuantity() >= templatePart.getQuantity() * multiplier) {
						if (temp.getLocation().name().equals(locationString)) {
							indexCost[i][0] = k;
							indexCost[i][1] = templatePart.getQuantity() * multiplier;
							counter++;
						}
					} else {

					}
				}// end if: partID
			}// end for: k
		}// end for: i
		if (counter == 0)
			throw new Exception("Error: Not enough quantities at that location.");
		// if here, then inventory has correct amount of parts
		for (int i = 0; i < ptParts.size(); i++) {
			try {
				// update locally
				invItems.get((int) indexCost[i][0]).setQuantity(invItems.get((int) indexCost[i][0]).getQuantity() - indexCost[i][1]);
				// update DB
				// Main.inventoryGateway.updateInvItemToDB(invItems.get((int) indexCost[i][0]));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void addInventoryItem(InventoryItemView iiv) {
		InventoryItemView parent = iiv;
		parent.hideError();

		Item item = parent.selectedItem;
		if (item == null) {
			parent.showError("Error: You must select a part");
			return;
		}

		InventoryItem newItem = null; // new item to be added
		try {
			if (item instanceof Part) {
				newItem = new PartInventoryItem((Part) item, parent.getQuantity(), parent.getItemLocation());
			} else if (item instanceof ProductTemplate) {
				newItem = new ProductInventoryItem((ProductTemplate) item, parent.getQuantity(), parent.getItemLocation());
				try {
					templateToProductQuantity(Main.templatesModel.getTemplateByID(item.getItemID()), parent.getItemLocation().name().toString(), parent.getQuantity());
				} catch (Exception e1) {
					throw new Exception(e1.getMessage());
				}
			}

			if (InventoryItemView.inventoryModel.itemExists(newItem)) {
				if (item instanceof ProductTemplate && InventoryItemView.inventoryModel.productExistsAtLocation(newItem)) {
					// update quantity of existing producttemplate
					InventoryItem invItem = InventoryItemView.inventoryModel.getProductAtLocation(newItem);
					invItem.setQuantity(invItem.getQuantity() + newItem.getQuantity());
					// update db
					Main.inventoryGateway.updateInvItemToDB(invItem);
					parent.setVisible(false);
					parent.setUpdateOnly();
					newItem.setItemView(parent);
					return;
				} else {
					throw new Exception("Error: That inventory item already exists");
				}

			}
			InventoryItemView.inventoryModel.addItem(newItem);
		} catch (Exception ex) {
			parent.showError(ex.getMessage());
			return;
		}

		parent.setVisible(false);
		parent.setUpdateOnly();
		newItem.setItemView(parent);
	}

	public void updateInventoryItem(InventoryItemView iiv) {
		InventoryItemView parent = iiv;

		InventoryItem oldItem = parent.getInventoryItem();
		try {
			// check if item to update has been created
			if (oldItem == null)
				throw new Exception("Error: Can't update a non-existing part.");

			// check if another item has the same part/location
			if (!oldItem.getLocation().equals(parent.getItemLocation()))
				if (InventoryItemView.inventoryModel.itemExistsByPartAndLocation(oldItem.getItem(), parent.getItemLocation()))
					throw new Exception("Error: That item is already in inventory");

			// update the part locally
			if (oldItem instanceof ProductInventoryItem) {
				double q;
				if ((q = (parent.getQuantity() - oldItem.getQuantity())) > 0) {
					templateToProductQuantity((ProductTemplate) oldItem.getItem(), oldItem.getLocation().name(), q);
					oldItem.update(parent.getQuantity(), parent.getItemLocation());
					Main.inventoryGateway.updateInvItemToDB(oldItem);
				} else {
					oldItem.update(parent.getQuantity(), parent.getItemLocation());
					Main.inventoryGateway.updateInvItemToDB(oldItem);
				}
			} else {
				oldItem.update(parent.getQuantity(), parent.getItemLocation());
			}
		} catch (Exception ex) {
			parent.showError(ex.getMessage());
			return;
		}

		// compare timestamps and reload if different
		long timeStamp = Main.inventoryGateway.getInvItemTimestamp(oldItem);
		long oldTimeStamp = inventoryController.itemTimeStamp;

		if (timeStamp != oldTimeStamp) {
			JOptionPane.showMessageDialog(parent.panel, "This InventoryItem has been modified since you last opened it.\n" + "\tYour changes have NOT been saved.\n");
			// set saved time stamp to that of the database
			inventoryController.itemTimeStamp = timeStamp;
			// retrieve new information from database and reload
			InventoryItem i = Main.inventoryGateway.getInvItemFromDBById(oldItem);
			try {
				oldItem.update(i.getQuantity(), i.getLocation());
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			inventoryController.updateInventoryList();
			parent.syncWithItem();
			parent.setVisible(false);
			parent.setVisible(true);
			return;
		}

		inventoryController.updateInventoryList();
		Main.inventoryGateway.updateInvItemToDB(oldItem);

		// hide the item view
		parent.setVisible(false);
	}

}
