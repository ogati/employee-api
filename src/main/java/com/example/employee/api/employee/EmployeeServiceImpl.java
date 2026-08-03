package com.example.employee.api.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public List<EmployeeResponse> getEmployees() {
    	List<Employee> employees = employeeRepository.findAll();
    	return EmployeeResponse.from(employees);
    }
    
    @Override
	@Transactional(readOnly = true)
	public Page<EmployeeResponse> getEmployees(Pageable pageable) {
    	Page<Employee> employees = employeeRepository.findAll(pageable);
    	return employees.map(EmployeeResponse::from);
    }

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeResponse> getEmployeesAboveDepartmentAverage() {
		List<Employee> employees = employeeRepository.findAllAboveDepartmentAverage();
		return EmployeeResponse.from(employees);
	}
	
    @Override
	@Transactional(readOnly = true)
	public EmployeeResponse getEmployeeById(Long id) {
	    Employee employee = employeeRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
	    return EmployeeResponse.from(employee);
	}

	@Override
	@Transactional(readOnly = true)
	public EmployeeResponse getEmployeeByIdWithDepartment(Long id) {
		EmployeeResponse response= employeeRepository.findByIdWithDepartment(id)
				.map(EmployeeResponse::from)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeResponse> getEmployeesByDepartmentId(Long id) {
		List<Employee> employees = employeeRepository.findByDepartmentId(id);
		return employees.stream().map(EmployeeResponse::from).toList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EmployeeResponse> getEmployeesByDepartmentId(Long id, Pageable pageable) {
		Page<Employee> employees = employeeRepository.findByDepartmentId(id, pageable);
		return employees.map(EmployeeResponse::from);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EmployeeResponse> search(EmployeeSearchCriteria criteria) {
		List<Employee> employees = employeeRepository.findAll(EmployeeSpecification.withCriteria(criteria));
		return employees.stream().map(EmployeeResponse::from).toList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public long count() {
		return employeeRepository.count();
	}
	
	@Override
	@Transactional
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
	@Transactional
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
	@Transactional
	public void deleteEmployee(Long id) {
	    if (!employeeRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Employee not found with id: " + id);
	    }

	    employeeRepository.deleteById(id);
	}
}
