package automationtraining.tests;

import org.testng.annotations.Test;

import automationtraining.base.BaseTest;
import automationtraining.pages.LoginPage;

public class LoginTest extends BaseTest {
	@Test
	public void loginTest() throws InterruptedException {
		LoginPage loginPage=new LoginPage(getDriver());
		getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		loginPage.verifyLigin();
		Thread.sleep(5000);
	}

}
