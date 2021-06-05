package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestRunner;

import utils.Allure;
import utils.FrameworkUtils;
import utils.Log;
import utils.WebActions;

public class TestListeners implements ITestListener {
	
	WebDriver driver = null;
	WebActions webActions = null;
	@Override		
    public void onFinish(ITestContext context) {
		//Allure.allureServe();
	}
    		
    @Override		
    public void onStart(ITestContext context){
    	FrameworkUtils.loadTestProperties();
    }	

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult result){
    	
    }
    		
    @Override		
    public void onTestFailure(ITestResult result){
    	ITestContext context = result.getTestContext();
    	driver = (WebDriver) context.getAttribute("WebDriver");
    	webActions = new WebActions(driver);
    	webActions.takeScreenshot();
    	Log.endTestCase();
    }		

    @Override		
    public void onTestSkipped(ITestResult result){
    	Log.endTestCase();
    }
    		
    @Override		
    public void onTestStart(ITestResult result){
    	Log.startTestCase(result.getName());
    }
   	
    @Override		
    public void onTestSuccess(ITestResult result){
    	Log.endTestCase();
    }

}
