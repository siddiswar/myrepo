package com.epam.runners;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.webdriver.WebDriverFactory;

/**
 * @author Siddu
 */

import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

//TestNG runner class. WebDriver is initialized @BeforeTest and destroyed in @AfterTest. So all the features will get executed in single webdriver instance
@CucumberOptions(features = { "src/test/resources/features" }, glue = { "com/epam/stepdefinitions",
		"com/epam/hooks" }, plugin = { "pretty", "html:target/cucumber-reports/",
				"json:target/cucumber-reports/cucumber.json" }, monochrome = true)
public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;
	WebDriver webDriver;
	Logger logger = Logger.getLogger(TestRunner.class.getName());

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@BeforeTest(alwaysRun = true)
	public void testNgBeforeTest() {
		logger.info("testNgBeforeTest : Creating WebDriver Instance");
		webDriver = WebDriverFactory.getInstance().getWebDriver();

	}

	@AfterTest(alwaysRun = true)
	public void testNgAfterTest() {
		logger.info("testNgAfterTest : Destroying WebDriver Instance");
		webDriver.quit();

	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
		// the 'featureWrapper' parameter solely exists to display the feature
		// file in a test report
		testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
	}

	/**
	 * Returns two dimensional array of PickleEventWrapper scenarios with their
	 * associated CucumberFeatureWrapper feature.
	 *
	 * @return a two dimensional array of scenarios features.
	 */
	@DataProvider
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		if (testNGCucumberRunner == null) {
			return;
		}
		testNGCucumberRunner.finish();
	}
}
