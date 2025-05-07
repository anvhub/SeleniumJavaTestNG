package testCase;

import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import elementRepository.AdminPage;
import elementRepository.LoginPage;
import elementRepository.ManageCategoryPage;

public class ManageCategoryTest extends BaseClass {
	LoginPage lp;
	AdminPage ap;
	ManageCategoryPage cp;

	@Test
	public void createNewCategory() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		ap = lp.login(groceryData(1, 0), groceryData(1, 1));
		cp = ap.clickOnManageCategoryTab();
		cp.createNewCategory();
		String actual = cp.getAlertMsg();
		String expected = "�\n" + "Alert!\n" + "Category Created Successfully";
		Assert.assertEquals(actual, expected, "::Category not created");
	}
}
