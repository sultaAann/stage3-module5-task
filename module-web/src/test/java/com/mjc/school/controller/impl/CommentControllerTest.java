package com.mjc.school.controller.impl;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

class CommentControllerTest {
    private static final String BASE_URL = "http://localhost:8080/comments";

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
                .body("content", equalTo("LALLALA"));
    }

    @Test
    void create() {
        given()
                .contentType("application/json")
                .body("{\"content\":\"TEST\", \"newsId\": 3}")
        .when()
                .post(BASE_URL)
        .then()
                .statusCode(201)
                .body("content", equalTo("TEST"))
                .body("newsId", equalTo(3))
                .body("id", greaterThan(0));
    }

    @Test
    void update() {
        given()
                .contentType("application/json")
                .pathParam("id", 3)
                .body("{\"content\": \"UPDATED\", \"newsId\": 5}")
        .when()
                .patch(BASE_URL + "/{id}")
        .then()
                .statusCode(200)
                .body("id", equalTo(3))
                .body("content", equalTo("UPDATED"));
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