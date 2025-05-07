package testCase;

import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import elementRepository.AdminPage;
import elementRepository.LoginPage;
import elementRepository.ManageFooterPage;

public class ManageFooterTest extends BaseClass {
	LoginPage lp;
	AdminPage ap;
	ManageFooterPage fp;
  @Test
  public void updateFooterText() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		ap = lp.login(groceryData(1, 0), groceryData(1, 1));
		fp = ap.clickOnManageFooterTab();
		fp.updateFooterUsingFaker();
		String actual = fp.getAlertMsg();
		String expected = "×\n" + "Alert!\n" + "Footer Text Updated Successfully";
		Assert.assertEquals(actual, expected, "::Footer Text Not Updated");
	}
}
