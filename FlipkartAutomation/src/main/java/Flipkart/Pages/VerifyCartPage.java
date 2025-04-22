package Flipkart.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Flipkart.Base.Selenium_Base;

public class VerifyCartPage extends Selenium_Base {

	public VerifyCartPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
	public Boolean verifyCart() {

		//Get Product title from Product Page

		String name = ProductPage.gettitle().trim().replaceAll("\\s+", " ");
		boolean productFound = false;

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Verify cart contains Selected product

		List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(loc.getProperty("verify.cart.xpath"))));

		for (WebElement item : cartItems) {
			String actualProductName = item.getText().trim().replaceAll("\\s+", " ");


			if (actualProductName.contains(name)||name.contains(actualProductName)) {
				productFound = true;
				break;
			}
		}

		if (productFound) {
			System.out.println("✅ Product '" + name + "' is in the cart.");
			return true;
		} 

		else {
			System.out.println("❌ Product '" + name + "' NOT found in the cart.");
			return false;
		}
	}
}



