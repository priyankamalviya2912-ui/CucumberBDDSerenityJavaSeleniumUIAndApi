package api.steps;

import api.AuthorApi;
import api.EmployeeApi;
import api.SpacexApi;
import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
//import net.thucydides.core.annotations.Steps;

public class AuthorSteps {

	// @Steps
	AuthorApi authorApi;
	SpacexApi spacexApi;
	EmployeeApi employeeApi;
   
	@When("user sets the API base URL")
	public void setBaseUrl() {
		authorApi.setBaseUrl();
	}

	@When("I request details for author from json")
	public void requestAuthorDetails() {

		authorApi.getAuthorFromJson();
	}

	@Then("validate personal name from json")
	public void validatePersonalName() {

		//authorApi.validatePersonalName();
		authorApi.validatePersonalNameFromPojo();
	}

	@And("validate alternate name from json")
	public void validateAlternateName() {

		authorApi.validateAlternateName();
	}
	
	@When("user sets the API base URL and verifies info")
	public void spaceXvalidate() {
		spacexApi.setBaseUrl();
		spacexApi.getSpacexResponse();
		spacexApi.validateCoreDataFromPojo();
		
	}
	
	@When("user sets the API base URL and post emp info")
	public void empApiSetAndPost() {
		employeeApi.setBaseUrl();
		employeeApi.postNewEmployee();
		employeeApi.validatePostNewEmployeeResponse();
		
	}

	
}