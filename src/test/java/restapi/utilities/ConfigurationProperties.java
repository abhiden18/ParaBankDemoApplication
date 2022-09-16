package restapi.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class ConfigurationProperties {
    static HashMap<String, String> configProperties = null;
	static Properties propInfo = new Properties();
	static InputStream fileStream = null;
	static String filename = "config.properties";


	/**
	 * Gets a property value based on a given key value
	 * @param inputKey type String
	 * @return String
	 */
	public static String getPropertyValueByKey(String inputKey) {
		String value = null; 
		try {
			if (!inputKey.equals(null) && !inputKey.equals("")) {
				fileStream = new Object(){}.getClass().getClassLoader().getResourceAsStream(filename);
				if (fileStream == null) {
					throw new FileNotFoundException("Unable to find configuration file: " + filename);
				}
				propInfo.load(fileStream);
				Enumeration<?> e = propInfo.propertyNames();
				if (!e.equals(null)) {
					while (e.hasMoreElements()) {
						String keyName = (String) e.nextElement();
						if (inputKey.equals(keyName)) {
							value = propInfo.getProperty(keyName);
							break;
						}
					}
				} 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Get all properties listed in .properties file
	 * @return HashMap<String,String>
	 */
	public static HashMap<String, String> getAllProperties() {
		String keyName = null,value = null;
		try {
			fileStream = new Object(){}.getClass().getClassLoader().getResourceAsStream(filename);
			if (fileStream == null) {
				throw new FileNotFoundException("Unable to find configuration file: " + filename);
			}
			propInfo.load(fileStream);
			Enumeration<?> e = propInfo.propertyNames();
			if (!e.equals(null)) {
				configProperties = new HashMap<String, String>();
				while (e.hasMoreElements()) {
					keyName = (String) e.nextElement();
					value = propInfo.getProperty(keyName);
					configProperties.put(keyName, value);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configProperties;
	}

	/**
	 * Get a file from the resources folder
	 * @return File object
	 */
	public static File getResourceFile(String fileName) throws FileNotFoundException, IOException
	{
		ClassLoader classLoader = new Object(){}.getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
}
