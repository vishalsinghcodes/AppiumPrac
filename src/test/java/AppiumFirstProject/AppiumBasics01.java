package AppiumFirstProject;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;


public class AppiumBasics01 {

	@Test
	public void AppiumTest() throws MalformedURLException, URISyntaxException {
		
		// To start the Appium server
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\Vishal Singh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1")
				.usingPort(4723)
				.build();
		
		// To start the aapium server
		service.start();
		
		
		// automation Code
		UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("VishalPhone");
        options.setApp("C:\\Users\\Vishal Singh\\eclipse-workspace\\AppiumFirstProject\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		
		// closing the Application
		//driver.quit();
		
		// Stopping the server
		//service.stop();
		

	}

}
