package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class WebDriver13_Headless_Chrome {
	private WebDriver driver;

	@Test
	public void TC_01_ChromeHeadless() {
		System.setProperty("webdriver.chrome.driver", "..\\SELENIUM_WEBDRIVER\\driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1366x768");
		driver = new ChromeDriver(options);
		System.out.println("Run with Chrome Headless");
		
		driver.get("https://automationfc.com");
		System.out.println("Step 01 - Open Automation FC site");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		System.out.println("Step 02 - Wait for page loaded successfully");

		Assert.assertEquals("Automation FC", driver.getTitle());
		Assert.assertEquals("https://automationfc.com/", driver.getCurrentUrl());
		System.out.println("Step 03 - Verify HomePage url and title");
	}
	
	@Test
	public void TC_02_ChromeGUI() {
		System.setProperty("webdriver.chrome.driver", "..\\SELENIUM_WEBDRIVER\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Run with Chrome GUI");
		
		driver.get("https://automationfc.com");
		System.out.println("Step 01 - Open Automation FC site");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		System.out.println("Step 02 - Wait for page loaded successfully");

		Assert.assertEquals("Automation FC", driver.getTitle());
		Assert.assertEquals("https://automationfc.com/", driver.getCurrentUrl());
		System.out.println("Step 03 - Verify HomePage url and title");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Close browser");
		driver.quit();
	}
}