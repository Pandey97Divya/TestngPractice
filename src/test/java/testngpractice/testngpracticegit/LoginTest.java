package testngpractice.testngpracticegit;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
	public WebDriver driver;
	@DataProvider(name= "loginTestData")
	public Object[][] loginTestData(){
		return new Object[][] {
//			POSITIVE TESTCASE
			{"standard_user", "secret_sauce", true},
			{"standard_user", "abcd", false},
			{"", "", false},
			{"abcd", "secret_sauce", false},
			{"", "secret_sauce", false},
			{"standard_user", "", false},
			{"standard", "abcd", false}
		};
	}
	@BeforeMethod
	public void browserSetup() {
		ChromeOptions co= new ChromeOptions();
		co.addArguments("--remote-allow-origin=*");
		driver= new ChromeDriver(co);
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	}
	@Test(dataProvider= "loginTestData")
	public void loginTest(String username, String password, Boolean result) throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder=\"Username\"]")).sendKeys(username);
		driver.findElement(By.xpath("//input[@placeholder=\"Password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id=\"login-button\"]")).click();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		}
	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		driver.quit();
	}
}
