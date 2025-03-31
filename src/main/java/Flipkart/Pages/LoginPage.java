package Flipkart.Pages;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import Flipkart.Base.Selenium_Base;

public class LoginPage extends Selenium_Base {
	public LoginPage(RemoteWebDriver driver) {
	this.driver=driver;
	}
public void signIn(String username ) {
	WebElement phNo = driver.findElement(By.xpath("//label[@class='Gq-80a']")); 
	type(phNo,username);
	phNo.sendKeys(Keys.ENTER);
	 Scanner sc = new Scanner(System.in);
     System.out.println("Please enter the OTP sent to your phone: ");
     String otp = sc.nextLine(); 
	WebElement code = driver.findElement(By.xpath("//div[@class='XDRRi5']"));
	type(code,otp);
	code.sendKeys(Keys.ENTER);
	
	
}	
}
