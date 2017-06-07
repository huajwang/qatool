package com.nuance.qa.tool;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandler {
	
	@ExceptionHandler(NullPointerException.class)
	public String nullPointerExceptionHandler(Exception ex) {
		System.out.println("null pointer exception is thrown: " + ex.getMessage());
		return "error/a";
	}
}
