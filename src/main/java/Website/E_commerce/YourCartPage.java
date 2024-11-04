package Website.E_commerce;

import java.util.List;

import org.openqa.selenium.By;
import java.util.stream.Collectors.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Website.AbstractComponent.ReusableComponent;

public class YourCartPage extends ReusableComponent {
	WebDriver driver;

	public YourCartPage(WebDriver driver) {
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
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderHistory;

	public Boolean VerifyOrderInCart(String addToCart) {
		Boolean inCartMatch = orderHistory.stream()
				.anyMatch(cartProducts -> cartProducts.getText().equalsIgnoreCase(addToCart));
	    return inCartMatch;

	}
}
