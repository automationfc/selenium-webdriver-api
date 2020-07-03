package automationfc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_02_Run_On_Chrome_Firefox_UI {
	WebDriver driver;

	@Test
	public void TC_01_RunOnChrome() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://automationfc.com");
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Automation FC Blog");
	}

	@Test
	public void TC_02_RunOnFirefox() {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.get("http://automationfc.com");
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Automation FC Blog");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
