package com.example.employee.api.department;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.api.common.dto.CountResponse;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
        return ResponseEntity.ok(DepartmentResponse.from(departmentService.getAllDepartments()));
	}
	
	@GetMapping("/count")
	public CountResponse getDepartmentCount() {
		return new CountResponse(departmentService.getDepartmentCount());
	}
}
