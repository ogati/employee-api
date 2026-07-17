package com.example.employee.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleResourceNotFound(EmployeeNotFoundException ex) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
	}

//	@ExceptionHandler
//	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
//		return ResponseEntity
//				.status(HttpStatus.BAD_REQUEST)
//				.body(new ErrorResponse("Validation failed", HttpStatus.BAD_REQUEST.value()));
//	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value())); 
	}
}
