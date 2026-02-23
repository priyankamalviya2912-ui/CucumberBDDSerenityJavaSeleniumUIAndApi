package api;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.model.util.EnvironmentVariables;


import static org.hamcrest.Matchers.*;

public class AuthorApi extends PageObject{
	private EnvironmentVariables environmentVariables;
	private String baseUrl;
	
	public void setBaseUrl() {
	
	baseUrl = net.serenitybdd.model.environment.EnvironmentSpecificConfiguration
            .from(environmentVariables)
            .getProperty("restapi.baseurl");

	}
	
	
    public void fetchAuthorDetails(String authorId) {

        SerenityRest.given()
                .baseUri(baseUrl) // Sets the base URL dynamically
                .pathParam("authorId", authorId)
                .get("/authors/{authorId}.json");
    }

   
    public void verifyPersonalName(String expectedName) {
        
        SerenityRest.then()
                .statusCode(200)
                .body("personal_name", is(expectedName));
    }

    
    public void verifyAlternateNamesInclude(String expectedAltName) {
        SerenityRest.then()
                .body("alternate_names", hasItem(expectedAltName));
    }
}
