package eu.hexasis.surrealconnect;

import com.surrealdb.connection.SurrealWebSocketConnection;
import com.surrealdb.driver.AsyncSurrealDriver;
import com.surrealdb.driver.SyncSurrealDriver;
import net.fabricmc.api.ModInitializer;

public class SurrealConnect implements ModInitializer {

	private static SurrealConnect INSTANCE;

	public final Config config = ConfigHandler.readFile();
	public final SurrealWebSocketConnection connection;

	public SurrealConnect() {
		connection =
			new SurrealWebSocketConnection(
				config.url,
				config.port,
				config.tls
			);
		connection.connect();

		SurrealConnect.INSTANCE = this;
	}

	public static SurrealConnect getInstance() {
		if (INSTANCE != null) {
			return INSTANCE;
		} else {
			throw new RuntimeException("SurrealConnect class has not been loaded yet");
		}
	}

	@Override
	public void onInitialize() {}

	public SyncSurrealDriver getSyncDriver() {
		return getSyncDriver(
			config.namespace,
			config.database
		);
	}

	public SyncSurrealDriver getSyncDriver(String namespace, String database) {
		SyncSurrealDriver driver = new SyncSurrealDriver(connection);
		driver.signIn(
			config.username,
			config.password
		);
		driver.use(namespace, database);
		return driver;
	}

	public AsyncSurrealDriver getAsyncDriver() {
		return getAsyncDriver(
			config.namespace,
			config.database
		);
	}

	public AsyncSurrealDriver getAsyncDriver(String namespace, String database) {
		AsyncSurrealDriver driver = new AsyncSurrealDriver(connection);
		driver.signIn(
			config.username,
			config.password
		).join();
		driver.use(namespace, database).join();
		return driver;
	}

}
