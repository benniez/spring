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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.bennie.spring.util.Writer;

/**
 * REST Service Client
 */
@Controller
public class RestClientController {

	private static final Logger logger = LoggerFactory
			.getLogger(RestClientController.class);

	private final RestTemplate restTemplate = new RestTemplate();

	public void getPhoto(@RequestParam("id") Long id,
			HttpServletResponse response) {

		logger.debug("Retrieve photo with id:" + id);

		// acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.IMAGE_JPEG);

		// header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		// send request as GET
		ResponseEntity<byte[]> result = this.restTemplate.exchange(
				"http://localhost:8080/spring-rest-provider/krams/person/{id}",
				HttpMethod.GET, entity, byte[].class, id);

		// display the image
		Writer.write(response, result.getBody());
	}

}
