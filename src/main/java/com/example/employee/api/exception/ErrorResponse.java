package com.example.employee.api.exception;

import java.time.Instant;

public record ErrorResponse(String error, int status, String timestamp) {
	
	public ErrorResponse(String error, int status) {
        this(error, status, Instant.now().toString());
    }
}
