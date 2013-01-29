package com.bennie.spring.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bennie.spring.service.ArithmeticService;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
	private static final Logger logger = LoggerFactory
			.getLogger(AjaxController.class);

	@Resource(name = "arithmeticService")
	private ArithmeticService arithmeticService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddPage() {
		return "ajax-add-page";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Integer add(
			@RequestParam(value = "inputNumber1", required = true) Integer inputNumber1,
			@RequestParam(value = "inputNumber2", required = true) Integer inputNumber2) {
		logger.debug("received Ajax request to add number");
		Integer sum = this.arithmeticService.add(inputNumber1, inputNumber2);
		return sum;
	}
}
