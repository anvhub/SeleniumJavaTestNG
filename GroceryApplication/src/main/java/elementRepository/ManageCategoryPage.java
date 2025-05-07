package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageCategoryPage {
	WebDriver driver;

	public ManageCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']//i[@class='fas fa-edit']")
	WebElement newBtn;
	@FindBy(xpath = "//input[@id='category']")
	WebElement category;
	@FindBy(xpath = "//li[@id='134-selectable']")
	WebElement groupSelect;
	@FindBy(xpath="//input[@id='main_img']")
	WebElement imgFile;
	@FindBy(xpath="//input[@value ='no' and @ name='top_menu']")
	WebElement showOnTopMenu;
	@FindBy(xpath="//input[@value ='no' and @ name='show_home']")
	WebElement showOnLeftMenu;
	@FindBy(xpath="//button[@name='create']")
	WebElement saveBtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement alertMsg;
	
	public void createNewCategory() {
		newBtn.click();
		category.sendKeys("Groceries");
		groupSelect.click();
		String imagePath = System.getProperty("user.dir") + "\\src\\test\\resources\\images\\groceries.jpg";
		imgFile.sendKeys(imagePath);
		showOnTopMenu.click();
		showOnLeftMenu.click();
		saveBtn.click();		
	}

	public String getAlertMsg() {
		return alertMsg.getText();
	}
}
