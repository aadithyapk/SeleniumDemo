package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;

@Listeners(TestListeners.class)

public class NewTest {
	
	private static WebDriver driver;
	
	HomePage objHomePage;
	BankManagerHomePage objBankManagerHomePage;
	
	@BeforeTest
	public void setupTests() {
		System.setProperty("webdriver.chrome.driver", ".//src/test/resources/executables/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider = "LoginCredentials")
	public void sampleTest(String strUserName, String strPassword) {
		
		objHomePage = new HomePage(driver);
		objHomePage.LaunchHomePage();
		objHomePage.LoginToHome(strUserName, strPassword);
		
		objBankManagerHomePage =  new BankManagerHomePage(driver);
		objBankManagerHomePage.assertBankManagerHomePageTitle();
	}
	
	@AfterTest
	public void teardownTests() {
		//driver.close();
		driver.quit();
	}

	@DataProvider(name="LoginCredentials")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            { "mngr299483", "suqUpej" }
        };
	}
	
}
