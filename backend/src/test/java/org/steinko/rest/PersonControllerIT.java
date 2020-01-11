package org.steinko.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.OK;
import static org.hamcrest.CoreMatchers.equalTo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.client.RestClientException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.get;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIT  {
	
	
	 @LocalServerPort
	 private  int localServerPort;
	 private String url;
	 private static Logger logger = LogManager.getLogger(PersonControllerIT.class);
	 
  @BeforeEach
  void setUp()  {
	url =  "http://localhost:" + localServerPort + "/person";
	logger.info(url);	
  }
  
  @Autowired
  private WebApplicationContext webApplicationContext;
   
  

  @Test
  void shoulReturnPerson() throws JsonProcessingException{
	  Person person  = new Person("2","Stein","Korsveien");
	   String jsonPerson = ControllerTestUtility.convertToJson(person);
	   
	   
	   given().
	       param("id","2")
	      .webAppContextSetup(webApplicationContext)
	   .when()
        .get(url)
      .then()
        .log().ifValidationFails()
        .statusCode(OK.value())
        .contentType(JSON)
        .body(equalTo(jsonPerson));
      
  }



 
  @Test
  void shouldCreatePerson() throws JsonProcessingException {
     Person person = new Person("1","Anne", "Korsveien");
     String jsonPerson = ControllerTestUtility.convertToJson(person);
     given().
        contentType("application/json").
        webAppContextSetup(webApplicationContext)
       .body(person).
     when()
       .post(url + "/create").
     then()
       .log().ifValidationFails()
       .statusCode(OK.value())
       .contentType(JSON);
       
 
 }

  @Test
  void shouldDeletePerson() 
  { 

	 url = url + "/1";
	 given()
	   .webAppContextSetup(webApplicationContext).
	 when()
	   .delete(url).
	 then()
	   .log().ifValidationFails()
       .statusCode(OK.value());
    }
    
  
   @Test  
   void shouldUpdatePerson() throws JsonProcessingException
  {
      Person person = new Person("1", "New Name", "Gilly");
      String jsonPerson = ControllerTestUtility.convertToJson(person);
      logger.info("start update product integration test");
      
      given().
         contentType("application/json").
         webAppContextSetup(webApplicationContext)
        .body(jsonPerson).
      when()
        .put(url + "/update").
      then()
        .log().ifValidationFails()
        .statusCode(OK.value());
      
      logger.info("end update product integration test");
  }
   
}
