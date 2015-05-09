Applied Software Engineering Assignment 6

Rodney Rodriguez
kto128

NOTES:
	- The project needs to use Java 8 because of the new Base64 class that is used 
	by logging.Utils in cabinetron_bean.

	- I spent several hours trying to get the jedis-2.6.2.jar to deploy to the 
	Glassfish server but I was unsuccessful just using the EJB project. I solved 
	this issue by creating a EAR project and adding the EJB as a dependency. So 
	in order to run the server you need to deploy the EAR project(CabinetronEAR)
	to the server not the EJB project.


Login screen:
	I already had a login screen but it had a ComboBox. I just replaced the 
	ComboBox with a username field, password field, close button, and login button.

Authentication as remote session EJB:
	I went with method 3a and used a stateless Authenticator class that returns a 
	Session object to the client.

User as entity EJB:
	You said in class not to worry about the persistence API

Inventory item add/change actions create log entries:
	When changes are made or inventory items are created the bean is
	notified to add that to the database.

Log View:
	There is a button inside the Inventory Item view to show
	the log. The log is just a JList filled with
	InventoryItemLogEntry objects.

Use of Redis as log database:
	The redis database is accessed by the bean. The gateway bean is called when
	the client calls the following methods:
	void InventoryItemLogGateway.addLogEntry(UUID, InventoryItemLogEntry)
	DefaultListModel<InventoryItemLogEntry> getLogListModel(UUID)

	-To store an entry a client sends the gateway the item id which is used as
	part of the key along with the entry itself. The Entry object is serialized
	and encoded using Base64 and stored as that.

	-To retrieve a list of log entries the client sends the gateway the item id
	which is used as part of the key and retries a sorted Set of the serialized
	object encoded in Base64. The data is decoded and constructed into an Entry
	object and added to a list to return to the client.
	
Client log views automatically update via Remote Observer as remote message EJB:
	Each client will register as a remote observer with the bean. On the client
	the active Logs will register as observers with the client side observer
	that is an observer of the bean. When a log entry is updated the bean will
	send a callback to the client observer containing the item id and log entry.
	The client side observers are notified and if the item id matches then the
	log entry will be added to its log locally to show changes in real-time.