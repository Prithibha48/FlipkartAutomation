package Flipkart.Base;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class CommonFunctions extends Selenium_Base{
	protected Properties prop;
	@BeforeTest
	public void setup() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("/FlipkartAutomation/test_data.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("baselink"));
	}
	
	@AfterTest
	public void quit() {
		driver.quit();
	}
}
