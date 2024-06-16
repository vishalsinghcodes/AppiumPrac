package AppiumFirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class SimplifiedSwipeGesture05 extends BaseTest{
	
	
	
	
	@Test
	public void swipeGestureTest() {
		// In this scenario we are testing that when we opened the gallery and we are on photos 
		// First By default the focus should be on the first image, Now we swipe and select the second image, now the focus should be true for second image
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Gallery']")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
		String checkFocusable = 	driver.findElement(By.xpath("//android.widget.Gallery//android.widget.ImageView[1]")).getAttribute("focusable");
		System.out.println(checkFocusable);
		Assert.assertEquals(checkFocusable, "true"); // To check the focusable field is true before the swipe
		// Swipe
		WebElement firstImage = driver.findElement(By.xpath("//android.widget.Gallery//android.widget.ImageView[1]"));
		swipeAnElement(firstImage, "left");
		// Swipe Once
		
		
		checkFocusable = 	driver.findElement(By.xpath("//android.widget.Gallery//android.widget.ImageView[1]")).getAttribute("focusable");
		Assert.assertEquals(checkFocusable, "false"); // To check the focusable field is false after the swipe as the second picture will be be in the focusable state
		
	}
	
	

}
