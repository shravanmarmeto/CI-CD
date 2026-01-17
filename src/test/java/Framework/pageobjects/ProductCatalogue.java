package Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyFramework.abstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	public ProductCatalogue(WebDriver driver) {
		super(driver); 
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath = "//h5[@style='text-transform: uppercase;']")
	List<WebElement> allProducts;
	
	@FindBy(xpath = "//div[@id='res']")
	WebElement productCount;
	
	@FindBy(xpath = "//button[@class='btn w-10 rounded']")
	public
	List<WebElement> atcButton;
	
	@FindBy(xpath = "//div[@id='toast-container']")
	public
	WebElement toastMessage;
	
	@FindBy(xpath = "(//button[@class='btn btn-custom'])[3]")
	WebElement cartIcon;
	
	public List<WebElement> getProductList() {
		waitUntilInvisible(toastMessage);
		waitUntilElementIsVisible(productCount);
		return allProducts;
	}
	
	
	public void clickCartIcon() {
		waitUntilInvisible(toastMessage);
		scrollToElement(cartIcon);
		cartIcon.click();
	}
}
