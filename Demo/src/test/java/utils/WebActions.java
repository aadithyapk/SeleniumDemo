package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;


public class WebActions {
	
	WebDriver driver;
	final static int intTimeoutSeconds = 15;
	final static int intPollingMilli = 200;
	final static boolean boolScreenshotOneveryAction = Boolean.parseBoolean(FrameworkUtils.getTestProperty("screenshotOnEveryAction.flag"));
	
	public WebActions (WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Enter text '{strText}' on element identified '{by}'")
	public void enterText(By by, String strText) {
		if (waitForElementVisibility(by)) {
			try {
				driver.findElement(by).clear();
				driver.findElement(by).sendKeys(strText);
				Log.logInfo("String - "+strText+" was sent to element identified "+by.toString());
			} catch (Exception e) {
				Log.logException("sending String - "+strText+" to element identified "+by.toString(), e);
			}
			finally {
				if (boolScreenshotOneveryAction) {
					takeScreenshot();
				}
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
				} catch (Exception e) {
					Log.logException("clicking element identified "+by.toString(), e);
				}
				finally {
					if (boolScreenshotOneveryAction) {
						takeScreenshot();
					}
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
		} catch(Exception e) {
			flag = false;
			Log.logException("waiting for element located by - "+by.toString()+" to be visible", e);
		}
		finally {
			if (boolScreenshotOneveryAction) {
				takeScreenshot();
			}
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
		} catch(Exception e) {
			flag = false;
			Log.logException("waiting for element located by - "+by.toString()+" to be clickable", e);
		}
		finally {
			if (boolScreenshotOneveryAction) {
				takeScreenshot();
			}
		}
		return flag;
	}
	
	@Step("Asserting page title to expected title '{strExpectedTitle}'")
	public void assertPageTitle(String strExpectedTitle) {
		String strActualTitle = driver.getTitle().trim();
		strExpectedTitle = strExpectedTitle.trim();
		boolean boolCompareResults = strActualTitle.equals(strExpectedTitle);
		if (boolCompareResults) {
			Log.logInfo("Actual title '"+strActualTitle+"' matches with expected title '"+strExpectedTitle+"'");
		}
		else {
			Log.logError("Actual title '"+strActualTitle+"' does not match with expected title '"+strExpectedTitle+"'");
		}
		if (boolScreenshotOneveryAction) {
			takeScreenshot();
		}
	}
	
	@Step("Lanching URL '{strURL}'")
	public void launchURL(String strURL) {
		driver.get(strURL);
		Log.logInfo("Launching URL '"+strURL+"'");
		if (boolScreenshotOneveryAction) {
			takeScreenshot();
		}
	}
	
	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] takeScreenshot() {
	    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
}