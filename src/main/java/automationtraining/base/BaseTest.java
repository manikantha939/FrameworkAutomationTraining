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
import org.testng.annotations.BeforeSuite;

import automationtraining.constatns.Constants;
import automationtraining.util.PropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest extends AbstractBase {
	
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	
	ThreadLocal<WebDriver> threadLocal=new ThreadLocal<WebDriver>();
	
	@BeforeSuite
	public void initialize() throws Exception {
		logger.info("Loading properties file");
		PropertyUtil.loadProperties();
	}
	
	@BeforeMethod
	public synchronized void setup(ITestContext context, Method method) {
		Map<String, String> localParameters = context.getCurrentXmlTest().getLocalParameters();
		String browser=localParameters.get("browser");
		WebDriver driver;
		if(browser.equals(Constants.CHROME)) {
			driver = new ChromeDriver();
		}else if(browser.equals(Constants.FIREFOX)) {
			driver=new FirefoxDriver();
		}else if(browser.equals(Constants.EDGE)){
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		logger.info("Driver instance created for browser: "+browser+" Successfully");
		threadLocal.set(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));	
	}
	
	public WebDriver getDriver() {
		return threadLocal.get();
	}
	
	
	@AfterMethod
	public void tearDown() {
		if(getDriver()!=null) {
			logger.info(getDriver()+" Closing the browser");
			getDriver().quit();
		}
	}
}
