package model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import view.Main;

public class InventoryModel {

	private DefaultListModel<InventoryItem> inventory;

	public InventoryModel() {
		inventory = new DefaultListModel<InventoryItem>();
	}

	public DefaultListModel<InventoryItem> getInventoryItemList() {
		return inventory;
	}

	public void setInventoryFromDB(DefaultListModel<InventoryItem> inventory) {
		this.inventory = inventory;
	}

	public InventoryItem getItem(int index) {
		return inventory.get(index);
	}

	public void addItem(InventoryItem item) {
		inventory.addElement(item);
		Main.inventoryGateway.addInvItemToDB(item);
	}

	/**
	 * 
	 * @param item
	 * @return true if the part with the same item already exists
	 */
	public boolean itemExists(InventoryItem item) {
		for (int i = 0; i < inventory.size(); i++) {
			if (item.equals(inventory.get(i)))
				return true;
		}
		return false;
		// return itemExistsByPartAndLocation(item.getPart(), item.getLocation());
	}
	
	public boolean productExistsAtLocation(InventoryItem item) {
		for (int i = 0; i < inventory.size(); i++) {
			if (item.equals(inventory.get(i)) && item.getLocation().equals(inventory.get(i).getLocation()))
				return true;
		}
		return false;
	}
	
	public InventoryItem getProductAtLocation(InventoryItem item) {
		for (int i = 0; i < inventory.size(); i++) {
			if (item.equals(inventory.get(i)) && item.getLocation().equals(inventory.get(i).getLocation()))
				return inventory.get(i);
		}
		return null;
	}

	public boolean itemExistsByPartAndLocation(Item part, Location location) {
		if (inventory.size() == 0)
			return false;
		for (int i = 0; i < inventory.size(); i++) {
			Object o;
			Part existingPart;
			if ((o = inventory.get(i).getItem()) instanceof Part)
				existingPart = (Part) o;
			else
				continue;
			Location existingLocation = inventory.get(i).getLocation();
			if (part.equals(existingPart) && location.equals(existingLocation))
				return true;
		}
		return false;
	}

	public void deleteItemsByPart(Part part) {
		if (part == null)
			return;
		ArrayList<InventoryItem> itemsToDelete = new ArrayList<InventoryItem>();
		for (int i = 0; i < inventory.size(); i++)
			if (inventory.get(i).getItem().equals(part))
				itemsToDelete.add(inventory.get(i));
		deleteItems(itemsToDelete);
	}

	public void deleteItems(ArrayList<InventoryItem> itemsToDelete) {
		if (itemsToDelete == null)
			return;
		for (InventoryItem item : itemsToDelete) {
			removeItem(item);
		}
	}

	/**
	 * Removes the item from the inventory
	 * 
	 * @param item
	 */
	public void removeItem(InventoryItem item) {
		if (item == null) {
			return;
		}
		Main.inventoryGateway.deleteInvItemFromDB(item);
		inventory.removeElement(item);
	}

	/**
	 * Removes a item at the index in the inventory
	 * 
	 * @param index
	 */
	public void removeItemAtIndex(int index) {
		if (index < 0 || index > inventory.size()) {
			return;
		}
		Main.inventoryGateway.deleteInvItemFromDB(inventory.get(index));
		inventory.remove(index);
	}
}
