package Website.E_commerce;

import java.io.IOException;
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
import org.testng.annotations.Test;

import Website.E_commerce.CartPage;
import Website.E_commerce.CheckOutPage;
import Website.E_commerce.ConfirmMessage;
import Website.E_commerce.LoginPage;
import Website.E_commerce.ProductCatalog;
import Website.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidation extends BaseTest {

	@Test(groups = {"ErrorValidation_LoginValidation"})
	public void LoginValidation() throws IOException, InterruptedException {
		String addToCart = "ZARA COAT 3";
		String countryName = "INDIA";
		loginPage.LoginWebsite("karunyadav@gmail.com", "12345678");
		Assert.assertEquals("Incorrect email and password.", loginPage.getErrorMessage());
	}
	
	@Test(groups = {"ErrorValidation_ProductValidation"})
	public void ProductValidation() throws IOException, InterruptedException {
		String addToCart = "ZARA COAT 3";
		String countryName = "INDIA";

		ProductCatalog productCatalog = loginPage.LoginWebsite("karunyadav@gmail.com", "123456789");

		// product catalog page
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.clickAddToCart(addToCart);
		CartPage cartPage = productCatalog.goToCartPage();

		// CartPage
		Boolean inCartMatch = cartPage.VerifyProductInCart("ZARA 3");
		Assert.assertFalse(inCartMatch);
	}
}
