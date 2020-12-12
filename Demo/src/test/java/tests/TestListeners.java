package tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import global.Log;

public class TestListeners implements ITestListener {
	
	@Override		
    public void onFinish(ITestContext Result) {
		
	}
    		
    @Override		
    public void onStart(ITestContext Result){
    	
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
