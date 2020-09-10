package webdriver_api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriver21_HandleAjax {
	
	private String URL = "http://demos.telerik.com/aspnet-ajax/ajax/examples/loadingpanel/explicitshowhide/defaultcs.aspx";
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp() {
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(URL);
	}
	
	@Test
	public void demoAjax() {

		/*Wait for grid to appear*/
		By container = By.cssSelector(".demo-container");
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(container));
		
		/*Get the text before performing an ajax call*/
		WebElement noDatesTextElement = driver.findElement(By.xpath("//div[@class='RadAjaxPanel']/span"));
		String textBeforeAjaxCall = noDatesTextElement.getText().trim();
		
		/*Click on the date*/
		driver.findElement(By.linkText("1")).click();
	
		/*Wait for loader to disappear */
		By loader = By.className("raDiv");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		
		/*Get the text after ajax call*/
		WebElement selectedDatesTextElement = driver.findElement(By.xpath("//div[@class='RadAjaxPanel']/span"));
		wait.until(ExpectedConditions.visibilityOf(selectedDatesTextElement));
		String textAfterAjaxCall = selectedDatesTextElement.getText().trim();
		
		/*Verify both texts before ajax call and after ajax call text.*/
		Assert.assertNotEquals(textBeforeAjaxCall, textAfterAjaxCall);
		
		String expectedTextAfterAjaxCall = "Sunday, November 01, 2015";
		
		/*Verify expected text with text updated after ajax call*/
		Assert.assertEquals(textAfterAjaxCall, expectedTextAfterAjaxCall);
		System.out.println(textAfterAjaxCall);
		System.out.println(textAfterAjaxCall);
		System.out.println(expectedTextAfterAjaxCall);
	}

}
