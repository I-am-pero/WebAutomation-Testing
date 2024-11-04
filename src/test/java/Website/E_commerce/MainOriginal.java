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

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainOriginal {

	public static void main(String[] args) {
		String addToCart = "ZARA COAT 3";
		// Open Browser
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();

		// Wait FullScreen
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// Open Website
		driver.get("https://rahulshettyacademy.com/client");

		// Send username and password
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("karunyadav@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("123456789");
		// Click on login
		driver.findElement(By.xpath("//input[@id='login']")).click();

		// Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// Retrive all items
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// Iterate product one by one in productName
		WebElement selectedProduct = products.stream()
				.filter(productName -> productName.findElement(By.xpath("//h5//b")).getText().equals(addToCart))
				.findFirst().orElse(null);

		// Add to cart
		selectedProduct.findElement(By.cssSelector("button[class='btn w-10 rounded']:last-of-type")).click();

		// wait until Product Added To Cart message appere
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));

		// wait until Animation circle disapere
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// Click on Cart
		driver.findElement(By.xpath("//button[@class='btn btn-custom']//i[@class='fa fa-shopping-cart']")).click();

		// Get all product in Cart
		List<WebElement> inCart = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		Boolean inCartMatch = inCart.stream()
				.anyMatch(cartProducts -> cartProducts.getText().equalsIgnoreCase(addToCart));
		Assert.assertTrue(inCartMatch);

		// Click checkout
		driver.findElement(By.cssSelector("ul li button[class='btn btn-primary']:last-of-type")).click();

		// Enter country
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india")
				.build().perform();
		// Wait for dropdown to appere
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']")));
		
		//click on country
		driver.findElement(By.cssSelector("button[class='ta-item list-group-item ng-star-inserted']:last-of-type")).click();

		// Click placeorder
		driver.findElement(By.cssSelector("a[class='btnn action__submit ng-star-inserted']")).click();
		
		//Check if text matches
		String confirmMessage = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
