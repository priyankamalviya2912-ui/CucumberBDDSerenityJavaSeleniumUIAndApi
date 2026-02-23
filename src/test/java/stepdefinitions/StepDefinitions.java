package stepdefinitions;

import api.AuthorApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import io.cucumber.java.en.Then;
import pages.CalculatorPopupPage;
import pages.MotorVehicleRegistrationPage;
import pages.StampDutyLandingPage;



public class StepDefinitions {
	MotorVehicleRegistrationPage motorVehicleRegistrationPage;
        
	StampDutyLandingPage stampDutyLandingPage;
       
    CalculatorPopupPage calculatorPopupPage;
        
        @Steps
        AuthorApi authorApi;
        
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
        
        @Then("All landing page elements should be accessible")
        public void verifyLandingPageElements() {
           // landingSteps.verifyLandingPageLoaded();
        }
        
       

        @Then("I should see the correct calculation or validation message")
        public void verifyCalculationOrMessage() {
           
        }
        
        
        @When("I select {string} and {string} for Is this registration for a passenger vehicle")
        public void i_select_and_enter_for(String passengerVehicle, String purchasePrice) {
        	if(passengerVehicle.equalsIgnoreCase("yes")) {
        		motorVehicleRegistrationPage.selectYesOnRegistrationRadio();
        	}
        	motorVehicleRegistrationPage.inputPurchasePriceText(purchasePrice);
        	motorVehicleRegistrationPage.clickCalculateButton();
        	//calculatorPopupPage.SwitchToPopup();
        	calculatorPopupPage.VerifyMotorVehicleRegistrationPopup();
        	calculatorPopupPage.VerifyPopupValues("$45,000.0");
        }
        
        @When("I request details for author with ID {string}")
        public void requestAuthorDetails(String authorId) {
        	authorApi.setBaseUrl();
            authorApi.fetchAuthorDetails(authorId);
        }

        @Then("the personal name should be {string}")
        public void verifyName(String name) {
            authorApi.verifyPersonalName(name);
        }

        @Then("the alternate names should include {string}")
        public void verifyAltName(String altName) {
            authorApi.verifyAlternateNamesInclude(altName);
        }
    }
