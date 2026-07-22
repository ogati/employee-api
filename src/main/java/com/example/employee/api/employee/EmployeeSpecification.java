package com.example.employee.api.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.PredicateSpecification;

import jakarta.persistence.criteria.Predicate;

public class EmployeeSpecification {

    public static PredicateSpecification<Employee> withCriteria(EmployeeSearchCriteria criteria) {
        return (root, cb) -> {
        	if (criteria == null) {
                return cb.conjunction();
            }
        	
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.name() != null && !criteria.name().isBlank()) {
                predicates.add(cb.equal(
                    cb.lower(root.get("name")), 
                    criteria.name().strip().toLowerCase()
                ));
            }

            if (criteria.nameContains() != null && !criteria.nameContains().isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.get("name")), 
                    "%" + criteria.nameContains().strip().toLowerCase() + "%"
                ));
            }

            if (criteria.salaryGreaterThan() != null) {
                predicates.add(cb.greaterThan(
                        root.get("salary"), 
                        criteria.salaryGreaterThan()
                    ));
            }

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
