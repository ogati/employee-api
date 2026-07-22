package com.example.employee.api.employee;

public record EmployeeSearchCriteria(String name, String nameContains, Long salaryGreaterThan) {}
