package eCommerceTestCases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTestEC {

	AndroidDriver driver;
	AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppium() throws MalformedURLException, URISyntaxException {

		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\Vishal Singh\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();

		service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("VishalPhone");
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\General-Store.apk");

		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	
	public void scrollToEndOfPage() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 1.0));
		} while (canScrollMore);
	}
	
	public float getFormattedAmount(String amountString) {
		return Float.parseFloat(amountString.split("\\$")[1]);	
	}
	
	
	public void longPressGesture(WebElement ele, int duration) {  // time to be given in milliseconds

		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", duration));

	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
	

}
