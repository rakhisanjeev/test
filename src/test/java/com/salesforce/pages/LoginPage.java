package com.salesforce.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.tekarch.base.BasePage;


public class LoginPage extends BasePage{

	// Login page constants
	
	public final  String EXPECTED_LOGINPAGE_TITLE = "Login | Salesforce";
	public final  String EXPECTED_LOGINPAGE_ERROR = "Please enter your password.";
	public final  String EXPECTED_HOMEPAGE_TITLE = "Home Page ~ Salesforce - Developer Edition";
	
	public LoginPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
	}
	@FindBy(id="username")
    WebElement userName;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(id="Login")
	WebElement loginButton;
	
	@FindBy(id="error")
	WebElement loginError;
	
	@FindBy(xpath="//input[@id='rememberUn']")
	WebElement rememberMe;
	
	@FindBy(id="forgot_password_link")
	WebElement forgotPasswordlink;
	
	@FindBy(id="error")
	WebElement err;
	
	
	public void enterUserName(String struserName) {
		typeElement(userName, struserName);
	}
	
	public void enterPassword(String strpassword) {
				password.sendKeys(strpassword);
	} 
	
	public void clickLogin() {
		clickElement(loginButton);
	}
	
	public void rememberMe() {
		if (!rememberMe.isSelected())
			clickElement(rememberMe);
	}
	public void clickForgotPassword() {
		clickElement(forgotPasswordlink);
	}
	
	public String getLoginError() {
		return loginError.getText();
	}
	public String getInvalidLoginError() {
		return err.getText();
	}
	
	
	
	public void loginSalesForce( String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickLogin();
	}
	public String getUserName() {
	String str=	driver.findElement(By.id("idcard-identity")).getText();
	return str;
		
	}
	public boolean rememberMeStatus() {
		 return rememberMe.isSelected();
	}
	
}
