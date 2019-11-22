package com.restassured.StepDefination;

import java.util.Properties;

import org.junit.Assert;

import com.restassured.Utility.Utility;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StepDefination_StarwarsAPI {
	private Response response;
	private Utility readPropertyFile;
	private Properties properties;
		
	@Given("^the user wants to know the names of starwars movies$")
	public void the_user_wants_to_know_the_names_of_starwars_movies() {
		RestAssured.given().contentType(ContentType.JSON); 
		readPropertyFile = new Utility();
		properties = readPropertyFile.ReadPropertyFile();
	}

	@When("^the user call the starwars api to know the \"([^\"]*)\" movie name$")
	public void the_user_call_the_starwars_api_to_know_the_movie_name(String movieVersion) {
		String firstDigit = movieVersion.substring(0,1);
		response = RestAssured.when().get(properties.getProperty("starwarMoviesURL")+firstDigit);
	}

	@Then("^the api gives the requested movie \"([^\"]*)\"$")
	public void the_api_gives_the_requested_movie(String movieName) {
		response.then().statusCode(200);
		Assert.assertTrue(movieName.equals(response.jsonPath().get("title").toString().trim()));
	}

	@Given("^the user wants to know the names of warships in starwars movies$")
	public void the_user_wants_to_know_the_names_of_warships_in_starwars_movies() {
		RestAssured.given().contentType(ContentType.JSON); ;
		readPropertyFile = new Utility();
		properties = readPropertyFile.ReadPropertyFile();
	}

	@When("^the user call the starwars api to know the name of the warship (\\d+)$")
	public void callStarWarsWarshipApi(int id) {
		response = RestAssured.when().get(properties.getProperty("starwarStarshipsURL")+id);
	}

	@Then("^the api gives the \"([^\"]*)\" and \"([^\"]*)\" of the warship$")
	public void verifyNameandModelOfWarship(String name, String model) {
		response.then().statusCode(200);
		Assert.assertTrue(name.equals(response.jsonPath().get("name").toString().trim()));
		Assert.assertTrue(model.equals(response.jsonPath().get("model").toString().trim()));
	}
	
	@Given("^the user wants to know the names of planets in starwars movies$")
	public void the_user_wants_to_know_the_names_of_planets_in_starwars_movies() {
		RestAssured.given().contentType(ContentType.JSON);
		readPropertyFile = new Utility();
		properties = readPropertyFile.ReadPropertyFile();
	}

	@When("^the user call the starwars api to know the name of the planet (\\d+)$")
	public void the_user_call_the_starwars_api_to_know_the_name_of_the_planet(int id) {
		response = RestAssured.when().get(properties.getProperty("starwarPlanetsURL")+id);
	}

	@Then("^the api gives \"([^\"]*)\"$")
	public void responseNameOfThePlanet(String planetName) {
		response.then().statusCode(200);
		Assert.assertTrue(planetName.equals(response.jsonPath().get("name").toString().trim()));
	}
	
}
