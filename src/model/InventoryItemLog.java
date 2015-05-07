package model;

import java.io.Serializable;

import javax.swing.DefaultListModel;

import view.Main;

public class InventoryItemLog implements Serializable {
	private static final long serialVersionUID = 4902117531444227541L;

	private DefaultListModel<InventoryItemLogEntry> entries;

	public InventoryItemLog() {
		entries = new DefaultListModel<>();
	}

	public DefaultListModel<InventoryItemLogEntry> getEntries() {
		return entries;
	}

	public void setEntries(DefaultListModel<InventoryItemLogEntry> entries) {
		this.entries = entries;
	}

	public void addLogEntry(InventoryItemLogEntry logEntry) {
		entries.addElement(logEntry);
	}
	
	public void addLogEntryToDB(InventoryItem item, InventoryItemLogEntry logEntry) {
		entries.addElement(logEntry);
		Main.itemLogGateway.addLogEntry(item, logEntry);
	}

}
