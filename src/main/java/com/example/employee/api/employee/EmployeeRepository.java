package com.example.employee.api.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByDepartmentName(String departmentName);

    List<Employee> findBySalaryGreaterThan(Long salary);

    List<Employee> findByNameContainingIgnoreCase(String name);
}
