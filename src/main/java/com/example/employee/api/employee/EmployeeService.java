package com.example.employee.api.employee;

import java.util.List;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(Long id);
	
	Employee createEmployee(Employee employee);
	
	Employee updateEmployee(Long id, Employee employee);
	
	void deleteEmployee(Long id);
	
	long getEmployeeCount();
}
