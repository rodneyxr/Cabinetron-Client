package model;

public class ProductInventoryItem extends InventoryItem {

	public ProductInventoryItem(ProductTemplate item, double quantity, Location location) throws Exception {
		super(item, quantity, location);
	}

	public ProductInventoryItem(ProductTemplate item, String itemID, double quantity, Location location) throws Exception {
		super(item, itemID, quantity, location);
	}

}
