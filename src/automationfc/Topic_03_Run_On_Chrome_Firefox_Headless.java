package automationfc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_03_Run_On_Chrome_Firefox_Headless {
	WebDriver driver;

	@Test
	public void TC_01_ChromeHeadless() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1366x768");
		driver = new ChromeDriver(options);

		driver.get("https://automationfc.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		Assert.assertEquals("Automation FC Blog", driver.getTitle());
		Assert.assertEquals("https://automationfc.com/", driver.getCurrentUrl());
	}

	@Test
	public void TC_02_ChromeGUI() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

		driver.get("https://automationfc.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		Assert.assertEquals("Automation FC Blog", driver.getTitle());
		Assert.assertEquals("https://automationfc.com/", driver.getCurrentUrl());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
