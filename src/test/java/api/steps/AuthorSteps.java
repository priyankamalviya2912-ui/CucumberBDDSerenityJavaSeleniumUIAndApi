package api.steps;

import api.AuthorApi;
import io.cucumber.java.en.*;
import net.serenitybdd.annotations.Steps;
//import net.thucydides.core.annotations.Steps;

public class AuthorSteps {

	// @Steps
	AuthorApi authorApi;
   
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

		authorApi.validatePersonalName();
	}

	@And("validate alternate name from json")
	public void validateAlternateName() {

		authorApi.validateAlternateName();
	}

	
}