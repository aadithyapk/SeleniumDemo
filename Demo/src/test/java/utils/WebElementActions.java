package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.qameta.allure.Step;


public class WebElementActions {
	
	WebDriver driver;
	final static int intTimeoutSeconds = 15;
	final static int intPollingMilli = 200;
	
	public WebElementActions (WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Enter text '{strText}' on element identified '{by}'")
	public void enterText(By by, String strText) {
		if (waitForElementVisibility(by)) {
			try {
				driver.findElement(by).clear();
				driver.findElement(by).sendKeys(strText);
				Log.logInfo("String - "+strText+" was sent to element identified "+by.toString());
			}catch (Exception e) {
				Log.logError("Error in sending String - "+strText+" to element identified "+by.toString());
				Log.logError(e.getMessage());
			}
		}	
	}
	
	@Step("Click element identified '{by}'")
	public void clickElement(By by) {
		if (waitForElementVisibility(by)) {
			if (waitForElementClickable(by)) {
				try {
					driver.findElement(by).click();
					Log.logInfo("Element identified "+by.toString()+" was clicked");
				}catch (Exception e) {
					Log.logError("Error in click element identified "+by.toString());
					Log.logError(e.getMessage());
				}				
			}
		}		
	}
	
	@Step("Wait for element identified '{by}' to be visible")
	public boolean waitForElementVisibility(By by) {
		boolean flag = true;
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
		        .withTimeout(Duration.ofSeconds(intTimeoutSeconds))
		        .pollingEvery(Duration.ofMillis(intPollingMilli))
		        .ignoring(NoSuchElementException.class);
		try {
			fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}catch(Exception e) {
			flag = false;
			Log.logError("Element located "+by.toString()+" is not visible after waiting.");
			Log.logError(e.getMessage());
		}
		return flag;
	}
	
	@Step("Wait for element identified '{by}' to be clickable")
	public boolean waitForElementClickable(By by) {
		boolean flag = true;
		FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
		        .withTimeout(Duration.ofSeconds(intTimeoutSeconds))
		        .pollingEvery(Duration.ofMillis(intPollingMilli))
		        .ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.elementToBeClickable(by));
		try {
			fluentWait.until(ExpectedConditions.elementToBeClickable(by));
		}catch(Exception e) {
			flag = false;
			Log.logError("Element located by - "+by.toString()+" is not clickable after waiting.");
			Log.logError(e.getMessage());
		}
		return flag;
	}
	
}