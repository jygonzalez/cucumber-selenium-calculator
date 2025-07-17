package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigPropertiesUtils {

	private static Properties properties;
	private static final String REL_PROPERTIES_FILE_PATH_DEFAULT = "src/test/resources/config/config.properties";

	private ConfigPropertiesUtils() {

	}

	private static synchronized void loadPropertiesFile() {

		if (properties == null) {

			properties = new Properties();
			File file = Paths.get(System.getProperty("user.dir"), REL_PROPERTIES_FILE_PATH_DEFAULT).toFile();

			try (FileInputStream fis = new FileInputStream(file)) {

				properties.load(fis);

			} catch (IOException e) {

				System.err.println("Error loading properties file: " + e.getMessage());
				throw new RuntimeException("Failed to load configuration properties.", e);

			}
		}
	}

	public static String getValue(String key) {

		String keyValue = null;

		if (properties == null) {
			loadPropertiesFile();
		}

		keyValue = properties.getProperty(key);

		if (keyValue == null) {
			System.err.println(String.format("Warning: Configuration key '%s' not found in properties file.", key));
		}

		return keyValue;
	}

}
