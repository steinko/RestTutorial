package org.steinko.rest;

/**
 * The code for the Cucmber Feature Person API Test
 */

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.Matchers.equalTo;

import java.net.URISyntaxException;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class PersonAPITest {
	
	@JsonPropertyOrder({  "id", "firstName", "familyName" })
	public class JsonPerson {
	    public int id;
	    public String firstName;
	    public String familyName;
	  
	   public JsonPerson(int id, String firstName, String familyName) {
		this.id = id;
		this.firstName = firstName;
		this.familyName = familyName;
	   }   
	 }  
	
	private static Logger logger = LogManager.getLogger(PersonAPITest.class);
	private Response response;
	
	@BeforeEach
	public void setUp() {
		RestAssured.baseURI = "http://localhost:8080";
	}
	
	
	/**
	 * Scenario Get the Person
	 */
	
	@Given("Person Exist")
	public void person_Exist() {
		
	}
	

	@When("I activet the get person")
	public void i_activet_the_get_person() {
		RestAssured.baseURI = "http://localhost:8080";
		response =when().get( "/person");
	}

	@Then("I recive a Person")
	public void i_recive_a_Person() {
		response.then().statusCode(200)
	    .assertThat()
	      .body("firstName", equalTo("Stein"))
	      .body("familyName", equalTo("Korsveien"));
	    
	}
	
	/**
	 * Scenario Create Person
	 */
	
	@Given("Person do not exist")
    public void person_do_not_exist() {
		
       
    }

    @When("I want to create a person with first name {string} and family name {string}")
    public void i_want_to_create_a_person_with_first_name_and_family_name(String firstName, String familyName) throws JsonProcessingException {
    	JsonPerson bean = new JsonPerson(1, firstName, familyName);
    	String json = convertToJson(bean);
    	RestAssured.baseURI = "http://localhost:8080";
    	response = given()
		          .contentType("application/json")
		    	  .body(json)
		          .when()
		          .post("person/");
    }


 
    @Then("The person is created with id")
    public void the_person_is_created_with_id() {
    	response.then().statusCode(201);
    }
    
    /**
	 * Scenario Delete Person
	 */

    @Given("Person with id {int} do  exist")
    public void person_with_id_do_exist(Integer id) {
    	
    }
    
   

    @When("I want to delete a person with id {int}")
    public void i_want_to_delete_a_person_with_id(Integer id) {
    	RestAssured.baseURI = "http://localhost:8080";
    	String url = "/person/" + id.toString();
    	response = given()
                   .when ()
                   .contentType ("application/json")
                   .delete (url );
    }

    @Then("The person is deleted")
    public void the_person_is_deleted() {
    	response.then().statusCode(200);
    }
    
    
    /**
   	 * Scenario Update Person
   	 */

    
    @Given("Person with id {int} exist with first name {string} and familiy name {string}")
    public void person_with_id_exist_with_first_name_and_familiy_name(Integer int1, String string, String string2) {
       
    }

    @When("I want to update the person with {int} with first name {string} familiy name {string}")
    public void i_want_to_update_the_person_with_with_first_name_familiy_name(Integer id, String firstName, String familyName) throws JsonProcessingException {
    	RestAssured.baseURI = "http://localhost:8080";
    	String url = "/person/" + id.toString();
    	JsonPerson bean = new JsonPerson(1, "Sture", "Stureson");
    	String json = convertToJson(bean);
    	response = given()
		          .contentType("application/json")
		    	  .body(json)
		          .when()
		          .put(url);
    }

	

    @Then("The persons first name and fammiliy name is updated")
    public void the_persons_first_name_and_fammiliy_name_is_updated() {
    	response.then().statusCode(200);
       
    }
    

    
    private String convertToJson(JsonPerson bean) throws JsonProcessingException {
    	
    	String json;
		try {	
    	   json = new ObjectMapper().writeValueAsString(bean);
        } 
    	catch (JsonProcessingException jsonProcessing) {
			logger.error(jsonProcessing.getMessage());
			throw(jsonProcessing);	
        }
		return json;
	}


}
