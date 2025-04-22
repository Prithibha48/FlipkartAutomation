package Flipkart.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;

import Flipkart.Utilities.ExtentReportManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;

public class CommonFunctions extends Selenium_Base {

    protected Properties prop;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeTest
    public void setup() {
        // Load test data
        prop = new Properties();
        try {
            prop.load(new FileInputStream("./test_data.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize Extent Report
        extent = ExtentReportManager.getInstance();

        // Browser selection (based on property file instead of Scanner)
        String browser = prop.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = createDriver("chrome");
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = createDriver("firefox");
                break;
            default:
                System.out.println("Unsupported browser. Defaulting to Chrome.");
                WebDriverManager.chromedriver().setup();
                driver = createDriver("chrome");
        }

        // Driver config
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(prop.getProperty("baselink"));
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }

    // Driver factory method
    private RemoteWebDriver createDriver(String browser) {
        RemoteWebDriver driver = null;
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        return driver;
    }
 
    // Screenshot capture method for Extent Report
    public String captureScreenshot(String screenshotName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName + "_" + System.currentTimeMillis() + ".png";
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            return path;
        } catch (IOException e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }

    @AfterMethod
    public void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }
}
