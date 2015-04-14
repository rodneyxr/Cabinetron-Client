package model;

public enum QuantityUnit {
	LinearFeet("Linear Feet", true), Pieces("Pieces", false), Unknown("Unknown", true);

	public String quantityUnitText;
	public boolean allowFractions;

	QuantityUnit(String quantityUnitText, boolean allowFractions) {
		this.quantityUnitText = quantityUnitText;
		this.allowFractions = allowFractions;
	}

	@Override
	public String toString() {
		return quantityUnitText;
	}
}