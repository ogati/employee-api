package com.example.employee.api.employee;

import java.util.List;

public record EmployeeResponse(Long id, String name, Long salary, String departmentName) {
	
	public static EmployeeResponse from(Employee employee) {
		return new EmployeeResponse(
			employee.getId(),
			employee.getName(),
			employee.getSalary(),
			employee.getDepartment().getName()
		);
	}
	
	public static List<EmployeeResponse> from(List<Employee> employees) {
		return employees.stream()
				.map(e -> new EmployeeResponse(
						e.getId(), 
						e.getName(), 
						e.getSalary(), 
						e.getDepartment().getName()))
				.toList();
	}
}
