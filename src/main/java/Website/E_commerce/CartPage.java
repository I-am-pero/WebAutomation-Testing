package Website.E_commerce;

import java.util.List;

import org.openqa.selenium.By;
import java.util.stream.Collectors.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Website.AbstractComponent.ReusableComponent;

public class CartPage extends ReusableComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		// this.driver is targating to local driver and copying constructor driver to
		// local driver
		this.driver = driver;

		//
		PageFactory.initElements(driver, this);
	}

	// DEMO PageFactory
	// @FindBy(xpath = "XYZ") -> driver.findElement(By.xpath("//ABC"))

	// Get all product in Cart
	@FindBy(css = "div[class='cartSection'] h3")
	List<WebElement> inCartProducts;

	@FindBy(css = "ul li button[class='btn btn-primary']:last-of-type")
	WebElement clickOnCheckOut;

	public Boolean VerifyProductInCart(String addToCart) {
		Boolean inCartMatch = inCartProducts.stream()
				.anyMatch(cartProducts -> cartProducts.getText().equalsIgnoreCase(addToCart));
	    return inCartMatch;

	}

	public CheckOutPage CheckOutPage() {
		// Click checkout
		clickOnCheckOut.click();

		// CheckOutPage
		return new CheckOutPage(driver);
	}

}
