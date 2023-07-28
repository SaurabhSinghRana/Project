package Organization.Ecommerce;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Org.PageObject.OrderAck;
import Org.PageObject.PlaceOrder;
import Org.PageObject.ProductCatalouge;

public class EcommerceBase extends BaseTest {

	@Test(dataProvider = "getData")
	public void createOrder(Map<String, String> input) throws InterruptedException {
//		extent.createTest("createOrder");
		ProductCatalouge product = new ProductCatalouge(driver);
		String loginMessage = login.loginUser(input.get("email"), input.get("password"));
		System.out.println(loginMessage);
		Assert.assertEquals(loginMessage, "Login Successfully");
		PlaceOrder order = product.selectProduct(input.get("item"));
		product.cart();
		order.selectCountry("India");
		OrderAck ack = order.placeOrder();
		String acknowledge = ack.orderAck();
		System.out.println(acknowledge);
		Assert.assertEquals(acknowledge, "THANKYOU FOR THE ORDER.");

	}

//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"anshika02@gmail.com","Iamking@123","ADIDAS ORIGINAL"},{"ramansingh@gmail.com","Raman123$","IPHONE 13 PRO"}};		
//	}

//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "anshika02@gmail.com");
//		map1.put("password", "Iamking@123");
//		map1.put("item", "ADIDAS ORIGINAL");
//		HashMap<Object, Object> map2 = new HashMap<Object, Object>();
//		map2.put("email", "ramansingh@gmail.com");
//		map2.put("password", "Raman123$");
//		map2.put("item", "IPHONE 13 PRO");
//		return new Object[][] { { map1 }, { map2 } };
//	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = jsonUtility("property\\Data.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}