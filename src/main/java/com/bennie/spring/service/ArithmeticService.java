package com.bennie.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("arithmeticService")
public class ArithmeticService {

	private static final Logger logger = LoggerFactory
			.getLogger(ArithmeticService.class);

	public Integer add(Integer operand1, Integer operand2) {
		logger.debug("Add 2 numbers");
		return operand1 + operand2;
	}
}
