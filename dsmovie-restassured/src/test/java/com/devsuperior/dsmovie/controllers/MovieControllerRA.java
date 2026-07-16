package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.tests.TokenUtil;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MovieControllerRA {

    private String existingMovieTitle, blankMovieTitle;
    private Long existingMovieId, nonExistingMovieId;
    private String adminUsername, adminPassword, clientUsername, clientPassword;
    private String adminToken, clientToken, invalidToken;

    private Map<String, Object> postMovieInstance;

    @BeforeEach
    void setUp() throws JSONException {
        baseURI = "http://localhost:8080";
        existingMovieTitle = "Matrix";
        existingMovieId = 7L;
        nonExistingMovieId = 100L;
        blankMovieTitle = "";

        adminUsername = "maria@gmail.com";
        adminPassword = "123456";
        clientUsername = "alex@gmail.com";
        clientPassword = "123456";

        adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
        clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);
        invalidToken = adminToken + "xpto"; // Invalid Token

        postMovieInstance = new HashMap<>();
        postMovieInstance.put("title","Test Movie");
        postMovieInstance.put("score",0.0);
        postMovieInstance.put("count",0);
        postMovieInstance.put("image","https://www.themoviedb.org/t/p/w533_and_h300_bestv2/jBJWaqoSCiARWtfV0GlqHrcdidd.jpg");
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

    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {
        postMovieInstance.put("title",blankMovieTitle);
        JSONObject newMovie = new JSONObject(postMovieInstance);
        given()
            .header("Content-type","application/json")
            .header("Authorization","Bearer " + adminToken)
            .body(newMovie.toString())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post("/movies")
        .then()
            .statusCode(422);
    }

    @Test
    public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
        JSONObject newMovie = new JSONObject(postMovieInstance);
        given()
            .header("Content-type","application/json")
            .header("Authorization","Bearer " + clientToken)
            .body(newMovie.toString())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post("/movies")
        .then()
            .statusCode(403);
    }

    @Test
    public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
        JSONObject newMovie = new JSONObject(postMovieInstance);
        given()
            .header("Content-type","application/json")
            .header("Authorization","Bearer " + invalidToken)
            .body(newMovie.toString())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post("/movies")
        .then()
            .statusCode(401);
    }
}
