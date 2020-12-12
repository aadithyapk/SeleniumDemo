package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BankManagerHomePage {
	
WebDriver driver;
	
	String strBankManagerHomePageTitle =  "Guru99 Bank Manager HomePage";
	
	public BankManagerHomePage (WebDriver driver) {
		this.driver = driver;
	}
	
	public void assertBankManagerHomePageTitle() {
		Assert.assertEquals(driver.getTitle().trim(),strBankManagerHomePageTitle);
	}

}
