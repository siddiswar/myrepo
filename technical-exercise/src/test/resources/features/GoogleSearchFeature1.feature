Feature: Test Google Search functionality 


Scenario: 
	Given User is on Google Search page 
	When User enters a proper search term "test" in search box 
	And User clicks Google Search Button 
	Then Results page containing results should get displayed 
	
Scenario: 
	Given User is on Google Search page 
	When User enters  improper search term "fghdsfghjkkjhgjhk" in search box 
	And User clicks Google Search Button 
	Then Results should not get displayed
	And User should be notified that no results are found 
	
Scenario: 
	Given User is on Google Search page 
	When User enters a proper search term "test" in search box 
	And User clicks Google Search Button 
	And User clicks on first five results in result page 
	Then User should be redirected to correct landing page of result 
	
	 