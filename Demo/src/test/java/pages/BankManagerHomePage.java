package pages;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import utils.WebActions;

public class BankManagerHomePage {
	
WebDriver driver;
	
	String strBankManagerHomePageTitle =  "Guru99 Bank Manager HomePage1";
	WebActions webActions;
	public BankManagerHomePage (WebDriver driver) {
		this.driver = driver;
		webActions = new WebActions(driver);
	}
	
	@Step("Assert Bank Manager Home page title")
	public void assertBankManagerHomePageTitle() {
		webActions.assertPageTitle(strBankManagerHomePageTitle);
	}

}
