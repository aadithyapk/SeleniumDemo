package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.qameta.allure.Step;
import utils.WebElementActions;

public class HomePage {
	
	WebDriver driver;
	WebElementActions  webElementAction;
	
	By txtUserName = By.name("uid");
	By txtPassword = By.name("password");
	By btnLogin = By.name("btnLogin");
	String strHomePageTitle =  "Guru99 Bank Home Page";
	
	public HomePage (WebDriver driver) {
		this.driver = driver;
		webElementAction = new WebElementActions(driver);
	}
	
	@Step("Enter username - {strUserName}")
	private void setUserName(String strUserName) {
		webElementAction.enterText(txtUserName, strUserName);
	}
	
	@Step("Enter password - {strPassword}")
	private void setPassword(String strPassword) {
		webElementAction.enterText(txtPassword, strPassword);
	}
	
	@Step("Click Login button")
	private void clickLogin() {
		webElementAction.clickElement(btnLogin);
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
