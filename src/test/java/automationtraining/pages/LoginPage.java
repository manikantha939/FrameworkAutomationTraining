package automationtraining.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import automationtraining.base.BasePage;
import automationtraining.exception.VerificationFailedException;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="input[name='username']")
	private WebElement userNameTxtBx;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordTxtBx;
	
	@FindBy(xpath = "//button[text()=' Login ']")
	private WebElement loginButton;
	
	public void verifyLigin() {
		try {
			enterText(userNameTxtBx, "Admin");
			enterText(passwordTxtBx, "admin123");
			click(loginButton);
		} catch (Exception e) {
			System.out.println("Login failed: "+e.getMessage());
			throw new VerificationFailedException("Logiun Failed: "+e.getMessage());
		}
	}
	
	
}
