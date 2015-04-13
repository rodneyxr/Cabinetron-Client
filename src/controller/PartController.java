package controller;

import model.Part;
import model.PartsModel;
import view.Main;
import view.PartView;

public class PartController {

	private PartsModel partsModel;
	private InventoryController inventoryController;

	public PartController(PartsModel partsModel, InventoryController inventoryController) {
		this.partsModel = partsModel;
		this.inventoryController = inventoryController;
	}

	public void createPart(PartView pv) {
		Part newPart = null; // new part to be created

		try {
			// create the new part
			newPart = new Part(pv.getPartNumber(), pv.getPartName(), pv.getVendor(), pv.getQuantityUnit(), pv.getExternalPartNumber());

			// check if the part exists
			if (partsModel.partExists(newPart))
				throw new Exception("Error: A part with that part number already exists");

			// add part to parts model
			partsModel.addPart(newPart);
		} catch (Exception ex) {
			// unable to create the part
			pv.showError(ex.getMessage());
			return;
		}

		// hide the part view
		pv.setVisible(false);

		if (pv.getPart() != null) { // if template
			// create a new instance of the part view
			newPart.setPartView(pv.clone());
			// reset the view to match the part's information because it was changed while creating the template
			pv.syncWithPart();
		} else {
			// this is a new part so set the part view
			newPart.setPartView(pv);
		}
		//add part to the database
		Main.partGateway.addPartToDB(newPart);
	}

	public void updatePart(PartView pv) {
		Part oldPart = pv.getPart();
		try {
			// check if part to update has been created
			if (oldPart == null)
				throw new Exception("Error: Can't update a non-existing part.");

			// check if another part has the same part number the user is attempting to update to
			if (!pv.getPartNumber().equals(oldPart.getPartNumber()))
				if (partsModel.partExistsByPartNumber(pv.getPartNumber()))
					throw new Exception("Error: A part with that part number already exists");

			// update the part
			oldPart.updatePart(pv.getPartNumber(), pv.getPartName(), pv.getVendor(), pv.getQuantityUnit(), pv.getExternalPartNumber());
		} catch (Exception ex) {
			pv.showError(ex.getMessage());
			return;
		}

		// notify the inventory list
		inventoryController.updateInventoryList();

		//update part to db
		Main.partGateway.updatePartToDB(oldPart);
		// hide the part view
		pv.setVisible(false);
	}

}
