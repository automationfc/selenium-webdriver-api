package webdriver_api;

import java.io.File;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

public class WebDriver03_AddExtensions {

	@Test (priority = 1)
	public void addExtensionToFirefox() throws Exception {
		FirefoxProfile profile = new FirefoxProfile();
		File extension = new File("..\\SELENIUM_WEBDRIVER\\extensions\\firebug-2.0.1.xpi");
		profile.addExtension(extension);
		WebDriver driver = new FirefoxDriver((Capabilities) profile);
		driver.navigate().to("http://google.com.vn");
		driver.close();
	}

	@Test (priority = 2)
	public void addExtensionToChrome(){
		System.setProperty("webdriver.chrome.driver","..\\SELENIUM_WEBDRIVER\\driver\\chromedriver.exe");
		File file = new File("..\\SELENIUM_WEBDRIVER\\extensions\\Google-Translate_v2.0.6.crx");
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(file);
		WebDriver driver = new ChromeDriver(options);
		driver.navigate().to("http://google.com.vn");
		driver.close();
	}

}