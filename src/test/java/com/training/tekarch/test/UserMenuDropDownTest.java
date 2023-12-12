package com.training.tekarch.test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.salesforce.pages.LoginPage;
import com.training.tekarch.base.BaseTest;
import com.training.tekarch.pages.HomePage;

import com.training.tekarch.pages.MyProfilePage;
import com.training.tekarch.utilities.CommonUtilities;


public class UserMenuDropDownTest extends BaseTest {
	
	WebDriver driver;
	
	LoginPage loginPage;
	HomePage homePage;
	MyProfilePage myProfilePage;

	String url;
	String username;
	String password;
	
	
	@BeforeMethod
	public void beforeMethod() throws IOException {
		
		driver = getDriver();
		
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		myProfilePage = new MyProfilePage(driver);
		
		url = CommonUtilities.getproperty("url");
		username = CommonUtilities.getproperty("userName");
		password = CommonUtilities.getproperty("password");
	
		driver.get(url);
		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20L));
		sAssert = new SoftAssert();
		_log = Logger.getLogger(UserMenuDropDownTest.class.getName());

	}


	@Test
	public void TC06_UserMenuDropdownOptions() {
		
		/* Step 1
		 * Launch and Login 	Launch https://www.login.salesforce.com 
		 * and provide positive <username> and <password> data to SalesForce Application.
		 * SalesForce login page is launched and application home page is logged in with correct username.
		 */
		
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);

		
		loginPage.loginSalesForce(username,password);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualName =homePage.usermenuDropdown.getText();
		String expectedName = "Rakhi Sinha";
		// check if the homepage is launched with the correct username
		Assert.assertEquals(actualName, expectedName);
		
		/* Step 2
		 * Check for user menu for <username> drop down	After salesforce application launch check
		 *  for the user menu drop down on the web page	User menu drop down shpuld be available
		 */
		helperVerifyMenuDropdown();
		sAssert.assertAll();	
	}
	@Test
	public void TC07_ValidateMyProfile() {
		
		String surname="Shrivastav";
				
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);
		//login salesforce successfully
		loginPage.loginSalesForce(username,password);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualName =homePage.usermenuDropdown.getText();
		String expectedName = "Rakhi Sinha";
		// check if the homepage is launched with the correct username
		Assert.assertEquals(actualName, expectedName);
		// Verify dropdown menu
		helperVerifyMenuDropdown();
		
		homePage.selectMyProfile();
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualProfileTitle = homePage.getPageTitle();
		String expectedProfileTitle="User: Rakhi Sinha ~ Salesforce - Developer Edition"; 
		sAssert.assertEquals(actualProfileTitle, expectedProfileTitle);
		//click Edit profile button 
		myProfilePage.clickEditBtn();
		wait.until(ExpectedConditions.visibilityOf(myProfilePage.editProfilePopup));
		
		
		String expectedText= "Edit Profile";
		
		//get Title of dialog( Edit Profile)
		String actualText= myProfilePage.editProfilePopup.getText();
		sAssert.assertEquals(actualText, expectedText);
		
		//switch to iframe in Edit profile dialog
		driver.switchTo().frame("contactInfoContentId");
		
		//click about and enter lastname and save
		
		myProfilePage.clickAboutBtn();
		
		//get the last name in a variable from about in Edit dialog box
		String lastName = myProfilePage.getLastName();
		//set the last name
		myProfilePage.setLastName(surname);
		myProfilePage.clickSaveAllBtn();
		
		//dialog closed, switch to default content
		
		driver.switchTo().defaultContent();
		String expectedUpdatedTitle = "User: Rakhi Shrivastav ~ Salesforce - Developer Edition";
		wait.until(ExpectedConditions.titleContains(surname));
		String actualUpdatedTitle = driver.getTitle();
		sAssert.assertEquals(actualUpdatedTitle, expectedUpdatedTitle);
		
		//reverting the changes in last name after assert
		
		myProfilePage.clickEditBtn();
		wait.until(ExpectedConditions.visibilityOf(myProfilePage.editProfilePopup));
		driver.switchTo().frame("contactInfoContentId");
		myProfilePage.clickAboutBtn();
		myProfilePage.setLastName(lastName);
		myProfilePage.clickSaveAllBtn();
		driver.switchTo().defaultContent();
		//done
		
		//Click on Post link
		myProfilePage.deleteAllComments();
		myProfilePage.clickPostBtn();
	
		WebElement iframe = driver.findElement(By.xpath("//iframe[contains(@title,'Rich Text')]"));
		driver.switchTo().frame(iframe);
		wait.until(ExpectedConditions.visibilityOf(myProfilePage.postTextInput));

		String  randomString = "Hello, "+ CommonUtilities.getRandomString(20);
		//String expectedComment= myProfilePage.postText(randomString);
		
		myProfilePage.postText(randomString);
		driver.switchTo().defaultContent();	
		int prevSize = myProfilePage.comments.size(); 
		
		myProfilePage.clickShareBtn();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().refresh();
		
		//By locator = By.xpath("//span[@class='feeditemtext cxfeeditemtext']/p");
		//wait.until(ExpectedConditions.numberOfElementsToBe(locator, prevSize+1));
		myProfilePage.comments = driver.findElements(By.xpath("//span[@class='feeditemtext cxfeeditemtext']/p"));
		String actualComment=myProfilePage.comments.get(0).getText();
		sAssert.assertEquals(actualComment, randomString);	
		myProfilePage.deleteAllComments();
		//click on file link
		
		myProfilePage.clickFileBtn();
		myProfilePage.clickUploadFileBtn();
		prevSize = myProfilePage.postedFilesList.size();
		String filePath = CommonUtilities.getResourceFile("dummy.txt");
		myProfilePage.uploadChatFile(filePath);
	
		myProfilePage.clickShareBtn();		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.navigate().refresh();
		myProfilePage.postedFilesList = driver.findElements(By.xpath("//a/span[@title='dummy']"));
		wait.until(ExpectedConditions.visibilityOf(myProfilePage.postedFilesList.get(0)));
		
		String actualFileName=myProfilePage.postedFilesList.get(0).getText();
		sAssert.assertEquals(actualFileName, "dummy");	
		myProfilePage.deleteAllComments();	
		
		//Add photo link
		
		myProfilePage.addPhoto();
		driver.switchTo().frame("uploadPhotoContentId");
		String photoFilePath = CommonUtilities.getResourceFile("flowers.jpg");
		myProfilePage.getPhoto(photoFilePath);
		myProfilePage.setPhoto();
		myProfilePage.uploadPhoto();
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myProfilePage.deleteProfilePhoto();
		sAssert.assertAll();

	}
	@Test
	public void TC08_ValidateMySettings() {
		_log = Logger.getLogger(UserMenuDropDownTest.class.getName());  
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, loginPage.EXPECTED_LOGINPAGE_TITLE);
		_log.info(actualTitle);
		
		loginPage.loginSalesForce(username,password);
		
		wait.until(ExpectedConditions.visibilityOf(homePage.usermenuDropdown));
		String actualName =homePage.usermenuDropdown.getText();
		String expectedName = "Rakhi Sinha";
		// check if the homepage is launched with the correct username
		sAssert.assertEquals(actualName, expectedName);
		/* Step 1
		 * user menu for <username> drop down is selected
		 * Drop down with "My profile", "My Settings", "Developer Console","Logout" is displayed
		 */
		helperVerifyMenuDropdown();
		/*STep 2
		 * Click on personal link and select Login History link. 
		 * Click on Download login  history link.
		 */
		homePage.clickMySettings();
		String expectedUrl="https://tekarch-12f-dev-ed.my.salesforce.com/ui/setup/Setup?setupid=PersonalSetup";
		sAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		myProfilePage.clickPersonal();
		myProfilePage.clickLoginHistory();
		//delete file, if any before downloading
		CommonUtilities.deleteFileInDownLoadDir("LoginHistory*.csv" );
		//download file
		myProfilePage.downloadLoginHistoryFile();
		
		//Login history is displayed and the data is downloaded in .csv format.
		sAssert.assertTrue(CommonUtilities.checkFileExists("LoginHistory*.csv" ));
		/*Step 3
		 * Click on Display & Layout link and select Customize My Tabs link.
		 * Select "Salesforce Chatter" option from custom App: 
		 * drop down. Select Reports tab from Available Tabs list. Click on >(Add) button. 
		 */
		myProfilePage.clickDisplayLayout();
		myProfilePage.clickCustomizeMyTabs();
		myProfilePage.selectFromCustomAppDropdown("Salesforce Chatter");
		myProfilePage.selectFromAvailableTabs("Reports");
		myProfilePage.addToSelectedTabs();
		myProfilePage.saveInSelectedTabs();
		//myProfilePage.selectSalesForceChatter();
		sAssert.assertTrue(myProfilePage.isElementInList("Reports"));
		//reverting the changes
		
		
		myProfilePage.clickDisplayLayout();
		myProfilePage.clickCustomizeMyTabs();
		//myProfilePage.selectFromCustomAppDropdown("Salesforce Chatter");
		myProfilePage.selectFromSelectedTabs("Reports");
		myProfilePage.removeFromSelectedTabs();
		myProfilePage.saveInSelectedTabs();
		/*Step 4
		 * Provide <EmailName> in Email Name field, <EmailAddress> in Email
		 * Address field, Select automatic BCC radio button and click on save button
		 * 
		 */
		myProfilePage.selectEmail();
		myProfilePage.selectEmailSettings();
		myProfilePage.setEmailname("Rakhi Sinha");
		myProfilePage.setEmailAddress("rakhisanjeev@yahoo.co.uk");
		myProfilePage.setBcc();
		myProfilePage.saveEmailSettings();
		String expectedTitle="My Email Settings ~ Salesforce - Developer Edition";
		sAssert.assertEquals(driver.getTitle(), expectedTitle);
		
		/* Step 5.
		 * Click on Calendar & Reminders link and click on Activity Reminders link.
		 * On Reminders page click on Open a test Reminder button.
		 */
		
		myProfilePage.clickCalendarandReminder();
		myProfilePage.clickActivityReminder();
		myProfilePage.clickOpenTestReminder();
		ArrayList<String> handles =myProfilePage.getWindowHandles();
		driver.switchTo().window(handles.get(1));
		// checktitle of popup contains reminder
		String title = driver.getTitle();
		
		sAssert.assertTrue(title.contains("Reminders"));
		driver.close();
		
		//click close button
				
		//after closing popup 
		driver.switchTo().window(handles.get(0));		
		
		_log.debug("Test Completed");
		sAssert.assertAll();	
		
	}
	@Test
	public void TC09_verifyDeveloperConsole() {
	  
		loginPage.loginSalesForce(username,password);
		
		/* Step 1
		 * user menu for <username> drop down is selected	
		 * Drop down with "My profile", "My Settings", "Developer Console",
		 * "Logout" is displayed
		 */
		helperVerifyMenuDropdown();
		/* Step 2
		 * Click on Developer Console link from the drop down
		 * 	Force.com Developer Console window is displayed
		 */
			homePage.clickDeveloperConsole();
			
			ArrayList<String> handles =myProfilePage.getWindowHandles();
			driver.switchTo().window(handles.get(1));
			// checktitle of popup contains reminder
			String title = driver.getTitle();
			
			sAssert.assertTrue(title.equals("Developer Console"));
			
			
		/* Step 3
		 * Click on close button the developer console browser	
		 * Force.com Developer Console window is closed
		 */
			//click close button
			driver.close();	
			//after closing popup 
			driver.switchTo().window(handles.get(0));
		
		
	}
	
	@Test
	public void TC10_verifyLogoutOption() {
		loginPage.loginSalesForce(username,password);
		/* Step 1
		 * Select user menu for <username> drop down[TC01]
		 * user menu for <username> drop down is selected
		 * Drop down with "My profile", "My Settings", "Developer Console","Logout" is displayed
		 */
		helperVerifyMenuDropdown();
		/*Step 2
		 * Click on Logout option from the drop down
		 * 	Click on Logout option from the drop down	
		 * Logout  of current sales force application  
		 * and https://login.salesforce.com/ page is displayed.
		 */
		homePage.logoutOfSalesForce();
		String expectedUrl="https://tekarch-12f-dev-ed.my.salesforce.com/";
		sAssert.assertEquals(driver.getCurrentUrl(), expectedUrl);
				
		
	}
	
	@AfterMethod
	public void teardown() {
		takeScreenShot(driver);		
		driver.quit();
	}
	
	public void helperVerifyMenuDropdown() {
		_log.info("Menu options checking.");
		homePage.clickUserMenuDropDown();
		
		ArrayList<String> expectedMenuItems = new ArrayList<String>();
		/* 
		 * Click on the user menu for <username> drop down	Click on the 
		 * user menu for <username> drop down	Drop down with "My profile", 
		 * "My Settings", "Developer Console","Logout" , 
		 * "Switching to lightning Experience" is displayed
		 */
		expectedMenuItems.add("My Profile");
		expectedMenuItems.add("My Settings");
		expectedMenuItems.add("Developer Console");
		expectedMenuItems.add("Switch to Lightning Experience");
		expectedMenuItems.add("Logout");
		
		int i = 0;
		
		List<WebElement> userNavItems = homePage.getMenuItemsElements();
		
		sAssert.assertEquals(userNavItems.size(), expectedMenuItems.size());
		
		for (WebElement item : userNavItems) {
			String actualMenuName = item.getText();
			sAssert.assertEquals(actualMenuName, expectedMenuItems.get(i));
			i++;
		}
		_log.info("Menu options checked");
		
	}
}
