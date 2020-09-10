package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriver06_RunOnMicrosoftEdge {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.edge.driver", "..\\SELENIUM_WEBDRIVER\\driver\\MicrosoftWebDriver.exe");
		driver = new EdgeDriver();
	}

	@Test
	public void Test01_LoginWithEmptyEmailPassword() throws Exception {
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Click Login button
		driver.findElement(By.id("send2")).click();
		Thread.sleep(2000);

		// Get error message at 'Email Address' field
		String errorEmail = driver.findElement(By.id("advice-required-entry-email")).getText();

		// Verify error message display correctly
		Assert.assertEquals(errorEmail, "This is a required field.");

		// Get error message at 'Password' field
		String errorPassword = driver.findElement(By.id("advice-required-entry-pass")).getText();

		// Verify error message display correctly
		Assert.assertEquals(errorPassword, "This is a required field.");
	}

	@Test
	public void Test02_LoginWithInvalidEmail() throws Exception {
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Input correct Username
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");

		// Click Login button
		driver.findElement(By.id("send2")).click();

		// Get error message is displayed
		String errorMessage = driver.findElement(By.id("advice-validate-email-email")).getText();

		// Verify error message display correctly
		Assert.assertEquals(errorMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Test03_LoginWithIncorectPassword() throws Exception {
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Input correct Username
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");

		// Input correct Password
		driver.findElement(By.id("pass")).sendKeys("123");

		// Click Login button
		driver.findElement(By.id("send2")).click();

		// Get error message at 'Password' field
		String errorPassword = driver.findElement(By.id("advice-validate-password-pass")).getText();

		// Verify message display correctly
		Assert.assertEquals(errorPassword, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Test04_LoginWithCorrectEmailPassword() throws Exception {
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Input correct Username
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");

		// Input correct Password
		driver.findElement(By.id("pass")).sendKeys("123123");

		// Click Login button
		driver.findElement(By.id("send2")).click();

		// Verify that 'Search' textbox display correctly
		driver.findElement(By.id("search")).isDisplayed();
	}

	@Test
	public void Test05_SearchAnItemSuccessfully() throws Exception {
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Input data and click Search
		driver.findElement(By.id("search")).sendKeys("SAMSUNG GALAXY");
		driver.findElement(By.id("search")).sendKeys(Keys.ENTER);

		// Verify that 'Samsung Galaxy' item display correctly
		driver.findElement(By.id("product-collection-image-3")).isDisplayed();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}