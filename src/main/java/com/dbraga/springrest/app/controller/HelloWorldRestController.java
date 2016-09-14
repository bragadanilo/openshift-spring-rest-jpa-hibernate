package com.dbraga.springrest.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dbraga.springrest.app.dao.PersonDAO;
import com.dbraga.springrest.app.domain.Person;

@RestController
public class HelloWorldRestController {

	@Autowired
	private PersonDAO personDAO;

	@RequestMapping("/")
	public String welcome() {
		return "index";
	}

	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = "application/json")
	public List<Person> getAll() {

		List<Person> personList = personDAO.getAll();
		System.out.println(personList);

		return personList;
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET, produces = "application/json")
	public Person getById(@PathVariable String id) {

		Person person = personDAO.searchById(Long.valueOf(id));
		System.out.println(person);

		return person;
	}
	
	@RequestMapping(value = "/person", method = RequestMethod.POST, produces="application/json")
	public Person postMethod(@RequestBody Person input) throws Exception {
		// HTTP body example: { "name": "dan", "email": "any text" }
		
		Person addedPerson = personDAO.save(input);
		
		System.out.println("Person received" + input + " added: "+addedPerson);
		return input;
	}
	
	@RequestMapping(value = "/person/{id}", method = RequestMethod.PUT, produces="application/json")
	public Person putMethod(@PathVariable Long id, @RequestBody Person person) throws Exception {
		// HTTP body example: { "name": "dan", "email": "any text" }
		
		Person personDB = personDAO.searchById(id);
		personDB.setName(person.getName());
		personDB.setEmail(person.getEmail());
		
		personDB = personDAO.update(personDB);
				
		return personDB;
	}
	
	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE, produces="application/json")
	public void deleteMethod(@PathVariable Long id) throws Exception {
		
		personDAO.delete(personDAO.searchById(id));		
	}

}
