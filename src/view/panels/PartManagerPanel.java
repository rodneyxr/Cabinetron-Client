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

import model.QuantityUnit;

/**
 * 
 * @author Rodney
 */
public class PartManagerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form PartManagerPanel
	 */
	public PartManagerPanel() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		labelTitle = new JLabel();
		labelPartNumber = new JLabel();
		labelError = new JLabel();
		labelPartID = new JLabel();
		labelPartName = new JLabel();
		labelVendor = new JLabel();
		labelQuantityUnit = new JLabel();
		labelExternalPartNumber = new JLabel();
		textFieldPartID = new JTextField();
		textFieldPartNumber = new JTextField();
		textFieldPartName = new JTextField();
		textFieldVendor = new JTextField();
		comboBoxQuantityUnit = new JComboBox<QuantityUnit>();
		textFieldExternalPartNumber = new JTextField();
		panelButtons = new JPanel();
		buttonUpdatePart = new JButton();
		buttonCreatePart = new JButton();

		comboBoxQuantityUnit.setModel(new DefaultComboBoxModel<QuantityUnit>(QuantityUnit.values()));
		comboBoxQuantityUnit.setSelectedItem(QuantityUnit.Unknown);

		setLayout(new GridBagLayout());

		labelTitle.setFont(new Font("Tahoma", 0, 24)); // NOI18N
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setText("Part Manager");
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

		labelPartID.setText("UUID:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelPartID, gridBagConstraints);

		labelPartNumber.setText("Part Number:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelPartNumber, gridBagConstraints);

		labelPartName.setText("Part Name:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelPartName, gridBagConstraints);

		labelVendor.setText("Vendor:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelVendor, gridBagConstraints);

		labelQuantityUnit.setText("Quantity Unit:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelQuantityUnit, gridBagConstraints);

		labelExternalPartNumber.setText("External Part #:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelExternalPartNumber, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		textFieldPartID.setHorizontalAlignment(JTextField.CENTER);
		textFieldPartID.setDisabledTextColor(Color.BLUE);
		textFieldPartID.setEnabled(false);
		textFieldPartID.setText("[System Generated]");
		add(textFieldPartID, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(textFieldPartNumber, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(textFieldPartName, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(textFieldVendor, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(comboBoxQuantityUnit, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(textFieldExternalPartNumber, gridBagConstraints);

		panelButtons.setLayout(new GridBagLayout());

		buttonUpdatePart.setText("Update Part");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelButtons.add(buttonUpdatePart, gridBagConstraints);

		buttonCreatePart.setText("Create Part");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelButtons.add(buttonCreatePart, gridBagConstraints);

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

	private JLabel labelPartID;
	private JLabel labelPartNumber;
	private JLabel labelExternalPartNumber;
	private JLabel labelPartName;
	private JLabel labelQuantityUnit;
	private JLabel labelTitle;
	private JLabel labelVendor;
	private JPanel panelButtons;

	public JLabel labelError;
	public JButton buttonCreatePart;
	public JButton buttonUpdatePart;
	public JComboBox<QuantityUnit> comboBoxQuantityUnit;
	public JTextField textFieldPartID;
	public JTextField textFieldExternalPartNumber;
	public JTextField textFieldPartName;
	public JTextField textFieldPartNumber;
	public JTextField textFieldVendor;
}
