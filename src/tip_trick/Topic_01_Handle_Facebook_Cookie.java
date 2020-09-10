package tip_trick;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Handle_Facebook_Cookie {
	WebDriver driver;
	Set<Cookie> allCookies;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");

	}

	@Test
	public void TC_01_Get_Cookie() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("...");
		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("...");
		driver.findElement(By.xpath("//input[@data-testid='royal_login_button']")).click();

		sleepInSecond(5);

		allCookies = driver.manage().getCookies();
		System.out.println("Cookie name = " + allCookies);
	}

	@Test
	public void TC_02_Set_Cookie() {
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}

		allCookies = driver.manage().getCookies();
		System.out.println("Cookie name = " + allCookies);
		driver.navigate().refresh();
		sleepInSecond(5);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}