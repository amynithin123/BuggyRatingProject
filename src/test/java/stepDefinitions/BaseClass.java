package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import pageObjects.HomePage;



public class BaseClass {

	public WebDriver driver;
	public static Logger logger;
	public Properties configProp;
	public LoginPage lp;
	public HomePage hp;
	


	// Created for generating random string for unique email id
	public static String randomString() {

		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}

	// Created for generating random numbers
	public static String randomNumbers() {

		String generatedNo = RandomStringUtils.randomNumeric(10);
		return (generatedNo);
	}
}