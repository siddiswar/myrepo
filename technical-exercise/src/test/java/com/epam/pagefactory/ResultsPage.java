package com.epam.pagefactory;
/**
 * @author Siddu
 */

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultsPage {

	WebDriverWait wait;
	Logger logger = Logger.getLogger(ResultsPage.class.getName());

	public ResultsPage(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
		wait = new WebDriverWait(webDriver, 10);
	}

	@FindBy(id = "resultStats")
	private WebElement resultStatElement;

	@FindBy(xpath = "//div[@class='srg']/div[@class='g']/div/div/div/a")
	private List<WebElement> allSearchResultLinkElements;

	@FindBy(xpath = "//span[contains(text(),'Next')]")
	private WebElement paginationNextElement;

	@FindBy(xpath = "//div[@id='res']//p[1]")
	private WebElement noResultsElement;

	public boolean searchResultsFound() {
		try {
			wait.until(ExpectedConditions.visibilityOf(resultStatElement));
			logger.info("Search Results found");
			return true;
		} catch (Exception e) {
			logger.info("Search Results not found");
			return false;
		}

	}

	public List<WebElement> getSearchResultLinkElements() {
		wait.until(ExpectedConditions.visibilityOfAllElements(allSearchResultLinkElements));
		logger.info("Results count :" + allSearchResultLinkElements.size());
		return allSearchResultLinkElements;
	}

	public boolean paginationNextElementFound() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(paginationNextElement));
			logger.info("Pagination next element found");
			return true;
		} catch (Exception e) {
			logger.info("Pagination next element not found");
			return false;
		}

	}

	public void clickPaginationNextElement() {
		wait.until(ExpectedConditions.elementToBeClickable(paginationNextElement));
		paginationNextElement.click();
	}

	public String getResultStatText() {
		wait.until(ExpectedConditions.visibilityOf(resultStatElement));
		return resultStatElement.getText();
	}

	public String noResultsElementText() {
		wait.until(ExpectedConditions.visibilityOf(noResultsElement));
		return noResultsElement.getText();
	}

}
