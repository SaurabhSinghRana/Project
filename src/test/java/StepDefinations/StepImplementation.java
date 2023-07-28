package StepDefinations;

import java.io.IOException;

import org.testng.Assert;

import Org.PageObject.LandingPage;
import Org.PageObject.OrderAck;
import Org.PageObject.PlaceOrder;
import Org.PageObject.ProductCatalouge;
import Organization.Ecommerce.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepImplementation extends BaseTest {

	public LandingPage landginpage;
	String acknowledge;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landginpage = lauchApplication();
	}

	@Given("^I logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_password(String username, String password) {
		String loginMessage = login.loginUser(username, password);
		System.out.println(loginMessage);
		Assert.assertEquals(loginMessage, "Login Successfully");
	}

	@When("^I add the product (.+) in cart and checkout the product$")
	public void add_the_product_in_cart(String item) throws InterruptedException {
		ProductCatalouge product = new ProductCatalouge(driver);
		PlaceOrder order = product.selectProduct(item);
		product.cart();
		order.selectCountry("India");
		OrderAck ack = order.placeOrder();
		acknowledge = ack.orderAck();

	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		System.out.println(acknowledge);
		Assert.assertEquals(acknowledge, string);
	}

	@After
	public void closeDriver() {
		closeBrowser();
	}

}
