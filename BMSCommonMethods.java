package Libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BMSCommonMethods extends Driver {

	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getDynamicWebElementForModuleBar
	 * Arguments			: identifiers,module name, sub module name
	 * Use 					: find the element based on the locator and changing the module and sub module value dynamically
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	public static WebElement getDynamicWebElementForModuleBar(String[] identifiers, String Module,String subModule) {
		WebElement element = null;
		try {
			if (Continue.get()) {
				if (!identifiers[0].equals("")) {
					String object = identifiers[0].replace("dynamicValue", Module);
					String object1 = object.replace("dynamic_Value", subModule);
					clog.info("xpath is : " + object1);
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
	 * Method Name			: selectSubModule
	 * Arguments			: module name, sub module name
	 * Use 					: Select the sub module based on the Module and sub module
	 * Designed By			: JJ
	 * Last Modified Date 	: 29-06-2021
	--------------------------------------------------------------------------------------------------------*/

	
   public static void selectSubModule(String module, String subModule) throws Exception {
		
	   String[] objectProperty = Utlities.FindObject("BMS_Module_Selection_Bar", "WebLink");
		WebElement dynamicModuleBar = getDynamicWebElementForModuleBar(objectProperty, module, subModule);
		Wait.waitUntillClickable(dynamicModuleBar);
		WebAction.click(dynamicModuleBar);
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: BMS_Module_Selection_Bar to Click");
			clog.info(" :: Failed at Object: BMS_Module_Selection_Bar to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Object: BMS_Module_Selection_Bar" );
		}
	}
   
   /*---------------------------------------------------------------------------------------------------------
	 * Method Name			: selectModule
	 * Arguments			: module name, sub module name
	 * Use 					: Select the  module based on the Module Name
	 * Designed By			: JJ
	 * Last Modified Date 	: 29-06-2021
	--------------------------------------------------------------------------------------------------------*/

	
  public static void selectModule(String module) throws Exception {
		
	  String[] objectProperty = Utlities.FindObject("BMS_Module_Click", "WebLink");
		Wait.waitUntillClickable(Web.getDynamicWebElement(objectProperty,module));
		WebAction.click(Web.getDynamicWebElement(objectProperty,module));
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Obj: BMS_Module_Click to Click");
			clog.info(" :: Failed at Obj: BMS_Module_Click to Click");
			throw new Exception();
		} else {
			clog.info(" :: Action Click on Obj: BMS_Module_Click");
		}
  }

  
  
  /*---------------------------------------------------------------------------------------------------------
   * Method Name            : clickWithExceptionHandling
   * Arguments            : String object name, strung object type
   * Use                     : Click on the element and if any exception try to click once again 
   * Designed By            : Jayakumar Manoharan
   * Last Modified Date     : 01-07-2021
  --------------------------------------------------------------------------------------------------------*/



  public static void clickWithExceptionHandling(String objectName, String objectType) throws Exception {
      String[] objectProperty = Utlities.FindObject(objectName, objectType);

      try {
          WebElement element = Web.getWebElement(objectProperty);
          WebDriverWait wait = new WebDriverWait(driver.get(), 30);
          wait.until(ExpectedConditions.visibilityOf(element));
          wait.until(ExpectedConditions.elementToBeClickable(element));
          JavascriptExecutor js = (JavascriptExecutor) driver.get();
          js.executeScript("arguments[0].scrollIntoView(true);", element);
          js.executeScript("arguments[0].click();", element);
          Wait.waitForPageToLoad();
      } catch (Exception e) {
          WebElement element = Web.getWebElement(objectProperty);
          WebDriverWait wait = new WebDriverWait(driver.get(), 30);
          wait.until(ExpectedConditions.visibilityOf(element));
          wait.until(ExpectedConditions.elementToBeClickable(element));
          JavascriptExecutor js = (JavascriptExecutor) driver.get();
          js.executeScript("arguments[0].scrollIntoView(true);", element);
          js.executeScript("arguments[0].click();", element);
          Wait.waitForPageToLoad();
      }
  }


}
