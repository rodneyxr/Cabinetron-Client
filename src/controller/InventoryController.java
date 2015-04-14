package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.InventoryItem;
import model.InventoryModel;
import model.Part;
import model.PartsModel;
import model.ProductTemplate;
import model.ProductTemplatesModel;
import view.InventoryItemView;
import view.Main;
import view.MainView;
import view.PartView;
import view.ProductTemplateView;

public class InventoryController implements MouseListener, ActionListener {
	private InventoryModel inventoryModel;
	private PartsModel partsModel;
	private ProductTemplatesModel templatesModel;
	private MainView mainView;
	public long itemTimeStamp;

	public InventoryController(InventoryModel inventoryModel, PartsModel partsModel, ProductTemplatesModel templatesModel, MainView mainView) {
		this.inventoryModel = inventoryModel;
		this.partsModel = partsModel;
		this.templatesModel = templatesModel;
		this.mainView = mainView;
	}

	public void updateInventoryList() {
		mainView.updateInventoryList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Add Inventory Item":
			if (!Main.userSession.canAddInventory()) {
				Main.errorDialog("You don't have permission to do that!");
				return;
			}
			InventoryItemView iv = new InventoryItemView();
			iv.setVisible(true);
			break;

		case "Delete Inventory Item":
			if (!Main.userSession.canDeleteInventory()) {
				Main.errorDialog("You don't have permission to do that!");
				return;
			}
			int itemIndex = mainView.getSelectedInventoryItemIndex();
			if (itemIndex < 0)
				return;
			inventoryModel.getItem(itemIndex).getItemView().setVisible(false);
			inventoryModel.removeItemAtIndex(itemIndex);
			break;

		case "Add Part": // add part button clicked
			if (!Main.userSession.canAddParts()) {
				Main.errorDialog("You don't have permission to do that!");
				return;
			}

			// create part view
			PartView pv = new PartView();
			pv.setVisible(true);
			break;

		case "Delete Part": // delete part button clicked
			if (!Main.userSession.canDeleteParts()) {
				Main.errorDialog("You don't have permission to do that!");
				return;
			}
			int partIndex = mainView.getSelectedPartIndex();
			if (partIndex < 0)
				return;
			if (Main.productTemplateGateway.partIsInTemplate(partsModel.getPart(partIndex).getItemID().toString()) == true) {
				JPanel panel = new JPanel();
				JOptionPane.showMessageDialog(panel, "Error: You cannot delete this part because\n" + "This part is associated with a Product Template.");
				return;
			}
			// iterate through inventory list and delete all associated items
			inventoryModel.deleteItemsByPart(partsModel.getPart(partIndex));
			partsModel.getPart(partIndex).getPartView().setVisible(false);
			partsModel.removePartAtIndex(partIndex);
			break;

		case "Add Template":
			if (!Main.userSession.canAddProductTemplates()) {
				Main.errorDialog("You don't have permission to do that!");
				return;
			}
			ProductTemplateView ptv = new ProductTemplateView();
			ptv.setVisible(true);
			break;

		case "Delete Template":
			if (!Main.userSession.canDeleteProductTemplates()) {
				Main.errorDialog("You don't have permission to do that!");
				return;
			}
			int templateIndex = mainView.getSelectedProductTemplateIndex();
			if (templateIndex < 0)
				return;
			templatesModel.getTemplate(templateIndex).getView().dispose();
			templatesModel.removeTemplateAtIndex(templateIndex);
			break;

		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = ControllerUtils.getDoubleClickedItemInList(e);
		if (o == null)
			return;

		if (o instanceof Part) {
			if (!Main.userSession.canAddParts()) {
				Main.errorDialog("You don't have permission to do that!");
				return;
			}
			Part part = (Part) o;
			part.getPartView().hideError();
			part.getPartView().setVisible(true);
		} else if (o instanceof InventoryItem) {
			if (!Main.userSession.canAddInventory()) {
				Main.errorDialog("You don't have permission to do that!");
				return;
			}
			InventoryItem item = (InventoryItem) o;
			itemTimeStamp = System.currentTimeMillis();
			Main.inventoryGateway.updateInvitemTimestamp(item, itemTimeStamp);
			item.getItemView().hideError();
			item.getItemView().setVisible(true);
		} else if (o instanceof ProductTemplate) {
			if (!Main.userSession.canAddProductTemplates()) {
				Main.errorDialog("You don't have permission to do that!");
				return;
			}
			ProductTemplate template = (ProductTemplate) o;
			template.getView().hideError();
			template.getView().setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// do not need
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// do not need
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// do not need
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// do not need
	}
}
