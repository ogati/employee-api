package com.example.employee.api.employee;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee.api.department.Department;
import com.example.employee.api.department.DepartmentRepository;
import com.example.employee.api.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
	public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
	public Employee getEmployeeById(Long id) {
	    return employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
	}

	@Override
	public List<Employee> search(EmployeeSearchCriteria criteria) {
		return employeeRepository.findAll(EmployeeSpecification.withCriteria(criteria));
	}
	
	@Override
	public long count() {
		return employeeRepository.count();
	}
	
	@Override
    public Employee createEmployee(EmployeeCreateRequest request) {
		Department department = departmentRepository.findById(request.departmentId())
			.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.departmentId()));
		
		Employee employee = new Employee();
		employee.setName(request.name());
		employee.setSalary(request.salary());
		employee.setDepartment(department);
		
        return employeeRepository.save(employee);
    }

	@Override
	public Employee updateEmployee(Long id, EmployeeUpdateRequest request) {		
	    Employee existing = employeeRepository.findById(id)
		    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		Department department = departmentRepository.findById(request.departmentId())
			.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.departmentId()));

	    existing.setSalary(request.salary());
	    existing.setDepartment(department);

	    return employeeRepository.save(existing);
	}

	@Override
	public void deleteEmployee(Long id) {
	    if (!employeeRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Employee not found with id: " + id);
	    }

	    employeeRepository.deleteById(id);
	}
}
