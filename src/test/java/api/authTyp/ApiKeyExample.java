package api.authTyp;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiKeyExample {

    public static void main(String[] args) {

        Response response =
                RestAssured
                        .given()
                        .queryParam("q", "London")
                        .queryParam("appid", "YOUR_API_KEY")
                        .when()
                        .get("https://api.openweathermap.org/data/2.5/weather");

        response.prettyPrint();
    }
}