package com.training.tekarch.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.tekarch.base.BasePage;

public class MergeAccountsPage extends BasePage{
	
	public MergeAccountsPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver,Duration.ofSeconds(20L));
		actions = new Actions(driver);
	}
	
	@FindBy(xpath="//a[contains(text(), 'Merge Accounts')]")
	public WebElement mergeAccounts;
	
	@FindBy(id="srch")
	public WebElement accountName;
	
	@FindBy(name="srchbutton")
	public WebElement findAcctsBtn;	
	
	@FindBy(xpath="//table[@class='list']")
	public WebElement listTable;	
	
	@FindBy(name="goNext")
	public WebElement nextBtn;	
	
	@FindBy(xpath="//div[@class='ptRightTitle'] ")
	public WebElement subTitle;
	
	@FindBy(xpath="//input[@name='save']")
	public WebElement mergeBtn;
	
	@FindBy(xpath="//table[@class='list']//tr[2]/th[1]/a")
	public WebElement mergedAccountName;
	
	@FindBy(name="delete")
	public WebElement deleteBtn;

	public void clickMergeAccounts() {
		clickElement(mergeAccounts);
	}	
	public void enterAccountNumber(String acctName) {
		accountName.sendKeys(acctName);
	}
	public void clickFindAccounts() {
		clickElement(findAcctsBtn);
	}
	public Boolean checkIfNumberOfAccountsinListIsMorethanOne() {
		//find the columns in each row coumn index 1 is accounts

		List<WebElement> accList = listTable.findElements(By.xpath("//tr//th/input"));
		
		return accList.size()>1?true:false;
		
	}
	public void mergeTopTwoAccounts() {
		//find the columns in each row coumn index 1 is accounts
				List<WebElement> accList = listTable.findElements(By.xpath("//tr//th/input"));
				//unselect all accounts
				
				
				//select toptwo accounts
				if(accList.size()>1) {
					for(WebElement acct: accList) {
						unselectCheckBox(acct);
					}
					for(int i=0;i<2;i++) {
						selectCheckBox(accList.get(i));
					}
				clickElement(nextBtn);
				}
				else {
					_log.info("Number of account available in list is less than 2");
				}
	}
	
	public void clickMergeAccts() {		
		clickElement(mergeBtn);	
		driver.switchTo().alert().accept();
	}
	public void clickDeleteMergedAccount() {
		clickElement(mergedAccountName);
		clickElement(deleteBtn);
		driver.switchTo().alert().accept();
	}

}
