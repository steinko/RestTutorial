package org.steinko.rest;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class PersonAPITest {
	// private static Logger logger = LogManager.getLogger(PersonAPITest.class);
	
	private Response response;
	
	@Test
	public void whenRequestGet_thenOK(){ 
	    when().get( "/person")
	    .then().statusCode(200)
	    .assertThat()
	      .body("firstName", equalTo("Stein"))
	      .body("familyName", equalTo("Korsveien"));
	}
	
	@Given("Person Exist")
	public void person_Exist() {
		
	}

	@When("I activet the get person")
	public void i_activet_the_get_person() {
		response =when().get( "/person");
	}

	@Then("I recive a Person")
	public void i_recive_a_Person() {
		response.then().statusCode(200)
	    .assertThat()
	      .body("firstName", equalTo("Stein"))
	      .body("familyName", equalTo("Korsveien"));
	    
	}
	
	@Given("Person do not exist")
    public void person_do_not_exist() {
		
       
    }

    @When("I want to create a person with first name {string} and family name {string}")
    public void i_want_to_create_a_person_with_first_name_and_family_name(String firstName, String familyName) {
    	String person = "{\"fisrtName\":\" " + firstName + "\",\"familyName\":\" " + familyName + "\"}";
    	//logger.info(person);
    	response = given()
		          .contentType("application/json")
		    	  .body(person)
		          .when()
		          .post("person/");
    }



    @Then("The person is created")
    public void the_person_is_created() {
    	response.then().statusCode(201);
    }


}
