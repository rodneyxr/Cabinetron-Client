package view.panels;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import logging.InventoryItemLogEntry;

/**
 * 
 * @author Rodney
 */
public class ItemLogPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ItemLogPanel() {
		initComponents();
	}

	private void initComponents() {

		scrollLogEntryList = new JScrollPane();
		listLogEntries = new JList<InventoryItemLogEntry>();
		listLogEntries.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayout(new BorderLayout());

		/**
		 * Log
		 */
		listLogEntries.setFont(new Font("Tahoma", 1, 18)); // NOI18N
		scrollLogEntryList.setViewportView(listLogEntries);
		add(scrollLogEntryList);

	}

	// inventory
	public JList<InventoryItemLogEntry> listLogEntries;
	private JScrollPane scrollLogEntryList;
}
