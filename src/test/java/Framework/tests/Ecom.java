package Framework.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.TestComponent.Baseclass;
import Framework.pageobjects.ProductCatalogue;
import Framework.pageobjects.cartPage;
import Framework.pageobjects.checkoutPage;
import Framework.pageobjects.orderPage;

public class Ecom extends Baseclass {
	String product = "ZARA COAT 3";

	@Test
	public void ecom() throws Exception {
		lp.login("buvanshetty@gmail.com", "Shravan@1");
		ProductCatalogue plp = new ProductCatalogue(driver);
		List<WebElement> all = plp.getProductList();

		//Thread.sleep(2000);
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
		plp.clickCartIcon();
		cartPage c = new cartPage(driver);
		List<WebElement> cartAll = c.allProducts;
		for (int i = 0; i < cartAll.size(); i++) {

			String pro = cartAll.get(i).getText();
			Assert.assertEquals(pro, product);
		}
		c.clickCheckOutButton();
		checkoutPage cp = new checkoutPage(driver);
		String country = "India";
		Actions act = new Actions(driver);
		act.sendKeys(cp.countryTextField, country).build().perform();
		List<WebElement> countrydrop = cp.countryDropdown;
		for (int i = 0; i < countrydrop.size(); i++) {
			if (countrydrop.get(i).getText().equals(country)) {
				countrydrop.get(i).click();
			}
		}
		cp.clickPlaceOrder();
		Assert.assertTrue(cp.thankyouMessage.isDisplayed());

	}

	// Verify whether the placed order is displayed in orders page
	@Test(dependsOnMethods = { "ecom" })
	public void orderpage() throws Exception {
		lp.login("buvanshetty@gmail.com", "Shravan@1");
		lp.clickOrders();
		orderPage op = new orderPage(driver);
		List<WebElement> allorders = op.allOrders;
		for (int i = 0; i < allorders.size(); i++) {
			if (allorders.get(i).getText().equalsIgnoreCase(product)) {
				Assert.assertTrue(allorders.get(i).isDisplayed());
			}
		}

	}

}
