package test.project;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import test.project.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonUnitTests {

	
	@Autowired
	private MockMvc mockMvc;
	private MvcResult mvcResult;
	private Person person;
	
	@BeforeEach
	void setUp() throws Exception {
				
	}
	
	
	@Test
	void registerPerson() throws Exception {
		person = new Person();
		person.setName("Miguel");
		person.setLast_name("Medina");
		person.setIdentification(999999999);
		person.setHeight(170);
		person.setAge(19);
		
		mvcResult = mockMvc.perform(post("/person/").
				contentType(MediaType.APPLICATION_JSON).
				content(mapToJson(person))).andExpect(status().isCreated()).andReturn();
	}
	
	
	@Test
	void getPeople() throws Exception {
		mockMvc.perform(get("/person/"))
		.andExpect(status().isOk());
	}
	
	@Test
	void deletePersonById() throws Exception{
		mockMvc.perform(delete("/person/delete/"+19))
		.andExpect(status().isOk());
	}
	
	protected String mapToJson(Object obj) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
