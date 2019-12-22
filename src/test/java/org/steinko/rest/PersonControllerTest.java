package org.steinko.rest;

import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PersonControllerTest {
	private static Logger logger = LogManager.getLogger(PersonController.class);
	

	private MockMvc mvc;
	
	@BeforeEach
	void setUp() {
		mvc= MockMvcBuilders.standaloneSetup(new PersonController()).build();
	}
	
	
@Test
public void getProductsList() throws Exception {
   String uri = "/person";
   String person = "{\"fisrtName\":\"Stein\",\"familyName\":\"Korsveien\"}";
		       
   MvcResult mvcResult = mvc.perform(get(uri)
      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
   
   int status = mvcResult.getResponse().getStatus();
   assertEquals(200, status);
   String content = mvcResult.getResponse().getContentAsString();
   assertEquals( content, "{\"firstName\":\"Stein\",\"familyName\":\"Korsveien\"}");
}

@Test
public void createProduct() throws Exception {
  
   String uri = "/person/";
   Person person = new Person("Anne","Korsveien");
 
   ObjectMapper mapper = new ObjectMapper();
   
   String inputJson = mapper.writeValueAsString(person);
   MvcResult mvcResult = mvc.perform(post(uri)
      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
   
   int status = mvcResult.getResponse().getStatus();
   assertEquals(201, status);
}

}
