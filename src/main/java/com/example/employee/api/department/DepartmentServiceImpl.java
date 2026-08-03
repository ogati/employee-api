package com.example.employee.api.department;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employee.api.exception.ResourceNotFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

	@Override
	@Transactional(readOnly = true)
	public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Department getDepartmentByNameIgnoreCase(String name) {
		return departmentRepository.findByNameIgnoreCase(name)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with name: " + name));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Department> getDepartmentsByNameContainingIgnoreCase(String name) {
		return departmentRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	@Transactional(readOnly = true)
	public long getDepartmentCount() {
		return departmentRepository.count();
	}
}
