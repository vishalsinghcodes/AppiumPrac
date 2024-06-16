package AppiumFirstProject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.File;

public class PracticeRough {
	
	
	
	@Test
	public void RoughTest() throws MalformedURLException, URISyntaxException {
	
		
		
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Vishal Singh\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\main.js"))
				.usingPort(4723)
				.withIPAddress("127.0.0.1").build();
		
		service.start();
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("VishalPhone");
		options.setApp("C:\\Users\\Vishal Singh\\eclipse-workspace\\AppiumFirstProject\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		
		AndroidDriver driver = new AndroidDriver( new URI("http://127.0.0.1:4723").toURL(),options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Preference\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"4. Default values\"]")).click();
		
		
        
        
		
	}
	
	
	

}
