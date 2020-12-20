package tests;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
//import io.github.bonigarcia.wdm.WebDriverManager;
import pages.*;
import utils.ExcelUtils;
import utils.FrameworkUtils;
import utils.Log;

@Listeners(TestListeners.class)

public class NewTest {
	
	private static WebDriver driver;
	private static String strTestCaseName;
	
	HomePage objHomePage;
	BankManagerHomePage objBankManagerHomePage;
	
	@BeforeTest
	public void setupTests() {
		System.setProperty("webdriver.chrome.driver", FrameworkUtils.getTestProperty("chromeDriver.path"));
		//WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		Log.logInfo("Starting web driver");
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void getMethodName(Method method) {
		strTestCaseName = method.getName();
	}
	
	@Test(dataProvider = "LoginCredentials")
	@Description("Sample Login test to Guru99 site")
	@Severity(SeverityLevel.BLOCKER)
	public void TC001_sampleTest(String strUserName, String strPassword) {
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
		Log.logInfo("Quitting webdriver");
	}

	@DataProvider(name="LoginCredentials")
    public Object[][] loginDataProvider() {
		Object[][] testObjArray;
		testObjArray = new Object[][] {	{ "mngr299483", "suqUpej" } };
		/*String strFilePath, strSheetName;
		int intTestCaseRow;
		strFilePath = FrameworkUtils.getTestProperty("testdata.path");
		strSheetName = "Login";
		ExcelUtils.setExcelFile(strFilePath, strSheetName);
		intTestCaseRow = ExcelUtils.getRowContains(strTestCaseName,0);
		testObjArray = ExcelUtils.getTableArray(strFilePath,strSheetName,intTestCaseRow);*/
	    return testObjArray;
	}	
}
