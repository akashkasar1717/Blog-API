package com.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Globanexceptionhandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handlingResourceNotFoundException(ResourceNotFoundException ex) {
		String msg = ex.getMessage();
		return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handlingMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		Map<String, String> res = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String Fieldname = ((FieldError) error).getField();
			String message = error.getDefaultMessage();

			res.put(Fieldname, message);
		});

		return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);

	}

}
