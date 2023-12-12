package com.training.tekarch.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.pages.LoginPage;
import com.training.tekarch.base.BaseTest;
import com.training.tekarch.pages.ForgotPasswordPage;
import com.training.tekarch.pages.HomePage;
import com.training.tekarch.utilities.CommonUtilities;

public class LoginTest extends BaseTest {
	WebDriver driver;
	com.salesforce.pages.LoginPage loginPage;
	HomePage homePage;
	String url;
	String username;
	String password;
	

	CommonUtilities common = new CommonUtilities();

	@BeforeMethod(groups= {"abcd"})
	public void beforeMethod() throws IOException {
		driver = getDriver();
		
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		
		url = CommonUtilities.getproperty("url");
		username = CommonUtilities.getproperty("userName");
		password = CommonUtilities.getproperty("password");
		sAssert = new SoftAssert();
		driver.get(url);
		driver.manage().window().maximize();;
		//WebDriverWait(driver,  Duration.ofSeconds(20L));

	}

	@Test(groups= {"abcd"})
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
	@Test(groups= {"abcd"})
	public void TC02_navigateToSalesForceHomePage() {
		
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		// 1. set user Name
		loginPage.enterUserName(username);
		// Set blankPassword
		loginPage.enterPassword(password);
		// click Login button
		loginPage.clickLogin();	
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualTitle1 = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle1, loginPage.EXPECTED_HOMEPAGE_TITLE);
		
		
	}
	@Test
	public void TC03_verifyRememberMe() {
		
		
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		// 1. set user Name
		loginPage.enterUserName(username);
		// Set blankPassword
		loginPage.enterPassword(password);
		loginPage.rememberMe();	
		// click Login button
		loginPage.clickLogin();		
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualTitle1 = loginPage.getPageTitle();
		
		Assert.assertEquals(actualTitle1, loginPage.EXPECTED_HOMEPAGE_TITLE);
		homePage.logoutOfSalesForce();
		wait.until(ExpectedConditions.titleContains(loginPage.EXPECTED_LOGINPAGE_TITLE));
		String actualTitle3=loginPage.getPageTitle();
		Assert.assertEquals(actualTitle3, loginPage.EXPECTED_LOGINPAGE_TITLE);
		Boolean value = loginPage.rememberMeStatus();
		Assert.assertTrue(value);
		String actualName = loginPage.getUserName();
		Assert.assertEquals(actualName, username);		
				
	}
	@Test
	public void TC04_verifyForgotPassword() {
		ForgotPasswordPage forgotPwdPage = new ForgotPasswordPage(driver);
		
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		loginPage.clickForgotPassword();
		
		wait.until(ExpectedConditions.visibilityOf(forgotPwdPage.continueBtn));
		
		String actualTitle1 = forgotPwdPage.getPageTitle();
		
		Assert.assertEquals(actualTitle1, forgotPwdPage.EXPECTED_FORGOTPASS_TITLE);
		// forgot password page actions
		forgotPwdPage.setUserName(username);
		forgotPwdPage.clickContinue();
		String actualMessage= forgotPwdPage.messageDisplayed();
		String expectedMessage= "We�ve sent you an email with a link to finish resetting your password.";
		Assert.assertEquals(actualMessage, expectedMessage);
		
	}
	@Test
	public void TC05_validateLoginErrorMessage() {
		
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		// 1. set user Name
		loginPage.enterUserName("abcde");
		// Set blankPassword
		loginPage.enterPassword("asdf");
		// click Login button
		loginPage.clickLogin();	
		String expectedMessage = "Your login attempt has failed. The username or password may be incorrect, or your location or login time may be restricted. Please contact the administrator at your company for help";
		String actualMessage=loginPage.getInvalidLoginError();
		Assert.assertEquals(actualMessage, expectedMessage);
	}
		

	@AfterMethod
	public void teardown() {
		takeScreenShot(driver);
		driver.close();
	}

}
