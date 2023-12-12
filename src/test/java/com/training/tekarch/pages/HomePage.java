package com.training.tekarch.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.tekarch.base.BasePage;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
		actions = new Actions(driver);
	}
	
	

	@FindBy(id="userNavLabel")
	public WebElement usermenuDropdown;
	
	
	
	@FindBy(xpath="//a[@title='My Profile']")
	public WebElement myProfile;	
	
	@FindBy(xpath="//a[@title='My Settings']")
	public WebElement mySettings;	
	
	@FindBy(xpath="//a[@title='Developer Console (New Window)']")
	public WebElement developerConsole;	
	
	@FindBy(xpath="//a[@title='Switch to Lightning Experience']")
	public WebElement switchToLightning;	
	
	@FindBy(xpath="//a[@title='Logout']")
	public WebElement logout;
	
	@FindBy(id="userNav-menuItems")
	public WebElement userNavmenuItems;
	
	
	public void clickUserMenuDropDown() {
		clickElement(usermenuDropdown);
	}
	
	public void selectMyProfile() {
		if(!myProfile.isDisplayed())
			clickElement(usermenuDropdown);
		clickElement(myProfile);
	}
	
	public void logoutOfSalesForce() {
		if(!logout.isDisplayed())
			clickElement(usermenuDropdown);
		clickElement(logout);
	}
	public void clickMySettings() {
		if(!mySettings.isDisplayed())
			clickElement(usermenuDropdown);
		clickElement(mySettings);
	}
	public void clickDeveloperConsole() {
		if(!developerConsole.isDisplayed())
			clickElement(usermenuDropdown);
		clickElement(developerConsole);
	}
	public void clickSwitchToLightning() {
		if(!switchToLightning.isDisplayed())
			clickElement(usermenuDropdown);
		clickElement(switchToLightning);
	}
	
	public List<WebElement> getMenuItemsElements(){
		return  userNavmenuItems.findElements(By.cssSelector("a"));
	}
	

	

}
