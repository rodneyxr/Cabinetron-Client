package model;

import java.util.UUID;

import view.PartView;

public class Part implements Item {

	private UUID partID;
	private String partNumber;
	private String partName;
	private String vendor;
	private String externalPartNumber;
	private QuantityUnit quantityUnit;
	private PartView pv;

	/**
	 * To be used when creating a new part and generate a new part id
	 * 
	 * @param partNumber
	 * @param partName
	 * @param vendor
	 * @param quantityUnit
	 * @param externalPartNumber
	 * @throws Exception
	 */
	public Part(String partNumber, String partName, String vendor,
			QuantityUnit quantityUnit, String externalPartNumber)
			throws Exception {
		updatePart(true, null, partNumber, partName, vendor, quantityUnit,
				externalPartNumber);
	}

	/**
	 * To be used when recreating a part and need to construct a new part view
	 * and reload the part id
	 * 
	 * @param partID
	 * @param partNumber
	 * @param partName
	 * @param vendor
	 * @param quantityUnit
	 * @param externalPartNumber
	 * @throws Exception
	 */
	public Part(String partID, String partNumber, String partName,
			String vendor, QuantityUnit quantityUnit, String externalPartNumber)
			throws Exception {
		updatePart(false, partID, partNumber, partName, vendor, quantityUnit,
				externalPartNumber);
	}

	public void updatePart(String partNumber, String partName, String vendor,
			QuantityUnit quantityUnit, String externalPartNumber)
			throws Exception {
		updatePart(false, null, partNumber, partName, vendor, quantityUnit,
				externalPartNumber);
	}

	private void updatePart(boolean isNewPart, String partID,
			String partNumber, String partName, String vendor,
			QuantityUnit quantityUnit, String externalPartNumber)
			throws Exception {
		setPartNumber(partNumber);
		setPartName(partName);
		setPartVendor(vendor);
		setQuantityUnit(quantityUnit);
		setExternalPartNumber(externalPartNumber);
		if (isNewPart)
			generatePartID();
		if (partID != null) {
			this.setPartID(partID);
			PartView.assignPartView(this);
		}
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) throws Exception {
		partNumber = partNumber.toUpperCase();
		if (partNumber.length() == 0) {
			throw new Exception("Error: You need to enter a part number!");
		}
		if (!partNumber.startsWith("P")) {
			throw new Exception("Error: The part number must start with 'P'");
		}
		if (partNumber.length() < 2) {
			throw new Exception("Error: The part number is too short");
		}
		if (partNumber.length() > 20) {
			throw new Exception("Error: The part number is too long");
		}
		this.partNumber = partNumber;
	}

	@Override
	public String getItemName() {
		return partName;
	}

	public void setPartName(String partName) throws Exception {
		if (partName.length() == 0) {
			throw new Exception("Error: You need to enter a part name!");
		}
		if (partName.length() > 255) {
			throw new Exception("Error: The part name is too long");
		}
		this.partName = partName;
	}

	public String getExternalPartNumber() {
		return externalPartNumber;
	}

	public void setExternalPartNumber(String externalPartNumber)
			throws Exception {
		if (externalPartNumber.length() > 50)
			throw new Exception("Error: External Part # is too long");
		else
			this.externalPartNumber = externalPartNumber;
	}

	public UUID getItemID() {
		return partID;
	}

	public void setPartID(String uuidString) throws IllegalArgumentException {
		this.partID = UUID.fromString(uuidString);
	}

	private void generatePartID() {
		this.partID = UUID.randomUUID();
	}

	public String getPartVendor() {
		return vendor;
	}

	public void setPartVendor(String vendor) throws Exception {
		String v = vendor;
		if (vendor == null || vendor.length() == 0)
			v = "";
		if (vendor.length() > 255) {
			throw new Exception("Error: Vendor name length is too long");
		}
		this.vendor = v;
	}

	public PartView getPartView() {
		return pv;
	}

	public void setPartView(PartView pv) {
		this.pv = pv;
		pv.setPart(this);
	}

	@Override
	public QuantityUnit getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(QuantityUnit quantityUnit) throws Exception {
		if (quantityUnit.equals(QuantityUnit.Unknown))
			throw new Exception("Error: Quantity Unit cannot be 'Unknown'");
		this.quantityUnit = quantityUnit;
	}

	public String info() {
		return String.format(
				"ID: %s, Part #: %s, Name: %s, Vendor: %s, ext-#: %s, QU: %s",
				getItemID(), getPartNumber(), getItemName(), getPartVendor(),
				getExternalPartNumber(), getQuantityUnit());
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Part))
			return false;
		Part other = (Part) object;
		return partNumber.equals(other.getPartNumber());
	}

	@Override
	public String toString() {
		return getItemName();
	}
}
