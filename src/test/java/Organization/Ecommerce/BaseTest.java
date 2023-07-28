package Organization.Ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Org.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public LandingPage login;
	public WebDriver driver;
	public ExtentReports extent;

	public WebDriver Initalize(String Browser) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");

		if (Browser.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (Browser.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (Browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (Browser.contains("Chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Browser.contains("headless")) {
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1376, 768));
			} else
				driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public WebDriver InitalizeChrome() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.BROWSER_NAME, "chrome");
//		EdgeOptions cap = new EdgeOptions();

		driver = new RemoteWebDriver(new URL("http://192.168.29.158:4444"), cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public WebDriver InitalizeFirefox() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");
		driver = new RemoteWebDriver(new URL("http://192.168.29.158:4444"), cap);
		return driver;
	}

//	@BeforeMethod(alwaysRun = true)
	public LandingPage lauchApplication() throws IOException {
		Properties property = new Properties();
		FileInputStream fis = new FileInputStream("property\\config.properties");
		property.load(fis);

		String Browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: property.getProperty("Browser");
		driver = Initalize(Browser);
		login = new LandingPage(driver);
		login.goToURL();
		return login;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplicationOnChrome() throws MalformedURLException {
		driver = InitalizeChrome();
		login = new LandingPage(driver);
		login.goToURL();
		return login;
	}

	public LandingPage launchApplicationOnFirefox() throws MalformedURLException {
		driver = InitalizeFirefox();
		login = new LandingPage(driver);
		login.goToURL();
		return login;
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		System.out.println("Closing the Browser");
		driver.close();
//		driver.quit();

	}

	public List<HashMap<String, String>> jsonUtility(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> json = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return json;
	}

//	@BeforeMethod(alwaysRun = true)
	public ExtentReports getReports() {
		String path = System.getProperty("user.dir");
		System.out.println(path);
		ExtentSparkReporter reporter = new ExtentSparkReporter(path + "\\Reports\\report.html");
		reporter.config().setReportName("Test Automation");
		reporter.config().setDocumentTitle("Report");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Saurabh");
		return extent;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws Exception {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("target\\" + testCaseName + ".png"));
		return "target\\" + testCaseName + ".png";
	}

//	@AfterClass(alwaysRun = true)
	public void reportFlush() {
		extent.flush();
	}
}
