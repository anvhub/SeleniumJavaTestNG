package testCase;

import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import elementRepository.AdminPage;
import elementRepository.LoginPage;
import elementRepository.ManageNewsPage;

public class ManageNewsTest extends BaseClass {

	LoginPage lp;
	AdminPage ap;
	ManageNewsPage np;

	@Test(groups = {"smoke"})
	public void verifyAlertSuccessWhileCreatingNewNews() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		ap = lp.login(groceryData(1, 0), groceryData(1, 1));
		np = ap.clickOnManageNewsTab();
		np.createNewMsg();
		String actual = np.getAlertMsg();
		String expected = "×\n" + "Alert!\n" + "News Created Successfully";
		Assert.assertEquals(actual, expected, Constant.np_verifyAlertSuccessWhileAddingNewNews);
	}

	@Test
	public void verifyNewsIsListedWhileSearching() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		ap = lp.login(groceryData(1, 0), groceryData(1, 1));
		np = ap.clickOnManageNewsTab();
		np.searchMsg();
		String actualSearchMsg = np.getSearchMsg();
		String expectedSearchMsg = "Buy 2";
		String actual = actualSearchMsg.contains(expectedSearchMsg) ? expectedSearchMsg : "Not Found";
		Assert.assertEquals(actual, expectedSearchMsg, Constant.np_verifyCategoryIsListedWhileSearching);
	}
	@Test
	public void verifyAlertSuccessWhileUpdatingNews() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		ap = lp.login(groceryData(1, 0), groceryData(1, 1));
		np = ap.clickOnManageNewsTab();
		String updatedText = "Super Saver Deals Available This Week";
		np.updateNews(updatedText);
		String actualUpdateAlert = np.getAlertMsg();
		String expectedUpdateAlert = "×\n" + "Alert!\n" + "News Updated Successfully";
		Assert.assertEquals(actualUpdateAlert, expectedUpdateAlert, Constant.np_verifyAlertSuccessWhileUpdatingNews);
	}
	@Test
	public void verifyAlertSuccessWhileDeletingNews() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		ap = lp.login(groceryData(1, 0), groceryData(1, 1));
		np = ap.clickOnManageNewsTab();
		np.deleteCreateMessage();
		String actualDeleteAlert = np.getDeleteAlertMessage();
		String expectedDeleteAlert = "×\n" + "Alert!\n" + "News Deleted Successfully";
		Assert.assertEquals(actualDeleteAlert, expectedDeleteAlert, Constant.np_verifyAlertSuccessWhileDeletingNews);
	}

}
