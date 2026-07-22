package com.example.employee.api.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmployeeCreateRequest(

	@NotBlank
	String name,
	
	@NotNull
	@Positive
	Long salary,
	
	@NotNull
	Long departmentId
) {}
