package com.example.employee.api.employee;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.api.common.dto.CountResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(EmployeeResponse.from(employeeService.getEmployeeById(id)));
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
