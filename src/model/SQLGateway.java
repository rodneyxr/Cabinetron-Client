package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;
import javax.swing.DefaultListModel;

public class SQLGateway extends Gateway {
	private static final boolean DEBUG = true;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

	// public void updateInvitemTimestamp(InventoryItem inv, long timeStamp) {
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Updating timestamp for invitem");
	// String sql = "UPDATE InventoryPart SET LastModified = ? WHERE InventoryPartId = ?";
	// statement = connection.prepareStatement(sql);
	// statement.setLong(1, timeStamp);
	// statement.setString(2, inv.getItemID().toString());
	// statement.execute();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public long getInvItemTimestamp(InventoryItem inv) {
	// long timeStamp = -9;
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Getting timestamp form inventory item");
	// String sql = "SELECT InventoryPart.LastModified FROM InventoryPart WHERE InventoryPart.InventoryPartId = ?";
	// statement = connection.prepareStatement(sql);
	// statement.setString(1, inv.getItemID().toString());
	// resultSet = statement.executeQuery();
	// resultSet.first();
	// timeStamp = resultSet.getLong("LastModified");
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return timeStamp;
	// }

	// public InventoryItem getInvItemFromDBById(InventoryItem item) {
	// InventoryItem newItem = null;
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Retriving inv item form db by id");
	// String sql = "SELECT * FROM InventoryPart WHERE InventoryPartId = ?";
	// statement = connection.prepareStatement(sql);
	// statement.setString(1, item.getItemID().toString());
	// resultSet = statement.executeQuery();
	// resultSet.first();
	// String partIDString = resultSet.getString("PartId");
	// String InventoryItemID = resultSet.getString("InventoryPartId");
	// double quantity = resultSet.getDouble("Quantity");
	// String locationString = resultSet.getString("Location");
	// Location location = Location.valueOf(locationString);
	// UUID partID = UUID.fromString(partIDString);
	// // create inventory item
	// Part newPart = Main.partsModel.getPartById(partID);
	// try {
	// newItem = new InventoryItem(newPart, InventoryItemID, quantity, location);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return newItem;
	// }

	// @Override
	// public void deleteInvItemFromDB(InventoryItem inv) {
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Deleting inv item from db: " + inv.toString());
	// String sqlStatement = "DELETE FROM InventoryPart WHERE InventoryPartId = ?";
	// statement = connection.prepareStatement(sqlStatement);
	// System.out.println("delting: " + inv.getItemID().toString());
	// statement.setString(1, inv.getItemID().toString());
	// System.out.println(statement.toString());
	//
	// statement.execute();
	// System.out.println("Delete inv item from DB success!");
	// } catch (SQLException e) {
	// if (DEBUG)
	// System.out.println("Delete inv item from DB failed!");
	// e.printStackTrace();
	// }
	// }

	// @Override
	// public void deletePartFromDB(Part part) {
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Deleting part from db: " + part.toString());
	// String sqlStatement = "DELETE FROM Part WHERE PartId = ?";
	// statement = connection.prepareStatement(sqlStatement);
	// statement.setString(1, part.getPartID().toString());
	// statement.execute();
	// System.out.println("Delete part from DB success!");
	// } catch (SQLException e) {
	// if (DEBUG)
	// System.out.println("Delete part from DB failed!");
	// e.printStackTrace();
	// }
	// }

	// @Override
	// public void addInvItemToDB(InventoryItem inv) {
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Adding Inventory Item to DB: " + inv.toString());
	// String sqlStatement = "INSERT INTO InventoryPart(InventoryPartId, PartId, Quantity, Location) " + "VALUES (?,?,?,?)";
	// statement = connection.prepareStatement(sqlStatement);
	// statement.setString(1, inv.getItemID().toString());
	// statement.setString(2, inv.getPart().getPartID().toString());
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

	// @Override
	// public void updateInvItemToDB(InventoryItem inv) {
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Updating inv item to db:" + inv.toString());
	// String sqlStatement = "UPDATE  InventoryPart SET Quantity = ?, Location = ?" + "WHERE InventoryPartId = ?";
	// statement = connection.prepareStatement(sqlStatement);
	// statement.setDouble(1, inv.getQuantity());
	// statement.setString(2, inv.getLocation().name());
	// statement.setString(3, inv.getItemID().toString());
	// System.out.println(statement.toString());
	// statement.execute();
	// if (DEBUG)
	// System.out.println("Update inv item to db successful!");
	// } catch (SQLException e) {
	// if (DEBUG)
	// System.out.println("Update inv item to db failed!");
	// e.printStackTrace();
	// }
	// }
	//
	// @Override
	// public void updatePartToDB(Part part) {
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Updating part to db:" + part.toString());
	// String sqlStatement = "UPDATE  Part SET PartNumber = ?, PartVendor = ?," + "PartName = ?, PartUnit = ?, PartExternalNumber = ?" + "WHERE PartId = ?";
	// statement = connection.prepareStatement(sqlStatement);
	// statement.setString(1, part.getPartNumber());
	// statement.setString(2, part.getPartVendor());
	// statement.setString(3, part.getPartName());
	// statement.setString(4, part.getQuantityUnit().name());
	// statement.setString(5, part.getExternalPartNumber());
	// statement.setString(6, part.getPartID().toString());
	// statement.execute();
	// if (DEBUG)
	// System.out.println("Adding part to db successful!");
	// } catch (SQLException e) {
	// if (DEBUG)
	// System.out.println("Update part to db failed!");
	// e.printStackTrace();
	// }
	// }
	//
	// @Override
	// public void addPartToDB(Part part) {
	// statement = null;
	// resultSet = null;
	// try {
	// if (DEBUG)
	// System.out.println("Adding part to db...");
	// String sqlStatement = "INSERT INTO Part(PartId, PartNumber, PartVendor,PartName, PartUnit, PartExternalNumber) VALUES" + "(?,?,?,?,?,?)";
	// statement = connection.prepareStatement(sqlStatement);
	// statement.setString(1, part.getPartID().toString());
	// statement.setString(2, part.getPartNumber());
	// statement.setString(3, part.getPartVendor());
	// statement.setString(4, part.getPartName());
	// statement.setString(5, part.getQuantityUnit().name());
	// statement.setString(6, part.getExternalPartNumber());
	// statement.execute();
	// } catch (SQLException e) {
	// if (DEBUG)
	// System.out.println("Adding part to db failed!");
	// e.printStackTrace();
	// }
	// if (DEBUG)
	// System.out.println("Adding part to db successful!");
	// }
	//
	// public DefaultListModel<InventoryItem> getInvItems() {
	// DefaultListModel<InventoryItem> inv = new DefaultListModel<InventoryItem>();
	// statement = null;
	// resultSet = null;
	// boolean isNext = true;
	// try {
	// statement = connection.prepareStatement("SELECT * FROM InventoryPart");
	// resultSet = statement.executeQuery();
	// resultSet.first();
	// while (isNext) {
	// // get values from db
	// try {
	// if (DEBUG)
	// System.out.println("Creating invitem list from db");
	// String partIDString = resultSet.getString("PartId");
	// String InventoryItemID = resultSet.getString("InventoryPartId");
	// double quantity = resultSet.getDouble("Quantity");
	// String locationString = resultSet.getString("Location");
	// Location location = Location.valueOf(locationString);
	// UUID partID = UUID.fromString(partIDString);
	// // create inventory item
	// Part newPart = Main.partsModel.getPartById(partID);
	// if (DEBUG)
	// System.out.println("part found: " + newPart.toString());
	// InventoryItem newInvItem = new InventoryItem(newPart, InventoryItemID, quantity, location);
	// inv.addElement(newInvItem);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// if (!resultSet.next())
	// isNext = false;
	// }// end while
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return inv;
	// }

	// public DefaultListModel<Part> getParts() {
	// DefaultListModel<Part> parts = new DefaultListModel<Part>();
	// boolean isNext = true;
	// statement = null;
	// resultSet = null;
	// try {
	// statement = connection.prepareStatement("SELECT * FROM Part");
	// resultSet = statement.executeQuery();
	// resultSet.first();
	// while (isNext) {
	// try {
	// // fetch values from the resultSet
	// String partID = resultSet.getString("PartId");
	// String partNumber = resultSet.getString("PartNumber");
	// String vendor = resultSet.getString("PartVendor");
	// String partName = resultSet.getString("PartName");
	// String externalPartNumber = resultSet.getString("PartExternalNumber");
	// String quString = resultSet.getString("PartUnit");
	// QuantityUnit qu = QuantityUnit.valueOf(quString);
	// // attempt to create part
	// if (DEBUG)
	// System.out.println("creating part from db... : " + partNumber);
	// Part newPart = new Part(partID.toString(), partNumber, partName, vendor, qu, externalPartNumber);
	// // set partID
	// newPart.setPartID(partID);
	// // add new part to list
	// parts.addElement(newPart);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// // break loop if there is no next row
	// if (!resultSet.next())
	// isNext = false;
	// }// end while
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return parts;
	// }

	public SQLGateway() throws Exception {
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

	//
	// public DataSource getDataSource() {
	// Properties props = new Properties();
	// FileInputStream fp = null;
	// try {
	// if (DEBUG)
	// System.out.println("Reading database properties.");
	// fp = new FileInputStream("db.properties");
	// props.load(fp);
	// if (DEBUG)
	// System.out.println("Database properties read successfully");
	// } catch (IOException e) {
	// e.printStackTrace();
	// return null;
	// }
	// try {
	// MysqlDataSource mysqlDS = new MysqlDataSource();
	// mysqlDS.setURL("jdbc:mysql://devcloud.fulgentcorp.com:3306/lrp333");
	// mysqlDS.setUser("lrp333");
	// mysqlDS.setPassword("MYDi57eKHZInMWXt5MkS");
	// // mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
	// // mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
	// // mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
	// return mysqlDS;
	// } catch (RuntimeException e) {
	// e.printStackTrace();
	// return null;
	// }
	// }
	//
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

	// add new product template
	public void addProductTemplatetoDB(ProductTemplate pt) {
		statement = null;
		try {
			// add parts first
			for (int i = 0; i < pt.getTemplateParts().size(); i++)
				if (addProductTemplatePart(pt.getTemplateParts().get(i)) < 0)
					throw new SQLException("adding product templates failed!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO ProductTemplate(templateID,productNumber,productDescription) VALUES(?,?,?)";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, pt.getTemplateID().toString());
			statement.setString(2, pt.getProductNumber());
			statement.setString(3, pt.getDescription());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// add product template parts
	public int addProductTemplatePart(ProductTemplatePart ptp) {
		statement = null;
		String sql = "INSERT INTO ProductTemplatePart(productTemplateID, partID,quantity) VALUES (?,?,?)";

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, ptp.getProductTemplateID().toString());
			statement.setString(2, ptp.getPartID().toString());
			statement.setDouble(3, ptp.getQuantity());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	// delete product template
	public void deleteProductTemplate(ProductTemplate pt) {
		statement = null;
		try {
			if (DEBUG)
				System.out.println("Deleting producttemplate from db");
			String sql = "DELETE FROM ProductTemplate WHERE templateID = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, pt.getTemplateID().toString());
			statement.execute();
			deleteAllProductTemplateParts(pt.getTemplateID().toString());
			System.out.println("Delete ProductTemplate from DB success!");
		} catch (SQLException e) {
			if (DEBUG)
				System.out.println("Delete ProductTemplate from DB failed!");
			e.printStackTrace();
		}

	}

	// delete product template part
	public void deleteProductTemplatePart(String productTemplateID, ProductTemplatePart ptp) {
		statement = null;
		try {
			if (DEBUG)
				System.out.println("Deleting ProductTemplatePart from db");
			String sql = "DELETE FROM ProductTemplatePart WHERE productTemplateID = ? AND partID = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, productTemplateID);
			statement.setString(2, ptp.getPartID().toString());
			statement.execute();
			if (DEBUG)
				System.out.println("Delete ProductTemplatePart from DB success!");
		} catch (SQLException e) {
			if (DEBUG)
				System.out.println("Delete ProductTemplatePart from DB failed!");
			e.printStackTrace();
		}

	}

	// delete product template parts
	public void deleteAllProductTemplateParts(String productTemplateID) {
		statement = null;
		try {
			if (DEBUG)
				System.out.println("Deleting all ProductTemplatePart from db");
			String sql = "DELETE FROM ProductTemplatePart WHERE productTemplateID = ?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, productTemplateID);
			statement.execute();
			if (DEBUG)
				System.out.println("Delete all ProductTemplatePart from DB success!");
		} catch (SQLException e) {
			if (DEBUG)
				System.out.println("Delete all ProductTemplatePart from DB failed!");
			e.printStackTrace();
		}

	}

	// update template part
	public void updateProductTemplatePart(ProductTemplatePart oldPart, double quantity) {
		statement = null;
		String sql = "UPDATE ProductTemplatePart SET quantity =? WHERE productTemplateID = ? AND partID = ?";
		try {
			if (DEBUG)
				System.out.println("Updating product template");
			statement = connection.prepareStatement(sql);
			statement.setDouble(1, quantity);
			statement.setString(2, oldPart.getProductTemplateID().toString());
			statement.setString(3, oldPart.getPartID().toString());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// update product template
	public void updateProductTemplate(ProductTemplate oldPart, String productNumber, String productDesc) {
		statement = null;
		String sql = "UPDATE ProductTemplate SET productNumber =?,productDescription = ? WHERE templateID = ?";
		try {
			if (DEBUG)
				System.out.println("Updating product template");
			statement = connection.prepareStatement(sql);
			statement.setString(1, productNumber);
			statement.setString(2, productDesc);
			statement.setString(3, oldPart.getTemplateID().toString());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// load previous product templates
	public DefaultListModel<ProductTemplate> getProductTemplates() {
		DefaultListModel<ProductTemplate> templates = new DefaultListModel<ProductTemplate>();
		boolean isNext = true;
		statement = null;
		resultSet = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM ProductTemplate");
			resultSet = statement.executeQuery();
			resultSet.first();
			while (isNext) {
				try {
					// fetch values from the resultSet
					String templateID = resultSet.getString("templateID");
					// attempt to create part
					ProductTemplate pt = new ProductTemplate(templateID, resultSet.getString("productNumber"), resultSet.getString("productDescription"));
					if (DEBUG)
						System.out.println("creating ProductTemplate from db... ");
					// add new part to list
					templates.addElement(pt);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// break loop if there is no next row
				if (!resultSet.next())
					isNext = false;
			}// end while
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// get product templates to the created templates
		for (int i = 0; i < templates.getSize(); i++) {
			templates.get(i).setTemplateParts(getProductTemplateParts(templates.get(i).getTemplateID().toString()));
			// templates.get(i).templateParts = getProductTemplateParts(templates.get(i).getTemplateID().toString());
			// if (DEBUG)System.out.println("part "+i +" "+templates.get(i).getTemplateParts().getSize());
		}
		return templates;
	}

	public DefaultListModel<ProductTemplatePart> getProductTemplateParts(String templateID) {
		DefaultListModel<ProductTemplatePart> parts = new DefaultListModel<ProductTemplatePart>();
		boolean isNext = true;
		statement = null;
		resultSet = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM ProductTemplatePart WHERE productTemplateID = ?");
			statement.setString(1, templateID);
			resultSet = statement.executeQuery();
			resultSet.first();
			while (isNext) {
				try {
					// fetch values from the resultSet
					ProductTemplatePart ptp = new ProductTemplatePart(UUID.fromString(resultSet.getString("productTemplateID")), UUID.fromString(resultSet.getString("partID")),
							resultSet.getDouble("quantity"));
					// attempt to create part
					if (DEBUG)
						System.out.println("creating ProductTemplatePart from db... : ");
					// add new part to list
					parts.addElement(ptp);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// break loop if there is no next row
				if (!resultSet.next())
					isNext = false;
			}// end while
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parts;
	}

	public boolean partIsInTemplate(String partID) {
		statement = null;
		resultSet = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM ProductTemplatePart WHERE partID = ?");
			statement.setString(1, partID);
			resultSet = statement.executeQuery();
			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
