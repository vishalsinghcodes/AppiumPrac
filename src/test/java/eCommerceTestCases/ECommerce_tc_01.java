package eCommerceTestCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.functions.ExpectedCondition;

public class ECommerce_tc_01 extends BaseTestEC{
	
	
	@Test
	public void fillForm01() throws InterruptedException {
		//System.out.println(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText());;
		// 1. To type the name in the Name text field
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vishal Singh");
		// 2. Hiding the Keyboard - After Typing the name the keyboard is not disappearing and the radio button is hidden, we can not interact with the radio button if the keyboard is still in open state
		driver.hideKeyboard(); // this method is directly use to close the keyboard
		// 3. Selecting Gender - Selected Female radio button
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		// 4. Now the country dropDown 
		// Lets suppose you want to select the country which is not visible in the opened area at the first time when you tap the Country dropDown when it shows the list
		   // 4.1 - Tap on country drop-down.
		   // 4.2 - Scroll to the country of your choice. 
		   // 4.3 - Tap on the country of your choice.
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click(); // Click on dropdown
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"))")); // Scroll to the country
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='India']")).click();  // Tap on the desired country
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(2000);
	}
	
	
	@Test
	public void toastTestMessage() throws InterruptedException {
		// Its very tough for taking the screenShot of the toast in the Appium Inspector
		// Here we will learn to test the message that appear in the toast after we click on the Let's Shop Button without entering any text in the name field. 
		// We can not grab/inspect the toast messages using Appium inspector
		// One way is that. when the developer develop the toast message - they are required to use common toast tag which is - android.widget.Toast
		// so simply we will use the Xpath and create the X-path of the toast - //android.widget.Toast
		// Ok, in this case It may happen that on the screen there are two tags, So we can make use of indexing in the X-Path to detect the required toast. 
		// Like - (//android.widget.Toast)[2] for the 2nd/Lower toast message.
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessagefromgetText = driver.findElement(By.xpath("//android.widget.Toast")).getText();
		System.out.println(toastMessagefromgetText);
		Assert.assertEquals(toastMessagefromgetText, "Please enter your name"); // Here Rahul said that the .getText() will not work, But it is working. 
		// may be he said that in toast message the text is generally contained in the <name> attribute. So we can grab the value of that attribute and verify the same. 
		String toastMessagefromAttribute = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
		System.out.println(toastMessagefromAttribute);
		Assert.assertEquals(toastMessagefromAttribute, "Please enter your name");
		Thread.sleep(2000);
	}
	
	
	@Test
	public void addToCartDesiredProductTest() {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vishal Singh");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click(); // Click on dropdown
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bhutan\"))")); // Scroll to the country
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Bhutan']")).click();  // Tap on the desired country
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click(); //Login done
		// Now suppose you wanna buy Shoes - "Jordan Lift Off" - Now you need to scroll up to that product in a similar way you did for country
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan Lift Off\"))")); 
		// Now the next step is to add to cart, But the problem is there may be several add to cart buttons based on several factors, but we need to only tap on the button which 
		// is for our choice of shoes, We should not use indexing in this case. 
		String requiredProduct = "Jordan Lift Off";
		int numberOfProductCurrentlyonPage = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for(int i = 0;i<numberOfProductCurrentlyonPage;i++) {
			String currentProduct = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(currentProduct.equalsIgnoreCase(requiredProduct)) {
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i+1).click();  // bcoz we are starting from 0 - This is to click on add to cart
			}
			
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		// Now our product is added to the cart, But we have to validate that the product added in the cart is the same product which we added in the previous screen. 
		// Here we can observe one thing that the ID we used in the prev. page and the cart page is same for the products. So in the sequence of the execution 
		// suppose your cart page is still not opened and the script will detect the ID of the product page and will assert it. which is incorrect 
		// So in this case we should wait for the cart page to fully loaded and then we must apply the assert on the product name. 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.TextView[@text='Cart'"))));		
		//or
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		String finalPageProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(finalPageProduct, requiredProduct);
		
	}
	
	
	
	@Test
	public void verifyTotalAmountIsCorrect() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vishal Singh");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click(); // Click on dropdown
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Angola\"))")); // Scroll to the country
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Angola']")).click();  // Tap on the desired country
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click(); //Login done	
		
		//Here Rahul have used below logic to add only first 2 products to the cart
		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
		Thread.sleep(2000); // It is taking sometime for the 1st add to cart to be add to cart again (the text)
		driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();
		//Thread.sleep(2000);
		// we are again using the index 0 beacause once we are clibbking on the Add to cart, It is changed to Added for sometime
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		List<WebElement> PriceList = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		
		Float totalAmount =0f;
		for(WebElement ele : PriceList) {
			//totalAmount += Float.parseFloat(ele.getText().split("\\$")[1]);
			totalAmount += getFormattedAmount(ele.getText());
		}
		
		//For doing above task Rahul asked to create a method to parse the String amount and return the float value
		
		
		//System.out.println(totalAmount);
		//Amount from Page 
		Float amountFromPage = getFormattedAmount(driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText());
		System.out.println("Total Amount Calculated : "+ totalAmount);
		System.out.println("Amount from Page : "+ amountFromPage);
		Assert.assertEquals(totalAmount, amountFromPage); // to check the total amount 
		
		// Now to check the Terms of Conditions - Longpress on the element on the cart page
		// this longpressGesture method I have written in the base class. based on the Appium gesture document of github
		longPressGesture(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")), 2000); // time to be given in milliseconds
		
		
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.id("android:id/message")).getText(), "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
		driver.findElement(By.id("android:id/message")).getText();
		driver.findElement(By.id("android:id/button1")).click();		
		driver.findElement(By.xpath("//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		
		Thread.sleep(2000);
		
		// this change I am inserting to check if my code is working
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void roughTestToaddDesiredProductsinCartIncomplete() throws InterruptedException {   // -------------------------- Incomplete hai --------------------------
		/* In this test, the major objective is to verify that the total amount displayed on the cart page is correct 
		 * based on the price of the products added to the cart and the quantities specified for each product.
		 */
		List<String> requiredItemsList = Arrays.asList("Jordan Lift Off", "PG 3");
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vishal Singh");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click(); // Click on dropdown
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bhutan\"))")); // Scroll to the country
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Bhutan']")).click();  // Tap on the desired country
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click(); //Login done	
		//	driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan Lift Off\"))")); 
		boolean canScrollMore;
		do {
			int numberOfProductCurrentlyonPage = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for(int i = 0;i<numberOfProductCurrentlyonPage;i++) {
			String currentProduct = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(requiredItemsList.contains(currentProduct)) {
			//	requiredItemsList
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i+1).click(); 
			}		
		}	
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 100, "height", 100, "direction", "down", "percent", 50.0));
		
		} while (canScrollMore);
		
		
//		String requiredProduct = "Jordan Lift Off";
//		int numberOfProductCurrentlyonPage = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
//		for(int i = 0;i<numberOfProductCurrentlyonPage;i++) {
//			String currentProduct = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
//			if(currentProduct.equalsIgnoreCase(requiredProduct)) {
//				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i+1).click(); 
//			}
//			
//		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
//		String finalPageProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
//		Assert.assertEquals(finalPageProduct, requiredProduct);
 Thread.sleep(5000);
		
	}
	

}
