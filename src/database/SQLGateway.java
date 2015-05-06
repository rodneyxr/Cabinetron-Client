package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;
import javax.swing.DefaultListModel;

import model.ProductTemplate;
import model.ProductTemplatePart;

public class SQLGateway extends Gateway {
	private static final boolean DEBUG = true;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

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
