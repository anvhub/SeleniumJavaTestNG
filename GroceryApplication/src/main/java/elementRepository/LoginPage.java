package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement userNameField;
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement passwordField;
	@FindBy(xpath="//button[text()='Sign In']")
	WebElement signInButton;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement errorMessage;

	
	public AdminPage login(String userName,String password) {
		userNameField.sendKeys(userName);
		passwordField.sendKeys(password);
		signInButton.click();
		return new AdminPage(driver);
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}	
}
