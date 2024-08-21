package com.mustycodified.employeeservice.repositories;

import com.mustycodified.employeeservice.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
