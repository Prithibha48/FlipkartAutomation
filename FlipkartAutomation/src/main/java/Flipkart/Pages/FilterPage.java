package Flipkart.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Flipkart.Base.Selenium_Base;

public class FilterPage extends Selenium_Base {
	
	public FilterPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	//Method to filter Items based on the value
	public boolean filter(String value) {
	    try {
	        WebElement filterBtn = driver.findElement(By.xpath(loc.getProperty("filter.max.xpath")));
	        Select price = new Select(filterBtn);
	        String cleanedValue = value.trim().replace(".0", "");

	        price.selectByValue(cleanedValue);

	        // Wait briefly to ensure the selection reflects in the DOM
	        Thread.sleep(500); // Ideally use WebDriverWait if there's dynamic behavior

	        // Confirm if the selected value is correctly selected
	        WebElement selectedOption = price.getFirstSelectedOption();
	        return selectedOption.getAttribute("value").equals(cleanedValue);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	//Method to get the filtered list of items
	public boolean filteredList() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		
		List<WebElement> productItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(loc.getProperty("filter.list.xpath"))));
		System.out.println("Total number of items after filtering: "+productItems.size());
		
		for (WebElement item : productItems) {
			String itemName = item.getText();  
			System.out.println(itemName);
		}
		if(productItems.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}
}