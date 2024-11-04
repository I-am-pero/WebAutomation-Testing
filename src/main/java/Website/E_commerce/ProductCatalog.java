package Website.E_commerce;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Website.AbstractComponent.ReusableComponent;

public class ProductCatalog extends ReusableComponent {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		// this.driver is targating to local driver and copying constructor driver to
		// local driver
		this.driver = driver;

		//
		PageFactory.initElements(driver, this);
	}

	// DEMO PageFactory
	// @FindBy(xpath = "XYZ") -> driver.findElement(By.xpath("//ABC"))

	// Retrive all items
	@FindBy(css = "div[class*='mb-3']")
	List<WebElement> productName;

	@FindBy(css = ".ng-animating")
	WebElement waitForAnimationToDisappear;
	
	By waitForProductToAppear = By.cssSelector("div[class*='mb-3']");
	By clickAddToCartOnProduct = By.cssSelector("button[class='btn w-10 rounded']");
	By addToCartGreenMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(waitForProductToAppear);
		return productName;
	}

	public WebElement whichProductAddToCart(String addToCart) {
		// Iterate product one by one in productName
		WebElement selectedProduct = getProductList().stream()
				.filter(product -> product.findElement(By.xpath("//div[@class='card-body']//h5//b")).getText().equals(addToCart))
				.findFirst().orElse(null);
		return selectedProduct;
	}

	public void clickAddToCart(String addToCart) {
		// Add to cart
		WebElement addProductToCart = whichProductAddToCart(addToCart);
		addProductToCart.findElement(clickAddToCartOnProduct).click();
		waitForElementToAppear(addToCartGreenMessage);
		waitForElementToDisppear(waitForAnimationToDisappear);
		
		
	}

}
