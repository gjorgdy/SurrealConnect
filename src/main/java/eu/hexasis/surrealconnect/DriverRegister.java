package eu.hexasis.surrealconnect;

import com.surrealdb.driver.AsyncSurrealDriver;
import com.surrealdb.driver.SyncSurrealDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverRegister {

	public static final Map<DriverID, SyncSurrealDriver> syncSurrealDriverMap = new HashMap<>();
	public static final Map<DriverID, AsyncSurrealDriver> asyncSurrealDriverMap = new HashMap<>();

	public record DriverID(
		String namespace,
		String database
	) {}

	public static void setSync(String namespace, String database, SyncSurrealDriver driver) {
		syncSurrealDriverMap.put(
			new DriverID(
				namespace,
				database
			),
			driver
		);
	}

	public static SyncSurrealDriver getSync(String namespace, String database) {
		return syncSurrealDriverMap.get(
			new DriverID(
				namespace,
				database
			)
		);
	}

	public static void setAsync(String namespace, String database, AsyncSurrealDriver driver) {
		asyncSurrealDriverMap.put(
			new DriverID(
				namespace,
				database
			),
			driver
		);
	}

	public static AsyncSurrealDriver getAsync(String namespace, String database) {
		return asyncSurrealDriverMap.get(
			new DriverID(
				namespace,
				database
			)
		);
	}
}
