package AppiumFirstProject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class MobileBrowserBaseTest {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppiumTest() throws MalformedURLException, URISyntaxException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Vishal Singh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();

		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("VishalPhone");

		// options.setApp("C:\\Users\\Vishal
		// Singh\\eclipse-workspace\\AppiumFirstProject\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		// // here we are not automating the app so I have commented this out

		// Now to set the chrome browser we will use set capability.

		options.setCapability(CapabilityType.BROWSER_NAME, "Chrome");

		options.setChromedriverExecutable(
				"C:\\Users\\Vishal Singh\\eclipse-workspace\\AppiumFirstProject\\src\\test\\java\\resources\\chromedriver.exe");
		// here we have given the chrome driver path which we have downloaded to be
		// used, this is same as setting the path in selenium.

		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterClass
	public void tearDown() {

		driver.quit();
		service.stop();

	}

	/*
	 * { "app":
	 * "C:\\\\Users\\\\Vishal Singh\\\\eclipse-workspace\\\\AppiumFirstProject\\\\src\\\\test\\\\java\\\\resources\\\\ApiDemos-debug.apk"
	 * , "automationName": "UIAutomator2", "deviceName": "VishalPhone",
	 * "platformName": "android" }
	 */

}
