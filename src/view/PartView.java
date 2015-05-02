package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import model.Part;
import model.QuantityUnit;
import view.panels.PartManagerPanel;
import controller.PartController;

public class PartView extends SessionView implements Cloneable {

	private static final long serialVersionUID = 1L;
	private PartManagerPanel panel;

	private Part part;
	public static PartController partController;

	public void setPart(Part part) {
		this.part = part;
		syncWithPart();
	}

	public Part getPart() {
		return this.part;
	}

	public PartView() {
		super("Cabinetron: Part Manager");
		this.part = null;
	}

	private PartView(PartView template) {
		super("Cabinetron: Part Manager");
		setPart(template.getPart());
	}

	{
		try {
			setIconImage(ImageIO.read(new File("src/res/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(480, 420);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new PartManagerPanel();
		add(panel, BorderLayout.CENTER);

		syncWithPart();

		panel.buttonUpdatePart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				partController.updatePart(PartView.this);
			}
		});

		panel.buttonCreatePart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				partController.createPart(PartView.this);
			}
		});
	}

	public void syncWithPart() {
		if (part == null)
			return;
		panel.textFieldPartID.setText(part.getItemID().toString());
		panel.textFieldPartNumber.setText(part.getPartNumber());
		panel.textFieldPartName.setText(part.getItemName());
		panel.textFieldVendor.setText(part.getPartVendor());
		panel.textFieldExternalPartNumber.setText(part.getExternalPartNumber());
		panel.comboBoxQuantityUnit.setSelectedItem(part.getQuantityUnit());
		hideError();
	}

	public void showError(String message) {
		panel.labelError.setText(message);
		panel.labelError.setVisible(true);
	}

	public void hideError() {
		panel.labelError.setText("");
		panel.labelError.setVisible(false);
	}

	public String getPartNumber() {
		return panel.textFieldPartNumber.getText();
	}

	public String getPartName() {
		return panel.textFieldPartName.getText();
	}

	public String getVendor() {
		return panel.textFieldVendor.getText();
	}

	public QuantityUnit getQuantityUnit() {
		return (QuantityUnit) panel.comboBoxQuantityUnit.getSelectedItem();
	}

	public String getExternalPartNumber() {
		return panel.textFieldExternalPartNumber.getText();
	}

	public static void assignPartView(Part part) {
		PartView pv = new PartView();
		part.setPartView(pv);
	}

	@Override
	public PartView clone() {
		return new PartView(this);
	}

	@Override
	public void setVisible(boolean b) {
		syncWithPart();
		super.setVisible(b);
	}

}
