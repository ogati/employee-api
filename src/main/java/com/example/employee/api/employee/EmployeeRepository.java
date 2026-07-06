package com.example.employee.api.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {
	private static final List<Employee> EMPLOYEES;
	
	static {
		EMPLOYEES = new ArrayList<Employee>();
		EMPLOYEES.add(new Employee(1L, "Alice", "Admin", 4500.00));
		EMPLOYEES.add(new Employee(2L, "Earl", "Sale", 8200.00));
	}
	
	public List<Employee> findAll() {
		return EMPLOYEES;
	}
	
	public Optional<Employee> findById(Long id) {
        return EMPLOYEES.stream()
                .filter(emp -> emp.id().equals(id))
                .findFirst();
	}
	
	public Employee save(Employee employee) {
		// if id exists, replace (update)
        if (employee.id() != null) {
            deleteById(employee.id());
        }

        // if id is null, generate new id
        if (employee.id() == null) {
            long newId = EMPLOYEES.stream()
                    .mapToLong(Employee::id)
                    .max()
                    .orElse(0L) + 1;

            employee = new Employee(
                    newId,
                    employee.name(),
                    employee.department(),
                    employee.salary());
        }

        EMPLOYEES.add(employee);
        return employee;
	}
	
	public boolean existsById(Long id) {
        return EMPLOYEES.stream()
                .anyMatch(emp -> emp.id().equals(id));
	}
	
	public void deleteById(Long id) {
		EMPLOYEES.removeIf(emp -> emp.id().equals(id));
	}
}
