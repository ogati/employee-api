package com.example.employee.api.employee;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.api.common.dto.CountResponse;
import com.example.employee.api.exception.InvalidRequestException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping(params = "filter")
	public ResponseEntity<List<EmployeeResponse>> getEmployeesAboveDepartmentAverage(@RequestParam String filter) {
		if (!Objects.equals(filter, "aboveDepartmentAverage")) {
			throw new InvalidRequestException("Missing request parameter: filter=aboveDepartmentAverage");
		}
		
    	List<Employee> employees = employeeService.getEmployeesAboveDepartmentAverage();
    	return ResponseEntity.ok(EmployeeResponse.from(employees));
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
    	Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(EmployeeResponse.from(employee));
    }
    
    @GetMapping(path = "/{id}", params = "include")
    public ResponseEntity<EmployeeResponse> getEmployeeByIdWithDepartment(
    		@PathVariable Long id, @RequestParam String include) {
    	if (!Objects.equals(include, "department")) {
			throw new InvalidRequestException("Missing request parameter: include=department");
    	}
    	
    	Employee employee = employeeService.getEmployeeByIdWithDepartment(id);
        return ResponseEntity.ok(EmployeeResponse.from(employee));
    }
    
    @GetMapping(params = "departmentId")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartmentId(@RequestParam Long departmentId) {
    	List<Employee> employees = employeeService.getEmployeesByDepartmentId(departmentId);
    	return ResponseEntity.ok(EmployeeResponse.from(employees));
    }
    
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> search(EmployeeSearchCriteria criteria) {
    	return ResponseEntity.ok(EmployeeResponse.from(employeeService.search(criteria)));
    }
    
    @GetMapping("/count")
    public CountResponse getEmployeeCount() {
    	return new CountResponse(employeeService.count());
    }
    
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeCreateRequest request) {
    	Employee createdEmployee = employeeService.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeResponse.from(createdEmployee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
    		@PathVariable Long id, 
    		@Valid @RequestBody EmployeeUpdateRequest request) {
    	Employee employee = employeeService.updateEmployee(id, request);
    	return ResponseEntity.ok(EmployeeResponse.from(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    	employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}
