package testCase;

import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import constant.Constant;
import elementRepository.AdminPage;
import elementRepository.LoginPage;

public class LoginPageTest extends BaseClass {
	LoginPage lp;
	AdminPage ap;

	@Test
	public void verifyDashboardTextWhileLoginWithValidCredentials() throws InvalidFormatException, IOException {
		lp = new LoginPage(driver);
		//ap = new AdminPage(driver);
		ap=lp.login(groceryData(1,0),groceryData(1,1));
		String actual = ap.getDashboardString();
		String expected = "Dashboard";
		Assert.assertEquals(actual, expected,Constant.lp_verifyDashboardTextWhileLoginWithValidCredentials);
	}
	@DataProvider(name="invalid credentials")
	public Object[][] invalidCredentials(){
		return new Object[][] {{"admin1","admin2"},{"admin","admin2"},{"admin1","admin3"},{"admn1","admin2"}};
	}
	
	@Test(dataProvider = "invalid credentials")
	public void verifyTheErrorMessageWhileLoginWithInvalidCredentials(String userName,String password) {
		lp = new LoginPage(driver);
		lp.login(userName,password);
		String actual = lp.getErrorMessage();
		String expected = "×\n"
				+ "Alert!\n"
				+ "Invalid Username/Password";
		Assert.assertEquals(actual, expected, "::Error Message not as expected");
}}
