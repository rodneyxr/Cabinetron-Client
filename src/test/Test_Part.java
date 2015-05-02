package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import model.Part;
import model.QuantityUnit;

import org.junit.Before;
import org.junit.Test;

public class Test_Part {

	private String partName;
	private String partName_maxLen;

	private String partNumber;
	private String partNumber_maxLen;

	private String vendor;
	private String vendor_null;
	private String vendor_empty;

	private Part newPart;

	@Before
	public void setUp() throws Exception {
		partNumber_maxLen = "";
		partName_maxLen = "";
		// build max length strings for part vars
		for (int i = 0; i < 255; i++) {
			partName_maxLen += 'a';
		}
		for (int i = 0; i < 20; i++) {
			partNumber_maxLen += 'a';
		}

		// set non max length part vars with alphanumeric and special chars
		partNumber = "abc123!@#$%^&*";
		partName = "abc123!@#$%^&*";
		vendor = "abc123!@#$%^&*";
		vendor_null = null;
		vendor_empty = "";

	}

	// part number tests
	@Test
	public void partNumber_maxLength() {
		try {
			newPart = new Part(partNumber_maxLen, partName, vendor, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
		}
		assertNotEquals(null, newPart);
	}

	@Test
	public void partNumber_valid() {
		try {
			newPart = new Part(partNumber, partName, vendor, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
		}
		assertNotEquals(null, newPart);
	}

	@Test()
	public void partNumber_lengthInvalid() {
		try {
			newPart = new Part(partNumber_maxLen += 'a', partName, vendor, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
			assertEquals("The part number is too long", e.getMessage());
		}
	}

	// part name tests
	@Test
	public void partName_maxLength() {
		try {
			newPart = new Part(partNumber, partName_maxLen, vendor, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
		}
		assertNotEquals(null, newPart);
	}

	@Test
	public void partName_valid() {
		try {
			newPart = new Part(partNumber, partName, vendor, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
		}
		assertNotEquals(null, newPart);
	}

	@Test()
	public void partName_lengthInvalid() {
		try {
			newPart = new Part(partNumber, partName_maxLen += 'a', vendor, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
			assertEquals("The part name is too long", e.getMessage());
		}
	}

	// vendor tests
	@Test
	public void vendor_valid() {
		try {
			newPart = new Part(partNumber, partName, vendor, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
		}
		assertNotEquals(null, newPart);
	}

	@Test
	public void vendor_null() {
		try {
			newPart = new Part(partNumber, partName, vendor_null, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
		}
		assertEquals(null, newPart);
	}

	@Test
	public void vendor_empty() {
		try {
			newPart = new Part(partNumber, partName, vendor_empty, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
		}
		assertNotEquals(null, newPart);
	}

	// quantity tests

	@Test
	public void quantity_valid() {
		try {
			newPart = new Part(partNumber, partName, vendor, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
		}
		assertNotEquals(null, newPart);
	}

	@Test
	public void quantity_invalid() {
		try {
			newPart = new Part(partNumber, partName, vendor, QuantityUnit.Pieces, "X99");
		} catch (Exception e) {
		}
		assertEquals(null, newPart);
	}

}
