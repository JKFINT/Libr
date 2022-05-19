package Libraries;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiebelCommonMethods extends Driver {

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitTillSiebelLoad
	 * Arguments			: 
	 * Use 					: wait until the sieble load icon disappear
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 24-06-2021
	--------------------------------------------------------------------------------------------------------*/

	/*public static void waitTillSiebelLoad() throws InterruptedException {
		try {
			if (Continue.get() == true) {
				clog.info("inside overlay Load");
				Wait.waitTillObjectDisappears("Siebel_maskOverlay_", "WebEdit");
			}
		} catch (Exception e) {
		}
	}*/
	
	public static boolean checkLoad() {
		boolean isOverLayDisplayed = false;
		try {
			if (Continue.get() == true) {
				clog.info("inside overlay Load");
				Thread.sleep(5000);
				isOverLayDisplayed = driver.get().findElement(By.id("maskoverlay")).isDisplayed();
			}
		} catch (Exception e) {

		}
		
		return isOverLayDisplayed;

	}
	
	public static void waitTillSiebelLoad(){
		for(int load=0;load<=500;load++){
			Wait.waitForSomeTime();
			boolean overLay=checkLoad();
			if(!overLay){
				break;
			}
		}
		Wait.waitForSomeTime();
	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: selectRecord
	 * Arguments			: 
	 * Use 					: select Record from the siebel application
	 * Designed By			: JJ
	 * Last Modified Date 	: 24-06-2021
	--------------------------------------------------------------------------------------------------------*/


	public static String selectRecord(String settingBTN) throws InterruptedException, AWTException {

		String[] objectProperty = Utlities.FindObject(settingBTN, "WebButton");
		Wait.waitUntillClickable(Web.getWebElement(objectProperty));
		WebAction.clickWithJS(Web.getWebElement(objectProperty));
		
		waitTillSiebelLoad();
		Thread.sleep(2000);
		clog.info("clicked on setting button");
		Wait.waitUntillClickable(Web.getWebElement("siebelRecord","WebButton"));
		WebAction.clickWithOffset(Web.getWebElement("siebelRecord","WebButton"));
		waitTillSiebelLoad();
		
		clog.info("clicked on about Record");
		Wait.waitUntillVisible(Web.getWebElement("siebelRowId", "WebButton"));
		String getRowID = WebAction.readText(Web.getWebElement("siebelRowId", "WebButton"));

		
		clog.info("row id copied");

		Wait.waitUntillClickable(Web.getWebElement("siebelRecordClose", "WebButton"));
		WebAction.clickWithOffset(Web.getWebElement("siebelRecordClose", "WebButton"));

		
		clog.info("Clicked on Ok button");
		return getRowID;
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: tabNavigator
	 * Arguments			: tab option
	 * Use 					: Navigates to the required tab on the first Navbar after logging inside the Siebel
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 24-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void tabNavigator(String value) {
		Boolean finder = false;
		try {
			String[] objectProperty = Utlities.FindObject("Siebel_Nav_Tab_", "WebEdit");
			List<WebElement> options1 = Web.getWebElements(objectProperty);
			Wait.waitUntillVisiblityOfAllElements(options1);
			for (org.openqa.selenium.WebElement option : options1) {

				if (WebAction.readText(option).equalsIgnoreCase(value)) {
					Wait.waitUntillClickable(option);
					WebAction.click(option);
					finder = true;
					break;
				}
			}

			if (finder == false) {
				String[] objectProperty1 = Utlities.FindObject("Siebel_Nav_Tabs_Options_", "WebEdit");
				List<WebElement> option2 = Web.getWebElements(objectProperty1);
				Wait.waitUntillVisiblityOfAllElements(options1);
				for (org.openqa.selenium.WebElement option : option2) {

					if (WebAction.readText(option).equalsIgnoreCase(value)) {
						Wait.waitUntillClickable(option);
						WebAction.click(option);
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
	 * Method Name			: waitForOrderStatusToGetPending
	 * Arguments			: 
	 * Use 					: Waits and runs the loop for 20 times until the order status changes to pending
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 24-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void waitForOrderStatusToGetPending1(String orderStatus) throws Exception {
		waitTillSiebelLoad();
		String statusAttribute = null;
		Web.WebLink.isEnabled("Siebel_Execute_Query_Button_");
		for (int i = 1; i <= 20; i++) {
			Web.WebLink.clickWithJS("Siebel_Execute_Query_Button_");

			Web.Alert.alertAccept();
			statusAttribute = Web.WebEdit.readAttributeValue(orderStatus);
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
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: readOrderStatusArabicWebTable
	 * Arguments			: String orderStatus, String desiredStatus
	 * Use 					: Waits and runs the loop for 20 times until the order status changes to the users need like pending Applied or failed
	 * Designed By			: Soniya
	 * Last Modified Date 	: 28-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static String readOrderStatusArabicWebTable(String objectName, String columnName, String rowName,
			String rowValue) throws Exception {
		
		waitTillSiebelLoad();
		String OrderStatus = null;
		Web.WebLink.isEnabled("Siebel_Execute_Query_Button_");
		for (int i = 1; i <= 20; i++) {
			Web.WebLink.clickWithJS("Siebel_Execute_Query_Button_");
			 OrderStatus = readTextArabicWebTable(objectName,columnName,rowName,rowValue);
			if
			  (OrderStatus.equalsIgnoreCase("Pending")) {
			  clog.info("Pending status found"); Result.takescreenshot("Final status");
			  break; } 
			else if (OrderStatus.equalsIgnoreCase("Applied")) {
			  clog.info("Pending status found"); Result.takescreenshot("Final status");
			  break; 
			  } 
			else if (OrderStatus.equalsIgnoreCase("Failed")) {
			  clog.info("Failed status found"); Result.takescreenshot("Final status");
			  break; }
		}
		return OrderStatus;
		
	}


	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitForOrderStatusToGetFromUser
	 * Arguments			: String orderStatus, String desiredStatus
	 * Use 					: Waits and runs the loop for 20 times until the order status changes to the users need like pending Applied or failed
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 24-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void waitForOrderStatusToGetFromUser(String orderStatus, String desiredStatus) throws Exception {
		waitTillSiebelLoad();
		String statusAttribute = null;
		Web.WebLink.isEnabled("Siebel_Execute_Query_Button_");
		for (int i = 1; i <= 20; i++) {
			Web.WebLink.clickWithJS("Siebel_Execute_Query_Button_");

			Web.Alert.alertAccept();
			statusAttribute = Web.WebEdit.readAttributeValue(orderStatus);
			if (statusAttribute.equalsIgnoreCase(desiredStatus)) {
				clog.info("Pending status found");
				Result.takescreenshot("Final status");
				break;
			}
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name : navBarElement
	* Arguments : NavbarLink
	* Use : Navigates to the desired Tab
	* Designed By : Jayakumar Manoharan
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void navBarElement(String NavbarLink) {
		try {
			if (Web.WebLink.isAvailable(NavbarLink)) {
				Web.WebLink.click(NavbarLink);
			}

			else {
				List<WebElement> jumpTabs = Web.getWebElements("Siebel_View_Jump_Tabs_Index", "WebLink");
				int size = jumpTabs.size();
				clog.info("size of jump tabs : " + size);
				Wait.waitUntillVisiblityOfAllElements(jumpTabs);
				for (WebElement tab : jumpTabs) {
					WebAction.click(tab);
					if (Web.WebLink.isAvailable(NavbarLink)) {
						Web.WebLink.click(NavbarLink);
						break;
					}

				}
			}
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			clog.info(Result.getStackMsg(e));
		}
	}

	

	/*---------------------------------------------------------------------------------------------------------
	* Method Name : navBarElementJ
	* Arguments : NavbarLink
	* Use : Navigates to the desired Tab
	* Designed By : Jayakumar Manoharan
	* Last Modified Date : 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void navBarElementJ(String NavbarLink) {
		try {
			if (Web.WebLink.isAvailable(NavbarLink)) {
				Web.WebLink.clickWithJS(NavbarLink);
			}

			else {
				List<WebElement> jumpTabs = Web.getWebElements("Siebel_View_Jump_Tabs_Index", "WebLink");
				int size = jumpTabs.size();
				clog.info("size of jump tabs : " + size);
				Wait.waitUntillVisiblityOfAllElements(jumpTabs);
				for (WebElement tab : jumpTabs) {
					WebAction.clickWithJS(tab);
					if (Web.WebLink.isAvailable(NavbarLink)) {
						Web.WebLink.clickWithJS(NavbarLink);
						break;
					}

				}
			}
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			clog.info(Result.getStackMsg(e));
		}
	}

	

	

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getHeaderColumnNumber
	 * Arguments			: String,String
	 * Use 					: get Header Column Number Web table
	 * Modified             : JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static int getHeaderColumnNumber(String objName, String columnName) {
		int columnNum = 0;
		try {
			String[] objprop = Utlities.FindObject(objName, "WebTable");
			
			Wait.waitUntillVisible(Web.getWebElement(objprop));

			String headTableXpath = objprop[0]
					+ "/ancestor::div[@class='ui-jqgrid-bdiv']/preceding-sibling::div[@class='ui-state-default ui-jqgrid-hdiv']//table";

			WebElement element = driver.get().findElement(By.xpath(headTableXpath));

			List<WebElement> columnElement = element.findElement(By.tagName("tr")).findElements(By.tagName("th"));

			for (int columnNumber = 1; columnNumber < columnElement.size(); columnNumber++) {
				
				WebElement element1 =columnElement.get(columnNumber);
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", element1);
				clog.info("element scrollIntoView successfully");
				
				clog.info(" ::colNumber Text " + columnElement.get(columnNumber).getText());
				clog.info(" ::colName Text " + columnName.trim());
				clog.info(" ::colName Number " + columnNumber);

				if (columnElement.get(columnNumber).getText().trim().equalsIgnoreCase(columnName.trim())) {
					columnNum = columnNumber;
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

		return columnNum;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getColumnNumberContains
	 * Arguments			: String,String
	 * Use 					: get Column Number Web table contains Arabic
	 * Modified             : JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static int getHeaderColumnNumberContains(String objName, String columnName) {

		int columnNum = 0;
		try {

			String[] objprop = Utlities.FindObject(objName, "WebTable");
			
			Wait.waitUntillVisible(Web.getWebElement(objprop));

			String headTableXpath = objprop[0]
					+ "/ancestor::div[@class='ui-jqgrid-bdiv']/preceding-sibling::div[@class='ui-state-default ui-jqgrid-hdiv']//table";

			WebElement element = driver.get().findElement(By.xpath(headTableXpath));

			// element=element.findElement(By.xpath("/ancestor::div[@class='ui-jqgrid-bdiv']/preceding-sibling::div[@class='ui-state-default
			// ui-jqgrid-hdiv']//table"));

			List<WebElement> columnElement = element.findElement(By.tagName("tr")).findElements(By.tagName("th"));

			for (int columnNumber = 1; columnNumber < columnElement.size(); columnNumber++) {

				WebElement element1 =columnElement.get(columnNumber);
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", element1);
				clog.info("element scrollIntoView successfully");
				
				clog.info(" ::colNumber Text " + columnElement.get(columnNumber).getText());
				clog.info(" ::colName Text " + columnName.trim());

				if (columnElement.get(columnNumber).getText().trim().contains(columnName.trim())) {
					columnNum = columnNumber;
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
		clog.info(" ::colNumber  " + columnNum);
		return columnNum;
	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: getRowNumberContains
	* Arguments			: String,String,String
	* Use 					: get row Number Web table contains Arabic
	* Designed By			: AG
	* Modified             : JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static int getRowNumberContains(String objName, String rowReference, String columnValue) {
		int rowNo = 0;
		try {
			WebElement element = Web.getWebElement(objName, "WebTable");
			int colNumber = getHeaderColumnNumberContains(objName, rowReference);
			clog.info(" ::colNumber  " + colNumber);
			int rowCount = WebAction.getRowCount(objName);
			clog.info(" ::rowCount  " + rowCount);
			for (int crrow = 1; crrow <= rowCount; crrow++) {

				WebElement element1 =element.findElements(By.tagName("tr")).get(crrow).findElements(By.tagName("td"))
						.get(colNumber);
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", element1);
				clog.info("element scrollIntoView successfully");
				
				
				String cellValue = element.findElements(By.tagName("tr")).get(crrow).findElements(By.tagName("td"))
						.get(colNumber).getText();

				clog.info(" ::cellValue  " + cellValue);

				if (cellValue.trim().contains(columnValue.trim())) {
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
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getRowNumber
	 * Arguments			: String,String,String
	 * Use 					: get row Number Web table 
	 * Designed By			: AG
	 * Modified             : JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static int getRowNumber(String objName, String rowReference, String colName) {
		int rowNo = 0;
		try {
			WebElement element = Web.getWebElement(objName, "WebTable");
			int colNumber = getHeaderColumnNumber(objName, rowReference);
			clog.info(" ::colNumber  " + colNumber);
			int rowCount = WebAction.getRowCount(objName);
			clog.info(" ::rowCount  " + rowCount);
			for (int crrow = 0; crrow < rowCount; crrow++) {

				WebElement element1 =element.findElements(By.tagName("tr")).get(crrow).findElements(By.tagName("td"))
						.get(colNumber);
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", element1);
				clog.info("element scrollIntoView successfully");
				
				String cellValue = element.findElements(By.tagName("tr")).get(crrow).findElements(By.tagName("td"))
						.get(colNumber).getText();

				clog.info(" ::cellValue  " + cellValue);
				if (cellValue.trim().equalsIgnoreCase(colName.trim())) {
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

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: readTextEnglishWebTable
	* Arguments			: String , String , String , String 
	* Use 					: read the text from web table which contains English value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static String readTextEnglishWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		String value = WebAction.getWebTableCellData(objectName, rowNumber, columnNumber);
		clog.info("received Value " + value);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to retrive the value from " + objectName);
			clog.info(" :: Failed to retrive the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action value retrive the value from: " + objectName + " :: " + value);
		}
		return value;
	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: readTextArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: read the text from web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static String readTextArabicWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		String value = WebAction.getWebTableCellData(objectName, rowNumber, columnNumber);
		clog.info("received Value " + value);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to retrive the value from " + objectName);
			clog.info(" :: Failed to retrive the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action value retrive the value from: " + objectName + " :: " + value);
		}
		return value;
	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: clickDataEnglishWebTable
	* Arguments			: String , String , String , String 
	* Use 					: click the text from web table which contains English value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickDataEnglishWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickWebTable(objectName, rowNumber, columnNumber);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: clickDataArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: click the text from web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickDataArabicWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickWebTable(objectName, rowNumber, columnNumber);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickJSDataEnglishWebTable
	 * Arguments			: String , String , String , String 
	 * Use 					: click with JS the text from web table which contains English value
	 * Designed By			: JJ
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickJSDataEnglishWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickWithJSWebTable(objectName, rowNumber, columnNumber);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: clickJSDataArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: click with JS the text from web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickJSDataArabicWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickWithJSWebTable(objectName, rowNumber, columnNumber);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickLinkEnglishWebTable
	 * Arguments			: String , String , String , String 
	 * Use 					: click link the text from web table which contains English value
	 * Designed By			: JJ
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickLinkEnglishWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickLinkJSWebTable(objectName, rowNumber, columnNumber);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: clickLinkArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: click the link text from web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickLinkArabicWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickLinkJSWebTable(objectName, rowNumber, columnNumber);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickOffsetEnglishWebTable
	 * Arguments			: String , String , String , String 
	 * Use 					: click link with Offset the text from web table which contains English value
	 * Designed By			: JJ
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickOffsetEnglishWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickLinkWithOffsetWebTable(objectName, rowNumber, columnNumber);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: clickOffsetArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: click the link with Offset text from web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickOffsetArabicWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickLinkWithOffsetWebTable(objectName, rowNumber, columnNumber);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickCheckBoxEnglishWebTable
	 * Arguments			: String , String , String , String 
	 * Use 					: click CheckBox the from web table which contains English value
	 * Designed By			: JJ
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickCheckBoxEnglishWebTable(String objectName, String columnName, String rowName,
			String rowValue) throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickCheckBoxWebTable(objectName, rowNumber, columnNumber);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: clickCheckBoxArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: click the CheckBox from web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickCheckBoxArabicWebTable(String objectName, String columnName, String rowName,
			String rowValue) throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickCheckBoxWebTable(objectName, rowNumber, columnNumber);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickDropDownEnglishWebTable
	 * Arguments			: String , String , String , String 
	 * Use 					: click DropDown from web table which contains English value
	 * Designed By			: JJ
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickDropDownEnglishWebTable(String objectName, String columnName, String rowName,
			String rowValue) throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickDropDownWebTable(objectName, rowNumber, columnNumber);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: clickDropDownArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: click DropDown from web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickDropDownArabicWebTable(String objectName, String columnName, String rowName,
			String rowValue) throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickDropDownWebTable(objectName, rowNumber, columnNumber);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickOffsetDropDownEnglishWebTable
	 * Arguments			: String , String , String , String 
	 * Use 					: click DropDown from web table which contains English value
	 * Designed By			: JJ
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickOffsetDropDownEnglishWebTable(String objectName, String columnName, String rowName,
			String rowValue) throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickOffsetDropDownWebTable(objectName, rowNumber, columnNumber);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: clickOffsetDropDownArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: click DropDown from web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickOffsetDropDownArabicWebTable(String objectName, String columnName, String rowName,
			String rowValue) throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickOffsetDropDownWebTable(objectName, rowNumber, columnNumber);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickTextEnglishWebTable
	 * Arguments			: String , String , String , String 
	 * Use 					: click text from web table which contains English value
	 * Designed By			: JJ
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickTextEnglishWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickOffsetDataWebTable(objectName, rowNumber, columnNumber);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: clickTextArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: click text from web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickTextArabicWebTable(String objectName, String columnName, String rowName, String rowValue)
			throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.clickOffsetDataWebTable(objectName, rowNumber, columnNumber);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click the value from " + objectName);
			clog.info(" :: Failed to click the value from  " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click  the value from: " + objectName);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: writeTextEnglishWebTable
	 * Arguments			: String , String , String , String 
	 * Use 					: write text in web table which contains English value
	 * Designed By			: JJ
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void writeTextEnglishWebTable(String objectName, String columnName, String rowName, String rowValue,
			String object, String value) throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.writeDataWebTable(objectName, rowNumber, columnNumber, object, value);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to enter the value from " + objectName + ":: " + value);
			clog.info(" :: Failed to click the value from  " + objectName + ":: " + value);
			throw new Exception();
		} else {
			clog.info(" :: Action enter  the value in: " + objectName + ":: " + value);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: writeTextArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: write text in web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void writeTextArabicWebTable(String objectName, String columnName, String rowName, String rowValue,
			String object, String value) throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.writeDataWebTable(objectName, rowNumber, columnNumber, object, value);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to enter the value from " + objectName + ":: " + value);
			clog.info(" :: Failed to click the value from  " + objectName + ":: " + value);
			throw new Exception();
		} else {
			clog.info(" :: Action enter  the value in: " + objectName + ":: " + value);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: writeEnterTextEnglishWebTable
	 * Arguments			: String , String , String , String 
	 * Use 					: write text and enter in web table which contains English value
	 * Designed By			: JJ
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void writeEnterTextEnglishWebTable(String objectName, String columnName, String rowName,
			String rowValue, String object, String value) throws Exception {

		Wait.waitForElement(objectName, "WebTable");

		int columnNumber = getHeaderColumnNumber(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumber(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.writeDataAndEnterWebTable(objectName, rowNumber, columnNumber, object, value);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to enter the value from " + objectName + ":: " + value);
			clog.info(" :: Failed to click the value from  " + objectName + ":: " + value);
			throw new Exception();
		} else {
			clog.info(" :: Action enter  the value in: " + objectName + ":: " + value);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: writeEnterTextArabicWebTable
	* Arguments			: String , String , String , String 
	* Use 					: write text and enter in web table which contains Arabic value
	* Designed By			: JJ
	* Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void writeEnterTextArabicWebTable(String objectName, String columnName, String rowName,
			String rowValue, String object, String value) throws Exception {

		Wait.waitForElement(objectName, "WebTable");
		int columnNumber = getHeaderColumnNumberContains(objectName, columnName);
		clog.info("column Number " + columnNumber);
		int rowNumber = getRowNumberContains(objectName, rowName, rowValue);
		clog.info("rowNumber " + rowNumber);
		WebAction.writeDataAndEnterWebTable(objectName, rowNumber, columnNumber, object, value);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to enter the value from " + objectName + ":: " + value);
			clog.info(" :: Failed to click the value from  " + objectName + ":: " + value);
			throw new Exception();
		} else {
			clog.info(" :: Action enter  the value in: " + objectName + ":: " + value);
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: getCustomerID
	* Arguments			: String 
	* Use 					: Generate random customer ID and return
	* Designed By			: Lenin
	* Last Modified Date 	: 07-07-2021
	--------------------------------------------------------------------------------------------------------*/
	
	
	public static String getCustomerID(String ID_Type) {
        String RandomNumber = null;
        Date today = new Date();
        DateFormat DATE_FORMAT;    
        DATE_FORMAT = new SimpleDateFormat("ddMM");
        String date = DATE_FORMAT.format(today);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HHmmss");
        String time = TIME_FORMAT.format(cal.getTime());

        if (ID_Type.contains("Commercial")) {
            ID_Type = "GCC ID";
        }
        
        if(ID_Type.equalsIgnoreCase("GCC ID")){
        	 RandomNumber = date + time;
        }
        else if(ID_Type.equalsIgnoreCase("Corporate ID")){
        	 RandomNumber = "7" + date + time;
        }
        return RandomNumber;
    }

}
