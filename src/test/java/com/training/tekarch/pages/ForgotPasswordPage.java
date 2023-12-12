package com.training.tekarch.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.tekarch.base.BasePage;

public class ForgotPasswordPage extends BasePage{

	// Login page constants
	

	public final  String EXPECTED_FORGOTPASS_TITLE = "Forgot Your Password | Salesforce";
	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver,Duration.ofSeconds(20L));
		actions = new Actions(driver);
	}

	//Forgot password page
	@FindBy(xpath="//input[@id='un']")
	WebElement name;
	
	@FindBy(id="continue")
	 public WebElement continueBtn;
	
	@FindBy(xpath="//p[1]")
	 public WebElement message;
	
	
	
	public String getUserName() {
	String str=	driver.findElement(By.id("idcard-identity")).getText();
	return str;
		
	}
	
	public void setUserName(String str) {
		typeElement(name, str);
	}
	public void clickContinue() {
		clickElement(continueBtn);
	}
	public String messageDisplayed() {
		return message.getText();
	}

}
