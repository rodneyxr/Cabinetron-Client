package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Location;

/**
 * 
 * @author Rodney
 */
public class InventoryItemPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form PartManagerPanel
	 */
	public InventoryItemPanel() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		labelTitle = new JLabel();
		labelError = new JLabel();
		labelItemID = new JLabel();
		labelSelectedPart = new JLabel();
		labelQuantity = new JLabel();
		labelLocation = new JLabel();
		textFieldItemID = new JTextField();
		buttonSelectedPart = new JButton();
		textFieldQuantity = new JTextField();
		comboBoxLocation = new JComboBox<Location>();
		panelButtons = new JPanel();
		buttonUpdateItem = new JButton();
		buttonAddItem = new JButton();

		comboBoxLocation.setModel(new DefaultComboBoxModel<Location>(Location.values()));
		comboBoxLocation.setSelectedItem(Location.Unknown);

		setLayout(new GridBagLayout());

		labelTitle.setFont(new Font("Tahoma", 0, 24)); // NOI18N
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setText("Inventory Item Manager");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(labelTitle, gridBagConstraints);

		labelError.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		labelError.setForeground(new Color(255, 0, 0));
		labelError.setHorizontalAlignment(SwingConstants.CENTER);
		labelError.setText("Error: Invalid action");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		labelError.setVisible(false);
		add(labelError, gridBagConstraints);

		labelItemID.setText("UUID:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelItemID, gridBagConstraints);

		labelSelectedPart.setText("Item:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelSelectedPart, gridBagConstraints);

		labelQuantity.setText("Quantity:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelQuantity, gridBagConstraints);

		labelLocation.setText("Location:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelLocation, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		textFieldItemID.setHorizontalAlignment(JTextField.CENTER);
		textFieldItemID.setDisabledTextColor(Color.BLUE);
		textFieldItemID.setEnabled(false);
		textFieldItemID.setText("[System Generated]");
		add(textFieldItemID, gridBagConstraints);

		buttonSelectedPart.setText("Select Item");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(buttonSelectedPart, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(textFieldQuantity, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(comboBoxLocation, gridBagConstraints);

		panelButtons.setLayout(new GridBagLayout());

		buttonUpdateItem.setText("Update Item");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelButtons.add(buttonUpdateItem, gridBagConstraints);

		buttonAddItem.setText("Add Item");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelButtons.add(buttonAddItem, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(panelButtons, gridBagConstraints);
	}

	private JLabel labelItemID;
	private JLabel labelLocation;
	private JLabel labelSelectedPart;
	private JLabel labelQuantity;
	private JLabel labelTitle;
	private JPanel panelButtons;

	public JLabel labelError;
	public JButton buttonAddItem;
	public JButton buttonUpdateItem;
	public JButton buttonSelectedPart;
	public JComboBox<Location> comboBoxLocation;
	public JTextField textFieldItemID;
	public JTextField textFieldQuantity;
}
