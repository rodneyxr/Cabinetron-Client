package model;

import java.io.Serializable;

import javax.swing.DefaultListModel;

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

}
