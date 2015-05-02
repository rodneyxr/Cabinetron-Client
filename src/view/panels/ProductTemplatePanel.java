package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import model.ProductTemplatePart;

/**
 * 
 * @author Rodney
 */
public class ProductTemplatePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form ProductTemplatePanel
	 */
	public ProductTemplatePanel() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		labelTitle = new JLabel();
		jPanel2 = new JPanel();
		buttonUpdateTemplate = new JButton();
		buttonCreateTemplate = new JButton();
		labelError = new JLabel();
		jSplitPane1 = new JSplitPane();
		jPanel1 = new JPanel();
		jScrollPane2 = new JScrollPane();
		listTemplateParts = new JList<ProductTemplatePart>();
		buttonDeleteTemplatePart = new JButton();
		buttonAddTemplatePart = new JButton();
		jLabel5 = new JLabel();
		jPanel3 = new JPanel();
		labelProductID = new JLabel();
		labelProductNumber = new JLabel();
		labelDescription = new JLabel();
		jScrollPane1 = new JScrollPane();
		textAreaDescription = new JTextArea();
		textFieldProductID = new JTextField();
		textFieldProductNumber = new JTextField();

		setLayout(new GridBagLayout());

		labelTitle.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		labelTitle.setText("Product Template Manager");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(labelTitle, gridBagConstraints);

		jPanel2.setLayout(new GridBagLayout());

		buttonUpdateTemplate.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		buttonUpdateTemplate.setText("Update Product Template");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		jPanel2.add(buttonUpdateTemplate, gridBagConstraints);

		buttonCreateTemplate.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		buttonCreateTemplate.setText("Create Product Template");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		jPanel2.add(buttonCreateTemplate, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(jPanel2, gridBagConstraints);

		labelError.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		labelError.setForeground(new Color(255, 0, 0));
		labelError.setText("Error: Invalid action");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		add(labelError, gridBagConstraints);

		jSplitPane1.setDividerLocation(387);
		jSplitPane1.setEnabled(false);

		jPanel1.setLayout(new GridBagLayout());

		listTemplateParts.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		listTemplateParts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jScrollPane2.setViewportView(listTemplateParts);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		jPanel1.add(jScrollPane2, gridBagConstraints);

		buttonDeleteTemplatePart.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		buttonDeleteTemplatePart.setText("Delete");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 0.2;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		jPanel1.add(buttonDeleteTemplatePart, gridBagConstraints);

		buttonAddTemplatePart.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		buttonAddTemplatePart.setText("Add");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 0.2;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		jPanel1.add(buttonAddTemplatePart, gridBagConstraints);

		jLabel5.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		jLabel5.setText("Product Template Parts:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		jPanel1.add(jLabel5, gridBagConstraints);

		jSplitPane1.setRightComponent(jPanel1);

		jPanel3.setLayout(new GridBagLayout());

		labelProductID.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		labelProductID.setText("UUID:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(1, 4, 1, 1);
		jPanel3.add(labelProductID, gridBagConstraints);

		labelProductNumber.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		labelProductNumber.setText("Product Number:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.weightx = 0;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(1, 4, 1, 1);
		jPanel3.add(labelProductNumber, gridBagConstraints);

		labelDescription.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		labelDescription.setText("Description:");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(1, 4, 1, 1);
		jPanel3.add(labelDescription, gridBagConstraints);

		textAreaDescription.setColumns(20);
		textAreaDescription.setFont(new Font("Monospaced", 0, 14)); // NOI18N
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setRows(5);
		textAreaDescription.setWrapStyleWord(true);
		jScrollPane1.setViewportView(textAreaDescription);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 4, 1, 1);
		jPanel3.add(jScrollPane1, gridBagConstraints);

		textFieldProductID.setHorizontalAlignment(JTextField.CENTER);
		textFieldProductID.setDisabledTextColor(Color.BLUE);
		textFieldProductID.setEnabled(false);
		textFieldProductID.setText("[System Generated]");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		jPanel3.add(textFieldProductID, gridBagConstraints);

		// textFieldProductNumber.setText("");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.ipadx = 3;
		gridBagConstraints.ipady = 3;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 0.1;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		jPanel3.add(textFieldProductNumber, gridBagConstraints);

		jSplitPane1.setLeftComponent(jPanel3);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		add(jSplitPane1, gridBagConstraints);
	}

	public void setRightPanel(JPanel panel) {
		jSplitPane1.setRightComponent(panel);
		jSplitPane1.setDividerLocation(387);
	}

	public void revertRightPanel() {
		setRightPanel(jPanel1);
	}

	private JLabel jLabel5;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JSplitPane jSplitPane1;
	private JLabel labelDescription;
	private JLabel labelProductNumber;
	private JLabel labelTitle;
	private JLabel labelProductID;

	public JButton buttonAddTemplatePart;
	public JButton buttonCreateTemplate;
	public JButton buttonDeleteTemplatePart;
	public JButton buttonUpdateTemplate;
	public JLabel labelError;
	public JList<ProductTemplatePart> listTemplateParts;
	public JTextArea textAreaDescription;
	public JTextField textFieldProductNumber;
	public JTextField textFieldProductID;
}
