package com.bennie.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.bennie.spring.domain.Person;
import com.bennie.spring.domain.PersonList;
import com.bennie.spring.util.Writer;

/**
 * REST Service Client
 */

/*
 * @RequestMapping(value = "/getphoto", method = RequestMethod.GET) , id
 * 
 * @RequestMapping(value = "/getall", method = RequestMethod.GET)
 * 
 * @RequestMapping(value = "/get", method = RequestMethod.GET)
 * 
 * @RequestMapping(value = "/add", method = RequestMethod.GET)
 * 
 * @RequestMapping(value = "/add", method = RequestMethod.POST)
 * 
 * @RequestMapping(value = "/update", method = RequestMethod.GET)
 * 
 * @RequestMapping(value = "/update", method = RequestMethod.POST)
 * 
 * @RequestMapping(value = "/delete", method = RequestMethod.GET)
 */

@Controller
@RequestMapping("/admin")
public class RestClientController {

	private static final MediaType REST_REQUEST_TYPE = MediaType.APPLICATION_JSON;
	// private static final MediaType REST_REQUEST_TYPE =
	// MediaType.APPLICATION_XML;
	// define REST Service URI
	private static final String REST_URI_GETPHOTO = "http://localhost:8080/spring/rest/person/{id}";
	private static final String REST_URI_GET_PERSON = "http://localhost:8080/spring/rest/person/{id}";
	private static final String REST_URI_GET_ALL_PERSON = "http://localhost:8080/spring/rest/persons";
	private static final String REST_URI_ADD_PERSON = "http://localhost:8080/spring/rest/person";
	private static final String REST_URI_UPDATE_PERSON = "http://localhost:8080/spring/rest/person/{id}";
	private static final String REST_URI_DELETE_PERSON = "http://localhost:8080/spring/rest/person/{id}";

	private static final Logger logger = LoggerFactory
			.getLogger(RestClientController.class);

	private final RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/getphoto", method = RequestMethod.GET)
	public void getPhoto(@RequestParam("id") Long id,
			HttpServletResponse response) {

		logger.info("Retrieve photo with id:" + id);

		HttpEntity<String> entity = new HttpEntity<String>(
				buildRequestHeader(MediaType.IMAGE_JPEG));

		// send request as GET
		ResponseEntity<byte[]> result = this.restTemplate.exchange(
				REST_URI_GETPHOTO, HttpMethod.GET, entity, byte[].class, id);
		// display the image
		Writer.write(response, result.getBody());
	}

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ModelAndView getAllPerson() {
		HttpEntity<Person> entity = new HttpEntity<Person>(
				buildRequestHeader(REST_REQUEST_TYPE));
		ResponseEntity<PersonList> result = this.restTemplate.exchange(
				REST_URI_GET_ALL_PERSON, HttpMethod.GET, entity,
				PersonList.class);
		ModelAndView mav = new ModelAndView("personspage");
		mav.addObject("persons", result.getBody().getPerson());
		return mav;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ModelAndView getPerson(@RequestParam("id") Long id) {
		logger.info("display person info");
		HttpEntity<Person> entity = new HttpEntity<Person>(
				buildRequestHeader(REST_REQUEST_TYPE));
		ResponseEntity<Person> result = this.restTemplate.exchange(
				REST_URI_GET_PERSON, HttpMethod.GET, entity, Person.class, id);
		ModelAndView mav = new ModelAndView("getpage");
		mav.addObject("person", result.getBody());
		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView getAddPerson() {
		logger.info("Add person page display");
		ModelAndView mav = new ModelAndView("addpage");
		mav.addObject("personAttribute", new Person());
		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView getAddPerson(
			@ModelAttribute("personAttribute") Person person) {
		logger.info("Add person action");

		HttpEntity<Person> entity = new HttpEntity<Person>(person,
				buildRequestHeader(REST_REQUEST_TYPE));
		ResponseEntity<Person> result = this.restTemplate.exchange(
				REST_URI_ADD_PERSON, HttpMethod.POST, entity, Person.class);
		ModelAndView mav = new ModelAndView("resultpage");
		result.getStatusCode();
		return mav;

	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updatePerson(
			@RequestParam(value = "id", required = true) Integer id) {
		logger.info("Update Person page display");
		HttpEntity<Person> entity = new HttpEntity<Person>(
				buildRequestHeader(REST_REQUEST_TYPE));
		ResponseEntity<Person> result = this.restTemplate.exchange(
				REST_URI_GET_PERSON, HttpMethod.GET, entity, Person.class, id);
		ModelAndView mav = new ModelAndView("updatepage");
		mav.addObject("personAttribute", result.getBody());
		return mav;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updatePerson(
			@ModelAttribute("personAttribute") Person person,
			@RequestParam(value = "id", required = true) Long id) {
		logger.info("Update Person Action");
		HttpEntity<Person> entity = new HttpEntity<Person>(person,
				buildRequestHeader(REST_REQUEST_TYPE));
		ResponseEntity<String> result = this.restTemplate.exchange(
				REST_URI_UPDATE_PERSON, HttpMethod.PUT, entity, String.class,
				id);
		ModelAndView mav = new ModelAndView("resultpage");
		mav.addObject("reuslt", result);
		return mav;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deletePerson(@RequestParam("id") Long id) {
		logger.info("delete existing person");

		HttpEntity<String> entity = new HttpEntity<String>(
				buildRequestHeader(REST_REQUEST_TYPE));

		ResponseEntity<String> result = restTemplate.exchange(
				REST_URI_DELETE_PERSON, HttpMethod.DELETE, entity,
				String.class, id);
		ModelAndView mav = new ModelAndView("resultpage");
		mav.addObject("result", result);
		return mav;
	}

	private HttpHeaders buildRequestHeader(MediaType mediaType) {
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(mediaType);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		return headers;
	}

}
