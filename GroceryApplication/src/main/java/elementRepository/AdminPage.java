package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;

public class AdminPage {
	GeneralUtilities gu = new GeneralUtilities();
	WebDriver driver;

	public AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[text()=\"Dashboard\"]")
	WebElement dashboardText;
	@FindBy(xpath = "//a//p[text()='Manage News']")
	WebElement manageNewsTab;
	@FindBy(xpath = "//a//p[text()='Manage Category']")
	WebElement manageCategoryTab;
	@FindBy(xpath = "//a//p[text()='Manage Contact']")
	WebElement manageContactTab;
	@FindBy(xpath="//p[text()='Manage Footer Text']")
	WebElement manageFooterTab;

	public String getDashboardString() {
		return dashboardText.getText();
	}

	public ManageNewsPage clickOnManageNewsTab() {
		manageNewsTab.click();
		return new ManageNewsPage(driver);
	}

	public ManageCategoryPage clickOnManageCategoryTab() {
		manageCategoryTab.click();
		return new ManageCategoryPage(driver);

	}

	public ManageContactUsPage clickOnManageContactTab() {
		manageContactTab.click();
		return new ManageContactUsPage(driver);
	}
	
	public ManageFooterPage clickOnManageFooterTab() {
		manageFooterTab.click();
		return new ManageFooterPage(driver);
	}
}
