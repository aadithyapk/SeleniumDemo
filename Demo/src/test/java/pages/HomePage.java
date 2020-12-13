package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.qameta.allure.Step;

public class HomePage {
	
	WebDriver driver;
	
	By txtUserName = By.name("uid");
	By txtPassword = By.name("password");
	By btnLogin = By.name("btnLogin");
	String strHomePageTitle =  "Guru99 Bank Home Page";
	
	public HomePage (WebDriver driver) {
		this.driver = driver;
	}
	
	private void setUserName(String strUserName) {
		driver.findElement(txtUserName).sendKeys(strUserName);
	}
	
	private void setPassword(String strPassword) {
		driver.findElement(txtPassword).sendKeys(strPassword);
	}
	
	private void clickLogin() {
		driver.findElement(btnLogin).click();
	}
	
	@Step("Launch Guru99 homepage and assert page title")
	public void LaunchHomePage() {
		driver.get("http://demo.guru99.com/v4/");
		Assert.assertEquals(driver.getTitle().trim(),strHomePageTitle);
	}
	
	@Step("Login to home page using username - {strUserName} and password - {strPassword}")
	public void LoginToHome(String strUserName, String strPassword) {
		this.setUserName(strUserName);
		this.setPassword(strPassword);
		this.clickLogin();
	}
}
