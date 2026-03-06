package ui.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pages.CalculatorPopupPage;
import pages.MotorVehicleRegistrationPage;
import pages.StampDutyLandingPage;



public class StepDefinitions {
	MotorVehicleRegistrationPage motorVehicleRegistrationPage;
        
	StampDutyLandingPage stampDutyLandingPage;
       
    CalculatorPopupPage calculatorPopupPage;
   
        
        @Given("Open the Stamp Duty landing page")
        public void openLandingPage() {
        	stampDutyLandingPage.open();
        }
        
        
        @When("Click the 'Check online' button to navigate to the calculator")
        public void clickCheckOnline() {
        	stampDutyLandingPage.clickCheckOnlineButton();
        }
        
        @Then("The calculator page should be displayed")
        public void verifyCalculatorPageDisplayed() {
        	motorVehicleRegistrationPage.navigateToMotorVehicleRegistration();
			motorVehicleRegistrationPage.verifyMotorRegistrationPageIsDisplayed();
        }
    
        
        @When("I select {string} and {string} for Is this registration for a passenger vehicle")
        public void i_select_and_enter_for(String passengerVehicle, String purchasePrice) {
        	if(passengerVehicle.equalsIgnoreCase("yes")) {
        		motorVehicleRegistrationPage.selectYesOnRegistrationRadio();
        	}
        	motorVehicleRegistrationPage.inputPurchasePriceText(purchasePrice);
        	motorVehicleRegistrationPage.clickCalculateButton();
        	calculatorPopupPage.VerifyMotorVehicleRegistrationPopup();
        	calculatorPopupPage.VerifyPopupValues("$45,000.00");
        }
        
        @Then("I verify the {string} is {string}")
        public void verifyFieldAndValue(String fieldName, String expectedValue) {
        	calculatorPopupPage.VerifyMotorVehicleRegistrationPopup();
        	calculatorPopupPage.VerifyPopupValues(fieldName);
        	calculatorPopupPage.VerifyPopupValues(expectedValue);
        }
        
    }
