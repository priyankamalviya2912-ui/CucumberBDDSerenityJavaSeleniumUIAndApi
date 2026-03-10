package api.authTyp;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BearerTokenExample {

    public static void main(String[] args) {

        String token = "12345";

        Response response =
                RestAssured
                        .given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://httpbin.org/bearer");

        response.prettyPrint();
    }
}