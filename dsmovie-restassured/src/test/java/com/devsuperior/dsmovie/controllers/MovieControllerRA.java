package com.devsuperior.dsmovie.controllers;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MovieControllerRA {

    private String existingMovieTitle;
    private Long existingMovieId, nonExistingMovieId;

    @BeforeEach
    void setUp() {
        baseURI = "http://localhost:8080";
        existingMovieTitle = "Matrix";
        existingMovieId = 7L;
        nonExistingMovieId = 100L;
    }


    @Test
    public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {
        given()
            .when()
                .get("/movies")
            .then()
                .statusCode(200);
    }

    @Test
    public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {
        given()
            .when()
                .get("/movies?title={existingMovieTitle}", existingMovieTitle)
            .then()
                .statusCode(200)
                .body("content.id[0]", is(4))
                .body("content.title[0]", equalTo("Matrix Resurrections"))
                .body("content.image[0]", equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/hv7o3VgfsairBoQFAawgaQ4cR1m.jpg"))
                .body("content.count[0]", is(0))
                .body("content.score[0]", is(0.0F));
    }

    @Test
    public void findByIdShouldReturnMovieWhenIdExists() {
        given()
            .when()
                .get("/movies/{id}", existingMovieId)
            .then()
                .statusCode(200)
                .body("id", is(7))
                .body("title", equalTo("Titanic"))
                .body("image", equalTo("https://www.themoviedb.org/t/p/w533_and_h300_bestv2/yDI6D5ZQh67YU4r2ms8qcSbAviZ.jpg"))
                .body("count", is(0))
                .body("score", is(0.0F));
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {
        given()
            .when()
                .get("/movies/{id}", nonExistingMovieId)
            .then()
                .statusCode(404);
    }

//    @Test
//    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {
//    }
//
//    @Test
//    public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
//    }
//
//    @Test
//    public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
//    }
}
