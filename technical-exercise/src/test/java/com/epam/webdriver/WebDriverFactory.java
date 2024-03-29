package com.epam.webdriver;
/**
 * @author Siddu
 */
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.epam.utils.PropertiesReaderUtil;


public class WebDriverFactory {
	//singleton webdriver instance
	
	Logger logger = Logger.getLogger(WebDriverFactory.class.getName());

	private static WebDriverFactory webDriverFactory = new WebDriverFactory();

	private WebDriver webDriver;

	private WebDriverFactory() {
		PropertiesReaderUtil propertiesReaderUtil = PropertiesReaderUtil.getInstance();// Loading
																						// properties
		String browserName = System.getProperty("browser_name");
		logger.info("Browser :" + browserName);
		if (browserName.equalsIgnoreCase("FIREFOX")) {
			/*DesiredCapabilities capabilities = DesiredCapabilities.firefox(); 
	        capabilities.setCapability("browser.privatebrowsing.autostart", true);*/
			System.setProperty("webdriver.gecko.driver", System.getProperty("geckodriver"));
			
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("browser.privatebrowsing.autostart", true);
			webDriver = new FirefoxDriver(options);
		} else if (browserName.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("chromedriver"));
			ChromeOptions chromeOptions = new ChromeOptions();
			webDriver = new ChromeDriver(chromeOptions);
		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("iedriver"));
			DesiredCapabilities capabilities= DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			webDriver = new InternetExplorerDriver();
		}
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
	}

	public static WebDriverFactory getInstance() {
		return webDriverFactory;

	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

}
