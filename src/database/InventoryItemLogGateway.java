package database;

import model.InventoryItem;
import model.InventoryItemLogEntry;
import redis.clients.jedis.Jedis;

// Redis DB #42
public class InventoryItemLogGateway {

	public static final String KEY_LOG = "inventory_item_log.";

	public InventoryItemLogGateway() {
	}

	public void addLogEntry(InventoryItem item, InventoryItemLogEntry entry) {
		Jedis jedis = open();
		System.out.println(getKeyForItem(item));
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
// TODO: append an entry onto inventory_item_log.eb6cd3dc-2475-47d7-bf0e-06f85ea1f5d6 
}
