package org.steinko.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;
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
	private PersonService service;
	
	public PersonController() {
		service = new PersonService();
	  
	}
	
	@GetMapping
	public Person getPerson(@RequestParam("id") String id) {
		
		logger.info("get id: " +  id);
		Integer result = Integer.valueOf(id);	
		Person selected = service.getPersonById(result);
		logger.info("get selected:" + selected.toString());
		return selected;
	}
	
	@PostMapping(value= "/create")
	public Person createPerson(@RequestBody Person person) {
		logger.info("post:"+ person.getId()+ person.getFirstName()+ person.getFamilyName());
		return person;
	}
	
	
	@DeleteMapping(value = "/{id}")
	public  ResponseEntity<String> deletePerson( @PathVariable("id") int id) {
		logger.info("delete");
	    return new ResponseEntity<String>( HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update")
	public void updatePerson(@RequestBody Person person) 
	{
		logger.info("put: "+ person.toString());
		service.udatePersonById(person);
		logger.info("end controller put");
	   
	}
	

}
