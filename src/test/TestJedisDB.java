package test;

import org.junit.Before;

public class TestJedisDB {

	@Before
	public void setUp() throws Exception {

	}

	// @Test
	// public void TestBlobify() throws Exception {
	// InventoryItemLogEntry entry = new InventoryItemLogEntry("Test Description");
	// String blob = Utils.blobify(entry);
	// InventoryItemLogEntry entry1 = (InventoryItemLogEntry) Utils.deblobify(blob);
	// assertEquals(entry.toString(), entry1.toString());
	// }

	// @Test
	// public void TestJedis() throws Exception {
	// InventoryItemLogGateway logGateway = new InventoryItemLogGateway();
	// Part p = new Part("PN900", "hello", "hello", QuantityUnit.Pieces, "X99");
	// PartInventoryItem partItem = new PartInventoryItem(p, 200, Location.Facility1Warehouse1);
	// logGateway.addLogEntry(partItem, new InventoryItemLogEntry("Test Description"));
	// }

}
