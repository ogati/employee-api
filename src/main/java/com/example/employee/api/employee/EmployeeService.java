package com.example.employee.api.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
	
	List<EmployeeResponse> getAllEmployees();
	
	List<EmployeeResponse> getEmployeesAboveDepartmentAverage();
	
	EmployeeResponse getEmployeeById(Long id);
	
	EmployeeResponse getEmployeeByIdWithDepartment(Long id);

	List<EmployeeResponse> getEmployeesByDepartmentId(Long id);
	
	Page<EmployeeResponse> getEmployeesByDepartmentId(Long id, Pageable pageable);

	List<EmployeeResponse> search(EmployeeSearchCriteria criteria);
	
	EmployeeResponse createEmployee(EmployeeCreateRequest request);
	
	EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request);
	
	void deleteEmployee(Long id);
	
	long count();
}
