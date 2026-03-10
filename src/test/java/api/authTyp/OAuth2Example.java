package api.authTyp;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuth2Example {

    public static void main(String[] args) {

        String body = "{ \"username\": \"mor_2314\", \"password\": \"83r5^_\" }";

        Response loginResponse =
                RestAssured
                        .given()
                        .header("Content-Type", "application/json")
                        .body(body)
                        .post("https://fakestoreapi.com/auth/login");

        String token = loginResponse.jsonPath().getString("token");

        System.out.println("Token: " + token);

        // Step 2 Use Token

        Response response =
                RestAssured
                        .given()
                        .auth()
                        .oauth2(token)
                        .get("https://fakestoreapi.com/products");

        response.prettyPrint();
    }
}
