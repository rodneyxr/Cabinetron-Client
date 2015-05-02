package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Part;
import model.PartsModel;
import view.panels.PartPickerPanel;

public class PartPickerView extends SessionView {
	private static final long serialVersionUID = 1L;

	private PartPickerPanel panel;
	private InventoryItemView inventoryItemView;
	private Part selectedPartInList;

	/**
	 * 
	 * @param inventory
	 */
	public PartPickerView(PartsModel partsModel,
			InventoryItemView inventoryItemView) {
		super("Cabinetron Part Picker");
		this.inventoryItemView = inventoryItemView;

		try {
			setIconImage(ImageIO.read(new File("src/res/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create the main view
		panel = new PartPickerPanel();
		add(panel, BorderLayout.CENTER);

		this.setSize(520, 480);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// link the lists to their respective models
		panel.listParts.setModel(Main.partsModel.getPartList());

		activateListeners();
	}

	public PartPickerView(PartsModel partsModel) {
		super("Cabinetron Part Picker");

		// create the main view
		panel = new PartPickerPanel();
		add(panel, BorderLayout.CENTER);

		this.setSize(520, 480);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// link the lists to their respective models
		panel.listParts.setModel(Main.partsModel.getPartList());

		activateListeners();
	}

	public int getSelectedPartIndex() {
		return panel.listParts.getSelectedIndex();
	}

	public void updatePartsList() {
		panel.listParts.updateUI();
	}

	public JButton getButtonSelect() {
		return panel.buttonSelect;
	}

	public Part getSelectedPartInList() {
		return selectedPartInList;
	}

	public void showView() {
		this.setVisible(true);
		if (inventoryItemView == null
				|| inventoryItemView.getSelectedItem() == null)
			return;
		panel.listParts.setSelectedValue(inventoryItemView.getSelectedItem(),
				true);
	}

	public void hideView() {
		this.setVisible(false);
	}

	private void updateInfoForPart(Part part) {
		if (part == null)
			return;
		panel.labelPartID.setText(String.valueOf(part.getItemID()));
		panel.labelPartName.setText(part.getItemName());
		panel.labelPartNumber.setText(part.getPartNumber());
		panel.labelPartVendor.setText(part.getPartVendor());
		panel.labelQuantityUnit.setText(part.getQuantityUnit().toString());
		panel.labelExternalPartNumber.setText(part.getExternalPartNumber());
	}

	private void activateListeners() {
		panel.listParts.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				@SuppressWarnings("unchecked")
				JList<Part> list = (JList<Part>) e.getSource();
				if (list.getSelectedValue() == null)
					PartPickerView.this.setEnabled(false);
				PartPickerView.this.updateInfoForPart(list.getSelectedValue());
				PartPickerView.this.selectedPartInList = list.getSelectedValue();
			}
		});

		panel.buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("PartPickerView: 'Cancel' button clicked.");
				PartPickerView.this.hideView();
			}
		});
	}
}
