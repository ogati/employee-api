package com.example.employee.api.department;

import java.util.List;

import org.springframework.stereotype.Service;

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
	public long getDepartmentCount() {
		return departmentRepository.count();
	}
}
