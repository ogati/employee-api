package com.example.employee.api.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	private List<Employee> employees = new ArrayList<>();
	
    @GetMapping("employees")
    public List<Employee> getEmployees() {
    	if (employees.isEmpty()) {
    		return employees;
    	}
    	
        return employees;
    }
}
