package api_utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiUtils {

    public static Response getResponse(String url) {
        return RestAssured.get(url)
                .then()
                .extract()
                .response();
    }

    public static Response postClassModel(Object modelClass, ContentType contentType, String url) {
        return RestAssured.given()
                .contentType(contentType)
                .body(modelClass)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

}
