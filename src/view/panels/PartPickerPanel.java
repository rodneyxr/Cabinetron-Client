package view.panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Part;

/**
 * 
 * @author Rodney
 */
public class PartPickerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form PartPicker
	 */
	public PartPickerPanel() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		scrollPaneListParts = new JScrollPane();
		listParts = new JList<Part>();
		panelPartInfo = new JPanel();
		tmpLabelPartID = new JLabel();
		labelPartID = new JLabel();
		tmpLabelPartName = new JLabel();
		labelPartName = new JLabel();
		tmpLabelPartNumber = new JLabel();
		labelPartNumber = new JLabel();
		tmpLabelPartVendor = new JLabel();
		labelPartVendor = new JLabel();
		tmpLabelQuantityUnit = new JLabel();
		labelQuantityUnit = new JLabel();
		tmpLabelExternalPartNumber = new JLabel();
		labelExternalPartNumber = new JLabel();
		buttonCancel = new JButton();
		buttonSelect = new JButton();

		listParts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		setLayout(new GridBagLayout());

		listParts.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		scrollPaneListParts.setViewportView(listParts);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(scrollPaneListParts, gridBagConstraints);

		panelPartInfo.setLayout(new GridBagLayout());

		tmpLabelPartID.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelPartID.setText("UUID:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(tmpLabelPartID, gridBagConstraints);

		labelPartID.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelPartID.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(labelPartID, gridBagConstraints);

		tmpLabelPartName.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelPartName.setText("Part Name:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(tmpLabelPartName, gridBagConstraints);

		labelPartName.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelPartName.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(labelPartName, gridBagConstraints);

		tmpLabelPartNumber.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelPartNumber.setText("Part Number:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(tmpLabelPartNumber, gridBagConstraints);

		labelPartNumber.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelPartNumber.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(labelPartNumber, gridBagConstraints);

		tmpLabelPartVendor.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelPartVendor.setText("Vendor:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(tmpLabelPartVendor, gridBagConstraints);

		labelPartVendor.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelPartVendor.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(labelPartVendor, gridBagConstraints);

		tmpLabelQuantityUnit.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelQuantityUnit.setText("Quantity Unit:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(tmpLabelQuantityUnit, gridBagConstraints);

		labelQuantityUnit.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelQuantityUnit.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(labelQuantityUnit, gridBagConstraints);

		tmpLabelExternalPartNumber.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelExternalPartNumber.setText("External Part #:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(tmpLabelExternalPartNumber, gridBagConstraints);

		labelExternalPartNumber.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelExternalPartNumber.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelPartInfo.add(labelExternalPartNumber, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0;
		add(panelPartInfo, gridBagConstraints);

		buttonCancel.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		buttonCancel.setText("Cancel");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(buttonCancel, gridBagConstraints);

		buttonSelect.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		buttonSelect.setText("Select Part");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(buttonSelect, gridBagConstraints);
	}

	private JScrollPane scrollPaneListParts;
	private JLabel tmpLabelExternalPartNumber;
	private JLabel tmpLabelPartID;
	private JLabel tmpLabelPartName;
	private JLabel tmpLabelPartNumber;
	private JLabel tmpLabelPartVendor;
	private JLabel tmpLabelQuantityUnit;
	private JPanel panelPartInfo;

	public JButton buttonCancel;
	public JButton buttonSelect;
	public JLabel labelExternalPartNumber;
	public JLabel labelPartID;
	public JLabel labelPartName;
	public JLabel labelPartNumber;
	public JLabel labelPartVendor;
	public JLabel labelQuantityUnit;
	public JList<Part> listParts;

}
