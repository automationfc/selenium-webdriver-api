package webdriver_api;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriver07_DragAndDropFile {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
	}

	@Test (enabled=true)
	public void dragAndDropAnElement01() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://jqueryui.com/droppable/");
		driver.manage().window().maximize();

		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);

		WebElement From = driver.findElement(By.id("draggable"));
		WebElement To = driver.findElement(By.id("droppable"));

		dragAndDrop(From, To);
		Thread.sleep(4000);

		String actualText = driver.findElement(By.xpath("//*[@id='droppable']/p")).getText();
		System.out.println(actualText);
		Assert.assertEquals(actualText, "Dropped!");
	}

	@Test (enabled=true)
	public void dragAndDropAnElement02() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		driver.manage().window().maximize();
		Thread.sleep(3000);

		WebElement From = driver.findElement(By.id("draggable"));
		WebElement To = driver.findElement(By.id("droptarget"));
		
		dragAndDrop(From, To);
		Thread.sleep(3000);

		String actualText = driver.findElement(By.id("droptarget")).getText();
		System.out.println(actualText);
		Assert.assertEquals(actualText, "You did great!");
	}
	
	@Test (enabled=true)
	public void dragAndDropAnElement03() throws Exception {
		driver.get("http://dhtmlx.com/docs/products/dhtmlxTree/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(5000);

		WebElement elementToMove = driver.findElement(By.xpath("//div[@id='treebox2']//span[text()='Ian Rankin']"));
		Actions builder = new Actions(driver);
		Action drag = builder.clickAndHold(elementToMove).build();
		drag.perform();

		WebElement moveToElement = driver.findElement(By.xpath("//div[@id='treebox2']//span[text()='James Johns']"));
		Actions builder2 = new Actions(driver);
		Action dragAndDrop = builder2.moveToElement(moveToElement).release(moveToElement).build();
		dragAndDrop.perform();
		Thread.sleep(5000);
	}
	
	@Test (enabled=true)
	public void dragAndDropAnElement04() throws AWTException, Exception {
	driver.get("http://demo.kaazing.com/forex");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	Thread.sleep(5000);
	
	WebElement sourceElement = driver.findElement(By.xpath("//img[@title='GBPEUR']"));
	Actions builder = new Actions(driver);
	Action drag = builder.clickAndHold(sourceElement).build();
	drag.perform();
	    
	WebElement targetElement = driver.findElement(By.xpath("//div[@name='0']"));
	Point coordinates = targetElement.getLocation();
	System.out.println(coordinates);
	
	Robot robot = new Robot(); 
	robot.mouseMove(coordinates.getX() + 100,coordinates.getY()+120);
	Thread.sleep(2000);
	
	WebElement moveSuccess = driver.findElement(By.id("GBPEUR"));
	if(moveSuccess.isDisplayed()){
		System.out.println("Drag and drop successfully");
	}
	}
	
	public void dragAndDrop(WebElement sourceElement,	WebElement destinationElement) {
		try {
			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
				Actions action = new Actions(driver);
				action.dragAndDrop(sourceElement, destinationElement).build().perform();
			} else {
				System.out.println("Element was not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + sourceElement + "or"+ destinationElement	+ "is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + sourceElement + "or"+ destinationElement + " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Error occurred while performing drag and drop operation "+ e.getStackTrace());
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}