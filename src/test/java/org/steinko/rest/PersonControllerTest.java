package org.steinko.rest;

import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonControllerTest {
@Test
public void getProductsList() throws Exception {
   String uri = "/person";
   String person = "{\"fisrtName\":\"Stein\",\"familyName\":\"Korsveien\"}";
		      
   
  
   MockMvc mvc;
   
   mvc= MockMvcBuilders.standaloneSetup(new PersonController()).build();
   
   MvcResult mvcResult = mvc.perform(get(uri)
      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
   
   int status = mvcResult.getResponse().getStatus();
   assertEquals(200, status);
   String content = mvcResult.getResponse().getContentAsString();
   assertEquals( content, "{\"firstName\":\"Stein\",\"familyName\":\"Korsveien\"}");
}

}
