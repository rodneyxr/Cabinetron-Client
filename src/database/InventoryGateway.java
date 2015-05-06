package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;
import javax.swing.DefaultListModel;

import model.InventoryItem;
import model.Location;
import model.Part;
import model.PartInventoryItem;
import model.ProductInventoryItem;
import model.ProductTemplate;
import view.Main;

public class InventoryGateway extends Gateway {
	private static final boolean DEBUG = false;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

	// new statements
	String updateTimestamp = "UPDATE InventoryItem SET LastModified = ? WHERE inventoryItemID = ?";
	String getTimestamp = "SELECT InventoryItem.LastModified FROM InventoryItem WHERE InventoryItem.inventoryItemID = ?";
	String getItemsByID = "SELECT * FROM InventoryItem WHERE inventoryItemID = ?";
	String deleteItemByID = "DELETE FROM InventoryItem WHERE inventoryItemID = ?";
	String addInvItem = "INSERT INTO InventoryItem(stockType,inventoryItemID, itemID, Quantity, Location) VALUES (?,?,?,?,?)";
	String updateInvItem = "UPDATE  InventoryItem SET Quantity = ?, Location = ? WHERE inventoryItemID = ?";
	String getInvItems = "SELECT * FROM InventoryItem";

	public InventoryGateway() throws Exception {
		DataSource ds = getDataSource();
		if (ds == null)
			throw new Exception("Datasource is null");
		try {
			if (DEBUG)
				System.out.println("Attempting to connect to the Database.");
			connection = ds.getConnection();
			if (DEBUG)
				System.out.println("Database connection successful");
		} catch (SQLException e) {
			throw new Exception("SQL Error: " + e.getMessage());
		}
	}

	public void close() {
		if (DEBUG)
			System.out.println("Closing db connection...");
		try {
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param inv
	 * @param timeStamp
	 */
	public void updateInvitemTimestamp(InventoryItem inv, long timeStamp) {
		statement = null;
		resultSet = null;
		try {
			if (DEBUG)
				System.out.println("Updating timestamp for invitem");
			String sql = updateTimestamp;
			statement = connection.prepareStatement(sql);
			statement.setLong(1, timeStamp);
			statement.setString(2, inv.getItemID().toString());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param inv
	 * @return
	 */
	public long getInvItemTimestamp(InventoryItem inv) {
		long timeStamp = -9;
		statement = null;
		resultSet = null;
		try {
			if (DEBUG)
				System.out.println("Getting timestamp form inventory item");
			String sql = getTimestamp;
			statement = connection.prepareStatement(sql);
			statement.setString(1, inv.getItemID().toString());
			resultSet = statement.executeQuery();
			resultSet.first();
			timeStamp = resultSet.getLong("LastModified");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeStamp;
	}

	/**
	 * 
	 * @param item
	 * @return InventoryItem
	 */
	public InventoryItem getInvItemFromDBById(InventoryItem item) {
		InventoryItem newItem = null;
		statement = null;
		resultSet = null;
		try {
			if (DEBUG)
				System.out.println("Retriving inv item form db by id");
			String sql = getItemsByID;
			statement = connection.prepareStatement(sql);
			statement.setString(1, item.getItemID().toString());
			resultSet = statement.executeQuery();
			resultSet.first();
			String partIDString = resultSet.getString("itemID");
			String InventoryItemID = resultSet.getString("inventoryItemId");
			double quantity = resultSet.getDouble("Quantity");
			String locationString = resultSet.getString("Location");
			Location location = Location.valueOf(locationString);
			UUID partID = UUID.fromString(partIDString);
			// create inventory item
			Part newPart = Main.partsModel.getPartById(partID);
			try {
				newItem = new PartInventoryItem(newPart, InventoryItemID, quantity, location);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newItem;
	}

	/**
	 * Deletes an inventory item from the database
	 * 
	 * @param inv
	 */
	public void deleteInvItemFromDB(InventoryItem inv) {
		statement = null;
		resultSet = null;
		try {
			if (DEBUG)
				System.out.println("Deleting inv item from db: " + inv.toString());
			String sqlStatement = deleteItemByID;
			statement = connection.prepareStatement(sqlStatement);
			System.out.println("delting: " + inv.getItemID().toString());
			statement.setString(1, inv.getItemID().toString());
			System.out.println(statement.toString());

			statement.execute();
			System.out.println("Delete inv item from DB success!");
		} catch (SQLException e) {
			if (DEBUG)
				System.out.println("Delete inv item from DB failed!");
			e.printStackTrace();
		}
	}

	/**
	 * This function adds an Inventory item to the database
	 * 
	 * @param inv
	 */
	public void addInvItemToDB(InventoryItem inv) {
		statement = null;
		resultSet = null;
		try {
			if (DEBUG)
				System.out.println("Adding Inventory Item to DB: " + inv.toString());
			String sqlStatement = addInvItem;
			statement = connection.prepareStatement(sqlStatement);
			statement.setString(1, inv.getClass().getSimpleName());
			statement.setString(2, inv.getItemID().toString());
			statement.setString(3, inv.getItem().getItemID().toString());
			statement.setDouble(4, inv.getQuantity());
			statement.setString(5, inv.getLocation().name());
			statement.execute();
		} catch (SQLException e) {
			if (DEBUG)
				System.out.println("Add Inventory Item to db failed!");
			e.printStackTrace();
		}
		if (DEBUG)
			System.out.println("Add Inventory Item to db successful!");
	}

	//
	// /**
	// * @note Old function
	// */
	// public void addInvItemToDB(InventoryItem inv) {
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Adding Inventory Item to DB: "
	// + inv.toString());
	// String sqlStatement = addInvItem;
	// statement = connection.prepareStatement(sqlStatement);
	// statement.setString(1, inv.getItemID().toString());
	// statement.setString(2, inv.getItem().getItemID().toString());
	// statement.setDouble(3, inv.getQuantity());
	// statement.setString(4, inv.getLocation().name());
	// statement.execute();
	// } catch (SQLException e) {
	// if (DEBUG)
	// System.out.println("Add Inventory Item to db failed!");
	// e.printStackTrace();
	// }
	// if (DEBUG)
	// System.out.println("Add Inventory Item to db successful!");
	// }

	/**
	 * This function updates an Inventory Item in the DB
	 * 
	 * @param InventoryItem
	 *            inv
	 */
	public void updateInvItemToDB(InventoryItem inv) {
		statement = null;
		resultSet = null;
		try {
			if (DEBUG)
				System.out.println("Updating inv item to db:" + inv.toString());
			String sqlStatement = updateInvItem;
			statement = connection.prepareStatement(sqlStatement);
			statement.setDouble(1, inv.getQuantity());
			statement.setString(2, inv.getLocation().name());
			statement.setString(3, inv.getItemID().toString());
			System.out.println(statement.toString());
			statement.execute();
			if (DEBUG)
				System.out.println("Update inv item to db successful!");
		} catch (SQLException e) {
			if (DEBUG)
				System.out.println("Update inv item to db failed!");
			e.printStackTrace();
		}
	}

	/**
	 * This method retrieves all Inventory Items from the database
	 * 
	 * @return DefaultListModel<InventoryItem>
	 */

	public DefaultListModel<InventoryItem> getInvItems() {
		DefaultListModel<InventoryItem> inv = new DefaultListModel<InventoryItem>();
		statement = null;
		resultSet = null;
		boolean isNext = true;
		try {
			statement = connection.prepareStatement(getInvItems);
			resultSet = statement.executeQuery();
			resultSet.first();
			while (isNext) {
				// get values from db
				try {
					if (DEBUG)
						System.out.println("Creating invitem list from db");
					String stockType = resultSet.getString("stockType");
					String InventoryItemID = resultSet.getString("inventoryItemID");
					String itemIDString = resultSet.getString("itemID");
					double quantity = resultSet.getDouble("Quantity");
					String locationString = resultSet.getString("Location");
					Location location = Location.valueOf(locationString);
					UUID itemID = UUID.fromString(itemIDString);
					// create inventory item
					InventoryItem newInvItem = null;
					if (stockType.equals("PartInventoryItem")) {
						Part newPart = Main.partsModel.getPartById(itemID);
						newInvItem = new PartInventoryItem(newPart, InventoryItemID, quantity, location);
					} else if (stockType.equals("ProductInventoryItem")) {
						ProductTemplate item = Main.templatesModel.getTemplateByID(itemID);
						newInvItem = new ProductInventoryItem((ProductTemplate) item, InventoryItemID, quantity, location);
					}
					if (newInvItem == null)
						throw new Exception("new Inv Item is null");
					inv.addElement(newInvItem);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!resultSet.next())
					isNext = false;
			}// end while
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inv;
	}

}
