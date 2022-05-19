package Libraries;

import java.awt.AWTException;
import java.awt.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
//import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*---------------------------------------------------------------------------------------------------------
 * Class Name			: Method
 * Use 					: Has the functions to do operation on the objects on the web page 
 * Designed By			: AG
 * Last Modified Date 	: 25-Apr-2016
 --------------------------------------------------------------------------------------------------------*/
public class Method extends Driver {
	// private static Wait<WebDriver> wait=new WebDriverWait((WebDriver) driver,
	// waitTimeOut);
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: setTD
	 * Arguments			: identifier and value
	 * Use 					: Enters value in the Web Edit
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void setETD(String[] identify, String val) throws AWTException {
		screenLive();
		int i = 0;

		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[0])));
							driver.get().findElement(By.xpath(identify[0])).sendKeys(val);
							driver.get().findElement(By.xpath(identify[0])).click();
							driver.get().findElement(By.xpath(identify[0])).sendKeys(Keys.ENTER);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[1])));
							driver.get().findElement(By.name(identify[1])).sendKeys(val);
							driver.get().findElement(By.name(identify[1])).click();
							driver.get().findElement(By.name(identify[1])).sendKeys(Keys.ENTER);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[2])));
							driver.get().findElement(By.id(identify[2])).sendKeys(val);
							driver.get().findElement(By.id(identify[2])).click();
							driver.get().findElement(By.id(identify[2])).sendKeys(Keys.ENTER);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[3])));
							driver.get().findElement(By.className(identify[3])).sendKeys(val);
							driver.get().findElement(By.className(identify[3])).click();
							driver.get().findElement(By.className(identify[3])).sendKeys(Keys.ENTER);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[4])));
							driver.get().findElement(By.linkText(identify[4])).sendKeys(val);
							driver.get().findElement(By.linkText(identify[4])).click();
							driver.get().findElement(By.linkText(identify[4])).sendKeys(Keys.ENTER);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to set");
			clog.error(Result.getStackMsg(Error));
			// clog.error(Result.getStackMsg(Error));

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitForPageToLoad
	 * Arguments			: webdriver.get() and timeout in seconds
	 * Use 					: Checks for the browser ready state and waits for the page to load 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void waitForPageToLoad(WebDriver fdriver, int timeOutInSeconds) throws AWTException {
		screenLive();
		try {
			fdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver.get();
			String command = "return document.readyState";
			// Check the readyState before doing any waits
			if (js.executeScript(command).toString().equals("complete")) {
				return;
			}
			for (int i = 0; i < timeOutInSeconds; i++) {
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
	 * Method Name			: setdropvalue
	 * Arguments			: identifier and value
	 * Use 					: Enters value in the drop down 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void setdropvalue(String[] identify, String val) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length; i++) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							driver.get().findElement(By.xpath(identify[0])).click();
							driver.get().findElement(By.xpath(identify[0])).sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = driver.get().findElement(By.xpath(identify[0])).getAttribute("value");
								if (brcode.contains(val)) {
									driver.get().findElement(By.xpath(identify[0])).click();
									brfound = "yes";
									break;
								} else {
									driver.get().findElement(By.xpath(identify[0])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							driver.get().findElement(By.name(identify[1])).click();
							driver.get().findElement(By.name(identify[1])).sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = driver.get().findElement(By.name(identify[1])).getAttribute("value");
								if (brcode.contains(val)) {
									driver.get().findElement(By.name(identify[1])).click();
									brfound = "yes";
									break;
								} else {
									driver.get().findElement(By.name(identify[1])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							driver.get().findElement(By.id(identify[2])).click();
							driver.get().findElement(By.id(identify[2])).sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = driver.get().findElement(By.id(identify[2])).getAttribute("value");
								if (brcode.contains(val)) {
									driver.get().findElement(By.id(identify[2])).click();
									brfound = "yes";
									break;
								} else {
									driver.get().findElement(By.id(identify[2])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							driver.get().findElement(By.className(identify[3])).click();
							driver.get().findElement(By.className(identify[3])).sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = driver.get().findElement(By.className(identify[3]))
										.getAttribute("value");
								if (brcode.contains(val)) {
									driver.get().findElement(By.className(identify[3])).click();
									brfound = "yes";
									break;
								} else {
									driver.get().findElement(By.className(identify[3])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							driver.get().findElement(By.linkText(identify[4])).click();
							driver.get().findElement(By.linkText(identify[4])).sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = driver.get().findElement(By.linkText(identify[4]))
										.getAttribute("value");
								if (brcode.contains(val)) {
									driver.get().findElement(By.linkText(identify[4])).click();
									brfound = "yes";
									break;
								} else {
									driver.get().findElement(By.linkText(identify[4])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to set drop value");
			clog.error(Result.getStackMsg(Error));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: setTD
	 * Arguments			: identifier and value
	 * Use 					: Enters value in the Web Edit
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void setTD(String[] identify, String val) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[0])));
							driver.get().findElement(By.xpath(identify[0])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[1])));
							driver.get().findElement(By.name(identify[1])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[2])));
							driver.get().findElement(By.id(identify[2])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[3])));
							driver.get().findElement(By.className(identify[3])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							//// Scroll(driver.get().findElement(By.xpath(identify[4])));
							driver.get().findElement(By.linkText(identify[4])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to set");
			clog.error(Result.getStackMsg(Error));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: selectTD
	 * Arguments			: identifier and value
	 * Use 					: Selects value in the drop down
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void selectTD(String[] identify, String val) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							driver.get().findElement(By.xpath(identify[0])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							driver.get().findElement(By.name(identify[1])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							driver.get().findElement(By.id(identify[2])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							driver.get().findElement(By.className(identify[3])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							driver.get().findElement(By.linkText(identify[4])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to select");
			clog.error(Result.getStackMsg(Error));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickTD
	 * Arguments			: identifier
	 * Use 					: Click the Web element, Button or Link
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void clickTD(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// clog.info("link text is 1 " + identify[0]);
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							driver.get().findElement(By.xpath(identify[0])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							driver.get().findElement(By.name(identify[1])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							driver.get().findElement(By.id(identify[2])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							driver.get().findElement(By.className(identify[3])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						// clog.info("link text is " + identify[4]);
						if (identify[4] != "") {
							// clog.info("link text is " + identify[4]);
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							driver.get().findElement(By.linkText(identify[4])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void clearTD(String[] identify) throws AWTException {
		screenLive();

		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// clog.info("link text is 1 " + identify[0]);
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							driver.get().findElement(By.xpath(identify[0])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							driver.get().findElement(By.name(identify[1])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							driver.get().findElement(By.id(identify[2])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							driver.get().findElement(By.className(identify[3])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						// clog.info("link text is " + identify[4]);
						if (identify[4] != "") {
							// clog.info("link text is " + identify[4]);
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							driver.get().findElement(By.linkText(identify[4])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void waitForElementClickable(String[] identify) {
		// screenLive();
		int j = 0;
		boolean object_identified = false;

		Exception Error = null;
		for (j = 0; j < identify.length;) {
			try {
				// clog.info(String.valueOf(j));
				// clog.info(identify[i]);
				WebDriverWait wait = new WebDriverWait(driver.get(), 100);
				if (Continue.get() == true) {
					// clog.info(String.valueOf(identify.length));
					// clog.info(String.valueOf(j));
					switch (j) {
					case 0:
						// clog.info(identify[0]);
						// clog.info(String.valueOf(!identify[0].equalsIgnoreCase("")));
						if (!identify[0].equalsIgnoreCase("")) {
							// Scroll(driver.get().findElement(By.xpath(identify[0])));

							WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							object_identified = true;
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (!object_identified) {
							if (!identify[1].equalsIgnoreCase("")) {
								// Scroll(driver.get().findElement(By.xpath(identify[1])));
								WebElement findElement = driver.get().findElement(By.name(identify[1]));
								wait.until(ExpectedConditions.elementToBeClickable(findElement));
								object_identified = true;
								break;
							} else {
								throw new Exception(Error);
							}
						}
					case 2:
						if (!object_identified) {
							if (!identify[2].equalsIgnoreCase("")) {
								// Scroll(driver.get().findElement(By.xpath(identify[2])));
								WebElement findElement = driver.get().findElement(By.id(identify[2]));
								wait.until(ExpectedConditions.elementToBeClickable(findElement));
								object_identified = true;
								break;
							} else {
								throw new Exception(Error);
							}
						}
					case 3:
						if (!object_identified) {
							if (!identify[3].equalsIgnoreCase("")) {
								// Scroll(driver.get().findElement(By.xpath(identify[3])));
								WebElement findElement = driver.get().findElement(By.className(identify[3]));
								wait.until(ExpectedConditions.elementToBeClickable(findElement));
								object_identified = true;
								break;
							} else {
								throw new Exception(Error);
							}
						}
					case 4:
						if (!object_identified) {
							if (!identify[4].equalsIgnoreCase("")) {
								// Scroll(driver.get().findElement(By.xpath(identify[4])));
								WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
								wait.until(ExpectedConditions.elementToBeClickable(findElement));
								object_identified = true;
								break;
							} else {
								throw new Exception(Error);
							}
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				j++;
				Error = e;
				Continue.set(true);
			}
		}
		if (j == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}
	public static Boolean waitCheckEnable(String identify) throws AWTException {
		screenLive();
		boolean vis = false;
		WebDriverWait wait = new WebDriverWait(driver.get(), 10);
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		try {
		WebElement findElement = driver.get().findElement(By.xpath(identify));
		wait.until(ExpectedConditions.visibilityOf(findElement));
		//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		js.executeScript("arguments[0].scrollIntoView(true);", findElement);
		vis =driver.get().findElement(By.xpath(identify)).isEnabled();
		
		}catch(Exception e){
			vis = false;
		}
		return vis;
	}
	
	
	public static Boolean waitCheckDisplayed(String identify) throws AWTException {
		screenLive();
		boolean vis = false;
		WebDriverWait wait = new WebDriverWait(driver.get(), 10);
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		try {
		WebElement findElement = driver.get().findElement(By.xpath(identify));
		wait.until(ExpectedConditions.visibilityOf(findElement));
		//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		js.executeScript("arguments[0].scrollIntoView(true);", findElement);
		vis =driver.get().findElement(By.xpath(identify)).isDisplayed();
		
		}catch(Exception e){
			vis = false;
		}
		return vis;
	}
	
	
public static void clickTable(String identify) throws AWTException {
		
		WebDriverWait wait = new WebDriverWait(driver.get(), 100);
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		WebElement findElement = driver.get().findElement(By.xpath(identify));
		wait.until(ExpectedConditions.visibilityOf(findElement));
		js.executeScript("arguments[0].scrollIntoView(true);", findElement);
		wait.until(ExpectedConditions.elementToBeClickable(findElement));
		boolean enabled = findElement.isEnabled();
		findElement.click();
		
	}
	public static void waitForElementAnd_Scroll(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					WebDriverWait wait = new WebDriverWait(driver.get(), 100);
					JavascriptExecutor js = (JavascriptExecutor) driver.get();
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// clog.info("link text is 1 " + identify[0]);
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							WebElement findElement = driver.get().findElement(By.name(identify[1]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							WebElement findElement = driver.get().findElement(By.id(identify[2]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							WebElement findElement = driver.get().findElement(By.className(identify[3]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						// clog.info("link text is " + identify[4]);
						if (identify[4] != "") {
							// clog.info("link text is " + identify[4]);
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void clicktWithJavaScript(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		WebElement element = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							element = driver.get().findElement(By.xpath(identify[0]));
							// moveToElement(element);
							// waitUntilVisible(element);
							// waitUntilClickable(element);
							JavascriptExecutor executor = ((JavascriptExecutor) driver.get());
							executor.executeScript("arguments[0].click();", element);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							element = driver.get().findElement(By.name(identify[1]));
							// moveToElement(element);
							// waitUntilVisible(element);
							// waitUntilClickable(element);
							JavascriptExecutor executor = ((JavascriptExecutor) driver.get());
							executor.executeScript("arguments[0].click();", element);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							element = driver.get().findElement(By.id(identify[2]));
							// moveToElement(element);
							// waitUntilVisible(element);
							// waitUntilClickable(element);
							JavascriptExecutor executor = ((JavascriptExecutor) driver.get());
							executor.executeScript("arguments[0].click();", element);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							element = driver.get().findElement(By.className(identify[3]));
							/// moveToElement(element);
							// waitUntilVisible(element);
							// waitUntilClickable(element);
							JavascriptExecutor executor = ((JavascriptExecutor) driver.get());
							executor.executeScript("arguments[0].click();", element);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							element = driver.get().findElement(By.linkText(identify[4]));
							// moveToElement(element);
							// waitUntilVisible(element);
							// waitUntilClickable(element);
							JavascriptExecutor executor = ((JavascriptExecutor) driver.get());
							executor.executeScript("arguments[0].click();", element);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clearTD
	 * Arguments			: identifier
	 * Use 					: Clears the value in the web edit or drop down
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void clear_TD(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {

				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (!identify[0].equalsIgnoreCase("")) {
							driver.get().findElement(By.xpath(identify[0])).clear();
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (!identify[1].equalsIgnoreCase("")) {
							driver.get().findElement(By.name(identify[1])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (!identify[2].equalsIgnoreCase("")) {
							driver.get().findElement(By.id(identify[2])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (!identify[3].equalsIgnoreCase("")) {
							driver.get().findElement(By.className(identify[3])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (!identify[4].equalsIgnoreCase("")) {
							driver.get().findElement(By.linkText(identify[4])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to clear");
			clog.error(Result.getStackMsg(Error));
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getval
	 * Arguments			: identifier and value
	 * Use 					: reads value
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static String getText(String[] identify) throws AWTException {
		screenLive();
		String TxtVal = null;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							//TxtVal = driver.get().findElement(By.xpath(identify[0])).getAttribute("value");
							TxtVal = driver.get().findElement(By.xpath(identify[0])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							TxtVal = driver.get().findElement(By.name(identify[1])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							TxtVal = driver.get().findElement(By.id(identify[2])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							TxtVal = driver.get().findElement(By.className(identify[3])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							TxtVal = driver.get().findElement(By.linkText(identify[4])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to get val");
			clog.error(Result.getStackMsg(Error));
			return null;
		} else {
			return TxtVal;
		}
	}

	
	public static String getval(String[] identify) throws AWTException {
		screenLive();
		String TxtVal = null;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							TxtVal = driver.get().findElement(By.xpath(identify[0])).getAttribute("value");
							//TxtVal = driver.get().findElement(By.xpath(identify[0])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							TxtVal = driver.get().findElement(By.xpath(identify[0])).getAttribute("value");
							//TxtVal = driver.get().findElement(By.name(identify[1])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							TxtVal = driver.get().findElement(By.xpath(identify[0])).getAttribute("value");
							//TxtVal = driver.get().findElement(By.id(identify[2])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							TxtVal = driver.get().findElement(By.xpath(identify[0])).getAttribute("value");
							//TxtVal = driver.get().findElement(By.className(identify[3])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							TxtVal = driver.get().findElement(By.xpath(identify[0])).getAttribute("value");
							//TxtVal = driver.get().findElement(By.linkText(identify[4])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to get val");
			clog.error(Result.getStackMsg(Error));
			return null;
		} else {
			return TxtVal;
		}
	}
	public static String readValue(String identify) throws AWTException {
		String text = null;
		 WebDriverWait wait = new WebDriverWait(driver.get(), 100);
		JavascriptExecutor executor = ((JavascriptExecutor) driver.get());
		WebElement elem = driver.get().findElement(By.xpath(identify));
		 wait.until(ExpectedConditions.visibilityOf(elem));
        //driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       // executor.executeScript("arguments[0].scrollIntoView(true);", elem);
       // elem.isDisplayed();
		
		text= executor.executeScript("return arguments[0].textContent;", elem).toString();
		clog.info("value readed sucessfully :"+text);
		return text;
				
	}
	public static String get_text_(String[] identify) throws AWTException {
		screenLive();
		String TxtVal = null;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							TxtVal = driver.get().findElement(By.xpath(identify[0])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							TxtVal = driver.get().findElement(By.name(identify[1])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							TxtVal = driver.get().findElement(By.id(identify[2])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							TxtVal = driver.get().findElement(By.className(identify[3])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							TxtVal = driver.get().findElement(By.linkText(identify[4])).getText();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to get val");
			clog.error(Result.getStackMsg(Error));
			return null;
		} else {
			return TxtVal;
		}
	}

	public static String get_Attribute_(String[] identify, String attributeName) throws AWTException {
		screenLive();
		String TxtVal = null;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							TxtVal = driver.get().findElement(By.xpath(identify[0])).getAttribute(attributeName);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							TxtVal = driver.get().findElement(By.name(identify[1])).getAttribute(attributeName);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							TxtVal = driver.get().findElement(By.id(identify[2])).getAttribute(attributeName);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							TxtVal = driver.get().findElement(By.className(identify[3])).getAttribute(attributeName);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							TxtVal = driver.get().findElement(By.linkText(identify[4])).getAttribute(attributeName);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to get val");
			clog.error(Result.getStackMsg(Error));
			return null;
		} else {
			return TxtVal;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waittillobjvisible
	 * Arguments			: identifier and value
	 * Use 					: waits till the object exists
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	
		
	public static void waittillobjvisible(String[] identify) throws AWTException {
        screenLive();
        String vis = "false";
        int countval = 1;
        while (countval < 5) {
            int i = 0;
            for (i = 0; i < identify.length;) {
                try {
                    WebDriverWait wait = new WebDriverWait(driver.get(), 100);
                    JavascriptExecutor js = (JavascriptExecutor) driver.get();
                    switch (i) {
                    
                    case 0:
                        if (identify[0] != "") {
                            WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
                            wait.until(ExpectedConditions.visibilityOf(findElement));
                            driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                            js.executeScript("arguments[0].scrollIntoView(true);", findElement);
                            driver.get().findElement(By.xpath(identify[0])).isDisplayed();
                            break;
                        } else {
                            throw new Exception();
                        }
                    case 1:
                        if (identify[1] != "") {
                            WebElement findElement = driver.get().findElement(By.xpath(identify[1]));
                            wait.until(ExpectedConditions.visibilityOf(findElement));
                            driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                            js.executeScript("arguments[0].scrollIntoView(true);", findElement);
                            driver.get().findElement(By.name(identify[1])).isDisplayed();
                            break;
                        } else {
                            throw new Exception();
                        }
                    case 2:
                        if (identify[2] != "") {
                            WebElement findElement = driver.get().findElement(By.xpath(identify[2]));
                            wait.until(ExpectedConditions.visibilityOf(findElement));
                            driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                            js.executeScript("arguments[0].scrollIntoView(true);", findElement);
                            driver.get().findElement(By.id(identify[2])).isDisplayed();
                            break;
                        } else {
                            throw new Exception();
                        }
                    case 3:
                        if (identify[3] != "") {
                            WebElement findElement = driver.get().findElement(By.xpath(identify[3]));
                            wait.until(ExpectedConditions.visibilityOf(findElement));
                            driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                            js.executeScript("arguments[0].scrollIntoView(true);", findElement);
                            driver.get().findElement(By.className(identify[3])).isDisplayed();
                            break;
                        } else {
                            throw new Exception();
                        }
                    case 4:
                        if (identify[4] != "") {
                            WebElement findElement = driver.get().findElement(By.xpath(identify[4]));
                            wait.until(ExpectedConditions.visibilityOf(findElement));
                            driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                            js.executeScript("arguments[0].scrollIntoView(true);", findElement);
                            driver.get().findElement(By.linkText(identify[4])).isDisplayed();
                            break;
                        } else {
                            throw new Exception();
                        }
                    }
                    break;
                } catch (Exception e) {
                    i++;
                }
            }
            if (i == identify.length) {
                vis = "false";
                countval++;
            } else {
                vis = "true";
                break;
            }
        }
        if (vis == "false") {
            Continue.set(false);

 

            clog.info("Object does't Visible");
        }
    }

	public static boolean existobj(String[] identify) throws AWTException {
		screenLive();
		String vis = "false";
		int countval = 1;
		Exception Error = null;
		while (countval < 2) {
			int i = 0;
			for (i = 0; i < identify.length;) {
				try {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							driver.get().findElement(By.xpath(identify[0])).isDisplayed();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							driver.get().findElement(By.name(identify[1])).isDisplayed();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							driver.get().findElement(By.id(identify[2])).isDisplayed();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							driver.get().findElement(By.className(identify[3])).isDisplayed();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							driver.get().findElement(By.linkText(identify[4])).isDisplayed();
							break;
						} else {
							throw new Exception(Error);
						}
					}
					break;
				} catch (Exception e) {
					i++;
					Error = e;
					//clog.error(Result.getStackMsg(Error));
				}
			}
			if (i == identify.length) {
				vis = "false";
				countval++;
			} else {
				vis = "true";
				break;
			}
		}
		if (vis == "false") {
			return false;
		} else {
			return true;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Methodwaittillenabled
	 * Arguments			: identifier and value
	 * Use 					: waits till the object exists
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static boolean Methodwaittillenabled(String[] identify) throws AWTException {
		screenLive();
		String vis = "false";
		int countval = 1;
		while (countval < 2) {
			int i = 0;
			for (i = 0; i < identify.length;) {
				try {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							driver.get().findElement(By.xpath(identify[0])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 1:
						if (identify[1] != "") {
							driver.get().findElement(By.name(identify[1])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 2:
						if (identify[2] != "") {
							driver.get().findElement(By.id(identify[2])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 3:
						if (identify[3] != "") {
							driver.get().findElement(By.className(identify[3])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 4:
						if (identify[4] != "") {
							driver.get().findElement(By.linkText(identify[4])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					}
					break;
				} catch (Exception e) {
					clog.error(Result.getStackMsg(e));
					i++;
				}
			}
			if (i == identify.length) {
				vis = "false";
				countval++;
			} else {
				vis = "true";
				break;
			}
		}
		if (vis == "false") {
			return false;
		} else {
			return true;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Setvalue
	 * Arguments			: Update the tag value with the value in the testdata sheet
	 * Use 					: waits till the object exists
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static Document Setvalue(Document doc, String key, String val) throws AWTException {
		screenLive();
		NodeList nList = doc.getElementsByTagName(key);
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				nNode.setTextContent(val);
			}
		}
		return doc;
	}

	public static void Scroll(WebElement element) throws AWTException {
		screenLive();
		JavascriptExecutor jse = (JavascriptExecutor) driver.get();
		jse.executeScript("arguments[0].//ScrollIntoView();", element);
		driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	public static WebElement getWebElement(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		WebElement element = null;

		for (i = 0; i < identify.length;) {
			try {

				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (!identify[0].equalsIgnoreCase("")) {
							element = driver.get().findElement(By.xpath(identify[0]));

							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (!identify[1].equalsIgnoreCase("")) {
							element = driver.get().findElement(By.name(identify[1]));
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (!identify[2].equalsIgnoreCase("")) {
							element = driver.get().findElement(By.id(identify[2]));
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (!identify[3].equalsIgnoreCase("")) {
							element = driver.get().findElement(By.className(identify[3]));
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (!identify[4].equalsIgnoreCase("")) {
							element = driver.get().findElement(By.linkText(identify[4]));
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to clear");
			clog.error(Result.getStackMsg(Error));
		}

		return element;
	}

	public static void getAttribute(String tagName, String type) throws AWTException {
		screenLive();

		List<String> attribute = new ArrayList<String>();
		final List<WebElement> allElements = driver.get().findElements(By.tagName(tagName));
		for (WebElement element : allElements) {
			attribute.add(element.getAttribute(type));
		}
		for (String str : attribute) {
			System.out.println(str);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickTD
	 * Arguments			: identifier
	 * Use 					: Click the Web element, Button or Link
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void clickEnable(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							driver.get().findElement(By.xpath(identify[0])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							driver.get().findElement(By.name(identify[1])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							driver.get().findElement(By.id(identify[2])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							driver.get().findElement(By.className(identify[3])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							driver.get().findElement(By.linkText(identify[4])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void screenLive() throws AWTException {

		Actions acc = new Actions(driver.get()); 
		
		//acc.keyDown(keys.);
		
		//acc.keyUp(org.sikuli.hotkey.Keys.NUM_LOCK);

	// clog.info("screen live");

	}

	public static void taf_click(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								break;
							} catch (Exception e) {
								findElement.click();
								break;
							}
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							WebElement findElement = driver.get().findElement(By.name(identify[1]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								break;
							} catch (Exception e) {
								findElement.click();
								break;
							}

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							WebElement findElement = driver.get().findElement(By.id(identify[2]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								break;
							} catch (Exception e) {
								findElement.click();
								break;
							}

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							WebElement findElement = driver.get().findElement(By.className(identify[3]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								break;

							} catch (Exception e) {
								findElement.click();
								break;
							}

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								break;

							} catch (Exception e) {
								findElement.click();
								break;
							}

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void taf_click_J(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							WebElement findElement = driver.get().findElement(By.name(identify[1]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							WebElement findElement = driver.get().findElement(By.id(identify[2]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							WebElement findElement = driver.get().findElement(By.className(identify[3]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							break;

						} else {
							throw new Exception(Error);

						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void taf_click_(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							WebElement findElement = driver.get().findElement(By.name(identify[1]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							WebElement findElement = driver.get().findElement(By.id(identify[2]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							WebElement findElement = driver.get().findElement(By.className(identify[3]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void taf_edit(String[] identify, String value) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								executor.executeScript("arguments[0].setAttribute('value', '" + value + "');",
										findElement);
								break;
							} catch (Exception e) {
								findElement.click();
								findElement.sendKeys(value);
								break;
							}
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							WebElement findElement = driver.get().findElement(By.name(identify[1]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								executor.executeScript("arguments[0].setAttribute('value', '" + value + "');",
										findElement);
								break;
							} catch (Exception e) {
								findElement.click();
								findElement.sendKeys(value);
								break;
							}

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							WebElement findElement = driver.get().findElement(By.id(identify[2]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								executor.executeScript("arguments[0].setAttribute('value', '" + value + "');",
										findElement);
								break;
							} catch (Exception e) {
								findElement.click();
								findElement.sendKeys(value);
								break;
							}

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							WebElement findElement = driver.get().findElement(By.className(identify[3]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								executor.executeScript("arguments[0].setAttribute('value', '" + value + "');",
										findElement);
								break;
							} catch (Exception e) {
								findElement.click();
								findElement.sendKeys(value);
								break;
							}

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							try {
								JavascriptExecutor executor = (JavascriptExecutor) driver.get();
								executor.executeScript("arguments[0].click();", findElement);
								executor.executeScript("arguments[0].setAttribute('value', '" + value + "');",
										findElement);
								break;
							} catch (Exception e) {
								findElement.click();
								findElement.sendKeys(value);
								break;
							}

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}
	public static void waitForElementAnd_Scroll(String[] identify,String value) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					WebDriverWait wait = new WebDriverWait(driver.get(), 100);
					JavascriptExecutor js = (JavascriptExecutor) driver.get();
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// clog.info("link text is 1 " + identify[0]);
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							String object=identify[0].replace("dynamic", value);
							clog.info("xpath is : "+object);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							clog.info("xpath is Visible: "+object);
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							clog.info("xpath is clickable: "+object);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							//clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							WebElement findElement = driver.get().findElement(By.name(identify[1]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							WebElement findElement = driver.get().findElement(By.id(identify[2]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							WebElement findElement = driver.get().findElement(By.className(identify[3]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						// clog.info("link text is " + identify[4]);
						if (identify[4] != "") {
							// clog.info("link text is " + identify[4]);
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}
	public static void waitForElementAnd_Scroll(String obj) throws AWTException {
		screenLive();	
		WebDriverWait wait = new WebDriverWait(driver.get(), 100);
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		WebElement findElement = driver.get().findElement(By.xpath(obj));
		wait.until(ExpectedConditions.visibilityOf(findElement));
		driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		js.executeScript("arguments[0].scrollIntoView(true);", findElement);
		driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(findElement));
		driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		boolean enabled = findElement.isEnabled();
		clog.info("" + findElement + " is enabled : " + enabled);
		
	}
	public static void clickOffset(String identify) throws AWTException {
		WebElement elem = driver.get().findElement(By.xpath(identify));
	    int width = elem.getSize().getWidth();
	    Actions act = new Actions(driver.get());
	    act.moveToElement(elem).moveByOffset((width/2)-2, 0).click().perform();
		
	}
	
	public static Boolean waitCheckEnable(String[] identify) throws AWTException {
		screenLive();
		String vis = "false";
		int countval = 1;
		while (countval < 5) {
			int i = 0;
			for (i = 0; i < identify.length;) {
				try {
					WebDriverWait wait = new WebDriverWait(driver.get(), 10);
					JavascriptExecutor js = (JavascriptExecutor) driver.get();
					switch (i) {
					
					case 0:
						if (identify[0] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().findElement(By.xpath(identify[0])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 1:
						if (identify[1] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[1]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().findElement(By.name(identify[1])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 2:
						if (identify[2] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[2]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().findElement(By.id(identify[2])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 3:
						if (identify[3] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[3]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().findElement(By.className(identify[3])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 4:
						if (identify[4] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[4]));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							//driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().findElement(By.linkText(identify[4])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					}
					break;
				} catch (Exception e) {
					i++;
				}
			}
			if (i == identify.length) {
				vis = "false";
				countval++;
			} else {
				vis = "true";
				break;
			}
		}
		if (vis == "false") {
			return false;
		} else {
			return true;
		}
	}
	public static void clickDynamic(String[] identify, String obj) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				WebDriverWait wait = new WebDriverWait(driver.get(), 100);
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							
							String object=identify[0].replace("dynamic", obj);
							clog.info("xpath is : "+object);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							clog.info("xpath is Visible: "+object);
							driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							//js.executeScript("arguments[0].scrollIntoView(true);", findElement);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							clog.info("xpath is clickable: "+object);
							driver.get().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
							boolean enabled = findElement.isEnabled();
							clog.info("" + findElement + " is enabled : " + enabled);
							
							findElement.click();
							//executor.executeScript("arguments[0].click();", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							

							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							

							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}
	public static void taf_edit_J(String[] identify, String value) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							executor.executeScript("arguments[0].setAttribute('value', '" + value + "');", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							WebElement findElement = driver.get().findElement(By.name(identify[1]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							executor.executeScript("arguments[0].setAttribute('value', '" + value + "');", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							WebElement findElement = driver.get().findElement(By.id(identify[2]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							executor.executeScript("arguments[0].setAttribute('value', '" + value + "');", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							WebElement findElement = driver.get().findElement(By.className(identify[3]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							executor.executeScript("arguments[0].setAttribute('value', '" + value + "');", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							executor.executeScript("arguments[0].click();", findElement);
							executor.executeScript("arguments[0].setAttribute('value', '" + value + "');", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void taf_edit_(String[] identify, String value) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							WebElement findElement = driver.get().findElement(By.xpath(identify[0]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							findElement.sendKeys(value);
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							WebElement findElement = driver.get().findElement(By.name(identify[1]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							findElement.sendKeys(value);
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							WebElement findElement = driver.get().findElement(By.id(identify[2]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							findElement.sendKeys(value);
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							WebElement findElement = driver.get().findElement(By.className(identify[3]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							findElement.sendKeys(value);
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							findElement.click();
							findElement.sendKeys(value);
							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	
	public static void edit_Dynamic_J(String[] identify, String obj,String value) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("value", obj);
							clog.info("xpath is : "+object);
							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							//executor.executeScript(object, identify);
							executor.executeScript("arguments[0].click();", findElement);
							executor.executeScript("arguments[0].setAttribute('value', '" + value + "');", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							WebElement findElement = driver.get().findElement(By.name(identify[1]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							WebElement findElement = driver.get().findElement(By.id(identify[2]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							WebElement findElement = driver.get().findElement(By.className(identify[3]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							WebElement findElement = driver.get().findElement(By.linkText(identify[4]));
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							wait.until(ExpectedConditions.elementToBeClickable(findElement));

							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void Edit_dynamic_(String[] identify, String obj,String value) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("value",obj);
							clog.info("xpath is : "+object);
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							findElement.click();
							findElement.sendKeys(value);
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	
	public static void click_Dynamic_J(String[] identify, String obj) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("value", obj);
							clog.info("xpath is : "+object);
							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							//executor.executeScript(object, identify);
							executor.executeScript("arguments[0].click();", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							

							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							

							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}
	public static void click_Dynamic(String[] identify, String obj) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("dynamic", obj);
							clog.info("xpath is : "+object);
							JavascriptExecutor executor = (JavascriptExecutor) driver.get();
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							//executor.executeScript(object, identify);
							executor.executeScript("arguments[0].click();", findElement);
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							

							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							

							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static String getDynamicText(String[] identify,String value) throws AWTException {
		screenLive();
		String text=null;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					WebDriverWait wait = new WebDriverWait(driver.get(), 100);
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("dynamic", value);
							clog.info("xpath is : "+object);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.visibilityOf(findElement));
							text=findElement.getText();
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							

							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							

							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
		return text;
	
	}
	public static void click_dynamic_(String[] identify, String obj) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("value", obj);
							clog.info("xpath is : "+object);
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							findElement.click();
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	
	public static void bMS_Module_Bar(String[] identify, String objone, String obtwo) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("value", objone);
							String object2 = object.replace("secondalue", obtwo);
							clog.info("xpath is : "+object2);
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							WebElement findElement = driver.get().findElement(By.xpath(object2));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							findElement.click();
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void Eportal_LandingPage_Plans(String[] identify, String objone, String obtwo) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("value", objone);
							String object2 = object.replace("secondalue", obtwo);
							//plan Amount
					//		(//span[contains(text(),'value')]//following::small[contains(text(),'secondalue')][1]//parent::div//following-sibling::div//child::span)[1]
							//selecting plan
					//		(//span[contains(text(),'value')]//following::small[contains(text(),'secondalue')][1]//parent::div//preceding-sibling::div//parent::div/preceding-sibling::i)[1]
							//available limit
					//		((//span[contains(text(),'value')]//following::small[contains(text(),'secondalue')][1]//parent::div//preceding-sibling::div//parent::div/preceding-sibling::i)[1]//following::td[contains(text(),'Your available credit')])[2]//following-sibling::td//child::span
							//Renew Button
					//		((//span[contains(text(),'value')]//following::small[contains(text(),'secondalue')][1]//parent::div//preceding-sibling::div//parent::div/preceding-sibling::i)[1]//following::td[contains(text(),'Your available credit')])[2]//parent::tr//following::div[6]//child::a[contains(text(),'Renew Now')]
							clog.info("xpath is : "+object2);
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							WebElement findElement = driver.get().findElement(By.xpath(object2));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							findElement.click();
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}



	public static void bMS_Module_(String[] identify, String objone) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("value", objone);
							clog.info("xpath is : "+object);
							WebDriverWait wait = new WebDriverWait(driver.get(), 100);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							findElement.click();
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}


	
	public static void taf_sign(String[] identify, int x, int y, int x1, int y1, int x2, int y2, int x3, int y3, int x4,
			int y4, int x5, int y5, int x6, int y6, int x7, int y7, int x8, int y8) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							WebElement signatureWebElement = driver.get().findElement(By.xpath(identify[0]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", signatureWebElement);
							signatureWebElement.click();
							Actions builder = new Actions(driver.get());
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x1, y1)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x2, y2)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x3, y3)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x4, y4)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x5, y5)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x6, y6)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x7, y7)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x8, y8)
									.release().build().perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							WebElement signatureWebElement = driver.get().findElement(By.name(identify[1]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", signatureWebElement);
							signatureWebElement.click();
							Actions builder = new Actions(driver.get());
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x1, y1)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x2, y2)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x3, y3)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x4, y4)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x5, y5)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x6, y6)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x7, y7)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x8, y8)
									.release().build().perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							WebElement signatureWebElement = driver.get().findElement(By.id(identify[2]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", signatureWebElement);
							signatureWebElement.click();
							Actions builder = new Actions(driver.get());
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x1, y1)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x2, y2)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x3, y3)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x4, y4)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x5, y5)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x6, y6)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x7, y7)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x8, y8)
									.release().build().perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							WebElement signatureWebElement = driver.get().findElement(By.className(identify[3]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", signatureWebElement);
							signatureWebElement.click();
							Actions builder = new Actions(driver.get());
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x1, y1)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x2, y2)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x3, y3)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x4, y4)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x5, y5)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x6, y6)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x7, y7)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x8, y8)
									.release().build().perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							WebElement signatureWebElement = driver.get().findElement(By.linkText(identify[4]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", signatureWebElement);
							signatureWebElement.click();
							Actions builder = new Actions(driver.get());
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x1, y1)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x2, y2)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x3, y3)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x4, y4)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x5, y5)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x6, y6)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x7, y7)
									.release().build().perform();
							builder.clickAndHold().moveToElement(signatureWebElement, x, y).moveByOffset(x8, y8)
									.release().build().perform();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	public static void dropDownSelect(String[] identify, String value) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							WebElement dropdown = driver.get().findElement(By.xpath(identify[0]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
							dropdown.click();
							Select select = new Select(dropdown);
							select.selectByVisibleText(value);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							WebElement dropdown = driver.get().findElement(By.name(identify[1]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
							dropdown.click();
							Select select = new Select(dropdown);
							select.selectByVisibleText(value);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							WebElement dropdown = driver.get().findElement(By.id(identify[2]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
							dropdown.click();
							Select select = new Select(dropdown);
							select.selectByVisibleText(value);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							WebElement dropdown = driver.get().findElement(By.className(identify[3]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
							dropdown.click();
							Select select = new Select(dropdown);
							select.selectByVisibleText(value);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							WebElement dropdown = driver.get().findElement(By.linkText(identify[4]));
							JavascriptExecutor js = (JavascriptExecutor) driver.get();
							js.executeScript("arguments[0].scrollIntoView(true);", dropdown);
							dropdown.click();
							Select select = new Select(dropdown);
							select.selectByVisibleText(value);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}
	
	
	public static WebElement findElement(String[] identify) throws AWTException {
		screenLive();
		WebElement findElement = null;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							findElement = driver.get().findElement(By.xpath(identify[0]));
							clog.info("locator xpath is  :  "+identify[0] );
							break;
		
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							 findElement = driver.get().findElement(By.name(identify[1]));
							 clog.info(" locator name is  :  "+identify[1] );
							 break;
							
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							 findElement = driver.get().findElement(By.id(identify[2]));
							 clog.info("locator id is  :  "+identify[2] );
							 break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							 findElement = driver.get().findElement(By.className(identify[3]));
							 clog.info("locator className is  :  "+identify[3] );
							 break;
							
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							 findElement = driver.get().findElement(By.linkText(identify[4]));
							 clog.info("locator linktext is  :  "+identify[4] );
							 break;
							
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
		return findElement;
		
	}
	public static Boolean checkAvailable(String[] identify, String obj,int timeout) throws Exception {
		Boolean available=false;
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							String object=identify[0].replace("value", obj);
							clog.info("xpath is : "+object);
							WebDriverWait wait = new WebDriverWait(driver.get(), timeout);
							WebElement findElement = driver.get().findElement(By.xpath(object));
							wait.until(ExpectedConditions.elementToBeClickable(findElement));
							available=true;	
							break;

						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							
							break;

						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				clog.info(Result.getStackMsg(e));
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			available=false;	
		}
		return available;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickOffset
	 * Arguments			: identifier
	 * Use 					: Click the Web element, Button or Link
	 * Designed By			: JJ
	 * Last Modified Date 	: 09-05-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickOffset(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							
							WebElement elem = driver.get().findElement(By.xpath(identify[0]));
						    int width = elem.getSize().getWidth();
						    Actions act = new Actions(driver.get());
						    act.moveToElement(elem).moveByOffset((width/2)-2, 0).click().perform();
							
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							
							WebElement elem = driver.get().findElement(By.name(identify[1]));
						    int width = elem.getSize().getWidth();
						    Actions act = new Actions(driver.get());
						    act.moveToElement(elem).moveByOffset((width/2)-2, 0).click().perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							
							WebElement elem = driver.get().findElement(By.id(identify[2]));
						    int width = elem.getSize().getWidth();
						    Actions act = new Actions(driver.get());
						    act.moveToElement(elem).moveByOffset((width/2)-2, 0).click().perform();
							
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							
							WebElement elem = driver.get().findElement(By.className(identify[3]));
						    int width = elem.getSize().getWidth();
						    Actions act = new Actions(driver.get());
						    act.moveToElement(elem).moveByOffset((width/2)-2, 0).click().perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						// clog.info("link text is " + identify[4]);
						if (identify[4] != "") {
							
							WebElement elem = driver.get().findElement(By.linkText(identify[4]));
						    int width = elem.getSize().getWidth();
						    Actions act = new Actions(driver.get());
						    act.moveToElement(elem).moveByOffset((width/2)-2, 0).click().perform();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}

	
	public static boolean checkEnable(String[] identify) throws AWTException {
		Method.screenLive();
		boolean status = false;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// clog.info("link text is 1 " + identify[0]);
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							status=	driver.get().findElement(By.xpath(identify[0])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							status= driver.get().findElement(By.name(identify[1])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							status= driver.get().findElement(By.id(identify[2])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							status= driver.get().findElement(By.className(identify[3])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						// clog.info("link text is " + identify[4]);
						if (identify[4] != "") {
							// clog.info("link text is " + identify[4]);
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							status= driver.get().findElement(By.linkText(identify[4])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
		return status;
	}
	public static boolean checkDisplayed(String[] identify) throws AWTException {
		Method.screenLive();
		boolean status = false;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// clog.info("link text is 1 " + identify[0]);
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							clog.info("locator xpath is  :  "+identify[0] );
							status=	driver.get().findElement(By.xpath(identify[0])).isDisplayed();
							clog.info("locator xpath Status  :  "+status );
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							clog.info("locator name is  :  "+identify[1] );
							status= driver.get().findElement(By.name(identify[1])).isDisplayed();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							clog.info("locator id is  :  "+identify[2] );
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							status= driver.get().findElement(By.id(identify[2])).isDisplayed();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							clog.info("locator className is  :  "+identify[3] );
							status= driver.get().findElement(By.className(identify[3])).isDisplayed();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						// clog.info("link text is " + identify[4]);
						if (identify[4] != "") {
							// clog.info("link text is " + identify[4]);
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							clog.info("locator linkText is  :  "+identify[4] );
							status= driver.get().findElement(By.linkText(identify[4])).isDisplayed();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				status = false;
				Continue.set(false);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
		return status;
	}
	
	public static void clickDouble(String[] identify) throws AWTException {
		screenLive();
		int i = 0;
		Exception Error = null;
		Actions action = new Actions(driver.get());
		WebElement element= null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// clog.info("link text is 1 " + identify[0]);
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							element=driver.get().findElement(By.xpath(identify[0]));
							action.doubleClick(element).perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							element=driver.get().findElement(By.name(identify[1]));
							action.doubleClick(element).perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							element=driver.get().findElement(By.id(identify[2]));
							action.doubleClick(element).perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							element=driver.get().findElement(By.className(identify[3]));
							action.doubleClick(element).perform();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						// clog.info("link text is " + identify[4]);
						if (identify[4] != "") {
							// clog.info("link text is " + identify[4]);
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							element=driver.get().findElement(By.linkText(identify[4]));
							action.doubleClick(element).perform();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
	}
	public static boolean checkEnable(String[] identify,String value) throws AWTException {
		Method.screenLive();
		boolean status = false;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							// clog.info("link text is 1 " + identify[0]);
							// Scroll(driver.get().findElement(By.xpath(identify[0])));
							String object=identify[0].replace("dynamic", value);
							clog.info("xpath is : "+object);
							status=	driver.get().findElement(By.xpath(object)).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[1])));
							status= driver.get().findElement(By.name(identify[1])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[2])));
							status= driver.get().findElement(By.id(identify[2])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							// Scroll(driver.get().findElement(By.xpath(identify[3])));
							status= driver.get().findElement(By.className(identify[3])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						// clog.info("link text is " + identify[4]);
						if (identify[4] != "") {
							// clog.info("link text is " + identify[4]);
							// Scroll(driver.get().findElement(By.xpath(identify[4])));
							status= driver.get().findElement(By.linkText(identify[4])).isEnabled();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Continue.set(true);
			}
		}
		if (i == identify.length) {
			Continue.set(false);
			// Result.fUpdateLog("Object does't Exists to click");
			clog.error(Result.getStackMsg(Error));
		}
		return status;
	}
	
	public static boolean checkDynamicEnabled(String[] identify,String value) throws AWTException {
		screenLive();
		String vis = "false";
		int countval = 1;
		Exception Error = null;
		while (countval < 2) {
		int i = 0;
		for (i = 0; i < identify.length;) {
		try {
		switch (i) {
		case 0:
		if (identify[0] != "") {
		String object=identify[0].replace("dynamic", value);
		clog.info("xpath is : "+object);
		driver.get().findElement(By.xpath(object)).isEnabled();
		break;
		} else {
		throw new Exception(Error);
		}
		case 1:
		if (identify[1] != "") {
		driver.get().findElement(By.name(identify[1])).isEnabled();
		break;
		} else {
		throw new Exception(Error);
		}
		case 2:
		if (identify[2] != "") {
		driver.get().findElement(By.id(identify[2])).isEnabled();
		break;
		} else {
		throw new Exception(Error);
		}
		case 3:
		if (identify[3] != "") {
		driver.get().findElement(By.className(identify[3])).isEnabled();
		break;
		} else {
		throw new Exception(Error);
		}
		case 4:
		if (identify[4] != "") {
		driver.get().findElement(By.linkText(identify[4])).isEnabled();
		break;
		} else {
		throw new Exception(Error);
		}
		}
		break;
		} catch (Exception e) {
		i++;
		Error = e;
		clog.error(Result.getStackMsg(Error));
		}
		}
		if (i == identify.length) {
		vis = "false";
		countval++;
		} else {
		vis = "true";
		break;
		}
		}
		if (vis == "false") {
		return false;
		} else {
		return true;
		}
		}

//	public static MobileElement getMobileElement(String[] identify,String value) {
//		int i = 0;
//		Exception Error = null;
//		MobileElement element = null;
//		//clog.info(identify[0]);
//		//clog.info(identify[1]);
//		//clog.info(identify[2]);
//		//clog.info(identify[3]);
//		//clog.info(identify[4]);
//		for (i = 0; i < identify.length;) {
//		try {
//
//		 if (Continue.get() == true) {
//		switch (i) {
//		case 0:
//		if(identify[0] != null)
//		{
//		if (!identify[0].equalsIgnoreCase("")) {
//		String object=identify[0].replace("value", value);
//		element = (MobileElement) new WebDriverWait(driver.get(), waitTimeOut)
//		.until(ExpectedConditions.presenceOfElementLocated(MobileBy.xpath(object)));
//		break;
//		} else {
//		throw new Exception(Error);
//		}
//		}
//		else
//		{
//		throw new Exception(Error);
//		}
//		case 1:
//		if(identify[1] != null)
//		{
//		if (!identify[1].equalsIgnoreCase("")) {
//
//		 element = (MobileElement) new WebDriverWait(driver.get(), waitTimeOut)
//		.until(ExpectedConditions.presenceOfElementLocated(MobileBy.name(identify[1])));
//
//		 break;
//		} else {
//		throw new Exception(Error);
//		}
//		}
//		else
//		{
//		throw new Exception(Error);
//		}
//		case 2:
//		if(identify[2] != null)
//		{
//		if (!identify[2].equalsIgnoreCase("")) {
//
//		 element = (MobileElement) new WebDriverWait(driver.get(), waitTimeOut)
//		.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(identify[2])));
//		break;
//		} else {
//		throw new Exception(Error);
//		}
//		}
//		else
//		{
//		throw new Exception(Error);
//		}
//		case 3:
//		if(identify[3] != null)
//		{
//		if (!identify[3].equalsIgnoreCase("")) {
//
//		 element = (MobileElement) new WebDriverWait(driver.get(), waitTimeOut).until(
//		ExpectedConditions.presenceOfElementLocated(MobileBy.className(identify[3])));
//		break;
//		} else {
//		throw new Exception(Error);
//		}
//		}
//		else
//		{
//		throw new Exception(Error);
//		}
//		case 4:
//		if(identify[4] != null)
//		{
//		if (!identify[4].equalsIgnoreCase("")) {
//
//		 element = (MobileElement) new WebDriverWait(driver.get(), waitTimeOut)
//		.until(ExpectedConditions.presenceOfElementLocated(MobileBy.linkText(identify[4])));
//		break;
//		} else {
//		throw new Exception(Error);
//		}
//		}
//		else
//		{
//		throw new Exception(Error);
//		}
//		}
//		}
//		break;
//		} catch (Exception e) {
//		//clog.info(Result.getStackMsg(e));
//		i++;
//		Error = e;
//		Continue.set(true);
//		}
//		if(element != null)
//		{
//		break;
//		}
//		}
//		if (i == identify.length) {
//		Continue.set(false);
//		// Result.fUpdateLog("Object does't Exists to clear");
//		clog.error(Result.getStackMsg(Error));
//		}
//
//		 return element;
//		}
//	
	
	/*----------------------------------------------------------------------------*/
	/*
	 * public static void waitForPageLoad() { ExpectedCondition<Boolean> expect =
	 * new ExpectedCondition<Boolean>() { public Boolean apply(WebDriver driver) {
	 * return ((JavascriptExecutor)
	 * driver).executeScript("return document.readyState").equals("complete"); } };
	 * Wait<WebDriver> wait = new WebDriverWait(driver.get(),waitTimeOut); try {
	 * wait.until(expect); } catch (Exception E) {
	 * 
	 * } }
	 * 
	 * /*---
	 */
	/*
	 * public static void waitUntilVisible(String[] identify) { int i = 0; Exception
	 * Error = null; WebElement element=null; for (i = 0; i < identify.length;) {
	 * try { if (Continue.get() == true) { switch (i) { case 0: if (identify[0] !=
	 * "") { element = driver.get().findElement(By.xpath(identify[0]));
	 * wait.until(ExpectedConditions.visibilityOf(element)); break; } else { throw
	 * new Exception(Error); } case 1: if (identify[1] != "") { element =
	 * driver.get().findElement(By.name(identify[1]));
	 * wait.until(ExpectedConditions.visibilityOf(element)); break; } else { throw
	 * new Exception(Error); } case 2: if (identify[2] != "") { element =
	 * driver.get().findElement(By.id(identify[2]));
	 * wait.until(ExpectedConditions.visibilityOf(element)); break; } else { throw
	 * new Exception(Error); } case 3: if (identify[3] != "") { element =
	 * driver.get().findElement(By.className(identify[3]));
	 * wait.until(ExpectedConditions.visibilityOf(element)); break; } else { throw
	 * new Exception(Error); } case 4: if (identify[4] != "") { element =
	 * driver.get().findElement(By.linkText(identify[4]));
	 * wait.until(ExpectedConditions.visibilityOf(element)); break; } else { throw
	 * new Exception(Error); } } } break; } catch (Exception e) { i++; Error = e;
	 * Continue.set(true); } } if (i == identify.length) { Continue.set(false);
	 * //Result.fUpdateLog("Object does't Exists to click");
	 * clog.error(Result.getStackMsg(Error)); } }
	 */
	/*---*/
	/*
	 * public static void waitUntilClickable(String[] identify) { int i = 0;
	 * Exception Error = null; WebElement element=null; for (i = 0; i <
	 * identify.length;) { try { if (Continue.get() == true) { switch (i) { case 0:
	 * if (identify[0] != "") { element =
	 * driver.get().findElement(By.xpath(identify[0]));
	 * wait.until(ExpectedConditions.elementToBeClickable(element)); break; } else {
	 * throw new Exception(Error); } case 1: if (identify[1] != "") { element =
	 * driver.get().findElement(By.name(identify[1]));
	 * wait.until(ExpectedConditions.elementToBeClickable(element)); break; } else {
	 * throw new Exception(Error); } case 2: if (identify[2] != "") { element =
	 * driver.get().findElement(By.id(identify[2]));
	 * wait.until(ExpectedConditions.elementToBeClickable(element)); break; } else {
	 * throw new Exception(Error); } case 3: if (identify[3] != "") { element =
	 * driver.get().findElement(By.className(identify[3]));
	 * wait.until(ExpectedConditions.elementToBeClickable(element)); break; } else {
	 * throw new Exception(Error); } case 4: if (identify[4] != "") { element =
	 * driver.get().findElement(By.linkText(identify[4]));
	 * wait.until(ExpectedConditions.elementToBeClickable(element)); break; } else {
	 * throw new Exception(Error); } } } break; } catch (Exception e) { i++; Error =
	 * e; Continue.set(true); } } if (i == identify.length) { Continue.set(false);
	 * //Result.fUpdateLog("Object does't Exists to click");
	 * clog.error(Result.getStackMsg(Error)); } }
	 */
}