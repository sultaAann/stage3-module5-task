package com.mjc.school.controller.impl;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

class NewsControllerTest {
    private static final String BASE_URL = "http://localhost:8080/news";

    @Test
    void readAll() {
        given()
        .when()
                .get(BASE_URL + "/all")
        .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void readById() {
        given()
                .pathParam("id", 3)
        .when()
                .get(BASE_URL + "/{id}")
        .then()
                .statusCode(200)
                .body("id", equalTo(3))
                .body("content", equalTo("BBBBBBBBBBBBBB"))
                .body("title", equalTo("BBBBBBBBBBB"));
    }

    @Test
    void create() {
        given()
                .contentType("application/json")
                .body("{\"title\": \"CREATE\" ,\"content\": \"CREATE\",\"authorId\": 1 }")
        .when()
                .post(BASE_URL)
        .then()
                .statusCode(201)
                .body("content", equalTo("CREATE"))
                .body("title", equalTo("CREATE"));
    }

    @Test
    void update() {
        given()
                .contentType("application/json")
                .pathParam("id", 3)
                .body("{\n" +
                        "        \"title\": \"UPDATED\",\n" +
                        "        \"content\": \"UPDATED\",\n" +
                        "        \"authorId\": 4\n" +
                        "    }")
        .when()
                .patch(BASE_URL + "/{id}")
        .then()
                .statusCode(200)
                .body("id", equalTo(3))
                .body("content", equalTo("UPDATED"))
                .body("title", equalTo("UPDATED"));
    }

    @Test
    void deleteById() {
        given()
                .pathParam("id", 3)
        .when()
                .delete(BASE_URL + "/{id}")
        .then()
                .statusCode(204);
    }
}