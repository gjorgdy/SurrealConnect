package eu.hexasis.surrealconnect;

import io.github.prismwork.prismconfig.api.PrismConfig;
import io.github.prismwork.prismconfig.api.config.DefaultDeserializers;
import io.github.prismwork.prismconfig.api.config.DefaultSerializers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigHandler {

	public static String path = "config/surreal-connect";

	public static Config readFile() {
		// select file
		File configFile = new File(path + "/config.json");
		// check if file exists
		if (configFile.isFile()) {
			// read file and return config object
			return PrismConfig.getInstance().serialize(
				Config.class,
				configFile,
				DefaultSerializers.getInstance().json5(Config.class)
			);
		} else {
			// create folder if it doesn't exist
			try {
				Files.createDirectories(Paths.get(path));
			} catch (IOException e) {
				throw new RuntimeException("Could not create config file: " + e);
			}
			// create empty config object
			Config baseConfig = new Config();
			// write to file
			PrismConfig.getInstance().deserializeAndWrite(
					Config.class,
					baseConfig,
					DefaultDeserializers.getInstance().json5(Config.class),
					configFile
			);
			// return created config objects
			return baseConfig;
		}

	}

}
