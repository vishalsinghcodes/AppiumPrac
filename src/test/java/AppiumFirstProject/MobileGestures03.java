package AppiumFirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class MobileGestures03 extends BaseTest {

	@Test
	public void testLongPressGesture() throws InterruptedException {

		// You can refer to official Appium Gesture Notes
		//https://github.com/appium/appium-uiautomator2-driver/blob/master/docs/android-mobile-gestures.md
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		
		//Time is given in milliseconds
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000)); // in milliseconds

		String menutext = driver.findElement(By.id("android:id/title")).getText();
		Assert.assertEquals(menutext, "Sample menu"); // If you want to check the text
		System.out.println("Menu text is correct");

		Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed()); // To check if the Sample Test
																						// menu is displayed
		System.out.println("Samople Menu is displayed");
		Thread.sleep(5000);

	}

}
