package Libraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Mobile extends Driver {
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: Mobile 
	 * Use 					: Contains all the common functions used for mobile automation  
	 * Designed By			: Mugaz
	 * Last Modified Date 	: 15-Dec-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void Openapp(String App, String Mobile, String OS) throws IOException {
		env.set("mobile");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("pCloudy_Username", ReadMobileproperties(Mobile, "pCloudy_Username"));
		capabilities.setCapability("pCloudy_ApiKey", ReadMobileproperties(Mobile, "pCloudy_ApiKey"));
		capabilities.setCapability("pCloudy_DurationInMinutes",
				ReadMobileproperties(Mobile, "pCloudy_DurationInMixnutes"));
		capabilities.setCapability("newCommandTimeout", ReadMobileproperties(Mobile, "newCommandTimeout"));
		capabilities.setCapability("launchTimeout", ReadMobileproperties(Mobile, "launchTimeout"));
		capabilities.setCapability("pCloudy_DeviceFullName", ReadMobileproperties(Mobile, "pCloudy_DeviceFullName"));
		capabilities.setCapability("platformVersion", ReadMobileproperties(Mobile, "platformVersion"));
		capabilities.setCapability("platformName", ReadMobileproperties(Mobile, "platformName"));
		capabilities.setCapability("automationName", ReadMobileproperties(Mobile, "automationName"));
		capabilities.setCapability("pCloudy_WildNet", ReadMobileproperties(Mobile, "pCloudy_WildNet"));
		capabilities.setCapability("pCloudy_EnableVideo", ReadMobileproperties(Mobile, "pCloudy_WildNet"));
		capabilities.setCapability("pCloudy_EnablePerformanceData", ReadMobileproperties(Mobile, "pCloudy_WildNet"));
		capabilities.setCapability("pCloudy_EnableDeviceLogs", ReadMobileproperties(Mobile, "pCloudy_WildNet"));

		if (OS.equalsIgnoreCase("iOS")) {
			capabilities.setCapability("pCloudy_ApplicationName", ReadAppproperties(App, "iOS_ipa"));
			capabilities.setCapability("acceptAlerts", ReadMobileproperties(Mobile, "acceptAlerts"));
			capabilities.setCapability("bundleId", ReadAppproperties(App, "bundleId"));
			driver.set(new IOSDriver(new java.net.URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities));
		} else {
			capabilities.setCapability("pCloudy_ApplicationName", ReadAppproperties(App, "Android_apk"));
			capabilities.setCapability("appPackage", ReadAppproperties(App, "appPackage"));
			capabilities.setCapability("appActivity", ReadAppproperties(App, "appActivity"));
			driver.set(
					new AndroidDriver(new java.net.URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities));
		}
	}

	public static String ReadMobileproperties(String fname, String propname) throws IOException {
		String fpath = WorkingDir.get() + "/Drivers/Devices/" + fname + ".properties";
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);

		prop.load(input);
		// prop.getProperty(propname);

		return prop.getProperty(propname);
	}

	public static String ReadAppproperties(String app, String propname) throws IOException {
		String fpath = WorkingDir.get() + "/Drivers/AppConfig/" + app + ".properties";
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);

		prop.load(input);
		// prop.getProperty(propname);

		return prop.getProperty(propname);
	}
}
