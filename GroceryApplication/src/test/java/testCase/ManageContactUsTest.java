package testCase;

import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import elementRepository.AdminPage;
import elementRepository.LoginPage;
import elementRepository.ManageContactUsPage;

public class ManageContactUsTest extends BaseClass {
	LoginPage lp;
	AdminPage ap;
	ManageContactUsPage cup;

	@Test(groups = {"smoke"})
	public void verifyAlertSuccessWhileUpdatingContactUs() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		ap = lp.login(groceryData(1, 0), groceryData(1, 1));
		cup = ap.clickOnManageContactTab();
		cup.updateContactUsUsingFaker();
		String actual = cup.getAlertMsg();
		String expected = "×\n" + "Alert!\n" + "Contact Updated Successfully";
		Assert.assertEquals(actual, expected,Constant.cup_verifyAlertSuccessWhileUpdatingContactUs);
	}
}
