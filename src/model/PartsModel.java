package model;

import java.util.UUID;

import javax.swing.DefaultListModel;

import view.Main;

public class PartsModel {

	private DefaultListModel<Part> parts;

	public PartsModel() {
		parts = new DefaultListModel<Part>();
	}

	public DefaultListModel<Part> getPartList() {
		return parts;
	}

	public void setPartsFromDB(DefaultListModel<Part> parts) {
		this.parts = parts;
	}

	public Part getPart(int index) {
		return parts.get(index);
	}

	public Part getPartById(UUID id) {
		Part newPart = null;
		for (int i = 0; i < parts.getSize(); i++) {
			newPart = parts.get(i);
			if (newPart.getItemID().equals(id)) {
				return newPart;
			}
		}
		return null;
	}

	public void addPart(Part part) {
		parts.addElement(part);
	}

	/**
	 * 
	 * @param part
	 * @return true if the part with the same partName already exists
	 */
	public boolean partExists(Part part) {
		return partExistsByPartNumber(part.getPartNumber());
	}

	/**
	 *
	 * @param part
	 * @return true if the part with the same partName already exists
	 */
	public boolean partExistsByPartNumber(String partNumber) {
		if (partNumber == null)
			return false;
		if (parts.size() == 0)
			return false;
		for (int i = 0; i < parts.size(); i++) {
			String existingPartNumber = parts.get(i).getPartNumber();
			if (partNumber.equals(existingPartNumber))
				return true;
		}
		return false;
	}

	/**
	 * Removes the part from the inventory
	 * 
	 * @param part
	 */
	public void removePart(Part part) {
		if (part == null) {
			System.out.println("The part is null.");
			return;
		}
		parts.removeElement(part);
		Main.partGateway.deletePartFromDB(part);
	}

	/**
	 * Removes a part at the index in the inventory
	 * 
	 * @param index
	 */
	public void removePartAtIndex(int index) {
		if (index < 0 || index > parts.size()) {
			System.out.println("The index is out of range.");
			return;
		}
		// delete part from part list and database
		Main.partGateway.deletePartFromDB(parts.get(index));
		parts.remove(index);
	}

}
