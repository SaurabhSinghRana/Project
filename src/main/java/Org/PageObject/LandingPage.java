git apackage Org.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Org.Configuration.Abstract;

public class LandingPage extends Abstract {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input#userEmail")
	WebElement email;

	@FindBy(css = "input#userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement login;

	@FindBy(xpath = "//div[contains(@class,'toast-')]")
	WebElement alertMessage;

	By toastMessage = By.cssSelector("#toast-container div div");

	public void goToURL() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

//	driver.findElement(By.cssSelector("input#userEmail")).sendKeys("anshika02@gmail.com");
//	driver.findElement(By.cssSelector("input#userPassword")).sendKeys("Iamking@123");
//	driver.findElement(By.id("login")).click();
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("toast-success")));
//	String loginMessage = driver.findElement(By.className("toast-success")).getText();

	public String loginUser(String emailID, String pwd) {
		email.sendKeys(emailID);
		password.sendKeys(pwd);
		login.click();
		waitUntilElementVisible(toastMessage);
		return alertMessage.getText();
//		ProductCatalouge product = new ProductCatalouge(driver);
//		return product;
	}

}
