package Libraries;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.CharMatcher;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Browser extends Driver {

	private Wait<WebDriver> wait = new WebDriverWait((WebDriver) driver, waitTimeOut);

	public static void killDriver() {
		try {
			driver.get().quit();
		} catch (Exception e) {

		}
	}

	public static void waitTillSiebelLoad() {
		try {
			if (Continue.get() == true) {
				clog.info("inside overlay Load");
				Thread.sleep(5000);
				boolean isOverLayDisplayed = driver.get().findElement(By.id("maskoverlay")).isDisplayed();
				if (isOverLayDisplayed) {
					Thread.sleep(10000);
					waitTillSiebelLoad();

				} else {
					Thread.sleep(5000);
				}
			}
		} catch (Exception e) {

		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebEdit
	 * Use 					: Subclass of browser class represents the WebEdit in the application and
	 * 						  contains functions for all the operations performed on web edit
	 * Designed By			: AG
	 * Last Modified Date 	: 16-April-2017
	--------------------------------------------------------------------------------------------------------*/

	public static class WebEdit {
		public static void SetE(String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			Method.clearTD(objprop);
			Thread.sleep(200);
			Method.setETD(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				throw new Exception();
			} else {
				clog.info(" :: Action SetText on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static void SetE(boolean screenshot, String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			Method.clearTD(objprop);
			Thread.sleep(200);
			Method.setETD(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action SetText on Obj: " + objname + " - Value: " + objvalue);
				}
				clog.info(" :: Action SetText on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static void SetD(String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Thread.sleep(200);
			Method.setTD(objprop, objvalue);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				throw new Exception();
			} else {
				clog.info(" :: Action SetText on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static void Set(String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			// clog.info("obj prop is "+objprop.toString());
			// clog.info("obj name is "+objname);
			// clog.info("obj value is "+objvalue);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
//			Method.waitForPageLoad();
			Method.clickEnable(objprop);
//			Method.waitUntilClickable(objprop);
			Method.clickTD(objprop);
			Method.clearTD(objprop);
			Thread.sleep(200);
			Method.setTD(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				throw new Exception();
			} else {
				clog.info(" :: Action SetText on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static void Set(boolean screenshot, String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			Method.clearTD(objprop);
			Thread.sleep(200);
			Method.setTD(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to set Value: " + objvalue);
				throw new Exception();
			} else {

				if (screenshot) {
					Result.takescreenshot(" :: Action SetText on Obj: " + objname + " - Value: " + objvalue);
				}
				clog.info(" :: Action SetText on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static String gettext(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);
			// waitforload();
			//
			return Method.getText(objprop);
		}

		public static String getTextValue(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);
			// waitforload();
			//
			return Method.getval(objprop);
		}

		public static String gettextJ(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			String obj = objprop[0];
			// Method.waittillobjvisible(objprop);
			// waitforload();
			//
			return Method.readValue(obj);
		}

		public static boolean isAvailable(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.checkDisplayed(objprop);
		}

		public static void click(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clearTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
				clog.info(" :: Failed at Obj: " + objname + " to Click");
				throw new Exception();
			} else {
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void doubleClick(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickDouble(objprop);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
				clog.info(" :: Failed at Obj: " + objname + " to Click");
				throw new Exception();
			} else {
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void click(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clearTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
				clog.info(" :: Failed at Obj: " + objname + " to Click");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action Click on Obj: " + objname);
				}
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void clear(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.clearTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Clear");
				clog.info(" :: Failed at Obj: " + objname + " to Clear");
				throw new Exception();
			} else {
				clog.info(" :: Action Clear on Obj: " + objname);
			}
		}

		public static void clear(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.clearTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Clear");
				clog.info(" :: Failed at Obj: " + objname + " to Clear");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action Clear on Obj: " + objname);
				}
				clog.info(" :: Action Clear on Obj: " + objname);
			}
		}

		public static void Doubleclick(String objname) {
			try {
				Method.screenLive();
				String[] objprop = Utlities.FindObject(objname, "WebEdit");
				String Xpath = objprop[0];
				org.openqa.selenium.WebElement element = driver.get().findElement(By.xpath(Xpath));
				((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView();", element);
				Actions action = new Actions(driver.get()).doubleClick(element);
				action.build().perform();
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
					clog.info(" :: Failed at Obj: " + objname + " to Double Click");
				}

			}
		}

		public static boolean exist(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.existobj(objprop);
		}

		public static void waittillvisible(String objname) throws Exception {
			// waitTillSiebelLoad();
			// waitforload();
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

		public static void waittillvisible(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action at Obj: " + objname + " - is Visible");
				}
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

		public static boolean waitTillEnabled(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.Methodwaittillenabled(objprop);

		}

		public static Boolean CheckDisabled(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			String cellXpath = objprop[0];
			org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpath));
			((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
			if (driver.get().findElement(By.xpath(cellXpath)).isDisplayed()
					& ((driver.get().findElement(By.xpath(cellXpath)).getAttribute("readonly")).equals("readonly"))) {
				clog.info(" :: Action at Obj: " + objname + " - is Disabled");
				return true;
			} else {
				clog.info(" :: Failed at Obj: " + objname + " - is not Disabled");
				return false;
			}
		}

		public static void taf_sendkeys(String objname, String value) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);
			waitforload();
			Method.taf_edit(objprop, value);
		}

		public static void taf_signature(String objname, int x, int y, int x1, int y1, int x2, int y2, int x3, int y3,
				int x4, int y4, int x5, int y5, int x6, int y6, int x7, int y7, int x8, int y8) throws Exception {
			Method.screenLive();
			String objtype = "WebEdit";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);
			waitforload();
			Method.taf_sign(objprop, x, y, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebButton
	 * Use 					: Subclass of browser class represents the WebButton in the application and
	 * 						  contains functions for all the operations performed on web edit
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebButton {
		public static void click(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action Click on Obj: " + objname);
				}
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void click(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			// Method.waitForPageLoad();
			waitforload();
			//
			// Method.waitUntilClickable(objprop);
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static boolean isEnabled(String objname) throws Exception {
			Method.screenLive();
			boolean obj = false;
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			obj = Method.checkEnable(objprop);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " disabled");
				clog.info(" :: Failed at Obj: " + objname + " disabled");
				throw new Exception();
			} else {

				clog.info(" :: Enabled: " + objname);
			}
			return obj;
		}

		public static void clickJ(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			// Method.waitForPageLoad();
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			// Method.waitUntilClickable(objprop);
			Method.clicktWithJavaScript(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void clickOf(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickOffset(objprop);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void waittillvisible(String objname) throws Exception {
			// waitTillSiebelLoad();
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

		public static void taf_Click(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);
			waitforload();
			Method.taf_click(objprop);
		}

		public static void waittillvisible(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action at Obj: " + objname + " - is Visible");
				}
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

		public static boolean exist(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.existobj(objprop);
		}

		public static boolean waitTillEnabled(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.Methodwaittillenabled(objprop);
		}

		public static Boolean isAvailable(String objname, String value, int time) throws Exception {
			Boolean isAvailable = false;
			Method.screenLive();
			String objtype = "WebButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			if (Method.checkAvailable(objprop, value, time)) {
				isAvailable = true;
			} else {
				isAvailable = false;
			}
			return isAvailable;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebLink
	 * Use 					: Subclass of browser class represents the WebLink in the application and
	 * 						  contains functions for all the operations performed on web edit
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebLink {
		public static void click(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static boolean isEnabled(String objname) throws Exception {
			Method.screenLive();
			boolean obj = false;
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			obj = Method.checkEnable(objprop);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " disabled");
				clog.info(" :: Failed at Obj: " + objname + " disabled");
				throw new Exception();
			} else {

				clog.info(" :: Enabled: " + objname);
			}
			return obj;
		}

		public static void clickJ(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			// Method.waitForPageLoad();
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			// Method.clickEnable(objprop);
			// Method.waitUntilClickable(objprop);
			Method.clicktWithJavaScript(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void clickOf(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickOffset(objprop);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void click(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action Click on Obj: " + objname);
				}
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void clickL(String objname, int R) throws Exception {
			Method.screenLive();
			waitforload();
			String[] objprop = Utlities.FindObject(objname, "WebLink");
			String cellXpath = objprop[0] + "//div[" + R + "]//div[1]/a";
			driver.get().findElement(By.xpath(cellXpath)).click();
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to clickL");
				clog.info(" :: Failed at Obj: " + objname + " to clickL");
				throw new Exception();
			} else {
				clog.info(" :: Action ClickL on Obj: " + objname);
			}
		}

		public static void clickL(boolean screenshot, String objname, int R) throws Exception {
			Method.screenLive();
			waitforload();
			String[] objprop = Utlities.FindObject(objname, "WebLink");
			String cellXpath = objprop[0] + "//div[" + R + "]//div[1]/a";
			driver.get().findElement(By.xpath(cellXpath)).click();
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to clickL");
				clog.info(" :: Failed at Obj: " + objname + " to clickL");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action ClickL on Obj: " + objname);
				}
				clog.info(" :: Action ClickL on Obj: " + objname);
			}
		}

		public static void taf_Click(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);
			waitforload();
			Method.taf_click(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void taf_sendkeys(String objname, String value) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);
			waitforload();
			Method.taf_edit(objprop, value);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to sendkeys");
				clog.info(" :: Failed at Obj: " + objname + " to sendkeys");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void taf_signature(String objname, int x, int y, int x1, int y1, int x2, int y2, int x3, int y3,
				int x4, int y4, int x5, int y5, int x6, int y6, int x7, int y7, int x8, int y8) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);
			waitforload();
			Method.taf_sign(objprop, x, y, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Sign");
				clog.info(" :: Failed at Obj: " + objname + " to Sign");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static boolean exist(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.existobj(objprop);
		}

		public static void waittillvisible(String objname) throws Exception {
			// waitTillSiebelLoad();
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

		public static void waittillvisible(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action at Obj: " + objname + " - is Visible");
				}
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

		public static boolean waitTillEnabled(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebLink";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.Methodwaittillenabled(objprop);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: ListBox
	 * Use 					: Subclass of browser class represents the ListBox in the application and
	 * 						  contains functions for all the operations performed on web edit
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class ListBox {
		public static void setdropvalue(String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.setdropvalue(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set drop Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to set drop Value: " + objvalue);
				throw new Exception();
			} else {
				clog.info(" :: Action SetDropValue on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static void selectOption(String option) throws Exception {

			try {
				Method.screenLive();
				String object = "//a[text()='" + option + "']";
				org.openqa.selenium.WebElement findElement = driver.get().findElement(By.xpath(object));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", findElement);
				js.executeScript("arguments[0].click();", findElement);

				clog.info(" :: Action Click on Obj: " + option);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + option + " to click");
				clog.info(" :: Failed at Obj: " + option + " to click");
				throw new Exception();
			}
		}

		public static void dropDown(String objname, String value) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.dropDownSelect(objprop, value);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set drop Value: " + value);
				clog.info(" :: Failed at Obj: " + objname + " - to set drop Value: " + value);
				throw new Exception();
			} else {
				clog.info(" :: Action SetDropValue on Obj: " + objname + " - Value: " + value);
			}

		}

		public static void setdropvalue(boolean screenshot, String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.setdropvalue(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set drop Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to set drop Value: " + objvalue);
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action SetDropValue on Obj: " + objname + " - Value: " + objvalue);
				}
				clog.info(" :: Action SetDropValue on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static void select(String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Thread.sleep(100);
			waitforload();
			//
			Method.clearTD(objprop);
			Thread.sleep(100);
			Method.selectTD(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set drop Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to set drop Value: " + objvalue);
				throw new Exception();
			} else {
				clog.info(" :: Action select on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static void select(boolean screenshot, String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Thread.sleep(100);
			waitforload();
			//
			Method.clearTD(objprop);
			Thread.sleep(100);
			Method.selectTD(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to set drop Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to set drop Value: " + objvalue);
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action select on Obj: " + objname + " - Value: " + objvalue);
				}
				clog.info(" :: Action select on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static void click(String objname) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waitForElementAnd_Scroll(objprop);
			waitforload();
			//
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void click(String objname, String value) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			// Method.waitForElementAnd_Scroll(objprop,value);
			// waitforload();
			//
			// Method.clickEnable(objprop);
			Method.clickDynamic(objprop, value);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void click(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waitForElementAnd_Scroll(objprop);
			waitforload();
			//
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action Click on Obj: " + objname);
				}
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void clear(String objname) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.clearTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Clear");
				clog.info(" :: Failed at Obj: " + objname + " to Clear");
				throw new Exception();
			} else {
				clog.info(" :: Action Clear on Obj: " + objname);
			}
		}

		public static void clear(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.clearTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Clear");
				clog.info(" :: Failed at Obj: " + objname + " to Clear");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action Clear on Obj: " + objname);
				}
				clog.info(" :: Action Clear on Obj: " + objname);
			}
		}

		public static String gettext(String objname) throws Exception {
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			String retVal = Method.getval(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to return value");
				clog.info(" :: Failed at Obj: " + objname + " to return value");
				throw new Exception();
			} else {

				clog.info(" :: Action Clear on Obj: " + objname);
			}
			return retVal;
		}

		public static boolean exist(String objname) throws Exception {
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.existobj(objprop);
		}

		public static boolean waitTillEnabled(String objname) throws Exception {
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.Methodwaittillenabled(objprop);
		}

		public static void waittillvisible(String objname) throws Exception {
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

		public static void waittillvisible(boolean screenshot, String objname) throws Exception {
			String objtype = "ListBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action at Obj: " + objname + " - is Visible");
				}
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebElement
	 * Use 					: Subclass of browser class represents the WebElement in the application and
	 * 						  contains functions for all the operations performed on web edit
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebElement {
		public static void click(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebElement";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot("  :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void clickOf(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebElement";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickOffset(objprop);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void click(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebElement";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waitForElementAnd_Scroll(objprop);
			waitforload();
			//
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot("  :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot("  :: Action Click on Obj: " + objname);
				}
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void waittillvisible(String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebElement";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

		public static void waittillvisible(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "WebElement";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waittillobjvisible(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - is not Visible");
				clog.info(" :: Failed at Obj: " + objname + " - is not Visible");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action at Obj: " + objname + " - is Visible");
				}
				clog.info(" :: Action at Obj: " + objname + " - is Visible");
			}
		}

		public static boolean exist(String objname) throws Exception {
			waitforload();
			String objtype = "WebElement";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.existobj(objprop);
		}

		public static boolean waitTillEnabled(String objname) throws Exception {
			waitforload();
			String objtype = "WebElement";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.Methodwaittillenabled(objprop);
		}

		public static void select(String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "WebElement";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.selectTD(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to select Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to select Value: " + objvalue);
				throw new Exception();
			} else {
				clog.info(" :: Action select on Obj: " + objname + " - Value: " + objvalue);
			}
		}

		public static void select(boolean screenshot, String objname, String objvalue) throws Exception {
			Method.screenLive();
			String objtype = "WebElement";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.selectTD(objprop, objvalue);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " - to select Value: " + objvalue);
				clog.info(" :: Failed at Obj: " + objname + " - to select Value: " + objvalue);
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action select on Obj: " + objname + " - Value: " + objvalue);
				}
				clog.info(" :: Action select on Obj: " + objname + " - Value: " + objvalue);
			}
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebRadioButton
	 * Use 					: Subclass of browser class represents the WebRadioButton in the application and
	 * 						  contains functions for all the operations performed on web edit
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebRadioButton {
		public static void click(String objname) throws Exception {
			Method.screenLive();
			String objtype = "RadioButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void clickOf(String objname) throws Exception {
			Method.screenLive();
			String objtype = "RadioButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickOffset(objprop);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void click(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "RadioButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action Click on Obj: " + objname);
				}
				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static boolean exist(String objname) throws Exception {
			Method.screenLive();
			waitforload();
			String objtype = "RadioButton";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.existobj(objprop);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebCheckBox
	 * Use 					: Subclass of browser class represents the WebRadioButton in the application and
	 * 						  contains functions for all the operations performed on web edit
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebCheckBox {
		public static void click(String objname) throws Exception {
			Method.screenLive();
			String objtype = "CheckBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Check box");
				clog.info(" :: Failed at Obj: " + objname + " to Check box");
				throw new Exception();
			} else {
				clog.info(" :: Action CheckBox on Obj: " + objname);
			}
		}

		public static void clickOf(String objname) throws Exception {
			Method.screenLive();
			String objtype = "CheckBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickOffset(objprop);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void click(boolean screenshot, String objname) throws Exception {
			Method.screenLive();
			String objtype = "CheckBox";
			String[] objprop = Utlities.FindObject(objname, objtype);
			waitforload();
			//
			Method.waitForElementAnd_Scroll(objprop);
			Method.clickEnable(objprop);
			Method.clickTD(objprop);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Check box");
				clog.info(" :: Failed at Obj: " + objname + " to Check box");
				throw new Exception();
			} else {
				if (screenshot) {
					Result.takescreenshot(" :: Action CheckBox on Obj: " + objname);
				}
				clog.info(" :: Action CheckBox on Obj: " + objname);
			}
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebTable
	 * Use 					: Subclass of browser class represents the WebTabel in the application and
	 * 						  contains functions for all the operations performed on Web Table
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class WebTable {

		public static String getDataFromTable(String objname, String columnName, String rowName, String rowValue)
				throws Exception {
			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			String value = getCellData(objname, rowNumber, columnNumber);
			clog.info("received Value " + value);
			return value;
		}

		public static String getDataFromTableA(String objname, String columnName, String rowName, String rowValue)
				throws Exception {
			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			String value = getCellData(objname, rowNumber, columnNumber);
			clog.info("received Value " + value);
			return value;
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: clickData
		--------------------------------------------------------------------------------------------------------*/

		public static void clickDataFromTableA(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			click(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}

		public static void clickDataFromTable(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			click(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}

		public static void clickDdIconDataFromTableA(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			click_div(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}

		public static void clickDdIconDataFromTable(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			click_div(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}
		/*------------------------------------------------------------------------------------------------------
		* Function Name: clickData with Java script
		--------------------------------------------------------------------------------------------------------*/

		public static void clickJDataFromTableA(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			click_JS(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}

		public static void clickJDataFromTable(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			click_JS(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}
		/*------------------------------------------------------------------------------------------------------
		* Function Name: clickLinkData
		--------------------------------------------------------------------------------------------------------*/

		public static void clickLinkDataFromTableA(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			clickLink_JS(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}

		public static void clickLinkDataFromTable(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			clickLink_JS(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}
		/*------------------------------------------------------------------------------------------------------
		* Function Name: clickLinkData
		--------------------------------------------------------------------------------------------------------*/

		public static void clickOfDataFromTableA(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			clickOf(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}

		public static void clickOfTDataFromTableA(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			clickOfT(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}

		public static void clickOfTDataFromTable(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			clickOfT(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}

		public static void clickOfDataFromTable(String objname, String columnName, String rowName, String rowValue)
				throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			clickOf(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}
		/*------------------------------------------------------------------------------------------------------
		* Function Name: clickCheckBox
		--------------------------------------------------------------------------------------------------------*/

		public static void clickCheckBoxDataFromTableA(String objname, String columnName, String rowName,
				String rowValue) throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			click_che(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}

		public static void clickCheckBoxDataFromTable(String objname, String columnName, String rowName,
				String rowValue) throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			click_che(objname, rowNumber, columnNumber);
			clog.info("table data clicked successfully");

		}
		/*------------------------------------------------------------------------------------------------------
			* Function Name: enterDataToTable and click enter Key
			--------------------------------------------------------------------------------------------------------*/

		public static void enterDataToTableA(String objname, String columnName, String rowName, String rowValue,
				String area, String value) throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			SetData(objname, rowNumber, columnNumber, area, value);
			clog.info(value + " Entered successfully");

		}

		public static void enterDataToTable(String objname, String columnName, String rowName, String rowValue,
				String area, String value) throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			SetData(objname, rowNumber, columnNumber, area, value);
			clog.info(value + " Entered successfully");

		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: enterDataToTable
		--------------------------------------------------------------------------------------------------------*/

		public static void writeDataToTableA(String objname, String columnName, String rowName, String rowValue,
				String area, String value) throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumberContains(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumberContains(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			SetDataE(objname, rowNumber, columnNumber, area, value);
			clog.info(value + " Entered successfully");

		}

		public static void writeDataToTable(String objname, String columnName, String rowName, String rowValue,
				String area, String value) throws Exception {

			waitForElement(objname, "WebTable");
			int columnNumber = getColumnNumber(objname, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = getRowNumber(objname, rowName, rowValue);
			clog.info("rowNumber " + rowNumber);
			SetDataE(objname, rowNumber, columnNumber, area, value);
			clog.info(value + " Entered successfully");

		}

		public static void clickOf(String objname, int rownum, int columnnum) throws Exception {
			Method.screenLive();
			waitforload();
			String[] objprop = Utlities.FindObject(objname, "WebTable");
			String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]/a";
			waitforload();
			Method.waitForElementAnd_Scroll(cellXpath);
			Method.clickOffset(cellXpath);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		public static void clickOfT(String objname, int rownum, int columnnum) throws Exception {
			Method.screenLive();
			waitforload();
			String[] objprop = Utlities.FindObject(objname, "WebTable");
			String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]";
			waitforload();
			Method.waitForElementAnd_Scroll(cellXpath);
			Method.clickOffset(cellXpath);
			if (Continue.get() == false) {
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			} else {

				clog.info(" :: Action Click on Obj: " + objname);
			}
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: Expand
		* Use :	To Expand the contents in specific Row
		* Designed By: Vinodhini
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static void Expand(String objname, int rownum) {
			try {
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String[] Expand = Utlities.FindObject("Expand", "WebButton");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]" + Expand[0];
				if (driver.get().findElement(By.xpath(cellXpath)).isDisplayed()) {
					driver.get().findElement(By.xpath(cellXpath)).click();
				}
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname);
				clog.info(" :: Failed at Obj: " + objname);
			}
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: scroll
		* Use :	Scrolls the particular cell in the given row and column of the webtable
		* Designed By: Vinodhini
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static void scroll(String objname, int rownum, int columnnum) throws Exception {
			String[] objprop = Utlities.FindObject(objname, "WebTable");
			String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + (columnnum + 1) + "]";
			driver.get().findElement(By.xpath(cellXpath)).click();
			WebElement scr1 = (WebElement) driver.get().findElement(By.xpath(cellXpath));
			((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: getRowCount
		* Use :	returns the total number of rows in the webtable
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static int getRowCount(String objname) throws Exception {
			try {
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr";
				List<org.openqa.selenium.WebElement> rows = driver.get().findElements(By.xpath(cellXpath));
				int rowcount = rows.size();
				clog.info(" :: Action getRowCount on Obj: " + objname);
				return rowcount;
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to getRowCount");
				clog.info(" :: Failed at Obj: " + objname + " to getRowCount");
				throw new Exception();
			}

		}

		public static int getColumnNumberContains(String objName, String columnName) {
			int colNo = 0;
			try {
				String[] objprop = Utlities.FindObject(objName, "WebTable");

				String headTableXpath = objprop[0]
						+ "/ancestor::div[@class='ui-jqgrid-bdiv']/preceding-sibling::div[@class='ui-state-default ui-jqgrid-hdiv']//table";
				int colCount = Browser.WebTable.getColCount(objName);
				// clog.info(String.valueOf(colCount));
				for (int columnnum = 1; columnnum <= colCount; columnnum++) {

					String celValObj = headTableXpath + "//tr[1]" + "//th[" + columnnum + "]";
					org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(celValObj));
					((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
					// ((RemoteWebDriver)
					// cDriver.get()).executeScript("arguments[0].scrollIntoView(true)",
					// driver.get().findElement(By.xpath(celValObj));
					String celVal = driver.get().findElement(By.xpath(celValObj)).getText();
					clog.info(celVal);
					// clog.info(celVal);
					if (celVal.trim().toLowerCase().contains(columnName.toLowerCase().trim())) {
						colNo = columnnum;
						break;
					}
				}
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				clog.info(Result.getStackMsg(e));
			}
			return colNo;
		}

		public static int getColumnNumber(String objName, String columnName) {
			int colNo = 0;
			try {
				String[] objprop = Utlities.FindObject(objName, "WebTable");

				String headTableXpath = objprop[0]
						+ "/ancestor::div[@class='ui-jqgrid-bdiv']/preceding-sibling::div[@class='ui-state-default ui-jqgrid-hdiv']//table";
				int colCount = Browser.WebTable.getColCount(objName);
				// clog.info(String.valueOf(colCount));
				for (int columnnum = 1; columnnum <= colCount; columnnum++) {
					String celValObj = headTableXpath + "//tr[1]" + "//th[" + columnnum + "]";
					org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(celValObj));
					((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
					// ((RemoteWebDriver)
					// cDriver.get()).executeScript("arguments[0].scrollIntoView(true)",
					// driver.get().findElement(By.xpath(celValObj));
					String celVal = driver.get().findElement(By.xpath(celValObj)).getText();
					clog.info(celVal);
					// clog.info(celVal);
					if (celVal.trim().equalsIgnoreCase(columnName.trim())) {
						colNo = columnnum;
						break;
					}
				}
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				clog.info(Result.getStackMsg(e));
			}
			return colNo;
		}

		public static int getRowNumberContains(String objName, String rowReference, String colName) {
			int rowNo = 0;
			try {
				String[] objprop = Utlities.FindObject(objName, "WebTable");
				int colNumber = getColumnNumber(objName, colName);
				int rowCount = getRowCount(objName);
				// clog.info("####"+String.valueOf(rowCount));
				// clog.info("###"+String.valueOf(colNumber));
				for (int crrow = 1; crrow <= rowCount; crrow++) {
					// clog.info("###"+String.valueOf(crrow));
					String ceValobj = objprop[0] + "//tr[" + crrow + "]//td[" + colNumber + "]";
					String celVal = driver.get().findElement(By.xpath(ceValobj)).getText();
					clog.info(String.valueOf(celVal));
					if (celVal.trim().toLowerCase().contains(rowReference.toLowerCase().trim())) {
						rowNo = crrow;
						break;
					}
				}
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				clog.info(Result.getStackMsg(e));
			}
			return rowNo;
		}

		public static int getRowNumber(String objName, String rowReference, String colName) {
			int rowNo = 0;
			try {
				String[] objprop = Utlities.FindObject(objName, "WebTable");
				int colNumber = getColumnNumber(objName, colName);
				int rowCount = getRowCount(objName);
				// clog.info("####"+String.valueOf(rowCount));
				// clog.info("###"+String.valueOf(colNumber));
				for (int crrow = 1; crrow <= rowCount; crrow++) {
					// clog.info("###"+String.valueOf(crrow));
					String ceValobj = objprop[0] + "//tr[" + crrow + "]//td[" + colNumber + "]";
					String celVal = driver.get().findElement(By.xpath(ceValobj)).getText();
					clog.info(String.valueOf(celVal));
					if (celVal.trim().equalsIgnoreCase(rowReference.trim())) {
						rowNo = crrow;
						break;
					}
				}
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				clog.info(Result.getStackMsg(e));
			}
			return rowNo;
		}

		public static boolean exist(String objname) throws Exception {
			String objtype = "WebTable";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.existobj(objprop);
		}

		public static boolean waitTillEnabled(String objname) throws Exception {
			String objtype = "WebTable";
			String[] objprop = Utlities.FindObject(objname, objtype);
			return Method.Methodwaittillenabled(objprop);
		}

		public static String DynamicGetText(String objname, String obj) throws Exception {
			Method.screenLive();
			String textValue = null;
			String objtype = "WebTable";
			String[] objprop = Utlities.FindObject(objname, objtype);
			// Method.waittillobjvisible(objprop);
			waitforload();
			textValue = Method.getDynamicText(objprop, obj);
			if (Continue.get() == false) {
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
				clog.info(" :: Failed at Obj: " + objname + " to Click");
				throw new Exception();
			} else {
				clog.info(" :: Action Click on Obj: " + objname);
			}
			return textValue;
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: getCellData
		* Use :	returns the value in the given row and column of the web table
		* Designed By: AG
		* Modified By: Vinodhini
		* Last Modified Date : 13-Feb-2017
		--------------------------------------------------------------------------------------------------------*/
		public static String getCellData(String objname, int rownum, int columnnum) throws Exception {
			try {
				String[] objprop = Utlities.FindObject(objname, "WebTable");

				String cellXpath = objprop[0] + "//tr[" + rownum + "]" + "//td[" + columnnum + "]";
				Thread.sleep(100);
				String celldata = driver.get().findElement(By.xpath(cellXpath)).getText();
				clog.info(" :: Action getCellData on Obj: " + objname);
				return celldata;
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to getCellData");
				clog.info(" :: Failed at Obj: " + objname + " to getCellData");
				throw new Exception();
			}

		}

		public static String getCellData_title(String objname, int rownum, int columnnum) throws Exception {
			try {
				String[] objprop = Utlities.FindObject(objname, "WebTable");

				String cellXpath = objprop[0] + "//tr[" + rownum + "]" + "//td[" + columnnum + "]";
				String celldata = driver.get().findElement(By.xpath(cellXpath)).getAttribute("title");
				clog.info(" :: Action getCellData_title on Obj: " + objname);
				return celldata;
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to getCellData_title");
				clog.info(" :: Failed at Obj: " + objname + " to getCellData_title");
				throw new Exception();
			}

		}

		public static String CellData(String objname, int rownum) throws Exception {
			try {
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String celldata = "";
				int a = Browser.WebTable.getColCount(objname);
				System.out.println(a);
				for (int columnnum = 1; columnnum >= a; columnnum++) {
					String cellXpath = objprop[0] + "//tr[" + rownum + "]" + "//td[" + columnnum + "]";
					celldata = driver.get().findElement(By.xpath(cellXpath)).getText();
					System.out.println(celldata);
				}
				clog.info(" :: Action CellData on Obj: " + objname);
				return celldata;
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to CellData");
				clog.info(" :: Failed at Obj: " + objname + " to CellData");
				throw new Exception();
			}

		}

		public static boolean DataSearch(String objname, String Data) throws Exception {
			boolean status = true;
			String[] objprop = Utlities.FindObject(objname, "WebTable");
			String cellXpath = objprop[0] + "/tr/td";
			System.out.println(cellXpath);
			List<org.openqa.selenium.WebElement> celldata = driver.get().findElements(By.xpath(cellXpath));
			for (org.openqa.selenium.WebElement data : celldata) {
				System.out.println(data.getText());
				if (data.getText().toString().equalsIgnoreCase(Data)) {
					status = true;
					System.out.println("True");
				} else {
					status = false;
				}
			}
			return status;

		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: CommentE
		* Use : Sets the Specified value to the Comment cell
		* Designed By: Vinodhini
		* Last Modified Date : 13-January-2017
		--------------------------------------------------------------------------------------------------------*/
		public static void CommentE(String objname, int rownum, int columnnum, String obj, String Val)
				throws Exception {
			try {
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpath)).click();
				String cellXpath1 = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//textarea[@name='" + obj
						+ "']";
				Thread.sleep(200);
				driver.get().findElement(By.xpath(cellXpath1)).clear();
				Thread.sleep(200);
				String vis = "false";
				int countval = 1;
				while (vis == "false" || countval < 10000)
					if (driver.get().findElement(By.xpath(cellXpath1)).isDisplayed()) {
						driver.get().findElement(By.xpath(cellXpath1)).sendKeys(Val);
						vis = "true";
						countval = 10000;
					} else {
						countval++;
						Thread.sleep(10);
					}
				clog.info(" :: Action SetDataE on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to SetDataE");
				clog.info(" :: Failed at Obj: " + objname + " to SetDataE");
				throw new Exception();
			}

		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: click
		* Use :	Clicks the given row and column of the webtable
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static void click(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
		}

		public static void click_JS(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]";
				org.openqa.selenium.WebElement findElement = driver.get().findElement(By.xpath(cellXpath));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", findElement);
				js.executeScript("arguments[0].click();", findElement);

				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
		}

		public static void clickLink_JS(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]/a";
				org.openqa.selenium.WebElement findElement = driver.get().findElement(By.xpath(cellXpath));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", findElement);
				js.executeScript("arguments[0].click();", findElement);

				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
		}

		public static void click(boolean screenshot, String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
			if (screenshot) {
				Result.takescreenshot(" :: Action Click on Obj: " + objname);
			}
		}

		public static void click_che(String objname, int rownum, int columnnum) throws Exception {
			try {
				// Method.screenLive();
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]/span/input";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
		}

		public static void click_che(boolean screenshot, String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]/span/input";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
			if (screenshot) {
				Result.takescreenshot(" :: Action Click on Obj: " + objname);
			}
		}

		public static void clickA(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//a";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
		}

		public static void click_div(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]/div";
				Method.clickOffset(cellXpath);
				// driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
		}

		public static void click_Div_JS(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]/div";
				org.openqa.selenium.WebElement findElement = driver.get().findElement(By.xpath(cellXpath));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", findElement);
				js.executeScript("arguments[0].click();", findElement);

				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
		}

		public static void clickA(boolean screenshot, String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//a";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
				clog.info(" :: Failed at Obj: " + objname + " to click");
				throw new Exception();
			}
			if (screenshot) {
				Result.takescreenshot(" :: Action Click on Obj: " + objname);
			}
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: clickL
		* Use :	Clicks the given row and column of the webtable
		* Designed By: Vinodhini
		* Last Modified Date : 07-March-2017
		--------------------------------------------------------------------------------------------------------*/
		public static void clickL(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + (columnnum + 1) + "]";
				driver.get().findElement(By.xpath(cellXpath)).click();
				String cellXpath1 = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//a";
				driver.get().findElement(By.xpath(cellXpath1)).click();
				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to clickL");
				clog.info(" :: Failed at Obj: " + objname + " to clickL");
				throw new Exception();
			}
		}

		public static void clickL(boolean screenshot, String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + (columnnum + 1) + "]";
				driver.get().findElement(By.xpath(cellXpath)).click();
				String cellXpath1 = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//a";
				driver.get().findElement(By.xpath(cellXpath1)).click();
				clog.info(" :: Action Click on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to clickL");
				clog.info(" :: Failed at Obj: " + objname + " to clickL");
				throw new Exception();
			}
			if (screenshot) {
				Result.takescreenshot(" :: Action Click on Obj: " + objname);
			}
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: getColCount
		* Use : get the column count of the given web table
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static int getColCount(String objname) throws Exception {
			int colcount = 0;
			try {

				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[1]//td";
				List<org.openqa.selenium.WebElement> cols = driver.get().findElements(By.xpath(cellXpath));
				colcount = cols.size();
				clog.info(" :: Action getColCount on Obj: " + objname);

			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				clog.info(Result.getStackMsg(e));
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to getColCount");
				clog.info(" :: Failed at Obj: " + objname + " to getColCount");
				// throw new Exception();
			}
			return colcount;

		}

		public static int getColCount1(String objname) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[1]//th";
				List<org.openqa.selenium.WebElement> cols = driver.get().findElements(By.xpath(cellXpath));
				int colcount = cols.size();
				clog.info(" :: Action getColCount on Obj: " + objname);
				return colcount;
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to getColCount");
				clog.info(" :: Failed at Obj: " + objname + " to getColCount");
				throw new Exception();
			}
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: getColumnname
		* Use : get the column Name for the row and column
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static String getColumnname(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//th[" + columnnum + "]";
				String celldata = driver.get().findElement(By.xpath(cellXpath)).getText();
				clog.info(" :: Action getColumnname on Obj: " + objname);
				return celldata;
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to getColumnname");
				clog.info(" :: Failed at Obj: " + objname + " to getColumnname");
				throw new Exception();
			}
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: waittillvisible
		* Use : Waits till the web table is visible
		* Designed By: AG
		* Last Modified Date : 15-June-2016
		--------------------------------------------------------------------------------------------------------*/
		public static void waittillvisible(String objname) throws Exception {
			// waitTillSiebelLoad();
			String objtype = "WebTable";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);
		}

		public static void SetTableData(String objname) throws Exception {
			String objtype = "WebTable";
			String[] objprop = Utlities.FindObject(objname, objtype);
			Method.waittillobjvisible(objprop);

		}

		public static void clickT(String objname) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				driver.get().findElement(By.xpath(objprop[0])).click();
				// driver.get().findElement(By.xpath(objprop[0])).click();
				clog.info(" :: Action clickT on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to clickT");
				clog.info(" :: Failed at Obj: " + objname + " to clickT");
				throw new Exception();
			}
		}

		public static void clickT(boolean screenshot, String objname) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				driver.get().findElement(By.xpath(objprop[0])).click();
				// driver.get().findElement(By.xpath(objprop[0])).click();
				clog.info(" :: Action clickT on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to clickT");
				clog.info(" :: Failed at Obj: " + objname + " to clickT");
				throw new Exception();
			}
			if (screenshot) {
				Result.takescreenshot(" :: Action clickT on Obj: " + objname);
			}
		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: SetDataE
		* Use : Sets the Specified value to the cell
		* Designed By: Vinodhini
		* Last Modified Date : 13-January-2017
		--------------------------------------------------------------------------------------------------------*/
		public static void SetDataE(String objname, int rownum, int columnnum, String obj, String Val)
				throws Exception {
			try {
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpath)).click();
				String cellXpath1 = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//input[@name='" + obj
						+ "']";
				Thread.sleep(200);
				driver.get().findElement(By.xpath(cellXpath1)).clear();
				Thread.sleep(200);
				String vis = "false";
				int countval = 1;
				while (vis == "false" || countval < 10000)
					if (driver.get().findElement(By.xpath(cellXpath1)).isDisplayed()) {
						Method.clickTable(cellXpath1);
						driver.get().findElement(By.xpath(cellXpath1)).sendKeys(Val);
						vis = "true";
						countval = 10000;
					} else {
						countval++;
						Thread.sleep(10);
					}
				clog.info(" :: Action SetDataE on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to SetDataE");
				clog.info(" :: Failed at Obj: " + objname + " to SetDataE");
				throw new Exception();
			}

		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: SetData
		* Use : Sets the Specified value to the cell
		* Designed By: Vinodhini
		* Last Modified Date : 13-January-2017
		--------------------------------------------------------------------------------------------------------*/
		public static void SetData(String objname, int rownum, int columnnum, String obj, String Val) throws Exception {
			try {
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpath)).click();
				String cellXpath1 = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//input[@name='" + obj
						+ "']";
				Thread.sleep(200);
				driver.get().findElement(By.xpath(cellXpath1)).clear();
				Thread.sleep(200);
				String vis = "false";
				int countval = 1;
				while (vis == "false" || countval < 10000)
					if (driver.get().findElement(By.xpath(cellXpath1)).isDisplayed()) {
						Thread.sleep(100);
						driver.get().findElement(By.xpath(cellXpath1)).sendKeys(Val);
						Thread.sleep(100);
						driver.get().findElement(By.xpath(cellXpath1)).sendKeys(Keys.ENTER);
						vis = "true";
						countval = 10000;
					} else {
						countval++;
						Thread.sleep(10);
					}
				clog.info(" :: Action SetData on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to SetData");
				clog.info(" :: Failed at Obj: " + objname + " to SetData");
				throw new Exception();
			}

		}

		/*------------------------------------------------------------------------------------------------------
		* Function Name: Check
		* Use : Sets the Specified value to the cell
		* Designed By: Vinodhini
		* Last Modified Date : 7-March-2017
		--------------------------------------------------------------------------------------------------------*/
		public static void Check(String objname, int rownum, int columnnum, String val) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpath)).click();
				String cellXpath1 = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//option[@value='" + val
						+ "']";
				driver.get().findElement(By.xpath(cellXpath1)).click();
				clog.info(" :: Action Check on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Check");
				clog.info(" :: Failed at Obj: " + objname + " to Check");
				throw new Exception();
			}

		}

		public static void Check(boolean screenshot, String objname, int rownum, int columnnum, String val)
				throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpath)).click();
				String cellXpath1 = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//option[@value='" + val
						+ "']";
				driver.get().findElement(By.xpath(cellXpath1)).click();
				clog.info(" :: Action Check on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Check");
				clog.info(" :: Failed at Obj: " + objname + " to Check");
				throw new Exception();
			}
			if (screenshot) {
				Result.takescreenshot(" :: Action Check on Obj: " + objname);
			}

		}

		public static void Link(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]/a";
				driver.get().findElement(By.xpath(cellXpath)).click();

				// driver.get().findElement(By.xpath(cellXpath)).
				clog.info(" :: Action Link on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Link");
				clog.info(" :: Failed at Obj: " + objname + " to Link");
				throw new Exception();
			}
		}

		public static void Link(boolean screenshot, String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]/a";
				driver.get().findElement(By.xpath(cellXpath)).click();

				// driver.get().findElement(By.xpath(cellXpath)).
				clog.info(" :: Action Link on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Link");
				clog.info(" :: Failed at Obj: " + objname + " to Link");
				throw new Exception();
			}
			if (screenshot) {
				Result.takescreenshot(" :: Action Link on Obj: " + objname);
			}
		}

		public static void Expand(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpathX = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpathX)).click();
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]/div/div";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Expand on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Expand");
				clog.info(" :: Failed at Obj: " + objname + " to Expand");
				throw new Exception();
			}

		}

		public static void Expand(boolean screenshot, String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpathX = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpathX)).click();
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]/div/div";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Expand on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Expand");
				clog.info(" :: Failed at Obj: " + objname + " to Expand");
				throw new Exception();
			}
			if (screenshot) {
				Result.takescreenshot(" :: Action Link on Obj: " + objname);
			}

		}

		public static void Popup(String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpathX = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpathX)).click();
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//span";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Popup on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Popup");
				clog.info(" :: Failed at Obj: " + objname + " to Popup");
				throw new Exception();
			}
		}

		public static void Popup(boolean screenshot, String objname, int rownum, int columnnum) throws Exception {
			try {
				waitforload();
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String cellXpathX = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
				driver.get().findElement(By.xpath(cellXpathX)).click();
				String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//span";
				driver.get().findElement(By.xpath(cellXpath)).click();
				clog.info(" :: Action Popup on Obj: " + objname);
			} catch (Exception e) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objname + " to Popup");
				clog.info(" :: Failed at Obj: " + objname + " to Popup");
				throw new Exception();
			}
			if (screenshot) {
				Result.takescreenshot(" :: Action Popup on Obj: " + objname);
			}
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: alert
	 * Use 					:  class represents the alert in the application and
	 * 						  contains functions for all the operations performed on alert
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static class alert {
//		public static void accept() {
//			String alertpresent = "false";
//			while (alertpresent == "false") {
//				try {
//					Alert simpleAlert = ((WebDriver) driver.get()).switchTo().alert();
//					simpleAlert.accept();
//					alertpresent = "true";
//					break;
//				} catch (Exception e) {
//					alertpresent = "false";
//				}
//
//			}
//		}

		public static void accept() {

			try {
				WebDriverWait wait = new WebDriverWait(driver.get(), 120);
				clog.info("waiting for alert");
				wait.until(ExpectedConditions.alertIsPresent());
				Alert simpleAlert = driver.get().switchTo().alert();
				simpleAlert.accept();

			} catch (Exception e) {
				clog.info("no alert present");
			}

		}

		public static void accept_(int time, String clogg) {

			try {
				WebDriverWait wait = new WebDriverWait(driver.get(), time);
				clog.info(clogg);
				wait.until(ExpectedConditions.alertIsPresent());
				Alert simpleAlert = driver.get().switchTo().alert();
				simpleAlert.accept();

			} catch (Exception e) {
				clog.info("no alert present");
			}

		}

//		public static void accept_withtext() {
//			String alertpresent = "false";
//			while (alertpresent == "false") {
//				try {
//					Alert simpleAlert = ((WebDriver) driver.get()).switchTo().alert();
//					String text = simpleAlert.getText();
//					clog.info(text);
//					simpleAlert.accept();
//					alertpresent = "true";
//					break;
//				} catch (Exception e) {
//					alertpresent = "false";
//				}
//
//			}
//		}

		public static void accept_withtext() {
			try {
				WebDriverWait wait = new WebDriverWait(driver.get(), 120);
				clog.info("waiting for alert");
				wait.until(ExpectedConditions.alertIsPresent());
				Alert simpleAlert = driver.get().switchTo().alert();
				String text = simpleAlert.getText();
				clog.info("message in alert : ' " + text + " '");
				simpleAlert.accept();

			} catch (Exception e) {
				clog.info("no alert present");
			}

		}

		public static String alert_Gettext(int timetowait) {
			String text = null;
			try {
				WebDriverWait wait = new WebDriverWait(driver.get(), timetowait);
				clog.info("waiting for alert");
				wait.until(ExpectedConditions.alertIsPresent());
				Alert simpleAlert = driver.get().switchTo().alert();
				text = simpleAlert.getText();

			} catch (Exception e) {
				clog.info("no alert present");
			}
			return text;
		}

	}

	public static void signEContract() {
		try {
			String huburl = "";
			if (user_id.get().equalsIgnoreCase("72")) {
				clog.info("BROWSER TRIGGERED INITIATED IN  :: SLBW022 ");
				huburl = "http://10.14.77.34:4444/wd/hub/";
			} else if (user_id.get().equalsIgnoreCase("67")|| user_id.get().equalsIgnoreCase("63")) {

				huburl = "http://10.14.77.31:4444/wd/hub/";

			} else if (user_id.get().equalsIgnoreCase("92")) {

				huburl = "http://10.14.77.34:4444/wd/hub/";

			} else if (user_id.get().equalsIgnoreCase("61")|| user_id.get().equalsIgnoreCase("114")) {
				huburl = "http://10.14.77.28:4444/wd/hub/";

			} else if ( user_id.get().equalsIgnoreCase("20")|| user_id.get().equalsIgnoreCase("93")) {

				huburl = "http://10.14.77.30:4444/wd/hub/";

			}
			else if ( user_id.get().equalsIgnoreCase("116")) {

				huburl = "http://10.14.77.34:4444/wd/hub/";

			}
			else if (user_id.get().equalsIgnoreCase("109")  ){

				huburl = "http://10.14.77.29:4444/wd/hub/";

			}else if (user_id.get().equalsIgnoreCase("1") || user_id.get().equalsIgnoreCase("57")) {
//				cap.setVersion("SLBW020");
				huburl = "http://10.14.77.34:4444/wd/hub/";

			} else if (user_id.get().equalsIgnoreCase("78")) {
//				cap.setVersion("SLBW020");
				huburl = "http://10.14.43.228:4444/wd/hub/";

			} else if (user_id.get().equalsIgnoreCase("81")) {
//				cap.setVersion("SLBW020");
				huburl = "http://10.14.43.228:4444/wd/hub/";

			} else if (user_id.get().equalsIgnoreCase("79")) {
//				cap.setVersion("SLBW020");
				huburl = "http://10.14.43.229:4444/wd/hub/";

			} else if (user_id.get().equalsIgnoreCase("80")) {
//				cap.setVersion("SLBW020");
				huburl = "http://10.14.43.230:4444/wd/hub/";

			} else if (user_id.get().equalsIgnoreCase("86")) {
//				cap.setVersion("SLBW020");
				huburl = "http://10.14.43.231:4444/wd/hub/";

			} else if (user_id.get().equalsIgnoreCase("87")) {
//				cap.setVersion("SLBW020");
				huburl = "http://10.14.43.232:4444/wd/hub/";

			} else {
//				ChromeOptions options = new ChromeOptions();
				huburl = "http://10.14.77.34:4444/wd/hub/";
			}
			DesiredCapabilities cap = new DesiredCapabilities();


			//options.setCapability("sessionTimeout","2m");
			//cap.setCapability("sessionTimeout","5m");
			cap.setBrowserName("signUpdate");
			//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);

			WebDriver autoDriver =  new RemoteWebDriver(new URL(huburl), cap);
			//driver.set(new RemoteWebDriver(new URL(huburl), cap));
		} catch (Exception e) {
			clog.info(Result.getStackMsg(e));
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
	}

	/*------------------------------------------------------------------------------------------------------
	* Function Name: OpenBrowser
	* Use : Opens a new browser and resizes it according the number of parallel instances
	* Designed By: AG
	* Last Modified Date : 15-June-2016
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static void OpenBrowser(String BrowserName, String URL) {
		env.set("Web");
		try {
			System.clearProperty("proxyHost");
			System.clearProperty("proxyPort");
			// System.setProperty("proxyHost", "proxy.mobily.lan");
			// System.setProperty("proxyPort", "8080");
			try {
				driver.get().close();
				driver.get().quit();
				driver.set(null);

			} catch (Exception e) {

			}
			// clog.info("##done till here");
			// clog.info(BrowserName);
			// clog.info(URL);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			clog.info("Video Name: " + runid.get() + "_" + TC_Id.get() + "_" + Usecase.get() + "_" + dtf.format(now));
			System.out.println(
					"Video Name: " + runid.get() + "_" + TC_Id.get() + "_" + Usecase.get() + "_" + dtf.format(now));
			String huburl = "http://localhost:4444/wd/hub";
			DesiredCapabilities cap = new DesiredCapabilities();
			// cap.setCapability("zal:name", runid.get() + "_" + TC_Id.get() + "_" +
			// Usecase.get());
			// cap.setCapability("zal:build",
			// runid.get()+"_"+TC_Id.get()+"_"+Usecase.get()+"_"+dtf.format(now));
			// cap.setCapability("zal:screenResolution","1280*720");
			switch (BrowserName.toLowerCase()) {
			case "mobile":
				// driver.set(new AndroidMobBrow().getNewDriver());
				driver.get().get(URL);
				System.out.println("Title " + driver.get().getTitle());
				break;
			case "firefox":
				// System.setProperty("webdriver.gecko.driver", WorkingDir.get() +
				// "/Drivers/geckodriver.exe");
				// driver.set(new FirefoxDriver());
				cap.setBrowserName("firefox");
//				cap.setPlatform(Platform.WINDOWS);
				driver.set(new RemoteWebDriver(new URL(huburl), cap));
				driver.get().get(URL);
				Thread.sleep(2000);
				maximize();
				System.out.println("Title " + driver.get().getTitle());
				break;
			case "chrome":
				// cap.setBrowserName("chrome");
				/*
				 * if (user_id.get().equalsIgnoreCase("72")) { cap.setVersion("SLBW020"); //
				 * cap.setVersion("SPPW018"); driver.set(new RemoteWebDriver(new URL(huburl),
				 * cap)); } else if (user_id.get().equalsIgnoreCase("67")) {
				 * cap.setVersion("SLBW016"); driver.set(new RemoteWebDriver(new URL(huburl),
				 * cap)); } else if (user_id.get().equalsIgnoreCase("92")) {
				 * cap.setVersion("SLBW017"); driver.set(new RemoteWebDriver(new URL(huburl),
				 * cap)); } else if (user_id.get().equalsIgnoreCase("61")) {
				 * cap.setVersion("SLBW018"); driver.set(new RemoteWebDriver(new URL(huburl),
				 * cap)); } else if (user_id.get().equalsIgnoreCase("63")) {
				 * cap.setVersion("SLBW019"); driver.set(new RemoteWebDriver(new URL(huburl),
				 * cap)); } else if (user_id.get().equalsIgnoreCase("1")) { //
				 * cap.setVersion("SLBW020"); huburl = "http://localhost:6666/wd/hub";
				 * driver.set(new RemoteWebDriver(new URL(huburl), cap)); } else { //
				 * ChromeOptions options = new ChromeOptions(); //
				 * options.addArguments("--headless","--window-size=1920,1200");
				 * System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
				 * + "//Drivers//chromedriver.exe"); driver.set(new ChromeDriver());
				 * System.out.println("Enter the browser class: " + BrowserName); }
				 */
				ChromeOptions options = new ChromeOptions();
				// options.addArguments("--no-sandbox");
				// options.setExperimentalOption("excludeSwitches",new
				// String[]{"enable-logging"});

				// 72 - Rajesh
				if (user_id.get().equalsIgnoreCase("72")) {
					huburl = "http://10.14.77.32:4444/wd/hub/";
					// 67 - Soniya 108 - Saravana Vig
				} else if (user_id.get().equalsIgnoreCase("67")|| user_id.get().equalsIgnoreCase("63")) {

					huburl = "http://10.14.77.31:4444/wd/hub/";

				}

				else if (user_id.get().equalsIgnoreCase("108")) {

					huburl = "http://10.14.77.28:4444/wd/hub/";

				}
				else if (user_id.get().equalsIgnoreCase("106")  ) {

					huburl = "http://10.14.43.231:4444/wd/hub/";

				}
				else if (user_id.get().equalsIgnoreCase("20")  ) {

					huburl = "http://10.14.43.230:4444/wd/hub/";

				}
				// 92 - Sathya
				else if (user_id.get().equalsIgnoreCase("92")) {
					huburl = "http://10.14.77.34:4444/wd/hub/";
// 94 - senthil
				}
				// 61- Gautham
				else if (user_id.get().equalsIgnoreCase("61")|| user_id.get().equalsIgnoreCase("114")) {
					huburl = "http://10.14.77.28:4444/wd/hub/";
// 94 - senthil
				}
				else if ( user_id.get().equalsIgnoreCase("116")) {

					huburl = "http://10.14.77.34:4444/wd/hub/";

				} else if (user_id.get().equalsIgnoreCase("94")) {
					huburl = "http://localhost:4444/wd/hub/";
					cap.setVersion("94local");

				}
				// 63 - Danny
				else if (user_id.get().equalsIgnoreCase("20") || user_id.get().equalsIgnoreCase("93") ){

					huburl = "http://10.14.77.30:4444/wd/hub/";

				}

				else if (user_id.get().equalsIgnoreCase("109")  ){

					huburl = "http://10.14.77.29:4444/wd/hub/";

				}

				else if (user_id.get().equalsIgnoreCase("1")) {
//					cap.setVersion("SLBW020");
					huburl = "http://10.14.77.9:4444/wd/hub/";

				}
				// 57 - Kavitha
				else if (user_id.get().equalsIgnoreCase("57") ) {
//					cap.setVersion("SLBW020");
					huburl = "http://10.14.77.9:4444/wd/hub/";

				} else if (user_id.get().equalsIgnoreCase("78")) {
//					cap.setVersion("SLBW020");
					huburl = "http://10.14.43.228:4444/wd/hub/";

				} else if (user_id.get().equalsIgnoreCase("81")) {
//					cap.setVersion("SLBW020");
					huburl = "http://10.14.43.228:4444/wd/hub/";

				} else if (user_id.get().equalsIgnoreCase("79")) {
//					cap.setVersion("SLBW020");
					huburl = "http://10.14.43.229:4444/wd/hub/";

				} else if (user_id.get().equalsIgnoreCase("80")) {
//					cap.setVersion("SLBW020");
					huburl = "http://10.14.43.230:4444/wd/hub/";

				} else if (user_id.get().equalsIgnoreCase("86")) {
//					cap.setVersion("SLBW020");
					huburl = "http://10.14.43.231:4444/wd/hub/";

				} else if (user_id.get().equalsIgnoreCase("87")) {
//					cap.setVersion("SLBW020");
					huburl = "http://10.14.43.232:4444/wd/hub/";

				}
				// cap.setCapability(ChromeOptions.CAPABILITY, options);
				driver.set(new RemoteWebDriver(new URL(huburl), options));
				driver.get().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get().get(URL);
				System.out.println("Title " + driver.get().getTitle());
				break;

			case "ie":
				// clog.info(huburl);
				if (Continue.get() == true) {
					cap.setBrowserName("internet explorer");
//                cap.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 30000);
//                cap.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
//                cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//                cap.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
//                cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
					// cap.setCapability("usePerProcessProxy", true);
					// cap.setCapability("browserCommandLineSwitches", "-private");
					// cap.setCapability("ensureCleanSession", true);
					// cap.setCapability("requireWindowFocus", false);
					cap.setCapability("sessionTimeout", "5m");
					cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
					cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
					//cap.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
					cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
					InternetExplorerOptions options1 = new InternetExplorerOptions();
					options1.introduceFlakinessByIgnoringSecurityDomains();
					options1.merge(cap);
					// clog.info(user_id.get());
					if (user_id.get().equalsIgnoreCase("72")) {
						huburl = "http://10.14.77.32:4444/wd/hub/";
					} else if (user_id.get().equalsIgnoreCase("67")|| user_id.get().equalsIgnoreCase("115")) {

						huburl = "http://10.14.77.31:4444/wd/hub/";

					} else if (user_id.get().equalsIgnoreCase("92")) {

						huburl = "http://10.14.77.34:4444/wd/hub/";

					} else if (user_id.get().equalsIgnoreCase("61")|| user_id.get().equalsIgnoreCase("114")) {
						huburl = "http://10.14.77.28:4444/wd/hub/";

					}else if ( user_id.get().equalsIgnoreCase("116")) {

						huburl = "http://10.14.77.34:4444/wd/hub/";

					}  else if (user_id.get().equalsIgnoreCase("94")) {
						huburl = "http://localhost:4444/wd/hub/";
						cap.setVersion("94local");

					}

					else if ( user_id.get().equalsIgnoreCase("20") || user_id.get().equalsIgnoreCase("93")) {

						huburl = "http://10.14.77.30:4444/wd/hub/";

					}
					else if (user_id.get().equalsIgnoreCase("109")  ){

						huburl = "http://10.14.77.29:4444/wd/hub/";

					}
					else if (user_id.get().equalsIgnoreCase("106")  ) {

						huburl = "http://10.14.43.231:4444/wd/hub/";

					}
					else if (user_id.get().equalsIgnoreCase("20")  ) {

						huburl = "http://10.14.43.230:4444/wd/hub/";

					}else if (user_id.get().equalsIgnoreCase("57")) {
//						cap.setVersion("SLBW020");
						huburl = "http://10.14.77.9:4444/wd/hub/";

					} else if (user_id.get().equalsIgnoreCase("1")) {
//					cap.setVersion("SLBW020");
						huburl = "http://10.14.77.9:4444/wd/hub/";

					} else if (user_id.get().equalsIgnoreCase("78")) {
//						cap.setVersion("SLBW020");
						huburl = "http://10.14.43.228:4444/wd/hub/";

					} else if (user_id.get().equalsIgnoreCase("81")) {
//						cap.setVersion("SLBW020");
						huburl = "http://10.14.43.228:4444/wd/hub/";

					} else if (user_id.get().equalsIgnoreCase("79")) {
//						cap.setVersion("SLBW020");
						huburl = "http://10.14.43.229:4444/wd/hub/";

					} else if (user_id.get().equalsIgnoreCase("80")) {
//						cap.setVersion("SLBW020");
						huburl = "http://10.14.43.230:4444/wd/hub/";

					} else if (user_id.get().equalsIgnoreCase("86")) {
//						cap.setVersion("SLBW020");
						huburl = "http://10.14.43.231:4444/wd/hub/";

					} else if (user_id.get().equalsIgnoreCase("87")) {
//						cap.setVersion("SLBW020");
						huburl = "http://10.14.43.232:4444/wd/hub/";

					}
					driver.set(new RemoteWebDriver(new URL(huburl), options1));
					driver.get().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
					driver.get().get(URL);

					System.out.println("Title " + driver.get().getTitle());
				}
				break;

			case "Appium":
				String Mobile = "";
				String device_Type = Testdata.get("Device_Type");
				if (device_Type.contains("cloud")) {
					Mobile = "pcloudy_" + Testdata.get("Device_Name");
					cap.setCapability("pCloudy_Username", ReadMobileproperties(Mobile, "pCloudy_Username"));
					cap.setCapability("pCloudy_ApiKey", ReadMobileproperties(Mobile, "pCloudy_ApiKey"));
					cap.setCapability("pCloudy_DurationInMinutes",
							ReadMobileproperties(Mobile, "pCloudy_DurationInMixnutes"));
					cap.setCapability("newCommandTimeout", ReadMobileproperties(Mobile, "newCommandTimeout"));
					cap.setCapability("launchTimeout", ReadMobileproperties(Mobile, "launchTimeout"));
					cap.setCapability("pCloudy_DeviceFullName", ReadMobileproperties(Mobile, "pCloudy_DeviceFullName"));
					cap.setCapability("platformVersion", ReadMobileproperties(Mobile, "platformVersion"));
					cap.setCapability("platformName", ReadMobileproperties(Mobile, "platformName"));
					cap.setCapability("automationName", ReadMobileproperties(Mobile, "automationName"));
					cap.setCapability("pCloudy_ApplicationName",
							ReadMobileproperties(Mobile, "pCloudy_ApplicationName"));
					cap.setCapability("pCloudy_WildNet", ReadMobileproperties(Mobile, "pCloudy_WildNet"));
				} else {
					cap.setCapability("deviceName", ReadMobileproperties(Mobile, "DeviceName"));
					cap.setCapability("udid", ReadMobileproperties(Mobile, "DeviceName"));
					cap.setCapability("platformVersion", ReadMobileproperties(Mobile, "version"));
					cap.setCapability("platformName", "ANDROID");
					cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
				}
				cap.setCapability("appPackage", Testdata.get("appPackage"));
				cap.setCapability("appActivity", Testdata.get("appActivity"));
				if (Mobile.contains("pcloudy")) {
					driver.set(new AndroidDriver(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), cap));
				} else {
					driver.set(new AndroidDriver(
							new URL("http://127.0.0.1:" + ReadMobileproperties(Mobile, "appiumport") + "/wd/hub"),
							cap));
					driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
			}
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			clog.info(Result.getStackMsg(e));
			e.printStackTrace();
		}
	}


	public static String getProp(String propname) throws IOException {
		String fpath = WorkingDir.get() + "/Drivers/Config/UserList.properties";
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);

		prop.load(input);

		return prop.getProperty(propname);
	}

	/*------------------------------------------------------------------------------------------------------
	* Function Name: openEcontractApplication
	* Use : Opens a new browser and resizes it according the number of parallel instances
	* Designed By: JJ
	* Last Modified Date : 15-June-2021
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static void openEcontractApplication(String BrowserName,String Environment, String application) {


        String URL=Utlities.getEnvironmentURLDetails(Environment,application);

        clog.info("URL ------>>>> "+URL);
		waitTimeOut=Utlities.getEnvironmentWaitTimeDetails(Environment);
		implicitWait=Utlities.getEnvironmentImplicitWaitTimeDetails(Environment);


		env.set("Web");
		try {
			System.clearProperty("proxyHost");
			System.clearProperty("proxyPort");
			// System.setProperty("proxyHost", "proxy.mobily.lan");
			// System.setProperty("proxyPort", "8080");
			try {
				driver.get().close();
				driver.get().quit();
				driver.set(null);

			} catch (Exception e) {

			}
			// clog.info("##done till here");
			// clog.info(BrowserName);
			// clog.info(URL);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			clog.info("Video Name: " + runid.get() + "_" + TC_Id.get() + "_" + Usecase.get() + "_" + dtf.format(now));
			System.out.println(
					"Video Name: " + runid.get() + "_" + TC_Id.get() + "_" + Usecase.get() + "_" + dtf.format(now));
			String huburl = "http://localhost:4444/wd/hub";
			String id=null;
			String machineName=null;
			DesiredCapabilities cap = new DesiredCapabilities();
			// cap.setCapability("zal:name", runid.get() + "_" + TC_Id.get() + "_" +
			// Usecase.get());
			// cap.setCapability("zal:build",
			// runid.get()+"_"+TC_Id.get()+"_"+Usecase.get()+"_"+dtf.format(now));
			// cap.setCapability("zal:screenResolution","1280*720");
			switch (BrowserName.toLowerCase()) {
			case "mobile":
				// driver.set(new AndroidMobBrow().getNewDriver());
				driver.get().get(URL);
				System.out.println("Title " + driver.get().getTitle());
				break;
			case "firefox":
				// System.setProperty("webdriver.gecko.driver", WorkingDir.get() +
				// "/Drivers/geckodriver.exe");
				// driver.set(new FirefoxDriver());
				cap.setBrowserName("firefox");
//				cap.setPlatform(Platform.WINDOWS);
				driver.set(new RemoteWebDriver(new URL(huburl), cap));
				driver.get().get(URL);
				Thread.sleep(2000);
				maximize();
				System.out.println("Title " + driver.get().getTitle());
				break;
			case "chrome":

				ChromeOptions options = new ChromeOptions();
				options.setCapability("sessionTimeout","2m");

				//options.setPageLoadStrategy(PageLoadStrategy.NONE);
				clog.info("CURRENT USER "+user_id.get());


				id=user_id.get();
				machineName=getProp("User_ID_"+id);
				huburl=getProp(machineName);
				clog.info("BROWSER TRIGGERED  IN  :: "+machineName );


				driver.set(new RemoteWebDriver(new URL(huburl), options));
				//driver.get().manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
				//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
				//driver.get().manage().timeouts().setScriptTimeout(5, TimeUnit.MINUTES);

				driver.get().get(URL);
				System.out.println("Title " + driver.get().getTitle());
				break;

			case "ie":
				// clog.info(huburl);
				if (Continue.get() == true) {
					clog.info("Running in ie");
					cap.setBrowserName("internet explorer");
//                cap.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 30000);
//                cap.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
//                cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//                cap.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
//                cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
					// cap.setCapability("usePerProcessProxy", true);
					// cap.setCapability("browserCommandLineSwitches", "-private");
					// cap.setCapability("ensureCleanSession", true);
					// cap.setCapability("requireWindowFocus", false);

					cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

					cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
					InternetExplorerOptions options1 = new InternetExplorerOptions();
					//cap.setCapability("sessionTimeout","5m");
					options1.setCapability("sessionTimeout","5m");
					options1.merge(cap);
					 clog.info("CURRENT USER "+user_id.get());

					  id=user_id.get();
					  machineName=getProp("User_ID_"+id);
					  huburl=getProp(machineName);
					  clog.info("BROWSER TRIGGERED  IN  :: "+machineName );


					driver.set(new RemoteWebDriver(new URL(huburl), options1));
					driver.get().manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
					driver.get().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.get().get(URL);

					System.out.println("Title " + driver.get().getTitle());
				}
				break;

			}
			maximize();
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			clog.info(Result.getStackMsg(e));
			e.printStackTrace();
		}
	}

/*------------------------------------------------------------------------------------------------------
	* Function Name: openApplication
	* Use : Opens a new browser and resizes it according the number of parallel instances
	* Designed By: JJ
	* Last Modified Date : 15-June-2021
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static void openApplication(String BrowserName,String Environment, String application) {


        String URL=Utlities.getEnvironmentURLDetails(Environment,application);

        clog.info("URL ------>>>> "+URL);
		waitTimeOut=Utlities.getEnvironmentWaitTimeDetails(Environment);
		implicitWait=Utlities.getEnvironmentImplicitWaitTimeDetails(Environment);


		env.set("Web");
		try {
			System.clearProperty("proxyHost");
			System.clearProperty("proxyPort");
			// System.setProperty("proxyHost", "proxy.mobily.lan");
			// System.setProperty("proxyPort", "8080");
			try {
				driver.get().close();
				driver.get().quit();
				driver.set(null);

			} catch (Exception e) {

			}
			// clog.info("##done till here");
			// clog.info(BrowserName);
			// clog.info(URL);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			clog.info("Video Name: " + runid.get() + "_" + TC_Id.get() + "_" + Usecase.get() + "_" + dtf.format(now));
			System.out.println(
					"Video Name: " + runid.get() + "_" + TC_Id.get() + "_" + Usecase.get() + "_" + dtf.format(now));
			String huburl = "http://localhost:4444/wd/hub";
			String id=null;
			String machineName=null;
			DesiredCapabilities cap = new DesiredCapabilities();
			// cap.setCapability("zal:name", runid.get() + "_" + TC_Id.get() + "_" +
			// Usecase.get());
			// cap.setCapability("zal:build",
			// runid.get()+"_"+TC_Id.get()+"_"+Usecase.get()+"_"+dtf.format(now));
			// cap.setCapability("zal:screenResolution","1280*720");
			switch (BrowserName.toLowerCase()) {
			case "mobile":
				// driver.set(new AndroidMobBrow().getNewDriver());
				driver.get().get(URL);
				System.out.println("Title " + driver.get().getTitle());
				break;
			case "firefox":
				// System.setProperty("webdriver.gecko.driver", WorkingDir.get() +
				// "/Drivers/geckodriver.exe");
				// driver.set(new FirefoxDriver());
				cap.setBrowserName("firefox");
//				cap.setPlatform(Platform.WINDOWS);
				driver.set(new RemoteWebDriver(new URL(huburl), cap));
				driver.get().get(URL);
				Thread.sleep(2000);
				maximize();
				System.out.println("Title " + driver.get().getTitle());
				break;
			case "chrome":

				ChromeOptions options = new ChromeOptions();
				options.setCapability("sessionTimeout","2m");

				//options.setPageLoadStrategy(PageLoadStrategy.NONE);
				clog.info("CURRENT USER "+user_id.get());


				id=user_id.get();
				machineName=getProp("User_ID_"+id);
				huburl=getProp(machineName);
				clog.info("BROWSER TRIGGERED  IN  :: "+machineName );


				driver.set(new RemoteWebDriver(new URL(huburl), options));
				//driver.get().manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
				//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
				//driver.get().manage().timeouts().setScriptTimeout(5, TimeUnit.MINUTES);

				driver.get().get(URL);
				System.out.println("Title " + driver.get().getTitle());
				break;

			case "ie":
				// clog.info(huburl);
				if (Continue.get() == true) {
					clog.info("Running in ie");
					cap.setBrowserName("internet explorer");
//                cap.setCapability(InternetExplorerDriver.BROWSER_ATTACH_TIMEOUT, 30000);
//                cap.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
//                cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
//                cap.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
//                cap.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
					// cap.setCapability("usePerProcessProxy", true);
					// cap.setCapability("browserCommandLineSwitches", "-private");
					// cap.setCapability("ensureCleanSession", true);
					// cap.setCapability("requireWindowFocus", false);

					cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

					cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
					InternetExplorerOptions options1 = new InternetExplorerOptions();
					//cap.setCapability("sessionTimeout","5m");
					options1.setCapability("sessionTimeout","5m");
					options1.merge(cap);
					 clog.info("CURRENT USER "+user_id.get());

					  id=user_id.get();
					  machineName=getProp("User_ID_"+id);
					  huburl=getProp(machineName);
					  clog.info("BROWSER TRIGGERED  IN  :: "+machineName );


					driver.set(new RemoteWebDriver(new URL(huburl), options1));
					driver.get().manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
					driver.get().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					driver.get().get(URL);

					System.out.println("Title " + driver.get().getTitle());
				}
				break;

			}
			maximize();
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			clog.info(Result.getStackMsg(e));
			e.printStackTrace();
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebList
	 * Use 					: Subclass of browser class represents the WebList in the application and
	 * 						  contains functions for all the operations performed on Web List
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 13-Nov-2017
	--------------------------------------------------------------------------------------------------------*/
	public static class WebList {
		public static void clickTab(String objname, String objvalue) throws Exception {
			waitforload();
			String objtype = "WebList";
			String[] objprop = Utlities.FindObject(objname, objtype);
			String cellXpath = objprop[0] + "//ul[1]//li";
			List<org.openqa.selenium.WebElement> List_Count = driver.get().findElements(By.xpath(cellXpath));
			int List = List_Count.size();
			for (int i = 1; i < List; i++) {
				cellXpath = objprop[0] + "//ul[1]//li[" + i + "]";
				org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpath));
				((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
				String celldata = driver.get().findElements(By.xpath(cellXpath)).get(0).getText();
				if (celldata.toLowerCase().contains(objvalue.toLowerCase())) {
					driver.get().findElement(By.xpath(cellXpath)).click();
					break;
				}
			}
			if (Continue.get() == false) {
				Continue.set(false);
				if (methodScreenShotTaken.get() == false) {
					Result.Methodscreenshot();
				}
				clog.info("Failed at Tab Selection: " + objname);
			}
		}
	}

	public static void maximize() {
		driver.get().manage().window().maximize();
	}

	public static Boolean Readystate() {
		((JavascriptExecutor) driver.get()).executeScript("return document.readyState");
		return true;
	}

	public static String ReadMobileproperties(String fname, String propname) throws IOException {
		String fpath = System.getProperty("user.dir") + "/Drivers/Devices/" + fname + ".properties";
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream(fpath);

		prop.load(input);
		// prop.getProperty(propname);

		return prop.getProperty(propname);
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: Common methods
	 * Use 					: Subclass of browser class represents the WebList in the application and
	 * 						  contains functions for all the operations performed on Web List
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 15-12-2020
	--------------------------------------------------------------------------------------------------------*/
	@SuppressWarnings("deprecation")
	public static boolean isAlertExist() {
		try {
			WebDriverWait wait = new WebDriverWait(driver.get(), 20);
			if (!(wait.until(ExpectedConditions.alertIsPresent()) == null)) {
				String popup = driver.get().switchTo().alert().getText();
				Result.fUpdateLog(popup);
				if (Validatedata("SmartLimit").equalsIgnoreCase("yes")) {
					String theDigits = CharMatcher.DIGIT.retainFrom(popup);
					Def_Smart_limit.set(theDigits);
				}
			}
			Browser.alert.accept();
			Browser.Readystate();
			return true;
		} catch (Exception e) {
			Result.fUpdateLog("No Alert Exist");
			Result.takescreenshot("No Alert Exist");
			e.getMessage();
			return false;
		}
	}
	// -------------------------------------------------------------------------------------------

	public static void ToWait() {
		try {
			int i = 0;
			driver.get().manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
			// i = i * 60 * 1000;
			Thread.sleep(15000);
		} catch (Exception e) {
			Result.takescreenshot(" Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
		}
	}

	// ---------------------------------------------------------------------------------------
	public static void waitmoreforload() throws AWTException {
		try {
			driver.get().manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
			Thread.sleep(10000);
			Method.waitForPageToLoad(driver.get(), 30);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Result.takescreenshot(" Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
		}

	}

	public static void waitmoreforload_(int timetoWait) throws AWTException {
		try {
			driver.get().manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
			Thread.sleep(10000);
			Method.waitForPageToLoad(driver.get(), timetoWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Result.takescreenshot(" Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
		}

	}

	// -----------------------------------------------------------------------------------------------

	public static void JavaScriptClick(String objname) throws InterruptedException {
		try {
			// ConditionalWait(objname, ObjTyp);
			// String[] objprop = Utlities.FindObject(objname, ObjTyp);
			waitforload();
			org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(objname));
			((RemoteWebDriver) driver.get()).executeScript("arguments[0].click()", scr1);
			Thread.sleep(1500);
		} catch (Exception e) {

			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
		}
	}

	// -------------------------------------------------------------------------------------------------------------

	public static void waitforobj(String obj, String obj1) {
		try {
			int time = 1;
			if (obj1.equals("WebButton"))
				while (Browser.WebButton.exist(obj) == false) {
					Thread.sleep(2000);
					time++;
					if (Browser.WebButton.exist(obj) == true)
						break;
					if (time > 40)
						break;
				}
			if (obj1.equals("WebLink"))
				while (Browser.WebLink.exist(obj) == false) {
					Thread.sleep(2000);
					time++;
					if (Browser.WebLink.exist(obj) == true)
						break;
					if (time > 40)
						break;
				}
			if (obj1.equals("WebEdit"))
				while (Browser.WebEdit.exist(obj) == false) {
					Thread.sleep(2000);
					time++;
					if (Browser.WebEdit.exist(obj) == true)
						break;
					if (time > 40)
						break;
				}
			if (time > 40)
				Continue.set(false);
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
		}
	}

	// --------------------------------------------------------------------------------------------------------------
	public static void scroll(String objname, String ObjTyp) {
		try {
			String[] objprop = Utlities.FindObject(objname, ObjTyp);
			org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(objprop[0]));
			((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
			Thread.sleep(1500);
		} catch (Exception e) {
			Continue.set(false);
			Result.takescreenshot(" Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
		}
	}

	// --------------------------------------------------------------------------------------------------------------
	public static int Select_Cell(String objname, String objTyp) throws Exception {
		int Col = 1;
		String Expected = objTyp;
		String[] obj = objTyp.split("_");
		if (obj.length > 1)
			Expected = objTyp.replace('_', ' ');
		int Col_Count = Browser.WebTable.getColCount(objname);
		for (int i = 1; i < Col_Count; i++) {
			Col = i;
			String cellXpath = "//table//th[" + i + "]";
			org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpath));
			((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
			String celldata = driver.get().findElements(By.xpath(cellXpath)).get(0).getText();
			if (celldata.toLowerCase().contains(Expected.toLowerCase()))
				break;
		}
		return Col;
	}

	// --------------------------------------------------------------------------------------------------------

	public static void Link_Select(String Text) throws AWTException {
		waitforload();
		String[] objprop = Utlities.FindObject("Link", "WebTable");
		String cellXpath = objprop[0] + Text + "']";
		org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpath));
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
		driver.get().findElement(By.xpath(cellXpath)).click();
	}

	// -----------------------------------------------------------------------------------------------------------------

	public static void waitforload() throws AWTException {
		driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Method.waitForPageToLoad(driver.get(), waitTimeOut);

	}

	// ------------------------------------------------------------------------------------------------------------

	public static void Text_Select(String Tag, String Text) throws AWTException {
		String cellXpath = "//" + Tag + "[text()='" + Text + "']";
		org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpath));
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
		waitforload();
		driver.get().findElement(By.xpath(cellXpath)).click();
		// waitforload();
	}// div option button span
		// --------------------------------------------------------------------------------------------------------------

	public static void Tag_Select(String Tag, String Text) throws AWTException {
		waitforload();
		String cellXpath = "//" + Tag + "[text()='" + Text + "']";
		org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpath));
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
	}

	// -----------------------------------------------------------------------------------------------------

	public static void Radio_Select(String Text) throws AWTException {
		waitforload();
		String cellXpath = "//input[@value='" + Text + "']";

		if (driver.get().findElement(By.xpath(cellXpath)).isDisplayed()) {
			org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpath));
			((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
			driver.get().findElement(By.xpath(cellXpath)).click();
			// break;}

		} else {
			Result.takescreenshot("Unable to select" + Text);
			Continue.set(false);

			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
	}

	public static void Radio_Select(boolean screenshot, String Text) throws AWTException {
		waitforload();
		String cellXpath = "//input[@value='" + Text + "']";

		if (driver.get().findElement(By.xpath(cellXpath)).isDisplayed()) {
			org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpath));
			((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
			driver.get().findElement(By.xpath(cellXpath)).click();
			// break;}

		} else {
			Result.takescreenshot("Unable to select" + Text);

			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}
		if (screenshot) {
			Result.takescreenshot(Text + " is clicked successfully");
		}
	}

	// --------------------------------------------------------------------------------------------------------

	public static void Radio_Select1(String Text) throws AWTException {
		waitforload();
		String cellXpath = "//input[@value='" + Text + "']";
		driver.get().findElement(By.xpath(cellXpath)).click();

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Radio_None

	 *--------------------------------------------------------------------------------------------------------*/
	public static void Radio_None(String Text) throws AWTException {
		waitforload();
		List<org.openqa.selenium.WebElement> elements = driver.get().findElements(
				By.xpath("//div[@class='siebui-ecfg-products']//div[1]//div[@class='siebui-ecfg-feature-group']"));
		int Size = elements.size();
		System.out.println(Size);
		boolean flag = false;
		waitforload();
		for (int i = 1; i <= Size; i++) {
			List<org.openqa.selenium.WebElement> cellXpath = driver.get()
					.findElements(By.xpath("//div[@class='siebui-ecfg-products']//div[1]//div[" + i
							+ "]//div[1]//table//div[1]//div[1]//input"));
			waitforload();
			for (int t = 1; t < cellXpath.size(); t++) {
				if (cellXpath.get(t).getAttribute("value").equals(Text)) {
					if (cellXpath.get(t).getAttribute("type").equals("radio")) {
						// Radio Button
						((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)",
								cellXpath.get(0));
						waitforload();
						cellXpath.get(0).click();
						flag = true;
						break;
					} else {
						// Check box
						((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)",
								cellXpath.get(t));
						waitforload();
						cellXpath.get(t).click();
						flag = true;
						break;
					}
				}
			}

			if (flag) {
				break;
			}

		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Title_Select

	--------------------------------------------------------------------------------------------------------*/
	public static void Title_Select(String Tag, String Text) throws AWTException {
		waitforload();
		String cellXpath = "//" + Tag + "[@title='" + Text + "']";
		org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpath));
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
		waitforload();
		driver.get().findElement(By.xpath(cellXpath)).click();
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Popup_Data
	--------------------------------------------------------------------------------------------------------*/
	public static void Popup_Data(String objname, int rownum, int columnnum, String Variable, String val) {
		try {
			String[] objprop = Utlities.FindObject(objname, "WebTable");
			String cellXpathX = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
			org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpathX));
			((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
			driver.get().findElement(By.xpath(cellXpathX)).click();
			String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//span";
			driver.get().findElement(By.xpath(cellXpath)).click();
			Browser.ListBox.select("PopupQuery_List", Variable);
			waitforload();
			Browser.WebEdit.Set("PopupQuery_Search", val);
			waitforload();
			Browser.WebButton.click("Popup_Go");
			waitforload();
		} catch (Exception e) {
			Result.takescreenshot(" Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Popup_Click
	--------------------------------------------------------------------------------------------------------*/
	public static void Popup_Click(String objname, int rownum, int columnnum) throws AWTException {

		waitforload();

		String[] objprop = Utlities.FindObject(objname, "WebTable");
		/*
		 * String cellXpath1X = objprop[0] + "//tr[" + rownum + "]//td[" + (columnnum +
		 * 1) + "]"; WebElement scr2 = cDriver.get().findElement(By.xpath(cellXpath1X));
		 * ((RemoteWebDriver)
		 * cDriver.get()).executeScript("arguments[0].scrollIntoView(true)", scr2);
		 */

		String cellXpathX = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]";
		org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpathX));
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
		driver.get().findElement(By.xpath(cellXpathX)).click();

		Actions action = new Actions(driver.get());
		action.sendKeys(Keys.TAB).build().perform();

		driver.get().findElement(By.xpath(cellXpathX)).click();
		String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//span";
		org.openqa.selenium.WebElement scr = driver.get().findElement(By.xpath(cellXpath));
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr);
		driver.get().findElement(By.xpath(cellXpath)).click();

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Popup_Click1
	--------------------------------------------------------------------------------------------------------*/
	public static void Popup_Click1(String objname, int rownum, int columnnum) throws AWTException {
		waitforload();
		String[] objprop = Utlities.FindObject(objname, "WebTable");
		String cellXpath1X = objprop[0] + "//tr[" + rownum + "]//td[" + (columnnum + 1) + "]";
		org.openqa.selenium.WebElement scr2 = driver.get().findElement(By.xpath(cellXpath1X));
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr2);

		String cellXpathX = objprop[0] + "//tr[" + rownum + "]//td[" + (columnnum - 1) + "]";
		org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(cellXpathX));
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
		driver.get().findElement(By.xpath(cellXpathX)).click();
		waitforload();
		Actions action = new Actions(driver.get());
		action.sendKeys(Keys.TAB).build().perform();
		waitforload();
		// action.sendKeys(scr1, Keys.TAB).build().perform();

		/*
		 * cDriver.get().findElement(By.xpath(cellXpathX)).sendKeys(Keys.TAB);
		 * waitforload();
		 */
		String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//span";
		org.openqa.selenium.WebElement scr = driver.get().findElement(By.xpath(cellXpath));
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", scr);
		driver.get().findElement(By.xpath(cellXpath)).click();

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: TabNavigator

	--------------------------------------------------------------------------------------------------------*/
	public static void TabNavigator(String value) {
		driver.get().manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		Boolean finder = false;
		try {
			Thread.sleep(2);
			List<org.openqa.selenium.WebElement> options1 = driver.get()
					.findElements(By.xpath("//div[@class='siebui-nav-tab siebui-subview-navs']/div/ul/li/a"));

			for (org.openqa.selenium.WebElement option1 : options1) {
				if ((option1.getText().equalsIgnoreCase(value))) {
					option1.click();
					finder = true;
					break;
				}
			}

			if (finder == false) {
				List<org.openqa.selenium.WebElement> options = driver.get()
						.findElements(By.xpath("//div[@class='siebui-nav-tab siebui-subview-navs']//option"));
				for (org.openqa.selenium.WebElement option : options) {
					if ((option.getText().equalsIgnoreCase(value))) {
						option.click();
						break;
					}
				}
			}

		} catch (Exception e) {
			Result.takescreenshot(" Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Upload

	--------------------------------------------------------------------------------------------------------*/
	public static void Upload(String Text, String File) {
		try {
			String path = Templete_FLD.get() + "/Guided_Journey/" + File;
			String[] objprop = Utlities.FindObject(Text, "WebButton");
			driver.get().findElement(By.xpath(objprop[0])).sendKeys(path);
			waitmoreforload();
			// Result.takescreenshot("File Uploaded");

		} catch (Exception e) {
			Driver.Continue.set(false);
			Result.fUpdateLog("Failed to Upload");
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: GlobalSearch

	--------------------------------------------------------------------------------------------------------*/
	public static void GlobalSearch(String Type, String Data) {
		try {
			waitforload();
			Browser.WebLink.waittillvisible("Global_Search");
			Browser.WebLink.click("Global_Search");
			waitforload();
			if (Type.equalsIgnoreCase("SIMSwap")) {
				Browser.WebEdit.Set("Phone_Guided", Data);
				Result.fUpdateLog("Global Search MSISDN");
				Result.takescreenshot("Global Search MSISDN");
				Browser.WebLink.click("GS_Go");
				waitforload();
				Thread.sleep(1000);
				Text_Select("button", "SIM Swap");
			}

			else {

				if (Type.equalsIgnoreCase("MSISDN")) {
					Browser.WebEdit.Set("Phone_Guided", Data);
					Result.fUpdateLog("Global Search MSISDN");
					Result.takescreenshot("Global Search MSISDN");
					Browser.WebLink.click("GS_Go");
					waitforload();
				}

				else if (Type.equalsIgnoreCase("ContactID")) {
					Browser.WebEdit.Set("ContactID_Guided", Data);
					Result.fUpdateLog("Global Search ContactID");
					Result.takescreenshot("Global Search ContactID");
					Browser.WebLink.click("GS_Go");
					waitforload();
				}

				Thread.sleep(1000);
				Result.fUpdateLog("Global Search Retrived Data");
				Result.takescreenshot("Global Search Retrived Data");
				waitforload();
				Browser.WebLink.waittillvisible("Global_Link");
				Browser.WebLink.click("Global_Link");
				waitforload();
			}
		} catch (Exception e) {
			Result.fUpdateLog("Exception Occcurred in Global Search");
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Continue.set(false);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: RadioL
	--------------------------------------------------------------------------------------------------------*/
	public static void RadioL(String Text) throws AWTException {
		Radio_Select(Text);
		waitforload();

		waitforload();
		driver.get()
				.findElement(By.xpath("//div[@class='div-table siebui-ecfg-table-collapse']//a[text()='" + Text + "']"))
				.click();

		waitforload();
		Result.fUpdateLog("Customising the Addon " + Text);
		waitforload();
	}

	// -------------------------------------------------------------------------------------------
	public static Object getKeyFromValue(HashMap<String, String> hm, String value) {
		for (Object o : hm.keySet()) {
			if (hm.get(o).equals(value)) {
				return o;
			}
		}
		return null;
	}

	public static void handlingNavBar(String dropdownclick, String DropdownValue, String DirectTabClick)
			throws InterruptedException {
		String objtype = "WebLink";
		String[] objprop1 = Utlities.FindObject(dropdownclick, objtype);
		String Object1 = objprop1[0];
		String[] objprop2 = Utlities.FindObject(DropdownValue, objtype);
		String Object2 = objprop2[0];
		String[] objprop3 = Utlities.FindObject(DirectTabClick, objtype);
		String Object3 = objprop3[0];
		// Method.clickTD(objprop);
		int exception = 0;

		try {
			driver.get().findElement(By.xpath(Object1)).click();
			Thread.sleep(2000);
			driver.get().findElement(By.xpath(Object2));
			exception = 1;
		} catch (NoSuchElementException e) {
			driver.get().findElement(By.xpath(Object3));
			exception = 2;
		}
		if (exception == 1) {
			driver.get().findElement(By.xpath(Object1)).click();
			Thread.sleep(2000);
			driver.get().findElement(By.xpath(Object2)).click();
		} else if (exception == 2) {
			driver.get().findElement(By.xpath(Object3)).click();
		} else {
			System.out.println("Failed at if else");
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: OracleDBConnector
	 * Use 					: To connect to the Oracle Database with Siebel
	 * Designed By			: Ahilan C
	 * Last Modified Date 	: 20-01-2021
	--------------------------------------------------------------------------------------------------------*/
	public static java.sql.Connection OracleDBConnector(String strUserID, String strPassword, String hostName,
			String port, String SIDName) throws SQLException {
		java.sql.Connection conn = null;
		String dbURL = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST =" + hostName + ")(PORT=" + port
					+ "))(CONNECT_DATA=(SID=" + SIDName + ")))";
			// Result.fUpdateLog("jdbcurl : " +dbURL);
			conn = DriverManager.getConnection(dbURL, strUserID, strPassword);
			Result.fUpdateLog("Successfully connected to the oracle database");

		} catch (Exception e) {
			// Result.fUpdateLog("Exception occurred *** " +
			// ExceptionUtils.getStackTrace(e));
		}
		return conn;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getOTPFromEAIDBQuery
	 * Use 					: To get OTP code from DB query in EAI
	 * Designed By			: Ahilan C
	 * Last Modified Date 	: 20-01-2021
	--------------------------------------------------------------------------------------------------------*/
	public static String getOTPFromEAIDBQuery(String strUserID, String strPassword, String hostName, String port,
			String SIDName, String MSISDN) throws SQLException {
		Statement stmt;
		java.sql.Connection conn = OracleDBConnector(strUserID, strPassword, hostName, port, SIDName);
		String OTPCode = null;

		try {
			stmt = conn.createStatement();
			System.out.println("---> Executing Query....");
			System.out.println("EAI OTP Query : SELECT * FROM SR_OTP_MAIN_TBL where MSISDN='" + MSISDN
					+ "' order by TIME_STAMP desc");
			ResultSet OTPTblRS = stmt.executeQuery(
					"SELECT * FROM SR_OTP_MAIN_TBL where MSISDN='" + MSISDN + "' order by TIME_STAMP desc");

			// SELECT * FROM SR_OTP_MAIN_TBL where MSISDN='<MSISDN>' order by TIME_STAMP
			// desc;

			while (OTPTblRS.next()) {
				OTPCode = OTPTblRS.getString("");
				// scripts to be added (once receive EAI DB credentials)
			}

			conn.close();
		} catch (Exception e) {
			clog.error(e.getMessage());
		}
		return OTPCode;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: updateByPassFingerPrintQuery
	 * Use 					: To bypass Finger Print verification in Siebel
	 * Designed By			: Ahilan C
	 * Last Modified Date 	: 21-01-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void updateByPassFingerPrintQuery(String strUserID, String strPassword, String hostName, String port,
			String SIDName, String orderID) throws SQLException {
		Statement stmt;
		java.sql.Connection conn = OracleDBConnector(strUserID, strPassword, hostName, port, SIDName);

		try {
			stmt = conn.createStatement();
			// Result.fUpdateLog("Siebel FP Query : UPDATE siebel.s_order_x SET CX_ATTRIB_7
			// = 'Y' WHERE row_id IN (SELECT row_id FROM siebel.s_order WHERE order_num in
			// ('"+orderID+"'))");
			stmt.executeUpdate(
					"UPDATE siebel.s_order_x SET CX_ATTRIB_7 = 'Y' WHERE row_id IN (SELECT row_id FROM siebel.s_order WHERE order_num in ('"
							+ orderID + "'))");
			conn.setAutoCommit(false);
			conn.commit();

			// Result.fUpdateLog("ByPass Finger Verify successfully updated");
			conn.close();

		} catch (Exception e) {
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
		} finally {
			conn.close();
		}

	}

	public static void waitForElement(String identifier, String identifier_type) throws InterruptedException {
		waitTillSiebelLoad();
		try {
			int iterations = 20;
			if (identifier_type.equals("WebLink")) {
				for (int i = 1; i <= iterations; i++) {
					String[] objprop1 = Utlities.FindObject(identifier, identifier_type);
					String Object1 = objprop1[0];
					int count = driver.get().findElements(By.xpath(Object1)).size();
					if (count != 0 && Readystate()) {

						// if(cDriver.get().findElement(By.xpath(identifier)).isDisplayed()) {
						clog.info("Element found");
						System.out.println("Element found");
						break;
					} else {
						Thread.sleep(1000);
						clog.info("Waiting for the element:" + identifier);
						System.out.println("Waiting for the element:" + identifier);
					}
				}
			} else if (identifier_type.equals("WebEdit")) {
				for (int i = 1; i <= iterations; i++) {
					String[] objprop1 = Utlities.FindObject(identifier, identifier_type);
					String Object1 = objprop1[0];
					int count = driver.get().findElements(By.xpath(Object1)).size();
					if (count != 0) {
						// if(cDriver.get().findElement(By.name(identifier)).isDisplayed()) {
						System.out.println("Element found");
						break;
					} else {
						Thread.sleep(1000);
						System.out.println("Waiting for the element:" + identifier);
					}
				}

			} else if (identifier_type.equals("WebButton")) {
				for (int i = 1; i <= iterations; i++) {
					String[] objprop1 = Utlities.FindObject(identifier, identifier_type);
					String Object1 = objprop1[0];
					int count = driver.get().findElements(By.xpath(Object1)).size();
					if (count != 0) {
						// if(cDriver.get().findElement(By.id(identifier)).isDisplayed()) {
						System.out.println("Element found");
						break;

					} else {
						Thread.sleep(1000);
						System.out.println("Waiting for the element:" + identifier);
					}
				}

			} else if (identifier_type.equals("WebList")) {
				for (int i = 1; i <= iterations; i++) {
					String[] objprop1 = Utlities.FindObject(identifier, identifier_type);
					String Object1 = objprop1[0];
					int count = driver.get().findElements(By.xpath(Object1)).size();
					if (count != 0) {
						// if(cDriver.get().findElement(By.className(identifier)).isDisplayed()) {
						System.out.println("Element found");

						break;

					} else {
						Thread.sleep(1000);
						System.out.println("Waiting for the element:" + identifier);
					}

				}

			} else if (identifier_type.equals("ListBox")) {
				for (int i = 1; i <= iterations; i++) {
					String[] objprop1 = Utlities.FindObject(identifier, identifier_type);
					String Object1 = objprop1[0];
					int count = driver.get().findElements(By.xpath(Object1)).size();
					if (count != 0) {
						// if(cDriver.get().findElement(By.className(identifier)).isDisplayed()) {
						System.out.println("Element found");

						break;

					} else {
						Thread.sleep(1000);
						System.out.println("Waiting for the element:" + identifier);
					}

				}

			} else if (identifier_type.equals("WebTable")) {
				for (int i = 1; i <= iterations; i++) {
					String[] objprop1 = Utlities.FindObject(identifier, identifier_type);
					String Object1 = objprop1[0];
					int count = driver.get().findElements(By.xpath(Object1)).size();
					if (count != 0) {
						// if(cDriver.get().findElement(By.className(identifier)).isDisplayed()) {
						System.out.println("Element found");

						break;

					} else {
						Thread.sleep(1000);
						System.out.println("Waiting for the element:" + identifier);
					}

				}

			}
		} catch (Exception e) {
			Continue.set(false);
			clog.info(Result.getStackMsg(e));
		}

	}

	public static void JavaScriptClickU(String objname, String objTyp) throws InterruptedException {
		try {
			// ConditionalWait(objname, ObjTyp);
			// waitforload();

			// String[] objprop1 = Utlities.FindObject(dropdownclick, objtype);
			// String Object1=objprop1[0];
			String[] objprop = Utlities.FindObject(objname, objTyp);
			Method.waittillobjvisible(objprop);
			String Object1 = objprop[0];
			org.openqa.selenium.WebElement scr1 = driver.get().findElement(By.xpath(Object1));
			((RemoteWebDriver) driver.get()).executeScript("arguments[0].click()", scr1);
			// Thread.sleep(1500);
		} catch (Exception e) {
			// Continue.set(false);
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" Exception occurred *** " + ExceptionUtils.getStackTrace(e));
			Result.fUpdateLog("Exception occurred *** " + ExceptionUtils.getStackTrace(e));
		}
	}

	public static boolean validateString(String Objname, String Expected_Result) throws IOException {
		clog.info("Expected Value : " + Expected_Result);
		String objpath = Utlities.FindObjectname(Objname);
		String appstring = driver.get().findElement(By.xpath(objpath)).getText();
		clog.info("Application String : " + appstring);
		String Statusstr = "Object Name :" + Objname + " --- Expected String :" + Expected_Result
				+ " --- Application String :" + appstring;
		if (appstring.trim().equals(Expected_Result.trim())) {
			clog.info(Statusstr + " -> PASS");
			return true;

		} else if (appstring.trim().equals(Objname.trim())) {
			clog.info(Statusstr + " -> PASS");
			return true;
		} else {
			clog.info(Statusstr + " -> FAIL");
			return false;
		}
	}

	public static void alert_sendKeys(String input_tobe_sent) {
		try {
			WebDriverWait wait = new WebDriverWait(driver.get(), 30);
			clog.info("waiting for alert");
			wait.until(ExpectedConditions.alertIsPresent());
			Alert simpleAlert = driver.get().switchTo().alert();
			simpleAlert.sendKeys(input_tobe_sent);
			Thread.sleep(2000);
			simpleAlert.accept();
		} catch (Exception e) {
			clog.info("No alert");
		}
	}

	public static void Print_eContract() throws InterruptedException {
		// Change the path of exe file
		// String command = System.getProperty("user.dir") +
		// "//Drivers//Print_eContract.exe";
		Thread.sleep(5000);
		String command = "E:\\develop1\\backend\\Files\\AutomationScripts\\Java\\TMT_Automation\\Drivers\\Print_eContract_Updated.exe";
		clog.info(command);
		System.out.println("inside pecontract method");
		// clog.info(command);
		// clog.info("nside pecontract method");
		try {
			Process process = Runtime.getRuntime().exec(command);
			clog.info("Command executed");
		} catch (IOException e) {
			// clog.error(Result.getStackMsg(e));
			;
		}
	}

	public static void WaitforPending(String objname, String objTyp) {

		// driver.get().get(GetCurrentUrl);
		// img[@rn='ExecuteQuery']
		waitTillSiebelLoad();
		String[] objprop = Utlities.FindObject(objname, objTyp);
		String Object1 = objprop[0];

		for (int i = 1; i <= 35; i++) {

			WebDriverWait wait = new WebDriverWait(driver.get(), 200);
			clog.info("inside WebDriverWait");
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.get().findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"))));
			org.openqa.selenium.WebElement refreshButton = driver.get()
					.findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"));
			boolean enabled = refreshButton.isEnabled();
			clog.info("RefreshButton is enabled : " + enabled);
			clog.info("attempt no : " + i);
			clog.info("waiting for order status change to pending");
			// driver.get().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			JavascriptExecutor executor = (JavascriptExecutor) driver.get();
			executor.executeScript("arguments[0].click();", refreshButton);
			clog.info("Clicked on refresh");
			try {
				WebDriverWait wait2 = new WebDriverWait(driver.get(), 20);
				wait2.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.get().switchTo().alert();
				alert.accept();

			} catch (Exception ex) {
				System.out.println("No Alerts");
			}
			Result.takescreenshot("After Submit");
			clog.info("Out of First alert");
			// refreshButton.click();
			String statusAttribute = driver.get().findElement(By.xpath(Object1)).getAttribute("value");
			clog.info("Status attribute is : " + statusAttribute);
			clog.info("-----------------------------------------");

			if (statusAttribute.equalsIgnoreCase("Pending")) {
				clog.info("Pending status found");
				Result.takescreenshot("Final status");

				break;

			} else if (statusAttribute.equalsIgnoreCase("Applied")) {
				clog.info("Pending status found");
				Result.takescreenshot("Final status");

				break;
			} else if (statusAttribute.equalsIgnoreCase("Failed")) {
				clog.info("Failed status found");
				Result.takescreenshot("Final status");

				break;
			}
		}

	}

	public static void WaitforStatusChange_Siebel(String objname, String objTyp, String attribute,
			String statusexpected) {

		// driver.get().get(GetCurrentUrl);
		// img[@rn='ExecuteQuery']
		waitTillSiebelLoad();
		String[] objprop = Utlities.FindObject(objname, objTyp);
		String Object1 = objprop[0];

		for (int i = 1; i > 0; i++) {

			WebDriverWait wait = new WebDriverWait(driver.get(), 200);
			clog.info("inside WebDriverWait");
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.get().findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"))));
			org.openqa.selenium.WebElement refreshButton = driver.get()
					.findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"));
			boolean enabled = refreshButton.isEnabled();
			clog.info("RefreshButton is enabled : " + enabled);
			clog.info("attempt no : " + i);
			clog.info("waiting for order status change to pending");
			// driver.get().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			JavascriptExecutor executor = (JavascriptExecutor) driver.get();
			executor.executeScript("arguments[0].click();", refreshButton);
			clog.info("Clicked on refresh");
			try {
				WebDriverWait wait2 = new WebDriverWait(driver.get(), 20);
				wait2.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.get().switchTo().alert();
				alert.accept();

			} catch (Exception ex) {
				System.out.println("No Alerts");
			}
			Result.takescreenshot("After Submit");
			clog.info("Out of First alert");
			// refreshButton.click();
			String statusAttribute = driver.get().findElement(By.xpath(Object1)).getAttribute(attribute);
			clog.info("Status attribute is : " + statusAttribute);
			clog.info("-----------------------------------------");

			if (statusAttribute.equalsIgnoreCase(statusexpected)) {
				clog.info("Pending status found");
				Result.takescreenshot("Final status");

				break;

			} else if (statusAttribute.equalsIgnoreCase("Failed")) {
				clog.info("Failed status found");
				Result.takescreenshot("Final status");

				break;
			}
		}

	}

	public static void WaitForPending_B2C(String objname, int rownum, int columnnum) {
		// driver.get().get(GetCurrentUrl);
		// img[@rn='ExecuteQuery']
		Browser.waitTillSiebelLoad();
		String[] objprop = Utlities.FindObject(objname, "WebTable");
		String cellXpath = objprop[0] + "//tr[" + rownum + "]" + "//td[" + columnnum + "]";

		for (int i = 1; i <= 25; i++) {
			WebDriverWait wait = new WebDriverWait(driver.get(), 500);
			clog.info("inside WebDriverWait");
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.get().findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"))));
			org.openqa.selenium.WebElement refreshButton = driver.get()
					.findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"));
			boolean enabled = refreshButton.isEnabled();
			clog.info("RefreshButton is enabled : " + enabled);
			clog.info("attempt no : " + i);
			clog.info("waiting for order status change to pending");
			// driver.get().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			JavascriptExecutor executor = (JavascriptExecutor) driver.get();
			executor.executeScript("arguments[0].click();", refreshButton);
			clog.info("Clicked on refresh");
			try {
				WebDriverWait wait2 = new WebDriverWait(driver.get(), 20);
				wait2.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.get().switchTo().alert();
				alert.accept();
			} catch (Exception ex) {
				System.out.println("No Alerts");
			}
			Result.takescreenshot("After Submit");
			clog.info("Out of First alert");
			// refreshButton.click();
			// String statusAttribute =
			// driver.get().findElement(By.xpath(Object1)).getAttribute("title");
			String statusAttribute = driver.get().findElement(By.xpath(cellXpath)).getText();
			clog.info("Status is ---->: " + statusAttribute);
			clog.info("-----------------------------------------");

			if (statusAttribute.equalsIgnoreCase("Pending") || statusAttribute.equalsIgnoreCase("Applied")) {
				clog.info("Pending status found");
				Result.takescreenshot("Final status");

				break;
			} else if (statusAttribute.equalsIgnoreCase("Failed")) {
				clog.info("Failed status found");
				Result.takescreenshot("Final status");

				break;
			}
		}
	}

	public static void WaitforPending_B2C(String objname, String objTyp) {

		// driver.get().get(GetCurrentUrl);
		// img[@rn='ExecuteQuery']
		waitTillSiebelLoad();
		String[] objprop = Utlities.FindObject(objname, objTyp);
		String Object1 = objprop[0];

		for (int i = 1; i <= 25; i++) {

			WebDriverWait wait = new WebDriverWait(driver.get(), 500);
			clog.info("inside WebDriverWait");
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.get().findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"))));
			org.openqa.selenium.WebElement refreshButton = driver.get()
					.findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"));
			boolean enabled = refreshButton.isEnabled();
			clog.info("RefreshButton is enabled : " + enabled);
			clog.info("attempt no : " + i);
			clog.info("waiting for order status change to pending");
			// driver.get().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			JavascriptExecutor executor = (JavascriptExecutor) driver.get();
			executor.executeScript("arguments[0].click();", refreshButton);
			clog.info("Clicked on refresh");
			try {
				WebDriverWait wait2 = new WebDriverWait(driver.get(), 20);
				wait2.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.get().switchTo().alert();
				alert.accept();

			} catch (Exception ex) {
				System.out.println("No Alerts");
			}
			Result.takescreenshot("After Submit");
			clog.info("Out of First alert");
			// refreshButton.click();
			String statusAttribute = driver.get().findElement(By.xpath(Object1)).getAttribute("title");
			clog.info("Status attribute is : " + statusAttribute);
			clog.info("-----------------------------------------");

			if (statusAttribute.equalsIgnoreCase("Pending") || statusAttribute.equalsIgnoreCase("Applied")) {
				clog.info("Pending status found");
				Result.takescreenshot("Final status");

				break;

			} else if (statusAttribute.equalsIgnoreCase("Failed")) {
				clog.info("Failed status found");
				Result.takescreenshot("Final status");

				break;
			}
		}

	}

	public static void WaitforPending_B2C1(String objname, String objTyp, String attribute, String status) {

		// driver.get().get(GetCurrentUrl);
		// img[@rn='ExecuteQuery']
		waitTillSiebelLoad();
		String[] objprop = Utlities.FindObject(objname, objTyp);
		String Object1 = objprop[0];

		for (int i = 1; i > 0; i++) {

			WebDriverWait wait = new WebDriverWait(driver.get(), 500);
			clog.info("inside WebDriverWait");
			wait.until(ExpectedConditions.elementToBeClickable(
					driver.get().findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"))));
			org.openqa.selenium.WebElement refreshButton = driver.get()
					.findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"));
			boolean enabled = refreshButton.isEnabled();
			clog.info("RefreshButton is enabled : " + enabled);
			clog.info("attempt no : " + i);
			clog.info("waiting for order status change to pending");
			// driver.get().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			JavascriptExecutor executor = (JavascriptExecutor) driver.get();
			executor.executeScript("arguments[0].click();", refreshButton);
			clog.info("Clicked on refresh");
			try {
				WebDriverWait wait2 = new WebDriverWait(driver.get(), 20);
				wait2.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.get().switchTo().alert();
				alert.accept();

			} catch (Exception ex) {
				System.out.println("No Alerts");
			}
			Result.takescreenshot("After Submit");
			clog.info("Out of First alert");
			// refreshButton.click();
			String statusAttribute = driver.get().findElement(By.xpath(Object1)).getAttribute(attribute);
			clog.info("Status attribute is : " + statusAttribute);
			clog.info("-----------------------------------------");

			if (statusAttribute.equalsIgnoreCase(status)) {
				clog.info("Pending status found");
				Result.takescreenshot("Final status");

				break;

			} else if (statusAttribute.equalsIgnoreCase("Failed")) {
				clog.info("Failed status found");
				Result.takescreenshot("Final status");

				break;
			}
		}

	}

	public static void WaitforPending1(String objname, String objTyp, String attribute, String status) {

		// driver.get().get(GetCurrentUrl);
		// img[@rn='ExecuteQuery']
		waitTillSiebelLoad();
		String[] objprop = Utlities.FindObject(objname, objTyp);
		String Object1 = objprop[0];
		WebDriverWait wait = new WebDriverWait(driver.get(), 200);
		clog.info("inside WebDriverWait");
		org.openqa.selenium.WebElement refreshButton = driver.get()
				.findElement(By.xpath("//div[@id='s_toolbar']/ul/li[@name ='ExecuteQuery']"));
		boolean enabled = refreshButton.isEnabled();
		clog.info("RefreshButton is enabled : " + enabled);
		wait.until(ExpectedConditions.elementToBeClickable(refreshButton));
		for (int i = 1; i >= 1; i++) {
			clog.info("attempt no : " + i);
			clog.info("waiting for order status change to pending");
			// driver.get().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			JavascriptExecutor executor = (JavascriptExecutor) driver.get();
			executor.executeScript("arguments[0].click();", refreshButton);
			clog.info("Clicked on refresh");
			try {
				Browser.alert.accept();

			} catch (Exception ex) {
				System.out.println("No Alerts");
			}
			Result.takescreenshot("After Submit");
			clog.info("Out of First alert");
			// refreshButton.click();
			String statusAttribute = driver.get().findElement(By.xpath(Object1)).getAttribute(attribute);
			clog.info("Status attribute is : " + statusAttribute);

			if (statusAttribute.equalsIgnoreCase(status)) {
				clog.info("Pending status found");
				Result.takescreenshot("Final status");

				break;

			} else if (statusAttribute.equalsIgnoreCase("Failed")) {
				clog.info("Failed status found");
				Result.takescreenshot("Final status");

				break;
			}
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name            : NavbarElement
	 * Use                    : To click objects in Navigation bar in siebel
	 * Designed By            : Gautham Balaji
	 * Last Modified Date     : 24-02-2021
	--------------------------------------------------------------------------------------------------------*/
	/*
	 * public static void NavbarElement(String NavbarLink) throws
	 * InterruptedException { String objtype = "WebLink"; String[] objprop1 =
	 * Utlities.FindObject(NavbarLink, objtype); String Object = objprop1[0];
	 *
	 * int H = 0;
	 *
	 * try { driver.get().findElement(By.xpath("//*[@rn='SiebScreenTabs']")); H = 1;
	 * } catch (NoSuchElementException h) {
	 * driver.get().findElement(By.xpath("//*[@rn='SiebViewJumpTab']")); H = 2; }
	 *
	 * boolean E = driver.get().findElement(By.xpath(Object)).isEnabled();
	 *
	 * if (E = true) { driver.get().findElement(By.xpath(Object)).click(); } else {
	 * if (H == 1) {
	 * driver.get().findElement(By.xpath("//*[@rn='SiebScreenTabs']")).click();
	 * Thread.sleep(1000); driver.get().findElement(By.xpath(Object)).click(); }
	 * else { for (int i = 1; i < 4; i++) {
	 * driver.get().findElement(By.xpath("(//*[@rn='SiebScreenTabs'])[i]")).click();
	 * boolean e = driver.get().findElement(By.xpath(Object)).isEnabled(); if (e ==
	 * true) { break; } }
	 * driver.get().findElement(By.xpath("(//*[@rn='SiebViewJumpTab'])[i]")).click()
	 * ; Thread.sleep(1000); driver.get().findElement(By.xpath(Object)).click(); } }
	 *
	 * }
	 *
	 *
	 *
	 * public static void NavbarElementJ(String NavbarLink) throws
	 * InterruptedException { String objtype = "WebLink"; String[] objprop1 =
	 * Utlities.FindObject(NavbarLink, objtype); String Object = objprop1[0];
	 * JavascriptExecutor js = (JavascriptExecutor) driver.get(); int H = 0;
	 *
	 * try { driver.get().findElement(By.xpath("//*[@rn='SiebScreenTabs']")); H = 1;
	 * } catch (NoSuchElementException h) {
	 * driver.get().findElement(By.xpath("//*[@rn='SiebViewJumpTab']")); H = 2; }
	 *
	 * boolean E = driver.get().findElement(By.xpath(Object)).isEnabled();
	 *
	 * if (E = true) { org.openqa.selenium.WebElement findElement =
	 * driver.get().findElement(By.xpath(Object));
	 * js.executeScript("arguments[0].click();", findElement);
	 *
	 * } else { if (H == 1) { org.openqa.selenium.WebElement Seibscreens =
	 * driver.get() .findElement(By.xpath("//*[@rn='SiebScreenTabs']"));
	 * js.executeScript("arguments[0].click();", Seibscreens); Thread.sleep(1000);
	 * org.openqa.selenium.WebElement findElement =
	 * driver.get().findElement(By.xpath(Object));
	 * js.executeScript("arguments[0].click();", findElement); } else { for (int i =
	 * 1; i < 4; i++) { org.openqa.selenium.WebElement siebScreensTabb =
	 * driver.get() .findElement(By.xpath("(//*[@rn='SiebScreenTabs'])[i]"));
	 * js.executeScript("arguments[0].click();", siebScreensTabb); boolean e =
	 * driver.get().findElement(By.xpath(Object)).isEnabled(); if (e == true) {
	 * break; } } org.openqa.selenium.WebElement siebviewJimpBar = driver.get()
	 * .findElement(By.xpath("(//*[@rn='SiebViewJumpTab'])[i]"));
	 * js.executeScript("arguments[0].click();", siebviewJimpBar);
	 * Thread.sleep(1000); org.openqa.selenium.WebElement findElement =
	 * driver.get().findElement(By.xpath(Object));
	 * js.executeScript("arguments[0].click();", findElement); } } }
	 */

	public static void NavbarElement(String NavbarLink) {
		String objtype = "WebLink";
		String[] objprop1 = Utlities.FindObject(NavbarLink, objtype);
		String Object = objprop1[0];

		int H = 0;

		try {
			driver.get().findElement(By.xpath("//*[@rn='SiebScreenTabs']"));
			H = 1;
		} catch (Exception h) {
			driver.get().findElement(By.xpath("//*[@rn='SiebViewJumpTab']"));
			H = 2;
		}

		try {
			driver.get().findElement(By.xpath(Object)).isEnabled();
			driver.get().findElement(By.xpath(Object)).click();
		} catch (Exception h) {
			if (H == 1) {
				driver.get().findElement(By.xpath("//*[@rn='SiebScreenTabs']")).click();
				driver.get().findElement(By.xpath(Object)).click();
			} else {
				for (int i = 1; i < 4;) {
					try {
						driver.get().findElement(By.xpath("(//*[@rn='SiebViewJumpTab'])[" + i + "]")).click();
						driver.get().findElement(By.xpath(Object)).isEnabled();
						driver.get().findElement(By.xpath(Object)).click();
						break;
					} catch (Exception k) {
						i++;
						if (i <= 4) {
							Continue.set(false);
							break;
						}
					}
				}

			}

		}

	}

	public static void NavbarElementJ(String NavbarLink) {
		String objtype = "WebLink";
		String[] objprop1 = Utlities.FindObject(NavbarLink, objtype);
		String Object = objprop1[0];
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		int H = 0;

		try {
			driver.get().findElement(By.xpath("//*[@rn='SiebScreenTabs']"));
			H = 1;
		} catch (Exception h) {
			driver.get().findElement(By.xpath("//*[@rn='SiebViewJumpTab']"));
			H = 2;
		}

		try {
			driver.get().findElement(By.xpath(Object)).isEnabled();
			org.openqa.selenium.WebElement findElement = driver.get().findElement(By.xpath(Object));
			js.executeScript("arguments[0].click();", findElement);

		} catch (Exception h) {
			if (H == 1) {
				driver.get().findElement(By.xpath("//*[@rn='SiebScreenTabs']")).click();
				org.openqa.selenium.WebElement findElement = driver.get().findElement(By.xpath(Object));
				js.executeScript("arguments[0].click();", findElement);

			} else {
				for (int i = 1; i < 4;) {
					try {
						driver.get().findElement(By.xpath("(//*[@rn='SiebViewJumpTab'])[" + i + "]")).click();
						driver.get().findElement(By.xpath(Object)).isEnabled();
						org.openqa.selenium.WebElement findElement = driver.get().findElement(By.xpath(Object));
						js.executeScript("arguments[0].click();", findElement);
						break;
					} catch (Exception k) {
						i++;
						if (i <= 4) {
							Continue.set(false);
							break;
						}
					}
				}

			}

		}

	}

//	public static String GetRSAToken() throws MalformedURLException, InterruptedException {
//		String Token1 = Utlities.GetRSAToken_u();
//		return Token1;
//	}

	public void waitUntilClickable(By element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {

			throw e;
		}
	}

	public static String CurrentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String CurrentTime = dtf.format(now);
		clog.info(CurrentTime);
		// System.out.println(dtf.format(now));
		return CurrentTime;
	}

	public static String Random_IDGeneration(String ID_Type) {
		String RandomNumber = "";

		if (ID_Type == "Commercial ID") {
			ID_Type = "GCC ID";
		}
		switch (ID_Type) {
		case "GCC ID":
			Date today = new Date();
			DateFormat DATE_FORMAT;
			DATE_FORMAT = new SimpleDateFormat("ddMM");
			String date = DATE_FORMAT.format(today);

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmmss");
			String time = TIME_FORMAT.format(cal.getTime());

			RandomNumber = date + time;
			break;
		// return RandomNumber;

		case "Corporate ID":
			Date today1 = new Date();
			DateFormat DATE_FORMAT1;
			DATE_FORMAT1 = new SimpleDateFormat("ddM");
			String date1 = DATE_FORMAT1.format(today1);

			Calendar cal1 = Calendar.getInstance();
			SimpleDateFormat TIME_FORMAT1 = new SimpleDateFormat("HHmmss");
			String time1 = TIME_FORMAT1.format(cal1.getTime());

			RandomNumber = "7" + date1 + time1;
			// return RandomNumber;
			break;
		default:
			System.out.println("Invalid ID Type");
			break;
		}
		return RandomNumber;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// TAFEELY///////////////////////////////

	public static void Signature(String xpath, int x, int y, int x1, int y1, int x2, int y2, int x3, int y3, int x4,
			int y4, int x5, int y5, int x6, int y6, int x7, int y7, int x8, int y8) {
		try {
			org.openqa.selenium.WebElement signatureWebElement = driver.get().findElement(By.xpath(xpath));
			JavascriptExecutor js = (JavascriptExecutor) driver.get();
			js.executeScript("arguments[0].scrollIntoView(true);", signatureWebElement);
			signatureWebElement.click();
			Actions builder = new Actions(driver.get());
			builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x1, y1).release().build()
					.perform();
			builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x2, y2).release().build()
					.perform();
			builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x3, y3).release().build()
					.perform();
			builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x4, y4).release().build()
					.perform();
			builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x5, y5).release().build()
					.perform();
			builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x6, y6).release().build()
					.perform();
			builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x7, y7).release().build()
					.perform();
			builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x8, y8).release().build()
					.perform();
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			clog.info(e.getMessage());
		}
	}

	public static void webdriver_wait_click(String xpath, int timetowait) {
		try {
			org.openqa.selenium.WebElement element = driver.get().findElement(By.xpath(xpath));
			WebDriverWait wait = new WebDriverWait(driver.get(), timetowait);
			clog.info("inside WebDriverWait");
			// boolean enabled = element.isEnabled();
			// clog.info("button is enabled : "+enabled);
			wait.until(ExpectedConditions.elementToBeClickable(element));

			try {
				// wait.until(ExpectedConditions.visibilityOfElementLocated( element));
				JavascriptExecutor executor = (JavascriptExecutor) driver.get();
				executor.executeScript("arguments[0].click();", element);
			} catch (Exception e) {
				element.click();
			}

			// element.click();
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			clog.info(e.getMessage());
		}
	}

	public static void webdriver_wait_sendKeys(String xpath, String value, int timetowait) {
		try {

			org.openqa.selenium.WebElement element = driver.get().findElement(By.xpath(xpath));
			WebDriverWait wait = new WebDriverWait(driver.get(), timetowait);
			clog.info("inside WebDriverWait");
			// boolean enabled = element.isEnabled();
			// clog.info("button is enabled : "+enabled);
			wait.until(ExpectedConditions.elementToBeClickable(element));

			try {
				// wait.until(ExpectedConditions.visibilityOfElementLocated( element));
				JavascriptExecutor executor = (JavascriptExecutor) driver.get();
				executor.executeScript("arguments[0].click();", element);
				executor.executeScript("arguments[0].setAttribute('value', '" + value + "');", element);
			} catch (Exception e) {
				element.click();
				element.sendKeys(value);
			}

			// element.click();
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			clog.info(e.getMessage());
		}
	}

	public static void taf_Click(String objname) throws Exception {
		Method.screenLive();
		String objtype = "WebButton";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		Method.taf_click(objprop);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void taf_sendkeys(String objname, String value) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		Method.taf_edit(objprop, value);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Send Keys");
			clog.info(" :: Failed at Obj: " + objname + " to  Send Keys");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void taf_Click_J(String objname) throws Exception {
		Method.screenLive();
		String objtype = "WebButton";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		Method.taf_click_J(objprop);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void taf_sendkeys_J(String objname, String value) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		Method.taf_edit_J(objprop, value);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Send Keys");
			clog.info(" :: Failed at Obj: " + objname + " to  Send Keys");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void taf_Click_(String objname) throws Exception {
		Method.screenLive();
		String objtype = "WebButton";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		Method.taf_click_(objprop);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void taf_sendkeys_(String objname, String value) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		Method.taf_edit_(objprop, value);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Send Keys");
			clog.info(" :: Failed at Obj: " + objname + " to Send Keys");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void Dynamic_Click_J_WB(String objname, String obj) throws Exception {
		Method.screenLive();
		String objtype = "WebButton";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.click_Dynamic_J(objprop, obj);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void Dynamic_Click_J_WL(String objname, String obj) throws Exception {
		Method.screenLive();
		String objtype = "WebLink";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.click_Dynamic_J(objprop, obj);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void Dynamic_Click_J_W(String objname, String obj) throws Exception {
		Method.screenLive();
		String objtype = "WebLink";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.click_Dynamic(objprop, obj);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static String DynamicGetText(String objname, String obj) throws Exception {
		Method.screenLive();
		String textValue = null;
		String objtype = "WebTable";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		textValue = Method.getDynamicText(objprop, obj);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
		return textValue;
	}

	public static void Dynamic_sendkeys_J(String objname, String obj, String value) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.edit_Dynamic_J(objprop, obj, value);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Send Keys");
			clog.info(" :: Failed at Obj: " + objname + " to  Send Keys");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void Dynamic_Click_WB(String objname, String obj) throws Exception {
		Method.screenLive();
		String objtype = "WebButton";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.click_dynamic_(objprop, obj);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void Dynamic_Click_WL(String objname, String obj) throws Exception {
		Method.screenLive();
		String objtype = "WebLink";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.click_dynamic_(objprop, obj);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void Dynamic_sendkeys_(String objname, String obj, String value) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.Edit_dynamic_(objprop, obj, value);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Send Keys");
			clog.info(" :: Failed at Obj: " + objname + " to Send Keys");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void taf_signature(String objname, int x, int y, int x1, int y1, int x2, int y2, int x3, int y3,
			int x4, int y4, int x5, int y5, int x6, int y6, int x7, int y7, int x8, int y8) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		Method.taf_sign(objprop, x, y, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname);
			clog.info(" :: Failed at Obj: " + objname);
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void BMS_Module_Bar(String objname, String obj1, String obj2) throws Exception {
		Method.screenLive();
		String objtype = "WebLink";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.bMS_Module_Bar(objprop, obj1, obj2);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void BMS_Module(String objname, String obj1) throws Exception {
		Method.screenLive();
		String objtype = "WebLink";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.bMS_Module_(objprop, obj1);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static String gettext_(String objname) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		return Method.get_text_(objprop);
	}

	public static String getAttribute_WE(String objname, String attributeName) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		String val = Method.get_Attribute_(objprop, attributeName);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname);
			clog.info(" :: Failed at Obj: " + objname);
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
		return val;
	}

	public static String getAttribute_WB(String objname, String attributeName) throws Exception {
		Method.screenLive();
		String objtype = "WebButton";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		String val = Method.get_Attribute_(objprop, attributeName);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname);
			clog.info(" :: Failed at Obj: " + objname);
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
		return val;
	}

	public static String getAttribute_WL(String objname, String attributeName) throws Exception {
		Method.screenLive();
		String objtype = "WebLink";
		String[] objprop = Utlities.FindObject(objname, objtype);
		Method.waittillobjvisible(objprop);
		waitforload();
		String val = Method.get_Attribute_(objprop, attributeName);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname);
			clog.info(" :: Failed at Obj: " + objname);
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
		return val;
	}

	public static boolean exist_WE(String objname) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		return Method.existobj(objprop);
	}

	public static boolean exist_WL(String objname) throws Exception {
		Method.screenLive();
		String objtype = "WebLink";
		String[] objprop = Utlities.FindObject(objname, objtype);
		return Method.existobj(objprop);
	}

	public static boolean exist_WB(String objname) throws Exception {
		Method.screenLive();
		String objtype = "WebButton";
		String[] objprop = Utlities.FindObject(objname, objtype);
		return Method.existobj(objprop);
	}

	public static void staleElementHandle_Button(String objname) throws Exception {
		Method.screenLive();
		String objtype = "WebButton";
		String[] objprop = Utlities.FindObject(objname, objtype);
		String obj = objprop[0];
		int i = 0;
		for (i = 0; i < 20; i++) {
			clog.info("i" + i);
			try {
				driver.get().findElement(By.xpath(obj)).click();
				break;
			} catch (Exception e) {
				clog.info(e.getMessage());

			}
		}
		if (i >= 20) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}

	}

	public static void staleElementHandle_sendkeys(String objname, String value) throws Exception {
		Method.screenLive();
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		String obj = objprop[0];
		int i = 0;
		for (i = 0; i < 20; i++) {
			clog.info("i" + i);
			try {
				driver.get().findElement(By.xpath(obj)).sendKeys(value);
				break;
			} catch (Exception e) {
				clog.info(e.getMessage());
			}
		}
		if (i >= 20) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
		}

	}

	public static String SiebelgetrowID(String settingBTn) throws InterruptedException, AWTException {
		String objtype = "WebButton";
		String[] objprop1 = Utlities.FindObject(settingBTn, objtype);
		// String Object1 = objprop1[0];
		waitforload();
		Method.clicktWithJavaScript(objprop1);
		// Method.clickOffset(objprop1);
		// driver.get().findElement(By.xpath(Object1)).click();
		Thread.sleep(2000);
		clog.info("clicked on setting button");
		//Web.WebLink.clickWithJS(driver.get().findElement(By.xpath("//a[@rn='About Record (SWE)']"));
		driver.get().findElement(By.xpath("//a[@rn='About Record (SWE)']")).click();
		Thread.sleep(2000);
		clog.info("clicked on about Record");
		String getrowid = driver.get().findElement(By.xpath("//div[@aria-label='Row #']")).getText();
		Thread.sleep(2000);
		clog.info("row id copied");
		driver.get().findElement(By.xpath("//button[@rn='CloseButton']")).click();
		clog.info("Clicked on Ok button");
		return getrowid;
	}

	public static void Implicitwait(int timetowait) {

		driver.get().manage().timeouts().implicitlyWait(timetowait, TimeUnit.SECONDS);
	}

	public static void Siebel_Verify_Fingerprint(String xpath) {

		WebDriverWait wait = new WebDriverWait(driver.get(), 100);
		org.openqa.selenium.WebElement findElement = driver.get().findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.elementToBeClickable(findElement));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView();", findElement);
		findElement.click();
	}

	public static void simpleAlert(String info) {

		try {
			WebDriverWait wait = new WebDriverWait(driver.get(), 20);

			clog.info(info);

			wait.until(ExpectedConditions.alertIsPresent());

			Alert alert = driver.get().switchTo().alert();

			alert.accept();

			clog.info("Alert accepted");

		}

		catch (Exception e) {

			clog.info("no alert present");
		}
	}

	public static void Siebel_Verify_Fingerprint_with_Alert(String xpath) {

		WebDriverWait wait = new WebDriverWait(driver.get(), 100);

		org.openqa.selenium.WebElement findElement = driver.get().findElement(By.xpath(xpath));

		simpleAlert("attempt2");

		wait.until(ExpectedConditions.elementToBeClickable(findElement));

		simpleAlert("attempt3");

		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView();", findElement);

		simpleAlert("attempt4");

		findElement.click();

	}

	public static void findElement(String objname, String objectType) throws Exception {
		Method.screenLive();
		String objtype = objectType;
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		Method.findElement(objprop);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static org.openqa.selenium.WebElement findElement_(String objname, String objectType) throws Exception {
		Method.screenLive();
		String objtype = objectType;
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		org.openqa.selenium.WebElement findElement = Method.findElement(objprop);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
		return findElement;
	}

	public static void sendKeys(String objname, String objectType, String data) throws Exception {

		if (data != null) {

			org.openqa.selenium.WebElement ele = null;

			try {
				ele = findElement_(objname, objectType);

				if (ele != null) {

					WebDriverWait wait = new WebDriverWait(driver.get(), 30);
					wait.until(ExpectedConditions.visibilityOf(ele));
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					boolean enabled = ele.isEnabled();
					clog.info(objname + "  is enabled  :  " + enabled);
					JavascriptExecutor js = (JavascriptExecutor) driver.get();
					js.executeScript("arguments[0].scrollIntoView(true);", ele);
					ele.click();
					ele.sendKeys(data);
				}

				else {
					clog.info(objname + "  is not found  ");
					Continue.set(false);
				}

			}

			catch (Exception e) {
				Continue.set(false);
				throw new Exception(data + "unable to send the data " + e.getMessage());

			}

		}

		else {
			Continue.set(false);
			clog.info(" test data value id null ");
		}

	}

	public static void sendKeys2(String objname, String objectType, String data) throws Exception {

		if (data != null) {

			org.openqa.selenium.WebElement ele = null;

			try {
				ele = findElement_(objname, objectType);

				if (ele != null) {

					WebDriverWait wait = new WebDriverWait(driver.get(), 30);
					wait.until(ExpectedConditions.visibilityOf(ele));
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					boolean enabled = ele.isEnabled();
					clog.info(objname + "  is enabled  :  " + enabled);
					JavascriptExecutor js = (JavascriptExecutor) driver.get();
					js.executeScript("arguments[0].scrollIntoView(true);", ele);
					ele.click();
					ele.sendKeys(data);
					ele.clear();
					ele.sendKeys(data);
				}

				else {
					clog.info(objname + "  is not found  ");
					Continue.set(false);
				}

			}

			catch (Exception e) {
				Continue.set(false);
				throw new Exception(data + "unable to send the data " + e.getMessage());

			}

		}

		else {
			Continue.set(false);
			clog.info(" test data value id null ");
		}

	}

	public static void sendKeysJS(String objname, String objectType, String data) throws Exception {

		if (data != null) {

			org.openqa.selenium.WebElement ele = null;

			try {
				ele = findElement_(objname, objectType);

				if (ele != null) {

					WebDriverWait wait = new WebDriverWait(driver.get(), 30);
					wait.until(ExpectedConditions.visibilityOf(ele));
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					boolean enabled = ele.isEnabled();
					clog.info(objname + "  is enabled  :  " + enabled);
					JavascriptExecutor js = (JavascriptExecutor) driver.get();
					js.executeScript("arguments[0].scrollIntoView(true);", ele);
					js.executeScript("arguments[0].click();", ele);
					js.executeScript("arguments[0].setAttribute('value','" + data + "');", ele);
					// js.executeScript("arguments[0].value = '" + data + "'", ele);
					// ele.sendKeys(data);
				}

				else {
					Continue.set(false);
					clog.info(objname + "  is not found  ");

				}

			}

			catch (Exception e) {
				Continue.set(false);
				throw new Exception(data + "unable to send the data " + e.getMessage());

			}

		}

		else {
			Continue.set(false);
			clog.info(" test data value id null ");
		}

	}

	public static void sendKeysJS2(String objname, String objectType, String data) throws Exception {

		if (data != null) {

			org.openqa.selenium.WebElement ele = null;

			try {
				ele = findElement_(objname, objectType);

				if (ele != null) {

					WebDriverWait wait = new WebDriverWait(driver.get(), 30);
					wait.until(ExpectedConditions.visibilityOf(ele));
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					boolean enabled = ele.isEnabled();
					clog.info(objname + "  is enabled  :  " + enabled);
					JavascriptExecutor js = (JavascriptExecutor) driver.get();
					js.executeScript("arguments[0].scrollIntoView(true);", ele);
					js.executeScript("arguments[0].click();", ele);
					js.executeScript("arguments[0].setAttribute('value','" + data + "');", ele);
					js.executeScript("arguments[0].setAttribute('value','');", ele);
					js.executeScript("arguments[0].value = '" + data + "'", ele);
				}

				else {
					Continue.set(false);
					clog.info(objname + "  is not found  ");

				}

			}

			catch (Exception e) {
				Continue.set(false);
				throw new Exception(data + "unable to send the data " + e.getMessage());

			}

		}

		else {
			Continue.set(false);
			clog.info(" test data value id null ");
		}

	}

	public static void clear_And_SendKeys(String objname, String objectType, String data) throws Exception {

		if (data != null) {

			org.openqa.selenium.WebElement ele = null;

			try {
				ele = findElement_(objname, objectType);

				if (ele != null) {

					WebDriverWait wait = new WebDriverWait(driver.get(), 30);
					wait.until(ExpectedConditions.visibilityOf(ele));
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					boolean enabled = ele.isEnabled();
					clog.info(objname + "  is enabled  :  " + enabled);
					JavascriptExecutor js = (JavascriptExecutor) driver.get();
					js.executeScript("arguments[0].scrollIntoView(true);", ele);
					ele.click();
					ele.clear();
					ele.sendKeys(data);
				}

				else {
					Continue.set(false);
					clog.info(objname + "  is not found  ");
				}

			}

			catch (Exception e) {
				Continue.set(false);
				throw new Exception(data + "unable to send the data " + e.getMessage());

			}

		}

		else {
			Continue.set(false);
			clog.info(" test data value id null ");
		}

	}

	public static void clear_And_SendKeysJS(String objname, String objectType, String data) throws Exception {

		if (data != null) {

			org.openqa.selenium.WebElement ele = null;

			try {
				ele = findElement_(objname, objectType);

				if (ele != null) {

					WebDriverWait wait = new WebDriverWait(driver.get(), 30);
					wait.until(ExpectedConditions.visibilityOf(ele));
					wait.until(ExpectedConditions.elementToBeClickable(ele));
					boolean enabled = ele.isEnabled();
					clog.info(objname + "  is enabled  :  " + enabled);
					JavascriptExecutor js = (JavascriptExecutor) driver.get();
					js.executeScript("arguments[0].scrollIntoView(true);", ele);
					js.executeScript("arguments[0].click();", ele);
					js.executeScript("arguments[0].setAttribute('value','');", ele);
					// js.executeScript("arguments[0].setAttribute('value','" + data + "');", ele);
					js.executeScript("arguments[0].value = '" + data + "'", ele);
				}

				else {
					Continue.set(false);
					clog.info(objname + "  is not found  ");
				}

			}

			catch (Exception e) {
				Continue.set(false);
				throw new Exception(data + "unable to send the data " + e.getMessage());

			}

		}

		else {
			Continue.set(false);
			clog.info(" test data value id null ");
		}

	}

	public static void click_(String objname, String objectType) throws Exception {

		org.openqa.selenium.WebElement ele = null;

		try {
			ele = findElement_(objname, objectType);

			if (ele != null) {

				WebDriverWait wait = new WebDriverWait(driver.get(), 30);
				wait.until(ExpectedConditions.visibilityOf(ele));
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				boolean enabled = ele.isEnabled();
				clog.info(objname + "  is enabled  :  " + enabled);
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				ele.click();

			}

			else {
				Continue.set(false);
				clog.info(objname + "  is not found  ");
			}

		}

		catch (Exception e) {
			Continue.set(false);
			throw new Exception(ele + "unable to click " + e.getMessage());

		}

	}

	public static void click_JS(String objname, String objectType) throws Exception {

		org.openqa.selenium.WebElement ele = null;

		try {
			ele = findElement_(objname, objectType);

			if (ele != null) {

				WebDriverWait wait = new WebDriverWait(driver.get(), 30);
				wait.until(ExpectedConditions.visibilityOf(ele));
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				boolean enabled = ele.isEnabled();
				clog.info(objname + "  is enabled  :  " + enabled);
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				js.executeScript("arguments[0].click();", ele);

			}

			else {
				Continue.set(false);
				clog.info(objname + "  is not found  ");
			}

		}

		catch (Exception e) {
			Continue.set(false);
			throw new Exception(ele + " unable to click " + e.getMessage());

		}

	}

	public static void Actions_MouseHover(String objname, String objectType) throws Exception {

		org.openqa.selenium.WebElement ele = null;

		try {
			ele = findElement_(objname, objectType);

			if (ele != null) {

				WebDriverWait wait = new WebDriverWait(driver.get(), 30);
				wait.until(ExpectedConditions.visibilityOf(ele));
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				boolean enabled = ele.isEnabled();
				clog.info(objname + "  is enabled  :  " + enabled);
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				Actions action = new Actions(driver.get());
				Actions moveToElement = action.moveToElement(ele);
				moveToElement.perform();

			}

			else {
				Continue.set(false);
				clog.info(objname + "  is not found  ");
			}

		}

		catch (Exception e) {
			Continue.set(false);
			throw new Exception(ele + " unable to move to element " + e.getMessage());

		}

	}

	public static boolean Element_Enabled(String objname, String objectType) throws Exception {

		boolean enabled = false;
		org.openqa.selenium.WebElement ele = null;

		try {
			ele = findElement_(objname, objectType);

			if (ele != null) {

				WebDriverWait wait = new WebDriverWait(driver.get(), 30);
				wait.until(ExpectedConditions.visibilityOf(ele));
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				enabled = ele.isEnabled();
				clog.info(objname + "  is enabled  :  " + enabled);
			}

			else {
				Continue.set(false);
				clog.info(objname + "  is not found  ");
			}

		}

		catch (Exception e) {
			Continue.set(false);
			throw new Exception(ele + " unable to click " + e.getMessage());

		}
		return enabled;

	}

	public static boolean Element_Displayed(String objname, String objectType) throws Exception {

		boolean displayed = false;
		org.openqa.selenium.WebElement ele = null;

		try {
			ele = findElement_(objname, objectType);

			if (ele != null) {

				WebDriverWait wait = new WebDriverWait(driver.get(), 30);
				wait.until(ExpectedConditions.visibilityOf(ele));
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				displayed = ele.isDisplayed();
				clog.info(objname + "  is displayed  :  " + displayed);
			}

			else {
				Continue.set(false);
				clog.info(objname + "  is not found  ");
			}

		}

		catch (Exception e) {
			Continue.set(false);
			throw new Exception(ele + " unable to click " + e.getMessage());

		}
		return displayed;

	}

	public static void Element_Scroll(String objname, String objectType, String scroll) throws Exception {

		org.openqa.selenium.WebElement ele = null;

		try {
			ele = findElement_(objname, objectType);

			if (ele != null) {

				WebDriverWait wait = new WebDriverWait(driver.get(), 30);
				wait.until(ExpectedConditions.visibilityOf(ele));
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(" + scroll + ");", ele);
			}

			else {
				Continue.set(false);
				clog.info(objname + "  is not found  ");
			}

		}

		catch (Exception e) {
			Continue.set(false);
			throw new Exception(ele + " unable to click " + e.getMessage());

		}

	}

	public static String getText_a(String objname, String objectType) throws Exception {

		String text = null;
		org.openqa.selenium.WebElement ele = null;

		try {
			ele = findElement_(objname, objectType);

			if (ele != null) {

				WebDriverWait wait = new WebDriverWait(driver.get(), 30);
				wait.until(ExpectedConditions.visibilityOf(ele));
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				text = ele.getText();
			}

			else {
				Continue.set(false);
				clog.info(objname + "  is not found  ");
			}

		}

		catch (Exception e) {
			Continue.set(false);
			throw new Exception(ele + " unable to click " + e.getMessage());

		}
		return text;

	}

	public static String getAttribute_a(String objname, String objectType, String Attribute) throws Exception {

		String text = null;
		org.openqa.selenium.WebElement ele = null;

		try {
			ele = findElement_(objname, objectType);

			if (ele != null) {

				WebDriverWait wait = new WebDriverWait(driver.get(), 30);
				wait.until(ExpectedConditions.visibilityOf(ele));
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
				text = ele.getAttribute(Attribute);
			}

			else {
				Continue.set(false);
				clog.info(objname + "  is not found  ");
			}

		}

		catch (Exception e) {
			Continue.set(false);
			throw new Exception(ele + " unable to click " + e.getMessage());

		}
		return text;

	}

	public static boolean Element_Display(String xpath) {
		boolean displayed = false;
		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		displayed = ele.isDisplayed();
		return displayed;

	}

	public static boolean Element_Enabled(String xpath) {
		boolean enabled = false;
		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		enabled = ele.isEnabled();
		return enabled;

	}

	public static void Element_SendKeys(String xpath, String data) {

		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.click();
		ele.sendKeys(data);

	}

	public static void Element_SendKeys_JS(String xpath, String data) {

		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		js.executeScript("arguments[0].click();", ele);
		js.executeScript("arguments[0].setAttribute('value','" + data + "');", ele);

	}

	public static void Element_SendKeys_(String xpath, String data) {

		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		// ele.click();
		ele.sendKeys(data);

	}

	public static void Element_SendKeys_JS_(String xpath, String data) {

		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		// js.executeScript("arguments[0].click();", ele);
		js.executeScript("arguments[0].setAttribute('value','" + data + "');", ele);

	}

	public static void Element_Click_(String xpath, String data) {

		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		ele.click();

	}

	public static void Element_Click_(String objname) throws AWTException {

		Method.screenLive();
		String[] objprop = Utlities.FindObject(objname, "WebEdit");
		String Xpath = objprop[0];
		org.openqa.selenium.WebElement element = driver.get().findElement(By.xpath(Xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();

	}

	public static void Element_click_JS(String xpath, String data) {

		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		js.executeScript("arguments[0].click();", ele);

	}

	public static String Element_getText(String xpath) {
		String text = null;

		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		text = ele.getText();

		return text;

	}

	public static String Element_getAttribute(String xpath, String AttributeName) {

		String attribute;

		org.openqa.selenium.WebElement ele = driver.get().findElement(By.xpath(xpath));
		WebDriverWait wait = new WebDriverWait(driver.get(), 30);
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		attribute = ele.getAttribute(AttributeName);

		return attribute;

	}

	public static void pressEnter(String objname, String objTyp) throws InterruptedException {

		String[] objprop = Utlities.FindObject(objname, objTyp);
		String Object = objprop[0];
		WebDriverWait wait = new WebDriverWait(driver.get(), 100);
		// clog.info("inside WebDriverWait");
		org.openqa.selenium.WebElement PressEnter_Element = driver.get().findElement(By.xpath(Object));
		wait.until(ExpectedConditions.visibilityOf(PressEnter_Element));

		boolean enabled = PressEnter_Element.isEnabled();
		clog.info("Element is enabled : " + enabled);
		wait.until(ExpectedConditions.elementToBeClickable(PressEnter_Element));
		PressEnter_Element.sendKeys(Keys.ENTER);

		// driver.get().findElement(By.xpath(Object)).sendKeys(Keys.ENTER);
	}

	public static void closeChildWindow() {
		try {
			clog.info("Waiting for child window");
			WebDriverWait wait1 = new WebDriverWait(driver.get(), 80);
			wait1.until(ExpectedConditions.numberOfWindowsToBe(2));
			clog.info("Waiting for windows");
			Set<String> windowHandles1 = driver.get().getWindowHandles();

			int size1 = driver.get().getWindowHandles().size();

			ArrayList<String> list1 = new ArrayList<String>();
			list1.addAll(windowHandles1);
			String ParentWindow1 = list1.get(0);

			clog.info("Windows Size " + size1);

			if (size1 == 1) {
				driver.get().switchTo().window(ParentWindow1);
			} else {
				String ChildWindow1 = list1.get(1);
				driver.get().switchTo().window(ChildWindow1);
				driver.get().close();
				driver.get().switchTo().window(ParentWindow1);
			}
		} catch (Exception e) {
			clog.info("No Window");
			e.printStackTrace();
		}
	}

	public static Boolean findSpecial(String inputString) {
		Boolean containsSpecial = false;
		String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
		for (int i = 0; i < inputString.length(); i++) {
			char ch = inputString.charAt(i);
			if (specialCharactersString.contains(Character.toString(ch))) {
				clog.info(inputString + " contains special character");
				// System.out.println(inputString+ " contains special character");
				containsSpecial = true;
				break;
			} else if (i == inputString.length() - 1) {
				clog.info(inputString + " does NOT contain special character");
				// System.out.println(inputString+ " does NOT contain special character");
				containsSpecial = false;
			}
		}
		return containsSpecial;
	}

	public static Boolean findSpecial(String inputString, String special) {
		Boolean containsSpecial = false;
		String specialCharactersString = special;
		// String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
		for (int i = 0; i < inputString.length(); i++) {
			char ch = inputString.charAt(i);
			if (specialCharactersString.contains(Character.toString(ch))) {
				clog.info(inputString + " contains special character");
				// System.out.println(inputString+ " contains special character");
				containsSpecial = true;
				break;
			} else if (i == inputString.length() - 1) {
				clog.info(inputString + " does NOT contain special character");
				// System.out.println(inputString+ " does NOT contain special character");
				containsSpecial = false;
			}
		}
		return containsSpecial;
	}

	public static boolean isEnabled(String type, String objname, String value) throws Exception {
		Method.screenLive();
		boolean obj = false;
		String objtype = type;
		String[] objprop = Utlities.FindObject(objname, objtype);
		waitforload();
		obj = Method.checkDynamicEnabled(objprop, value);
		if (Continue.get() == false) {
			Result.takescreenshot(" :: Failed at Obj: " + objname + " disabled");
			clog.info(" :: Failed at Obj: " + objname + " disabled");
			throw new Exception();
		} else {

			clog.info(" :: Enabled: " + objname);
		}
		return obj;
	}

	public static boolean checkEnable(String type, String objname) throws Exception {
		Method.screenLive();
		boolean obj = false;
		String objtype = type;
		String[] objprop = Utlities.FindObject(objname, objtype);
		// waitforload();
		obj = Method.waitCheckEnable(objprop);
		if (Continue.get() == false) {
			Result.takescreenshot(" :: Failed at Obj: " + objname + " disabled");
			clog.info(" :: Failed at Obj: " + objname + " disabled");
			throw new Exception();
		} else {

			clog.info(" :: Enabled: " + objname);
		}
		return obj;
	}

	public static boolean checkEnabled(String type, String objname) throws Exception {
		Method.screenLive();
		boolean obj = false;
		String objtype = type;
		String[] objprop = Utlities.FindObject(objname, objtype);
		// waitforload();
		obj = Method.waitCheckEnable(objprop[0]);

		if (Continue.get() == false) {
			Result.takescreenshot(" :: Failed at Obj: " + objname + " disabled");
			clog.info(" :: Failed at Obj: " + objname + " disabled");
			throw new Exception();
		} else {

			clog.info(" :: Enabled: " + objname);
		}

		return obj;
	}

	public static boolean checkDisplayed(String type, String objname) throws Exception {
		Method.screenLive();
		boolean obj = false;
		String objtype = type;
		String[] objprop = Utlities.FindObject(objname, objtype);
		// waitforload();
		obj = Method.waitCheckDisplayed(objprop[0]);

		if (Continue.get() == false) {
			Result.takescreenshot(" :: Failed at Obj: " + objname + " not displayed");
			clog.info(" :: Failed at Obj: " + objname + " not displayed");
			throw new Exception();
		} else {

			clog.info(" :: displayed: " + objname);
		}

		return obj;
	}

	public static int check_Availability_Of_Element(String objname, String objectType) throws Exception {
		String xPath = xPath(objname, objectType);
		clog.info(xPath);
		List<org.openqa.selenium.WebElement> elements = driver.get().findElements(By.xpath(xPath));
		int size = elements.size();
		return size;
	}

	public static void clickOf(String objname, String obj) throws Exception {
		Method.screenLive();
		String objtype = "WebButton";
		String[] objprop = Utlities.FindObject(objname, objtype);
		String object = objprop[0].replace("value", obj);
		clog.info("xpath is : " + object);
		Method.clickOffset(object);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	public static void file_Upload(String xpath, String filepath) {

		org.openqa.selenium.WebElement addFile = driver.get().findElement(By.xpath(xpath));
		((RemoteWebElement) addFile).setFileDetector(new LocalFileDetector());
		addFile.sendKeys(filepath);

	}

	public static String xPath(String objname, String objectType) throws Exception {
		Method.screenLive();
		String objtype = objectType;
		String[] objprop = Utlities.FindObject(objname, objtype);
		String xpath = objprop[0];
		return xpath;
	}

	public static String dynamicGetText(String objname, String obj) throws Exception {
		Method.screenLive();
		String textValue = null;
		String objtype = "WebEdit";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
		waitforload();
		textValue = Method.getDynamicText(objprop, obj);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to Click");
			clog.info(" :: Failed at Obj: " + objname + " to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: " + objname);
		}
		return textValue;
	}

	public static void openMailnator(String URL ){

		env.set("Web");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		clog.info("Video Name: " + runid.get() + "_" + TC_Id.get() + "_" + Usecase.get() + "_" + dtf.format(now));
		System.out.println(
		"Video Name: " + runid.get() + "_" + TC_Id.get() + "_" + Usecase.get() + "_" + dtf.format(now));
		String huburl = "http://localhost:4444/wd/hub";
		DesiredCapabilities cap = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--proxy-server=http://proxy.prod.mobily.lan");

	    //Proxy proxy = new Proxy();
	//proxy.setHttpProxy("http://proxy.prod.mobily.lan:8080");
	//proxy.setSslProxy("http://proxy.prod.mobily.lan:8080");
	    //cap.setCapability("proxy", proxy);

		if (user_id.get().equalsIgnoreCase("72")) {
		huburl = "http://10.14.77.32:4444/wd/hub/";
		} else if (user_id.get().equalsIgnoreCase("67")) {

		 huburl = "http://10.14.77.34:4444/wd/hub/";

		 } else if (user_id.get().equalsIgnoreCase("92")) {

		 huburl = "http://10.14.77.28:4444/wd/hub/";

		 } else if (user_id.get().equalsIgnoreCase("61")) {
		huburl = "http://10.14.77.30:4444/wd/hub/";

		 } else if (user_id.get().equalsIgnoreCase("94")) {
		huburl = "http://localhost:4444/wd/hub/";
		cap.setVersion("94local");

		 }
		else if (user_id.get().equalsIgnoreCase("63")) {

		 huburl = "http://10.14.77.30:4444/wd/hub/";

		 } else if (user_id.get().equalsIgnoreCase("1")) {
		// cap.setVersion("SLBW020");
		huburl = "http://10.14.77.9:4444/wd/hub/";

		 } else if (user_id.get().equalsIgnoreCase("57")) {
		// cap.setVersion("SLBW020");
		huburl = "http://10.14.77.9:4444/wd/hub/";

		 }
		else if (user_id.get().equalsIgnoreCase("78")) {
		// cap.setVersion("SLBW020");
		huburl = "http://10.14.43.228:4444/wd/hub/";

		 }else if (user_id.get().equalsIgnoreCase("81")) {
		// cap.setVersion("SLBW020");
		huburl = "http://10.14.43.228:4444/wd/hub/";

		 }else if (user_id.get().equalsIgnoreCase("79")) {
		// cap.setVersion("SLBW020");
		huburl = "http://10.14.43.229:4444/wd/hub/";

		 }else if (user_id.get().equalsIgnoreCase("80")) {
		// cap.setVersion("SLBW020");
		huburl = "http://10.14.43.230:4444/wd/hub/";

		 }else if (user_id.get().equalsIgnoreCase("86")) {
		// cap.setVersion("SLBW020");
		huburl = "http://10.14.43.231:4444/wd/hub/";

		 }else if (user_id.get().equalsIgnoreCase("87")) {
		// cap.setVersion("SLBW020");
		huburl = "http://10.14.43.232:4444/wd/hub/";

		 }else {
		// ChromeOptions options = new ChromeOptions();
		// huburl = "http://10.14.77.34:4444/wd/hub/";
		huburl = "http://10.14.77.28:4444/wd/hub/";
		}
		// cap.setCapability(ChromeOptions.CAPABILITY, options);
		try {
		driver.set(new RemoteWebDriver(new URL(huburl), options));
		} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		driver.get().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get().get(URL);
		System.out.println("Title " + driver.get().getTitle());
		}

}