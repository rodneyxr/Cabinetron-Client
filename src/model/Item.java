package model;

import java.util.UUID;

public interface Item {
	UUID getItemID();

	String getItemName();

	QuantityUnit getQuantityUnit();
}
