package stepdefinition;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import Framework.TestComponent.Baseclass;
import Framework.pageobjects.ProductCatalogue;
import Framework.pageobjects.cartPage;
import Framework.pageobjects.checkoutPage;
import Framework.pageobjects.loginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends Baseclass {

	public loginPage lp;
	public ProductCatalogue plp;

	@Given("Navigate to ecommerce page")
	public void Navigate_to_ecommerce_page() throws Exception {
		lp = launchApplication();
	}

	@Given("Login with {string} and {string}")
	public void Login_with_email_pwd(String email, String password) {
		lp.login(email, password);
	}

	@When("I add {string} to cart")
	public void Whev_adding_product_to_cart(String product) {
		 plp = new ProductCatalogue(driver);
		List<WebElement> all = plp.getProductList();

		for (int i = 0; i < all.size(); i++) {
			String pro = all.get(i).getText();
			if (pro.equalsIgnoreCase(product)) {
				List<WebElement> ele = plp.atcButton;
				plp.scrollToElement(ele.get(i));
				ele.get(i).click();
				plp.waitUntilElementIsVisible(plp.toastMessage);
				Assert.assertTrue(plp.toastMessage.isDisplayed());
			}
		}
	}

	@And("checkout {string} and submit the order")
	public void checkout_and_submitorder(String product) {
		plp.clickCartIcon();

		cartPage c = new cartPage(driver);
		List<WebElement> cartAll = c.allProducts;

		for (WebElement item : cartAll) {
			Assert.assertEquals(item.getText(), product);
		}

		c.clickCheckOutButton();

		checkoutPage cp = new checkoutPage(driver);
		String country = "India";
		Actions act = new Actions(driver);
		act.sendKeys(cp.countryTextField, country).build().perform();

		for (WebElement ele : cp.countryDropdown) {
			if (ele.getText().equals(country)) {
				ele.click();
				break;
			}
		}

		cp.clickPlaceOrder();
	}

	@Then("{string} message is displayed in thank you page")
	public void success(String string) {
		checkoutPage cp = new checkoutPage(driver);
		Assert.assertEquals(cp.thankyouMessage.getText(), string);

	}
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String expectedMessage) {
		Assert.assertEquals(expectedMessage, lp.getErrorMessage());

	}


}
