package api;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.model.util.EnvironmentVariables;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.JsonReader;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.hamcrest.core.IsEqual;

import com.fasterxml.jackson.databind.JsonNode;

public class EmployeeApi extends PageObject{

	private EnvironmentVariables environmentVariables;
    private String baseUrl;
    
    

    Response response;
    
    public void setBaseUrl() {

        baseUrl = net.serenitybdd.model.environment.EnvironmentSpecificConfiguration
                .from(environmentVariables)
                .getProperty("restapi.emp.baseurl");
    } 
    
    public void postNewEmployee() {
    	File payload = new File("src/test/resources/payload/createEmployee.json");
    	
    	response = SerenityRest
                .given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/create");
    }
    
    public void validatePostNewEmployeeResponse() {
        
    	JsonPath jsonPath = response.jsonPath();
    	JsonNode emp = JsonReader.empData();

    	String name = emp.get("name").asText();;
    	String salary = emp.get("salary").asText();
    	String age = emp.get("age").asText();
    
    	
    	assertThat(response.getStatusCode(), equalTo(200));
    	assertThat(jsonPath.getString("status"), equalTo("success"));
    	assertThat(jsonPath.getString("data.name"), equalTo(name));
    	assertThat(jsonPath.getString("data.salary"), equalTo(salary));
    	assertThat(jsonPath.getString("data.age"), equalTo(age));
    	
    }
    
}
