package com.restassured.StepDefination;

import java.util.Properties;

import org.json.simple.JSONObject;
import org.junit.Assert;

import com.restassured.Utility.Utility;
import static com.restassured.Constants.Constants.OPERATION_SUCCESS;
import static com.restassured.Constants.Constants.OPERATION_COMPLETED_SUCCESSFULLY;
import static com.restassured.Constants.Constants.FAULT_USER_ALREADY_EXISTS;
import static com.restassured.Constants.Constants.USER_ALREADY_EXIST;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefination_EmployeeService {
	private Response response;
	private Utility readPropertyFile;
	private Properties properties;
	RequestSpecification request;
	
	private String firstName, lastName, userName, password, email;
	
	@Given("^the admin wants to add a new employee in the system$")
	public void the_admin_wants_to_add_a_new_employee_in_the_system() {
		request = RestAssured.given();
		readPropertyFile = new Utility();
		properties = readPropertyFile.ReadPropertyFile();
	}

	@When("^he call the api to add the employee information$")
	public void he_call_the_api_to_add_the_employee_information() {
	    
		firstName = Utility.generateRandomAlphabetString(10);
		lastName = Utility.generateRandomAlphabetString(6);
		userName = Utility.generateRandomAlphabetString(8);
		password = Utility.generateRandomAlphabetString(9);
		email = (Utility.generateRandomAlphabetString(5)+"@gmail.com");
		
		JSONObject inputRequest = getJsonRequest();
		request.body(inputRequest.toJSONString());
		System.out.println(properties.getProperty("createEmployeeURL"));
		response = request.when().post(properties.getProperty("createEmployeeURL"));
	}

	@Then("^the employee is added in the system$")
	public void the_employee_is_added_in_the_system() {
		response.then().statusCode(200);
		Assert.assertEquals(OPERATION_SUCCESS,response.jsonPath().get("SuccessCode").toString().trim());
		Assert.assertEquals(OPERATION_COMPLETED_SUCCESSFULLY,response.jsonPath().get("Message").toString().trim());
	}

	@When("^the admin tries to add the same employee again$")
	public void the_admin_tries_to_add_the_same_employee_again() {
		response = request.when().post(properties.getProperty("createEmployeeURL"));
	}

	@Then("^he should get an error$")
	public void he_should_get_an_error() {
		response.then().statusCode(200);
		Assert.assertEquals(USER_ALREADY_EXIST,response.jsonPath().get("FaultId").toString().trim());
		Assert.assertEquals(FAULT_USER_ALREADY_EXISTS,response.jsonPath().get("fault").toString().trim());
	}
	
	private JSONObject getJsonRequest() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", firstName); 
		requestParams.put("LastName", lastName);
		 
		requestParams.put("UserName", userName);
		requestParams.put("Password", password);
		requestParams.put("Email",  email);
		return requestParams;
	}
}
