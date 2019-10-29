package com.epam.hooks;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.epam.webdriver.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * @author Siddu
 */
public class Hooks {
	//Hooks present in this class are not being used 
	Logger logger = Logger.getLogger(Hooks.class.getName());
	
	//WebDriver webDriver;

	@Before
	public void setUp(){
		logger.info("Before hook in Hooks class");
		//webDriver = WebDriverFactory.getInstance().getWebDriver();
	}
	
	@After
	public void tearDown(){
		logger.info("After hook in Hooks class");
		//webDriver.quit();
	}
}
