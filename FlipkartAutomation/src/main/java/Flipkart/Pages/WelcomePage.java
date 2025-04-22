package Flipkart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import Flipkart.Base.Selenium_Base;

public class WelcomePage extends Selenium_Base {
	
	public WelcomePage(RemoteWebDriver driver) {
		this.driver = driver;
	}

	// Method to verify the page title
	public void verifyTitle() {
		System.out.println(getTitle());
	}

	// Method to check if the logo is displayed
	public Boolean logodisplayed() {
		WebElement logo = driver.findElement(By.xpath(loc.getProperty("welcome.logo.xpath")));
		if (logo.isDisplayed()) {
			System.out.println("Logo is Displayed");
				return true;
		} else {
			System.out.println("Logo is Not Displayed");
			return false;
		}
		
	}
	
	//Method to search for an item
	public void search(String item) {
		WebElement searchBox= driver.findElement(By.xpath(loc.getProperty("welcome.searchBox.xpath")));
		type(searchBox,item);
		searchBox.sendKeys(Keys.ENTER);

	}



}
