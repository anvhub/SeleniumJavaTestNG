package elementRepository;

import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.GeneralUtilities;
import utilities.WaitUtilities;

public class ManageCategoryPage {
	WebDriver driver;
	WaitUtilities wu = new WaitUtilities();
	GeneralUtilities gu = new GeneralUtilities();

	public ManageCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']//i[@class='fas fa-edit']")
	WebElement newBtn;
	@FindBy(xpath = "//input[@name='category']")
	WebElement categoryName;
	@FindBy(id = "134-selectable")
	WebElement groupSelect;
	@FindBy(xpath = "//input[@id='main_img']")
	WebElement imgfile;
	@FindBy(xpath = "//input[@name='top_menu' and @value='no']")
	WebElement notShowOnTop;
	@FindBy(xpath = "//input[@name='show_home' and @value='no']")
	WebElement notShowOnLeft;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveBtn;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement successAlert;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement failedAlert;
	@FindBy(xpath = "//i[contains(@class,'fa-search')]")
	WebElement searchBtn;
	@FindBy(xpath = "//input[@name='un']")
	WebElement searchCategory;
	@FindBy(xpath = "//button[@name='Search']")
	WebElement searchCategoryBtn;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody")
	List<WebElement> searchResults;

	public void createNewCategory(String categoryName) {
        newBtn.click();
        this.categoryName.clear();
        this.categoryName.sendKeys(categoryName);
        groupSelect.click();
        imgfile.sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\images\\groceries.jpg");
        notShowOnTop.click();
        notShowOnLeft.click();
        saveBtn.click();
    }

    public String getAlertMsg() {
         try {
        	wu.waitForElementClickable(driver, successAlert, 5);           
            return successAlert.getText();
        } catch (TimeoutException e1) {
            try {
            	wu.waitForElementClickable(driver, failedAlert, 5);
                return failedAlert.getText();
            } catch (TimeoutException e2) {
                return "No alert found";
            }
        }
    }

    public String cleanAlertText(String rawText) {
        return rawText.replace("×", "").replace("Alert!", "").trim();
    }

    public void searchCategoryByName(String category) {
        searchBtn.click();
        searchCategory.clear();
        searchCategory.sendKeys(category);
        searchCategoryBtn.click();
    }

    public boolean isCategoryPresentInTable(String categoryName) {
                List<WebElement> updatedSearchResults = searchResults;
        for (WebElement row : updatedSearchResults) {
            if (row.getText().contains(categoryName)) {
                return true;
            }
        }
        return false;
    }

    public void editCategoryByName(String oldName, String newName) {
        searchCategoryByName(oldName);
        for (WebElement row : searchResults) {
            if (row.getText().contains(oldName)) {
                row.findElement(org.openqa.selenium.By.xpath(".//a[contains(@href,'edit')]")).click();
                categoryName.clear();
                categoryName.sendKeys(newName);
                saveBtn.click();
                break;
            }
        }
    }

    public void deleteCategoryByName(String categoryNameToDelete) {
        searchCategoryByName(categoryNameToDelete);
        for (WebElement row : searchResults) {
            if (row.getText().contains(categoryNameToDelete)) {
                row.findElement(org.openqa.selenium.By.xpath(".//a[@class='btn btn-sm btn btn-danger btncss']")).click();
                driver.switchTo().alert().accept();
                break;
            }
        }
    }
}
