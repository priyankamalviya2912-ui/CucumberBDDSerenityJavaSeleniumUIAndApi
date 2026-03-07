  
   
  Feature: Open Library  and validate details of Author - API Validation
  @API @Task3 @TC_03
  Scenario: Validate specific author details from Open Library using JSON test data
	When user sets the API base URL
    When I request details for author from json
    Then validate personal name from json 
    And validate alternate name from json
    

