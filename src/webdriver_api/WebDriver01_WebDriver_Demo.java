package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class WebDriver01_WebDriver_Demo {
	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.out.println("Init firefox browser");
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_OpenWordPressSite() {
		System.out.println("Open daominhdam.wordpress.com");
		driver.get("https://daominhdam.wordpress.com");
		
		System.out.println("Maximize browser");
		driver.manage().window().maximize();
		
		System.out.println("Wait for page loaded successfully");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Close browser");
		driver.quit();
	}
}