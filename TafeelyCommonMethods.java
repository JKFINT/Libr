package Libraries;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TafeelyCommonMethods extends Driver {
	
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: signature
	 * Arguments			: object Name
	 * Use 					: find the element and sign on the element
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	
	public static void signature(String objectName) throws Exception {
		String[] objectProperty = Utlities.FindObject(objectName, "WebEdit");
		Web.WebEdit.click(objectName);
		WebElement element = Web.getWebElement(objectProperty);
		Actions builder = new Actions(driver.get());
		builder.clickAndHold().moveToElement(element, 5, 5).moveByOffset(10, 10)
				.release().build().perform();
		builder.clickAndHold().moveToElement(element, 5, 5).moveByOffset(10, 8)
				.release().build().perform();
		builder.clickAndHold().moveToElement(element, 5, 5).moveByOffset(-15, 7)
				.release().build().perform();
		builder.clickAndHold().moveToElement(element, 5, 5).moveByOffset(12, 9)
				.release().build().perform();
		builder.clickAndHold().moveToElement(element, 5, 5).moveByOffset(10, 10)
				.release().build().perform();
		builder.clickAndHold().moveToElement(element, 5, 5).moveByOffset(12, 12)
				.release().build().perform();
		builder.clickAndHold().moveToElement(element, 5, 5).moveByOffset(-15, -15)
				.release().build().perform();
		builder.clickAndHold().moveToElement(element, 5, 5).moveByOffset(20, 20)
				.release().build().perform();
		if (Continue.get() == false) {
			if (methodScreenShotTaken.get() == false) {
				Result.Methodscreenshot();
			}
			Result.takescreenshot(" :: Failed at Object: " + objectName);
			clog.info(" :: Failed at Object: " + objectName);
			throw new Exception();
		} else {
			clog.info(" :: Signature on Object: " + objectName);
		}
	}
	
	
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitTillTafeelyLoad
	 * Arguments			: 
	 * Use 					: wait until tafeely Load disappear
	 * Designed By			: Sathya Lenin EARC
	 * Last Modified Date 	: 24-06-2021
	--------------------------------------------------------------------------------------------------------*/
	
	public static void waitTillTafeelyLoad() throws InterruptedException {
		try {
			if (Continue.get() == true) {
				clog.info("inside overlay Load");
				Wait.waitTillObjectDisappears("Tafeely_maskOverlay", "WebLink");
			}
		} catch (Exception e) {
		}
	}
	
	/*---------------------------------------------------------------------------------------------------------
     * Method Name    : tafeelyCommonSelectDropdownAndClick
     * Arguments        : 
     * Use                     : Click the Dropdown and Store all values in List from dropdown and Select the given text
     * Designed By    : Rajesh
     * Last Modified Date     : 27-07-2021
    --------------------------------------------------------------------------------------------------------*/

 

    public static void tafeelyCommonSelectDropdownAndClick(
        String MSISDN,
        String objectName1,
        String objectName2,
        String paymentType,
        String objectType
    ) throws Exception {
        Web.Dynamic.click(objectName1, MSISDN, objectType);
        String[] identifier = Utlities.FindObject(objectName2, objectType);
        //TafeelyCommonSelectDropdownandClick
        List<WebElement> paymentTypeElement = Web.getDynamicWebElements(
            identifier,
            MSISDN
        );
        WebAction.selectFromList(paymentTypeElement, paymentType);
    }
}
