//
//
//package api;
//
//import net.serenitybdd.core.pages.PageObject;
//import net.serenitybdd.rest.SerenityRest;
//import net.thucydides.model.util.EnvironmentVariables;
//import utils.JsonReader;
//import static org.hamcrest.Matchers.*;
//import static org.mockito.ArgumentMatchers.isNotNull;
//
//public class AuthorApi1 extends PageObject{
//	private EnvironmentVariables environmentVariables;
//	private String baseUrl;
//	
//	public void setBaseUrl() {
//	
//	baseUrl = net.serenitybdd.model.environment.EnvironmentSpecificConfiguration
//            .from(environmentVariables)
//            .getProperty("restapi.baseurl");
//
//	}
//	
//	public void getAuthorfromJson() {
//		  String authorId = JsonReader.getValue("authorId");
//
//		  SerenityRest.given()
//                  .baseUri(baseUrl)
//                  .when()
//                  .get("/author/" + authorId);
//		  System.out.println();
//	}
//	
//	
//    public void fetchAuthorDetails(String authorId) {
//
//        SerenityRest.given()
//                .baseUri(baseUrl) // Sets the base URL dynamically
//                .pathParam("authorId", authorId)
//                .get("/authors/{authorId}.json");
//    }
//
//   
//    public void verifyPersonalName(String expectedName) {
//        
//        SerenityRest.then()
//                .statusCode(200)
//                .body("personal_name", is(expectedName));
//    }
//
//    
//    public void verifyAlternateNamesInclude(String expectedAltName) {
//        SerenityRest.then()
//                .body("alternate_names", hasItem(expectedAltName));
//    }
//}
