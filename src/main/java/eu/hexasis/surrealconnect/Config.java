package eu.hexasis.surrealconnect;

import io.github.prismwork.prismconfig.api.PrismConfig;
import io.github.prismwork.prismconfig.api.config.DefaultDeserializers;

public class Config {

	public String url = "localhost";
	public int port = 8000;
	public boolean tls = false;
	public String username = "root";
	public String password = "root";

	// auto-connect to single database
	public String database = "";
	public String namespace = "";

	@Override
	public String toString() {
		return PrismConfig.getInstance().deserialize(
			Config.class,
			this,
			DefaultDeserializers.getInstance().json5(Config.class)
		);
	}
}
