package Libraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.browserstack.local.Local;

import io.appium.java_client.ios.IOSDriver;

public class MobileAppiOSBSDriver extends Driver {

	private static Local localInstance;

	public static void setupLocal() throws Exception {
		localInstance = new Local();
		Map<String, String> options = new HashMap<String, String>();
		options.put("key", ReadMobileproperties("browserStack", "bs_access_key"));
		localInstance.start(options);
	}

	public static void tearDownLocal() throws Exception {
		localInstance.stop();
	}

	public static void tearDown() throws Exception {
		driver.get().quit();

	}

	public static String ReadMobileproperties(String fname, String propname) throws IOException {
		String fpath = WorkingDir.get() + "/Drivers/Config/" + fname + ".properties";
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);

		prop.load(input);
		// prop.getProperty(propname);
		return prop.getProperty(propname);
	}

	public static void openBrowserStack(String OS) throws IOException {
		env.set("mobile");

		switch (OS) {
		case "iOS":
			clog.info("Browser stack connection started");
			DesiredCapabilities caps = new DesiredCapabilities();

			// Set your access credentials
			try {
				System.setProperty("proxyHost", "proxy.mobily.lan");
				System.setProperty("proxyPort", "8080");
				caps.setCapability("browserstack.user", ReadMobileproperties("iOS", "bs_Username"));
				caps.setCapability("browserstack.key", ReadMobileproperties("iOS", "bs_access_key"));
				//caps.setCapability("autoAcceptAlerts", "true");
				caps.setCapability("autoGrantPermissions", "true");

				clog.info("Browser stack connection started user name");
				// Set URL of the application under test
				// caps.setCapability("app", "bs://<app-id>");
				caps.setCapability("app", ReadMobileproperties("iOS", "app_Id"));

				// Specify device and os_version for testing
				caps.setCapability("device", ReadMobileproperties("iOS", "device"));
				caps.setCapability("os_version", ReadMobileproperties("iOS", "version"));

				clog.info("Browser stack connection started Device name");

				// Set other BrowserStack capabilities
				caps.setCapability("project", "Mobily iOS");
				caps.setCapability("build", Release_name.get());
				caps.setCapability("name", Usecase.get());

				clog.info("Browser stack connection started Project name");
				// Initialise the remote Webdriver using BrowserStack remote URL
				// and desired capabilities defined above

				driver.set(new IOSDriver(new java.net.URL("http://hub.browserstack.com/wd/hub"), caps));
				clog.info("Browser stack connection done");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "iOS Local":
			try {
				setupLocal();
				DesiredCapabilities capabilities = new DesiredCapabilities();

				// Set your access credentials
				capabilities.setCapability("browserstack.user", ReadMobileproperties("iOS", "bs_Username"));
				capabilities.setCapability("browserstack.key", ReadMobileproperties("iOS", "bs_access_key"));

				// Set URL of the application under test

				capabilities.setCapability("app", ReadMobileproperties("iOS", "app_Id"));

				// Specify device and os_version for testing
				capabilities.setCapability("device", ReadMobileproperties("iOS", "device"));
				capabilities.setCapability("os_version", ReadMobileproperties("iOS", "version"));

				// Set the browserstack.local capability to true
				capabilities.setCapability("browserstack.local", true);

				// Set other BrowserStack capabilities
				capabilities.setCapability("project", "First Java Project");
				capabilities.setCapability("build", Release_name.get());
				capabilities.setCapability("name", Usecase.get());

				// Initialise the remote Webdriver using BrowserStack remote URL
				// and desired capabilities defined above

				driver.set(new IOSDriver(new java.net.URL("http://hub.browserstack.com/wd/hub"), capabilities));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}
	}

	public static void openBrowserStack_iOS(String OS, String username, String Device) throws IOException {
		env.set("mobile");

		switch (OS) {
		case "iOS":
			clog.info("Browser stack connection started");
			DesiredCapabilities caps = new DesiredCapabilities();

			// Set your access credentials
			try {
				System.setProperty("proxyHost", "proxy.mobily.lan");
				System.setProperty("proxyPort", "8080");
				caps.setCapability("app", ReadMobileproperties("iOS", "app_Id"));
				//caps.setCapability("autoAcceptAlerts", "true");
				caps.setCapability("autoGrantPermissions", "true");
				clog.info("Browser stack connection started user name");


				caps.setCapability("browserstack.user", ReadMobileproperties("iOS", "BS_"+username+"_Name"));
				caps.setCapability("browserstack.key", ReadMobileproperties("iOS", "BS_"+username+"_Key"));


				caps.setCapability("device", ReadMobileproperties("iOS", Device));
				caps.setCapability("os_version", ReadMobileproperties("iOS", Device+"_Version"));



				clog.info("Browser stack connection started Device name");

				// Set other BrowserStack capabilities
				caps.setCapability("project", "Mobily iOS");
				caps.setCapability("build", Release_name.get());
				caps.setCapability("name", Usecase.get());

				clog.info("Browser stack connection started Project name");
				// Initialise the remote Webdriver using BrowserStack remote URL
				// and desired capabilities defined above

				driver.set(new IOSDriver(new java.net.URL("http://hub.browserstack.com/wd/hub"), caps));
				clog.info("Browser stack connection done");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "iOS Local":
			try {
				setupLocal();
				DesiredCapabilities capabilities = new DesiredCapabilities();

				// Set your access credentials
				capabilities.setCapability("browserstack.user", ReadMobileproperties("iOS", "bs_Username"));
				capabilities.setCapability("browserstack.key", ReadMobileproperties("iOS", "bs_access_key"));

				// Set URL of the application under test

				capabilities.setCapability("app", ReadMobileproperties("iOS", "app_Id"));

				// Specify device and os_version for testing
				capabilities.setCapability("device", ReadMobileproperties("iOS", "device"));
				capabilities.setCapability("os_version", ReadMobileproperties("iOS", "version"));

				// Set the browserstack.local capability to true
				capabilities.setCapability("browserstack.local", true);

				// Set other BrowserStack capabilities
				capabilities.setCapability("project", "First Java Project");
				capabilities.setCapability("build", Release_name.get());
				capabilities.setCapability("name", Usecase.get());

				// Initialise the remote Webdriver using BrowserStack remote URL
				// and desired capabilities defined above

				driver.set(new IOSDriver(new java.net.URL("http://hub.browserstack.com/wd/hub"), capabilities));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}
	}

}
