package logging;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

import javax.rmi.PortableRemoteObject;

import view.ClientItemObserver;

/*
 * StateObserver: client-side implementation of the observer
 * 				when remote EJB executes callback, 
 * 				the observer updates master's status bar
 * WARNING: be careful not to have EJB calls in the observer callback
 *	as EJB is blocked until callback completes.
 *	an EJB call in callback will Deadlock the client
 */
public class StateObserver implements StateObserverRemote {
	private static ArrayList<ClientItemObserver> observers = new ArrayList<>();

	// exportObject turns this class into a little client-side server
	// so that the remote EJB can call it back
	public StateObserver() throws RemoteException {
		PortableRemoteObject.exportObject(this);
	}

	// callback the method that the EJB remotely calls
	@Override
	public void callback(UUID item, InventoryItemLogEntry entry) throws RemoteException {
		if (item == null) {
			System.out.println("null item");
		}
		System.out.println("StateObserver: Callback for item: " + item);
		notifyClientObservers(item, entry);
		System.out.println("StateObserver: done with callback.");
	}

	private static void notifyClientObservers(UUID item, InventoryItemLogEntry entry) {
		for (ClientItemObserver observer : observers) {
			System.out.println("StateObserver: Notifiying observer...");
			observer.itemChanged(item, entry);
			System.out.println("StateObserver: Notified an observer");
		}
	}

	public static void register(ClientItemObserver observer) {
		if (observer != null) {
			observers.add(observer);
			System.out.println("StateObserver: registered observer");
		}
	}

	public static void unregister(ClientItemObserver observer) {
		if (observer != null) {
			observers.remove(observer);
			System.out.println("StateObserver: unregistered observer");
		}
	}

}
