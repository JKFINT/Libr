package Libraries;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait extends Driver {

	static WebDriverWait wait = new WebDriverWait(driver.get(), waitTimeOut);

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitUntillClickable
	 * Arguments			: WebElement
	 * Use 					: Wait till element ready to click
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static boolean waitUntillClickable(WebElement element) {

		boolean enabled = false;
		try {
			if (Continue.get()) {
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				wait.until(ExpectedConditions.visibilityOf(element));
				clog.info("element identified successfully");
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				clog.info("element scrollIntoView successfully");
				wait.until(ExpectedConditions.elementToBeClickable(element));
				clog.info("element is clickable");
				enabled = element.isEnabled();
				clog.info("element is enabled : " + enabled);
			}
		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

		return enabled;

	}
	
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitUntillClickEnable
	 * Arguments			: WebElement
	 * Use 					: Wait till element ready to click
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static boolean waitUntillClickEnable(WebElement element) {

		boolean enabled = false;
		try {
			if (Continue.get()) {
				wait.until(ExpectedConditions.visibilityOf(element));
				clog.info("element identified successfully");
				wait.until(ExpectedConditions.elementToBeClickable(element));
				clog.info("element is clickable");
				enabled = element.isEnabled();
				clog.info("element is enabled : " + enabled);
			}
		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

		return enabled;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitUntillClickable
	 * Arguments			: String Array
	 * Use 					: find the element and Wait till element to click
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void waitUntillClickable(String[] identifiers) throws AWTException {

		WebElement findElement = null;
		try {
			if (Continue.get()) {

				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				findElement = Web.getWebElement(identifiers);
				wait.until(ExpectedConditions.visibilityOf(findElement));
				js.executeScript("arguments[0].scrollIntoView(true);", findElement);
				clog.info("scrollIntoView successfully");
				wait.until(ExpectedConditions.elementToBeClickable(findElement));
				clog.info(" element is clickable");
				boolean enabled = findElement.isEnabled();
				clog.info("element is enabled : " + enabled);

			}

		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitUntillVisible
	 * Arguments			: String Array
	 * Use 					: find the element and Wait till element to click
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static boolean waitUntillVisible(WebElement element) {

		boolean display = false;
		try {
			if (Continue.get()) {
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				wait.until(ExpectedConditions.visibilityOf(element));
				clog.info("element identified successfully");
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				clog.info("element scrollIntoView successfully");
				display = element.isDisplayed();
				clog.info("element is displayed : " + display);
			}
		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

		return display;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitUntillVisible
	 * Arguments			: String Array
	 * Use 					: find the element and Wait till element to click
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static boolean waitUntillVisible(String[] identifiers) throws AWTException {

		boolean display = false;
		try {
			if (Continue.get()) {

				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				WebElement findElement = Web.getWebElement(identifiers);
				wait.until(ExpectedConditions.visibilityOf(findElement));
				js.executeScript("arguments[0].scrollIntoView(true);", findElement);
				clog.info("scrollIntoView successfully");
				display = findElement.isDisplayed();
				clog.info("element is displayed : " + display);
			}

		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return display;

	}

	

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitUntillVisiblityOfAllElements
	 * Arguments			: WebElement ArrayList
	 * Use 					: find the elements and Wait until visible
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void waitUntillVisiblityOfAllElements(List<WebElement> elements) {
		try {
			if (Continue.get()) {
				wait.until(ExpectedConditions.visibilityOfAllElements(elements));
				clog.info("element identified successfully");
			}
		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: checkAvailable
	 * Arguments			: String ,String 
	 * Use 					: check the element is available in the page or not
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static boolean checkAvailable(String objectName, String objectType) throws Exception {
		boolean isAvailable = false;
		String[] objectProperty = Utlities.FindObject(objectName, objectType);

		int size = Web.getWebElements(objectProperty).size();
		clog.info("occurance of object " + objectName + " is : " + size);
		if (size > 0) {
			isAvailable = Web.getWebElement(objectProperty).isDisplayed();
		} else {
			isAvailable = false;
		}
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
			clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
			throw new Exception();
		} else {
			clog.info(objectName + "is Available : " + isAvailable);
		}
		return isAvailable;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: dynamicCheckAvailable
	 * Arguments			: String ,String , String
	 * Use 					: check the element is available in the page or not
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static boolean dynamicCheckAvailable(String objectName, String value, String objectType) throws Exception {
		boolean isAvailable = false;
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		int size = Web.getDynamicWebElements(objectProperty, value).size();
		clog.info("occurance of object " + objectName + " is : " + size);
		if (size > 0) {
			isAvailable = Web.getDynamicWebElement(objectProperty, value).isDisplayed();
		} else {
			isAvailable = false;
		}
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
			clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
			throw new Exception();
		} else {
			clog.info(objectName + "is Available : " + isAvailable);
		}
		return isAvailable;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: dynamicCheckEnable
	 * Arguments			: String ,String , String
	 * Use 					: To check the element is enabled 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static boolean dynamicCheckEnable(String objectName, String value, String objectType) throws Exception {
		boolean isEnabled = false;
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		int size = Web.getDynamicWebElements(objectProperty, value).size();
		clog.info("occurance of object " + objectName + " is : " + size);
		if (size > 0) {
			isEnabled = Web.getDynamicWebElement(objectProperty, value).isEnabled();
		} else {
			isEnabled = false;
		}
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to check object : " + objectName + " is Enabled or not");
			clog.info(" :: Failed to check object :  " + objectName + " is Enabled or not");
			throw new Exception();
		} else {
			clog.info(objectName + "is Enabled : " + isEnabled);
		}
		return isEnabled;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: checkEnable
	 * Arguments			: String ,String 
	 * Use 					: To check the element is enabled 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static boolean checkEnable(String objectName, String objectType) throws Exception {
		boolean isEnabled = false;
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		int size = Web.getWebElements(objectProperty).size();
		clog.info("occurance of object " + objectName + " is : " + size);
		if (size > 0) {
			isEnabled = Web.getWebElement(objectProperty).isEnabled();
		} else {
			isEnabled = false;
		}
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
			clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
			throw new Exception();
		} else {
			clog.info(objectName + "is Enabled : " + isEnabled);
		}
		return isEnabled;
	}
	
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: checkSelected
	 * Arguments			: String ,String 
	 * Use 					: To check the element is enabled 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static boolean checkSelected(String objectName, String objectType) throws Exception {
		boolean isSelected = false;
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		int size = Web.getWebElements(objectProperty).size();
		clog.info("occurance of object " + objectName + " is : " + size);
		if (size > 0) {
			isSelected = Web.getWebElement(objectProperty).isSelected();
		} else {
			isSelected = false;
		}
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to check object : " + objectName + " is isSelected or not");
			clog.info(" :: Failed to check object :  " + objectName + " is isSelected or not");
			throw new Exception();
		} else {
			clog.info(objectName + "is isSelected : " + isSelected);
		}
		return isSelected;
	}
	
	

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitForElement
	 * Arguments			: String , String
	 * Use 					: Wait until element to available and use
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	public static void waitForElement(String identifier, String identifierType) throws InterruptedException {

		try {
			int iterations = 200;
				for (int i = 1; i <= iterations; i++) {
					
					String[] objectProperty = Utlities.FindObject(identifier, identifierType);
					int count = Web.getWebElements(objectProperty).size();
					clog.info("occurance of object " + identifier + " is : " + count);	
					if (count != 0 && readyState()) {
						clog.info("Element found");
						System.out.println("Element found");
						break;
					} else {
						Thread.sleep(5000);
						clog.info("Waiting for the element:" + identifier);
						System.out.println("Waiting for the element:" + identifier);
					}
				}
			
		} catch (Exception e) {
			Continue.set(false);
			clog.info(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: readyState
	 * Arguments			:
	 * Use 					: Wait until DOM Load
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
	
	public static Boolean readyState() {
		((JavascriptExecutor) driver.get()).executeScript("return document.readyState");
		return true;
	}

	

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitForPageToLoad
	 * Arguments			: 
	 * Use 					: Checks for the browser ready state and waits for the page to load 
	 * Designed By			: JJ
	 * Last Modified Date 	: 23-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void waitForPageToLoad() throws AWTException {
		try {

			driver.get().manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

			JavascriptExecutor js = (JavascriptExecutor) driver.get();
			String command = "return document.readyState";
			// Check the readyState before doing any waits
			
			for (int i = 0; i < 50000; i++) {
				if (js.executeScript(command).toString().equals("complete")) {
					break;
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			clog.error(Result.getStackMsg(e));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name : waitTillObjectDisappears
	 * Arguments : String objectName, String objectType
	 * Use : find the element and Wait till element to click
	 * Designed By : Sathya Lenin EARC
	 * Last Modified Date : 24-06-2021
	 --------------------------------------------------------------------------------------------------------*/

	public static void waitTillObjectDisappears(String objectName, String objectType) throws InterruptedException {
		try {
			if (Continue.get() == true) {
				clog.info("inside overlay Load");
				waitForPageToLoad();
				String[] objectProperty = Utlities.FindObject(objectType, objectType);
				int size = Web.getWebElements(objectProperty).size();
				clog.info("Occurance of object : " + objectName + " is : " + size);
				if (size > 0) {
					wait.until(ExpectedConditions.invisibilityOf(Web.getWebElement(objectProperty)));
					clog.info("element disappeared");
				}

				else {
					clog.info("Page Load Completed");
				}
			}
		} catch (Exception e) {
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name : waitForSomeTime
	 * Arguments : 
	 * Use : default wait for 5 seconds
	 * Designed By : JJ
	 * Last Modified Date : 24-06-2021
	 --------------------------------------------------------------------------------------------------------*/

	
	public static void waitForSomeTime() {

		try {
			waitForPageToLoad();
			Thread.sleep(5000);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name : waitForMoreTime
	 * Arguments : 
	 * Use : default wait for 5 mins
	 * Designed By : JJ
	 * Last Modified Date : 24-06-2021
	 --------------------------------------------------------------------------------------------------------*/

	
	public static void waitForMoreTime() {

		try {
			waitForPageToLoad();
			driver.get().manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
			Thread.sleep(5000);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}


}
