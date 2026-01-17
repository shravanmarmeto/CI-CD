package rahulshettyFramework.rahulshettyFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ecom {

	@Test
	public void ecom() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("buvanshetty@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Shravan@1");
		driver.findElement(By.id("login")).click();
		List<WebElement> all = driver.findElements(By.xpath("//h5[@style='text-transform: uppercase;']"));
		String product = "ZARA COAT 3";
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		for (int i = 0; i < all.size(); i++) {
			String pro = all.get(i).getText();
			if (pro.equalsIgnoreCase(product)) {

				List<WebElement> ele = driver.findElements(By.xpath("//button[@class=\"btn w-10 rounded\"]"));
				act.scrollToElement(ele.get(i)).build().perform();
				ele.get(i).click();
				WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
				wait.until(ExpectedConditions.visibilityOf(toastMessage));
				Assert.assertTrue(toastMessage.isDisplayed());
			}
		}
		WebElement cart = driver.findElement(By.xpath("(//button[@class=\"btn btn-custom\"])[3]"));
		act.scrollToElement(cart).build().perform();
		cart.click();
		List<WebElement> cartAll = driver.findElements(By.xpath("//div[@class=\"cartSection\"]/h3"));
		for (int i = 0; i < cartAll.size(); i++) {

			String pro = cartAll.get(i).getText();
			Assert.assertEquals(pro, product);
		}
		WebElement checkoutButton = driver.findElement(By.xpath("(//button[@class=\"btn btn-primary\"])[3]"));
		act.scrollToElement(checkoutButton).build().perform();
		checkoutButton.click();
		String country = "India";
		act.sendKeys(driver.findElement(By.xpath("(//input[@class='input txt text-validated'])[2]")), country).build()
				.perform();
		List<WebElement> countrydrop = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
		for (int i = 0; i < countrydrop.size(); i++) {
			if (countrydrop.get(i).getText().equals(country)) {
				countrydrop.get(i).click();
			}
		}
		WebElement placeorder = driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"));
		act.scrollToElement(placeorder).build().perform();
		placeorder.click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='hero-primary']")).isDisplayed());

	}
}
