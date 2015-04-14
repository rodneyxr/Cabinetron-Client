package view.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Part;
import model.ProductTemplatePart;
import view.Main;
import view.PartPickerView;
import view.ProductTemplateView;

/**
 *
 * @author Rodney
 */
public class ProductTemplatePartView extends JPanel {

	private static final long serialVersionUID = 1L;

	private ProductTemplateView parent;
	private ProductTemplatePart templatePart;
	private PartPickerView partPickerView;

	/**
	 * Creates new form PartManagerPanel
	 */
	public ProductTemplatePartView(ProductTemplateView parent) {
		this.parent = parent;
		this.partPickerView = new PartPickerView(Main.partsModel);
		initComponents();
		registerListeners();
	}

	public void setProductTemplatePart(ProductTemplatePart templatePart) {
		this.templatePart = templatePart;
	}

	public ProductTemplatePart getProductTemplatePart() {
		return templatePart;
	}

	private double getQuantity() throws NumberFormatException {
		return Double.parseDouble(textFieldQuantity.getText());
	}

	public void syncWithTemplatePart() {
		if (templatePart == null)
			return;
		this.textFieldQuantity.setText(Double.toString(templatePart.getQuantity()));
	}
	
	public void setPart() {
		buttonModifyTemplatePart.setText("Update");
		
	}

	private Part selectedPart;

	private void registerListeners() {
		buttonModifyTemplatePart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
					case "Update":
						try {
							ProductTemplateView.templateController.updateProductTemplatePart(templatePart, getQuantity());
						} catch (Exception e2) {
							showError(e2.getMessage());
							return;
						}
						parent.revertRightPanel();
						break;

					case "Create":
						try {
							if (parent.getProductTemplate() == null) {
								if (selectedPart == null)
									throw new Exception("Error: You need to select a part.");
								parent.addElementToTemplatePartList(new ProductTemplatePart(selectedPart.getItemID(), getQuantity()));	
							} else {
								ProductTemplateView.templateController.createProductTemplatePart(parent.getProductTemplate(), selectedPart, getQuantity());
							}
						} catch (Exception e1) {
							showError(e1.getMessage());
							return;
						}
						parent.revertRightPanel();
						break;

					default:
						break;

				}
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.revertRightPanel();
				syncWithTemplatePart();
			}
		});

		buttonSelectedPart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				partPickerView.showView();
			}
		});

		// select part (inside part picker) button
		partPickerView.getButtonSelect().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.hideError();
				Part part = partPickerView.getSelectedPartInList();
				if (part == null) {
					partPickerView.hideView();
					parent.toFront();
					showError("Error: No part was selected");
					return;
				}
				selectedPart = partPickerView.getSelectedPartInList();
				buttonSelectedPart.setText(part.getItemID().toString());
				partPickerView.hideView();
			}
		});

	}

	public void showError(String message) {
		labelError.setText(message);
		labelError.setVisible(true);
	}

	public void hideError() {
		labelError.setText("");
		labelError.setVisible(false);
	}

	public void setParent(ProductTemplateView parent) {
		this.parent = parent;
	}

	public void setUpdateOnly() {
		buttonSelectedPart.setEnabled(false);
		buttonModifyTemplatePart.setText("Update");
	}
	
	public static void assignProductTemplatePartView(ProductTemplatePart ptp) {
		ProductTemplatePartView ptpv = new ProductTemplatePartView(ptp.getTemplate().getView());
		ptp.setView(ptpv);
	}

	// ////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * GUI
	 */
	// ////////////////////////////////////////////////////////////////////////////////////////
	private JLabel labelItemID;
	private JLabel labelSelectedPart;
	private JLabel labelQuantity;
	private JLabel labelTitle;
	private JPanel panelButtons;

	public JLabel labelError;
	public JButton buttonModifyTemplatePart;
	public JButton buttonCancel;
	public JButton buttonSelectedPart;
	public JTextField textFieldItemID;
	public JTextField textFieldQuantity;

	private void initComponents() {
		GridBagConstraints gridBagConstraints;
		labelTitle = new JLabel();
		labelError = new JLabel();
		labelItemID = new JLabel();
		labelSelectedPart = new JLabel();
		labelQuantity = new JLabel();
		textFieldItemID = new JTextField();
		buttonSelectedPart = new JButton();
		textFieldQuantity = new JTextField();
		panelButtons = new JPanel();
		buttonCancel = new JButton();
		buttonModifyTemplatePart = new JButton();

		setLayout(new GridBagLayout());

		labelTitle.setFont(new Font("Tahoma", 0, 18)); // NOI18N
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setText("Template Part");
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

		labelItemID.setText("Product ID");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.insets = new Insets(1, 5, 1, 1);
		add(labelItemID, gridBagConstraints);

		labelSelectedPart.setText("Part ID:");
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

		buttonSelectedPart.setText("Select Part");
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

		panelButtons.setLayout(new GridBagLayout());

		buttonCancel.setText("Cancel");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelButtons.add(buttonCancel, gridBagConstraints);

		buttonModifyTemplatePart.setText("Create");
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.ipady = 10;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new Insets(1, 1, 1, 1);
		panelButtons.add(buttonModifyTemplatePart, gridBagConstraints);

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
}
