package model;

public class PartInventoryItem extends InventoryItem {

	public PartInventoryItem(Part item, double quantity, Location location)
			throws Exception {
		super(item, quantity, location);
	}

	public PartInventoryItem(Part item, String itemID, double quantity,
			Location location) throws Exception {
		super(item, itemID, quantity, location);
	}

}
