package com.example.employee.api.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
	
	@Query("""
	    SELECT e
	    FROM Employee e
	    JOIN FETCH e.department
	    WHERE e.id = :id
	    """)
	Optional<Employee> findByIdWithDepartment(@Param("id") Long id);
	
	@Query("""
        SELECT e
        FROM Employee e
        WHERE e.salary >
            (SELECT AVG(e2.salary)
             FROM Employee e2
             WHERE e2.department = e.department)
        """)
    List<Employee> findAllAboveDepartmentAverage();
	
	List<Employee> findByDepartmentId(Long id);
	
	Page<Employee> findByDepartmentId(Long id, Pageable pageable);
}
