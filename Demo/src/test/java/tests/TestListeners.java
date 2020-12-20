package tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Allure;
import utils.FrameworkUtils;
import utils.Log;
import utils.WebActions;

public class TestListeners implements ITestListener {
	
	WebDriver driver = null;
	WebActions webActions = null;
	@Override		
    public void onFinish(ITestContext Result) {
		Allure.allureServe();
	}
    		
    @Override		
    public void onStart(ITestContext Result){
    	FrameworkUtils.loadTestProperties();
    }	

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result){
    	
    }
    		
    @Override		
    public void onTestFailure(ITestResult Result){
    	ITestContext context = Result.getTestContext();
    	driver = (WebDriver) context.getAttribute("WebDriver");
    	webActions = new WebActions(driver);
    	webActions.takeScreenshot();
    	Log.endTestCase();
    }		

    @Override		
    public void onTestSkipped(ITestResult Result){
    	Log.endTestCase();
    }
    		
    @Override		
    public void onTestStart(ITestResult Result){
    	Log.startTestCase(Result.getName());
    }
   	
    @Override		
    public void onTestSuccess(ITestResult Result){
    	Log.endTestCase();
    }

}
