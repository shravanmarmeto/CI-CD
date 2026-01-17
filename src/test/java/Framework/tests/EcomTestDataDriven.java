package Framework.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class EcomTestDataDriven extends Baseclass {
	// String product = "ZARA COAT 3";

	@Test(dataProvider = "data", groups= {"purchase"})
	public void ecom(HashMap< String, String> input) throws Exception {
		lp.login(input.get("email"), input.get("password"));
		ProductCatalogue plp = new ProductCatalogue(driver);
		List<WebElement> all = plp.getProductList();

		// Thread.sleep(2000);
		for (int i = 0; i < all.size(); i++) {
			String pro = all.get(i).getText();
			if (pro.equalsIgnoreCase(input.get("product"))) {

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
			Assert.assertEquals(pro, input.get("product"));
		}
		c.clickCheckOutButton();
		checkoutPage cp = new checkoutPage(driver);
		String country = "India";
		Actions act = new Actions(driver);
		act.sendKeys(cp.countryTextField, country).build().perform();
		List<WebElement> countrydrop = cp.countryDropdown;
		for (WebElement c1 : countrydrop) {
			if (c1.getText().equalsIgnoreCase(country)) {
				c1.click();

				break;
			}
		}

		cp.clickPlaceOrder();
		Assert.assertTrue(cp.thankyouMessage.isDisplayed());

	}
	
	

	@DataProvider
	public Object[][] data() {
		HashMap< String, String> map=new HashMap<String, String>();
		map.put("email", "buvanshetty@gmail.com");
		map.put("password", "Shravan@1");
		map.put("product", "ZARA COAT 3");
		
		HashMap< String, String> map1=new HashMap<String, String>();
		map1.put("email", "kavanshetty@gmail.com");
		map1.put("password", "Shravan@1");
		map1.put("product", "ADIDAS ORIGINAL");
		return new Object[][] { {map}, {map1} };
		
		
		
//		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\data\\purchaseorder.json"),
//				StandardCharsets.UTF_8);
//		return new Object[][] { {data.get(0)}, {data.get(1)} };
//		
		//{{"email", "password", "product"}}
	}
}
