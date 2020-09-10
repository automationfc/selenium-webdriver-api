package webdriver_api;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebDriver23_RefreshAPage {

	WebDriver driver;

	@BeforeTest
	public void start() {
		driver = new FirefoxDriver();
	}

	@Test
	public void RefreshAPage() {
	    driver.get("https://accounts.google.com/SignUp");
	    driver.findElement(By.id("firstname-placeholder")).sendKeys(Keys.F5);
	    
	    driver.get("https://accounts.google.com/SignUp");
	    driver.navigate().refresh();
	    
	    driver.get("https://accounts.google.com/SignUp");
	    driver.navigate().to(driver.getCurrentUrl());
	    
	    driver.get("https://accounts.google.com/SignUp");
	    driver.get(driver.getCurrentUrl());
	    
	    driver.get("https://accounts.google.com/SignUp");
	    driver.findElement(By.id("firstname-placeholder")).sendKeys("\uE035");
	    
	    driver.quit();
	}

}