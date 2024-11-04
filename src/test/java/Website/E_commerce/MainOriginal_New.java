package Website.E_commerce;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Website.E_commerce.CartPage;
import Website.E_commerce.CheckOutPage;
import Website.E_commerce.ConfirmMessage;
import Website.E_commerce.LoginPage;
import Website.E_commerce.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MainOriginal_New {

	public static void main(String[] args) {
		String addToCart = "ZARA COAT 3";
		String countryName = "INDIA";
		// Open Browser
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();

		// Wait FullScreen
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// LoginPage
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goToURL();
		loginPage.LoginWebsite("karunyadav@gmail.com", "123456789");

		// product catalog page
		ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.clickAddToCart(addToCart);
		productCatalog.goToCartPage();

		// CartPage
		CartPage cartPage = new CartPage(driver);
		Boolean inCartMatch = cartPage.VerifyProductInCart(addToCart);
		Assert.assertTrue(inCartMatch);
		cartPage.CheckOutPage();

		// CheckOutPage
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.SelectCountry(countryName);
		checkOutPage.Submit();

		// ConfirmMessage
		ConfirmMessage confirmMessage = new ConfirmMessage(driver);
		String getMessage = confirmMessage.verificationOfMssg();
		Assert.assertTrue(getMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
