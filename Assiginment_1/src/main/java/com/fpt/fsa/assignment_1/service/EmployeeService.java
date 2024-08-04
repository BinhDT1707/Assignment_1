package com.fpt.fsa.assignment_1.service;

import com.fpt.fsa.assignment_1.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee saveEmployee(Employee employee);
}
