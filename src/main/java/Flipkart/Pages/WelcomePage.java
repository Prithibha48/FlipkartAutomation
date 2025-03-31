package Flipkart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import Flipkart.Base.Selenium_Base;

public class WelcomePage extends Selenium_Base{
	public WelcomePage(RemoteWebDriver driver) {
		this.driver=driver;
	}
	public void verifyTitle(){
		System.out.println(getTitle());
	}

	public void logodisplayed() {
        WebElement logo = driver.findElement(By.xpath("//img[@title='Flipkart']"));
        if (logo.isDisplayed()) {
            System.out.println("Logo is Displayed");
        } else {
            System.out.println("Logo is Not Displayed");
        }
      }
	}
