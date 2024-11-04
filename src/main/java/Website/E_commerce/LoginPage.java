package Website.E_commerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Website.AbstractComponent.ReusableComponent;

public class LoginPage extends ReusableComponent{
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// this.driver is targating to local driver and copying constructor driver to local driver
		this.driver = driver;
		
		//
		PageFactory.initElements(driver, this);
	}
	
	// DEMO PageFactory
	// @FindBy(xpath = "XYZ") -> driver.findElement(By.xpath("//ABC"))
	
	@FindBy(xpath = "//input[@id = 'userEmail']")
	WebElement userEmail;
	
	@FindBy(xpath = "//input[@id = 'userPassword']")
	WebElement userPassword;
	
	@FindBy(xpath = "//input[@id='login']")
	WebElement loginButton;
	
	@FindBy(css = "div[aria-label='Incorrect email or password.'")
	WebElement errorMessage;
	
	public ProductCatalog LoginWebsite(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		
		// product catalog page
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage() {
		waitForElementVisibilityOf(errorMessage);
		return errorMessage.getText();
	}
	
	public void goToURL() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
