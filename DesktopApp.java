package Libraries;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class DesktopApp extends Driver{
	
	public static void printEcontract() throws InterruptedException {
		try {
			Screen screen = new Screen();
			String fpath = WorkingDir.get() + "/Drivers/Desktop/eContract";
			Pattern imageSearch = new Pattern(
					fpath+"/Next1.png");
			screen.wait(imageSearch, 10);
			screen.click(imageSearch);
			
		} catch (FindFailed e) {
			
		}
		
	}

	
	
}
