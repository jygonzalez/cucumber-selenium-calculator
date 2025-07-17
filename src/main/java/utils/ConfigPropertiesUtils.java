package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReaderUtils {
	
	private static Properties properties;	
	private static FileInputStream fis;
	private static File file;
	private static String relPropertiesFilePathDefault = "src/test/resources/config/config.properties";
	
	private static Properties loadPropertiesFile() {
		
		properties = new Properties();
		file = new File(System.getProperty("user.dir") + File.pathSeparator + relPropertiesFilePathDefault);
		
		try {
			fis = new FileInputStream(file);
			properties.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return properties;		
	}
	
	public static String getValue(String key) {
		
		String keyValue = null;
		
		try {
			if (file == null && properties == null) {
				loadPropertiesFile();
			}
			
			keyValue = properties.getProperty(key);
			return keyValue;
		} catch (Exception e) {
			System.out.println(String.format("ERROR while getting config property value for key '{}', message is: {}", keyValue, e.getMessage()));
			return keyValue;
		}	
	}
	
}
