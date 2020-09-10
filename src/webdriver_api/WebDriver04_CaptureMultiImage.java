package webdriver_api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

public class WebDriver04_CaptureMultiImage {
	public String fileName;
	public WebDriver driver;

	@Test
	public void runTestcase() throws Exception {
		FirefoxProfile profile = new FirefoxProfile();
		File extension = new File("..\\SELENIUM_WEBDRIVER\\extensions\\EuroBonus.xpi");
		profile.addExtension(extension);
		driver = new FirefoxDriver((Capabilities) profile);

		ArrayList<String> arrDomainList = new ArrayList<String>();
		arrDomainList.add("https://daominhdam.wordpress.com");
		arrDomainList.add("https://vntesters.com");

		try {
			for (int i = 0; i < arrDomainList.size(); i++) {
				driver.get(arrDomainList.get(i));
				System.out.println(arrDomainList.get(i));
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				fileName = driver.getCurrentUrl();
				fileName = fileName.replace("https", "");
				fileName = fileName.replace("http", "");
				fileName = fileName.replace("<", "");
				fileName = fileName.replace(";", "");
				fileName = fileName.replace(":", "");
				fileName = fileName.replace("'", "");
				fileName = fileName.replace("{", "");
				fileName = fileName.replace("}", "");
				fileName = fileName.replace("[", "");
				fileName = fileName.replace("]", "");
				fileName = fileName.replace("|", "");
				WebDriver04_CaptureMultiImage.takeSnapShot(driver, "C:\\ReportFirefox\\" + fileName + ".png");
			}
		} catch (Exception e) {
			driver.quit();
			driver = new FirefoxDriver((Capabilities) profile);
		}
		driver.close();
	}

	public static void takeSnapShot(WebDriver webdriver, String fileName) {
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		File sourceFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}