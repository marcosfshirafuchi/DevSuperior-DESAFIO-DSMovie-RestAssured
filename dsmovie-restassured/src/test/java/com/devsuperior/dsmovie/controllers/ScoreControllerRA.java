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

public class ScoreControllerRA {

	private Long nonExistingMovieId, missingMovieId;
	private String adminUsername, adminPassword, clientUsername, clientPassword;
	private String adminToken, clientToken;

	private Map<String, Object> putScoreInstance;

	@BeforeEach
	public void setUp() throws JSONException {
		baseURI = "http://localhost:8080";

		nonExistingMovieId = 100L;
		missingMovieId = null;

		adminUsername = "maria@gmail.com";
		adminPassword = "123456";
		clientUsername = "alex@gmail.com";
		clientPassword = "123456";

		adminToken = TokenUtil.obtainAccessToken(adminUsername, adminPassword);
		clientToken = TokenUtil.obtainAccessToken(clientUsername, clientPassword);

		putScoreInstance = new HashMap<>();
		putScoreInstance.put("movieId", 1);
		putScoreInstance.put("score", 4);
	}
	
	@Test
	public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist() throws Exception {
		putScoreInstance.put("movieId", nonExistingMovieId);
		JSONObject newScore = new JSONObject(putScoreInstance);
		given()
			.header("Content-type","application/json")
			.header("Authorization","Bearer " + clientToken)
			.body(newScore.toString())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/scores")
		.then()
			.statusCode(404);
	}
	
	@Test
	public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId() throws Exception {
		putScoreInstance.put("movieId", missingMovieId);
		JSONObject newScore = new JSONObject(putScoreInstance);
		given()
			.header("Content-type","application/json")
			.header("Authorization","Bearer " + adminToken)
			.body(newScore.toString())
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/scores")
		.then()
			.statusCode(422);
	}

	@Test
	public void saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero() throws Exception {
	}
}
