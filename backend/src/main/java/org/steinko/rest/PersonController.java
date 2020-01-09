package org.steinko.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import  org.springframework.http.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController("persomController")
@RequestMapping(value = "/person")
public class PersonController {
	
	private static Logger logger = LogManager.getLogger(PersonController.class);
	private Map<String,Person> persons;
	
	public PersonController() {
	  persons = new HashMap<String,Person>();
	  persons.put("1",new Person("1","Oddmund",  "Korsveien"));
	  persons.put("2",new Person("2","Stein",  "Korsveien"));
	}
	
	@GetMapping
	public ResponseEntity<Person> getPerson(@RequestParam("id") String id) {
		logger.info("get id: " +  id);
		Person selected = persons.get(id);
		logger.info("get selected:" + selected.toString());
		ResponseEntity<Person> response = new ResponseEntity<Person>(selected,HttpStatus.OK);
		return response;
	}
	
	@PostMapping(value= "/create")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		Person aPerson =  person;
		logger.info("post:"+ person.getId()+ person.getFirstName()+ person.getFamilyName());
		return new ResponseEntity<Person> (person,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public  ResponseEntity<String> deletePerson( @PathVariable("id") int id) {
		logger.info("delete");
	    return new ResponseEntity<String>( HttpStatus.OK);
	}
	
	@PutMapping(value = "/update", headers="Accept=application/json")
	public ResponseEntity<String> updatePerson(@RequestBody Person person) 
	{
		logger.info("put: "+ person.toString());
		Person selected = persons.get(person.getId());
		logger.info("put selected:" + selected.toString());
		
		selected.setFirstName(person.getFirstName());
		selected.setFamilyName(person.getFamilyName());
		logger.info(selected.toString());
	    return new ResponseEntity<String>(HttpStatus.OK);
	}
	

}
