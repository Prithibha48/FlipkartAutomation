package Tests;

import java.util.Scanner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Flipkart.Base.CommonFunctions;
import Flipkart.Pages.*;

public class testFlow  extends CommonFunctions{
	homePage home;
	LoginPage log;
	WelcomePage welcome;
	String phoneNO;
	@BeforeClass
	void setObject() {
		phoneNO =prop.getProperty("phNo");
		home = new homePage(driver);
		log = new LoginPage(driver);
		welcome = new WelcomePage(driver);
		
	}
	
	@Test(priority=1)
	void log() {
		home.clicklogin();
		log.signIn(phoneNO);
	}
	@Test(priority = 2)
	void verify() {
		welcome.verifyTitle();
		welcome.logodisplayed();
	}

}
