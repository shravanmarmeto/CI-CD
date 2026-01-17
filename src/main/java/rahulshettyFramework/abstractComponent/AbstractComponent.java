package rahulshettyFramework.abstractComponent;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void waitUntilElementIsVisible(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitUntilClickable(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilInvisible(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.invisibilityOf(element));
	}
	

	
	public void scrollToElement(WebElement ele) {
		Actions act=new Actions(driver);
		
		act.scrollToElement(ele).build().perform();
	}

	public void randomClick() {
		Actions act=new Actions(driver);
		
		act.click().build().perform();
	}
	
	 
}