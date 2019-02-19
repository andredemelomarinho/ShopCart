package drive;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import automation.utils.PropertiesUtils;
import config.TestConfig;


public class DriverFactory {
	

	public WebDriver getDriver(boolean parallelTest) {
		WebDriver driver = null;

		try {
			switch (PropertiesUtils.getTestProperty("driver").toUpperCase()) {
			case "FIREFOX":
				driver = getFirefoxDriver();
				break;
			case "CHROME":
				driver = getChromeDriver();
				break;
			case "IE":
				driver = getInternetExplorerDriver();
				break;
			default:
				driver = getFirefoxDriver();
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Erro ao criar driver: ", e);
		}
		if(driver == null) {
			throw new RuntimeException("Driver não criado!");
		}
		return driver;
	}

	private WebDriver getFirefoxDriver() throws MalformedURLException {
		WebDriver driver = null;

		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("network.proxy.type", ProxyType.AUTODETECT.ordinal());
		profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", "target/evidencias");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.closeWhenDone", true);
        profile.setPreference("plugin.scan.Acrobat", "99.0");
        profile.setPreference("plugin.scan.plid.all", false);
        profile.setPreference("pdfjs.disabled", true);

		FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setCapability("marionette", true);
        browserOptions.setCapability(FirefoxDriver.PROFILE, profile);

		if (TestConfig.useRemoteDriver()) {
			driver = new RemoteWebDriver(TestConfig.remoteDriverURL(), browserOptions);
		} else {
			if (isWindowsOS()) {
				System.setProperty("webdriver.firefox.driver", "src\\test\\resources\\drives\\geckodriver.exe");
			} else {
				System.setProperty("webdriver.firefox.driver", "src/test/resources/drives/geckodriver");
			}
			driver = new FirefoxDriver(browserOptions);
		}
		if(driver != null) {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		return driver;
	}

	private WebDriver getInternetExplorerDriver() throws MalformedURLException {
		WebDriver driver = null;
		InternetExplorerOptions browserOptions = new InternetExplorerOptions();
		if (TestConfig.useRemoteDriver()) {
			driver = new RemoteWebDriver(TestConfig.remoteDriverURL(), browserOptions);
		} else {
			System.setProperty("webdriver.ie.driver", "src\\test\\resources\\drives\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(browserOptions);
		}
		if(driver != null) {
			driver.manage().window().maximize();
		}
		return driver;
	}

	private WebDriver getChromeDriver() throws MalformedURLException {
		WebDriver driver = null;
		ChromeOptions browserOptions = new ChromeOptions();
		if (TestConfig.useRemoteDriver()) {
			driver = new RemoteWebDriver(TestConfig.remoteDriverURL(), browserOptions);
		} else {
			if (isWindowsOS()) {
				System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drives\\chromedriver.exe");
			} else {
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drives/chromedriver");
			}
			driver = new ChromeDriver(browserOptions);
		}
		if(driver != null) {
			driver.manage().window().maximize();
		}
		return driver;
	}

	private boolean isWindowsOS() {
		String os = System.getProperty("os.name");
			return os.contains("Windows");
	}

}
