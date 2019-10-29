How to Run:
	checkout the project
	In conf/config.properties file, set browser_name=FIREFOX or browser_name=CHROME. Right now the value is set to FIREFOX 
	Do a mvn clean install 
	If we want to run tests on CHROME browser, In conf/config.properties file, set browser_name=CHROME and do a mvn clean install 
	
	Note : Tests are run in test phase of maven build life cycle

Where to see test report:
	html report => target\cucumber-reports\index.html
	json report => target\cucumber-reports\

Tests are tested successfully on FIREFOX and CHROME browsers. Couldn't get to work on IE browser due to short notice.		
	
About the Project:

Used latest versions of following dependencies:
	selenium-java
	testng
	cucumber-java
	cucumber-testng
	log4j
	

Tested against following browser/driver versions: 
	Firefox Version:70.0 
	Chrome Version :78.0.3904.70
	chromedriver version	:78.0.3904.70		
	geckodriver version 	:71.0.0.7191
	
Framework Details:

	Build Tool Used:
		Maven is used as build tool
	
	Used latest versions of following dependencies:
		selenium-java
		testng
		cucumber-java
		cucumber-testng
		log4j

	BDD: 	
		Selenium WebDriver scripts are written in BDD approach using Cucumber-JVM
		Cucumber Feature files are at the location: src/test/resources/features/
		All Step definitions are in a single file : src/test/java/com/epam/stepdefinitions/AllSteps.java
	
	Testing Framework Used: 
		TestNG is used as Test Framework. 
		TestNG Test runner is used to run the tests : src/test/java/com/epam/runners/TestRunner.java
		testng.xml file is not used for running the suite
		TestNG @BeforeTest and @AfterTest are used to create and destroy WebDriver singleton instance . All the tests are run in a single WebDriver instance . Driver is destroyed only after all the features are run 
		In Every 'Then' step, TestNg assertions are used to assert that the actual and expected behaviors are matching
		
	Reporting:
		Cucumber built in reports are generated
			html report => target\cucumber-reports\index.html
			json report => target\cucumber-reports\ 
		These are basic reports
		
	Logging:
		log4j is used to implement logging
		log4j.xml is found in conf folder
		logs are written in logs\application.log
		Currently log4j is configured to overwrite log file everytime
		
	Comments:
		Tried to provide comments wherever needed
		
	Data Driven approach:
		Though few of the step definitions are parameterized, did not implement data driven approach to drive test data using excel files,json files,Cucumber data table, Cucumber Scenario outline as these are minimal tests and also due to time constraint
			 
			