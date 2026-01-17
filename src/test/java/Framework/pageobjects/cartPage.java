package Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyFramework.abstractComponent.AbstractComponent;

public class cartPage extends AbstractComponent {

	public cartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	public
	List<WebElement> allProducts;
	@FindBy(xpath = "(//button[@type='button'])[2]")
	WebElement checkouElement;
	
	public void clickCheckOutButton() {
		waitUntilElementIsVisible(checkouElement);
		scrollToElement(checkouElement);
		checkouElement.click();
	}
	
}
