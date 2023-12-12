package com.training.tekarch.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.tekarch.base.BasePage;

public class EditViewPage extends BasePage {

	public EditViewPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
		actions = new Actions(driver);
	}
	
	
	@FindBy(id = "fname")
	public WebElement viewName;
	
	@FindBy(id="devname")
	WebElement viewUniqueName;

	@FindBy(name="save")
	public WebElement saveEditViewBtn;	
	
	@FindBy(xpath="//a[text()='Merge Accounts']")
	public WebElement mergeAccounts;	
		
	@FindBy(id = "fcol1")
	public WebElement fieldNameCombobox1;

	@FindBy(id = "fop1")
	public WebElement operatorCombobox1;

	@FindBy(id = "fval1")
	public WebElement fieldValue1;
	
	public void setViewName(String name) {
		typeElement(viewName, name);
	}
	public void setViewUniqueName(String name) {
		typeElement(viewUniqueName, name);
	}
	
	public void clickSaveView() {
		clickElement(saveEditViewBtn);
	}
	
	public void selectFieldNameCombobox1(String selection) {
		selectOption(fieldNameCombobox1, selection);
	}

	public void selectOperatorCombobox1(String selection) {
		selectOption(operatorCombobox1, selection);
	}

	public void setFieldValue1(String value) {
		typeElement(fieldValue1, value);
	}
	
	public String getViewName() {		
		return viewName.getAttribute("value");
	}

}
