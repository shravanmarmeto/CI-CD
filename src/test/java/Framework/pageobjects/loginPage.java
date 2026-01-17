package Framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyFramework.abstractComponent.AbstractComponent;

public class loginPage extends AbstractComponent {

	WebDriver driver;

	public loginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement username;
	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement password;
	@FindBy(id = "login")
	WebElement loginButton;
	@FindBy(xpath = "//div[contains(@class,'toast-error')]")
	WebElement errorMessage;
	@FindBy(xpath = "(//button[@class='btn btn-custom'])[2]")
	WebElement ordersPage;

	public void login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginButton.click();
	}

	public void clickOrders() {
		waitUntilElementIsVisible(ordersPage);
		ordersPage.click();
	}

	public void goToWebsite() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");

	}

	public String getErrorMessage() {
		waitUntilElementIsVisible(errorMessage);
		return errorMessage.getText();
	}
}
