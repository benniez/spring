package com.bennie.spring.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bennie.spring.domain.Person;
import com.bennie.spring.domain.PersonList;
import com.bennie.spring.service.PersonService;

@Controller
@RequestMapping("/rest")
public class RestProviderController {
	private static final String ACCEPT_TYPE = "Accept=application/xml,application/json";

	private static final Logger logger = LoggerFactory
			.getLogger(RestProviderController.class);

	@Resource(name = "personService")
	private PersonService personService;

	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET, headers = "Accept=image/jpeg,image/jpg,image/png,image/gif")
	public @ResponseBody
	byte[] getPhoto(@PathVariable("id") Long id) throws IOException {

		InputStream is = this.getClass().getResourceAsStream("/bella.jpg");
		BufferedImage img = ImageIO.read(is);
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ImageIO.write(img, "jpg", bao);
		logger.debug("Retrieving photo as byte array image");
		return bao.toByteArray();
	}

	// "Accept=application/xml" or "Accept=application/json"
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET, headers = ACCEPT_TYPE)
	public @ResponseBody
	Person getPerson(@PathVariable("id") Integer id, Model model) {
		logger.debug("Provider has received request to get person with id: "
				+ id);
		model.addAttribute("person", personService.get(id));
		return personService.get(id);

	}

	// GET ALL PERSON
	@RequestMapping(value = "/persons", method = RequestMethod.GET, headers = ACCEPT_TYPE)
	public ResponseEntity<PersonList> getPersons() {

		PersonList pl = new PersonList();
		pl.setPerson(this.personService.getAll());
		return new ResponseEntity<PersonList>(pl, HttpStatus.OK);
	}

	// ADD PERSON
	@RequestMapping(value = "/person", method = RequestMethod.POST, headers = ACCEPT_TYPE)
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		logger.info("start adding person:" + person.toString());
		return new ResponseEntity<Person>(this.personService.add(person),
				HttpStatus.OK);
	}

	// UPDATE PERSON
	@RequestMapping(value = "/person/{id}", method = RequestMethod.PUT, headers = ACCEPT_TYPE)
	public ResponseEntity<String> updatePerson(@RequestBody Person person,
			@PathVariable("id") Integer id) {
		person.setId(id);
		return new ResponseEntity<String>(this.personService.edit(person)
				.toString(), HttpStatus.OK);
	}

	// Delete PERSON
	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE, headers = ACCEPT_TYPE)
	public ResponseEntity<String> deletePerson(@PathVariable("id") Integer id) {
		return new ResponseEntity<String>(this.personService.delete(id)
				.toString(), HttpStatus.OK);
	}
}
