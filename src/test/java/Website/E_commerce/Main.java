package Website.E_commerce;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Website.E_commerce.CartPage;
import Website.E_commerce.CheckOutPage;
import Website.E_commerce.ConfirmMessage;
import Website.E_commerce.LoginPage;
import Website.E_commerce.ProductCatalog;
import Website.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Main extends BaseTest {
	/* Comment*/
	String addToCart = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void order(HashMap<String, String> input) throws IOException, InterruptedException {
		String countryName = "INDIA";

		ProductCatalog productCatalog = loginPage.LoginWebsite(input.get("email"), input.get("password"));

		// product catalog page
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.clickAddToCart(input.get("orderThisItem"));
		CartPage cartPage = productCatalog.goToCartPage();

		// CartPage
		Boolean inCartMatch = cartPage.VerifyProductInCart(input.get("orderThisItem"));
		Assert.assertTrue(inCartMatch);
		CheckOutPage checkOutPage = cartPage.CheckOutPage();

		// CheckOutPage
		checkOutPage.SelectCountry(countryName);
		ConfirmMessage confirmMessage = checkOutPage.Submit();

		// ConfirmMessage
		String getMessage = confirmMessage.verificationOfMssg();
		Assert.assertTrue(getMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "order" })
	public void YourOrder() {
		ProductCatalog productCatalog = loginPage.LoginWebsite("karunyadav@gmail.com", "123456789");
		YourCartPage yourCartPage = productCatalog.yourCartPage();
		Assert.assertTrue(yourCartPage.VerifyOrderInCart(addToCart));
	}

	@DataProvider 
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToHashMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\Website\\Data\\PurchaseOrderData.json");
		
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	/*
	 * @DataProvider 
	 * public Object[][] getData() { HashMap<String, String> map = new
	 * HashMap<String, String>(); map.put("email", "karunyadav@gmail.com");
	 * map.put("password", "123456789"); map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "arunyadav@gmail.com"); map1.put("password",
	 * "Ay@123456789"); map1.put("productName", "ZARA COAT 3"); return new
	 * Object[][] {{map},{map1}}; }
	 */
}
