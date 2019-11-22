package com.restassured.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

public class Utility {
	
	private final String propertyFilePath= "config/config.properties";
	Properties properties;
	
	public Properties ReadPropertyFile(){
		 BufferedReader reader;
		 try {
			 reader = new BufferedReader(new FileReader(propertyFilePath));
			 properties = new Properties();
			 try {
				 	properties.load(reader);
				 	reader.close();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		 } 
		 return properties;
	 }
	
	public static String generateRandomAlphabetString(int length) {
		String generatedString = RandomStringUtils.randomAlphanumeric(10);
		return generatedString;
	}

}
