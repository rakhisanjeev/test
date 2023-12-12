package com.training.tekarch.test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.pages.LoginPage;
import com.training.tekarch.base.BaseTest;
import com.training.tekarch.pages.AccountsPage;
import com.training.tekarch.pages.EditViewPage;
import com.training.tekarch.pages.HomePage;
import com.training.tekarch.pages.MergeAccountsPage;
import com.training.tekarch.pages.NewAccountPage;
import com.training.tekarch.pages.ReportPage;
import com.training.tekarch.utilities.CommonUtilities;

public class AccountsTest extends BaseTest {
	WebDriver driver;
	com.salesforce.pages.LoginPage loginPage;
	HomePage homePage;
	AccountsPage accountsPage;
	EditViewPage editViewPage;
	String url;
	String username;
	String password;
	

	CommonUtilities common = new CommonUtilities();
	private static String createdViewName;

	@BeforeMethod
	public void beforeMethod() throws IOException {
		driver = getDriver();
		
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		accountsPage = new AccountsPage(driver);
		editViewPage= new EditViewPage(driver);
				
		url = CommonUtilities.getproperty("url");
		username = CommonUtilities.getproperty("userName");
		password = CommonUtilities.getproperty("password");
		sAssert = new SoftAssert();
		driver.get(url);
		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
		_log = Logger.getLogger(UserMenuDropDownTest.class.getName());
	}

	@Test
	public void TC11_verifyCreateNewAccount() {
		
		/* Step 1
		 * Launch and Login 	Launch https://www.login.salesforce.com 
		 * and provide positive <username> and <password> data to SalesForce Application.
		 * SalesForce login page is launched and application home page is logged in with correct username.
		 */
		
		String actualTitle = loginPage.getPageTitle();
		sAssert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		
		loginPage.loginSalesForce(username,password);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualName =homePage.usermenuDropdown.getText();
		String expectedName = "Rakhi Sinha";
		// check if the homepage is launched with the correct username
		sAssert.assertEquals(actualName, expectedName);
		
		
		accountsPage.clickAccountsTab();
		String expectedTitle = "Accounts: Home ~ Salesforce - Developer Edition";
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='save']")));
		sAssert.assertEquals(driver.getTitle(), expectedTitle);
		
		accountsPage.clickCreatenewAccount("Account");
		
		NewAccountPage newAccountPage = new NewAccountPage(driver);
		String accountName= CommonUtilities.getRandomString(10); 
		newAccountPage.setAccountName(accountName);
		newAccountPage.clickAccountTypeCombobox("Technology Partner");
		newAccountPage.clickCustomerPriorityCombobox("High");
		newAccountPage.clickSaveNewAccountBtn();
		String expectedTitle1="Account: "+accountName+ "~ Salesforce - Developer Edition";
		sAssert.assertEquals(driver.getTitle(), expectedTitle1);		
	}
	
	@Test
	public void TC12_verifyCreateNewView() {
		
		/* Step 1
		 * Launch and Login 	Launch https://www.login.salesforce.com 
		 * and provide positive <username> and <password> data to SalesForce Application.
		 * SalesForce login page is launched and application home page is logged in with correct username.
		 */
		
		String actualTitle = loginPage.getPageTitle();
		sAssert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		
		loginPage.loginSalesForce(username,password);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualName =homePage.usermenuDropdown.getText();
		String expectedName = "Rakhi Sinha";
		// check if the homepage is launched with the correct username
		sAssert.assertEquals(actualName, expectedName);
		
		
		accountsPage.clickAccountsTab();
		String expectedTitle = "Accounts: Home ~ Salesforce - Developer Edition";
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='save']")));
		sAssert.assertEquals(driver.getTitle(), expectedTitle);		
		
		accountsPage.clickCreateNewView();
		createdViewName= CommonUtilities.getRandomString(7); 
		editViewPage.setViewName(createdViewName);
		editViewPage.setViewUniqueName(createdViewName);
		editViewPage.clickSaveView();
		wait.until(ExpectedConditions.visibilityOf(accountsPage.createNew));
		accountsPage = new AccountsPage(driver);
		String actualViewName= accountsPage.getSelectedAccountFromDropdown();
		
		sAssert.assertEquals(actualViewName, createdViewName);
		
		
		accountsPage.deleteSelectedView();
		sAssert.assertAll();
	}
	
	@Test
	public void TC13_VerifyEditView() {
		_log = Logger.getLogger(UserMenuDropDownTest.class.getName());
		/* Step 1
		 * Launch and Login 	Launch https://www.login.salesforce.com 
		 * and provide positive <username> and <password> data to SalesForce Application.
		 * SalesForce login page is launched and application home page is logged in with correct username.
		 */
		
		String actualTitle = loginPage.getPageTitle();
		sAssert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		
		loginPage.loginSalesForce(username,password);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualName =homePage.usermenuDropdown.getText();
		String expectedName = "Rakhi Sinha";
		// check if the homepage is launched with the correct username
		Assert.assertEquals(actualName, expectedName);
		
		
		
		createNewView();
		_log.info("view Created successfully with name "+ createdViewName);
		String expectedTitle = "Accounts ~ Salesforce - Developer Edition";
	//	driver.navigate().refresh();
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='save']")));
		sAssert.assertEquals(driver.getTitle(), expectedTitle);
		
		//accountsPage.selectAccountView(createdViewName);
		//String actualViewAcctName= accountsPage.getSelectedAccountFromDropdown();
		//sAssert.assertEquals(actualViewAcctName, createdViewName);
		accountsPage.clickEditBtn();
		_log.info("Clicked Edit Btn");
		//we are now at edit view page
		wait.until(ExpectedConditions.visibilityOf(editViewPage.viewName));
		String actualViewName = editViewPage.getViewName();
		sAssert.assertEquals(actualViewName, createdViewName);
		String viewNewName = CommonUtilities.getRandomString(7); 
		editViewPage.setViewName(viewNewName);
		
		editViewPage.selectFieldNameCombobox1("Account Name");
		editViewPage.selectOperatorCombobox1("contains");
		editViewPage.setFieldValue1("a");
		
		editViewPage.clickSaveView();
		
		actualViewName= accountsPage.getSelectedAccountFromDropdown();
		
		sAssert.assertEquals(actualViewName, viewNewName);
		
		List<WebElement> accountlist = accountsPage.getAccountNameElementList();
		for(WebElement acc : accountlist) {
			String actualActName =acc.getText();
			//since condition is case insensitive, 
			//converting account name to lower case
			actualActName =actualActName.toLowerCase();
			sAssert.assertTrue(actualActName.contains("a"));		
		}
		
		sAssert.assertAll();
		
		accountsPage.deleteSelectedView();
		
		
	}
	@Test
	public void TC14_verifyMergeAccountsFunctionality() {
		
		/* Step 1
		 * Launch and Login 	Launch https://www.login.salesforce.com 
		 * and provide positive <username> and <password> data to SalesForce Application.
		 * SalesForce login page is launched and application home page is logged in with correct username.
		 */
		
		String actualTitle = loginPage.getPageTitle();
		sAssert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		
		loginPage.loginSalesForce(username,password);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualName =homePage.usermenuDropdown.getText();
		String expectedName = "Rakhi Sinha";
		// check if the homepage is launched with the correct username
		sAssert.assertEquals(actualName, expectedName);
		
		
		accountsPage.clickAccountsTab();
		String expectedTitle = "Accounts: Home ~ Salesforce - Developer Edition";
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='save']")));
		sAssert.assertEquals(driver.getTitle(), expectedTitle);
		MergeAccountsPage mergeAccountsPage = new MergeAccountsPage(driver);
	    mergeAccountsPage.clickMergeAccounts();
	    mergeAccountsPage.enterAccountNumber("ABC Holding");
	    mergeAccountsPage.clickFindAccounts();
	    Boolean acctsGreaterThanOne = mergeAccountsPage.checkIfNumberOfAccountsinListIsMorethanOne();
	    System.out.println(acctsGreaterThanOne);
		sAssert.assertTrue(acctsGreaterThanOne);
		mergeAccountsPage.mergeTopTwoAccounts();
		String actualSubTitle= mergeAccountsPage.subTitle.getText();
		String expectedSubTitle="Step 2 of 2";
		sAssert.assertEquals(actualSubTitle, expectedSubTitle);
		mergeAccountsPage.clickMergeAccts();
		driver.navigate().refresh();
		String expectedPageTitle="Accounts: Home ~ Salesforce - Developer Edition";
		sAssert.assertEquals(driver.getTitle(), expectedPageTitle);
		String actualMergeName= mergeAccountsPage.mergedAccountName.getText();
		String expectedMergeName= "ABC Holding";
		sAssert.assertEquals(actualMergeName, expectedMergeName);
	   
	    mergeAccountsPage.clickDeleteMergedAccount();
	    sAssert.assertAll();
	    
	}
	
	@Test
	public void TC15_verifyCreateAccountsReport() {
		
		/* Step 1
		 * Launch and Login 	Launch https://www.login.salesforce.com 
		 * and provide positive <username> and <password> data to SalesForce Application.
		 * SalesForce login page is launched and application home page is logged in with correct username.
		 */
		
		String actualTitle = loginPage.getPageTitle();
		sAssert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		
		loginPage.loginSalesForce(username,password);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualName =homePage.usermenuDropdown.getText();
		String expectedName = "Rakhi Sinha";
		// check if the homepage is launched with the correct username
		sAssert.assertEquals(actualName, expectedName);
		
		
		accountsPage.clickAccountsTab();
		String expectedTitle = "Accounts: Home ~ Salesforce - Developer Edition";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='save']")));
		sAssert.assertEquals(driver.getTitle(), expectedTitle);
		
		ReportPage reportPage= new ReportPage(driver);
		reportPage.clickAccountswithLastActivitygreaterthanamonth();
		String expectedPageTitle ="Unsaved Report ~ Salesforce - Developer Edition";
		sAssert.assertEquals(driver.getTitle(), expectedPageTitle);		
		reportPage.selectDateField();
		reportPage.selectCreatedDate();
		reportPage.clickFromDate();
		reportPage.clickPrevMonth();
		reportPage.selectFromDate();
		reportPage.clickToDate();
		reportPage.selectToDateToday();
		reportPage.clickSaveBtn();
		
		String  randomString = "Rep0"+ CommonUtilities.getRandomString(5);
		reportPage.setReportName(randomString);
		String  randomString1 = "RepUniq0"+ CommonUtilities.getRandomString(5);
		reportPage.setReportUniqueName(randomString1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reportPage.reportUniqueName.click();
		reportPage.clickSaveRunReportBtn();
		String expectedReportTitle= randomString +" ~ Salesforce - Developer Edition";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='"+randomString+"']")));
		sAssert.assertEquals(driver.getTitle(), expectedReportTitle);
		reportPage.clickDelRepBtn();
		
		
		//clean up
		// click action item -- //span[@class='actionItems']
		// click delete --- span[text()='Delete']
		//click ok on messagebox
		
		
		sAssert.assertAll();
		
	}
		
	
	
	@AfterMethod
	public void teardown() {
		takeScreenShot(driver);
		driver.close();
	}
	
	public void createNewView() {
		accountsPage.clickAccountsTab();
		//String expectedTitle = "Accounts: Home ~ Salesforce - Developer Edition";
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='save']")));
				
		
		accountsPage.clickCreateNewView();
		createdViewName= CommonUtilities.getRandomString(7); 
		editViewPage.setViewName(createdViewName);
		editViewPage.setViewUniqueName(createdViewName);
		editViewPage.clickSaveView();
		wait.until(ExpectedConditions.visibilityOf(accountsPage.createNew));
		
		
		
		
	}

}
