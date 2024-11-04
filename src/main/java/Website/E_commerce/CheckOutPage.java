package Website.E_commerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KarunYadav.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		// PageFactory
		PageFactory.initElements(driver, this);
	}

	// PageFactory
	@FindBy(css = "a[class='btnn action__submit ng-star-inserted']")
	WebElement submit;

	// PageFactory
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	// PageFactory
	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement selectCountry;

	By waitForCountryListToAppear = By
			.cssSelector("section[class='ta-results list-group ng-star-inserted']:nth-child(2)");

	public void SelectCountry(String countryName) {
		// Using Action class to give input in the field
		Actions act = new Actions(driver);
		act.sendKeys(country, countryName).build().perform();

		waitForElementToAppear(waitForCountryListToAppear);

		selectCountry.click();
	}

	public ConfirmMessage Submit() {
		submit.click();
		ConfirmMessage confirmMessage = new ConfirmMessage(driver);
		return confirmMessage;
	}
}
