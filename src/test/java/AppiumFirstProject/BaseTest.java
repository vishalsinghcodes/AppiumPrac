package AppiumFirstProject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;

	@BeforeClass
	public void configureAppiumTest() throws MalformedURLException, URISyntaxException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\Vishal Singh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();

		service.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("VishalPhone");
		options.setApp(
				"C:\\Users\\Vishal Singh\\eclipse-workspace\\AppiumFirstProject\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	public void longPressGesture(WebElement ele, int duration) {

		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", duration));

	}

	public void scrollABit() {
		boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
				ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent",
						1.0));
	}

	public void scrollToEndOfPage() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 1.0));
		} while (canScrollMore);
	}
	
	public void scrollToAnElementUsingAppiumMethod(String accessibilityId) {

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
		
		
		
		
		
	}
	
	public void scrollToAnElementUsingAndroidMethod(String accessibilityId) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+accessibilityId+"\"))"));	
	}
	
	public void swipeAnElement(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				   "elementId", ((RemoteWebElement)element).getId(),
				    "direction", direction,
				    "percent", 0.10 // This is swipe are size, when I tried with 0.75 It swiped to the end of the list so check before using the bigger percent
				));
	}

	
	public void dragElementToSpecificCordinates(WebElement element, int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId(),
			    "endX", x,
			    "endY", y
			));
	}
	
	
	
	
	public static int[] getRandomCoordinatewithinElement(String bounds) { // To get random co-ordinates of the element within the bound
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
        int[] droppoints  = new int[2];
        droppoints[0] = randomX;
      //  System.out.println(droppoints[0][0]);
        droppoints[1]= randomY;
      //  System.out.println( droppoints[0][1]);
        // Return the random coordinates as a Point
        return droppoints;
    }
	
	

	@AfterClass
	public void tearDown() {

		driver.quit();
		service.stop();

	}

	/*
	 * { "app":
	 * "C:\\\\Users\\\\Vishal Singh\\\\eclipse-workspace\\\\AppiumFirstProject\\\\src\\\\test\\\\java\\\\resources\\\\ApiDemos-debug.apk"
	 * , "automationName": "UIAutomator2", "deviceName": "VishalPhone",
	 * "platformName": "android" }
	 */

}
