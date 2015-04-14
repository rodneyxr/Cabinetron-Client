package model;

import java.util.UUID;

import view.Main;
import view.panels.ProductTemplatePartView;

public class ProductTemplatePart {

	private UUID productTemplateID; // foreign key to Product Template
	private UUID partID; // foreign key to Part
	private double quantity;
	private ProductTemplatePartView ptpv;
	private ProductTemplate template;
	

	public ProductTemplatePart(UUID partID, double quantity) throws Exception {
		updateTemplatePart(true, UUID.randomUUID(), partID, quantity);
	}
	
	public ProductTemplatePart(UUID productTemplateID, UUID partID, double quantity) throws Exception {
		updateTemplatePart(true, productTemplateID, partID, quantity);
	}

	public void updateTemplatePart(UUID productTemplateID, UUID partID, double quantity) throws Exception {
		updateTemplatePart(false, productTemplateID, partID, quantity);
	}

	private void updateTemplatePart(boolean isNew, UUID productTemplateID, UUID partID, double quantity) throws Exception {
		setProductTemplateID(productTemplateID);
		setPartID(partID);
		setQuantity(quantity);
		
	}

	public UUID getProductTemplateID() {
		return productTemplateID;
	}

	public void setProductTemplateID(UUID productTemplateID) throws Exception {
		if (productTemplateID == null)
			throw new Exception("Error: Product Template ID is null.");
		this.productTemplateID = productTemplateID;
	}

	public void setProductTemplateID(String uuidString) throws Exception {
		this.productTemplateID = UUID.fromString(uuidString);
	}

	public UUID getPartID() {
		return partID;
	}

	public void setPartID(UUID partID) throws Exception {
		if (partID == null)
			throw new Exception("Error: Part ID is null.");
		this.partID = partID;
	}

	public void setPartID(String uuidString) throws Exception {
		this.partID = UUID.fromString(uuidString);
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) throws Exception {
		if (quantity <= 0.0)
			throw new Exception("Error: Quantity amount is too low");
		this.quantity = quantity;
	}
	
	
	public String toString(){
		return this.getQuantity() +"\t\t"+ Main.partsModel.getPartById(partID).toString();
	}

	public ProductTemplatePartView getView() {
		return ptpv;
	}

	public void setView(ProductTemplatePartView ptpv) {
		this.ptpv = ptpv;
	}

	public ProductTemplate getTemplate() {
		return template;
	}

	public void setTemplate(ProductTemplate template) {
		this.template = template;
	}

}
