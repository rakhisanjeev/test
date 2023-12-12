package com.training.tekarch.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.tekarch.base.BasePage;

public class AccountsPage extends BasePage{
	
	public AccountsPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
		actions = new Actions(driver);
	}
	
	

	@FindBy(id="Account_Tab")
	public WebElement accountsTab;
	
	@FindBy(xpath="//div[@id='tsidButton']")
	public WebElement menuDropdown;
	
	@FindBy(xpath="//div[@id='toolbar']//a[text()='Sales']")
	public WebElement sales;
	
	@FindBy(id="createNew")
	public WebElement createNew;
	
	@FindBy(xpath="//div[@id='createNewMenu']/a")
	public WebElement menuItems;
	
	@FindBy(xpath="//form[@id='filter_element']//a[text()='Edit']")
	public WebElement editAcctView;
	
	@FindBy(xpath="//td[@class='x-grid3-td-Name inlineEditWriteHidden']//a")
	public List<WebElement> accountNameList;
	
	@FindBy(xpath="//a[text()='Merge Accounts']")
	public WebElement mergeAccountsLink;
	

	@FindBy(xpath="//form[@id='filter_element']//a[text()='Create New View']")
	public WebElement createNewView;
	
	@FindBy(xpath="//select[@name='fcf']")
	public WebElement selectAccttoView;
	
	@FindBy(xpath="//a[text()='Edit']")
	public WebElement editBtn;
	
	@FindBy(xpath="//a[text()='Delete']")
	public WebElement deleteBtn;
	
	@FindBy(xpath = "//input[@value='OK']")
	public WebElement btnOK;

	@FindBy(xpath="//select[@class='title']")
	public WebElement viewList;
	
	
	
	public void clickAccountsTab( ) {
		clickElement(accountsTab);
	}
		
	public void clickCreatenewAccount(String selection) {
		clickElement(createNew);
		List<WebElement> menuList = driver.findElements
				(By.xpath("//div[@id='createNewMenu']//a"));
		for(WebElement ele: menuList) {
			wait.until(ExpectedConditions.visibilityOf(ele));
			if(ele.getText().equals(selection)) {
				clickElement(ele);
			}
		}

	}
	public String viewListName( ) {
		Select list = new Select(viewList);
		return list.getFirstSelectedOption().getText();
	}
	
	public  String getSelectedAccountFromDropdown( ) {
		clickElement(selectAccttoView);
		Select list = new Select(selectAccttoView);
		
		return list.getFirstSelectedOption().getText();
		
	}

	public void clickCreateNewView( ) {
		clickElement(createNewView);
	}
	public void clickEditBtn( ) {
		clickElement(editBtn);
	}
	
	
	public List<WebElement> getAccountNameElementList() {
		return accountNameList;
	}

	public void deleteSelectedView() {
		clickElement(deleteBtn);
		driver.switchTo().alert().accept();
		
	}
	
	

	

}
