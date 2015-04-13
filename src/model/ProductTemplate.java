package model;

import java.util.UUID;

import javax.swing.DefaultListModel;

import view.ProductTemplateView;

public class ProductTemplate implements Item {

	private UUID templateID; // auto-generated unique ID
	private String productNumber; // alphanumeric and symbols, max length 20
	private String productDescription; // alphanumeric and symbols, max length
										// 255
	public DefaultListModel<ProductTemplatePart> templateParts;
	private ProductTemplateView ptv;

	// Constraint: product #'s MUST start with �A� and must be unique

	/**
	 * To be used when creating a new ProductTemplate and generate a new templateID
	 * 
	 * @param productNumber
	 * @param productDescription
	 * @throws Exception
	 */
	public ProductTemplate(String productNumber, String productDescription) throws Exception {
		updateTemplate(true, null, productNumber, productDescription, new DefaultListModel<ProductTemplatePart>());
	}

	/**
	 * To be used when recreating a template from a database and construct a new ProductTemplateView and reload the information
	 * 
	 * @param templateID
	 * @param productNumber
	 * @param productDescription
	 * @throws Exception
	 */
	public ProductTemplate(String templateID, String productNumber, String productDescription) throws Exception {
		updateTemplate(false, templateID, productNumber, productDescription, new DefaultListModel<ProductTemplatePart>());
	}

	public void updateTemplate(String productNumber, String productDescription) throws Exception {
		updateTemplate(false, null, productNumber, productDescription, null);
	}

	private void updateTemplate(boolean isNew, String templateID, String productNumber, String productDescription, DefaultListModel<ProductTemplatePart> templateParts) throws Exception {
		// set product number
		setProductNumber(productNumber);

		// set description
		setDescription(productDescription);

		// set template parts list
		if (templateParts != null)
			setTemplateParts(templateParts);

		// generate template id if new
		if (isNew)
			generateTemplateID();
		if (templateID != null) {
			this.setTemplateID(templateID);

			ProductTemplateView.assignProductTemplateView(this);
			ptv.partsList = templateParts;
			ptv.setTemplatePartList();
		}

	}

	public UUID getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String uuidString) throws Exception {
		this.templateID = UUID.fromString(uuidString);
		updatePartProductIDs();
	}

	private void generateTemplateID() throws Exception {
		this.templateID = UUID.randomUUID();
		updatePartProductIDs();
	}

	private void updatePartProductIDs() throws Exception {
		for (int i = 0; i < templateParts.size(); i++)
			templateParts.get(i).setProductTemplateID(templateID);
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) throws Exception {
		productNumber = productNumber.toUpperCase();
		if (productNumber.length() == 0) {
			throw new Exception("Error: You need to enter a product number!");
		}
		if (!productNumber.startsWith("A")) {
			throw new Exception("Error: The product number must start with 'A'");
		}
		if (productNumber.length() < 2) {
			throw new Exception("Error: The product number is too short");
		}
		if (productNumber.length() > 20) {
			throw new Exception("Error: The product number is too long");
		}
		this.productNumber = productNumber;
	}

	public String getDescription() {
		return productDescription;
	}

	public void setDescription(String description) throws Exception {
		if (productNumber.length() > 255)
			throw new Exception("The product description is too long");
		this.productDescription = description;
	}

	public ProductTemplateView getView() {
		return ptv;
	}

	public void setView(ProductTemplateView ptv) {
		this.ptv = ptv;
		ptv.setProductTemplate(this);
	}

	public DefaultListModel<ProductTemplatePart> getTemplateParts() {
		return templateParts;
	}

	public void setTemplateParts(DefaultListModel<ProductTemplatePart> templateParts) {
		this.templateParts = templateParts;
		if (ptv == null) {
			return;
		}
		ptv.setTemplatePartModel(templateParts);
	}

	public void addTemplatePart(ProductTemplatePart templatePart) {
		templateParts.addElement(templatePart);
	}

	public void removeTemplatePart(ProductTemplatePart templatePart) {
		templateParts.removeElement(templatePart);
	}

	public void removeTemplatePartAtIndex(int index) {
		if (index < 0 || index > templateParts.size()) {
			System.out.println("The index is out of range.");
			return;
		}
		templateParts.remove(index);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProductTemplate))
			return false;
		ProductTemplate other = (ProductTemplate) object;
		return productNumber.equals(other.getProductNumber());
	}

	@Override
	public String toString() {
		return productNumber + ": " + productDescription;
	}

	@Override
	public UUID getItemID() {
		return getTemplateID();
	}

	@Override
	public String getItemName() {
		return getProductNumber();
	}

	@Override
	public QuantityUnit getQuantityUnit() {
		return QuantityUnit.Pieces;
	}

}
