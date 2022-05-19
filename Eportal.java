package Libraries;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Eportal extends Driver {

	public static void dropDown(String xpath, String data) {

		List<WebElement> msisdndrop = driver.get().findElements(By.xpath(xpath));
		for (WebElement ele : msisdndrop) {
			if (ele.getText().contains(data)) {
				ele.click();
				break;
			}
		}

	}
	
	public static void Eportal_LandingPage_Plans_(String objname, String obj1, String obj2) throws Exception {
		Method.screenLive();
		String objtype = "WebLink";
		String[] objprop = Utlities.FindObject(objname, objtype);
		// Method.waittillobjvisible(objprop);
	    Browser.waitforload();
		Method.Eportal_LandingPage_Plans(objprop, obj1, obj2);
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

}
