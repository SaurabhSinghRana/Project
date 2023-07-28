package Org.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Org.Configuration.Abstract;

public class OrderAck extends Abstract {
	WebDriver driver;

	public OrderAck(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement orderConfirmation;

	public String orderAck() {
		return orderConfirmation.getText();
	}
}
