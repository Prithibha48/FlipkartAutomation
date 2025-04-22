package Flipkart.Pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;


import Flipkart.Base.Selenium_Base;

public class SearchResultPage extends Selenium_Base {

	public SearchResultPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
	//Method to get the list of items after search
	public Boolean searchList() {
		List<WebElement> productLinks = driver.findElements(By.xpath(loc.getProperty("filter.list.xpath")));
		
		for (WebElement link : productLinks) {
			String productName = link.getText();  
			System.out.println(productName);
		}
		System.out.println();
		if(productLinks.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}
}
