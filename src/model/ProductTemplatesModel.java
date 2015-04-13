package model;

import java.util.UUID;

import javax.swing.DefaultListModel;

import view.Main;

public class ProductTemplatesModel {

	private DefaultListModel<ProductTemplate> templates;

	public ProductTemplatesModel() {
		templates = new DefaultListModel<ProductTemplate>();
	}

	public DefaultListModel<ProductTemplate> getTemplateList() {
		return templates;
	}

	public void setTemplatesFromDB(DefaultListModel<ProductTemplate> templates) {
		this.templates = templates;
	}

	public ProductTemplate getTemplate(int index) {
		return templates.get(index);
	}

	public void addTemplate(ProductTemplate template) {
		templates.addElement(template);
	}

	public void removeTemplate(ProductTemplate template) {
		if (template == null) {
			System.out.println("The template is null");
			return;
		}

		templates.removeElement(template);
		// TODO: update database
		Main.productTemplateGateway.deleteProductTemplate(template);
	}

	public void removeTemplateAtIndex(int index) {
		if (index < 0 || index >= templates.size()) {
			System.out.println("The index is out of range.");
			return;
		}

		// TODO: delete template from database
		Main.productTemplateGateway.deleteProductTemplate(templates.get(index));
		templates.remove(index);
	}

	/**
	 * 
	 * @param template
	 * @return true if the template already exists
	 */
	public boolean templateExists(ProductTemplate template) {
		for (int i = 0; i < templates.size(); i++) {
			if (template.equals(templates.get(i)))
				return true;
		}
		return false;
	}

	public boolean templateExistsByProductNumber(String productNumber) {
		if (templates.size() == 0)
			return false;
		for (int i = 0; i < templates.size(); i++) {
			String existingProductNumber = templates.get(i).getProductNumber();
			if (productNumber.equals(existingProductNumber))
				return true;
		}
		return false;
	}

	public ProductTemplate getTemplateByID(UUID id) {
		ProductTemplate pt = null;
		for (int i = 0; i < templates.getSize(); i++) {
			pt = templates.get(i);
			if (pt.getItemID().equals(id)) {
				return pt;
			}
		}
		return null;
	}
}
