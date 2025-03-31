package Flipkart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import Flipkart.Base.Selenium_Base;

public class homePage extends Selenium_Base {
 public homePage(RemoteWebDriver driver) {
	 this.driver=driver;
 }
 public void clicklogin() {
	 WebElement login= driver.findElement(By.xpath("//a[@title='Login'][position()=1]"));
	 click(login);
 }
}
