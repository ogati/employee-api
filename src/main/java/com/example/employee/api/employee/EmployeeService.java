package com.example.employee.api.employee;

import java.util.List;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	List<Employee> getEmployeesAboveDepartmentAverage();
	
	Employee getEmployeeById(Long id);
	
	Employee getEmployeeByIdWithDepartment(Long id);

	List<Employee> getEmployeesByDepartmentId(Long id);

	List<Employee> search(EmployeeSearchCriteria criteria);
	
	Employee createEmployee(EmployeeCreateRequest request);
	
	Employee updateEmployee(Long id, EmployeeUpdateRequest request);
	
	void deleteEmployee(Long id);
	
	long count();
}
