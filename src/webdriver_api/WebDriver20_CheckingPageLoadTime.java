package webdriver_api;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebDriver20_CheckingPageLoadTime {

	WebDriver driver;

	@BeforeTest
	public void start() {
		driver = new FirefoxDriver();
	}

	@Test
	public void LoadTime() {

		long startTime = System.currentTimeMillis() / 1000;
		System.out.println("The startTime is " + startTime);
		driver.get("https://daominhdam.wordpress.com/");
		// Set the acceptable Page load time to 60 sec
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		WebElement search = driver.findElement(By.className("search-toggle"));
		// Iterate through the loop as long as time(60sec) is with in the acceptable
		// Page load time
		while (((System.currentTimeMillis() / 1000) - startTime) < 60) {
			if (search.isDisplayed()) {
				long endTime = System.currentTimeMillis() / 1000;
				System.out.println("The endTime is " + endTime);
				long loadTime = endTime - startTime;
				System.out.println("Totaltime: " + loadTime + " seconds");
				break;
			}
		}
	}

}