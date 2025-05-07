package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageNewsPage {
	WebDriver driver;

	public ManageNewsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']//i[@class='fas fa-edit']")
	WebElement newBtn;
	@FindBy(xpath = "//textarea[@id='news']")
	WebElement news;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveBtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement alertMsg;
	@FindBy(xpath = "//i[@class=' fa fa-search']")
	WebElement searchBtn;
	@FindBy(xpath = "//input[@name='un']")
	WebElement searchNews;
	@FindBy(xpath = "//button[@value='sr']")
	WebElement search;
	@FindBy(xpath = "//tbody")
	WebElement searchResult;
	@FindBy(xpath="//i[@class='fas fa-edit']")
	WebElement editBtn;
	@FindBy(xpath = "//button[@name='update']")
	WebElement updateBtn;
	

	public void createNewMsg() {
		newBtn.click();
		news.sendKeys("Buy 2 Get 1 Offer on selected items");
		saveBtn.click();
	}

	public String getAlertMsg() {
		return alertMsg.getText();
	}

	public void searchMsg() {
		searchBtn.click();
		searchNews.sendKeys("Buy 2");
		search.click();
	}	

	public String getSearchMsg() {
		return searchResult.getText();
	}
	
}