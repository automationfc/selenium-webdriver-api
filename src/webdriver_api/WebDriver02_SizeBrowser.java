package webdriver_api;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class WebDriver02_SizeBrowser {

	@Test
	public void openBrowserwithGivenDimension1() {
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("https://daominhdam.wordpress.com");
		Dimension size = new Dimension(320, 480);
		driver.manage().window().setSize(size);
	}

	@Test
	public void openBrowserwithGivenDimension2() {
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("https://daominhdam.wordpress.com");
		Dimension size = new Dimension(640, 960);
		driver.manage().window().setSize(size);
	}

	@Test
	public void openBrowserwithGivenDimension3() {
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("https://daominhdam.wordpress.com");
		driver.manage().window().maximize();
	}
}