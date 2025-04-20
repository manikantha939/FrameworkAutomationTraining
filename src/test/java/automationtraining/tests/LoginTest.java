package automationtraining.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import automationtraining.base.BaseTest;
import automationtraining.pages.LoginPage;

public class LoginTest extends BaseTest {
	
	private static final Logger logger=LogManager.getLogger(LoginTest.class);
	@Test
	public void loginTest() throws Exception {
		LoginPage loginPage=new LoginPage(getDriver());
		getDriver().get(getProperty("url"));
		logger.info("Navigated to URL: "+getProperty("url"));
		loginPage.verifyLogin();
		logger.info("Login successful");
	}

	@Test
	public void loginTest1() throws Exception {
		LoginPage loginPage=new LoginPage(getDriver());
		getDriver().get(getProperty("url"));
		logger.info("Navigated to URL: "+getProperty("url"));
		loginPage.verifyLogin();
		logger.info("Login successful");
	}
}
