package Wbsite.Resources;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ExtentReporterNG {
	@BeforeTest
	public static ExtentReports config() {
		// ExtentReports, ExtentSparkReporter
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter ESR = new ExtentSparkReporter(path);
		ESR.config().setReportName("Web Automation Full Report");
		ESR.config().setDocumentTitle("Test Result");
		
		ExtentReports ER = new ExtentReports();
		ER.attachReporter(ESR);
		ER.setSystemInfo("Tester", "Karun Yadav");
		return ER;
	}
}
