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
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.IsEqual;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.pojo.Employee;

//post with diff ways - map and pojo in body
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
    	JsonPath jsonPath = new JsonPath(payload);
    	
    	Map<String,String> payloadAsHmap = new HashMap<>();
    	
    	//if wish to take all json data then start with ""
    	payloadAsHmap=  jsonPath.getMap("tc01");
    	
    	response = SerenityRest
                .given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(payloadAsHmap)
                .when()
                .post("/create");
    }
    
    public void postNewEmployeeUsingPojo() {
    	File payload = new File("src/test/resources/payload/createEmployee.json");

    	
    	Employee empPojo = JsonPath.from(payload)
                .getObject("tc01", Employee.class);
		

    	// use values
    	System.out.println(empPojo.getName());
    	System.out.println(empPojo.getSalary());
    	System.out.println(empPojo.getAge());
    	
    	response = SerenityRest
                .given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(empPojo)
                .when()
                .post("/create");
    	
    	System.out.println(response.getBody());
    	System.out.println();
    	
    }
    
    public void validatePostNewEmployeeResponse() {
        
    	JsonPath jsonPath = response.jsonPath();
    
    	File payload = new File("src/test/resources/payload/createEmployee.json");
    	JsonPath jsonPathOfemp = JsonPath.from(payload);
    	

    	String name = jsonPathOfemp.getString("tc01.name");
    	String salary = jsonPathOfemp.getString("tc01.salary");
    	String age = jsonPathOfemp.getString("tc01.age");
    
    	
    	assertThat(response.getStatusCode(), equalTo(200));
    	assertThat(jsonPath.getString("status"), equalTo("success"));
    	assertThat(jsonPath.getString("data.name"), equalTo(name));
    	assertThat(jsonPath.getString("data.salary"), equalTo(salary));
    	assertThat(jsonPath.getString("data.age"), equalTo(age));
    	
    }
    
}
