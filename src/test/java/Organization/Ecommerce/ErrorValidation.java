package Organization.Ecommerce;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ErrorValidation extends BaseTest {

	WebDriver driver;
	@Test(dataProvider = "getData", groups = "error")
	public void invalidLogin(String email, String password) throws MalformedURLException {
//		driver = InitalizeFirefox();
		login.goToURL();
		String loginMessage = login.loginUser(email, password);
		System.out.println(loginMessage);
		Assert.assertEquals(loginMessage, "Incorrect email or password.@");

	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "abc@gmail.com", "12342@er" } };

	}

}
