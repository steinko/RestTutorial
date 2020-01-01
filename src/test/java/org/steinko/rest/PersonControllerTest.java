package org.steinko.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.springframework.beans.factory.annotation.Autowired;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.get;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.OK;
import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonProcessingException;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
	
	private static Logger logger = LogManager.getLogger(PersonController.class);
	   
  @Test
  public void shouldReturnPersonDetails() throws Exception,JsonProcessingException {
    Person person  = new Person(2,"Stein","Korsveien");
    String jsonPerson = ControllerTestUtility.convertToJson(person);
	
    String url = "/person";
   
    given()
     .standaloneSetup(new PersonController()).
    when()
       .get(url).
    then()
        .log().ifValidationFails()
        .statusCode(OK.value())
        .contentType(JSON)
        .body(equalTo(jsonPerson));
  }

  @Test
  public void createProduct() throws Exception, JsonProcessingException {
  
     String uri = "/person";
     Person person = new Person(1,"Anne","Korsveien");
 
     String jsonPerson = ControllerTestUtility.convertToJson(person);
   
     given().
       standaloneSetup(new PersonController()).
       body(jsonPerson).
     when().
       post(uri).
     then().
       log().ifValidationFails().
       statusCode(OK.value()).
       contentType(JSON);
}

  @Test
  public void deleteProduct() throws Exception {
	  
	  String uri = "/person/1";
	  given().
	     standaloneSetup(new PersonController()).
	   when().
	     delete(uri).
	  then().
	     log().ifValidationFails().
	     statusCode(OK.value());  
  }
  
  @Test
  public void udateProduct() throws Exception,JsonProcessingException {
	  String uri = "/person";
	  Person person = new Person(1,"Upadate","Name");
	  String jsonPerson = ControllerTestUtility.convertToJson(person);
	  
	   given().
	     queryParam("id",1).
	     standaloneSetup(new PersonController()).
	     body(jsonPerson).
	   when().
	     put(uri).
	  then().
	     log().ifValidationFails().
	     statusCode(OK.value()).
	     contentType(JSON);
	  
  }

}
