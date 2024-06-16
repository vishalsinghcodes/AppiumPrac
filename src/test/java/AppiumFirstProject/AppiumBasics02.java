package AppiumFirstProject;

import org.openqa.selenium.By;
import org.testng.Assert;
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

public class AppiumBasics02 extends BaseTest {

	@Test
	public void WifiSettingsName() throws MalformedURLException, URISyntaxException {
		// All the code of building, starting the server and to set the AndroidUIAutomatorproperties are moved to Base Class to make the code look clean in test

		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		driver.findElement(By.id("android:id/edit")).sendKeys("Vishal Wifi");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click(); // appium guys suggested to use
																							// AppiumBy.className
																							// instead of simple
																							// By.ClassName as it is
																							// unstable

	}

	@Test
	public void CheckLabelOfPopup() {
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		System.out.println("Alert Title name is - "+ alertTitle);
		Assert.assertEquals(alertTitle, "WiFi settings");

	}

}
