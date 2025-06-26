package automationtraining.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import automationtraining.base.BasePage;

public class FramePage extends BasePage {
	
	private static final Logger logger=LogManager.getLogger(FramePage.class);
	
	public FramePage(WebDriver driver, ExtentTest extentTest) {
		super(driver, extentTest);
	}
	
	@FindBy(css = "[href='#Multiple']")
	private WebElement multipleWindowsTab;
	
	public void multipleFrame() {
		click(multipleWindowsTab);
		getExtentTest().info("Clicked on the multiple windows tab");
		logger.info("Clicked on the multiple windows tab");
	}

}
