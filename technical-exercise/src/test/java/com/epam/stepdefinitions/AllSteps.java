package com.epam.stepdefinitions;

/**
 * @author Siddu
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.epam.pagefactory.ResultsPage;
import com.epam.pagefactory.SearchPage;
import com.epam.utils.PropertiesReaderUtil;
import com.epam.webdriver.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllSteps {
	// This is a single step definition file. Contains steps for all feature
	// files
	WebDriver webDriver;
	String expectedSearchPageTitle;
	PropertiesReaderUtil propertiesReaderUtil;
	SearchPage searchPage;
	ResultsPage resultsPage;
	String expectedResultTargetLink;
	Map<String, Map<String, String>> allSearchResultMaps = new HashMap<String, Map<String, String>>();
	Logger logger = Logger.getLogger(AllSteps.class.getName());

	@Before
	public void setUp() {
		logger.info("Before hook");
		propertiesReaderUtil = PropertiesReaderUtil.getInstance();
		webDriver = WebDriverFactory.getInstance().getWebDriver();
		searchPage = new SearchPage(webDriver);
		resultsPage = new ResultsPage(webDriver);
	}

	@After
	public void tearDown() {
		logger.info("After hook");
		// webDriver.quit();
	}

	@Given("User is on Google Search page")
	public void user_is_on_Google_Search_page() throws InterruptedException {
		logger.info("user_is_on_Google_Search_page");
		webDriver.get(System.getProperty("google_url"));
		Thread.sleep(3000);
		;
		expectedSearchPageTitle = System.getProperty("google_searchpage_title");
		String actualSearchPageTitle = webDriver.getTitle();
		logger.info("expectedSearchPageTitle : " + expectedSearchPageTitle);
		logger.info("actualSearchPageTitle : " + actualSearchPageTitle);

		Assert.assertEquals(actualSearchPageTitle, expectedSearchPageTitle, "Google Search page title is not matching");

	}

	@When("User enters a proper search term {string} in search box")
	public void user_enters_a_proper_search_term_in_search_box(String properSearchTerm) {
		logger.info("User enters a proper search term {string} in search box");
		searchPage.enterSearchTerm(properSearchTerm);
	}

	@When("User clicks Google Search Button")
	public void user_clicks_Google_Search_Button() {
		logger.info("User clicks Google Search Button");
		searchPage.submitSearch();

	}

	@Then("results page should get displayed")
	public void results_page_should_get_displayed() {
		logger.info("results page should get displayed");
		logger.info(webDriver.getTitle());
		resultsPage.searchResultsFound();
		resultsPage.getSearchResultLinkElements();

	}

	@Then("Results page containing results should get displayed")
	public void results_page_containing_results_should_get_displayed() {
		logger.info("results page containing results should get displayed");
		boolean areSearchResultsFound = resultsPage.searchResultsFound();
		Assert.assertTrue(areSearchResultsFound, "Search Results not found");
	}

	@When("User enters  improper search term {string} in search box")
	public void user_enters_improper_search_term_in_search_box(String improperSearchTerm) {
		logger.info("User enters  improper search term {string} in search box");
		searchPage.enterSearchTerm(improperSearchTerm);
	}

	@Then("Results should not get displayed")
	public void results_should_not_get_displayed() {
		logger.info("results should not get displayed");
		boolean areSearchResultsFound = resultsPage.searchResultsFound();
		Assert.assertFalse(areSearchResultsFound, "Search Results found even for improper search term");
	}

	@Then("User should be notified that no results are found")
	public void user_should_be_notified_that_no_results_are_found() {
		logger.info("User should be notified that no results are found");
		String noResultsElementText = resultsPage.noResultsElementText();
		Assert.assertTrue(noResultsElementText.contains("did not match any documents"),
				"User is not notified about no results");
	}

	// In this method, we are iterating through each search result,getting the
	// href, clicking each link, getting the target url after redirection and
	// storing them into map.
	// After clicking each link, we are navigating back to results page and
	// locating elements again as previously located link elements are stale noe
	@When("User clicks on first five results in result page")
	public void user_clicks_on_first_five_results_in_result_page() throws InterruptedException {
		logger.info("User clicks on first five results in result page");
		List<WebElement> allSearchResultLinkElements = resultsPage.getSearchResultLinkElements();
		for (Integer i = 0; i < allSearchResultLinkElements.size() / 2; i++) {
			Map<String, String> searchResultMap = new HashMap<String, String>();
			// for (WebElement
			// eachSearchResultLinkElement:allSearchResultLinkElements){
			allSearchResultLinkElements = resultsPage.getSearchResultLinkElements();
			WebElement eachSearchResultLinkElement = allSearchResultLinkElements.get(i);
			searchResultMap.put("expectedhref", eachSearchResultLinkElement.getAttribute("href"));
			eachSearchResultLinkElement.click();
			searchResultMap.put("actuallandingurl", webDriver.getCurrentUrl());
			logger.info(i);
			logger.info("expectedhref: " + searchResultMap.get("expectedhref"));
			logger.info("actuallandingurl: " + searchResultMap.get("actuallandingurl"));
			allSearchResultMaps.put(i.toString(), searchResultMap);
			webDriver.navigate().back();
			Thread.sleep(2000);
		}
	}

	//In This step, we are asserting that actual target url and expected href are the same
	@Then("User should be redirected to correct landing page of result")
	public void user_should_be_redirected_to_correct_landing_page_of_result() {
		logger.info("User should be redirected to correct landing page of result");
		for (String searchRecordNumber : allSearchResultMaps.keySet()) {
			Map<String, String> eachSearchResultMap = allSearchResultMaps.get(searchRecordNumber);
			Assert.assertEquals(eachSearchResultMap.get("actuallandingurl"), eachSearchResultMap.get("expectedhref"),
					"Actual and expected hrefs are matching");
			logger.info("URL comparison assertion passed");
		}
	}

	@Then("Result page should contain pagination next element")
	public void result_page_should_contain_pagination_next_element() {
		logger.info("Result page should contain pagination next element");
		boolean paginationNextElementFound = resultsPage.paginationNextElementFound();
		Assert.assertTrue(paginationNextElementFound, "Pagination Next element not found");
	}

	@When("User clicks pagination next")
	public void user_clicks_pagination_next() {
		logger.info("User clicks pagination next");
		resultsPage.clickPaginationNextElement();
	}

	@Then("User should be taken to second page")
	public void user_should_be_taken_to_second_page() {
		logger.info("User should be taken to second page");
		String getResultStatText = resultsPage.getResultStatText();
		Assert.assertTrue(getResultStatText.contains("Page 2 of about"),
				"User is not taken to second page after next was clicked");
	}

	@Then("Second page should have results")
	public void second_page_should_have_results() {
		logger.info("Second page should have results");
		boolean areSearchResultsFound = resultsPage.searchResultsFound();
		Assert.assertTrue(areSearchResultsFound, "Search Results not found in second page");
	}

}
