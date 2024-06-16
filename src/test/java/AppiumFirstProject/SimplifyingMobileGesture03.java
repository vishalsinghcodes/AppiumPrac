package AppiumFirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class SimplifyingMobileGesture03 extends BaseTest{

	
	@Test
	public void testLongPressGesture() throws InterruptedException {

		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
		WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		longPressGesture(ele, 5000); // I have created a method in Base class for same
		String menutext = driver.findElement(By.id("android:id/title")).getText();
		Assert.assertEquals(menutext, "Sample menu"); // If you want to check the text
		System.out.println("Menu text is correct");

		Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed()); // To check if the Sample Test
																						// menu is displayed
		System.out.println("Samople Menu is displayed");
		Thread.sleep(5000);

	}
	
	
	
	
}
