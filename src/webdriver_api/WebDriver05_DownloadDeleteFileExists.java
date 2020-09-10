package webdriver_api;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriver05_DownloadDeleteFileExists {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","..\\SELENIUM_WEBDRIVER\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void test01_DownloadAndDeleteFileFullName() throws Exception {
		String file = "smilechart.xls";
		driver.navigate().to("http://spreadsheetpage.com/index.php/file/C35/P10/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		deleteAllFileInFolder();

		driver.findElement(By.xpath("//a[contains(text(),'smilechart.xls')]")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		waitForDownloadFileFullnameCompleted(file);

		int countFileBeforeDelete = countFilesInDirectory();
		System.out.println("SAU KHI TAI VE: " + countFileBeforeDelete);

		Assert.assertEquals(countFileBeforeDelete, 1);

		deleteFileFullName(file);

		int countFileAfterDelete = countFilesInDirectory();
		System.out.println("SAU KHI XOA: " + countFileAfterDelete);

		Assert.assertEquals(countFileAfterDelete, 0);
	}

	@Test
	public void test02_DownloadAndDeleteFileContainName() throws Exception {
		String file = ".xls";
		driver.navigate().to("http://spreadsheetpage.com/index.php/file/C35/P10/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		deleteAllFileInFolder();

		driver.findElement(By.xpath("//a[contains(text(),'smilechart.xls')]")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		waitForDownloadFileContainsNameCompleted(file);

		int countFileBeforeDelete = countFilesInDirectory();
		System.out.println("SAU KHI TAI VE: " + countFileBeforeDelete);

		Assert.assertEquals(countFileBeforeDelete, 1);
		deleteFileContainName(file);

		int countFileAfterDelete = countFilesInDirectory();
		System.out.println("SAU KHI XOA: " + countFileAfterDelete);

		Assert.assertEquals(countFileAfterDelete, 0);
	}

	public void deleteAllFileInFolder() {
		try {
			String pathFolderDownload = getPathContainDownload();
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public String getPathContainDownload() {
		String path = "";
		String machine_name;
		machine_name = System.getProperty("user.home");
		path = String.format("%s\\Downloads\\", machine_name);
		return path;
	}

	public void waitForDownloadFileFullnameCompleted(String fileName)
			throws Exception {
		int i = 0;
		while (i < 30) {
			boolean exist = isFileExists(fileName);
			if (exist == true) {
				i = 30;
			}
			Thread.sleep(500);
			i = i + 1;
		}
	}

	public boolean isFileExists(String file) {
		try {
			String pathFolderDownload = getPathContainDownload();
			File files = new File(pathFolderDownload + file);
			boolean exists = files.exists();
			return exists;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	public int countFilesInDirectory() {
		String pathFolderDownload = getPathContainDownload();
		File file = new File(pathFolderDownload);
		int i = 0;
		for (File listOfFiles : file.listFiles()) {
			if (listOfFiles.isFile()) {
				i++;
			}
		}
		return i;
	}

	public void deleteFileFullName(String fileName) {
		if (isFileExists(fileName)) {
			deleteFullName(fileName);
		}
	}

	public void deleteFullName(String fileName) {
		try {
			if (isFileExists(fileName)) {
				String pathFolderDownload = getPathContainDownload();
				File files = new File(pathFolderDownload + fileName);
				files.delete();
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public void waitForDownloadFileContainsNameCompleted(String fileName)
			throws Exception {
		int i = 0;
		while (i < 30) {
			boolean exist = isFileContain(fileName);
			if (exist == true) {
				i = 30;
			}
			Thread.sleep(500);
			i = i + 1;
		}
	}

	public boolean isFileContain(String fileName) {
		try {
			boolean flag = false;
			String pathFolderDownload = getPathContainDownload();
			File dir = new File(pathFolderDownload);
			File[] files = dir.listFiles();
			if (files == null || files.length == 0) {
				flag = false;
			}
			for (int i = 1; i < files.length; i++) {
				if (files[i].getName().contains(fileName)) {
					flag = true;
				}
			}
			return flag;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return false;
		}
	}

	public void deleteFileContainName(String fileName) {
		deleteContainName(fileName);
	}

	public void deleteContainName(String fileName) {
		try {
			String files;
			String pathFolderDownload = getPathContainDownload();
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					if (files.contains(fileName)) {
						new File(listOfFiles[i].toString()).delete();
					}
				}
			}
		} catch (Error e){
			System.out.print(e.getMessage());
		}
		}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}