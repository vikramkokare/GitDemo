package vikramkokareacadamey.resources;

import java.io.ObjectInputStream.GetField;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	static ExtentReports extent = new ExtentReports();
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir")+ "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Vikram Kokare");
		return extent;
	}

}
