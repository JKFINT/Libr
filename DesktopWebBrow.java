package Libraries;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DesktopWebBrow extends Driver {

	WebDriver remoteDriver = null;

	public DesktopWebBrow() {
		
	}

	public WebDriver getNewDriver() {
		remoteDriver = getRemoteDriver();
		return remoteDriver;
	}

	public WebDriver getRemoteDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		switch (browser.get().toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", WorkingDir.get() + "/Drivers/chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			// Set the notification setting it will override the default setting
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			//capability.setPlatform(Platform.UNIX);
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--disable-extensions");
			options.setExperimentalOption("prefs", prefs);
			options.setProxy(null);
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			break;
			
		case "firefox":
			System.setProperty("webdriver.gecko.driver", WorkingDir.get() + "/Drivers/geckodriver.exe");
			// System.setProperty("webdriver.Firefoxdriver", Driver.basepth.get() +
			// "/geckodriver.exe");
			// File pathToBinary = new File("C:\\Users\\ImranH2\\AppData\\Local\\Mozilla
			// Firefox\\firefox.exe");
			// FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.UNIX);
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("network.proxy.type", 0);
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
			capability.setCapability(FirefoxDriver.PROFILE,firefoxProfile);
			//remoteDriver = new FirefoxDriver();
			// remoteDriver = new FirefoxDriver();
			break;
			
		case "ie":
			capability = DesiredCapabilities.internetExplorer();
			capability.setBrowserName("internetExplorer");
			capability.setPlatform(Platform.WINDOWS);
			
			//System.setProperty("webdriver.ie.driver", WorkingDir.get() + "/Drivers/IEDriverServer.exe");
			//DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			capability.setCapability("ignoreZoomSetting", true);
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability("requireWindowFocus", true);
			capability.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			//remoteDriver = new InternetExplorerDriver();
			break;
		}
//		try
//		{
//			remoteDriver = new RemoteWebDriver(new URL("http://10.34.216.100:4444/wd/hub"), capability);
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		return remoteDriver;
		return remoteDriver;
	}

}