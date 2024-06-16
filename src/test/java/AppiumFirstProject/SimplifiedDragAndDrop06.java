package AppiumFirstProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class SimplifiedDragAndDrop06 extends BaseTest{
	
	
	@Test
	public void dragAndDropTest() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement draggable = driver.findElement(By.xpath("//android.view.View[@resource-id='io.appium.android.apis:id/drag_dot_1']"));
		
		// 1. By making use of Appium Inspector to check for the Co-ordinates of the drop field and using "Tap/Swipe By co-ordinates" just above the mobile screen of Appium inspector.
		
	//     dragElementToSpecificCordinates(draggable, 650,583);   //defined in BaseClass
		
		
		
		// 2. By Making use of get attribute to get the dropping co-ordinates of the element
		String dropCoordinates = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2")).getAttribute("bounds");
		int[] dropPoints = getRandomCoordinatewithinElement(dropCoordinates);  //defined in BaseClass
		dragElementToSpecificCordinates(draggable, dropPoints[0],dropPoints[1]);
		
		String dragResult = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(dragResult, "Dropped!");
		
	}
	
	
	// To get random co-ordinates of the element within the bound
	
	

}
