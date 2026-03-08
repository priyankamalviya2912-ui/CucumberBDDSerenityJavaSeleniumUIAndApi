package api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.model.util.EnvironmentVariables;
import io.restassured.response.Response;
import utils.JsonReader;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import api.pojo.spacexpojo.Spacex;

public class SpacexApi extends PageObject {
	
	Response response;
	

    private EnvironmentVariables environmentVariables;
    private String baseUrl;

    public void setBaseUrl() {

        baseUrl = net.serenitybdd.model.environment.EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty("restapi.spacex.baseurl");
    } 
    
 // WHEN STEP
    public void getSpacexResponse() {

       
        response = SerenityRest
                .given()
                .baseUri(baseUrl)
                .when()
                .get();
        response.prettyPrint();
    }
    
    public void validateCoreDataFromPojo() {

    	Spacex spacex = response.as(Spacex.class);
    	try {
        assertThat(spacex.getCores().size(), greaterThan(0));
        assertThat(spacex.getCores().get(0).getCore(), notNullValue());
        assertThat(spacex.getCores().get(0).getFlight(), notNullValue());
        System.out.println(spacex.getCores().get(0).getCore());
        System.out.println(spacex.getCores().get(0).getFlight());
    	}
    	catch (Exception e) {
    		System.out.println();
    	}
    }
}
    