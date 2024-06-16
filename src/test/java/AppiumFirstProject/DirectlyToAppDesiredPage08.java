package AppiumFirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class DirectlyToAppDesiredPage08 extends BaseTest {
	/*
	 * Sure! Here are the short definitions for App Package and App Activity in
	 * terms of Android:
	 * 
	 * ### App Package App Package**: The unique identifier for an Android
	 * application, typically in the format of a reverse domain name (e.g.,
	 * `com.example.myapp`). It is defined in the `AndroidManifest.xml` file and
	 * used to uniquely identify the app on the device and Google Play Store.
	 * 
	 * ### App Activity App Activity**: A single screen or interface within an
	 * Android application. It represents a specific task or action that the user
	 * can perform. Activities are defined in the `AndroidManifest.xml` file and
	 * serve as entry points for user interactions. An app can have multiple
	 * activities, with one designated as the main activity that launches when the
	 * app starts.
	 */

	// Open Command Prompt and type below commands -
	// 1. adb devices - To check if the device is connected or not
	// 2. adb shell dumpsys window | find "mCurrentFocus" - For Windows
	// adb shell dumpsys window | grep -E "mCurrentFocus" - For Mac
	// Now the output of aboce command looks like -
	// mCurrentFocus=Window{17965a6 u0
	// io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies}
	// Anything before slash is the package name - So, io.appium.android.apis will
	// be the package name
	// and anything after / is the activity name - So,
	// io.appium.android.apis.preference.PreferenceDependencies is the activity name

	@Test
	public void directlyToAppDesiredPage08Test() throws InterruptedException {

		// Activity activity = new Activity("io.appium.android.apis",
		// "io.appium.android.apis.preference.PreferenceDependencies");

		// driver.startActivity(activity);
		// startActivity is deprecated, instead use below -
		// https://github.com/appium/appium-uiautomator2-driver/blob/master/README.md#mobile-startactivity
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
				"io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		driver.findElement(By.id("android:id/edit")).sendKeys("Vishal Wifi");
		driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
		Thread.sleep(2000);

	}

}
