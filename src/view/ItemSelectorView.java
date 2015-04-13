package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Item;
import model.Part;
import model.ProductTemplate;
import view.panels.PartPickerPanel;
import view.panels.TemplatePickerPanel;

public class ItemSelectorView extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane;
	private PartPickerPanel partPickerPanel;
	private TemplatePickerPanel templatePickerPanel;

	// private Item selectedItem;
	private Item selectedPart;
	private Item selectedTemplate;

	public ItemSelectorView() {
		super("Cabinetron: Item Selector");
		
		try {
			setIconImage(ImageIO.read(new File("src/res/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setSize(600, 450);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		tabbedPane = new JTabbedPane();
		partPickerPanel = new PartPickerPanel();
		templatePickerPanel = new TemplatePickerPanel();
		tabbedPane.addTab("Part", partPickerPanel);
		tabbedPane.addTab("Template", templatePickerPanel);

		// link the lists to their respective models
		partPickerPanel.listParts.setModel(Main.partsModel.getPartList());
		templatePickerPanel.listTemplates.setModel(Main.templatesModel.getTemplateList());

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1f;
		c.weighty = 1f;
		c.fill = GridBagConstraints.BOTH;
		add(tabbedPane, c);

		activateListeners();
	}

	private void activateListeners() {
		/**
		 * Part Picker
		 */
		partPickerPanel.listParts.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				@SuppressWarnings("unchecked")
				JList<Part> list = (JList<Part>) e.getSource();
				if (list.getSelectedValue() == null)
					ItemSelectorView.this.setEnabled(false);
				ItemSelectorView.this.updateInfoForPart(list.getSelectedValue());
				ItemSelectorView.this.selectedPart = list.getSelectedValue();
			}
		});

		partPickerPanel.buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("PartPickerView: 'Cancel' button clicked.");
				ItemSelectorView.this.hideView();
			}
		});

		/**
		 * Template Picker
		 */
		templatePickerPanel.listTemplates.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				@SuppressWarnings("unchecked")
				JList<ProductTemplate> list = (JList<ProductTemplate>) e.getSource();
				if (list.getSelectedValue() == null)
					ItemSelectorView.this.setEnabled(false);
				ItemSelectorView.this.updateInfoForTemplate(list.getSelectedValue());
				ItemSelectorView.this.selectedTemplate = list.getSelectedValue();
			}
		});

		templatePickerPanel.buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ItemSelectorView: templatPickerPanel 'Cancel' button clicked.");
				ItemSelectorView.this.hideView();
			}
		});
	}

	public JButton getSelectPartButton() {
		return partPickerPanel.buttonSelect;
	}

	public JButton getSelectTemplateButton() {
		return templatePickerPanel.buttonSelect;
	}

	private void updateInfoForPart(Part part) {
		if (part == null)
			return;
		partPickerPanel.labelPartID.setText(String.valueOf(part.getItemID()));
		partPickerPanel.labelPartName.setText(part.getItemName());
		partPickerPanel.labelPartNumber.setText(part.getPartNumber());
		partPickerPanel.labelPartVendor.setText(part.getPartVendor());
		partPickerPanel.labelQuantityUnit.setText(part.getQuantityUnit().toString());
		partPickerPanel.labelExternalPartNumber.setText(part.getExternalPartNumber());
	}

	public Part getSelectedPartInList() {
		return (Part) selectedPart;
	}

	private void updateInfoForTemplate(ProductTemplate template) {
		if (template == null)
			return;
		templatePickerPanel.labelTemplateID.setText(String.valueOf(template.getItemID()));
		templatePickerPanel.labelProductNumber.setText(template.getItemName());
		templatePickerPanel.labelQuantityUnit.setText(template.getQuantityUnit().toString());
		templatePickerPanel.labelTemplateDescription.setText(template.getDescription());
	}

	public ProductTemplate getSelectedTemplateInList() {
		return (ProductTemplate) selectedTemplate;
	}

	public void hideView() {
		this.setVisible(false);
	}

	// public void showView() {
	// this.setVisible(true);
	// if (inventoryItemView == null || inventoryItemView.getSelectedPart() == null)
	// return;
	// panel.listParts.setSelectedValue(inventoryItemView.getSelectedPart(), true);
	// }

}
