package org.steinko.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class PersonController {
	
	@RequestMapping(value = "/person")
	public Person getPerson() {
		return new Person("Stein", "Korsveien");
	}
	
	@PostMapping("/person/")
	public ResponseEntity<String> createPerson(@RequestBody Person person) {
		Person aPerson =  person;
		return new ResponseEntity<String> (HttpStatus.CREATED);
	}

}
