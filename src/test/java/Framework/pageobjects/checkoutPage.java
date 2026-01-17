package Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyFramework.abstractComponent.AbstractComponent;

public class checkoutPage extends AbstractComponent {

	public checkoutPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//input[@class='input txt text-validated'])[2]")
	public
	WebElement countryTextField;
	@FindBy(xpath = "//span[@class='ng-star-inserted']")
	public
	List<WebElement> countryDropdown;
	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	
	public WebElement placeOrderButton;
	@FindBy(xpath = "//h1[@class='hero-primary']")
	public
	WebElement thankyouMessage;
	public void clickPlaceOrder() {
		waitUntilElementIsVisible(placeOrderButton);
		randomClick();
		scrollToElement(placeOrderButton);
		placeOrderButton.click();
	}
	
}
