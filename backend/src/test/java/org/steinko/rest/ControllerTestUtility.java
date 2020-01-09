package org.steinko.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControllerTestUtility {
	
	private static Logger logger = LogManager.getLogger(ControllerTestUtility.class);
	
	public static String convertToJson(Person person) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		   String jsonPerson;
		   try {
		     jsonPerson = mapper.writeValueAsString(person);
		   } catch(JsonProcessingException ex)
		   {
			   
			   logger.error(ex.getMessage());
			   throw ex ;
		   }
		return jsonPerson;
	}

}
