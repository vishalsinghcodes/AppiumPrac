package AppiumFirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.functions.ExpectedCondition;

public class SimplifiedScrollGesture04 extends BaseTest {


	// There are two ways of doing the scroll Gesture

	// 1. By using the java Script executor
	// 2. By using UiAutomator method which Google originally have created

	@Test
	public void ScrollGestureTest() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		// To Scroll once
	//	 scrollABit();
		
		// To Scroll till the end of the page - Use Do while loop
	//	 scrollToEndOfPage();  // Defined in BaseClass
			
		// To Scroll to an element using Appium Javascript executor method - making use
			
		String accessibilityId	= "Spinner";
	//	 scrollToAnElementUsingAppiumMethod(accessibilityId);   // Defined in BaseClass

		
		// 2. Making use of Google native Uiautomator class - If you know the element to
		// where you wanna scroll to
		// 
		scrollToAnElementUsingAndroidMethod(accessibilityId);   // Defined in BaseClass
		//
		//this is UiAutomator method which Google originally have created
		// you can just give it in a method and can scroll by giving the text to which
		// you wanna scroll to
		Thread.sleep(2000);

	}

}
