package testCase;

import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import elementRepository.AdminPage;
import elementRepository.LoginPage;
import elementRepository.ManageCategoryPage;

public class ManageCategoryTest extends BaseClass {
	LoginPage lp;
	AdminPage ap;
	ManageCategoryPage cp;

	@Test(priority = 1,groups = {"smoke"})
    public void verifyAlertWhileCreatingNewCategory() throws IOException {
        lp = new LoginPage(driver);
        ap = lp.login(groceryData(1, 0), groceryData(1, 1));
        cp = ap.clickOnManageCategoryTab();        
        // Check if category already exists before creating a new one
        String categoryToCreate = "Groceries";
        if (!cp.isCategoryPresentInTable(categoryToCreate)) {
            cp.createNewCategory(categoryToCreate);        }
        String rawAlertMsg = cp.getAlertMsg();
        String actual = cp.cleanAlertText(rawAlertMsg).replaceAll("[^\\x00-\\x7F]", "").trim();
        String expected;
        if (actual.equalsIgnoreCase("Category Already Exists")) {
            expected = "Category Already Exists";
            Assert.assertEquals(actual, expected, Constant.cp_verifyAlertCategoryAlreadyExists);
        } else {
            expected = "Category Created Successfully";
            Assert.assertEquals(actual, expected, Constant.cp_verifyAlertWhileCreatingNewCategory);
        }
    }

    @Test(priority = 2)
    public void verifyCategoryIsListedWhileSearching() throws IOException {
        lp = new LoginPage(driver);
        ap = lp.login(groceryData(1, 0), groceryData(1, 1));
        cp = ap.clickOnManageCategoryTab();
        String categoryToSearch = "Groceries";
        cp.searchCategoryByName(categoryToSearch);
        String actual = cp.isCategoryPresentInTable(categoryToSearch) ? categoryToSearch : "Not Found";
        String expected = categoryToSearch;
        Assert.assertEquals(actual, expected, Constant.cp_verifyCategoryIsListedWhileSearching);
    }

    @Test(priority = 3)
    public void verifyCategoryIsEditedSuccessfully() throws InvalidFormatException, IOException {
        lp = new LoginPage(driver);
        ap = lp.login(groceryData(1, 0), groceryData(1, 1));
        cp = ap.clickOnManageCategoryTab();
        cp.editCategoryByName("Diecast", "Diecasts");
        // Handling alert and checking for success
        try {
            String actual = cp.cleanAlertText(cp.getAlertMsg()).replaceAll("[^\\x00-\\x7F]", "").trim();
            String expected = "Category Updated Successfully";
            Assert.assertEquals(actual, expected, Constant.cp_verifyCategoryIsEditedSuccessfully);
        } catch (TimeoutException e) {
            Assert.fail("Alert for category edit was not found.");
        }
    }

    @Test(priority = 4)
    public void verifyCategoryIsDeletedSuccessfully() throws IOException {
        lp = new LoginPage(driver);
        ap = lp.login(groceryData(1, 0), groceryData(1, 1));
        cp = ap.clickOnManageCategoryTab();
        String categoryToDelete = "Groceries";
        cp.deleteCategoryByName(categoryToDelete);
        // Handling alert and checking for success
        try {
            String rawAlertMsg = cp.getAlertMsg();
            String actual = cp.cleanAlertText(rawAlertMsg).replaceAll("[^\\x00-\\x7F]", "").trim();
            String expected = "Category Deleted Successfully";
            Assert.assertEquals(actual, expected, Constant.cp_verifyCategoryIsDeletedSuccessfully);
        } catch (TimeoutException e) {
            Assert.fail("Alert for category deletion was not found.");
        }
    }
}
