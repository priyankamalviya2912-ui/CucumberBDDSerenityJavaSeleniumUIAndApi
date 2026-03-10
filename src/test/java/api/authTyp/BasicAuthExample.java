package api.authTyp;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BasicAuthExample {

    public static void main(String[] args) {

        Response response =
                RestAssured
                        .given()
                        .auth()
                        .basic("user", "passwd")
                        .when()
                        .get("https://httpbin.org/basic-auth/user/passwd");

        response.prettyPrint();
        System.out.println("Status Code: " + response.getStatusCode());
    }
}