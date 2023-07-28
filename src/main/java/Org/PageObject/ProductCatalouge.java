package Org.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Org.Configuration.Abstract;

public class ProductCatalouge extends Abstract {
	WebDriver driver;

	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".toast-message")
	WebElement alertMessage;

	By list = By.cssSelector("b");
	By Item = By.cssSelector("button:last-of-type");
	By toastMessage = By.cssSelector(".toast-message");

	public PlaceOrder selectProduct(String item) {
		WebElement prod = products.stream().filter(product -> product.findElement(list).getText().equals(item))
				.findFirst().orElse(null);
		prod.findElement(Item).click();
		waitUntilElementVisible(toastMessage);
		PlaceOrder order = new PlaceOrder(driver);
		return order;
	}

//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//	WebElement prod = products.stream()
//			.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL"))
//			.findFirst().orElse(null);
//
//	prod.findElement(By.cssSelector("button:last-of-type")).click();
//
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));
//	driver.findElement(By.cssSelector(".toast-message")).getText();

}
