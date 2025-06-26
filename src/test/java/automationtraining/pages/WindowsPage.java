package automationtraining.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import automationtraining.base.BasePage;

public class WindowsPage extends BasePage {
	
	public static final Logger logger= LogManager.getLogger(WindowsPage.class);

	public WindowsPage(WebDriver driver, ExtentTest extentTest) {
		super(driver, extentTest);
	}
	
	@FindBy(css = "div#Tabbed>a")
	private WebElement clickBtnTabbed;
	
	@FindBy(css = "div#Seperate>button")
	private WebElement clickBtnSeparate;
	
	@FindBy(css = "div#Multiple>button")
	private WebElement clickBtnMultiple;
	
	@FindBy(css = "#main_navbar a[href$='downloads']")
	WebElement downloadTab;
	
	public void openNewTabbedWindows() {
		click(clickBtnTabbed);
		logger.info("Clicked on the button to open new tabbed window");
	}
	
	public void openNewSeparateWindows() {
		click(clickBtnSeparate);
		logger.info("Clicked on the button to open new separate window");
	}
	
	public void openNewMultipleWindows() {
		click(clickBtnMultiple);
		logger.info("Clicked on the button to open new multiple window");
	}
	
	public void clickDownloadTab() {
		click(downloadTab);
		logger.info("Clicked on the downloads link: "+getDriver().getTitle());
	}
}
