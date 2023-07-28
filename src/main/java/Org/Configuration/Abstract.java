package Org.Configuration;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Abstract {

	public WebDriver driver;
	WebDriverWait wait;

	public Abstract(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	@FindBy(xpath = "//button[contains(@routerlink,'cart')]")
	static WebElement cart;

	@FindBy(css = ".form-group input")
	WebElement country;

	public void waitUntilElementVisible(By tagName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(tagName));
	}

	public void cart() {
		cart.click();
	}

	public void setCountry(String countryName) {
		Actions selectCountry = new Actions(driver);
		selectCountry.sendKeys(country, countryName).build().perform();
	}

	
}
