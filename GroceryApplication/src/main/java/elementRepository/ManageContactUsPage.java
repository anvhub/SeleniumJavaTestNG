package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.FakerUtility;

public class ManageContactUsPage {
	WebDriver driver;
	FakerUtility fu;

	public ManageContactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		fu = new FakerUtility();
	}
	
	@FindBy(xpath="//i[@class='fas fa-edit']")
	WebElement editBtn;
	@FindBy(xpath="//input[@id='phone']")
	WebElement phoneNo;
	@FindBy(xpath="//textarea[@name='address']")
	WebElement address;
	@FindBy(xpath="//textarea[@name='del_time']")
	WebElement deliveryTime;
	@FindBy(xpath="//input[@id='del_limit']")
	WebElement deliveryChargeLimit;
	@FindBy(xpath="//button[@name='Update']")
	WebElement updateBtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement alertMsg;
	
	public void updateContactUsUsingFaker() {
		editBtn.click();
		phoneNo.clear();
		phoneNo.sendKeys(fu.generateRandomDigits(10));
		address.clear();
		address.sendKeys(fu.generateAddress());
		deliveryTime.clear();
		deliveryTime.sendKeys(fu.generateRandomDigits(1));
		deliveryChargeLimit.clear();
		deliveryChargeLimit.sendKeys(fu.generateRandomDigits(2));	
		updateBtn.click();
	}
	public String getAlertMsg() {
		return alertMsg.getText();
	}

}
 