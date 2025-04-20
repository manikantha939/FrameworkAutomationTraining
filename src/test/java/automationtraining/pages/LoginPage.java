package automationtraining.pages;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import automationtraining.base.BasePage;
import automationtraining.exception.VerificationFailedException;

public class LoginPage extends BasePage {
	
	public static final Logger logger= LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		super(driver);
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
			click(loginButton);
		} catch (Exception e) {
			logger.error("Login failed: "+e.getMessage());
			throw new VerificationFailedException("Logiun Failed: "+e.getMessage());
		}
	}
	
	
}
