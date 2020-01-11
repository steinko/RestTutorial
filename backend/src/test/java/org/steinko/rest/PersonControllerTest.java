package org.steinko.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class PersonControllerTest {
	
	private static Logger logger = LogManager.getLogger(PersonController.class);
	
	@Mock
	private PersonService service;
	   
  @Test
  public void shouldReturnPersonDetailsforId2() throws Exception,JsonProcessingException {
    Person person  = new Person("2","Stein","Korsveien");
    String jsonPerson = ControllerTestUtility.convertToJson(person);
	
    String url = "/person";
    
     logger.info("start unit test shouldReturnPersonDetailsforId");
     
     when(service.getPersonById(2)).thenReturn(person);
				
     
    given()
     .param("id","2")
     .standaloneSetup(new PersonController()).
    when()
       .get(url).
    then()
        .log().ifValidationFails()
        .statusCode(OK.value())
        .contentType(JSON)
        .body(equalTo(jsonPerson));
    
    logger.info("end unit test shouldReturnPersonDetailsforId");
  }
  
  @Test
  public void shouldReturnPersonDetailsforId1() throws Exception,JsonProcessingException {
    Person person  = new Person("1","Oddmund","Korsveien");
    String jsonPerson = ControllerTestUtility.convertToJson(person);
	
    String url = "/person";
    
    logger.info("start unit test shouldReturnPersonDetailsforId 1");
    when(service.getPersonById(1)).thenReturn(person);
    
    given()
     .param("id","1")
     .standaloneSetup(new PersonController()).
    when()
       .get(url).
    then()
        .log().ifValidationFails()
        .statusCode(OK.value())
        .contentType(JSON)
        .body(equalTo(jsonPerson));
    
    logger.info("end unit test shouldReturnPersonDetailsforId 1");
  }

  
  @Test
  public void createProduct() throws Exception, JsonProcessingException {
  
     String uri = "/person/create";
     Person person = new Person("1","Anne","Korsveien");
 
     String jsonPerson = ControllerTestUtility.convertToJson(person);
   
     given().
       contentType("application/json").
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
  public void updateProduct() throws Exception,JsonProcessingException {
	  
	  String uri = "/person/update";
	  Person person = new Person("1","Updated","Name");
	  String jsonPerson = ControllerTestUtility.convertToJson(person);
	  
	  logger.info("start unit test update product");
	  logger.info(jsonPerson);
	   given().
	     contentType("application/json").
	     standaloneSetup(new PersonController()).
	     body(jsonPerson).
	   when().
	     put(uri).
	  then().
	     log().ifValidationFails().
	     statusCode(OK.value());
	   
	  
	   
	   logger.info("end unit test update product");
	  
  }

}
