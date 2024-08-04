package com.fpt.fsa.assignment_1.repositories;

import com.fpt.fsa.assignment_1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
