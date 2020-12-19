package tests;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Allure;
import utils.FrameworkUtils;
import utils.Log;

public class TestListeners implements ITestListener {
	
	@Override		
    public void onFinish(ITestContext Result) {
		try {
			Allure.allureServe();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
