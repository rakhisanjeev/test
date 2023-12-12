package com.training.tekarch.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.tekarch.base.BasePage;

public class NewAccountPage extends BasePage {

	public NewAccountPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver,Duration.ofSeconds(20L));
		actions = new Actions(driver);
	}

	
	@FindBy(id = "acc2")
	public WebElement accountName;
	
	@FindBy(id="acc6")
	public WebElement accountTypeCombobox;
	
	@FindBy(id="00N8Z000004OiM1")
	public WebElement customerPriorityCombobox;
	
	@FindBy(name="save")
	public WebElement saveNewAccountBtn;


	public void setAccountName(String name) {
		accountName.sendKeys(name);
	}
	public void clickAccountTypeCombobox(String selection) {
		selectOption(accountTypeCombobox, selection);

	}
	public void clickCustomerPriorityCombobox(String selection) {
		selectOption(customerPriorityCombobox, selection);

	}
	public void clickSaveNewAccountBtn() {
		clickElement(saveNewAccountBtn);
	}

}
