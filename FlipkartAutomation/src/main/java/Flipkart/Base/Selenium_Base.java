package Flipkart.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium_Base {

	protected RemoteWebDriver driver = null;
	protected static WebDriverWait wait = null;
	protected Properties loc;
	// Logger instance for logging
	private static final Logger logger = Logger.getLogger(Selenium_Base.class.getName());

	// Constructor to initialize WebDriverWait
	public Selenium_Base() {

		// Initialize WebDriverWait
		wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(30)); // Set up the wait time for all elements
		loc = new Properties();

		try {
			// Updated the path to be relative to the project folder
			loc.load(new FileInputStream("./locators.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Get the page title

	public String getTitle() {
		logger.log(Level.INFO, "Fetching page title");
		return driver.getTitle();
	}

	// Click on an element

	protected void click(WebElement ele) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			logger.log(Level.INFO, "Clicking on element: {0}", ele);
			ele.click();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error clicking on element: {0}", ele.toString());
			e.printStackTrace();
		}
	}

	// Type data into an input field

	public void type(WebElement ele, String data) {

		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
			logger.log(Level.INFO, "Typing data into element: {0}", ele);
			ele.clear(); // Clear the field before entering text
			ele.sendKeys(data);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error typing into element: {0}", ele.toString());
			e.printStackTrace();
		}
	}


	// Switch between browser windows by index

	public void windowNavigation(int i) {

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowList = new ArrayList<>(windowHandles);

		if (i >= 0 && i < windowList.size()) {
			String targetWindow = windowList.get(i);

			try {
				logger.log(Level.INFO, "Switching to window: {0}", targetWindow);
				driver.switchTo().window(targetWindow);
				logger.log(Level.INFO, "Successfully switched to window: {0}", targetWindow);
			}
			catch (Exception e) {
				logger.log(Level.SEVERE, "Error switching to window: {0}", targetWindow);
				e.printStackTrace();
			}
		} 
		else {
			logger.log(Level.WARNING, "Invalid window index: {0}", i);
		}
	}

	// Close the driver and quit the browser

	public void quitDriver() {

		try {
			if (driver != null) {
				logger.log(Level.INFO, "Quitting WebDriver session.");
				driver.quit();
			}
		} 
		catch (Exception e) {
			logger.log(Level.SEVERE, "Error quitting the driver.");
			e.printStackTrace();
		}
	}

	// Get the WebDriver instance 

	public RemoteWebDriver getDriver() {
		return driver;
	}
}
