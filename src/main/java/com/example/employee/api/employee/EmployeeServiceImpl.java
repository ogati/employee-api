package com.example.employee.api.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public List<EmployeeResponse> getAllEmployees() {
    	List<Employee> employees = employeeRepository.findAll();
    	return EmployeeResponse.from(employees);
    }

	@Override
	public List<EmployeeResponse> getEmployeesAboveDepartmentAverage() {
		List<Employee> employees = employeeRepository.findAllAboveDepartmentAverage();
		return EmployeeResponse.from(employees);
	}
	
    @Override
	public EmployeeResponse getEmployeeById(Long id) {
	    Employee employee = employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
	    return EmployeeResponse.from(employee);
	}

	@Override
	public EmployeeResponse getEmployeeByIdWithDepartment(Long id) {
		EmployeeResponse response= employeeRepository.findByIdWithDepartment(id)
				.map(EmployeeResponse::from)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		return response;
	}

	@Override
	public List<EmployeeResponse> getEmployeesByDepartmentId(Long id) {
		List<Employee> employees = employeeRepository.findByDepartmentId(id);
		return employees.stream().map(EmployeeResponse::from).toList();
	}
	
	@Override
	public Page<EmployeeResponse> getEmployeesByDepartmentId(Long id, Pageable pageable) {
		Page<Employee> employees = employeeRepository.findByDepartmentId(id, pageable);
		return employees.map(EmployeeResponse::from);
	}
	
	@Override
	public List<EmployeeResponse> search(EmployeeSearchCriteria criteria) {
		List<Employee> employees = employeeRepository.findAll(EmployeeSpecification.withCriteria(criteria));
		return employees.stream().map(EmployeeResponse::from).toList();
	}
	
	@Override
	public long count() {
		return employeeRepository.count();
	}
	
	@Override
    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
		Department department = departmentRepository.findById(request.departmentId())
			.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.departmentId()));
		
		Employee employee = new Employee();
		employee.setName(request.name());
		employee.setSalary(request.salary());
		employee.setDepartment(department);
		
        return EmployeeResponse.from(employeeRepository.save(employee));
    }

	@Override
	public EmployeeResponse updateEmployee(Long id, EmployeeUpdateRequest request) {		
	    Employee existing = employeeRepository.findById(id)
		    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		Department department = departmentRepository.findById(request.departmentId())
			.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.departmentId()));

	    existing.setSalary(request.salary());
	    existing.setDepartment(department);

	    return EmployeeResponse.from(employeeRepository.save(existing));
	}

	@Override
	public void deleteEmployee(Long id) {
	    if (!employeeRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Employee not found with id: " + id);
	    }

	    employeeRepository.deleteById(id);
	}
}
