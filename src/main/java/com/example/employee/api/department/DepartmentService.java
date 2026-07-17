package com.example.employee.api.department;

import java.util.List;

public interface DepartmentService {
	List<Department> getAllDepartments();
	
	long getDepartmentCount();
}
