package appiumtests;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import java.io.IOException;
import java.net.URL;

public class AppiumAndroidTests {
	
	private static AppiumDriverLocalService service;
	private static AndroidDriver<WebElement> driver;
	
	@BeforeSuite
    public void globalSetup () throws IOException {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @AfterSuite
    public void globalTearDown () {
        if (service != null) {
            service.stop();
        }
    }

    public URL getServiceUrl () {
        return service.getUrl();
    }
    
	@BeforeTest
	public void setupTests() {
		
        DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		//capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		//capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.customermobile.preload.vzw");
		//capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "");
		driver = new AndroidDriver<WebElement>(getServiceUrl(), capabilities);
	}
	
	@AfterTest
    public void tearDown() {
        //driver.quit();
    }
	
	
	@Test
	public void sampleTest() {
		driver.installApp("C:\\Users\\aadit\\git\\SeleniumDemo\\Demo\\src\\test\\resources\\executables\\apks\\com.customermobile.preload.vzw_Retail_Demo.apk");
	}

}
