package com.training.tekarch.utilities;


import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.RandomStringUtils;

import static com.training.tekarch.utilities.Constants.USER_DIR;
import static com.training.tekarch.utilities.Constants.APPLICATION_PROPERTIES;
import static com.training.tekarch.utilities.Constants.DB_PROPERTIES;
public class CommonUtilities {
	
	
	public static String getproperty(String Key) throws IOException { 
		
	String sPath = USER_DIR+File.separator+APPLICATION_PROPERTIES;
	 FileInputStream fileinput = new FileInputStream(sPath);
	 Properties prop = new Properties();
	 prop.load(fileinput);
	 String value = prop.getProperty(Key);
	 return value;
	 
	}
	
	public static String getdbproperty(String Key) throws IOException { 
		
	String sPath = USER_DIR+File.separator+DB_PROPERTIES;
	 FileInputStream fileinput = new FileInputStream(sPath);
	 Properties prop = new Properties();
	 prop.load(fileinput);
	 String value = prop.getProperty(Key);
	 return value;
	 
	}
	
	/**
	* getRandomString
	* generate a alphnumeric String
	* @param size  of string
	* @return void
	*/
	public static String getRandomString(int size) {
		 int length = size;
		    boolean useLetters = true;
		    boolean useNumbers = true;
		    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		    return generatedString;

	}
	
	public static String getResourceFile(String fileName) {
		
		String url ="";
		File f = new File(url);
		return f.getAbsolutePath()+File.separator+"src"+File.separator+"test"+File.separator+"resources"
				+File.separator+fileName;
		
		
	}

	public static boolean checkFileExists(String regex) {
		String homeDirPath = FileUtils.getUserDirectoryPath()+File.separator+"Downloads";
		File dir = new File(homeDirPath);
		FileFilter fileFilter = new WildcardFileFilter(regex);
		int i =0;
		while( i<10) {
			File[] files = dir.listFiles(fileFilter);
			if(files.length==1)
				return true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public static void deleteFileInDownLoadDir(String regex) {
		String homeDirPath = FileUtils.getUserDirectoryPath()+File.separator+"Downloads";
		File dir = new File(homeDirPath);
		FileFilter fileFilter = new WildcardFileFilter(regex);
		File[] files = dir.listFiles(fileFilter);
		for(int i=0; i<files.length;i++ )
			files[i].delete();
	}
}
