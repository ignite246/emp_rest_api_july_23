package com.rahul.projects.EmployeeRESTAPI.services;

import com.rahul.projects.EmployeeRESTAPI.entities.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    List<Employee> fetchAll(Integer pageNumber, Integer pageSize);
    Employee fetchEmployeeByEmpId(Integer id);
    boolean deleteEmployeeById(Integer id);

}
