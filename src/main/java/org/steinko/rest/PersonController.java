package org.steinko.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class PersonController {
	
	@RequestMapping(value = "/person")
	public Person getPersion() {
		return new Person();
	}

}
