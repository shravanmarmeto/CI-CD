package Framework.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Framework.TestComponent.Baseclass;

public class homepagetestdummy extends Baseclass {

	@Test
	public void ecomHomepage() throws Exception {
		lp.login("kavanshetty@gmail.com", "Shravan@1");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='res']")).isDisplayed());
	}
}