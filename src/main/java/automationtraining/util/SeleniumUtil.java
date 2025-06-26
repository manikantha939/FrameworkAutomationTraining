package automationtraining.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class SeleniumUtil {
	
	private static final Logger logger=LogManager.getLogger(SeleniumUtil.class);

	public static void switchToWindow(WebDriver driver, String title) {
		Set<String> allWindows=driver.getWindowHandles();
		for(String window : allWindows) {
			logger.info("Switching to window: "+driver.getTitle());
			driver.switchTo().window(window);
			if (title.equals(driver.getTitle())) {
				break;
			}
		}
	}
	
	private void switchToWindow(WebDriver driver, int index) {
		Set<String> allWindows=driver.getWindowHandles();
		List<String> allWindowsList = new ArrayList<>(allWindows);
		driver.switchTo().window(allWindowsList.get(index));
	}
	
	public static void switchToWindow(String parentWindow, WebDriver driver, ExtentTest extentTest) {
		Set<String> allWindows=driver.getWindowHandles();
		for(String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
				extentTest.info("Switched to new window: " + window);
				break;
			}
		}
	}
}
