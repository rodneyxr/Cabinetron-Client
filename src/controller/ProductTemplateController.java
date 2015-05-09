package controller;

import model.Part;
import model.ProductTemplate;
import model.ProductTemplatePart;
import model.ProductTemplatesModel;
import view.Main;
import view.ProductTemplateView;
import view.panels.ProductTemplatePartView;

public class ProductTemplateController {

	private ProductTemplatesModel templatesModel;
	public boolean isNew;

	public ProductTemplateController(ProductTemplatesModel templatesModel) {
		this.templatesModel = templatesModel;
	}

	public void createProductTemplate(ProductTemplateView ptv) throws Exception {
		// create the new template
		ProductTemplate template = new ProductTemplate(ptv.getProductNumber(), ptv.getProductDescription());

		if (templatesModel.templateExists(template))
			throw new Exception("Error: That product template already exists.");

		// set template list to that of views
		template.setTemplateParts(ptv.getPartsList());

		// add template uuid to its parts uuid fields
		for (int i = 0; i < template.getTemplateParts().getSize(); i++) {
			ProductTemplatePart ptp = template.getTemplateParts().get(i);
			ptp.setProductTemplateID(template.getTemplateID());
		}

		// add template to the database
		Main.productTemplateGateway.addProductTemplatetoDB(template);

		// add template to template list
		templatesModel.addTemplate(template);

		// hide the template view
		ptv.setVisible(false);

		// this is a new template so set the view
		template.setView(ptv);
	}

	public void updateProductTemplate(ProductTemplateView ptv) throws Exception {
		ProductTemplate oldTemplate = ptv.getProductTemplate();

		// check if template to update has been created
		if (oldTemplate == null)
			throw new Exception("Error: Can't update a non-existing part.");

		// check if another template has the same product number
		if (!oldTemplate.getProductNumber().equals(ptv.getProductNumber()))
			if (templatesModel.templateExistsByProductNumber(ptv.getProductNumber()))
				throw new Exception("Error: That product template already exists.");

		// update the product template in database
		Main.productTemplateGateway.updateProductTemplate(oldTemplate, ptv.getProductNumber(), ptv.getProductDescription());

		// update the template
		oldTemplate.updateTemplate(ptv.getProductNumber(), ptv.getProductDescription());

		// hide the view
		ptv.setVisible(false);
	}

	public void addProductTemplatePart(ProductTemplateView ptv) throws Exception {
		ProductTemplatePartView templatePartView = new ProductTemplatePartView(ptv);
		ptv.setRightPanel(templatePartView);
	}

	public void deleteProductTemplatePart(ProductTemplateView ptv) throws Exception {
		ProductTemplatePart tp = ptv.getSelectedPart();
		Main.productTemplateGateway.deleteProductTemplatePart(tp.getProductTemplateID().toString(), tp);
		ptv.removePartByPartID(tp.getPartID().toString());
	}

	public void createProductTemplatePart(ProductTemplate template, Part selectedPart, double quantity) throws Exception {
		if (selectedPart == null)
			throw new Exception("Error: You need to select a part.");
		if (template == null) {
		} else {
			ProductTemplatePart templatePart = new ProductTemplatePart(template.getTemplateID(), selectedPart.getItemID(), quantity);
			Main.productTemplateGateway.addProductTemplatePart(templatePart);
			template.addTemplatePart(templatePart);
		}
	}

	public void updateProductTemplatePart(ProductTemplatePart templatePart, double quantity) throws Exception {
		if (templatePart == null)
			throw new Exception("Error: That product template part does not exists.");
		Main.productTemplateGateway.updateProductTemplatePart(templatePart, quantity);
		templatePart.setQuantity(quantity);
	}

}
