//package api.steps;
//
//import api.AuthorApi1;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import net.serenitybdd.annotations.Steps;
//import io.cucumber.java.en.Then;
//import pages.CalculatorPopupPage;
//import pages.MotorVehicleRegistrationPage;
//import pages.StampDutyLandingPage;
//import utils.JsonReader;
//
//
//
//public class ApiStepDefinition {
//        
//        @Steps
//        AuthorApi1 authorApi;
//        
//        @When("I set base url")
//        public void setBaseUrl() {
//        	authorApi.setBaseUrl();
//        }
//        
//        @When("I request details for author from json")
//        public void requestAuthorDetails() {
//        		authorApi.getAuthorfromJson();
//          
//        }
//        
//        
//        
//        
//        
//        //api
//        @When("I request details for author with ID {string}")
//        public void requestAuthorDetails(String authorId) {
//        	authorApi.setBaseUrl();
//            authorApi.fetchAuthorDetails(authorId);
//        }
//
//        @Then("the personal name should be {string}")
//        public void verifyName(String name) {
//            authorApi.verifyPersonalName(name);
//        }
//
//        @Then("the alternate names should include {string}")
//        public void verifyAltName(String altName) {
//            authorApi.verifyAlternateNamesInclude(altName);
//        }
//    }
