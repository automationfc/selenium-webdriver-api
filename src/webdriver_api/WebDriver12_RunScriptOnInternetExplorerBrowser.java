package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriver12_RunScriptOnInternetExplorerBrowser {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.ie.driver", "..\\SELENIUM_WEBDRIVER\\driver\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();

		driver.navigate().to("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		// Select using ID attribute
		Select select = new Select(driver.findElement(By.id("job1")));

		// Verify Dropdown doesn't support multi-select
		Assert.assertFalse(select.isMultiple());

		// Verify Dropdown has five options
		Assert.assertEquals(5, select.getOptions().size());

		// Select an option in Dropdown using visible text
		select.selectByVisibleText("Manual Tester");

		// Verify first option equal Manual Tester
		Assert.assertEquals("Manual Tester", select.getFirstSelectedOption().getText());
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}
}