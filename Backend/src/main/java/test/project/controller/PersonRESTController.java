package test.project.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.project.model.Person;
import test.project.service.PersonService;

@RestController
@RequestMapping("/person/")
public class PersonRESTController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping
	private ResponseEntity<List<Person>> getAllPersons(){
		return ResponseEntity.ok(personService.findAll());
	}
	
	@PostMapping
	private ResponseEntity<Person> savePerson(@RequestBody Person person){
		try {
			if(personService.getByIdentification(person.getIdentification()) != null ) {
				return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
			}else {
			Person personSaved = personService.save(person);
			return ResponseEntity.created(new URI("/person/"+personSaved.getId())).body(personSaved);
			}
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}	
	}
	
	
	@DeleteMapping (value = "delete/{id}")
	private ResponseEntity<Boolean> deletePerson(@PathVariable ("id") long id){
		try {
			personService.deleteById(id);
			return ResponseEntity.ok(!(personService.findById(id)!=null));
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}