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

public class MyProfilePage extends BasePage {
	public MyProfilePage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver,Duration.ofSeconds(20L));
		actions = new Actions(driver);
		
	}

	@FindBy(xpath = "//h3[contains(text(),'Contact')]//img[@title='Edit Profile']")
	public WebElement editBtn;

	@FindBy(xpath = "//h2[@id='contactInfoTitle']")
	public WebElement editProfilePopup;

	@FindBy(xpath = "//li[@id='aboutTab']/a")
	public WebElement aboutBtn;

	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement lastName;

	@FindBy(xpath = "//input[@value='Save All']")
	public WebElement saveAllBtn;

	@FindBy(xpath = "//a[@id='publisherAttachTextPost']")
	public WebElement postBtn;

	@FindBy(xpath = "//body")
	public WebElement postTextInput;

	@FindBy(xpath = "//input[@id='publishersharebutton']")
	public WebElement shareBtn;

	@FindBy(xpath = "//span[@class='feeditemtext cxfeeditemtext']/p")
	public List<WebElement> comments;

	@FindBy(xpath = "//a[@id='publisherAttachContentPost']")
	WebElement fileBtn;

	@FindBy(xpath = "//a[@id='chatterUploadFileAction']")
	WebElement fileUploadBtn;

	@FindBy(xpath = "//input[@id='chatterFile']")
	WebElement chooseFileBtn;

	@FindBy(xpath = "//a/span[@title='dummy']")
	public List<WebElement> postedFilesList;

	@FindBy(xpath = "//a[@title='More Actions']")
	public List<WebElement> moreActionOnPost;

	@FindBy(xpath = "//a[@title='Delete this post']")
	public List<WebElement> deleteThisPost;

	@FindBy(xpath = "//input[@value='OK']")
	public WebElement btnOK;

	@FindBy(xpath = "//a[@id='uploadLink']")
	WebElement addPhotoBtn;

	@FindBy(xpath = "//span[contains(@class,'chatter-avatarFull')]")
	WebElement chatPhoto;

	@FindBy(xpath = "//input[@class='fileInput']")
	WebElement choosePhotoBtn;

	@FindBy(xpath = "//input[@id='j_id0:uploadFileForm:uploadBtn']")
	WebElement saveBtn;

	@FindBy(xpath = "//input[@value='Save']")
	WebElement photoUploadBtn;

	@FindBy(xpath = "//a[@id='deletePhoto']")
	WebElement photoDeleteBtn;

	@FindBy(xpath = "//a//span[text()='Personal']")
	WebElement personal;

	@FindBy(xpath = "//a[@id='LoginHistory_font']")
	WebElement loginHistory;

	@FindBy(xpath = "//div[@class='pShowMore']/a")
	WebElement downloadLoginHistory;

	// @FindBy(id="RelatedUserLoginHistoryList_title")
	// WebElement loginDetail;

	@FindBy(id = "DisplayAndLayout_font")
	WebElement display;

	@FindBy(xpath = "//a[@id='CustomizeTabs_font']")
	WebElement customizeMyTabs;

	@FindBy(xpath = "//select[@id='p4']")
	WebElement customAppDropdown;

	@FindBy(xpath = "//select[@id='duel_select_0']")
	WebElement availableTabs;
	
	@FindBy(xpath="//img[@class='rightArrowIcon']")
	WebElement addToTabsBtn;
	
	@FindBy(xpath="//select[@id='duel_select_1']")
	WebElement selectedTabs;
	
	@FindBy(xpath="//input[@name='save']")
	WebElement saveToTabsBtn;
	
	@FindBy(xpath="//div[@id='tsidButton']")
	WebElement MenuDropdown;
	
	@FindBy(xpath="//div[@id='toolbar']//a[text()='Salesforce Chatter']")
	WebElement salesforceChatter;
	
	@FindBy(id="tabBar")
	WebElement tabBar;
	
	@FindBy(xpath="//a/img[@title='Remove']")
	WebElement removeFromSelectedTabs;
		
	@FindBy(id="EmailSetup_font")
	WebElement email;
	
	@FindBy(xpath="//a[@id='EmailSettings_font']")
	WebElement emailSettings;
	
	@FindBy(xpath="//input[@id='sender_name']")
	WebElement emailName;
	
	@FindBy(id="sender_email")
	WebElement emailAddress;
	
	@FindBy(id="auto_bcc1")
	WebElement automaticBcc;
	
	@FindBy(xpath="//input[@name='save']")
	WebElement saveMyEmailSettingsBtn;
	
	@FindBy(id="CalendarAndReminders_font")
	WebElement calendarandReminders;
	
	@FindBy(id="Reminders_font")
	WebElement activityReminders;
	
	@FindBy(id="testbtn")
	WebElement openTestReminderBtn;
	

	
	
	

	public void addPhoto() {

		actions.moveToElement(chatPhoto).perform();
		clickElement(addPhotoBtn);
	}

	public void clickAboutBtn() {
		clickElement(aboutBtn);
	}

	public void clickCustomizeMyTabs() {
		clickElement(customizeMyTabs);
	}

	public void clickDisplayLayout() {
		clickElement(display);
	}

	public void clickEditBtn() {
		clickElement(editBtn);
	}

	public void clickFileBtn() {
		clickElement(fileBtn);
	}

	public void clickLoginHistory() {
		clickElement(loginHistory);
	}

	public void clickPersonal() {
		clickElement(personal);
	}

	public void clickPostBtn() {
		clickElement(postBtn);
	}

	public void clickSaveAllBtn() {
		clickElement(saveAllBtn);
	}

	public void clickShareBtn() {
		clickElement(shareBtn);
	}

	public void clickUploadFileBtn() {
		clickElement(fileUploadBtn);
	}

	public void deleteAllComments() {
		int totalComments = moreActionOnPost.size() - 1;
		for (int i = totalComments; i >= 0; i--) {
			clickElement(moreActionOnPost.get(i));
			clickElement(deleteThisPost.get(i));
			clickElement(btnOK);
		}
	}

	public void deleteProfilePhoto() {
		actions.moveToElement(chatPhoto).perform();
		clickElement(photoDeleteBtn);
		clickElement(btnOK);

	}

	public void downloadLoginHistoryFile() {
		clickElement(downloadLoginHistory);
	}

	public String getLastName() {
		return lastName.getAttribute("value");
	}

	public void getPhoto(String path) {
		choosePhotoBtn.sendKeys(path);
	}

	public void postText(String str) {
		postTextInput.sendKeys(str);

	}

	public void selectFromAvailableTabs(String selection) {

		Select option = new Select(availableTabs);
		option.selectByVisibleText(selection);

	}

	public void selectFromCustomAppDropdown(String selection) {
		clickElement(customAppDropdown);
		Select option = new Select(customAppDropdown);
		//List<WebElement> optionsList = option.getOptions();
		//wait.until(ExpectedConditions.numberOfElementsToBeMoreThan())
		option.selectByVisibleText(selection);
		

	}

	public void setLastName(String str) {
		lastName.clear();
		lastName.sendKeys(str);
	}

	public void setPhoto() {
		clickElement(saveBtn);
	}

	public void uploadChatFile(String path) {
		chooseFileBtn.sendKeys(path);
	}

	public void uploadPhoto() {
		clickElement(photoUploadBtn);
	}
	
	public void addToSelectedTabs() {
		
		clickElement(addToTabsBtn);
	}
	
    public void saveInSelectedTabs() {
		
		clickElement(saveToTabsBtn);
	}
    
    public void selectSalesForceChatter() {
    	clickElement(MenuDropdown);
    	clickElement(salesforceChatter);
    }
   
   public List<WebElement> getListofTabBar( ) {
	   
	   //return tabBar.findElements(By.xpath("//li/a"));
	   return driver.findElement(By.id("tabBar")).findElements(By.xpath("//li/a"));
	   
   }
   
   public Boolean isElementInList(String value) {
	   List<WebElement> list =getListofTabBar();
	   
	  for(WebElement element : list ) {
		  System.out.print(element.getText());
		  System.out.print(element.getAttribute("value"));
		  if(value.equals(element.getText())) {
			  return true;
		  }
	  }
	  return false;
   }

    public void selectFromSelectedTabs(String selection) {
    		
    	selectOption(selectedTabs,selection);
   }

    public void removeFromSelectedTabs() {
	clickElement(removeFromSelectedTabs);	
    }
    
    public void selectEmail() {
    	clickElement(email);
    }
    
    public void selectEmailSettings() {
    	clickElement(emailSettings);
    }
    
    public void setEmailname(String name) { 
    	emailName.clear();
    	emailName.sendKeys(name);    	
    }
    
    public void setEmailAddress(String email) { 
    	emailAddress.clear();
    	emailAddress.sendKeys(email);    	
    }
    
    public void setBcc() {
		if (!automaticBcc.isSelected())
			clickElement(automaticBcc);
    }
    public void saveEmailSettings() {
    	clickElement(saveMyEmailSettingsBtn);
    }
    
    public void clickCalendarandReminder() {
    	clickElement(calendarandReminders);
    }
    public void clickActivityReminder() {
    	clickElement(activityReminders);
    }
    
    public void clickOpenTestReminder() {
    	clickElement(openTestReminderBtn);
    	
    }
    
    
    

}
