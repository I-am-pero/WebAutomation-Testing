package Website.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MavenProject.PageObjects.LoginDetails;
import Website.E_commerce.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {
		// Properties class

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\Wbsite\\Resources\\GlobalData.Properties");

		// Expecting file input steam
		properties.load(fileInputStream);
		// Property you want
		String browserName = properties.getProperty("browser");

		if (browserName.equalsIgnoreCase("fireFox")) {
			// Open Browser
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("chrome")) {
			// Open Browser
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			// Open Browser
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		// Wait FullScreen
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	// Read data from jeson file
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException {
		// Read json to string
		String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap -> get jackson datbind from repo.
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	// Snagit
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot SC = (TakesScreenshot)driver;
		File source = SC.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";			
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();

		// LoginPage
		loginPage = new LoginPage(driver);
		loginPage.goToURL();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}
}
