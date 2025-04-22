package Flipkart.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Flipkart.Base.Selenium_Base;

public  class ProductPage extends Selenium_Base {


	protected static WebDriverWait wait = null;
	public static String title;
	public  String price;


	public ProductPage(RemoteWebDriver driver) {
		this.driver = driver;
	}

	//Method to Select the first Product
	public boolean selectProduct() {
	    try {
	        WebElement product = driver.findElement(By.xpath(loc.getProperty("product.item.xpath")));
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        click(product);

	        // Navigate to the new product window/tab
	        windowNavigation(1);

	        // Wait for product name and price to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(loc.getProperty("product.name.css"))));
	        WebElement productPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("product.price.xpath"))));

	        // Capture product details
	        title = productName.getText();
	        price = productPrice.getText();
	        System.out.println(title + " - " + price);

	        // Return true if title and price are not empty
	        return (title != null && !title.isEmpty()) && (price != null && !price.isEmpty());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	//Method to Add the Product to Cart

	public Boolean addToCart() {

		WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loc.getProperty("product.cart.xpath"))));
		boolean buttonEnabled = addToCart.isEnabled();

		//Check if Add to cart is enabled

		if(buttonEnabled) {
			click(addToCart);
			System.out.println("Item is Added To the Cart");
			return true;
		}
		else {
			System.out.println("Add To Cart is not Enabled - Item is Out Of Stock");
			return false;
		}
	}


	public static String gettitle() {
		return title;

	}

}
