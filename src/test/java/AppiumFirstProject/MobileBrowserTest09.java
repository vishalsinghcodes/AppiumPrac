package AppiumFirstProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Rectangle;

public class MobileBrowserTest09 extends MobileBrowserBaseTest { // Remember that we have used Mobile browserbase test
																	// to configure our tests for mobile browser

	@Test
	public void mobileBrowserTest01() { // here we will be automating the Mobile web brower lets say - chrome app
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Vishal Singh Labs");
		System.out.println(driver.getTitle());
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

	}

	@Test
	public void anotherWebTestUsingRahulWeb() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/angularAppdemo/"); // this is a web responsive website, We need to be
																		// very careful in these case
		// It means that the web view and Mobile view are different from each other like
		// in rahils website also the three options - Products Library, and Cart got
		// folded into hamburger when we
		// changed the view from inspect option from web to mobile view.
		driver.findElement(By.className("navbar-toggler-icon")).click();
		driver.findElement(By.xpath("//a[text()='Products ']")).click();
		int xcordinateOfElement = driver.findElement(By.xpath("//a[text()='Devops']")).getRect().getX();
		int ycordinateOfElement = driver.findElement(By.xpath("//a[text()='Devops']")).getRect().getY();
		((JavascriptExecutor) driver)
				.executeScript("window.scrollTo(" + xcordinateOfElement + "," + ycordinateOfElement + ")","");
		// Rahul have used scrollBy that is for the cases where you know how much to
		// scroll. But in my method I have taken out the Cordinates of the Element and
		// have scrolled before clicking.
		// int width = ele.getRect().getDimension().getWidth();
//		
//		((JavascriptExecutor)driver).executeScript("window.scrollTo", null)

		driver.findElement(By.xpath("//a[text()='Devops']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector(".product-title")).getText(), "DEVOPS");
		Thread.sleep(5000);

	}

}
