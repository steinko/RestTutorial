package org.steinko.rest;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import io.restassured.response.Response;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PersonAPITest {
	
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

}
