package api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.model.util.EnvironmentVariables;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;

import com.google.gson.stream.JsonReader;

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
    	
    	 // Point to JSON file
        File file = new File("src/test/resources/testdata/author.json");
        
        // Read JSON and parse authorId
        JsonPath jsonPath =JsonPath.from(file);
        String authorId = jsonPath.getString("author.authorId");
        

        response = SerenityRest
                .given()
                .baseUri(baseUrl)
                .when()
                .get("/authors/" + authorId + ".json");
        response.prettyPrint();
    }

    // THEN STEP - Validate Personal Name
    public void validatePersonalName() {
    	 // Point to JSON file
        File file = new File("src/test/resources/testdata/author.json");
        
    
        String expectedName = JsonPath.from(file).getString("author.personalName");
        

        response.then()
                .statusCode(200)
                .body("personal_name", equalTo(expectedName));
    }
    
    public void validatePersonalNameFromPojo() {
    	Author author = response.as(Author.class);
    	
    	 // Point to JSON file
        File file = new File("src/test/resources/testdata/author.json");
        
    
        String expectedname = JsonPath.from(file).getString("author.personalName");
    	
    	try {
    	assertThat(author.getName(), equalTo(expectedname));
    	
    	}
    	catch(Exception e) {
    		System.out.println("error");
    	}
    	System.out.println("Author Name: " + author.getName());
    	System.out.println("Birth Date: " + author.getBirth_date());
    	System.out.println("Type: " + author.getType().getKey());
    }

    // THEN STEP - Validate Alternate Name
    public void validateAlternateName() {
    	
    	// Point to JSON file
        File file = new File("src/test/resources/testdata/author.json");
        
    
        String expectedAltName = JsonPath.from(file).getString("author.alternateName");


        response.then()
                .body("alternate_names", hasItem(expectedAltName));
    }
}