package testCase;

import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import elementRepository.AdminPage;
import elementRepository.LoginPage;
import elementRepository.ManageNewsPage;

public class ManageNewsTest extends BaseClass {

	LoginPage lp;
	AdminPage ap;
	ManageNewsPage np;

	@Test
	public void createNews() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		ap = lp.login(groceryData(1, 0), groceryData(1, 1));
		np = ap.clickOnManageNewsTab();
		np.createNewMsg();
		String actual = np.getAlertMsg();
		String expected = "×\n" + "Alert!\n" + "News Created Successfully";
		Assert.assertEquals(actual, expected, "::News not created");
	}
	@Test
	public void searchMsg() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		ap = lp.login(groceryData(1, 0), groceryData(1, 1));
		np = ap.clickOnManageNewsTab();
		np.searchMsg();
		String actualSearchMsg = np.getSearchMsg();
		String expectedSearchMsg = "Buy 2";
		boolean actual = actualSearchMsg.contains(expectedSearchMsg);
		Assert.assertTrue(actual, ":: Search Failed");
	}
	



		
}
