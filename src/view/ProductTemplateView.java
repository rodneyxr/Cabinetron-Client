package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import model.ProductTemplate;
import model.ProductTemplatePart;
import view.panels.ProductTemplatePanel;
import view.panels.ProductTemplatePartView;
import controller.ControllerUtils;
import controller.ProductTemplateController;

public class ProductTemplateView extends SessionView {
	private static final long serialVersionUID = 1L;

	public static ProductTemplateController templateController;
	private ProductTemplate template;
	public DefaultListModel<ProductTemplatePart> partsList;

	// GUI items
	private ProductTemplatePanel panel; // panel to hold all elements

	public ProductTemplateView() {
		super("Cabinetron: Product Template");

		try {
			setIconImage(ImageIO.read(new File("src/res/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setSize(800, 450);
		this.setMinimumSize(new Dimension(800, 450));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new ProductTemplatePanel();
		hideError();
		add(panel, BorderLayout.CENTER);
		partsList = new DefaultListModel<ProductTemplatePart>();
		activateListeners();
	}

	public ProductTemplate getProductTemplate() {
		return template;
	}

	public void setProductTemplate(ProductTemplate template) {
		this.template = template;
		syncWithTemplate();
	}

	public void syncWithTemplate() {
		if (template == null)
			return;
		panel.textFieldProductID.setText(template.getTemplateID().toString());
		panel.textFieldProductNumber.setText(template.getProductNumber());
		panel.textAreaDescription.setText(template.getDescription());
		updateTemplatePartList();
	}

	public void showError(String message) {
		panel.labelError.setText(message);
		panel.labelError.setVisible(true);
	}

	public void hideError() {
		panel.labelError.setText("");
		panel.labelError.setVisible(false);
	}

	public ProductTemplate getSelectedTemplate() {
		return template;
	}

	public void updateTemplatePartList() {
		panel.listTemplateParts.updateUI();
	}

	public String getProductNumber() {
		return panel.textFieldProductNumber.getText();
	}

	public String getProductDescription() {
		return panel.textAreaDescription.getText();
	}

	public ProductTemplatePart getSelectedPart() {
		return (ProductTemplatePart) panel.listTemplateParts.getSelectedValue();
	}

	private void activateListeners() {
		panel.buttonDeleteTemplatePart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					templateController.deleteProductTemplatePart(ProductTemplateView.this);
				} catch (Exception e1) {
					showError(e1.getMessage());
				}
			}
		});

		panel.buttonAddTemplatePart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					templateController.addProductTemplatePart(ProductTemplateView.this);
				} catch (Exception e1) {
					showError(e1.getMessage());
				}
			}
		});

		panel.buttonUpdateTemplate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					templateController.updateProductTemplate(ProductTemplateView.this);
				} catch (Exception e1) {
					showError(e1.getMessage());
				}
			}
		});

		panel.buttonCreateTemplate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					templateController.createProductTemplate(ProductTemplateView.this);
				} catch (Exception e1) {
					showError(e1.getMessage());
				}
			}
		});

		panel.listTemplateParts.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object o = ControllerUtils.getDoubleClickedItemInList(e);
				if (o == null)
					return;
				if (o instanceof ProductTemplatePart) {
					System.out.println("ProductTemplateView: mouseClicked()");
					ProductTemplatePart templatePart = (ProductTemplatePart) o;
					ProductTemplatePartView templatePartView = new ProductTemplatePartView(ProductTemplateView.this);
					ProductTemplateView.this.setRightPanel(templatePartView);
					templatePartView.textFieldItemID.setText(templatePart.getProductTemplateID().toString());
					templatePartView.buttonSelectedPart.setText(templatePart.getPartID().toString());
					templatePartView.setUpdateOnly();
					templatePartView.textFieldQuantity.setText(String.valueOf(templatePart.getQuantity()));
					templatePartView.setProductTemplatePart(templatePart);
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
	}

	public void setRightPanel(JPanel p) {
		panel.setRightPanel(p);
	}

	public JPanel getRightPanel() {
		return panel;
	}

	public void revertRightPanel() {
		panel.revertRightPanel();
	}

	public void addElementToTemplatePartList(ProductTemplatePart part) {
		partsList.addElement(part);
		setTemplatePartList();
	}

	public DefaultListModel<ProductTemplatePart> getPartsList() {
		return partsList;
	}

	public JList<ProductTemplatePart> getList() {
		return panel.listTemplateParts;
	}

	public void setTemplatePartModel(DefaultListModel<ProductTemplatePart> templateParts) {
		// update local reference
		this.partsList = templateParts;

		// update jlist model
		panel.listTemplateParts.setModel(templateParts);
	}

	public void setTemplatePartList() {
		panel.listTemplateParts.setModel(partsList);
		this.updateTemplatePartList();
	}

	/**
	 * set this item as old and only allow updating
	 */
	public void setUpdateOnly() {
		panel.buttonCreateTemplate.setVisible(false);
	}

	public static void assignProductTemplateView(ProductTemplate pt) {
		ProductTemplateView ptv = new ProductTemplateView();
		pt.setView(ptv);
	}

	@Override
	public void setVisible(boolean b) {
		syncWithTemplate();
		super.setVisible(b);
	}

	public void removePartByPartID(String partid) {
		for (int i = 0; i < partsList.getSize(); i++) {
			ProductTemplatePart ptp = partsList.get(i);
			if (ptp.getPartID().toString().equals(partid)) {
				partsList.remove(i);
			}
		}
	}
}
