package Flipkart.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium_Base {
	protected RemoteWebDriver driver = null;
	protected static WebDriverWait wait = null;
	public String getTitle(){
		return driver.getTitle();
	}
	
	protected void click(WebElement ele) {
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
	}
	
	public void type(WebElement ele, String data) {
		wait.until(ExpectedConditions.visibilityOf(ele));
		ele.clear();
		ele.sendKeys(data);
	}
	public void wimdowNavigation(int i) {
		Set<String>windowshandle= driver.getWindowHandles();
		List<String>list=new ArrayList<String>(windowshandle);
		 if (i >= 0 && i < list.size()) {
	            String targetWindow = list.get(i);
	            // Switch to the window at the given index
	            driver.switchTo().window(targetWindow);
	            System.out.println("Switched to window: " + targetWindow);
	        } else {
	            System.out.println("Invalid window index: " + i);
	        }
	} 
}
