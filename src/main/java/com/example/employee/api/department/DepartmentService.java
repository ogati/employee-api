package com.example.employee.api.department;

import java.util.List;

public interface DepartmentService {
	
	List<Department> getAllDepartments();
	
	Department getDepartmentByNameIgnoreCase(String name);
	
	List<Department> getDepartmentsByNameContainingIgnoreCase(String name);
	
	long getDepartmentCount();
}
