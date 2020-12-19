package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.qameta.allure.Step;

public class BankManagerHomePage {
	
WebDriver driver;
	
	String strBankManagerHomePageTitle =  "Guru99 Bank Manager HomePage";
	
	public BankManagerHomePage (WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Assert Bank Manager Home page title")
	public void assertBankManagerHomePageTitle() {
		Assert.assertEquals(driver.getTitle().trim(),strBankManagerHomePageTitle);
	}

}
