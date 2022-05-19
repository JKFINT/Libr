package Libraries;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.browserstack.local.Local;

import io.appium.java_client.android.AndroidDriver;



public class MobileAppAndroidBSDriver extends Driver {

	private static Local localInstance;

	public static void openBrowserStack(String OS, String username, String device) throws IOException {
		env.set("mobile");
		clog.info("Browser stack connection started");
		switch (OS) {
		case "Android":
			DesiredCapabilities caps = new DesiredCapabilities();
			// Set your access credentials
			try {
    		System.setProperty("proxyHost", "proxy.mobily.lan");
		        System.setProperty("proxyPort", "8080");
				// Set URL of the application under test
				caps.setCapability("app", ReadMobileproperties("Android", "app_Id"));

				caps.setCapability("browserstack.user", ReadMobileproperties("Android", "BS_"+username+"_Name"));

				caps.setCapability("browserstack.key", ReadMobileproperties("Android", "BS_"+username+"_Key"));

				caps.setCapability("device", ReadMobileproperties("Android", device));

				caps.setCapability("os_version", ReadMobileproperties("Android", device+"_Version"));


				caps.setCapability("browserstack.debug", "true");
				caps.setCapability("browserstack.video", "true");
				// Set other BrowserStack capabilities
				caps.setCapability("project", "MOBILY ANDROID");
				caps.setCapability("build", Release_name.get());
				caps.setCapability("name", Usecase.get());

				// Initialise the remote Webdriver using BrowserStack remote URL
				// and desired capabilities defined above

				driver.set(new AndroidDriver(new java.net.URL("http://hub.browserstack.com/wd/hub"), caps));
				clog.info("Browser stack connection done");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// clog.info(e.printStackTrace());
				clog.info(Result.getStackMsg(e));
			}
			break;

		case "Android Local":
			try {

				clog.info("BS connection start");
				setupLocal();
				System.setProperty("http.proxyHost", "proxy.mobily.lan");
				System.setProperty("http.proxyPort", "8080");
				DesiredCapabilities capabilities = new DesiredCapabilities();

				// Set URL of the application under test
				capabilities.setCapability("app", ReadMobileproperties("Android", "app_Id"));

				// Set your access credentials
				capabilities.setCapability("browserstack.user", ReadMobileproperties("Android", "BS_"+username+"_Name"));

				capabilities.setCapability("browserstack.key", ReadMobileproperties("Android", "BS_"+username+"_Key"));

				capabilities.setCapability("device", ReadMobileproperties("Android", device));

				capabilities.setCapability("os_version", ReadMobileproperties("Android", device+"_Version"));

				capabilities.setCapability("browserstack.debug", "true");
				// Set the browserstack.local capability to true
				capabilities.setCapability("browserstack.local", "true");

				// Set other BrowserStack capabilities
				capabilities.setCapability("project", "MOBILY");
				capabilities.setCapability("build", Release_name.get());
				capabilities.setCapability("name", Usecase.get());

				// Initialise the remote Webdriver using BrowserStack remote URL
				// and desired capabilities defined above

				driver.set(new AndroidDriver(new java.net.URL("http://hub.browserstack.com/wd/hub"), capabilities));
				clog.info("BS connection sucess");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				clog.info(Result.getStackMsg(e));
				e.printStackTrace();
			}
			break;

		}

	}


	 public static void setupLocal() throws Exception {
		 localInstance = new Local();
		    Map<String, String> options = new HashMap<String, String>();
		    options.put("key",  ReadMobileproperties("browserStack", "bs_access_key"));
		    options.put("proxyHost", "proxy.mobily.lan");
		    options.put("proxyPort", "8080");
		    options.put("forceproxy", "true");
		    //options.put("localProxyHost", "proxy.mobily.lan");
		    //options.put("localProxyPort", "8080");
		    //options.put("forceproxy", "true");

		  //  options.put("binarypath", "C:\\Users\\shr00088\\.browserstack\\BrowserStackLocal.exe");
		  //  options.put("local", "true");
		    options.put("forcelocal", "true");
		    localInstance.start(options);
		  }

		  public static void tearDownLocal() throws Exception {
			  driver.get().quit();
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


}
