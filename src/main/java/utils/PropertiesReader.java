package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesReader {

	private static final Properties properties = loadFile();
	private static final String REL_PROPERTIES_FILE_PATH_DEFAULT = "src/test/resources/config/config.properties";

	private PropertiesReader() {

	}

	private static Properties loadFile() {

		Properties props = new Properties();
		File file = Paths.get(System.getProperty("user.dir"), REL_PROPERTIES_FILE_PATH_DEFAULT).toFile();

		try (FileInputStream fis = new FileInputStream(file)) {

			props.load(fis);

		} catch (IOException e) {

			System.err.println("Error loading properties file: " + e.getMessage());
			throw new RuntimeException("Failed to load configuration properties.", e);

		}

		return props;
	}

	public static String getValue(String key) {

		String keyValue = properties.getProperty(key);

		if (keyValue == null) {
			System.err.println(String.format("Warning: Configuration key '%s' not found in properties file.", key));
		}

		return keyValue;
	}

}
