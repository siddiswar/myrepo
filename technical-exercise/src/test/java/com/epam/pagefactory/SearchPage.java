package com.epam.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Siddu
 */
public class SearchPage {
	
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
		searchBoxElement.sendKeys(searchTerm);
	}
	
	public void clickSearchButton(){
		//wait.until(ExpectedConditions.elementToBeClickable(searchButtonElement));
		//searchButtonElement.click();
		searchBoxElement.submit();
		
		
	}
	
	
	
	
	

}
