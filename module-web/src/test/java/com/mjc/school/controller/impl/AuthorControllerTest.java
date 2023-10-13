package com.mjc.school.controller.impl;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class AuthorControllerTest {
    private static final String BASE_URL = "http://localhost:8080/authors";

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
                .pathParam("id", 4)
        .when()
                .get(BASE_URL + "/{id}")
        .then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("name", equalTo("PAVEL"));
    }

    @Test
    void create() {
        given()
                .contentType("application/json")
                .body("{\"name\":\"TEST\"}")
        .when()
                .post(BASE_URL)
        .then()
                .statusCode(201)
                .body("name", equalTo("TEST"))
                .body("id", greaterThan(0));
    }

    @Test
    void update() {
        given()
                .contentType("application/json")
                .pathParam("id", 4)
                .body("{\"name\": \"UPDATED\"}")
        .when()
                .put(BASE_URL + "/{id}")
        .then()
                .statusCode(200)
                .body("id", equalTo(4))
                .body("name", equalTo("UPDATED"));
    }

    @Test
    void deleteById() {
        given()
                .pathParam("id", 14)
        .when()
                .delete(BASE_URL + "/{id}")
        .then()
                .statusCode(204);
    }
}