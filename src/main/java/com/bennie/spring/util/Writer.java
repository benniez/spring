package com.bennie.spring.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class Writer {

	private static final Logger logger = LoggerFactory.getLogger(Writer.class);

	@ExceptionHandler(IOException.class)
	public void HandlerIOException() {
		logger.error("Unable to write report to the output stream");

	}

	public static void write(HttpServletResponse response,
			ByteArrayOutputStream bao) throws IOException {

		logger.debug("Writing result to the stream");

		ServletOutputStream outputStream = response.getOutputStream();
		bao.writeTo(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	public static void write(HttpServletResponse response, byte[] body) {
		// TODO Auto-generated method stub

	}

}
