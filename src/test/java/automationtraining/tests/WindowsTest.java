package automationtraining.tests;

import java.time.Duration;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import automationtraining.base.BaseTest;
import automationtraining.pages.WindowsPage;
import automationtraining.util.SeleniumUtil;

public class WindowsTest extends BaseTest {
	
	private static final Logger logger=LogManager.getLogger(LoginTest.class);
	
	@Test
	public void testTabbedWindows() {
		WindowsPage windowsPage=new WindowsPage(getDriver(),getExtentTest());
		getExtentTest().info("Windows test started");
		getDriver().get(getProperty("urlWindows"));
		getExtentTest().info("Navigated to URL: "+getProperty("urlWindows"));
		windowsPage.openNewTabbedWindows();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		
		String parentWindow=getDriver().getWindowHandle();
		Set<String> allWindows=getDriver().getWindowHandles();
		for(String window : allWindows) {
			if (!window.equals(parentWindow)) {
				getDriver().switchTo().window(window);
				getExtentTest().info("Switched to new window: "+window);
				break;
			}
		}

		windowsPage.clickDownloadTab();
//		getDriver().findElement(By.cssSelector("#main_navbar a[href$='downloads']")).click();
//		logger.info("Clicked on the downloads link: "+getDriver().getTitle());
	}
	
	@Test
	public void testSeparateWindows() {
		WindowsPage windowsPage=new WindowsPage(getDriver(),getExtentTest());
		getExtentTest().info("Windows test started");
		getDriver().get(getProperty("urlWindows"));
		getExtentTest().info("Navigated to URL: "+getProperty("urlWindows"));
		getDriver().findElement(By.cssSelector("a[href='#Seperate']")).click();
		windowsPage.openNewSeparateWindows();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		
		String parentWindow=getDriver().getWindowHandle();
		SeleniumUtil.switchToWindow(parentWindow, getDriver(), getExtentTest());
		windowsPage.clickDownloadTab();
		logger.info("Clicked on the downloads link: "+getDriver().getTitle());
	}
	
	@Test
	public void testMultipleWindows() {
		WindowsPage windowsPage=new WindowsPage(getDriver(),getExtentTest());
		getExtentTest().info("Windows test started");
		getDriver().get(getProperty("urlWindows"));
		getExtentTest().info("Navigated to URL: "+getProperty("urlWindows"));
		getDriver().findElement(By.cssSelector("a[href='#Multiple']")).click();
		windowsPage.openNewMultipleWindows();
		
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(3));
		
		String title="Selenium";
		SeleniumUtil.switchToWindow(getDriver(),title);
		windowsPage.clickDownloadTab();
	}
}
