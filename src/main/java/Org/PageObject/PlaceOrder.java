package Org.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Org.Configuration.Abstract;

public class PlaceOrder extends Abstract {
	WebDriver driver;

	public PlaceOrder(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li[class='totalRow'] .btn-primary")
	WebElement selectCountry;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(css = ".toast-title")
	WebElement alertMessage;

	By taResult = By.cssSelector(".ta-results");
	By toastTitle = By.cssSelector(".toast-title");

	public void selectCountry(String cntry) {
		selectCountry.click();
		setCountry(cntry);
		waitUntilElementVisible(taResult);
		country.click();
		JavascriptExecutor Js = (JavascriptExecutor) driver;
		Js.executeScript("window.scrollBy(0,5000)");

	}

	public OrderAck placeOrder() throws InterruptedException {
		Thread.sleep(2000);
		submit.click();
		waitUntilElementVisible(toastTitle);
		OrderAck ack = new OrderAck(driver);
		return ack;
	}

}
