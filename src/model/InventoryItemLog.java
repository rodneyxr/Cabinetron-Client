package model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultListModel;

import logging.InventoryItemLogEntry;
import logging.InventoryItemLogGatewayRemote;
import logging.StateObserver;
import view.Main;

public class InventoryItemLog implements Serializable {
	private static final long serialVersionUID = 4902117531444227541L;

	private DefaultListModel<InventoryItemLogEntry> entries;
	public static InventoryItemLogGatewayRemote itemLogGateway;
	public static StateObserver stateObserver;

	static {
		if (itemLogGateway == null || stateObserver == null) {
			if (Main.DEBUG_MODE) {
				System.out.println("InventoryItemLog: Connecting to InventoryItemLogGatewayRemote...");
			}
			try {
				Properties props = new Properties();
				props.put("org.omg.CORBA.ORBInitialHost", "localhost");
				props.put("org.omg.CORBA.ORBInitialPort", "3700");

				InitialContext itx = new InitialContext(props);
				itemLogGateway = (InventoryItemLogGatewayRemote) itx.lookup("java:global/CabinetronEAR/cabinetron_bean/InventoryItemLogGateway!logging.InventoryItemLogGatewayRemote");
				System.out.println("Successfully connected to InventoryItemLogGatewayRemote!");

				if (Main.DEBUG_MODE)
					System.out.println("InventoryItemLog: Initializing StateObserver...");
				stateObserver = new StateObserver();
				itemLogGateway.registerObserver(stateObserver);
				System.out.println("Registered client as InventoryItemLog observer.");
			} catch (NamingException e1) {
				e1.printStackTrace();
				System.err.println("Error: Failed to connect to InventoryItemLogGatewayRemote!");
				itemLogGateway = null;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public InventoryItemLog() {
		this(new DefaultListModel<InventoryItemLogEntry>());
	}

	public InventoryItemLog(DefaultListModel<InventoryItemLogEntry> entries) {
		setEntries(entries);
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

	public void addLogEntryToDB(UUID item, InventoryItemLogEntry logEntry) {
		itemLogGateway.addLogEntry(item, logEntry);
	}

}
