package automation.testing.selenium.parallel.tests;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases {
	
	public WebDriver driver;
	ThreadLocal<WebDriver> local;	
	
	@Test
	public void launchFacebook() {	
		local.get().get("https://www.facebook.com/");
		local.get().manage().window().maximize();
		local.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		System.out.println(local.get().getTitle());		
	}
	
	@Test
	public void launchTwitter() throws Exception {
		local.get().get("https://twitter.com/?lang=en");
		local.get().manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(local.get(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions.textToBe(By.cssSelector("*.r-19oahor span"), "Happening now"));
		System.out.println(local.get().getTitle());
	}
	
	@BeforeMethod(alwaysRun=true)
	public void initiateBrowser() {
		local = new ThreadLocal<WebDriver>();
		driver = new ChromeDriver();
		local.set(driver);
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		local.get().quit();		
	}
}
