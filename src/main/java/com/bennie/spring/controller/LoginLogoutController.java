package com.bennie.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class LoginLogoutController {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginLogoutController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(
			@RequestParam(value = "error", required = false) boolean error) {
		logger.debug("showing login page");
		ModelAndView mav = new ModelAndView("loginpage");
		if (error == true)
			mav.addObject("error",
					"You have enter an invalid username or password");
		else
			mav.addObject("error", "");
		return mav;

	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {
		logger.debug("showing denied page");
		return "deniedpage";
	}

}
