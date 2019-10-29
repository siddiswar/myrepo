Feature: Search functionality for google search engine 
	The User should be able to search on google home page. 	User should be shown with relevant results .
	Incase of no results user should be notified that there are no results
	On clicking the results user should be successfully navigated to target page 


Scenario: 
	On Searching with a proper search term User should be shown with proper search results 
	
	Given User is on Google Search page 
	When User enters a proper search term "test" in search box 
	And User clicks Google Search Button 
	Then Results page containing results should get displayed 
	
Scenario: 
	On Searching with an improper search term User should not get results and he should be notified about no results 

	Given User is on Google Search page 
	When User enters  improper search term "fghdsfghjkkjhgjhk" in search box 
	And User clicks Google Search Button 
	Then Results should not get displayed 
	And User should be notified that no results are found 
	
Scenario: 
	On clicking the search results, user should be taken to correct landing page successfully 
	
	Given User is on Google Search page 
	When User enters a proper search term "test" in search box 
	And User clicks Google Search Button 
	And User clicks on first five results in result page 
	Then User should be redirected to correct landing page of result 
	
	 