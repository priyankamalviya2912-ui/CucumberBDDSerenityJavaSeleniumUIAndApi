Feature: NSW Stamp Duty Calculator
    As a user
    I want to check stamp duty online
    So that I can calculate the expected duty amount

	@TC_01
    Scenario Outline: Navigate to Stamp Duty calculator from landing page
        Given Open the Stamp Duty landing page
        When Click the 'Check online' button to navigate to the calculator
        Then The calculator page should be displayed
  	  When I select "<PassengerVehicle>" and "<PurchasePrice>" for Is this registration for a passenger vehicle
  		Then I verify the "Is this registration for a passenger vehicle?" is "<PassengerVehicle>"
    And I verify the "Purchase price or value" is "<PurchasePrice>"
    And I verify the "Duty payable" is "<CalculatedDuty>"

  Examples:
    | PassengerVehicle | PurchasePrice | CalculatedDuty |
    | Yes              | 45,000         | $1,350.00        |
   
  