package org.steinko.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.boot.web.server.LocalServerPort;
import java.net.URI;
import java.net.URISyntaxException;
import java.lang.NullPointerException;
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.client.RestClientException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIT  {
	
	 @LocalServerPort
	 private  int localServerPort;
	 private String url;
	 private URI uri;
	 private static Logger logger = LogManager.getLogger(PersonController.class);
	 
  @BeforeEach
  void setUp() throws URISyntaxException,NullPointerException {
	url =  "http://localhost:" + localServerPort + "/person/";
	logger.info(url);
	
	uri = getUri(url);

  }

  @Test
  void shouldReponsOK() {
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    ResponseEntity<String> response = testRestTemplate.
    getForEntity(url, String.class);
    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }

  @Test
  void shouldCreatePerson() {
     Person person = new Person(1,"Anne", "Korsveien");
     TestRestTemplate restTemplate = new TestRestTemplate();
     ResponseEntity<String> response = restTemplate.postForEntity(uri,person, String.class);
     assertEquals(response.getStatusCode(), HttpStatus.CREATED);
 }

  @Test
  void shouldDeletePerson() throws URISyntaxException, NullPointerException { 
	 TestRestTemplate restTemplate = new TestRestTemplate();
	 url = url + "/1";
	 uri = getUri(url);
	
	        try {
	        	restTemplate.delete(uri);
	        } catch (RestClientException restClient) {
				logger.error(restClient.getMessage());	
			}
    }
  
   @Test  
   void updateName() throws URISyntaxException,NullPointerException
  {
      url = url + "/1";
      uri = getUri(url);
        
      Person updatedPerson = new Person(1, "New Name", "Gilly");
       
      TestRestTemplate restTemplate = new TestRestTemplate();
      restTemplate.put ( uri, updatedPerson);
  }
   
   private URI getUri(String url) throws URISyntaxException,NullPointerException {
	    URI uri;
	   try {
		    uri = new URI(url);
		    return uri;
		} catch (URISyntaxException uriSyntax) {
			logger.error(uriSyntax.getMessage());
			throw(uriSyntax);
			
		} catch (NullPointerException nullPointer) {
			logger.error(nullPointer.getMessage());
			throw (nullPointer);
		}
	  
	   
   }
}
