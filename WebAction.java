package Libraries;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebAction extends Driver {

	static WebDriverWait wait = new WebDriverWait(driver.get(), waitTimeOut);

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: click
	 * Arguments			: WebElement
	 * Use 					: click the element  
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void click(WebElement element) {
      
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				element.click();
				clog.info("element clicked successfully");
			}

		} catch (Exception e) {
			clog.info("element not clicked successfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickWithJS
	 * Arguments			: WebElement
	 * Use 					: click the element  with Java script executor
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickWithJS(WebElement element) {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				JavascriptExecutor executor = ((JavascriptExecutor) driver.get());
				executor.executeScript("arguments[0].click();", element);
				clog.info("element clicked successfully");
			}

		} catch (Exception e) {
			clog.info("element not clicked successfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickWithOffset
	 * Arguments			: WebElement
	 * Use 					: click the element  with Offset
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickWithOffset(WebElement element) {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				int width = element.getSize().getWidth();
				Actions act = new Actions(driver.get());
				act.moveToElement(element).moveByOffset((width / 2) - 2, 0).click().perform();
				clog.info("element clicked successfully");
			}

		} catch (Exception e) {
			clog.info("element not clicked successfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: doubleClick
	 * Arguments			: WebElement
	 * Use 					: doubleClick the element  with Action class
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void doubleClick(WebElement element) {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				Actions action = new Actions(driver.get()).doubleClick(element);
				action.build().perform();
				clog.info("element clicked successfully");
			}

		} catch (Exception e) {
			clog.info("element not clicked successfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickAction
	 * Arguments			: WebElement
	 * Use 					: click the element  with Action class
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickAction(WebElement element) {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				Actions action = new Actions(driver.get()).click(element);
				action.build().perform();
				clog.info("element clicked successfully");
			}

		} catch (Exception e) {
			clog.info("element not clicked successfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: pressEnter
	 * Arguments			: WebElement
	 * Use 					: press Enter on the element with Action class
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void pressEnter(WebElement element) {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				element.sendKeys(Keys.ENTER);
				clog.info("element clicked successfully");
			}

		} catch (Exception e) {
			clog.info("element not clicked successfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clear
	 * Arguments			: WebElement
	 * Use 					: clear the element based on the locator 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clear(WebElement element) {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				element.clear();
				clog.info("existing text cleared successfully");
			}

		} catch (Exception e) {
			clog.info("existing text not cleared successfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: writeAndEnter
	 * Arguments			: WebElement , String 
	 * Use 					: write the text in the element and perform enter key action 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void writeAndEnter(WebElement element, String value) {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {

				element.sendKeys(value);
				element.sendKeys(Keys.ENTER);
				clog.info("text entered sucessfully :" + value);
			}

		} catch (Exception e) {
			clog.info("text not entered sucessfully :" + value);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: writeEnterWithJS
	 * Arguments			: WebElement,  String
	 * Use 					: write the text in web element 
	 * Designed By			: JJ
	 * Last Modified Date 	: 01-07-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void writeEnterWithJS(WebElement element, String data) {

		try {
			if (Continue.get()) {
				
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].setAttribute('value','" + data + "');", element);
				js.executeScript("arguments[0].value = '" + data + "'", element);
				
				clog.info("text entered sucessfully :" + data);
				element.sendKeys(Keys.ENTER);
			}

		} catch (Exception e) {
			clog.info("text not entered sucessfully :" + data);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: writeWithJS
	 * Arguments			: WebElement,  String
	 * Use 					: write the text in web element 
	 * Designed By			: JJ
	 * Last Modified Date 	: 01-07-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void writeWithJS(WebElement element, String data) {

		try {
			if (Continue.get()) {
				
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].setAttribute('value','" + data + "');", element);
				js.executeScript("arguments[0].value = '" + data + "'", element);
				
				clog.info("text entered sucessfully :" + data);
			}

		} catch (Exception e) {
			clog.info("text not entered sucessfully :" + data);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: write
	 * Arguments			: WebElement,  String
	 * Use 					: write the text in web element 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void write(WebElement element, String value) {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				element.sendKeys(value);
				clog.info("text entered sucessfully :" + value);
			}

		} catch (Exception e) {
			clog.info("text not entered sucessfully :" + value);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: read the text
	* Arguments			: WebElement
	* Use 					: read the text from the locator
	* Designed By			: JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String readText(WebElement element) {
		String text = null;
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				text = element.getText();
				clog.info("text retrived sucessfully :" + text);
			}

		} catch (Exception e) {
			clog.info("text not retrived sucessfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return text;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: readTextWithJS
	 * Arguments			: WebElement
	 * Use 					: read the text with Java script executor
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String readTextWithJS(WebElement element) {
		String text = null;
		try {
			Wait.waitForPageToLoad();
			JavascriptExecutor js = (JavascriptExecutor) driver.get();
			if (Continue.get()) {
				text = js.executeScript("return arguments[0].textContent;", element).toString();
				clog.info("text retrived sucessfully :" + text);
			}

		} catch (Exception e) {
			clog.info("text not retrived sucessfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return text;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: readAttributeValue
	 * Arguments			: WebElement
	 * Use 					: read the attribute value
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String readAttributeValue(WebElement element) {
		String text = null;
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				text = element.getAttribute("value");
				clog.info("text retrived sucessfully :" + text);
			}

		} catch (Exception e) {
			clog.info("text not retrived sucessfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return text;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: readAttributeValue
	 * Arguments			: WebElement , String
	 * Use 					: read the attribute value based on attribute Name
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String readAttribute(WebElement element, String attributeName) {
		String text = null;
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				text = element.getAttribute(attributeName);
				clog.info("text retrived sucessfully :" + text);
			}

		} catch (Exception e) {
			clog.info("text not retrived sucessfully");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return text;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: setDropValue
	 * Arguments			: WebElement , String
	 * Use 					: set the drop down value
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	///////	--------------------------------------------------------------------------------------------------------*/

	public static void setDropValue(String[] objectProperty, String value) {
		try {
			
			if (Continue.get()) {
				Web.getWebElement(objectProperty).click();
				Web.getWebElement(objectProperty).sendKeys(String.valueOf(value.charAt(0)));
				String brfound = "no";
				while (brfound != "yes") {
					String brcode = Web.getWebElement(objectProperty).getAttribute("value");
					if (brcode.contains(value)) {
						Web.getWebElement(objectProperty).click();
						brfound = "yes";
						break;
					} else {
						Web.getWebElement(objectProperty).sendKeys(Keys.ARROW_DOWN);
					}
				}

				clog.info("dropDown value sucessfully selected :" + value);

			}
		} catch (Exception e) {
			clog.info("dropDown value selected not sucessfully :" + value);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: dropDownSelectText
	 * Arguments			: WebElement , String
	 * Use 					: select the drop down value based on visible text using select class
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void dropDownSelectText(WebElement element, String value) {
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {

				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				element.click();
				Select select = new Select(element);
				select.selectByVisibleText(value);

				clog.info("dropDown value sucessfully selected :" + value);

			}
		} catch (Exception e) {
			clog.info("dropDown value selected not sucessfully :" + value);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: dropDownSelect
	 * Arguments			: WebElement , integer
	 * Use 					: select the drop down value based on index using select class
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void dropDownSelectIndex(WebElement element, int index) {
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {

				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				element.click();
				Select select = new Select(element);
				select.selectByIndex(index);
				clog.info("dropDown value sucessfully selected :" + index);

			}
		} catch (Exception e) {
			clog.info("dropDown value selected not sucessfully :" + index);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: dropDownSelectValue
	 * Arguments			: WebElement , String
	 * Use 					: select the drop down value based on value using select class
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void dropDownSelectValue(WebElement element, String value) {
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {

				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				element.click();
				Select select = new Select(element);
				select.selectByValue(value);
				clog.info("dropDown value sucessfully selected :" + value);

			}
		} catch (Exception e) {
			clog.info("dropDown value selected not sucessfully :" + value);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: selectOption
	 * Arguments			: String
	 * Use 					: select the drop down value using JS
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void selectOption(String option) {
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {

				String object = "//a[text()='" + option + "']";
				WebElement findElement = driver.get().findElement(By.xpath(object));
				JavascriptExecutor js = (JavascriptExecutor) driver.get();
				js.executeScript("arguments[0].scrollIntoView(true);", findElement);
				js.executeScript("arguments[0].click();", findElement);
				clog.info(" dropDown value sucessfully selected " + option);
			}
		} catch (Exception e) {
			clog.info("dropDown value selected not sucessfully :" + option);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: selectFromList
	 * Arguments			: String [] , String
	 * Use 					: select the drop down value using list
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void selectFromList(List<WebElement> elements, String value) {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				for (WebElement ele : elements) {
					if (ele.getText().contains(value)) {
						ele.click();
						break;
					}
				}
			}
			clog.info(" dropDown value sucessfully selected " + value);
		} catch (Exception e) {
			clog.info("dropDown value selected not sucessfully :" + value);
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: alertAccept
	 * Arguments			: 
	 * Use 					: accept the alert
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void alertAccept() {

		try {
			//Wait.waitForPageToLoad();
			if (Continue.get()) {
				
				 WebDriverWait wait1 = new WebDriverWait(driver.get(), 50);
				 wait1.until(ExpectedConditions.alertIsPresent());
				clog.info(driver.get().switchTo().alert().getText());
				clog.info("alert is present");
				  driver.get().switchTo().alert().accept();
				 
				clog.info("alert accepted sucessfully");
			}

		} catch (Exception e) {
			clog.info("no alert present");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: alertWrite
	 * Arguments			: String 
	 * Use 					: write the the text to the alert
	 * Designed By			: JJ
	 * Last Modified Date 	: 27-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void alertWrite(String text) {

		try {

			if (Continue.get()) {
				 wait.until(ExpectedConditions.alertIsPresent());
				 clog.info("alert is present");
				Alert simpleAlert =  driver.get().switchTo().alert();
				simpleAlert.sendKeys(text);
				clog.info("value entered in alert sucessfully ::"+text);
				Thread.sleep(1000);
				simpleAlert.accept();
				clog.info("alert accepted sucessfully");
			}

		} catch (Exception e) {
			clog.info("no alert present");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: alertDecline
	 * Arguments			: 
	 * Use 					: decline the alert
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void alertDecline() {

		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				wait.until(ExpectedConditions.alertIsPresent());
				Alert simpleAlert = ((WebDriver) driver.get()).switchTo().alert();
				simpleAlert.dismiss();
				clog.info("alert declined sucessfully");
			}

		} catch (Exception e) {
			clog.info("no alert present");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getAlertText
	 * Arguments			: 
	 * Use 					: read the text from Alert
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String getAlertText() {
		String text = null;
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				wait.until(ExpectedConditions.alertIsPresent());
				Alert simpleAlert = ((WebDriver) driver.get()).switchTo().alert();
				text = simpleAlert.getText();
				clog.info("alert text retrived sucessfully  ::" + text);
			}

		} catch (Exception e) {
			clog.info("no alert present");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return text;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getAlertTextAndAccept
	 * Arguments			: 
	 * Use 					: read the text from Alert and accept
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String getAlertTextAndAccept() {
		String text = null;
		try {
		//	Wait.waitForPageToLoad();
			if (Continue.get()) {
				wait.until(ExpectedConditions.alertIsPresent());
				Alert simpleAlert = ((WebDriver) driver.get()).switchTo().alert();
				text = simpleAlert.getText();
				clog.info("alert text retrived sucessfully  ::" + text);
				simpleAlert.accept();
			}

		} catch (Exception e) {
			clog.info("no alert present");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return text;
	}
	
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: isAlertPresent
	 * Arguments			: 
	 * Use 					: check is Alert Present or not
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static Boolean isAlertPresent() {

		Boolean alertStatus=false;
		
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				 WebDriverWait wait1 = new WebDriverWait(driver.get(), 90);
				 wait1.until(ExpectedConditions.alertIsPresent());
				 clog.info(driver.get().switchTo().alert().getText());
				driver.get().switchTo().alert();
				alertStatus=true;
				clog.info("alert is present ");
			}

		} catch (Exception e) {
			
			alertStatus=false;
			clog.info("no alert present");
			
			
		}
		return alertStatus;
	}
	
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: isAlertPresentEnterText
	 * Arguments			: 
	 * Use 					: check is Alert Present and enter the value 
	 * Designed By			: JJ
	 * Last Modified Date 	: 09-08-2021
	--------------------------------------------------------------------------------------------------------*/

	public static Boolean isAlertPresentEnterText(String text) {

		Boolean alertStatus=false;
		
		try {
			Wait.waitForPageToLoad();
			if (Continue.get()) {
				 WebDriverWait wait1 = new WebDriverWait(driver.get(), 90);
				 wait1.until(ExpectedConditions.alertIsPresent());
				 Alert simpleAlert = ((WebDriver) driver.get()).switchTo().alert();
				 clog.info(simpleAlert.getText());
				 clog.info("alert is present ");
				 alertStatus=true;
				//driver.get().switchTo().alert();
				 Thread.sleep(2000);
				 simpleAlert.sendKeys(text);
				 clog.info("value entered in alert sucessfully ::"+text);
				 Thread.sleep(2000);
				 simpleAlert.accept();
				 clog.info("alert is accepted sucessfully ");
			}

		} catch (Exception e) {
			
			alertStatus=false;
			clog.info("no alert present");
			
		}
		return alertStatus;
		
	}
	
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: acceptIfPresent
	 * Arguments			: 
	 * Use 					: check is Alert Present or not
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static Boolean acceptIfPresent() {

		Boolean alertStatus=false;
		
		try {
		//	Wait.waitForPageToLoad();
			if (Continue.get()) {
				 WebDriverWait wait1 = new WebDriverWait(driver.get(), 50);
				 wait1.until(ExpectedConditions.alertIsPresent());
				 clog.info(driver.get().switchTo().alert().getText());
				 driver.get().switchTo().alert().accept();
				alertStatus=true;
				clog.info("alert is present ");
				
			}

		} catch (Exception e) {
			
			alertStatus=false;
			clog.info("no alert present");
			
			
		}
		return alertStatus;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: acceptIfPresent
	 * Arguments			: 
	 * Use 					: check is Alert Present or not
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String acceptIfPresentAndGetText() {

		String text = null;
		
		try {
		//	Wait.waitForPageToLoad();
			if (Continue.get()) {
				 WebDriverWait wait1 = new WebDriverWait(driver.get(), 50);
				 wait1.until(ExpectedConditions.alertIsPresent());
				 Alert simpleAlert = ((WebDriver) driver.get()).switchTo().alert();
				 text = simpleAlert.getText();
				 clog.info(driver.get().switchTo().alert().getText());
				 simpleAlert.accept();
				
				clog.info("alert is present ");
			}

		} catch (Exception e) {
			clog.info("no alert present");
			
		}
		return text;
	}

	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getColumnNumber
	 * Arguments			: String,String
	 * Use 					: get Column Number Web table
	 * Modified             : JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static int getColumnNumber(String objName, String columnName) {
		int columnNumber = 0;
		try {
			Wait.waitForPageToLoad();
			WebElement element = Web.getWebElement(objName, "WebTable");
			List<WebElement> columnElement = element.findElement(By.tagName("tr")).findElements(By.tagName("th"));

			for (; columnNumber < columnElement.size(); columnNumber++) {

				if (columnElement.get(columnNumber).getText().trim().equalsIgnoreCase(columnName.trim())) {
					//columnNumber=columnNumber;
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
		clog.info("columnNumber of ::"+columnName + ":: "+columnNumber);
		return columnNumber;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getColumnNumberContains
	 * Arguments			: String,String
	 * Use 					: get Column Number Web table contains Arabic
	 * Modified             : JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static int getColumnNumberContains(String objName, String columnName) {
		int columnNumber = 0;
		try {
			WebElement element = Web.getWebElement(objName, "WebTable");
			List<WebElement> columnElement = element.findElement(By.tagName("tr")).findElements(By.tagName("th"));

			for (; columnNumber < columnElement.size(); columnNumber++) {

				if (columnElement.get(columnNumber).getText().trim().contains(columnName.trim())) {
					
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

		return columnNumber;
	}

	/*------------------------------------------------------------------------------------------------------
	* Function Name: getRowCount
	* Arguments			: String
	* Use : get the row count of the given web table
	* Designed By: AG
	* Modified             : JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static int getRowCount(String objname) throws Exception {
		int rowcount = 0;
		try {
			
			WebElement element = Web.getWebElement(objname, "WebTable");
			List<WebElement> rows = element.findElements(By.tagName("tr"));
			rowcount = rows.size();
			clog.info(" :: Action getRowCount on Obj: " + rowcount);

		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to getRowCount");
			clog.info(" :: Failed at Obj: " + objname + " to getRowCount");

		}
		return rowcount;
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
		int rowNo = 1;
		try {
			WebElement element = Web.getWebElement(objName, "WebTable");
			int colNumber = getColumnNumber(objName, rowReference);
			int rowCount = getRowCount(objName);
			clog.info("total Row count  ::"+rowCount );
			for (int crrow = 1; crrow <= rowCount; crrow++) { 

				String cellValue = element.findElements(By.tagName("tr")).get(crrow).findElements(By.tagName("td"))
						.get(colNumber).getText();
				clog.info("cellValue  ::"+cellValue );
				if (cellValue.trim().equalsIgnoreCase(colName.trim())) {
					rowNo = crrow ;
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
		clog.info("Row Number of ::"+colName + ":: "+rowNo);
		return rowNo;
	}

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: getRowNumberContains
	* Arguments			: String,String,String
	* Use 					: get row Number Web table contains Arabic
	* Designed By			: AG
	* Modified             : JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static int getRowNumberContains(String objName, String rowReference, String colName) {
		int rowNo = 0;
		try {
			WebElement element = Web.getWebElement(objName, "WebTable");
			int colNumber = getColumnNumber(objName, colName);
			int rowCount = getRowCount(objName);
			for (int crrow = 0; crrow < rowCount; crrow++) {

				String cellValue = element.findElements(By.tagName("tr")).get(crrow).findElements(By.tagName("td"))
						.get(colNumber).getText();

				if (cellValue.trim().contains(rowReference.trim())) {
					rowNo = crrow ;
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

	/*------------------------------------------------------------------------------------------------------
	* Function Name: getWebTableCellData
	* Arguments			: String , integer, integer
	* Use :	returns the value in the given row and column of the web table
	* Designed By: JJ
	* Last Modified Date : 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static String getWebTableCellData(String objName, int rownum, int columnnum) throws Exception {
		try {
			
			
			WebElement element = Web.getWebElement(objName, "WebTable");
			String celldata = element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td"))
					.get(columnnum).getText();
			
				clog.info(" :: Action getCellData on Obj: " + objName + " :: "+celldata);
			return celldata;
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objName + " to getCellData");
			clog.info(" :: Failed at Obj: " + objName + " to getCellData");
			throw new Exception();
		}

	}

	/*------------------------------------------------------------------------------------------------------
	* Function Name: clickWebTable
	* Arguments			: String , integer, integer
	* Use :	click based on the given row and column of the web table
	* Designed By: JJ
	* Last Modified Date : 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickWebTable(String objName, int rownum, int columnnum) throws Exception {
		try {
			WebElement element = Web.getWebElement(objName, "WebTable");
			 element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td"))
					.get(columnnum).click();
			
			clog.info(" :: Action Click on Obj: " + objName);
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objName + " to click");
			clog.info(" :: Failed at Obj: " + objName + " to click");
			throw new Exception();
		}
	}

	/*------------------------------------------------------------------------------------------------------
	* Function Name: clickWithJSWebTable
	* Arguments			: String , integer, integer
	* Use : click with JS based on the given row and column of the web table
	* Designed By: JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickWithJSWebTable(String objname, int rownum, int columnnum) throws Exception {
		try {
			Wait.waitForPageToLoad();
			
			WebElement element = Web.getWebElement(objname, "WebTable");
			element = element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum);
			JavascriptExecutor js = (JavascriptExecutor) driver.get();
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			js.executeScript("arguments[0].click();", element);

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

	/*------------------------------------------------------------------------------------------------------
	* Function Name: clickLinkWithJSWebTable
	* Arguments			: String , integer, integer
	* Use : click the link with JS based on the given row and column of the web table
	* Designed By: JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickLinkJSWebTable(String objname, int rownum, int columnnum) throws Exception {
		try {
			Wait.waitForPageToLoad();
			WebElement element = Web.getWebElement(objname, "WebTable");
			element = element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).findElement(By.tagName("a"));
			JavascriptExecutor js = (JavascriptExecutor) driver.get();
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			js.executeScript("arguments[0].click();", element);
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

	/*------------------------------------------------------------------------------------------------------
	* Function Name: clickLinkWithOffsetWebTable
	* Arguments			: String , integer, integer
	* Use : click the link with Offset based on the given row and column of the web table
	* Designed             : JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickLinkWithOffsetWebTable(String objname, int rownum, int columnnum) throws Exception {
		Wait.waitForPageToLoad();
		WebElement element = Web.getWebElement(objname, "WebTable");
		element = element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).findElement(By.tagName("a"));
		Wait.waitForPageToLoad();
		Wait.waitUntillVisible(element);
		clickWithOffset(element);
		if (Continue.get() == false) {
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to click");
			clog.info(" :: Failed at Obj: " + objname + " to click");
			throw new Exception();
		} else {

			clog.info(" :: Action Click on Obj: " + objname);
		}
	}

	/*------------------------------------------------------------------------------------------------------
	* Function Name: clickOffsetWebTable
	* Arguments			: String , integer, integer
	* Use : click the data with Offset based on the given row and column of the web table
	* Modified             : JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickOffsetDataWebTable(String objName, int rownum, int columnnum) throws Exception {
		Wait.waitForPageToLoad();
		
		WebElement element = Web.getWebElement(objName, "WebTable");
		element= element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td"))
				.get(columnnum);
		
		
		 Wait.waitUntillVisible(element);
			clickWithOffset(element);
		 
		if (Continue.get() == false) {
			Result.takescreenshot(" :: Failed at Obj: " + objName + " to click");
			clog.info(" :: Failed at Obj: " + objName + " to click");
			throw new Exception();
		} else {

			clog.info(" :: Action Click on Obj: " + objName);
		}
	}

	/*------------------------------------------------------------------------------------------------------
	* Function Name: clickCheckBoxWebTable
	* Arguments			: String , integer, integer
	* Use : click the check box with Offset based on the given row and column of the web table
	* Designed By: JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickCheckBoxWebTable(String objname, int rownum, int columnnum) throws Exception {
		try {
			Wait.waitForPageToLoad();
			
			WebElement element = Web.getWebElement(objname, "WebTable");
	        element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).findElement(By.tagName("span")).findElement(By.tagName("input")).click();
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

	/*------------------------------------------------------------------------------------------------------
	* Function Name: clickDropDownWebTable
	* Arguments			: String , integer, integer
	* Use : click the dropdown with Offset based on the given row and column of the web table
	* Designed By: JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickDropDownWebTable(String objname, int rownum, int columnnum) throws Exception {
		try {
			Wait.waitForPageToLoad();
		
			WebElement element = Web.getWebElement(objname, "WebTable");
			element=element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).findElement(By.tagName("div"));
			JavascriptExecutor js = (JavascriptExecutor) driver.get();
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			js.executeScript("arguments[0].click();", element);

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

	/*------------------------------------------------------------------------------------------------------
	* Function Name: clickOffsetDropDownWebTable
	* Arguments			: String , integer, integer
	* Use : click the dropdown with Offset based on the given row and column of the web table
	* Designed By: JJ
	* Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickOffsetDropDownWebTable(String objname, int rownum, int columnnum) throws Exception {
		try {
			Wait.waitForPageToLoad();
		
			WebElement element = Web.getWebElement(objname, "WebTable");
			element=element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).findElement(By.tagName("div"));
			Wait.waitUntillVisible(element);
			clickWithOffset(element);
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

	/*------------------------------------------------------------------------------------------------------
	* Function Name: writeDataAndEnterWebTable
	* Arguments			: String , integer, integer
	* Use : write the text and press enter key based on the given row and column of the web table
	* Designed By: JJ
	* Last Modified Date :  18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void writeDataAndEnterWebTable(String objname, int rownum, int columnnum, String obj, String value)
			throws Exception {
		try {
			
			WebElement element = Web.getWebElement(objname, "WebTable");

			element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).click();

			
			element = Web.getWebElement(objname, "WebTable");
			
			//WebElement editBox=element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).findElement(By.xpath("//input[@name='" + textBoxName+ "']"));

			WebElement editBox=element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).findElement(By.xpath("//input"));
          //  String[] objprop = Utlities.FindObject(objname, "WebTable");
			
			//String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//input[@name='" + obj+ "']";
			

			writeAndEnter(editBox, value);

			clog.info(" :: Sucessfully enter value at Obj: " + objname + " :: " + value);
		} catch (Exception e) {
			Continue.set(false);
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objname + " to SetData "+value);
			clog.info(" :: Failed at Obj: " + objname + " to SetData  "+value);
			throw new Exception();
		}

	}

	/*------------------------------------------------------------------------------------------------------
	* Function Name: writeDataWebTable
	* Arguments			: String , integer, integer
	* Use : write the text based on the given row and column of the web table
	* Designed By: JJ
	* Last Modified Date :  18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void writeDataWebTable(String objname, int rownum, int columnnum, String obj, String val)
			throws Exception {
		try {
            WebElement element = Web.getWebElement(objname, "WebTable");
			

			element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).click();

			element = Web.getWebElement(objname, "WebTable");
			
			//WebElement editBox=element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).findElement(By.xpath("//input[@name='" + textBoxName+ "']"));

			WebElement editBox=element.findElements(By.tagName("tr")).get(rownum).findElements(By.tagName("td")).get(columnnum).findElement(By.xpath("//input"));
         
		//	String[] objprop = Utlities.FindObject(objname, "WebTable");
			
			//String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + columnnum + "]//input[@name='" + obj+ "']";
			
			
			clear(editBox);
			write(editBox, val);
			clog.info(" :: Sucessfully enter value at Obj: " + objname + " :: " + val);

		
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
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: moveToElement
	 * Arguments			: WebElement
	 * Use 					: move to the element based on the locator 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void moveToElement(WebElement element) {

		try {
			if (Continue.get()) {
				Actions action = new Actions(driver.get());
				Actions moveToElement = action.moveToElement(element);
				moveToElement.perform();
				clog.info("moved to the element successfully");
			}

		} catch (Exception e) {
			clog.info("unable to moved to the element");
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}

	}

}
