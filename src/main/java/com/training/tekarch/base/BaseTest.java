package com.training.tekarch.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static Logger _log ; 
	private WebDriver driver;
	
	protected SoftAssert sAssert;
	protected WebDriverWait wait ;
	
	public WebDriver getDriver() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    return driver;
	}
		
	
	 public static void takeScreenShot(WebDriver driver) {
		  
		  TakesScreenshot screenshot = ((TakesScreenshot) driver);
		  File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		  
		  Date CurrentDate = new Date();
		  String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(CurrentDate);
		  
		  String fileSeperator = System.getProperty("file.separator");
		  String ScreenshotPath = System.getProperty("user.dir") +fileSeperator+"Screenshots";
		  String fileName = "Screenshot"+timeStamp+".png";
		  String filePath = ScreenshotPath+fileSeperator+fileName;
		  
		  File destFile = new File(filePath);
		  
		  try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	 }
	 
}
