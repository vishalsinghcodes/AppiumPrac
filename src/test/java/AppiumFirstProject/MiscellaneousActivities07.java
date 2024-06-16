package AppiumFirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MiscellaneousActivities07 extends BaseTest {
	
	
	@Test
	public void miscellaneousTest() throws InterruptedException {
		
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		
		//1. To rotate the device
		DeviceRotation landscape = new DeviceRotation(0,0,90); //To Rotate the device 
		driver.rotate(landscape);
		
		
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		//2. To test the Clip-board
		driver.setClipboardText("Vishal Wifi");		// To set the ClipboardText
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());  // to get the Clipboeard text
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		
		//3. Actions buttons
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		
		Thread.sleep(5000);
		
		
	}
	
	

}
