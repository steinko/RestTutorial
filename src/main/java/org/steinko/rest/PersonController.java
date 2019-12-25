package org.steinko.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@CrossOrigin(origins = "*")
@RestController("persomController")
@RequestMapping("/person")
public class PersonController {
	
	private static Logger logger = LogManager.getLogger(PersonController.class);
	
	@RequestMapping
	public Person getPerson() {
		logger.info("get");
		return new Person(2,"Stein", "Korsveien");
	}
	
	@PostMapping("/")
	public ResponseEntity<String> createPerson(@RequestBody Person person) {
		Person aPerson =  person;
		logger.info("post:"+ person.getId().toString()+ person.getFirstName()+ person.getFamilyName());
		return new ResponseEntity<String> (HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<String> deletePerson( @PathVariable("id") int id) {
		logger.info("delete");
	    return new ResponseEntity<String>( HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> updateEmployee(@PathVariable("id") int id, @RequestBody Person person) 
	{
		logger.info("put:"+ person.getId().toString()+ person.getFirstName()+ person.getFamilyName());
	    return new ResponseEntity<Person>( HttpStatus.OK);
	}
	

}
