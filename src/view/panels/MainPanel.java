package view.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;

import model.InventoryItem;
import model.Part;
import model.ProductTemplate;

/**
 *
 * @author Rodney
 */
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form InventoryPanel
	 */
	public MainPanel() {
		initComponents();
	}

	private void initComponents() {
		GridBagConstraints gridBagConstraints;

		tabbedPanel = new JTabbedPane();

		// Inventory View
		panelInventory = new JPanel();
		scrollPaneInventoryList = new JScrollPane();
		listInventoryItems = new JList<InventoryItem>();
		buttonAddInventoryItem = new JButton();
		buttonDeleteInventoryItem = new JButton();

		// Parts View
		panelParts = new JPanel();
		scrollPaneParts = new JScrollPane();
		listParts = new JList<Part>();
		buttonAddPart = new JButton();
		buttonDeletePart = new JButton();

		// Product Template View
		panelTemplates = new JPanel();
		scrollPaneTemplates = new JScrollPane();
		listTemplates = new JList<ProductTemplate>();
		buttonAddTemplate = new JButton();
		buttonDeleteTemplate = new JButton();

		listInventoryItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listParts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTemplates.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		setLayout(new GridBagLayout());

		tabbedPanel.setFont(new Font("Tahoma", 0, 18)); // NOI18N

		/**
		 * Inventory
		 */
		panelInventory.setLayout(new GridBagLayout());

		listInventoryItems.setFont(new Font("Tahoma", 1, 18)); // NOI18N
		scrollPaneInventoryList.setViewportView(listInventoryItems);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelInventory.add(scrollPaneInventoryList, gridBagConstraints);

		buttonAddInventoryItem.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		buttonAddInventoryItem.setText("Add Inventory Item");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 0.15;
		gridBagConstraints.weighty = 0.15;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelInventory.add(buttonAddInventoryItem, gridBagConstraints);

		buttonDeleteInventoryItem.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		buttonDeleteInventoryItem.setText("Delete Inventory Item");
		buttonDeleteInventoryItem.setMaximumSize(new Dimension(127, 23));
		buttonDeleteInventoryItem.setMinimumSize(new Dimension(127, 23));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 0.15;
		gridBagConstraints.weighty = 0.15;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelInventory.add(buttonDeleteInventoryItem, gridBagConstraints);

		tabbedPanel.add("Inventory", panelInventory);

		/**
		 * Parts
		 */
		panelParts.setLayout(new GridBagLayout());

		listParts.setFont(new Font("Tahoma", 1, 18)); // NOI18N
		scrollPaneParts.setViewportView(listParts);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelParts.add(scrollPaneParts, gridBagConstraints);

		buttonAddPart.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		buttonAddPart.setText("Add Part");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 0.15;
		gridBagConstraints.weighty = 0.15;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelParts.add(buttonAddPart, gridBagConstraints);

		buttonDeletePart.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		buttonDeletePart.setText("Delete Part");
		buttonDeletePart.setMaximumSize(new Dimension(127, 23));
		buttonDeletePart.setMinimumSize(new Dimension(127, 23));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 0.15;
		gridBagConstraints.weighty = 0.15;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelParts.add(buttonDeletePart, gridBagConstraints);

		tabbedPanel.add("Parts", panelParts);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(tabbedPanel, gridBagConstraints);

		/**
		 * Product Templates
		 */
		panelTemplates.setLayout(new GridBagLayout());

		listTemplates.setFont(new Font("Tahoma", 1, 18)); // NOI18N
		scrollPaneTemplates.setViewportView(listTemplates);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		panelTemplates.add(scrollPaneTemplates, gridBagConstraints);

		buttonAddTemplate.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		buttonAddTemplate.setText("Add Template");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 0.15;
		gridBagConstraints.weighty = 0.15;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplates.add(buttonAddTemplate, gridBagConstraints);

		buttonDeleteTemplate.setFont(new Font("Tahoma", 0, 16)); // NOI18N
		buttonDeleteTemplate.setText("Delete Template");
		buttonDeleteTemplate.setMaximumSize(new Dimension(127, 23));
		buttonDeleteTemplate.setMinimumSize(new Dimension(127, 23));
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 5;
		gridBagConstraints.ipady = 5;
		gridBagConstraints.weightx = 0.15;
		gridBagConstraints.weighty = 0.15;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelTemplates.add(buttonDeleteTemplate, gridBagConstraints);

		tabbedPanel.add("Templates", panelTemplates);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		add(tabbedPanel, gridBagConstraints);
	}

	// inventory
	public JList<InventoryItem> listInventoryItems;
	public JButton buttonAddInventoryItem;
	public JButton buttonDeleteInventoryItem;
	public JPanel panelInventory;
	private JScrollPane scrollPaneInventoryList;

	// parts
	public JList<Part> listParts;
	public JButton buttonAddPart;
	public JButton buttonDeletePart;
	public JPanel panelParts;
	private JScrollPane scrollPaneParts;

	// templates
	public JList<ProductTemplate> listTemplates;
	public JButton buttonAddTemplate;
	public JButton buttonDeleteTemplate;
	public JPanel panelTemplates;
	private JScrollPane scrollPaneTemplates;

	public JTabbedPane tabbedPanel;
}
