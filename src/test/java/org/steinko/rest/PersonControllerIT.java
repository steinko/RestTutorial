package org.steinko.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIT {
	
	 @LocalServerPort
	  int localServerPort;

@Test
void shouldReponsOK() {
  String url =  "http://localhost:" + localServerPort + "/person/";
  TestRestTemplate testRestTemplate = new TestRestTemplate();
  ResponseEntity<String> response = testRestTemplate.
  getForEntity(url, String.class);
  assertEquals(response.getStatusCode(), HttpStatus.OK);
}
}

