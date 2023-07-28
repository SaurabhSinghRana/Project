package Organization.Ecommerce;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	
	public void config() {
		ExtentSparkReporter reporter = new ExtentSparkReporter("Reports\\report.html");
		reporter.config().setReportName("Test Automation");
		reporter.config().setDocumentTitle("Report");
		
		ExtentReports extentreports = new ExtentReports(); 
		extentreports.attachReporter(reporter);
		extentreports.setSystemInfo("Tester", "Saurabh");
			
	}

}
