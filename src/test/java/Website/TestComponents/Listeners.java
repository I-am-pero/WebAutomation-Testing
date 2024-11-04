package Website.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Wbsite.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports eReport = ExtentReporterNG.config();
	ExtentTest test;
	ThreadLocal<ExtentTest> ET = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = eReport.createTest(result.getMethod().getMethodName());
		ET.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ET.get().log(Status.PASS, "Pass");

		// 1 Take Snagit
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2 Attach in report
		ET.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ET.get().fail(result.getThrowable());
		// To get driver
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			exc.printStackTrace();
		}
		
		// 1 Take Snagit
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2 Attach in report
		ET.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		eReport.flush();
	}
}
