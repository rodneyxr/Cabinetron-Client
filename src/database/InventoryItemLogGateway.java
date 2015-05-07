package database;

import java.util.Set;

import model.InventoryItem;
import model.InventoryItemLog;
import model.InventoryItemLogEntry;
import redis.clients.jedis.Jedis;
import view.Main;
import controller.ControllerUtils;

// Redis DB #42
public class InventoryItemLogGateway {

	public static final String KEY_LOG = "inventory_item_log.";

	public InventoryItemLogGateway() {
	}

	public InventoryItemLog getLog(InventoryItem item) {
		if (Main.DEBUG_MODE)
			System.out.println("InventoryItemLogGateway->getLog(): " + getKeyForItem(item)); // DEBUG
		Jedis jedis = open();

		// instantiate log to return
		InventoryItemLog log = new InventoryItemLog();

		// get key and retrieve the set of entries from the DB
		String key = getKeyForItem(item);
		long cardinality = jedis.zcard(key);
		Set<String> blobs = jedis.zrange(key, 0, cardinality);
		System.out.println("Cardinality: " + cardinality + ", Size: " + blobs.size()); // DEBUG

		// add them to the log object
		for (String blob : blobs) {
			log.addLogEntry((InventoryItemLogEntry) ControllerUtils.deblobify(blob));
		}

		jedis.close();
		return log;
	}

	public void addLogEntry(InventoryItem item, InventoryItemLogEntry entry) {
		System.out.println("InventoryItemLogGateway->addLogEntry(): " + getKeyForItem(item)); // DEBUG
		System.out.println(entry); // DEBUG
		Jedis jedis = open();

		String key = getKeyForItem(item);
		// get cardinality of set to append to the end of the ordered set
		long insertPos = jedis.zcard(key);
		if (insertPos > 0)
			insertPos += 1;

		// add entry to the end of the ordered set
		jedis.zadd(key, insertPos, ControllerUtils.blobify(entry));

		jedis.close();
	}

	private final String getKeyForItem(InventoryItem item) {
		return KEY_LOG + item.getItemID().toString();
	}

	private Jedis open() {
		// Connect to Redis server
		Jedis jedis = new Jedis("cloud.fulgentcorp.com");
		jedis.auth("8fgJk923j3gdK2023e9jvBj45otrJ298dh3gHjk4t10jmV94buh");
		// 0 is the default database
		// NOTE: select the db that is assigned to you
		jedis.select(42);
		return jedis;
	}
}
