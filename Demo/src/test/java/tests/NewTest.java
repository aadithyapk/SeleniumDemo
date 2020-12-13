package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
//import io.github.bonigarcia.wdm.WebDriverManager;
import pages.*;

@Listeners(TestListeners.class)

public class NewTest {
	
	private static WebDriver driver;
	
	HomePage objHomePage;
	BankManagerHomePage objBankManagerHomePage;
	
	@BeforeTest
	public void setupTests() {
		System.setProperty("webdriver.chrome.driver", ".//src/test/resources/executables/chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider = "LoginCredentials")
	@Description("Sample Login test to Guru99 site")
	@Severity(SeverityLevel.BLOCKER)
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
            { "mngr299483", "suqUpej" },
            { "mngr299483", "wrongPassword" }
        };
	}
	
}
