package config;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import automation.utils.PropertiesUtils;

public class TestConfig {

	public static boolean useRemoteDriver() {
		String useRemoteDriver = PropertiesUtils.getTestProperty("remotedriver.use");
		return "true".equalsIgnoreCase(useRemoteDriver);
	}

	public static URL remoteDriverURL() throws MalformedURLException {
		String remoteHub = PropertiesUtils.getTestProperty("remotedriver.hub");
		return new URL(remoteHub);
	}

	private static String resourcePath(String path) {
		String basePath = "target/"; 
		String fullpath = basePath + path;
		File dir = new File(fullpath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		return fullpath;
	}

	public static String resourceLogsPath() {
		return resourcePath("logs/");
	}

	public static String resourceReportsPath() {
		return resourcePath("relatorios/");
	}

	public static String outputPath() {
		return resourcePath("out/");
	}

	public static String outputPath(String child) {
		return outputPath() + child;
	}

	public static String outputPathEvidencia() {
		return outputPath("Evidencia/");
	}

	public static String inputPath() {
		return resourcePath("in/");
	}

	public static String inputPath(String child) {
		return inputPath() + child;
	}

}
