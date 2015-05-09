package view;

import java.util.UUID;

import logging.InventoryItemLogEntry;

public interface ClientItemObserver {
	public void itemChanged(UUID item, InventoryItemLogEntry entry);
}
