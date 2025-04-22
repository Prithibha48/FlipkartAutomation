package Tests;

import org.testng.annotations.*;
import org.testng.Assert;
import Flipkart.Base.CommonFunctions;
import Flipkart.Pages.*;
import Flipkart.Utilities.ExcelUtils;
import Flipkart.Utilities.ExtentReportManager;

import com.aventstack.extentreports.*;

public class testFlow extends CommonFunctions {

	WelcomePage welcome;
	SearchResultPage searchPage;
	FilterPage filterPage;
	ProductPage productPage;
	VerifyCartPage verifyCartPage;
	ExtentReports extent;
	ExtentTest test;

	@BeforeTest
	public void setObject() {
		extent = ExtentReportManager.getInstance();
		welcome = new WelcomePage(driver);
		searchPage = new SearchResultPage(driver);
		filterPage = new FilterPage(driver);
		productPage = new ProductPage(driver);
		verifyCartPage = new VerifyCartPage(driver);
	}

	@Test(priority = 1)
	public void verifyTest() {
		test = extent.createTest("Verify Flipkart Home Page");

		welcome.verifyTitle();
		boolean logoDisplayed = welcome.logodisplayed();
		String pageTitle = welcome.getTitle();

		Assert.assertEquals(pageTitle, "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!", "Title matches!");
		Assert.assertTrue(logoDisplayed, "Logo is not displayed.");

		String screenshot = captureScreenshot("verifyTest");
		test.pass("Title and logo verified").addScreenCaptureFromPath(screenshot);
	}
	@DataProvider(name = "searchData")
	public Object[][] getSearchData() {
		return ExcelUtils.getTestData("Sheet1");
	}

	@Test(dependsOnMethods = "verifyTest", dataProvider = "searchData")
	public void searchItems(String searchTerm, String filterValue) {

		test = extent.createTest("Search for Product: " + searchTerm);
		welcome.search(searchTerm);
		boolean resultsExist = searchPage.searchList();
		Assert.assertTrue(resultsExist, "Search results are not displayed.");
		test.pass("Search results displayed").addScreenCaptureFromPath(captureScreenshot("searchItems"));

		// Pass filterValue to next method
		boolean filtered = filterPage.filter(filterValue);
		Assert.assertTrue(filtered, "Filter not applied.");
		test.pass("Filter applied").addScreenCaptureFromPath(captureScreenshot("filter"));
	}


	@Test(dependsOnMethods = "searchItems")
	public void filteredList() {
		test = extent.createTest("Check Filtered List");
		boolean resultsExist = filterPage.filteredList();
		Assert.assertTrue(resultsExist, "Filtered results are not displayed.");
		test.pass("Filtered results shown").addScreenCaptureFromPath(captureScreenshot("filteredList"));
	}

	@Test(dependsOnMethods = "filteredList")
	public void selectItem() {
		test = extent.createTest("Select Product");
		boolean itemSelected = productPage.selectProduct();
		Assert.assertTrue(itemSelected, "Product selection failed.");
		test.pass("Product selected").addScreenCaptureFromPath(captureScreenshot("selectItem"));
	}

	@Test(dependsOnMethods = "selectItem")
	public void addtocart() {
		test = extent.createTest("Add to Cart");
		boolean addedToCart = productPage.addToCart();
		Assert.assertTrue(addedToCart, "Product not added to cart.");
		test.pass("Product added to cart").addScreenCaptureFromPath(captureScreenshot("addtocart"));
	}

	@Test(dependsOnMethods = "addtocart")
	public void verifycart() {
		test = extent.createTest("Verify Cart");
		boolean cartVerified = verifyCartPage.verifyCart();
		Assert.assertTrue(cartVerified, "Product not found in cart.");
		test.pass("Cart verified").addScreenCaptureFromPath(captureScreenshot("verifycart"));
	}

	@AfterTest
	public void tearDownReport() {
		extent.flush();
	}
}
