package automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

	public final static String environment = getTestProperty("environment");
	private static Properties testProperties = null;
	private static Properties appProperties = null;

	private synchronized static void loadProp() throws IOException {
		if (testProperties == null) {
			testProperties = new Properties();
			FileInputStream file = new FileInputStream("src/test/resources/properties/testconfig.properties");
			testProperties.load(file);
			
			
		}
	}

	private static void loadAppProp() throws IOException {
		if (appProperties == null) {
			appProperties = new Properties();
			FileInputStream file = new FileInputStream("src/test/resources/properties/" + getTestProperty("appconfig") + ".properties");
			
			appProperties.load(file);
		}
	}

	public static String getAppProperty(String key) {
		try {
			loadAppProp();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appProperties.getProperty(key);
	}

	public static String getTestProperty(String atributo) {
		try {
			loadProp();
			return testProperties.getProperty(atributo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
