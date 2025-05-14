package elementRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.GeneralUtilities;
import utilities.WaitUtilities;

public class ManageNewsPage {
	WebDriver driver;
	WaitUtilities wu = new WaitUtilities();
	GeneralUtilities gu = new GeneralUtilities();

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
	public void updateNews(String updatedText) {
		searchBtn.click();
		searchNews.clear();
		searchNews.sendKeys("Buy 2");
		search.click();
		WebElement editBtn = driver.findElement(By.xpath("//tbody//tr[1]//i[@class='fas fa-edit']"));
		editBtn.click();
		news.clear();
		news.sendKeys(updatedText);
		updateBtn.click();
	}

	public void deleteCreateMessage() {

		List<WebElement> messageCells = driver
				.findElements(By.xpath("//table[@class='table table-bordered table-hover table-sm']//tr//td[1]"));
		String message = "Buy 2 Get 1 Offer on selected items";
		for (int i = 0; i < messageCells.size(); i++) {
			if (messageCells.get(i).getText().equals(message)) {
				String locator = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + (i + 1)
						+ "]//td[2]//a//i[@class='fas fa-trash-alt']";
				WebElement element = driver.findElement(By.xpath(locator));
				element.click();
				driver.switchTo().alert().accept();
				break;
			}
		}
	}

	public String getDeleteAlertMessage() {
		return alertMsg.getText();
	}

}