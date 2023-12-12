package com.training.tekarch.base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public static Logger _log ;  
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions actions;
	
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElement(WebElement element,int time) {
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(10L));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public String getPageTitle() {
				return driver.getTitle();
	}
	
	/**
	   * clickElementLocatedBy
	   * wait for element to be clickable before clicking
	   * @param by  (By class object)
	   * 
	   * @return void
	   */
	public void clickElementLocatedBy(By by) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		wait.until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
		
	}
	/**
	   * clickElement
	   * wait for element to be clickable before clicking
	   * @param element WebElement
	   * 
	   * @return void
	   */
public void clickElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		
		wait.until(ExpectedConditions.elementToBeClickable(element));
		if(element.isEnabled())
			element.click();
		
	}

/**
* typeElementLocatedBy
* wait for element to be visible before typing
* @param by  
* @param text String to be typed
* @return void
*/
public void typeElementLocatedBy(By by, String text) {
	
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	driver.findElement(by).clear();
	driver.findElement(by).sendKeys(text);
	
}
/**
* typeElement
* wait for element to be visible before typing
* @param element WebElement
* @param text String to be typed
* @return void
*/
public void typeElement(WebElement element, String text) {
	
	wait.until(ExpectedConditions.visibilityOf(element));
	element.clear();
	element.sendKeys(text);
	
}
public ArrayList<String> getWindowHandles( ) {
	ArrayList<String> list = new ArrayList<String>();
	wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	Iterator<String> windowIterator  =  driver.getWindowHandles().iterator();
	while(windowIterator.hasNext()) {
		list.add(windowIterator.next());
	}
	return list;
	
	
}

public void selectOption(WebElement combobox, String selection) {
	clickElement(combobox);
	Select option = new Select(combobox);
	option.selectByVisibleText(selection);	
}
public void selectCheckBox(WebElement chkBox) {
	if(!chkBox.isSelected())
		chkBox.click();
}
public void unselectCheckBox(WebElement chkBox) {
	if(chkBox.isSelected())
		chkBox.click();
	
}
	
}
