package model;

public enum Location {
	Facility1Warehouse1("Facility 1 Warehouse 1"), Facility1Warehouse2("Facility 1 Warehouse 2"), Facility2("Facility 2"), Unknown("Unknown");

	public String locationText;

	Location(String locationText) {
		this.locationText = locationText;
	}

	@Override
	public String toString() {
		return locationText;
	}
}
