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

import model.ProductTemplate;

/**
 * 
 * @author Rodney
 */
public class TemplatePickerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form PartPicker
	 */
	public TemplatePickerPanel() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		scrollPaneListTemplates = new JScrollPane();
		listTemplates = new JList<ProductTemplate>();
		panelTemplateInfo = new JPanel();
		tmpLabelTemplateID = new JLabel();
		labelTemplateID = new JLabel();
		tmpLabelProductNumber = new JLabel();
		labelProductNumber = new JLabel();
		tmpLabelTemplateDescription = new JLabel();
		labelTemplateDescription = new JLabel();
		tmpLabelQuantityUnit = new JLabel();
		labelQuantityUnit = new JLabel();
		buttonCancel = new JButton();
		buttonSelect = new JButton();

		listTemplates.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		setLayout(new GridBagLayout());

		listTemplates.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		scrollPaneListTemplates.setViewportView(listTemplates);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(scrollPaneListTemplates, gridBagConstraints);

		panelTemplateInfo.setLayout(new GridBagLayout());

		tmpLabelTemplateID.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelTemplateID.setText("UUID:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplateInfo.add(tmpLabelTemplateID, gridBagConstraints);

		labelTemplateID.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelTemplateID.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplateInfo.add(labelTemplateID, gridBagConstraints);

		tmpLabelProductNumber.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelProductNumber.setText("Product Number:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplateInfo.add(tmpLabelProductNumber, gridBagConstraints);

		labelProductNumber.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelProductNumber.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplateInfo.add(labelProductNumber, gridBagConstraints);

		tmpLabelTemplateDescription.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelTemplateDescription.setText("Description:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplateInfo.add(tmpLabelTemplateDescription, gridBagConstraints);

		labelTemplateDescription.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelTemplateDescription.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplateInfo.add(labelTemplateDescription, gridBagConstraints);

		tmpLabelQuantityUnit.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		tmpLabelQuantityUnit.setText("Quantity Unit:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplateInfo.add(tmpLabelQuantityUnit, gridBagConstraints);

		labelQuantityUnit.setFont(new Font("Tahoma", 0, 15)); // NOI18N
		labelQuantityUnit.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplateInfo.add(labelQuantityUnit, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0;
		add(panelTemplateInfo, gridBagConstraints);

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
		buttonSelect.setText("Select Template");
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

	private JScrollPane scrollPaneListTemplates;
	private JLabel tmpLabelTemplateID;
	private JLabel tmpLabelProductNumber;
	private JLabel tmpLabelTemplateDescription;
	private JLabel tmpLabelQuantityUnit;
	private JPanel panelTemplateInfo;

	public JButton buttonCancel;
	public JButton buttonSelect;
	public JLabel labelTemplateID;
	public JLabel labelProductNumber;
	public JLabel labelTemplateDescription;
	public JLabel labelQuantityUnit;
	public JList<ProductTemplate> listTemplates;

}
