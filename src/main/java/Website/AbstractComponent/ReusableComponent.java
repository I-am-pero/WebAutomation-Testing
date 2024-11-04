package Website.AbstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Website.E_commerce.CartPage;
import Website.E_commerce.YourCartPage;

public class ReusableComponent {
	WebDriver driver;

	public ReusableComponent(WebDriver driver) {
		this.driver = driver;
		//
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@class='btn btn-custom']//i[@class='fa fa-shopping-cart']")
	WebElement goToCartPage;

	@FindBy(xpath = "//button[@class='btn btn-custom']//i[@class='fa fa-handshake-o']")
	WebElement yourOrderPage;
	
	public void waitForElementToAppear(By findBy) {
		// Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementVisibilityOf(WebElement element) {
		// Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisppear(WebElement element) {
		// Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public CartPage goToCartPage() {
		goToCartPage.click();
		
		// CartPage
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public YourCartPage yourCartPage() {
		yourOrderPage.click();
		
		// CartPage
		YourCartPage yourCartPage = new YourCartPage(driver);
		return yourCartPage;
	}
}
