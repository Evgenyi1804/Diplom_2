package org.example.Api.Api;


import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class Api {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String BASE_PATH = "/api";
    private static final String ROOT = "/ingredients";

    public RequestSpecification specification() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH);
    }


    public Response getAllIngredients() {
        return specification()
                .get(ROOT);
    }
}