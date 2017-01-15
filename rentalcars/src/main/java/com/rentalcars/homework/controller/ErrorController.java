package com.rentalcars.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Class for proper exception handling.
 *
 */
@ControllerAdvice
public class ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	/**
	 * Handles any uncaught {@link Exception}.
	 * The exception will be logged in error level.
	 * 
	 * @param exception that was thrown.
	 * @return a {@link ResponseEntity} instance with {@link HttpStatus#INTERNAL_SERVER_ERROR} status code and empty body.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Void> handleException(Exception exception) {
		logger.error("Internal server error.", exception);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	/**
	 * Handles any uncaught {@link HttpMediaTypeNotSupportedException}.
	 * The exception will be logged in error level.
	 * 
	 * @param exception that was thrown.
	 * @return a {@link ResponseEntity} instance with {@link HttpStatus#UNSUPPORTED_MEDIA_TYPE} status code and empty body.
	 */
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Void> handleHttpMediaTypeNotSupportedException(Exception exception) {
		logger.error("Unsupported media type.", exception);
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
	}	
}