package com.example.employee.api.department;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee.api.exception.ResourceNotFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

	@Override
	public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentByNameIgnoreCase(String name) {
		return departmentRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with name: " + name));
	}

	@Override
	public List<Department> getDepartmentsByNameContainingIgnoreCase(String name) {
		return departmentRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public long getDepartmentCount() {
		return departmentRepository.count();
	}
}
