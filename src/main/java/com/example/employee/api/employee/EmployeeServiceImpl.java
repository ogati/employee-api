package com.example.employee.api.employee;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee.api.common.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

	public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

	public Employee getEmployeeById(Long id) {
	    return employeeRepository.findById(id)
	        .orElseThrow(() -> new EmployeeNotFoundException(id));
	}

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

	public Employee updateEmployee(Long id, Employee employee) {
	    Employee existing = employeeRepository.findById(id)
	        .orElseThrow(() -> new EmployeeNotFoundException(id));

	    existing = new Employee(
                id,
                employee.name(),
                employee.department(),
                employee.salary());

	    return employeeRepository.save(existing);
	}

	public void deleteEmployee(Long id) {
	    if (!employeeRepository.existsById(id)) {
	        throw new EmployeeNotFoundException(id);
	    }

	    employeeRepository.deleteById(id);
	}
}
