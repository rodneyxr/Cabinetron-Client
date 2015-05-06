package test;

import static org.junit.Assert.assertTrue;
import model.InventoryItemLogEntry;
import model.Location;
import model.Part;
import model.PartInventoryItem;
import model.QuantityUnit;

import org.junit.Before;
import org.junit.Test;

import controller.ControllerUtils;
import database.InventoryItemLogGateway;

public class TestJedisDB {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void TestBlobify() throws Exception {
		InventoryItemLogEntry entry = new InventoryItemLogEntry("Test Description");
		String blob = ControllerUtils.blobify(entry);
		InventoryItemLogEntry entry1 = (InventoryItemLogEntry) ControllerUtils.deblobify(blob);
		assertTrue(entry.toString().equals(entry1.toString()));
	}

	@Test
	public void TestJedis() throws Exception {
		InventoryItemLogGateway logGateway = new InventoryItemLogGateway();
		Part p = new Part("PN900", "hello", "hello", QuantityUnit.Pieces, "X99");
		PartInventoryItem partItem = new PartInventoryItem(p, 200, Location.Facility1Warehouse1);
		logGateway.addLogEntry(partItem, new InventoryItemLogEntry("Test Description"));
	}

}
