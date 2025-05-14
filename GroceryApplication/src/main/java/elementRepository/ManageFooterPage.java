package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.FakerUtility;
import utilities.GeneralUtilities;
import utilities.WaitUtilities;

public class ManageFooterPage {

	WebDriver driver;
	FakerUtility fu;
	WaitUtilities wu = new WaitUtilities();
	GeneralUtilities gu = new GeneralUtilities();

	public ManageFooterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		fu = new FakerUtility();
	}
	
	@FindBy(xpath="//i[@class='fas fa-edit']")
	WebElement editBtn;
	@FindBy(xpath="//textarea[@name='address']")
	WebElement address;
	@FindBy(xpath="//input[@id='email']")
	WebElement email;
	@FindBy(xpath="//input[@id='phone']")
	WebElement phoneNo;
	@FindBy(xpath="//button[@name='Update']")
	WebElement updateBtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement alertMsg;
	
	public void updateFooterUsingFaker() {
		editBtn.click();
		address.clear();
		address.sendKeys(fu.generateAddress());	
		email.clear();
		email.sendKeys(fu.generateEmail());
		phoneNo.clear();
		phoneNo.sendKeys(fu.generateRandomDigits(10));			
		updateBtn.click();
	}
	public String getAlertMsg() {
		return alertMsg.getText();
	}
}
