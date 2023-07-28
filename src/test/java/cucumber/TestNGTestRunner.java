package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber/Test3", glue = "Test3", monochrome = true, plugin = {
		"html:target/cucumber-reports" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
