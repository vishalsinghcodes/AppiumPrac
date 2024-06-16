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

public class DragAndDrop06 extends BaseTest{
	
	
	@Test
	public void dragAndDropTest() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		WebElement draggable = driver.findElement(By.xpath("//android.view.View[@resource-id='io.appium.android.apis:id/drag_dot_1']"));
		
		// 1. By making use of Appium Inspector to check for the Co-ordinates of the drop field and using "Tap/Swipe By co-ordinates" just above the mobile screen of Appium inspector.
//		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//			    "elementId", ((RemoteWebElement) draggable).getId(),
//			    "endX", 650,
//			    "endY", 583
//			));
//		
//		String dragResult = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
//		Assert.assertEquals(dragResult, "Dropped!");
//		
//		Thread.sleep(2000);
		
		
		// 2. By Making use of get attribute to get the dropping co-ordinates of the element
		
		String dropCoordinates = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2")).getAttribute("bounds");
		System.out.println(dropCoordinates);
		int[][] dropPoints = getRandomCoordinatewithinElementLocal(dropCoordinates);
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) draggable).getId(),
			    "endX", dropPoints[0][0],
			    "endY", dropPoints[0][1]
			));
		
		String dragResult = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
		Assert.assertEquals(dragResult, "Dropped!");
		
	}
	
	
	// To get random co-ordinates of the element within the bound
	public static int[][] getRandomCoordinatewithinElementLocal(String bounds) { // To get random co-ordinates of the element within the bound
		//String str = "[434,365][868,799]";
		String update = bounds.replaceAll("[^0-9]", " ");
		String[] strar = update.split(" ");
		List<String> corr = new ArrayList<>(Arrays.asList(strar));
		corr.removeAll(Arrays.asList("",null));
		//corr.stream().forEach(s->System.out.println(s));
		int[] coordinates = corr.stream().mapToInt(Integer::parseInt).toArray();
		// System.out.println("Integer coordinates: " + Arrays.toString(coordinates));
	
        int startX = coordinates[0];
     //   System.out.println(startX);
        int startY = coordinates[1];
   //    System.out.println(startY);
        int endX = coordinates[2];
        int endY = coordinates[3];

        // Generate random coordinates within the bounds
        Random random = new Random();
        int randomX = startX + random.nextInt(endX - startX + 1);
        int randomY = startY + random.nextInt(endY - startY + 1);
        int[][] droppoints  = new int[1][2];
        droppoints[0][0] = randomX;
        System.out.println(droppoints[0][0]);
        droppoints[0][1]= randomY;
        System.out.println( droppoints[0][1]);
        // Return the random coordinates as a Point
        return droppoints;
    }
	

}
