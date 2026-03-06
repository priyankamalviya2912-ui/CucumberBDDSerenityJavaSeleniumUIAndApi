package api.steps;

import api.AuthorApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import io.cucumber.java.en.Then;
import pages.CalculatorPopupPage;
import pages.MotorVehicleRegistrationPage;
import pages.StampDutyLandingPage;



public class ApiStepDefinition {
        
        @Steps
        AuthorApi authorApi;
        
        
        //api
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
