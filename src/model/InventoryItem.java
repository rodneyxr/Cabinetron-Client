package model;

import java.util.UUID;

import view.InventoryItemView;

public abstract class InventoryItem {

	// private Part part;
	private Item item;
	private UUID itemID;
	private double quantity;
	private Location location;

	private InventoryItemView iv;

	public InventoryItem(Item item, double quantity, Location location) throws Exception {
		update(true, item, null, quantity, location);
	}

	public InventoryItem(Item item, String itemID, double quantity, Location location) throws Exception {
		update(false, item, itemID, quantity, location);
	}

	public void update(double quantity, Location location) throws Exception {
		update(false, this.item, null, quantity, location);
	}

	private void update(boolean isNewItem, Item item, String itemID, double quantity, Location location) throws Exception {
		if (item != null)
			setItem(item);

		if (isNewItem) {
			generateItemID();
			// setPart(part);
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
		quantity = 0;
	}

	public void setQuantity(double quantity) throws Exception {
		if (quantity <= 0.0) {
			throw new Exception("Error: Quantity amount is too low");
		}
		this.quantity = quantity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) throws Exception {
		if (location.equals(Location.Unknown))
			throw new Exception("Error: Location cannot be 'Unknown'");
		this.location = location;
	}

	public InventoryItemView getItemView() {
		return iv;
	}

	public void setItemView(InventoryItemView iv) {
		this.iv = iv;
		iv.setInventoryItem(this);
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
