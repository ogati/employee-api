package com.example.employee.api.department;

import java.util.List;

public record DepartmentResponse(Long id, String name) {
	public static List<DepartmentResponse> from(List<Department> departments) {
		return departments.stream()
        		.map(d -> new DepartmentResponse(d.getId(), d.getName()))
        		.toList();
	}
}
