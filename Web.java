package Libraries;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.HttpCommandExecutor;


public class Web extends Driver {

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getWebElement
	 * Arguments			: objectName  objectType
	 * Use 					: find the element based on the locator 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static WebElement getWebElement(String objectName, String objectType) {
		String[] identifier = Utlities.FindObject(objectName, objectType);
		WebElement element = null;
		try {
			if (Continue.get()) {
				if (!identifier[0].equals("")) {
					element = driver.get().findElement(By.xpath(identifier[0]));
					clog.info("xpath --> " + identifier[0] + "retrived successfully");
				} else if (!identifier[1].equals("")) {
					element = driver.get().findElement(By.name(identifier[1]));
					clog.info("name --> " + identifier[1] + "retrived successfully");
				} else if (!identifier[2].equals("")) {
					element = driver.get().findElement(By.id(identifier[2]));
					clog.info("id --> " + identifier[2] + "retrived successfully");
				} else if (!identifier[3].equals("")) {
					element = driver.get().findElement(By.className(identifier[3]));
					clog.info("className --> " + identifier[3] + "retrived successfully");
				} else if (!identifier[4].equals("")) {
					element = driver.get().findElement(By.linkText(identifier[4]));
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
	 * Method Name			: getDynamicWebElement
	 * Arguments			: objectName  objectType dynamicValue
	 * Use 					: find the dynamic element 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static WebElement getDynamicWebElement(String objectName, String objectType, String dynamicValue) {
		String[] identifier = Utlities.FindObject(objectName, objectType);
		WebElement element = null;
		try {
			if (Continue.get()) {
				if (!identifier[0].equals("")) {
					String object = identifier[0].replace("dynamicValue", dynamicValue);
					clog.info("xpath is : " + object);
					element = driver.get().findElement(By.xpath(object));
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
	 * Method Name			: getDynamicWebElement
	 * Arguments			: identifiers
	 * Use 					: find the element based on the locator 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static WebElement getDynamicWebElement(String[] identifiers, String dynamicValue) {
		WebElement element = null;
		try {
			if (Continue.get()) {
				if (!identifiers[0].equals("")) {
					String object = identifiers[0];
					clog.info("My xpath is : " + object);
					clog.info("My dynamicValue is : " + dynamicValue);
				    String object1 = object.replace("dynamicValue", dynamicValue);
					clog.info("xpath is modified : " + object1);
					element = driver.get().findElement(By.xpath(object1));
					clog.info("xpath --> " + object1 + "retrived successfully");
				}
			}

		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return element;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getDynamicWebElements
	 * Arguments			: identifiers
	 * Use 					: find the element based on the locator 
	 * Designed By			: senthil
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static List<WebElement> getDynamicWebElements(String[] identifiers, String dynamicValue) {
		List<WebElement> element = null;
		try {
			if (Continue.get()) {
				if (!identifiers[0].equals("")) {
					String object = identifiers[0].replace("dynamicValue", dynamicValue);
					clog.info("xpath is : " + object);
					element = driver.get().findElements(By.xpath(object));
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
	 * Method Name			: getWebElement
	 * Arguments			: identifiers
	 * Use 					: find the element based on the locator 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static WebElement getWebElement(String[] identifiers) {
		WebElement element = null;
		try {
			if (Continue.get()) {
				if (!identifiers[0].equals("")) {
					element = driver.get().findElement(By.xpath(identifiers[0]));
					clog.info("xpath --> " + identifiers[0] + "retrived successfully");
				} else if (!identifiers[1].equals("")) {
					element = driver.get().findElement(By.name(identifiers[1]));
					clog.info("name --> " + identifiers[1] + "retrived successfully");
				} else if (!identifiers[2].equals("")) {
					element = driver.get().findElement(By.id(identifiers[2]));
					clog.info("id --> " + identifiers[2] + "retrived successfully");
				} else if (!identifiers[3].equals("")) {
					element = driver.get().findElement(By.className(identifiers[3]));
					clog.info("className --> " + identifiers[3] + "retrived successfully");
				} else if (!identifiers[4].equals("")) {
					element = driver.get().findElement(By.linkText(identifiers[4]));
					clog.info("linkText --> " + identifiers[4] + "retrived successfully");
				}
			}

		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return element;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getWebElements
	 * Arguments			: identifiers
	 * Use 					: find the list element based on the locator 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static List<WebElement> getWebElements(String[] identifiers) {
		
		List<WebElement> elements = null;

		try {
			if (Continue.get()) {
				if (!identifiers[0].equals("")) {
					elements = driver.get().findElements(By.xpath(identifiers[0]));
					clog.info("xpath --> " + identifiers[0] + "retrived successfully");
				} else if (!identifiers[1].equals("")) {
					elements = driver.get().findElements(By.name(identifiers[1]));
					clog.info("name --> " + identifiers[1] + "retrived successfully");
				} else if (!identifiers[2].equals("")) {
					elements = driver.get().findElements(By.id(identifiers[2]));
					clog.info("id --> " + identifiers[2] + "retrived successfully");
				} else if (!identifiers[3].equals("")) {
					elements = driver.get().findElements(By.className(identifiers[3]));
					clog.info("className --> " + identifiers[3] + "retrived successfully");
				} else if (!identifiers[4].equals("")) {
					elements = driver.get().findElements(By.linkText(identifiers[4]));
					clog.info("linkText --> " + identifiers[4] + "retrived successfully");
				}
			}

		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return elements;

	}
	/*---------------------------------------------------------------------------------------------------------
     * Method Name            : getWebElement
     * Arguments            : identifiers
     * Use                     : find the element based on the locator 
     * Designed By            : Sathya Lenin EARC
     * Last Modified Date     : 22-06-2021
    --------------------------------------------------------------------------------------------------------*/
    public static int getWebElementsSize (String[] identifiers) {
        int size = 0;
        try {
            if (Continue.get()) {
                if (!identifiers[0].equals("")) {
                    size = driver.get().findElements(By.xpath(identifiers[0])).size();
                    clog.info("xpath --> " + identifiers[0] + "retrived successfully");
                } else if (!identifiers[1].equals("")) {
                    size = driver.get().findElements(By.name(identifiers[1])).size();
                    clog.info("name --> " + identifiers[1] + "retrived successfully");
                } else if (!identifiers[2].equals("")) {
                    size = driver.get().findElements(By.id(identifiers[2])).size();
                    clog.info("id --> " + identifiers[2] + "retrived successfully");
                } else if (!identifiers[3].equals("")) {
                    size = driver.get().findElements(By.className(identifiers[3])).size();
                    clog.info("className --> " + identifiers[3] + "retrived successfully");
                } else if (!identifiers[4].equals("")) {
                    size = driver.get().findElements(By.linkText(identifiers[4])).size();
                    clog.info("linkText --> " + identifiers[4] + "retrived successfully");
                }
            }

 

        } catch (Exception e) {
            Continue.set(false);
            clog.error(Result.getStackMsg(e));
        }
        return size;

 

    }
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getWebElements
	 * Arguments			: objectName  objectType
	 * Use 					: find the list element based on the locator 
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static List<WebElement> getWebElements(String objectName, String objectType) {
		String[] identifier = Utlities.FindObject(objectName, objectType);
		List<WebElement> elements = null;
		try {
			if (Continue.get()) {
				if (!identifier[0].equals("")) {
					elements = driver.get().findElements(By.xpath(identifier[0]));
					clog.info("xpath --> " + identifier[0] + "retrived successfully");
				} else if (!identifier[1].equals("")) {
					elements = driver.get().findElements(By.name(identifier[1]));
					clog.info("name --> " + identifier[1] + "retrived successfully");
				} else if (!identifier[2].equals("")) {
					elements = driver.get().findElements(By.id(identifier[2]));
					clog.info("id --> " + identifier[2] + "retrived successfully");
				} else if (!identifier[3].equals("")) {
					elements = driver.get().findElements(By.className(identifier[3]));
					clog.info("className --> " + identifier[3] + "retrived successfully");
				} else if (!identifier[4].equals("")) {
					elements = driver.get().findElements(By.linkText(identifier[4]));
					clog.info("linkText --> " + identifier[4] + "retrived successfully");
				}
			}

		} catch (Exception e) {
			Continue.set(false);
			clog.error(Result.getStackMsg(e));
		}
		return elements;

	}

	

	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: WebEdit 
	 * Use 					: Subclass of browser class represents the WebEdit in the application and 
	 * 						  contains functions for all the operations performed on web edit   
	 * Designed By			: JJ
	 * Last Modified Date 	: 18-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static class WebEdit {

		/*---------------------------------------------------------------------------------------------------------
		* Method Name			: writeAndEnter
		* Arguments			: String where to write , String what to write
		* Use 					: Wait and click the field and clear the existing text and write the text in the element and perform enter key action 
		* Designed By			: JJ
		* Last Modified Date 	: 18-06-2021
		--------------------------------------------------------------------------------------------------------*/

		public static void writeAndEnter(String objectName, String objectValue) throws Exception {
			String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
			Wait.waitUntillClickable(getWebElement(objectProperty));
			WebAction.click(getWebElement(objectProperty));
			WebAction.clear(getWebElement(objectProperty));
			WebAction.writeAndEnter(getWebElement(objectProperty), objectValue);
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objectName + " - to set Value: " + objectValue);
				clog.info(" :: Failed at Obj: " + objectName + " - to set Value: " + objectValue);
				throw new Exception();
			} else {
				clog.info(" :: Action SetText on Obj: " + objectName + " - Value: " + objectValue);
			}

		}
		
		/*---------------------------------------------------------------------------------------------------------
	  	 * Method Name			: writeWithJS
	  	 * Arguments			: String where to write , String what to write
	  	 * Use 					: Write the text in the element 
	  	 * Designed By			: JJ 
	  	 * Last Modified Date 	: 01-07-2021
	  	--------------------------------------------------------------------------------------------------------*/
	   
		public static void writeWithJS(String objectName,String objectValue) throws Exception {
			String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
			Wait.waitUntillClickable(getWebElement(objectProperty));
			WebAction.clickWithJS(getWebElement(objectProperty));
			WebAction.writeWithJS(getWebElement(objectProperty), objectValue);
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
				clog.info(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
				throw new Exception();
			} else {
				clog.info(" :: Action SetText on Object: " + objectName + " - Value: " + objectValue);
			}
		}
		
		/*---------------------------------------------------------------------------------------------------------
	  	 * Method Name			: writeEnterWithJS
	  	 * Arguments			: String where to write , String what to write
	  	 * Use 					: Write the text in the element 
	  	 * Designed By			: JJ 
	  	 * Last Modified Date 	: 01-07-2021
	  	--------------------------------------------------------------------------------------------------------*/
	   
		public static void writeEnterWithJS(String objectName,String objectValue) throws Exception {
			String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
			Wait.waitUntillClickable(getWebElement(objectProperty));
			WebAction.clickWithJS(getWebElement(objectProperty));
			WebAction.writeEnterWithJS(getWebElement(objectProperty), objectValue);
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
				clog.info(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
				throw new Exception();
			} else {
				clog.info(" :: Action SetText on Object: " + objectName + " - Value: " + objectValue);
			}
		}
		/*---------------------------------------------------------------------------------------------------------
		* Method Name			: writeAndEnter
		* Arguments			: String where to write , String what to write
		* Use 					: Wait and click the field and clear the existing text and write the text in the element and perform enter key action 
		* Designed By			: JJ
		* Last Modified Date 	: 18-06-2021
		--------------------------------------------------------------------------------------------------------*/

		public static void clearEnter(String objectName, String objectValue) throws Exception {
			String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
			Wait.waitUntillClickable(getWebElement(objectProperty));
			WebAction.clear(getWebElement(objectProperty));
			WebAction.writeAndEnter(getWebElement(objectProperty), objectValue);
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at Obj: " + objectName + " - to set Value: " + objectValue);
				clog.info(" :: Failed at Obj: " + objectName + " - to set Value: " + objectValue);
				throw new Exception();
			} else {
				clog.info(" :: Action SetText on Obj: " + objectName + " - Value: " + objectValue);
			}

		}

		/*---------------------------------------------------------------------------------------------------------
		* Method Name			: click
		* Arguments			: String 
		* Use 					: Wait until element is ready to to click and click
		* Designed By			: JJ
		* Last Modified Date 	: 19-06-2021
		--------------------------------------------------------------------------------------------------------*/

		public static void click(String objectName) throws Exception {
			String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
			Wait.waitUntillClickable(getWebElement(objectProperty));
			WebAction.click(getWebElement(objectProperty));
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed to click " + objectName);
				clog.info(" ::Failed to click " + objectName);
				throw new Exception();
			} else {
				clog.info(" :: Action click on Obj: " + objectName);
			}
		}

	

	/*---------------------------------------------------------------------------------------------------------
	* Method Name			: dynamicClick
	* Arguments			: String , 
	* Use 					: Wait until element is ready to to click and click
	* Designed By			: JJ
	* Last Modified Date 	: 19-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void dynamicClick(String objectName, String value) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty, value));
		WebAction.click(getDynamicWebElement(objectProperty, value));
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to click " + objectName);
			clog.info(" ::Failed to click " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action click on Obj: " + objectName);
		}	
		
	}
	
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clickWithJS
  	 * Arguments			: ObjectName to identify and click the element 
  	 * Use 					: Wait and click the specified element using clickWithJS
  	 * Designed By			: Soniya 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clickWithJS(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.clickWithJS(getWebElement(objectProperty));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to clickWithJS Object: " + objectName );
			clog.info(" :: Failed to clickWithJS Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Clicked with JavaScriptExecutor on Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clickWithOffset
  	 * Arguments			: ObjectName to identify and click the element
  	 * Use 					: Wait and click the specified element using clickWithOffset
  	 * Designed By			: Soniya 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clickWithOffset(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.clickWithOffset(getWebElement(objectProperty));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to clickWithOffset Object: " + objectName );
			clog.info(" :: Failed to clickWithOffset Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Clicked with Offset on Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: doubleClick
  	 * Arguments			: ObjectName to identify and click the element
  	 * Use 					: Wait and click the specified element using doubleClick
  	 * Designed By			: Soniya 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void doubleClick(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.doubleClick(getWebElement(objectProperty));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to doubleClick Object: " + objectName );
			clog.info(" :: Failed to doubleClick Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Actions DoubleClicked Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clickAction
  	 * Arguments			: ObjectName to identify and click the element
  	 * Use 					: Wait and click the specified element using clickAction
  	 * Designed By			: Soniya 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clickAction(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.clickAction(getWebElement(objectProperty));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to clickAction Object: " + objectName );
			clog.info(" :: Failed to clickAction Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Actions click on Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: pressEnter
  	 * Arguments			: ObjectName to identify and click the element
  	 * Use 					: Wait and click the specified element using pressEnter
  	 * Designed By			: Soniya 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void pressEnter(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.pressEnter(getWebElement(objectProperty));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to pressEnter Object: " + objectName );
			clog.info(" :: Failed to pressEnter Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Pressed Enter on Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clear
  	 * Arguments			: ObjectName to identify and clear the field
  	 * Use 					: Wait and click the specified element using pressEnter
  	 * Designed By			: Soniya 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clear(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.clear(getWebElement(objectProperty));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to clear Object: " + objectName );
			clog.info(" :: Failed to clear Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Cleared the Object: " + objectName );
		}

	}
	
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: write
  	 * Arguments			: String where to write , String what to write
  	 * Use 					: Write the text in the element 
  	 * Designed By			: Soniya 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void write(String objectName,String objectValue) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.write(getWebElement(objectProperty), objectValue);
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			clog.info(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			throw new Exception();
		} else {
			clog.info(" :: Action SetText on Object: " + objectName + " - Value: " + objectValue);
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clearWrite
  	 * Arguments			: String where to write , String what to write
  	 * Use 					: Clear and write the text in the element 
  	 * Designed By			: Soniya 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clearWrite(String objectName,String objectValue) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.clear(getWebElement(objectProperty));
		WebAction.write(getWebElement(objectProperty), objectValue);
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			clog.info(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			throw new Exception();
		} else {
			clog.info(" :: Cleared and SetText on Object: " + objectName + " - Value: " + objectValue);
		}
	 }
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: readText
  	 * Arguments			: String to get text
  	 * Use 					: Wait and retrive the text from the Object
  	 * Designed By			: Danny Thanaraj
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static String readText(String objectName) throws Exception {
		String readText = null;
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillVisible(getWebElement(objectProperty));
		readText = WebAction.readText(getWebElement(objectProperty));

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to retrive Value");
			clog.info(" :: Failed at Object: " + objectName + " - to retrive Value");
			throw new Exception();
		} else {
			clog.info(readText + " :: is the value retrived from: " + objectName);
		}
		return readText;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: readTextWithJS
  	 * Arguments			: String to get text
  	 * Use 					: Wait and retrive the text from the Object using JavaScript
  	 * Designed By			: Danny Thanaraj
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static String readTextWithJS(String objectName) throws Exception {
		String readText = null;
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillVisible(getWebElement(objectProperty));
		readText = WebAction.readTextWithJS(getWebElement(objectProperty));

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to retrive Value");
			clog.info(" :: Failed at Object: " + objectName + " - to retrive Value");
			throw new Exception();
		} else {
			clog.info(readText + " :: is the value retrived from: " + objectName);
		}
		return readText;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: readAttributeValue
  	 * Arguments			: String to get attribute value
  	 * Use 					: Wait and retrive the value from the attribute
  	 * Designed By			: Danny Thanaraj
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static String readAttributeValue(String objectName) throws Exception {
		String readText = null;
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillVisible(getWebElement(objectProperty));
		readText = WebAction.readAttributeValue(getWebElement(objectProperty));

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to retrive Value");
			clog.info(" :: Failed at Object: " + objectName + " - to retrive Value");
			throw new Exception();
		} else {
			clog.info(readText + " :: is the value retrived from: " + objectName);
		}
		return readText;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: getAttributeValue
  	 * Arguments			: String to get corresponding attribute value
  	 * Use 					: Wait and retrive the value from the Object's corresponding attribute
  	 * Designed By			: Danny Thanaraj
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static String readAttribute(String objectName, String objectValue) throws Exception {
		String readAttribute = null;
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Wait.waitUntillVisible(getWebElement(objectProperty));
		readAttribute = WebAction.readAttribute(getWebElement(objectProperty), objectValue);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to retrive Value");
			clog.info(" :: Failed at Object: " + objectName + " - to retrive Value");
			throw new Exception();
		} else {
			clog.info(readAttribute + " :: is the value retrived from: " + objectName);
		}
		return readAttribute;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: isEnabled
  	 * Arguments			: String
  	 * Use 					: To check whether the Object enabled or not 
  	 * Designed By			: Danny Thanaraj
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	 public static boolean isEnabled(String objectName) throws Exception {
	        boolean isEnabled = false;
	        isEnabled = Wait.checkEnable(objectName, "WebEdit");        
	        if (!Continue.get()) {
	            if (!methodScreenShotTaken.get()) {
	                Result.Methodscreenshot();
	            }
	            Result.takescreenshot(" :: Failed to check object : " + objectName + " is Enabled or not");
	            clog.info(" :: Failed to check object : " + objectName + " is Enabled or not");
	            throw new Exception();
	        } else {
	            clog.info(objectName + "is Enabled : " + isEnabled);
	        }
	        return isEnabled;
	    }
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: isAvailable
  	 * Arguments			: String
  	 * Use 					: To check whether the Object available or not 
  	 * Designed By			: Danny Thanaraj
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static boolean isAvailable(String objectName) throws Exception {
		boolean isAvailable = false;
		isAvailable = Wait.checkAvailable(objectName, "WebEdit");
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
			clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
			throw new Exception();
		} else {
			clog.info(objectName + "is Enabled : " + isAvailable);
		}
		return isAvailable;
	}
	
	}
	
	/*---------------------------------------------------------------------------------------------------------
	* Class Name : WebButton
	* Use : Subclass of browser class represents the WebButton in the application and
	* contains functions for all the operations performed on Web Button
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/	
	public static class WebButton{
		/*---------------------------------------------------------------------------------------------------------
		* Method Name : click
		* Arguments : String to click
		* Use : To wait till object get clickable and click the object
		* Designed By : Danny Thanaraj
		* Last Modified Date : 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		public static void click(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebButton");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.click(getWebElement(objectProperty));

		 if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
		Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed to click " + objectName);
		clog.info(" ::Failed to click " + objectName);
		throw new Exception();
		} else {
		clog.info(" :: Clicked on Object: " + objectName);
		}

		 }
		/*---------------------------------------------------------------------------------------------------------
		* Method Name : clickWithJS
		* Arguments : String to click
		* Use : To wait till object get clickable and click the object using JavaScript
		* Designed By : Danny Thanaraj
		* Last Modified Date : 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		public static void clickWithJS(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebButton");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.clickWithJS(getWebElement(objectProperty));

		 if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
		Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed to click " + objectName);
		clog.info(" ::Failed to click " + objectName);
		throw new Exception();
		} else {
		clog.info(" :: Clicked with JavaScript Executor on Object: " + objectName);
		}

		 }
		/*---------------------------------------------------------------------------------------------------------
		* Method Name : clickWithOffset
		* Arguments : String to click
		* Use : To wait till object get clickable and click the object using action class(moveToElement)
		* Designed By : Danny Thanaraj
		* Last Modified Date : 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		public static void clickWithOffset(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebButton");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.clickWithOffset(getWebElement(objectProperty));

		 if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
		Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed to click " + objectName);
		clog.info(" ::Failed to click " + objectName);
		throw new Exception();
		} else {
		clog.info(" :: Action click on Object: " + objectName);
		}

		 }
		/*---------------------------------------------------------------------------------------------------------
		* Method Name : doubleClick
		* Arguments : String to click
		* Use : To wait till object get clickable and click the object using action class (doubleClick)
		* Designed By : Danny Thanaraj
		* Last Modified Date : 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		public static void doubleClick(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebButton");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.doubleClick(getWebElement(objectProperty));
		if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
		Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed to click " + objectName);
		clog.info(" ::Failed to click " + objectName);
		throw new Exception();
		} else {
		clog.info(" :: Actions double clicked on Object: " + objectName);
		}

		 }
		/*---------------------------------------------------------------------------------------------------------
		* Method Name : clickAction
		* Arguments : String to click
		* Use : To wait till object get clickable and click the object using action class(Click)
		* Designed By : Danny Thanaraj
		* Last Modified Date : 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		public static void clickAction(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebButton");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.clickAction(getWebElement(objectProperty));
		if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
		Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed to click " + objectName);
		clog.info(" ::Failed to click " + objectName);
		throw new Exception();
		} else {
		clog.info(" :: Actions clicked on Object: " + objectName);
		}
		}
		/*---------------------------------------------------------------------------------------------------------
		* Method Name : pressEnter
		* Arguments : String to click
		* Use : To wait till object get clickable and click the object by sending Virtual Key command
		* Designed By : Danny Thanaraj
		* Last Modified Date : 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		public static void pressEnter(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebButton");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.pressEnter(getWebElement(objectProperty));

		 if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
		Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed to click " + objectName);
		clog.info(" ::Failed to click " + objectName);
		throw new Exception();
		} else {
		clog.info(" :: Pressed ENTER on Object: " + objectName);
		}
		}
		/*---------------------------------------------------------------------------------------------------------
		* Method Name : isEnabled
		* Arguments : String
		* Use : To check whether the ObjectName enabled or not
		* Designed By : Danny Thanaraj
		* Last Modified Date : 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		 public static boolean isEnabled(String objectName) throws Exception {
		        boolean isEnabled = false;
		        isEnabled = Wait.checkEnable(objectName, "WebButton"); 
		        if (!Continue.get()) {
		            if (!methodScreenShotTaken.get()) {
		                Result.Methodscreenshot();
		            }
		            Result.takescreenshot(" :: Failed to check object : " + objectName + " is Enabled or not");
		            clog.info(" :: Failed to check object : " + objectName + " is Enabled or not");
		            throw new Exception();
		        } else {
		            clog.info(objectName + "is Enabled : " + isEnabled);
		        }
		        return isEnabled;
		    }
		/*---------------------------------------------------------------------------------------------------------
		* Method Name : isAvailable
		* Arguments : String
		* Use : To check whether the ObjectName available or not
		* Designed By : Danny Thanaraj
		* Last Modified Date : 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		 public static boolean isAvailable(String objectName) throws Exception {
				boolean isAvailable = false;
				isAvailable = Wait.checkAvailable(objectName, "WebButton");
				if (!Continue.get()) {
					if (!methodScreenShotTaken.get()) {
						Result.Methodscreenshot();
					}
					Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
					clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
					throw new Exception();
				} else {
					clog.info(objectName + "is Enabled : " + isAvailable);
				}
				return isAvailable;
			}
			
			}
	
	
	/*---------------------------------------------------------------------------------------------------------
	* Class Name : WebLink
	* Use : Subclass of browser class represents the WebLink in the application and
	* contains functions for all the operations performed on web link
	* Designed By : Shivashankar Palaniappan
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/


	public static class WebLink {
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : click
	* Arguments : String where to click
	* Use : Click the link and perform click action
	* Designed By : Shivashankar Palniappan
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void click(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebLink");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.click(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName + " - to click: ");
	clog.info(" :: Failed at Object: " + objectName + " - to Click: ");
	throw new Exception();
	} else {
	clog.info(" :: Clicked on Object: " + objectName );
	}

	 }
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : clickWithJS
	* Arguments : String where to clickjs
	* Use : ClickJS the link and perform click action
	* Designed By : Shivashankar Palniappan
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickWithJS(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebLink");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.clickWithJS(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName + " - to click with JavaScript Executor");
	clog.info(" :: Failed at Object: " + objectName + " - to click with JavaScript Executor");
	throw new Exception();
	} else {
	clog.info(" :: clicked with JavaScript Executor on Object: " + objectName );
	}

	 }

	/*---------------------------------------------------------------------------------------------------------
	* Method Name : clickWithOffset
	* Arguments : String where to click the offset
	* Use : Click the object with offset value
	* Designed By : Shivashankar Palniappan
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickWithOffset(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebLink");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.clickWithOffset(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName + " - to Clickoffset: ");
	clog.info(" :: Failed at Object: " + objectName + " - to Clickoffset: ");
	throw new Exception();
	} else {
	clog.info(" :: clicked with Offset on Object: " + objectName );
	}

	 }

	/*---------------------------------------------------------------------------------------------------------
	* Method Name : doubleClick
	* Arguments : String where to double click
	* Use : Click the link and perform double click
	* Designed By : Shivashankar Palniappan
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void doubleClick(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebLink");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.doubleClick(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName + " - to doubleClick");
	clog.info(" :: Failed at Object: " + objectName + " - to doubleClick ");
	throw new Exception();
	} else {
	clog.info(" :: Actions Double clicked on Object: " + objectName );
	}

	 }
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : clickAction
	* Arguments : String where to perform clickAction
	* Use : Click the link and perform clickAction
	* Designed By : Shivashankar Palniappan
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void clickAction(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebLink");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.clickAction(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName + " - to Actions click");
	clog.info(" :: Failed at Object: " + objectName + " - to Actions click");
	throw new Exception();
	} else {
	clog.info(" :: Actions clicked on Object: " + objectName );
	}

	 }

	/*---------------------------------------------------------------------------------------------------------
	* Method Name : pressEnter
	* Arguments : String where to perform pressEnter
	* Use : Click the link and perform pressEnter
	* Designed By : Shivashankar Palniappan
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static void pressEnter(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebLink");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.pressEnter(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName + " - to press Enter");
	clog.info(" :: Failed at Object: " + objectName + " - to press Enter ");
	throw new Exception();
	} else {
	clog.info(" :: Pressed ENTER on Object: " + objectName );
	}

	 }

	/*---------------------------------------------------------------------------------------------------------
	* Method Name : isEnabled
	* Arguments : String where to perform isEnabled
	* Use : Whether the element is enabled or disabled
	* Designed By : Shivashankar Palniappan
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	 public static boolean isEnabled(String objectName) throws Exception {
	        boolean isEnabled = false;
	        isEnabled = Wait.checkEnable(objectName, "WebLink"); 
	        if (!Continue.get()) {
	            if (!methodScreenShotTaken.get()) {
	                Result.Methodscreenshot();
	            }
	            Result.takescreenshot(" :: Failed to check object : " + objectName + " is Enabled or not");
	            clog.info(" :: Failed to check object : " + objectName + " is Enabled or not");
	            throw new Exception();
	        } else {
	            clog.info(objectName + "is Enabled : " + isEnabled);
	        }
	        return isEnabled;
	    }

	/*---------------------------------------------------------------------------------------------------------
	* Method Name : isAvailable
	* Arguments : String where to perform isAvailable
	* Use : Whether the element is available or not
	* Designed By : Shivashankar Palniappan
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	 public static boolean isAvailable(String objectName) throws Exception {
			boolean isAvailable = false;
			isAvailable = Wait.checkAvailable(objectName, "WebLink");
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
				clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
				throw new Exception();
			} else {
				clog.info(objectName + "is Enabled : " + isAvailable);
			}
			return isAvailable;
		}
		
		}
	
	/*---------------------------------------------------------------------------------------------------------
	* Class Name : ListBox
	* Use : Subclass of browser class represents the ListBox in the application and
	* contains functions for all the operations performed on List Box 
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	
	public static class ListBox {


		/*---------------------------------------------------------------------------------------------------------
		 * Method Name			: setDropValue
		 * Arguments			: String where to write , String what to select
		 * Use 					: Wait and select the drop down with suitable value
		 * Designed By			: Sathya Lenin EARC
		 * Last Modified Date 	: 20-06-2021
		--------------------------------------------------------------------------------------------------------*/

		public static void setDropValue(String objectName, String objectValue) throws Exception {
			String[] objectProperty = Utlities.FindObject(objectName, "ListBox");
			WebAction.setDropValue(objectProperty, objectValue);
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(
						" :: Failed at the Drop down object: " + objectName + " - to select Value: " + objectValue);
				clog.info(" :: Failed at the Drop down object: " + objectName + " - to select Value: " + objectValue);
				throw new Exception();
			} else {
				clog.info(" :: Selected the Drop down object : " + objectName + " - with Value: " + objectValue);
			}

		}

		/*---------------------------------------------------------------------------------------------------------
		 * Method Name			: dropDownSelectIndex
		 * Arguments			: String where to write , int what value to select by the index number
		 * Use 					: Wait and select the drop down with suitable value by index number
		 * Designed By			: Sathya Lenin EARC
		 * Last Modified Date 	: 20-06-2021
		--------------------------------------------------------------------------------------------------------*/

		public static void dropDownSelectIndex(String objectName, int index) throws Exception {
			String[] objectProperty = Utlities.FindObject(objectName, "ListBox");
			Wait.waitUntillClickable(getWebElement(objectProperty));
			WebAction.dropDownSelectIndex(getWebElement(objectProperty), index);
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(
						" :: Failed at the Drop down object: " + objectName + " - to select value by index : " + index);
				clog.info(
						" :: Failed at the Drop down object: " + objectName + " - to select value by index : " + index);
				throw new Exception();
			} else {
				clog.info(" :: Selected the Drop down object : " + objectName + " - with Value by index: " + index);
			}

		}

		/*---------------------------------------------------------------------------------------------------------
		 * Method Name			: dropDownSelectText
		 * Arguments			: String where to write , String what value to select by visible text
		 * Use 					: Wait and select the drop down with suitable value by visible text
		 * Designed By			: Sathya Lenin EARC
		 * Last Modified Date 	: 20-06-2021
		--------------------------------------------------------------------------------------------------------*/

		public static void dropDownSelectText(String objectName, String objectValue) throws Exception {
			String[] objectProperty = Utlities.FindObject(objectName, "ListBox");
			Wait.waitUntillClickable(getWebElement(objectProperty));
			WebAction.dropDownSelectText(getWebElement(objectProperty), objectValue);
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed at the Drop down object: " + objectName
						+ " - to select value by the visible text : " + objectValue);
				clog.info(" :: Failed at the Drop down object: " + objectName
						+ " - to select value by the visible text : " + objectValue);
				throw new Exception();
			} else {
				clog.info(" :: Selected the Drop down object : " + objectName + " - with Value by the visible text: "
						+ objectValue);
			}

		}

	

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: dropDownSelectValue
	 * Arguments			: String where to write , String what value to select by value
	 * Use 					: Wait and select the drop down with suitable value by value
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void dropDownSelectValue(String objectName, String objectValue) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "ListBox");
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.dropDownSelectValue(getWebElement(objectProperty), objectValue);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at the Drop down object: " + objectName
					+ " - to select value by the value : " + objectValue);
			clog.info(" :: Failed at the Drop down object: " + objectName + " - to select value by the value : "
					+ objectValue);
			throw new Exception();
		} else {
			clog.info(
					" :: Selected the Drop down object : " + objectName + " - with Value by the value: " + objectValue);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: selectOption
	 * Arguments			: String where to write , String what option to select
	 * Use 					: Wait and select the drop down with suitable value by value
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

    public static void selectOption( String objectValue) throws Exception {
		
		WebAction.selectOption(objectValue);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at the Drop down object: " + objectValue
					+ " - to select value by the option : " + objectValue);
			clog.info(" :: Failed at the Drop down object: " + objectValue + " - to select value by the option : "
					+ objectValue);
			throw new Exception();
		} else {
			clog.info(" :: Selected the Drop down object : " + objectValue + " - with Value by the option: "
					+ objectValue);
		}

	}

    /*---------------------------------------------------------------------------------------------------------
	 * Method Name			: selectFromList
	 * Arguments			: String where to write , String what option to select from the list
	 * Use 					: Wait and select the drop down with suitable value from the list
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void selectFromList(String objectName, String objectValue) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "ListBox");
		
		WebAction.selectFromList(getWebElements(objectProperty), objectValue);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at the Drop down object: " + objectName
					+ " - to select value from the list : " + objectValue);
			clog.info(" :: Failed at the Drop down object: " + objectName + " - to select value from the list : "
					+ objectValue);
			throw new Exception();
		} else {
			clog.info(" :: Selected the Drop down object : " + objectName + " - with Value from the list: "
					+ objectValue);
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: isEnabled
	 * Arguments			: String where to check
	 * Use 					: Wait and check the object is enabled or not
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	 public static boolean isEnabled(String objectName) throws Exception {
	        boolean isEnabled = false;
	        isEnabled = Wait.checkEnable(objectName, "ListBox"); 
	        if (!Continue.get()) {
	            if (!methodScreenShotTaken.get()) {
	                Result.Methodscreenshot();
	            }
	            Result.takescreenshot(" :: Failed to check object : " + objectName + " is Enabled or not");
	            clog.info(" :: Failed to check object : " + objectName + " is Enabled or not");
	            throw new Exception();
	        } else {
	            clog.info(objectName + "is Enabled : " + isEnabled);
	        }
	        return isEnabled;
	    }

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: isAvailable
	 * Arguments			: String where to check
	 * Use 					: Wait and check the object is Available or not
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	 public static boolean isAvailable(String objectName) throws Exception {
			boolean isAvailable = false;
			isAvailable = Wait.checkAvailable(objectName, "ListBox");
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
				clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
				throw new Exception();
			} else {
				clog.info(objectName + "is Enabled : " + isAvailable);
			}
			return isAvailable;
		}
		
		

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: readText
	 * Arguments			: String where to read Text
	 * Use 					: Wait and read the text of the object
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String readText(String objectName) throws Exception {
		String readText = null;
		String[] objectProperty = Utlities.FindObject(objectName, "ListBox");
		Wait.waitUntillVisible(getWebElement(objectProperty));
		readText = WebAction.readText(getWebElement(objectProperty));
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to read Text at object : " + objectName);
			clog.info(" :: Failed to read Text at object : " + objectName);
			throw new Exception();
		} else {
			clog.info("The text in the object : " + objectName + " is : " + readText);
		}
		return readText;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: readTextWithJS
	 * Arguments			: String where to read Text using Java Script Executor
	 * Use 					: Wait and read the text of the object using Java Script Executor
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String readTextWithJS(String objectName) throws Exception {
		String readText = null;
		String[] objectProperty = Utlities.FindObject(objectName, "ListBox");
		Wait.waitUntillVisible(getWebElement(objectProperty));
		readText = WebAction.readTextWithJS(getWebElement(objectProperty));
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to read Text at object :  " + objectName);
			clog.info(" :: Failed to read Text at object : " + objectName);
			throw new Exception();
		} else {
			clog.info("The text in the object : " + objectName + " is : " + readText);
		}
		return readText;

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: readAttributeValue
	 * Arguments			: String where to read the Attribute value, String the Attribute
	 * Use 					: Wait and read the Attribute value for the object on the given attribute name
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static String readAttributeValue(String objectName, String objectValue) throws Exception {
		String readAttribute = null;
		String[] objectProperty = Utlities.FindObject(objectName, "ListBox");
		Wait.waitUntillVisible(getWebElement(objectProperty));
		readAttribute = WebAction.readAttribute(getWebElement(objectProperty), objectValue);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to read the Attribute value at object: " + objectName);
			clog.info(" :: Failed to read the Attribute value at object: " + objectName);
			throw new Exception();
		} else {
			clog.info("The Attribute value in the object : " + objectName + " is : " + readAttribute);
		}
		return readAttribute;

	}


	}
	
	/*---------------------------------------------------------------------------------------------------------
	* Class Name : WebRadioButton
	* Use : Subclass of browser class represents the WebRadioButton in the application and
	* contains functions for all the operations performed on WebRadioButton
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static class WebRadioButton {
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : click
	* Arguments : String to identify and click the element
	* Use : wait and click the element
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void click(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebRadioButton");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.click(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName);
	clog.info(" :: Failed at Object: " + objectName );
	throw new Exception();
	} else {
	clog.info(" :: Clicked on Object: " + objectName);
	}

	 }
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : clickWithJS
	* Arguments : String to identify and click the element
	* Use : wait and click the element with Java script executor
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickWithJS(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebRadioButton");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.clickWithJS(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName);
	clog.info(" :: Failed at Object: " + objectName );
	throw new Exception();
	} else {
	clog.info(" :: Clicked with JavaScript Executor on Object : " + objectName);
	}

	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : clickWithOffset
	* Arguments : String to identify and click the element
	* Use : wait and click the element with Offset
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickWithOffset(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebRadioButton");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.clickWithOffset(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName);
	clog.info(" :: Failed at Object: " + objectName );
	throw new Exception();
	} else {
	clog.info(" :: Clicked with Offset on Object: " + objectName);
	}

	 }
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : doubleClick
	* Arguments : String to identify and double click the element
	* Use : wait and doubleClick the element with Action class
	* Designed By : Senthil
	* Last Modified Date : 20-06-20211
	--------------------------------------------------------------------------------------------------------*/

	public static void doubleClick(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebRadioButton");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.doubleClick(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName);
	clog.info(" :: Failed at Object: " + objectName );
	throw new Exception();
	} else {
	clog.info(" :: Actions DoubleClicked on Object : " + objectName);
	}

	 }
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : clickAction
	* Arguments : String to identify and click the element with Action class
	* Use : wait and click the element with Action class
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void clickAction(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebRadioButton");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.clickAction(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName);
	clog.info(" :: Failed at Object: " + objectName );
	throw new Exception();
	} else {
	clog.info(" :: Actions Clicked on Object: " + objectName);
	}

	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : pressEnter
	* Arguments : String to identify and click the element with Action class
	* Use : wait and press Enter on the element with Action class
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	public static void pressEnter(String objectName) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebRadioButton");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.pressEnter(getWebElement(objectProperty));
	if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
	Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName);
	clog.info(" :: Failed at Object: " + objectName );
	throw new Exception();
	} else {
	clog.info(" :: PressedEnter on Object : " + objectName);
	}

	}
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : isEnabled
	* Arguments : String to identify the element
	* Use : To check whether the Object enabled or not
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	 public static boolean isEnabled(String objectName) throws Exception {
	        boolean isEnabled = false;
	        isEnabled = Wait.checkEnable(objectName, "WebRadioButton"); 
	        if (!Continue.get()) {
	            if (!methodScreenShotTaken.get()) {
	                Result.Methodscreenshot();
	            }
	            Result.takescreenshot(" :: Failed to check object : " + objectName + " is Enabled or not");
	            clog.info(" :: Failed to check object : " + objectName + " is Enabled or not");
	            throw new Exception();
	        } else {
	            clog.info(objectName + "is Enabled : " + isEnabled);
	        }
	        return isEnabled;
	    }
	/*---------------------------------------------------------------------------------------------------------
	* Method Name : isAvailable
	* Arguments : String to identify the element
	* Use : wait Until the element is visible
	* Designed By : Senthil
	* Last Modified Date : 20-06-2021
	--------------------------------------------------------------------------------------------------------*/
	 public static boolean isAvailable(String objectName) throws Exception {
			boolean isAvailable = false;
			isAvailable = Wait.checkAvailable(objectName, "WebRadioButton");
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
				clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
				throw new Exception();
			} else {
				clog.info(objectName + "is Enabled : " + isAvailable);
			}
			return isAvailable;
		}
	}
	
	

/*---------------------------------------------------------------------------------------------------------
* Class Name : WebCheckBox
* Use : Subclass of browser class represents the WebCheckBox in the application and
* contains functions for all the operations performed on WebCheckBox
* Designed By : Senthil
* Last Modified Date : 20-06-2021
--------------------------------------------------------------------------------------------------------*/
public static class WebCheckBox {
/*---------------------------------------------------------------------------------------------------------
* Method Name : click
* Arguments : String to identify and click the element
* Use : wait and click the element
* Designed By : Senthil
* Last Modified Date : 20-06-2021
--------------------------------------------------------------------------------------------------------*/

public static void click(String objectName) throws Exception {
String[] objectProperty = Utlities.FindObject(objectName, "WebCheckBox");
Wait.waitUntillClickable(getWebElement(objectProperty));
WebAction.click(getWebElement(objectProperty));
if (!Continue.get()) {
if (!methodScreenShotTaken.get()) {
Result.Methodscreenshot();
}
Result.takescreenshot(" :: Failed at Object: " + objectName);
clog.info(" :: Failed at Object: " + objectName );
throw new Exception();
} else {
clog.info(" :: Clicked on Object : " + objectName);
}

 }
/*---------------------------------------------------------------------------------------------------------
* Method Name : clickWithJS
* Arguments : String to identify and click the element
* Use : wait and click the element with Java script executor
* Designed By : Senthil
* Last Modified Date : 20-06-2021
--------------------------------------------------------------------------------------------------------*/

public static void clickWithJS(String objectName) throws Exception {
String[] objectProperty = Utlities.FindObject(objectName, "WebCheckBox");
Wait.waitUntillClickable(getWebElement(objectProperty));
WebAction.clickWithJS(getWebElement(objectProperty));
if (!Continue.get()) {
if (!methodScreenShotTaken.get()) {
Result.Methodscreenshot();
}
Result.takescreenshot(" :: Failed at Object: " + objectName);
clog.info(" :: Failed at Object: " + objectName );
throw new Exception();
} else {
clog.info(" :: Clicked with JavaScript Executor on Object : " + objectName);
}

}
/*---------------------------------------------------------------------------------------------------------
* Method Name : clickWithOffset
* Arguments : String to identify and click the element
* Use : wait and click the element with Offset
* Designed By : Senthil
* Last Modified Date : 20-06-2021
--------------------------------------------------------------------------------------------------------*/

public static void clickWithOffset(String objectName) throws Exception {
String[] objectProperty = Utlities.FindObject(objectName, "WebCheckBox");
Wait.waitUntillClickable(getWebElement(objectProperty));
WebAction.clickWithOffset(getWebElement(objectProperty));
if (!Continue.get()) {
if (!methodScreenShotTaken.get()) {
Result.Methodscreenshot();
}
Result.takescreenshot(" :: Failed at Object: " + objectName);
clog.info(" :: Failed at Object: " + objectName );
throw new Exception();
} else {
clog.info(" :: Clicked with Offset on Object : " + objectName);
}

 }
/*---------------------------------------------------------------------------------------------------------
* Method Name : doubleClick
* Arguments : String to identify and double click the element
* Use : wait and doubleClick the element with Action class
* Designed By : Senthil
* Last Modified Date : 20-06-20211
--------------------------------------------------------------------------------------------------------*/

public static void doubleClick(String objectName) throws Exception {
String[] objectProperty = Utlities.FindObject(objectName, "WebCheckBox");
Wait.waitUntillClickable(getWebElement(objectProperty));
WebAction.doubleClick(getWebElement(objectProperty));
if (!Continue.get()) {
if (!methodScreenShotTaken.get()) {
Result.Methodscreenshot();
}
Result.takescreenshot(" :: Failed at Object: " + objectName);
clog.info(" :: Failed at Object: " + objectName );
throw new Exception();
} else {
clog.info(" :: Actions DoubleClicked on Object : " + objectName);
}

 }
/*---------------------------------------------------------------------------------------------------------
* Method Name : clickAction
* Arguments : String to identify and click the element with Action class
* Use : wait and click the element with Action class
* Designed By : Senthil
* Last Modified Date : 20-06-2021
--------------------------------------------------------------------------------------------------------*/

public static void clickAction(String objectName) throws Exception {
String[] objectProperty = Utlities.FindObject(objectName, "WebCheckBox");
Wait.waitUntillClickable(getWebElement(objectProperty));
WebAction.clickAction(getWebElement(objectProperty));
if (!Continue.get()) {
if (!methodScreenShotTaken.get()) {
Result.Methodscreenshot();
}
Result.takescreenshot(" :: Failed at Object: " + objectName);
clog.info(" :: Failed at Object: " + objectName );
throw new Exception();
} else {
clog.info(" :: Actions Clicked on Object : " + objectName);
}

}
/*---------------------------------------------------------------------------------------------------------
* Method Name : pressEnter
* Arguments : String to identify and pressEnter the element with Action class
* Use : wait and press Enter on the element with Action class
* Designed By : Senthil
* Last Modified Date : 20-06-2021
--------------------------------------------------------------------------------------------------------*/

public static void pressEnter(String objectName) throws Exception {
String[] objectProperty = Utlities.FindObject(objectName, "WebCheckBox");
Wait.waitUntillClickable(getWebElement(objectProperty));
WebAction.pressEnter(getWebElement(objectProperty));
if (!Continue.get()) {
if (!methodScreenShotTaken.get()) {
Result.Methodscreenshot();
}
Result.takescreenshot(" :: Failed at Object: " + objectName);
clog.info(" :: Failed at Object: " + objectName );
throw new Exception();
} else {
clog.info(" :: PressedEnter on Object : " + objectName);
}

}
/*---------------------------------------------------------------------------------------------------------
* Method Name : isEnabled
* Arguments : String to identify the element
* Use : To check whether the Object enabled or not
* Designed By : Senthil
* Last Modified Date : 20-06-2021
--------------------------------------------------------------------------------------------------------*/
public static boolean isEnabled(String objectName) throws Exception {
    boolean isEnabled = false;
    isEnabled = Wait.checkEnable(objectName, "WebCheckBox"); 
    if (!Continue.get()) {
        if (!methodScreenShotTaken.get()) {
            Result.Methodscreenshot();
        }
        Result.takescreenshot(" :: Failed to check object : " + objectName + " is Enabled or not");
        clog.info(" :: Failed to check object : " + objectName + " is Enabled or not");
        throw new Exception();
    } else {
        clog.info(objectName + "is Enabled : " + isEnabled);
    }
    return isEnabled;
}

/*---------------------------------------------------------------------------------------------------------
* Method Name : isSelected
* Arguments : String to identify the element
* Use : To check whether the Object Selected or not
* Designed By : JJ
* Last Modified Date : 06-07-2021
--------------------------------------------------------------------------------------------------------*/
public static boolean isSelected(String objectName) throws Exception {
    boolean isSelected = false;
   // isSelected = Web.isSelected(objectName, "WebCheckBox"); 
    if (!Continue.get()) {
        if (!methodScreenShotTaken.get()) {
            Result.Methodscreenshot();
        }
        Result.takescreenshot(" :: Failed to check object : " + objectName + " is isSelected or not");
        clog.info(" :: Failed to check object : " + objectName + " is isSelected or not");
        throw new Exception();
    } else {
        clog.info(objectName + "is Enabled : " + isSelected);
    }
    return isSelected;
}
/*---------------------------------------------------------------------------------------------------------
* Method Name : isAvailable
* Arguments : String to identify the element
* Use : wait Until the element is visible
* Designed By : Senthil
* Last Modified Date : 20-06-2021
--------------------------------------------------------------------------------------------------------*/
public static boolean isAvailable(String objectName) throws Exception {
	boolean isAvailable = false;
	isAvailable = Wait.checkAvailable(objectName, "WebCheckBox");
	if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
			Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
		clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
		throw new Exception();
	} else {
		clog.info(objectName + "is Enabled : " + isAvailable);
	}
	return isAvailable;
}
}
	
	
/*---------------------------------------------------------------------------------------------------------
 * Class Name            : Alert
 * Use                     : Subclass of browser class represents the Alert in the application and
 *                           contains functions for all the operations performed on Alert
 * Designed By            : Senthil
 * Last Modified Date     : 20-06-2021
--------------------------------------------------------------------------------------------------------*/

public static class Alert {
     /*---------------------------------------------------------------------------------------------------------
       * Method Name            : alertAccept
       * Use                     : To Accept the alert
       * Designed By            : Senthil
       * Last Modified Date     : 20-06-2021
      --------------------------------------------------------------------------------------------------------*/
  
    public static void alertAccept() throws Exception {
    WebAction.alertAccept();
    if (!Continue.get()) {
        if (!methodScreenShotTaken.get()) {
            Result.Methodscreenshot();
        }
        Result.takescreenshot("No Alert Present");
        clog.info("No Alert Present");
        throw new Exception();
    } else {
        clog.info("Alert Accepted");
    }
   
   
}
    
    /*---------------------------------------------------------------------------------------------------------
     * Method Name            : alertWrite
     * Use                     : write the the text to the alert 
     * Designed By            : JJ
     * Last Modified Date     : 27-06-2021
    --------------------------------------------------------------------------------------------------------*/

  public static void alertWrite(String value) throws Exception {
  WebAction.alertWrite(value);
  if (!Continue.get()) {
      if (!methodScreenShotTaken.get()) {
          Result.Methodscreenshot();
      }
      Result.takescreenshot("No Alert Present");
      clog.info("No Alert Present");
      throw new Exception();
  } else {
      clog.info("Alert Accepted");
  }
 
 
}
    
    /*---------------------------------------------------------------------------------------------------------
     * Method Name            : isAlertPresent
     * Use                     : To check alert present
     * Designed By            : JJ
     * Last Modified Date     : 20-06-2021
    --------------------------------------------------------------------------------------------------------*/

  public static Boolean isAlertPresent() throws Exception {
  Boolean alert=WebAction.isAlertPresent();
  if (!Continue.get()) {
      if (!methodScreenShotTaken.get()) {
          Result.Methodscreenshot();
      }
      Result.takescreenshot("No Alert Present");
      clog.info("No Alert Present");
      throw new Exception();
  } else {
      clog.info(" Alert Present");
  }
 return alert;
 
}
  
  
  /*---------------------------------------------------------------------------------------------------------
   * Method Name            : isAlertPresentEnterText
   * Use                     : To check alert present
   * Designed By            : JJ
   * Last Modified Date     : 09-08-2021
  --------------------------------------------------------------------------------------------------------*/

public static Boolean isAlertPresentEnterText(String text) throws Exception {
Boolean alert=WebAction.isAlertPresentEnterText(text);
if (!Continue.get()) {
    if (!methodScreenShotTaken.get()) {
        Result.Methodscreenshot();
    }
    Result.takescreenshot("No Alert Present");
    clog.info("No Alert Present");
    throw new Exception();
} else {
    clog.info(" Alert Present and value entered :"+text);
}
return alert;

}
  
  
  /*---------------------------------------------------------------------------------------------------------
   * Method Name            : acceptIfPresent
   * Use                     : accept if alert is present
   * Designed By            : JJ
   * Last Modified Date     : 20-06-2021
  --------------------------------------------------------------------------------------------------------*/

public static Boolean acceptIfPresent() throws Exception {
	Boolean alert=WebAction.acceptIfPresent();
if (!Continue.get()) {
    if (!methodScreenShotTaken.get()) {
        Result.Methodscreenshot();
    }
    Result.takescreenshot("No Alert Present");
    clog.info("No Alert Present");
    throw new Exception();
}
return alert;

}
   
     /*---------------------------------------------------------------------------------------------------------
       * Method Name            : alertDecline
       * Use                     : To Decline the alert
       * Designed By            : Senthil
       * Last Modified Date     : 20-06-2021
      --------------------------------------------------------------------------------------------------------*/
   
    public static void alertDecline() throws Exception {
        WebAction.alertDecline();
        if (!Continue.get()) {
            if (!methodScreenShotTaken.get()) {
                Result.Methodscreenshot();
            }
            Result.takescreenshot("No Alert Present");
            clog.info("No Alert Present");
            throw new Exception();
        } else {
            clog.info("Alert Declined");
        }
       
       
    }
   
     /*---------------------------------------------------------------------------------------------------------
       * Method Name            : getAlertText
       * Use                     : To get the text present in alert
       * Designed By            : Senthil
       * Last Modified Date     : 20-06-2021
      --------------------------------------------------------------------------------------------------------*/
   
    public static String getAlertText() throws Exception {
        String alertText=null;
         alertText = WebAction.getAlertText();
        if (!Continue.get()) {
            if (!methodScreenShotTaken.get()) {
                Result.Methodscreenshot();
            }
            Result.takescreenshot("No Alert Present");
            clog.info("No Alert Present");
            throw new Exception();
        } else {
            clog.info("Text presented in alert : " + alertText);
        }
        return alertText;
       
       
    }
   
     /*---------------------------------------------------------------------------------------------------------
       * Method Name            : getAlertTextAndAccept
       * Use                     : To get the text present in alert and accept the alert
       * Designed By            : Senthil
       * Last Modified Date     : 20-06-2021
      --------------------------------------------------------------------------------------------------------*/
   
    public static void getAlertTextAndAccept() throws Exception {
        WebAction.getAlertTextAndAccept();
        if (!Continue.get()) {
            if (!methodScreenShotTaken.get()) {
                Result.Methodscreenshot();
            }
            Result.takescreenshot("No Alert Present");
            clog.info("No Alert Present");
            throw new Exception();
        } else {
            clog.info("Text presented in alert is accepeted");
        }
       
       
    }
    
    /*---------------------------------------------------------------------------------------------------------
     * Method Name            : acceptIfPresentAndGetText
     * Use                     : To get the text present in alert and accept the alert
     * Designed By            : JJ
     * Last Modified Date     : 20-06-2021
    --------------------------------------------------------------------------------------------------------*/
 
    public static String acceptIfPresentAndGetText() throws Exception {
      String text=WebAction.acceptIfPresentAndGetText();
      if (!Continue.get()) {
          if (!methodScreenShotTaken.get()) {
              Result.Methodscreenshot();
          }
          Result.takescreenshot("No Alert Present");
          clog.info("No Alert Present");
          throw new Exception();
      } else {
          clog.info("Text presented in alert is accepeted");
      }
	return text;
     
     
  }
   
}




	public static class WebTable {

		/*---------------------------------------------------------------------------------------------------------
		* Method Name			: readTextEnglishWebTable
		* Arguments			: String , String , String , String 
		* Use 					: read the text from web table which contains English value
		* Designed By			: JJ
		* Last Modified Date 	: 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		public static String readTextEnglishWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			//Wait.waitForElement(objectName, "WebTable");

			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
		public static String readTextArabicWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");

			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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
		public static void clickDataEnglishWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");
			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
		public static void clickDataArabicWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");
			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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
		public static void clickJSDataEnglishWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");

			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
		public static void clickJSDataArabicWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");
			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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
		public static void clickLinkEnglishWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");

			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
		public static void clickLinkArabicWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");
			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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
		public static void clickOffsetEnglishWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");

			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
		public static void clickOffsetArabicWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");
			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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

			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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

			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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
		 * Method Name			: clickDropDownEnglishWebTable
		 * Arguments			: String , String , String , String 
		 * Use 					: click text from web table which contains English value
		 * Designed By			: JJ
		 * Last Modified Date 	: 20-06-2021
		--------------------------------------------------------------------------------------------------------*/
		public static void clickTextEnglishWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");

			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
		public static void clickTextArabicWebTable(String objectName, String columnName, String rowName,
				String rowValue) throws Exception {

			Wait.waitForElement(objectName, "WebTable");
			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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
		public static void writeTextEnglishWebTable(String objectName, String columnName, String rowName,
				String rowValue, String object, String value) throws Exception {

			Wait.waitForElement(objectName, "WebTable");

			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
		public static void writeTextArabicWebTable(String objectName, String columnName, String rowName,
				String rowValue, String object, String value) throws Exception {

			Wait.waitForElement(objectName, "WebTable");
			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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

			int columnNumber = WebAction.getColumnNumber(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumber(objectName, rowName, rowValue);
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
			int columnNumber = WebAction.getColumnNumberContains(objectName, columnName);
			clog.info("column Number " + columnNumber);
			int rowNumber = WebAction.getRowNumberContains(objectName, rowName, rowValue);
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

	}
	
	/*---------------------------------------------------------------------------------------------------------
	 * Class Name			: Dynamic 
	 * Use 					: Subclass of browser class represents the Dynamic in the application and 
	 * 						  contains functions for all the operations performed on Dynamic   
	 * Designed By			: Senthil
	 * Last Modified Date 	: 20-06-2021
	--------------------------------------------------------------------------------------------------------*/

	
	public static class Dynamic {
				
		/*---------------------------------------------------------------------------------------------------------
	  	 * Method Name			: click
	  	 * Arguments			: ObjectName to identify and click the element
	  	 * Use 					: Wait and click the specified element using click
	  	 * Designed By			: Senthil 
	  	 * Last Modified Date 	: 20-06-2021
	  	--------------------------------------------------------------------------------------------------------*/
	   
		public static void click(String objectName,String value,String objectType) throws Exception {
			String[] objectProperty = Utlities.FindObject(objectName, objectType);
			Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
			WebAction.click(getDynamicWebElement(objectProperty,value));
			
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed to Click Object: " + objectName );
				clog.info(" :: Failed to Click Object: " + objectName );
				throw new Exception();
			} else {
				clog.info(" :: Clicked on Object: " + objectName );
			}

		}
		
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clickWithJS
  	 * Arguments			: ObjectName to identify and click the element 
  	 * Use 					: Wait and click the specified element using clickWithJS
  	 * Designed By			: Senthil 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clickWithJS(String objectName,String value,String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
		WebAction.clickWithJS(getDynamicWebElement(objectProperty,value));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to clickWithJS Object: " + objectName );
			clog.info(" :: Failed to clickWithJS Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Clicked with JavaScriptExecutor on Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clickWithOffset
  	 * Arguments			: ObjectName to identify and click the element
  	 * Use 					: Wait and click the specified element using clickWithOffset
  	 * Designed By			: Senthil 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clickWithOffset(String objectName,String value,String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
		WebAction.clickWithOffset(getDynamicWebElement(objectProperty,value));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to clickWithOffset Object: " + objectName );
			clog.info(" :: Failed to clickWithOffset Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Clicked with Offset on Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: doubleClick
  	 * Arguments			: ObjectName to identify and click the element
  	 * Use 					: Wait and click the specified element using doubleClick
  	 * Designed By			: Senthil 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void doubleClick(String objectName,String value, String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
		WebAction.doubleClick(getDynamicWebElement(objectProperty,value));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to doubleClick Object: " + objectName );
			clog.info(" :: Failed to doubleClick Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Actions DoubleClicked Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clickAction
  	 * Arguments			: ObjectName to identify and click the element
  	 * Use 					: Wait and click the specified element using clickAction
  	 * Designed By			: Senthil 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clickAction(String objectName,String value,String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
		WebAction.clickAction(getDynamicWebElement(objectProperty,value));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to clickAction Object: " + objectName );
			clog.info(" :: Failed to clickAction Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Actions click on Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: pressEnter
  	 * Arguments			: ObjectName to identify and click the element
  	 * Use 					: Wait and click the specified element using pressEnter
  	 * Designed By			: Senthil 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void pressEnter(String objectName,String value,String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
		WebAction.pressEnter(getDynamicWebElement(objectProperty,value));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to pressEnter Object: " + objectName );
			clog.info(" :: Failed to pressEnter Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Pressed Enter on Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clear
  	 * Arguments			: ObjectName to identify and clear the field
  	 * Use 					: Wait and click the specified element using pressEnter
  	 * Designed By			: Senthil 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clear(String objectName,String value,String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
		WebAction.clear(getDynamicWebElement(objectProperty,value));
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed to clear Object: " + objectName );
			clog.info(" :: Failed to clear Object: " + objectName );
			throw new Exception();
		} else {
			clog.info(" :: Cleared the Object: " + objectName );
		}

	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: writeAndEnter
  	 * Arguments			: String where to write , String what to write
  	 * Use 					: Wait and click the field and clear the existing text and write the text in the element and perform enter key action 
  	 * Designed By			: Senthil 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void writeAndEnter(String objectName,String value,String objectValue,String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
		WebAction.click(getDynamicWebElement(objectProperty,value));
		WebAction.clear(getDynamicWebElement(objectProperty,value));
		WebAction.writeAndEnter(getDynamicWebElement(objectProperty,value), objectValue);
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			clog.info(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			throw new Exception();
		} else {
			clog.info(" :: Action SetText on Object: " + objectName + " - Value: " + objectValue);
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: write
  	 * Arguments			: String where to write , String what to write
  	 * Use 					: Write the text in the element 
  	 * Designed By			: Senthil 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void write(String objectName,String value,String objectValue,String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
		WebAction.write(getDynamicWebElement(objectProperty,value), objectValue);
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			clog.info(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			throw new Exception();
		} else {
			clog.info(" :: Action SetText on Object: " + objectName + " - Value: " + objectValue);
		}
	}
	

	/*---------------------------------------------------------------------------------------------------------
* Method Name			: writeWithJS
* Arguments			: String where to write , String what to write
* Use 					: Write the text in the element 
* Designed By			: JJ 
* Last Modified Date 	: 01-07-2021
--------------------------------------------------------------------------------------------------------*/

public static void writeWithJS(String objectName,String value,String objectValue,String objectType) throws Exception {
String[] objectProperty = Utlities.FindObject(objectName, objectType);
Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
WebAction.clickWithJS(getDynamicWebElement(objectProperty,value));
WebAction.writeWithJS(getDynamicWebElement(objectProperty,value), objectValue);

if (!Continue.get()) {
	if (!methodScreenShotTaken.get()) {
		Result.Methodscreenshot();
	}
	Result.takescreenshot(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
	clog.info(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
	throw new Exception();
} else {
	clog.info(" :: Action SetText on Object: " + objectName + " - Value: " + objectValue);
}
}
	
/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: writeEnterWithJS
	 * Arguments			: String where to write , String what to write
	 * Use 					: Write the text in the element 
	 * Designed By			: JJ 
	 * Last Modified Date 	: 01-07-2021
	--------------------------------------------------------------------------------------------------------*/

public static void writeEnterWithJS(String objectName,String objectValue) throws Exception {
	String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
	Wait.waitUntillClickable(getWebElement(objectProperty));
	WebAction.clickWithJS(getWebElement(objectProperty));
	WebAction.writeEnterWithJS(getWebElement(objectProperty), objectValue);
	if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
			Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
		clog.info(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
		throw new Exception();
	} else {
		clog.info(" :: Action SetText on Object: " + objectName + " - Value: " + objectValue);
	}
}

	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clearWrite
  	 * Arguments			: String where to write , String what to write
  	 * Use 					: Clear and write the text in the element 
  	 * Designed By			: Senthil 
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
   
	public static void clearWrite(String objectName,String value,String objectValue,String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getDynamicWebElement(objectProperty,value));
		WebAction.clear(getDynamicWebElement(objectProperty,value));
		WebAction.write(getDynamicWebElement(objectProperty,value), objectValue);
		
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			clog.info(" :: Failed at Object: " + objectName + " - to set Value: " + objectValue);
			throw new Exception();
		} else {
			clog.info(" :: Cleared and SetText on Object: " + objectName + " - Value: " + objectValue);
		}
	 }
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: readText
  	 * Arguments			: String, String , String
  	 * Use 					: Wait and retrive the text from the Object
  	 * Designed By			: Senthil
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static String readText(String objectName,String value,String objectType) throws Exception {
		String readText = null;
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillVisible(getDynamicWebElement(objectProperty,value));
		readText = WebAction.readText(getDynamicWebElement(objectProperty,value));

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to retrive Value");
			clog.info(" :: Failed at Object: " + objectName + " - to retrive Value");
			throw new Exception();
		} else {
			clog.info(readText + " :: is the value retrived from: " + objectName);
		}
		return readText;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: readTextWithJS
  	 * Arguments			: String, String, String
  	 * Use 					: Wait and retrive the text from the Object using JavaScript
  	 * Designed By			: Senthil
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static String readTextWithJS(String objectName,String value,String objectType) throws Exception {
		String readText = null;
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillVisible(getDynamicWebElement(objectProperty,value));
		readText = WebAction.readTextWithJS(getDynamicWebElement(objectProperty,value));

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to retrive Value");
			clog.info(" :: Failed at Object: " + objectName + " - to retrive Value");
			throw new Exception();
		} else {
			clog.info(readText + " :: is the value retrived from: " + objectName);
		}
		return readText;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: readAttributeValue
  	 * Arguments			: String, String, String
  	 * Use 					: Wait and retrive the value from the attribute
  	 * Designed By			: Senthil
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static String readAttributeValue(String objectName,String value,String objectType) throws Exception {
		String readText = null;
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillVisible(getDynamicWebElement(objectProperty,value));
		readText = WebAction.readAttributeValue(getDynamicWebElement(objectProperty,value));

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to retrive Value");
			clog.info(" :: Failed at Object: " + objectName + " - to retrive Value");
			throw new Exception();
		} else {
			clog.info(readText + " :: is the value retrived from: " + objectName);
		}
		return readText;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: getAttributeValue
  	 * Arguments			: String , String, String, String
  	 * Use 					: Wait and retrive the value from the Object's corresponding attribute
  	 * Designed By			: Senthil
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static String readAttribute(String objectName, String value,String objectValue,String objectType) throws Exception {
		String readAttribute = null;
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillVisible(getDynamicWebElement(objectProperty,value));
		readAttribute = WebAction.readAttribute(getDynamicWebElement(objectProperty,value), objectValue);

		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName + " - to retrive Value");
			clog.info(" :: Failed at Object: " + objectName + " - to retrive Value");
			throw new Exception();
		} else {
			clog.info(readAttribute + " :: is the value retrived from: " + objectName);
		}
		return readAttribute;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: isEnabled
  	 * Arguments			: String , String , String
  	 * Use 					: To check whether the Object enabled or not 
  	 * Designed By			: Senthil
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static boolean isEnabled(String objectName,String value,String objectType) throws Exception {
	    boolean isEnabled = false;
	    isEnabled = Wait.dynamicCheckEnable(objectName,value,objectType); 
	    if (!Continue.get()) {
	        if (!methodScreenShotTaken.get()) {
	            Result.Methodscreenshot();
	        }
	        Result.takescreenshot(" :: Failed to check object : " + objectName + " is Enabled or not");
	        clog.info(" :: Failed to check object : " + objectName + " is Enabled or not");
	        throw new Exception();
	    } else {
	        clog.info(objectName + "is Enabled : " + isEnabled);
	    }
	    return isEnabled;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: isAvailable
  	 * Arguments			: String, String ,String
  	 * Use 					: To check whether the Object available or not 
  	 * Designed By			: Senthil
  	 * Last Modified Date 	: 20-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	 public static boolean isAvailable(String objectName,String value,String objectType) throws Exception {
			boolean isAvailable = false;
			isAvailable = Wait.dynamicCheckAvailable(objectName,value,objectType);
			if (!Continue.get()) {
				if (!methodScreenShotTaken.get()) {
					Result.Methodscreenshot();
				}
				Result.takescreenshot(" :: Failed to check object : " + objectName + " is Available or not");
				clog.info(" :: Failed to check object :  " + objectName + " is Available or not");
				throw new Exception();
			} else {
				clog.info(objectName + "is Enabled : " + isAvailable);
			}
			return isAvailable;
		}
}	
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: moveToElement
  	 * Arguments			: String , String
  	 * Use 					: mouse over to the element 
  	 * Designed By			: JJ
  	 * Last Modified Date 	: 29-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	
	public static void moveToElement(String objectName, String objectType) throws Exception {

		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillClickable(getWebElement(objectProperty));
		WebAction.moveToElement(getWebElement(objectProperty));
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" ::Failed moved to the element successfully " + objectName);
			clog.info(" ::Failed moved to the element successfully " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Action moved to the element: " + objectName);
		}

		
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: scrollToElement
  	 * Arguments			: String , String
  	 * Use 					: Scroll to the element 
  	 * Designed By			: JJ
  	 * Last Modified Date 	: 29-06-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	
	public static void scrollToElement(String objectName, String objectType) throws Exception {

		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		Wait.waitUntillVisible(getWebElement(objectProperty));
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot("  ::Failed Scroll to the element successfully " + objectName);
			clog.info("  ::Failed Scroll to the element successfully " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Scroll to the element " + objectName);
		}

		
	}
	
	/*---------------------------------------------------------------------------------------------------------
     * Method Name            : readTextAndAssert
     * Arguments            : objectName  objectType and dataName
     * Use                     : find the element based on the locator and get text from then element then stores in the variable
     * Designed By            : Sathya LeninEARC
     * Last Modified Date     : 18-06-2021
    --------------------------------------------------------------------------------------------------------*/
	
	public static void readTextAndAssert(String objectName,String objectType,String dataName) throws Exception 
	{
	        String[] objectProperty = Utlities.FindObject(objectName, objectType);
	        String data = WebAction.readText(Web.getWebElement(objectProperty)); 
	        Utlities.saveToData(dataName, data); 
	        Result.takescreenshot(dataName);	 
	        if (ValidationData.get(dataName).equals("")) {
	            if (Utlities.pullData(TC_Id.get(), dataName).equals("")) {
	                clog.info(
	                    dataName + " Validation is not Provided for this Account"
	                );
	            } else {
	                Assertion.assertequals(
	                    Utlities.pullData(TC_Id.get(), dataName),
	                    data
	                );
	            }
	        } else {
	            Assertion.assertequals(ValidationData.get(dataName), data);
	        }
	    }
	
	
	/*---------------------------------------------------------------------------------------------------------
     * Method Name            : uploadFile
     * Arguments            : String  String
     * Use                     : Upload a file in to the locator
     * Designed By            : JJ
     * Last Modified Date     : 07-07-2021
    --------------------------------------------------------------------------------------------------------*/
	
	public static void uploadFile(String objectName,String fileName) throws Exception {
	 String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
	 String file="src/main/resources/BULK_ACTION"+fileName+".csv";
     WebAction.write(getWebElement(objectProperty), file); 
     if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: " + objectName + " - to set Value: " + fileName);
			clog.info(" :: Failed at Obj: " + objectName + " - to set Value: " + fileName);
			throw new Exception();
		} else {
			clog.info(" :: Action SetText on Obj: " + objectName + " - Value: " + fileName);
		}
		
	}
	
	/*---------------------------------------------------------------------------------------------------------
     * Method Name            : getCurrentDate
     * Arguments            : String  
     * Use                     : get current system date 
     * Designed By            : JJ
     * Last Modified Date     : 18-07-2021
    --------------------------------------------------------------------------------------------------------*/
	
	public static String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String CurrentTime = dtf.format(now);
		clog.info(CurrentTime);
		return CurrentTime;
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: moveToNextTab
  	 * Arguments			: String , String
  	 * Use 					: move to next Tab
  	 * Designed By			: JJ
  	 * Last Modified Date 	: 22-07-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	
	public static void moveToNextTab(String objectName, String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		WebElement element=getWebElement(objectProperty);
		element.sendKeys(Keys.ENTER);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot("  ::Failed Tab to the element successfully " + objectName);
			clog.info("  ::Failed Tab to the element successfully " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Tab to the element " + objectName);
		}

		
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: highLight
  	 * Arguments			: String , String
  	 * Use 					: High light 
  	 * Designed By			: JJ
  	 * Last Modified Date 	: 29-07-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	
	public static void highLight(String objectName, String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		WebElement element=getWebElement(objectProperty);
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript("arguments[0].style.border='2px solid red'",element);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot("  ::Failed highLight the element successfully " + objectName);
			clog.info("  ::Failed highLight the element successfully " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: highLighted to the element " + objectName);
		}

		
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: switchToFrame
  	 * Arguments			: String 
  	 * Use 					: switchToFrame
  	 * Designed By			: JJ
  	 * Last Modified Date 	: 29-07-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	
	public static void switchToFrame(String frameName) throws Exception {
		driver.get().switchTo().frame(frameName);
		if (!Continue.get()) {
			if (!methodScreenShotTaken.get()) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot("  ::Failed switch To Frame " );
			clog.info("  ::Failed switchToFrame ");
			throw new Exception();
		} else {
			clog.info(" :: switched To Frame sucessfully ");
		}

		
	}
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: click
  	 * Arguments			: String , String
  	 * Use 					: click with out scroll 
  	 * Designed By			: JJ
  	 * Last Modified Date 	: 29-07-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	
	
	public static void click(String objectName, String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		//Wait.waitUntillClickEnable(getWebElement(objectProperty));
		WebAction.click(getWebElement(objectProperty));
		if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
		Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed at Object: " + objectName + " - to click: ");
		clog.info(" :: Failed at Object: " + objectName + " - to Click: ");
		throw new Exception();
		} else {
		clog.info(" :: Clicked on Object: " + objectName );
		}

		 }
	
	/*---------------------------------------------------------------------------------------------------------
  	 * Method Name			: clickWithJs
  	 * Arguments			: String , String
  	 * Use 					: click with out scroll 
  	 * Designed By			: JJ
  	 * Last Modified Date 	: 29-07-2021
  	--------------------------------------------------------------------------------------------------------*/
	
	public static void clickWithJs(String objectName, String objectType) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, objectType);
		//Wait.waitUntillClickEnable(getWebElement(objectProperty));
		WebAction.clickWithJS(getWebElement(objectProperty));
		if (!Continue.get()) {
		if (!methodScreenShotTaken.get()) {
		Result.Methodscreenshot();
		}
		Result.takescreenshot(" :: Failed at Object: " + objectName + " - to click: ");
		clog.info(" :: Failed at Object: " + objectName + " - to Click: ");
		throw new Exception();
		} else {
		clog.info(" :: Clicked on Object: " + objectName );
		}

		 }

}
