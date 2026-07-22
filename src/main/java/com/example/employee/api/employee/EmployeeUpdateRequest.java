package com.example.employee.api.employee;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmployeeUpdateRequest(

	@NotNull
	@Positive
	Long salary,
	
	@NotNull
	Long departmentId
) {}
