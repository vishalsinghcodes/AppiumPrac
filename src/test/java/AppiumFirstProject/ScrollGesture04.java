package AppiumFirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.functions.ExpectedCondition;

public class ScrollGesture04 extends BaseTest {

	// There are two ways of doing the scroll Gesture

	// 1. By using the java Script executor
	// 2. By using UiAutomator method which Google originally have created

	@Test
	public void ScrollGestureTest() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		// 1. Making use of Java script executor - Make use of this method when you want
		// to scroll as per the points or co-ordinates.
		// and if you wanna scroll based on element use that Google one.
		// WebElement element =
		// driver.findElement(By.xpath("//android.widget.TextView[@content-desc='WebView']"));

		// To Scroll once

//		boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
//			    "left", 100, "top", 100, "width", 200, "height", 200,
//			    "direction", "down",
//			    "percent", 1.0
//			));

		// To Scroll till the end of the page - Use Do while loop

//		boolean canScrollMore;
//		do {
//		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
//				    "left", 100, "top", 100, "width", 200, "height", 200,
//				    "direction", "down",
//				    "percent", 1.0  // This is scroll are size - can be   <=1 and not negative
//				));
//			
//		}while(canScrollMore);

		// To Scroll to an element using Appium Javascript executor method - making use
		// of Do while only but this is slow you shold increase the scoll co-ordinates more to scroll more

		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 1.0));
			try {
				if (driver.findElement(AppiumBy.accessibilityId("Spinner")).isDisplayed());
				break;
			} catch (Exception e) {
				continue;
			}

		} while (canScrollMore);

		// 2. Making use of Google native Uiautomator class - If you know the element to
		// where you wanna scroll to
		// 
		//driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"));
		//
		//this is UiAutomator method which Google originally have created
		// you can just give it in a method and can scroll by giving the text to which
		// you wanna scroll to
		Thread.sleep(2000);

	}

}
