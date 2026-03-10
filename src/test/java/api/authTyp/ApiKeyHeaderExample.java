package api.authTyp;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiKeyHeaderExample {

    public static void main(String[] args) {

        String apiKey = "YOUR_API_KEY";

        Response response = RestAssured
                .given()
                .header("x-api-key", apiKey)
                .when()
                .get("https://api.thecatapi.com/v1/images/search");

        // Print response
        response.prettyPrint();

        // Print status code
        System.out.println("Status Code: " + response.getStatusCode());
    }
}