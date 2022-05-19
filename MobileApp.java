package Libraries;

import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MobileApp extends Driver {

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and click or touch
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void touch(String objname) throws Exception {
		String objtype = "MobileElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getMobileElement(objprop);
		waitUntilClickable(mElement);
		mElement.click();
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to touch ::" + objname);
			clog.info(" Unable to touch :: " + objname);
			throw new Exception();
		} else {
			clog.info(" clicked sucessfully  ::" + objname);
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and click or touch
	 * Designed By			: SS
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/
	  public static void touchaction(String objname) throws Exception {
		  String objtype = "MobileElement";
		  String[] objprop = Utlities.FindObject(objname, objtype);
		  MobileElement mElement = getMobileElement(objprop);
		  //IOSElement mElement = getiOSMobileElement(objprop);
		  new TouchAction((PerformsTouchActions) driver.get()).tap(point(mElement.getCenter())).perform().release();
		  if (Continue.get() == false) {
		  Result.takescreenshot(" Unable to touch ::" + objname);
		  clog.info(" Unable to touch :: " + objname);
		  throw new Exception();
		  } else {
		  clog.info(" clicked sucessfully ::" + objname);
		  }
		  }
		/*---------------------------------------------------------------------------------------------------------
		 * Class Name			: MobileApp
		 * Use 					: Scroll Up using java script
		 * Designed By			: SS
		 * Last Modified Date 	: 04-March-2020
		--------------------------------------------------------------------------------------------------------*/

	  public static void scrollUp() {

			try {
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				HashMap scrollObject = new HashMap();
				scrollObject.put("direction", "up");
				js.executeScript("mobile:scroll", scrollObject);

			} catch (TimeoutException e) {

				Continue.set(false);
			}

		}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to iOS element and click or touch
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void touchiOS(String objname) throws Exception {
		String objtype = "IOSElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		IOSElement mElement =  getiOSMobileElement(objprop);
		waitUntilClickable(mElement);
		mElement.click();
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to touch ::" + objname);
			clog.info(" Unable to touch :: " + objname);
			throw new Exception();
		} else {
			clog.info(" clicked sucessfully  ::" + objname);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and click or touch
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void touchD(String objname) throws Exception {
		String objtype = "MobileElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getMobileElement(objprop);
		//waitUntillVisible(mElement);
		mElement.click();
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to touch ::" + objname);
			clog.info(" Unable to touch :: " + objname);
			throw new Exception();
		} else {
			clog.info(" clicked sucessfully  ::" + objname);
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to dynamic mobile element and click or touch
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void touch(String objname,String value) throws Exception {
		String objtype = "MobileElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getDynamicMobileElement(objprop,value);
		waitUntilClickable(mElement);
		mElement.click();
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to touch ::" + objname);
			clog.info(" Unable to touch :: " + objname);
			throw new Exception();
		} else {
			clog.info(" clicked sucessfully  ::" + objname);
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and double click or touch
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void doubleTouch(String objname) throws Exception {
		String objtype = "MobileElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getMobileElement(objprop);
		waitUntilClickable(mElement);
		doubleClick(mElement);
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to touch ::" + objname);
			clog.info(" Unable to touch :: " + objname);
			throw new Exception();
		} else {
			clog.info(" clicked sucessfully  ::" + objname);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and get text
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static String readValue(String objname) throws Exception {
		String value = null;
		String objtype = "MobileElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getMobileElement(objprop);
		waitUntillVisible(mElement);
		value = mElement.getText();
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to read ::" + objname);
			clog.info(" Unable to read :: " + objname);
			throw new Exception();
		} else {
			clog.info(objname + " sucessfully value readed ::" + value);
		}
		return value;
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and get text
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static String readValue(String objname,String value1) throws Exception {
		String value = null;
		String objtype = "MobileElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getDynamicMobileElement(objprop,value1);
		waitUntillVisible(mElement);
	//	moveToElement(mElement);
		value = mElement.getText();
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to read ::" + objname);
			clog.info(" Unable to read :: " + objname);
			throw new Exception();
		} else {
			clog.info(objname + " sucessfully value readed ::" + value);
		}
		return value;
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: scrollDown to the Mobile App
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void scrollDown() {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver.get();
			HashMap scrollObject = new HashMap();
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);

		} catch (TimeoutException e) {

			Continue.set(false);
		}

	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and enter the value without click the element
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void enterText(String objname, String objvalue) throws Exception {
		String objtype = "MobileElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getMobileElement(objprop);
		waitUntilClickable(mElement);
		mElement.sendKeys(objvalue);
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to Enter :: " + objvalue + "on :: " + objname);
			clog.info(" Unable to Enter :: " + objvalue + "on :: " + objname);
			throw new Exception();
		} else {
			clog.info(objvalue + " Sucessfully Entered on:: " + objname);
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and enter the value without click the element
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void clearEnterText(String objname, String objvalue) throws Exception {
		String objtype = "MobileElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getMobileElement(objprop);
		waitUntilClickable(mElement);
		mElement.clear();
		mElement.sendKeys(objvalue);
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to Enter :: " + objvalue + "on :: " + objname);
			clog.info(" Unable to Enter :: " + objvalue + "on :: " + objname);
			throw new Exception();
		} else {
			clog.info(objvalue + " Sucessfully Entered on:: " + objname);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element click the element and enter the value
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void touchEnterText(String objname, String objvalue) throws Exception {
		String objtype = "MobileElement";
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getMobileElement(objprop);
		waitUntilClickable(mElement);
		mElement.click();
		mElement.sendKeys(objvalue);
		if (Continue.get() == false) {
			Result.takescreenshot(" Unable to Enter :: " + objvalue + "on :: " + objname);
			clog.info(" Unable to Enter :: " + objvalue + "on :: " + objname);
			throw new Exception();
		} else {
			clog.info(objvalue + " Sucessfully Entered on:: " + objname);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Wait Until mobile element to click state
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void waitUntilClickable(MobileElement element) {

		try {
			clog.info(" element clickable sucessfully  ::" );
			MobileElement temp = (MobileElement) new WebDriverWait(driver.get(), waitMTimeOut)
					.until(ExpectedConditions.elementToBeClickable(element));
		} catch (TimeoutException e) {
			clog.info(" element not clickable sucessfully  ::" );
			Continue.set(false);

		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Wait Until mobile element to visible state
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void waitUntillVisible(MobileElement element) {

		try {
			clog.info(" element clickable sucessfully  ::" );
			MobileElement temp = (MobileElement) new WebDriverWait(driver.get(), waitMTimeOut)
					.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			clog.info(" element not clickable sucessfully  ::" );
			Continue.set(false);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Touch the the mobile element using action class
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	protected void clickAction(MobileElement element) {

		waitUntilClickable(element);
		new Actions(driver.get()).moveToElement(element).click().perform();

	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Touch the the mobile element using action class
	 * Designed By			: SS
	 * Last Modified Date 	: 04-March-2022
	--------------------------------------------------------------------------------------------------------*/

//	protected void touchAction(PointOption x,ElementOption y) {
//
//		try {
//			AndroidDriver ad=(AndroidDriver) driver.get();
//		TouchAction touchAction=new TouchAction(ad);
//
//		touchAction.tap(x,y).perform();
//
//		} catch (TimeoutException e) {
//
//			Continue.set(false);
//		}
//	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: double the the mobile element using JavascriptExecutor
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void doubleClick(MobileElement element) {

		((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView();", element);
		Actions action = new Actions(driver.get()).doubleClick(element);
		action.build().perform();
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: validate the mobile element is in disabled status
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static Boolean checkDisabled(MobileElement element) throws Exception {
		((RemoteWebDriver) driver.get()).executeScript("arguments[0].scrollIntoView(true)", element);
		if (element.isDisplayed() & ((element.getAttribute("readonly")).equals("readonly"))) {

			return true;
		} else {

			return false;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: scroll the mobile application vertically
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void verticalScroll() {
		try {
			Thread.sleep(3000);
			Dimension size = driver.get().manage().window().getSize();
			int anchor = (int) (size.width * 0.5);
			int startPoint = (int) (size.height * 0.5);
			int endPoint = (int) (size.height * 0.1);
			new TouchAction((PerformsTouchActions) driver.get()).press(PointOption.point(anchor, startPoint))
					.waitAction().moveTo(PointOption.point(anchor, endPoint)).release().perform();

		} catch (Exception e) {
			System.out.print(e);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: scroll the mobile application vertically on left side
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void verticalScrollAnchorLeft() {
		try {
			Thread.sleep(3000);
			Dimension size = driver.get().manage().window().getSize();
			int anchor = (int) (size.width * 0.1);
			int startPoint = (int) (size.height * 0.5);
			int endPoint = (int) (size.height * 0.1);
			new TouchAction((PerformsTouchActions) driver.get()).press(PointOption.point(anchor, startPoint))
					.waitAction().moveTo(PointOption.point(anchor, endPoint)).release().perform();
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: scroll the mobile application horizontal
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void horizontalScroll(MobileElement element) {

		Dimension size = driver.get().manage().window().getSize();
		int anchor = (element.getCenter().getY());
		int startPoint = (int) (size.width * 0.6);
		int endPoint = (int) (size.width * 0.1);

		new TouchAction((PerformsTouchActions) driver.get()).press(PointOption.point(startPoint, anchor))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500)))
				.moveTo(PointOption.point(endPoint, anchor)).release().perform();
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and check enable
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static Boolean isEnable(String objname) throws Exception {
		String objtype = "MobileElement";
		Boolean elementIs = false;
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getMobileElement(objprop);
		if (mElement.isEnabled()) {
			elementIs=true;
			Continue.set(true);
		} else {
			elementIs=false;
			Continue.set(false);
		}
		return elementIs;
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: to press back button of device
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static void goBack() throws Exception {
		 driver.get().navigate().back();
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and check display
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static Boolean isDisplay(String objname) throws Exception {
		String objtype = "MobileElement";
		Boolean elementIs = false;
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getMobileElement(objprop);
		if (mElement.isDisplayed()) {
			elementIs=true;
			Continue.set(true);
		} else {
			elementIs=false;
			Continue.set(false);
		}
		return elementIs;
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string in to mobile element and check display
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	public static Boolean isDisplay(String objname,String value) throws Exception {
		String objtype = "MobileElement";
		Boolean elementIs = false;
		String[] objprop = Utlities.FindObject(objname, objtype);
		MobileElement mElement = getDynamicMobileElement(objprop,value);
		if (mElement.isDisplayed()) {
			elementIs=true;
			Continue.set(true);
		} else {
			elementIs=false;
			Continue.set(false);
		}
		return elementIs;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string into mobile element and get mobile element
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/
	public static MobileElement getMobileElement(String[] identifier) {

		MobileElement element = null;
		try {
			if (Continue.get()) {
				if (!identifier[0].equals("")) {
					element = (MobileElement) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(identifier[0])));
					clog.info("xpath --> " + identifier[0] + "retrived successfully");
				} else if (!identifier[1].equals("")) {
					element = (MobileElement) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.name(identifier[1])));
					clog.info("name --> " + identifier[1] + "retrived successfully");
				} else if (!identifier[2].equals("")) {
					element = (MobileElement) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(identifier[2])));
					clog.info("id --> " + identifier[2] + "retrived successfully");
				} else if (!identifier[3].equals("")) {
					element = (MobileElement) new WebDriverWait(driver.get(), waitMTimeOut).until(
							ExpectedConditions.presenceOfElementLocated(MobileBy.className(identifier[3])));
					clog.info("className --> " + identifier[3] + "retrived successfully");
				} else if (!identifier[4].equals("")) {
					element = (MobileElement) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.linkText(identifier[4])));
					clog.info("linkText --> " + identifier[4] + "retrived successfully");
				}
			}

		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return element;

	}


	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string into IOSElement and return
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

   public static IOSElement getiOSMobileElement(String[] identifier) {

	IOSElement element = null;
		try {
			if (Continue.get()) {
				if (!identifier[0].equals("")) {
					element = (IOSElement) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(identifier[0])));
					clog.info("xpath --> " + identifier[0] + "retrived successfully");
				} else if (!identifier[1].equals("")) {
					element = (IOSElement) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.name(identifier[1])));
					clog.info("name --> " + identifier[1] + "retrived successfully");
				} else if (!identifier[2].equals("")) {
					element = (IOSElement) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(identifier[2])));
					clog.info("id --> " + identifier[2] + "retrived successfully");
				} else if (!identifier[3].equals("")) {
					element = (IOSElement) new WebDriverWait(driver.get(), waitMTimeOut).until(
							ExpectedConditions.presenceOfElementLocated(MobileBy.className(identifier[3])));
					clog.info("className --> " + identifier[3] + "retrived successfully");
				} else if (!identifier[4].equals("")) {
					element = (IOSElement) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.linkText(identifier[4])));
					clog.info("linkText --> " + identifier[4] + "retrived successfully");
				}
			}

		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return element;

	}


	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string into list mobile element and get list mobile element
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

@SuppressWarnings("unchecked")
public static List<MobileElement> getListMobileElement(String[] identifier) {

	List<MobileElement> element = null;
		try {
			if (Continue.get()) {
				if (!identifier[0].equals("")) {
					element = (List<MobileElement>) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(identifier[0])));

					clog.info("xpath --> " + identifier[0] + "retrived successfully");
				} else if (!identifier[1].equals("")) {
					element = (List<MobileElement>) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.name(identifier[1])));
                   clog.info("name --> " + identifier[1] + "retrived successfully");
				} else if (!identifier[2].equals("")) {
					element = (List<MobileElement>) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(identifier[2])));
					clog.info("id --> " + identifier[2] + "retrived successfully");
				} else if (!identifier[3].equals("")) {
					element = (List<MobileElement>) new WebDriverWait(driver.get(), waitMTimeOut).until(
							ExpectedConditions.presenceOfElementLocated(MobileBy.className(identifier[3])));
					clog.info("className --> " + identifier[3] + "retrived successfully");
				} else if (!identifier[4].equals("")) {
					element = (List<MobileElement>) new WebDriverWait(driver.get(), waitMTimeOut)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.linkText(identifier[4])));
					clog.info("linkText --> " + identifier[4] + "retrived successfully");
				}
			}

		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return element;

	}


	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: Convert string into dynamic mobile element and get mobile element
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/


public static MobileElement getDynamicMobileElement(String[] identifier,String value) {

	MobileElement element = null;
	try {
		if (Continue.get()) {
			if (!identifier[0].equals("")) {
				  String object=identifier[0].replace("value", value);
				clog.info("xpath is : " + object);
				element = (MobileElement) new WebDriverWait(driver.get(), waitTimeOut)
						.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(object)));
								clog.info("xpath --> " + object + "retrived successfully");
			}
		}

	} catch (Exception e) {
		Continue.set(false);
		clog.error(Result.getStackMsg(e));
	}
	return element;

}

/*---------------------------------------------------------------------------------------------------------
 * Class Name			: MobileApp
 * Use 					: Convert string into dynamic iOS element and get iOS element
 * Designed By			: JJ
 * Last Modified Date 	: 04-March-2020
--------------------------------------------------------------------------------------------------------*/


public static IOSElement getDynamiciOSElement(String[] identifier,String value) {

	IOSElement element = null;
try {
	if (Continue.get()) {
		if (!identifier[0].equals("")) {
			  String object=identifier[0].replace("value", value);
			clog.info("xpath is : " + object);
			element = (IOSElement) new WebDriverWait(driver.get(), waitTimeOut)
					.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(object)));
							clog.info("xpath --> " + object + "retrived successfully");
		}
	}

} catch (Exception e) {
	Continue.set(false);
	clog.error(Result.getStackMsg(e));
}
return element;

}



	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: MobileApp
	 * Use 					: To Create random AlphaNumericString
	 * Designed By			: JJ
	 * Last Modified Date 	: 04-March-2020
	--------------------------------------------------------------------------------------------------------*/

	  public static String getRandomAlphaString(int n) {
	        // chose a Character random from this String
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        // create StringBuffer size of AlphaNumericString
	        StringBuilder sb = new StringBuilder(n);
	        for (int i = 0; i < n; i++) {
	            // generate a random number between
	            // 0 to AlphaNumericString variable length
	            int index
	                    = (int) (AlphaNumericString.length()
	                    * Math.random());

	            // add Character one by one in end of sb
	            sb.append(AlphaNumericString
	                    .charAt(index));
	        }
	        return sb.toString();
	    }

	  /*---------------------------------------------------------------------------------------------------------
		 * Class Name			: MobileApp
		 * Use 					: Hide Keyboard
		 * Designed By			: JJ
		 * Last Modified Date 	: 04-March-2020
		--------------------------------------------------------------------------------------------------------*/
		public static void hideKeyBoard() {

			AndroidDriver ad=(AndroidDriver) driver.get();
			ad.hideKeyboard();
	    }
		 /*---------------------------------------------------------------------------------------------------------
		 * Class Name			: MobileApp
		 * Use 					: To handle Mobile App android ENV issue
		 * Designed By			: JJ
		 * Last Modified Date 	: 04-March-2020
		--------------------------------------------------------------------------------------------------------*/
		public static void navigateMobileApp() {
			MobileElement btnEnglishAndroid=null;

		try{
			btnEnglishAndroid = (MobileElement) new WebDriverWait(driver.get(), 10)
			.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("englishLnBtn")));
	     if(btnEnglishAndroid.isDisplayed()){
	    	 clog.info("Unable to fetch data please try again error not displayed");
	     }
		}catch(Exception e){

			for(int i=0;i<=5;i++){
				try{
					MobileElement btnTryAgainAndroid = (MobileElement) new WebDriverWait(driver.get(), 10)
							.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath("//android.widget.Button[contains(@text,'TRY AGAIN')]")));

					if(btnTryAgainAndroid.isDisplayed()){
						clog.info("Unable to fetch data please try again error displayed");
						btnTryAgainAndroid.click();
						btnEnglishAndroid.isDisplayed();
						break;
					}

				}catch(Exception ex){
					i++;
					if(i==6){
						Continue.set(false);
						clog.error(Result.getStackMsg(ex));
						break;
					}

				}

			}

		}

	    }

		public static void moveToElement(MobileElement element) {



			try {
				TouchActions action = new TouchActions(driver.get());
				action.scroll(element, 10, 100);
				action.perform();

			} catch (TimeoutException e) {

				Continue.set(false);
			}

		}

		/*---------------------------------------------------------------------------------------------------------
		 * Class Name			: MobileApp
		 * Use 					:  Clear the text from the element
		 * Designed By			: SS
		 * Last Modified Date 	: 14-April-2021
		--------------------------------------------------------------------------------------------------------*/

		public static void clear(String objname) throws Exception {
	        String objtype = "Mobile";
	        String[] objprop = Utlities.FindObject(objname, objtype);
	        MobileElement mElement = getMobileElement(objprop);
	        waitUntilClickable(mElement);
	        mElement.clear();
	        if (Continue.get() == false) {
	            Result.takescreenshot(" Unable to touch ::" + objname);
	            clog.info(" Unable to touch :: " + objname);
	            throw new Exception();
	        } else {
	            clog.info(" clicked sucessfully  ::" + objname);
	        }
	    }

		/*---------------------------------------------------------------------------------------------------------
		 * Class Name			: MobileApp
		 * Use 					: pick single value from picker weel
		 * Designed By			: SS
		 * Last Modified Date 	: 14-April-2021
		--------------------------------------------------------------------------------------------------------*/
		public static void pickerwheel1(String objname, String objvalue) throws Exception {
	        String objtype = "Mobile";
	        String[] objprop = Utlities.FindObject(objname, objtype);
	        MobileElement mElement = getMobileElement(objprop);
	        waitUntillVisible(mElement);
	        mElement.setValue(objvalue);
	        if (Continue.get() == false) {
	            Result.takescreenshot(" Unable to Enter :: " + objvalue + "on :: " + objname);
	            clog.info(" Unable to Enter :: " + objvalue + "on :: " + objname);
	            throw new Exception();
	        } else {
	            clog.info(objvalue + " Sucessfully Entered on:: " + objname);
	        }
	    }
		/*---------------------------------------------------------------------------------------------------------
		 * Class Name			: MobileApp
		 * Use 					: pick multi value from picker weel
		 * Designed By			: SS
		 * Last Modified Date 	: 14-April-2021
		--------------------------------------------------------------------------------------------------------*/
		 public static void pickerwheel2(String objname, String objvalue,String objvalue1) throws Exception {
		        String objtype = "Mobile";
		        String[] objprop = Utlities.FindObject(objname, objtype);
		        MobileElement mElement = getMobileElement(objprop);
		        waitUntillVisible(mElement);
		        mElement.setValue(objvalue);
		        mElement.setValue(objvalue1);
		        if (Continue.get() == false) {
		            Result.takescreenshot(" Unable to Enter :: " + objvalue + objvalue1 +"on :: " + objname);
		            clog.info(" Unable to Enter :: " + objvalue + objvalue1 +"on :: " + objname);
		            throw new Exception();
		        } else {
		            clog.info(objvalue + objvalue1 + " Sucessfully Entered on:: " + objname);
		        }

		    }

		 public static void closeApp() throws Exception {

	            IOSDriver ad=(IOSDriver) driver.get();
	            ad.closeApp();

	         }
		 public static void terminateApp(String bundleId) throws Exception {

	            IOSDriver ad=(IOSDriver) driver.get();
	            ad.terminateApp(bundleId);

	         }
		 public static void touchelemenetsxycoordinates(String objname,int xcord,int ycord) throws Exception {
		        String objtype = "Mobile";
		        String[] objprop = Utlities.FindObject(objname, objtype);
		        MobileElement mElement = getMobileElement(objprop);
		        waitUntilClickable(mElement);
		        Actions action = new Actions(driver.get());
		        action.moveToElement(mElement, xcord, ycord).click().build().perform();
		        if (Continue.get() == false) {
		            Result.takescreenshot(" Unable to touch ::" + objname);
		            clog.info(" Unable to touch :: " + objname);
		            throw new Exception();
		        } else {
		            clog.info(" clicked sucessfully  ::" + objname);
		        }
		    }
		 public static void touchxycoordinates(int xOffset,int yOffset) throws Exception {
		        Actions action = new Actions(driver.get());
		        action.moveByOffset(xOffset, yOffset).click().build().perform();
		    }
		 public static void appReactivate(String objname) throws Exception {
		        IOSDriver ad=(IOSDriver) driver.get();
		        HashMap<String, Object> args = new HashMap<>();
		        args.put("bundleId", objname);
		        ad.executeScript("mobile: activateApp", args);
		    }
		  public static void touchxycoordinates1(int xOffset,int yOffset) throws Exception {
		        IOSDriver ad=(IOSDriver) driver.get();
		        Actions action = new Actions(ad);
		        action.moveByOffset(xOffset, yOffset).click().build().perform();
		    }
		  public static void touchJ(String objname) throws Exception {
				String objtype = "MobileElement";
				String[] objprop = Utlities.FindObject(objname, objtype);
				MobileElement mElement = getMobileElement(objprop);
			//	waitUntillVisible(mElement);
				clickJ(mElement);
				if (Continue.get() == false) {
					Result.takescreenshot(" Unable to touch ::" + objname);
					clog.info(" Unable to touch :: " + objname);
					throw new Exception();
				} else {
					clog.info(" clicked sucessfully  ::" + objname);
				}
			}
		  public static void clickJ(MobileElement element) {

			  JavascriptExecutor executor = ((JavascriptExecutor) driver.get());
	            executor.executeScript("arguments[0].click();", element);

			}
		  public static void touchactionxycoordinates(int xOffset,int yOffset) throws Exception {
			  IOSDriver ad=(IOSDriver) driver.get();

			  TouchAction touchAction = new TouchAction(ad);
			  touchAction.tap(PointOption.point(xOffset, yOffset)).perform();

			  }

		  /*---------------------------------------------------------------------------------------------------------
			 * Class Name			: MobileApp
			 * Use 					: pick single value from picker weel
			 * Designed By			: SS
			 * Last Modified Date 	: 14-April-2021
			--------------------------------------------------------------------------------------------------------*/
			@SuppressWarnings("unchecked")
			public static void pick(String objname, String objvalue) throws Exception {

				List<MobileElement> el = (List<MobileElement>) new WebDriverWait(driver.get(), waitMTimeOut)
						.until(ExpectedConditions.presenceOfElementLocated(MobileBy.className(("XCUIElementTypePickerWheel"))));

				//List<MobileElement> el = driver.findElements(MobileBy.className("XCUIElementTypePickerWheel"));

				// set first PickerWheel
				el.get(0).setValue("March");

				// set second PickerWheel
				//el.get(1).setValue(txt);
		    }

}
