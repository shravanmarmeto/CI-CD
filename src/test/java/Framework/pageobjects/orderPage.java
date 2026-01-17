package Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyFramework.abstractComponent.AbstractComponent;

public class orderPage extends AbstractComponent {
	WebDriver driver;

	public orderPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	public List<WebElement> allOrders;
}
