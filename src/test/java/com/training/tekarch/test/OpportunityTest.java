package com.training.tekarch.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.pages.LoginPage;
import com.training.tekarch.base.BaseTest;
import com.training.tekarch.pages.HomePage;


import com.training.tekarch.utilities.CommonUtilities;

public class OpportunityTest extends BaseTest {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	String url;
	String username;
	String password;
	

	CommonUtilities common = new CommonUtilities();

	@BeforeMethod
	public void beforeMethod() throws IOException {
		driver = getDriver();
		
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		
		url = common.getproperty("url");
		username = common.getproperty("userName");
		password = common.getproperty("password");
		sAssert = new SoftAssert();
		driver.get(url);
		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver,  Duration.ofSeconds(20L));

	}

	@Test
	public void TC01_verifyBlankpasswordLoginError() {
		
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		// 1. set user Name
		loginPage.enterUserName(username);
		// Set blankPassword
		loginPage.enterPassword("");
		// click Login button
		loginPage.clickLogin();
		// getting actual error string
		String actualError = loginPage.getLoginError();
		// Asserting with expected Login Error
		Assert.assertEquals(actualError, loginPage.EXPECTED_LOGINPAGE_ERROR);

	}
	
		

	@AfterMethod
	public void teardown() {
		takeScreenShot(driver);
		driver.close();
	}

}
