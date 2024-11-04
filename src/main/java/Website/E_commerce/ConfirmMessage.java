package Website.E_commerce;

import org.openqa.selenium.WebDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import KarunYadav.AbstractComponents.AbstractComponent;
import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class ConfirmMessage extends AbstractComponent {

	WebDriver driver;

	public ConfirmMessage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmMssg;
	
	public String verificationOfMssg() {
		return confirmMssg.getText();
	}
}
