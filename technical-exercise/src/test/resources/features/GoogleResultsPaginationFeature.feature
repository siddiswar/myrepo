Feature: Pagination functionality for google search engine 
	Incase there are too many results, proper pagination should be implemented and 
    User should be allowed to navigate through pages 


Scenario: 
	User should get a next link to navigate to next page when there are too many results 
	
	Given   User is on Google Search page 
	When   User enters a proper search term "test" in search box 
	And   User clicks Google Search Button 
	Then   Result page should contain pagination next element 
	
	
Scenario: 
	User should be taken to next page on clicking next 

	Given   User is on Google Search page 
	When   User enters a proper search term "test" in search box 
	And   User clicks Google Search Button 
	And   User clicks pagination next 
	Then   User should be taken to second page 
	And   Second page should have results