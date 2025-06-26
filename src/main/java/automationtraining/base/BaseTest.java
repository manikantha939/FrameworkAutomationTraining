package automationtraining.base;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest;

import automationtraining.constatns.Constants;
import automationtraining.util.PropertyUtil;
import automationtraining.util.ReportUtil;

public class BaseTest extends AbstractBase {
	
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	ThreadLocal<WebDriver> threadLocal=new ThreadLocal<WebDriver>();
	ThreadLocal<ExtentTest> threadLocalExtentTest=new ThreadLocal<ExtentTest>();
	
	@BeforeSuite
	public void initialize() throws Exception {
		logger.info("Loading properties file");
		PropertyUtil.loadProperties();
		ReportUtil.initializeReport();
	}
	
	@AfterSuite
	public void generateReport() {
		ReportUtil.flushReport();
	}
	
	@BeforeMethod
	public synchronized void setup(ITestContext context, Method method) throws MalformedURLException {
		Map<String, String> localParameters = context.getCurrentXmlTest().getLocalParameters();
		String browser=localParameters.get("browser");
		WebDriver driver;
		ExtentTest extentTest;
		extentTest=ReportUtil.getExtentReports().createTest(method.getName());
		extentTest.assignCategory(context.getName());
		extentTest.assignDevice(browser);
		
		String runmode=System.getProperty("runmode");
		if("LOCAL".equalsIgnoreCase(runmode)) {
			if(browser.equals(Constants.CHROME)) {
				driver = new ChromeDriver();
			}else if(browser.equals(Constants.FIREFOX)) {
				driver=new FirefoxDriver();
			}else if(browser.equals(Constants.EDGE)){
				driver=new EdgeDriver();
			}else {
				driver=new ChromeDriver();
			}
		}else {
			URL url=URI.create(Constants.GRID_URL).toURL();
			if(browser.equals(Constants.CHROME)) {
				ChromeOptions options = new ChromeOptions();
				driver = new RemoteWebDriver(url, options);
			}else if(browser.equals(Constants.FIREFOX)) {
				FirefoxOptions options = new FirefoxOptions();
				driver = new RemoteWebDriver(url, options);
			}else if(browser.equals(Constants.EDGE)){
				EdgeOptions options = new EdgeOptions();
				driver = new RemoteWebDriver(url, options);
			}else {
				ChromeOptions options = new ChromeOptions();
				driver = new RemoteWebDriver(url, options);
			}
		}
		logger.info("Driver instance created for browser: "+browser+" Successfully");
		threadLocal.set(driver);
		threadLocalExtentTest.set(extentTest);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));	
	}
	
	public WebDriver getDriver() {
		return threadLocal.get();
	}
	
	protected ExtentTest getExtentTest() {
		return threadLocalExtentTest.get();
	}
	
	@AfterMethod
	public void tearDown() {
		if(getDriver()!=null) {
			logger.info(getDriver()+" Closing the browser");
			getDriver().quit();
		}
	}
}
