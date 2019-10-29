package com.epam.pagefactory;
/**
 * @author Siddu
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchPage {
	// PageFactory implementation of web elements present in search page by
	// using proper waits

	WebDriverWait wait;
	
	public SearchPage(WebDriver webDriver){
		PageFactory.initElements(webDriver, this);
		wait = new WebDriverWait(webDriver, 10);
	}
	
	@FindBy(name="q")
	private WebElement searchBoxElement;
	
	@FindBy(name="btnK")
	private WebElement searchButtonElement;
	
	public void enterSearchTerm(String searchTerm){
		wait.until(ExpectedConditions.visibilityOf(searchBoxElement));
		searchBoxElement.sendKeys(searchTerm);
	}
	
	public void submitSearch(){
		//wait.until(ExpectedConditions.elementToBeClickable(searchButtonElement));
		//searchButtonElement.click();
		searchBoxElement.submit();
		
		
	}
	
	
	
	
	

}
