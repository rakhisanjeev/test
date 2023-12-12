package com.training.tekarch.pages;

import java.time.Duration;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.tekarch.base.BasePage;

public class ReportPage extends BasePage {

	public ReportPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
		actions = new Actions(driver);
	}

	@FindBy(xpath = "//a[text()='Accounts with last activity > 30 days']")
	public WebElement acctsWithLastActivityMoreThanaMonth;

	@FindBy(id = "ext-gen148")
	public WebElement dateFieldComboBox;

	@FindBy(xpath = "//div[text()='Created Date']")
	public WebElement createdDate;

	@FindBy(id = "ext-gen152")
	public WebElement fromDate;

	@FindBy(xpath = "//a[contains(@title,'Previous Month')]")
	public List<WebElement> prevMonth;

	@FindBy(xpath = "//table[@class='x-date-inner']//tbody")
	public WebElement dateTable;

	@FindBy(id = "ext-gen154")
	public WebElement toDate;

	@FindBy(xpath = "//a[contains(@title,'Next Month')]")
	public List<WebElement> nextMonth;

	@FindBy(xpath = "//button[text()='Today']")
	public List<WebElement> today;
	
	@FindBy(xpath="//button[@id='ext-gen49']")
	public WebElement saveBtn;
	
	@FindBy(xpath="//input[@name='reportName']")
	public WebElement reportName;
	
	@FindBy(xpath="//input[@name='reportDevName']")
	public WebElement reportUniqueName;
	
	@FindBy(xpath="//button[normalize-space(text())='Save and Run Report']")
	public WebElement saveandRunReportBtn;
	
	@FindBy(xpath="//h2[contains(text(), 'Report Generation Status')]")
	public WebElement header;
	
	@FindBy(name="delrep")
	public WebElement delRepBtn;

	public void clickAccountswithLastActivitygreaterthanamonth() {
		clickElement(acctsWithLastActivityMoreThanaMonth);
	}

	public void selectDateField() {
		clickElement(dateFieldComboBox);
	}

	public void selectCreatedDate() {
		clickElement(createdDate);
	}

	public void clickFromDate() {
		clickElement(fromDate);
	}

	public void clickPrevMonth() {
		clickElement(prevMonth.get(0));
	}

	public void selectFromDate() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String day1 = String.valueOf(day);
		clickElement(fromDate);
		clickElement(prevMonth.get(0));

		List<WebElement> listColumns = dateTable.findElements(By.xpath("//td//span"));
		for (WebElement cell : listColumns) {
			if (cell.getText().equals(day1)) {
				cell.click();
				break;
			}
		}

		
	}

	public void clickToDate() {
		clickElement(toDate);
	}

	public void clickNextMonth() {
		if(nextMonth.size()>1)
			clickElement(nextMonth.get(1));
		else
			clickElement(nextMonth.get(0));	
	}

	public void selectToDateToday() {
		if(today.size()>1)
			clickElement(today.get(1));
		else
			clickElement(today.get(0));
	}

	public void selectFromDateToday() {
		clickElement(today.get(0));
	}
	
	public void clickSaveBtn( ) {
		clickElement(saveBtn);
	}
	public void setReportName(String name) {
		typeElement(reportName, name);
	}
	public void setReportUniqueName(String name) {
		typeElement(reportUniqueName, name);
	}
	public void clickSaveRunReportBtn( ) {
		clickElement(saveandRunReportBtn);
	}
	
	public void clickDelRepBtn( ) {
		clickElement(delRepBtn);
		driver.switchTo().alert().accept();
	}
}
