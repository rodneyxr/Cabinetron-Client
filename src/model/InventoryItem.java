package model;

import java.util.UUID;

import logging.InventoryItemLogEntry;
import view.InventoryItemView;

public abstract class InventoryItem {

	private Item item;
	private UUID itemID;
	private double quantity;
	private Location location;
	private InventoryItemLog log;

	private InventoryItemView iv;

	private boolean created = false;

	public InventoryItem(Item item, double quantity, Location location) throws Exception {
		update(true, item, null, quantity, location);
		created = true;
	}

	public InventoryItem(Item item, String itemID, double quantity, Location location) throws Exception {
		update(false, item, itemID, quantity, location);
		created = true;
	}

	public void update(double quantity, Location location) throws Exception {
		update(false, this.item, null, quantity, location);
	}

	private void update(boolean isNewItem, Item item, String itemID, double quantity, Location location) throws Exception {
		if (item != null)
			setItem(item);

		if (isNewItem) {
			generateItemID();
		}

		if (!isNewItem && quantity == 0.0)
			setZeroQuantity();
		else
			setQuantity(quantity);

		setLocation(location);

		if (itemID != null) {
			setItemID(itemID);
			InventoryItemView.assignItemView(this);
			iv.setUpdateOnly();
		}

		if (isNewItem) {
			InventoryItemLog.itemLogGateway.addLogEntry(this.getItemID(), new InventoryItemLogEntry("Insert \"Added\""));
		}
	}

	public Item getItem() {
		return item;
	}

	private void setItem(Item item) throws Exception {
		if (item == null)
			throw new Exception("Error: Part is null");
		this.item = item;
	}

	public UUID getItemID() {
		return itemID;
	}

	private void setItemID(String uuidString) throws IllegalArgumentException {
		this.itemID = UUID.fromString(uuidString);
	}

	private void generateItemID() {
		this.itemID = UUID.randomUUID();
	}

	public double getQuantity() {
		return quantity;
	}

	private void setZeroQuantity() {
		if (created && quantity != 0) {
			InventoryItemLogEntry logEntry = new InventoryItemLogEntry( //
					String.format("Change (quantity modified) \"Quantity changed from <%.2f> to <%.2f>\"", quantity, 0d));
			getLog().addLogEntryToDB(this.getItemID(), logEntry);
		}
		quantity = 0;
	}

	public void setQuantity(double quantity) throws Exception {
		if (quantity <= 0.0) {
			throw new Exception("Error: Quantity amount is too low");
		}
		if (created && this.quantity != quantity) {
			InventoryItemLogEntry logEntry = new InventoryItemLogEntry( //
					String.format("Change (quantity modified) \"Quantity changed from <%.2f> to <%.2f>\"", this.quantity, quantity));
			getLog().addLogEntryToDB(this.getItemID(), logEntry);
		}
		this.quantity = quantity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) throws Exception {
		if (location.equals(Location.Unknown))
			throw new Exception("Error: Location cannot be 'Unknown'");
		if (created && !location.equals(this.location)) {
			InventoryItemLogEntry logEntry = new InventoryItemLogEntry( //
					"Change (location modified) \"Location changed from <" + this.location + "> to <" + location + ">\"");
			getLog().addLogEntryToDB(this.getItemID(), logEntry);
		}
		this.location = location;
	}

	public InventoryItemView getItemView() {
		return iv;
	}

	public void setItemView(InventoryItemView iv) {
		this.iv = iv;
		iv.setInventoryItem(this);
	}

	public InventoryItemLog getLog() {
		if (log == null) {
			log = new InventoryItemLog(InventoryItemLog.itemLogGateway.getLogListModel(this.getItemID()));
		}
		return log;
	}

	@Override
	public boolean equals(Object object) {
		InventoryItem other = (InventoryItem) object;
		return item.equals(other.getItem()) && location.equals(other.getLocation());
	}

	@Override
	public String toString() {
		String quantityString = "";
		if (item.getQuantityUnit().allowFractions)
			quantityString = String.format("%.2f", getQuantity());
		else
			quantityString = String.format("%.0f", getQuantity());
		return String.format("%s %s of %s at %s", quantityString, item.getQuantityUnit(), item.getItemName(), getLocation());
	}

}
