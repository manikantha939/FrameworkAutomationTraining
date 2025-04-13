package automationtraining.base;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import automationtraining.constatns.Constants;

public class BaseTest {
	private WebDriver driver;
	
	@BeforeMethod
	public void setup(ITestContext context, Method method) {
		Map<String, String> localParameters = context.getCurrentXmlTest().getLocalParameters();
		String browser=localParameters.get("browser");
		if(browser==Constants.CHROME) {
			driver = new ChromeDriver();
		}else if(browser==Constants.FIREFOX) {
			driver=new FirefoxDriver();
		}else if(browser==Constants.EDGE){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		
		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
