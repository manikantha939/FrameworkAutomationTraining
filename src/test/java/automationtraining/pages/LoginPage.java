package automationtraining.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

import automationtraining.base.BasePage;
import automationtraining.exception.VerificationFailedException;
import automationtraining.util.ScreenshotUtil;

public class LoginPage extends BasePage {
	
	public static final Logger logger= LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver, ExtentTest extentTest) {
		super(driver, extentTest);
	}

	@FindBy(css="input[name='username']")
	private WebElement userNameTxtBx;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordTxtBx;
	
	@FindBy(xpath = "//button[text()=' Login ']")
	private WebElement loginButton;
	
	public void verifyLogin() throws IOException {
		try {
			enterText(userNameTxtBx, getProperty("username"));
			enterText(passwordTxtBx, getProperty("password"));
			getExtentTest().log(Status.INFO, "Username and password entered");
			click(loginButton);
			Media m=MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotUtil.takeScreenShot(getDriver(), "login")).build();
			getExtentTest().log(Status.PASS, "Login is Successful",m);
		} catch (Exception e) {
			logger.error("Login failed: "+e.getMessage());
			getExtentTest().log(Status.FAIL, "Login is Failed");
			throw new VerificationFailedException("Logiun Failed: "+e.getMessage());
		}
	}
	
	
}
