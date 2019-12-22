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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIT {
	
	 @LocalServerPort
	 private  int localServerPort;
	 private String url;
	 private URI uri;
	 private static Logger logger = LogManager.getLogger(PersonController.class);
	 
@BeforeEach
void setUp()  {
	url =  "http://localhost:" + localServerPort + "/person/";
	logger.info(url);
	
	try {
	    uri = new URI(url);
	} catch (URISyntaxException uriSyntax) {
		logger.error(uriSyntax.getMessage());
		
	} catch (NullPointerException nullPointer) {
		logger.error(nullPointer.getMessage());
	}

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
   Person person = new Person("Anne", "Korsveien");
   TestRestTemplate restTemplate = new TestRestTemplate();
   ResponseEntity<String> response = restTemplate.postForEntity(uri,person, String.class);
   assertEquals(response.getStatusCode(), HttpStatus.CREATED);

}
}
