package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.DefaultListModel;

public class PartGateway extends Gateway {
	private static final boolean DEBUG = false;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

	
	public PartGateway() throws Exception {
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
	 * @param part
	 */
	public void deletePartFromDB(Part part) {
		statement = null;
		resultSet = null;
		try {
			if (DEBUG)
				System.out.println("Deleting part from db: " + part.toString());
			String sqlStatement = "DELETE FROM Part WHERE PartId = ?";
			statement = connection.prepareStatement(sqlStatement);
			statement.setString(1, part.getItemID().toString());
			statement.execute();
			System.out.println("Delete part from DB success!");
		} catch (SQLException e) {
			if (DEBUG)
				System.out.println("Delete part from DB failed!");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param part
	 */
	public void updatePartToDB(Part part) {
		statement = null;
		resultSet = null;
		try {
			if (DEBUG)
				System.out.println("Updating part to db:" + part.toString());
			String sqlStatement = "UPDATE  Part SET PartNumber = ?, PartVendor = ?,"
					+ "PartName = ?, PartUnit = ?, PartExternalNumber = ?"
					+ "WHERE PartId = ?";
			statement = connection.prepareStatement(sqlStatement);
			statement.setString(1, part.getPartNumber());
			statement.setString(2, part.getPartVendor());
			statement.setString(3, part.getItemName());
			statement.setString(4, part.getQuantityUnit().name());
			statement.setString(5, part.getExternalPartNumber());
			statement.setString(6, part.getItemID().toString());
			statement.execute();
			if (DEBUG)
				System.out.println("Adding part to db successful!");
		} catch (SQLException e) {
			if (DEBUG)
				System.out.println("Update part to db failed!");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param part
	 */
	public void addPartToDB(Part part) {
		statement = null;
		resultSet = null;
		try {
			if (DEBUG)
				System.out.println("Adding part to db...");
			String sqlStatement = "INSERT INTO Part(PartId, PartNumber, PartVendor,PartName, PartUnit, PartExternalNumber) VALUES"
					+ "(?,?,?,?,?,?)";
			statement = connection.prepareStatement(sqlStatement);
			statement.setString(1, part.getItemID().toString());
			statement.setString(2, part.getPartNumber());
			statement.setString(3, part.getPartVendor());
			statement.setString(4, part.getItemName());
			statement.setString(5, part.getQuantityUnit().name());
			statement.setString(6, part.getExternalPartNumber());
			statement.execute();
		} catch (SQLException e) {
			if (DEBUG)
				System.out.println("Adding part to db failed!");
			e.printStackTrace();
		}
		if (DEBUG)
			System.out.println("Adding part to db successful!");
	}

	/**
	 * 
	 * @return
	 */
	public DefaultListModel<Part> getParts() {
		DefaultListModel<Part> parts = new DefaultListModel<Part>();
		boolean isNext = true;
		statement = null;
		resultSet = null;
		try {
			statement = connection.prepareStatement("SELECT * FROM Part");
			resultSet = statement.executeQuery();
			resultSet.first();
			while (isNext) {
				try {
					// fetch values from the resultSet
					String partID = resultSet.getString("PartId");
					String partNumber = resultSet.getString("PartNumber");
					String vendor = resultSet.getString("PartVendor");
					String partName = resultSet.getString("PartName");
					String externalPartNumber = resultSet
							.getString("PartExternalNumber");
					String quString = resultSet.getString("PartUnit");
					QuantityUnit qu = QuantityUnit.valueOf(quString);
					// attempt to create part
					if (DEBUG)
						System.out.println("creating part from db... : "
								+ partNumber);
					Part newPart = new Part(partID.toString(), partNumber,
							partName, vendor, qu, externalPartNumber);
					// set partID
					newPart.setPartID(partID);
					// add new part to list
					parts.addElement(newPart);
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

}
