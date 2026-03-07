package api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.model.util.EnvironmentVariables;
import io.restassured.response.Response;
import utils.JsonReader;

import static org.hamcrest.Matchers.*;
import api.pojo.Author;




public class AuthorApi extends PageObject {

    private EnvironmentVariables environmentVariables;
    private String baseUrl;

    Response response;

    public void setBaseUrl() {

        baseUrl = net.serenitybdd.model.environment.EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty("restapi.baseurl");
    }

    // WHEN STEP
    public void getAuthorFromJson() {

        String authorId = JsonReader.getValue("authorId");

        response = SerenityRest
                .given()
                .baseUri(baseUrl)
                .when()
                .get("/authors/" + authorId + ".json");
        response.prettyPrint();
    }

    // THEN STEP - Validate Personal Name
    public void validatePersonalName() {

        String expectedName = JsonReader.getValue("personalName");

        response.then()
                .statusCode(200)
                .body("personal_name", equalTo(expectedName));
    }
    
    public void validatePersonalNameFromPojo() {
    	Author author = response.as(Author.class);

    	System.out.println("Author Name: " + author.getName());
    	System.out.println("Birth Date: " + author.getBirth_date());
    }

    // THEN STEP - Validate Alternate Name
    public void validateAlternateName() {

        String expectedAltName = JsonReader.getValue("alternateName");

        response.then()
                .body("alternate_names", hasItem(expectedAltName));
    }
}