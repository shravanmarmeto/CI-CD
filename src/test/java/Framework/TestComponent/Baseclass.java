package Framework.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Framework.pageobjects.loginPage;
import rahulshettyFramework.abstractComponent.AbstractComponent;

public class Baseclass {
	public WebDriver driver;
	public loginPage lp;

	public WebDriver initializeDriver() throws IOException {
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\data.properties");
		p.load(fis);
		// 1 browser value form system i.e CMD another from property file
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: p.getProperty("browser");

		// p.getProperty("browser");
		if (browser.contains("chrome")) { //contains for headless
			ChromeOptions option = new ChromeOptions();
			if (browser.contains("headless")) {
				option.addArguments("headless");
			}
			driver = new ChromeDriver(option);

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(1440, 990));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	public String getScreenshot(String name, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(System.getProperty("user.dir") + "//reports//" + name + ".png");
		FileUtils.copyFile(src, des);
		return System.getProperty("user.dir") + "//reports//" + name + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public loginPage launchApplication() throws Exception {
		driver = initializeDriver();
		lp = new loginPage(driver);
		lp.goToWebsite();
		return lp;
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.close();
	}
}
