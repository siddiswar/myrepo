Feature: Test Google Search functionality 

Scenario: 
	Given User is on Google Search page 
	When User enters a proper search term "test" in search box 
	And User clicks Google Search Button 
	Then Result page should contain pagination next element 
	
	
Scenario: 
	Given User is on Google Search page 
	When User enters a proper search term "test" in search box 
	And User clicks Google Search Button 
	And User clicks pagination next 
	Then User should be taken to second page
	And Second page should have results