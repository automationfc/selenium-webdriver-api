package automationfc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_06_Run_On_Internet_Explorer {
	WebDriver driver;

	@Test
	public void TC_01_RunOnSafari() {
		driver = new SafariDriver();
		driver.get("http://automationfc.com");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Automation FC Blog");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
