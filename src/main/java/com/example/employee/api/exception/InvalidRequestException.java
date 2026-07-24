package com.example.employee.api.exception;

public class InvalidRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public InvalidRequestException(String error) {
        super(error);
    }
}
