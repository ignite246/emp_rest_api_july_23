package com.rahul.projects.EmployeeRESTAPI.services;

import com.rahul.projects.EmployeeRESTAPI.entities.Employee;
import com.rahul.projects.EmployeeRESTAPI.exceptions.EmployeeNotFoundException;
import com.rahul.projects.EmployeeRESTAPI.repos.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo ,pageSize);
        return employeeRepository.findAll(pageable).get().toList();
    }

    @Override
    public Employee fetchEmployeeByEmpId(Integer id) {
        logger.info("===> Finding employee with emp id :: {} <===",id);
        try {
            Thread.sleep(1000);
            final Optional<Employee> optional = employeeRepository.findById(id);
            if (optional.isPresent()) {
                return optional.get();
            } else {
                throw new EmployeeNotFoundException("Employee Not Found for given id : " + id);
            }
        }
        catch(EmployeeNotFoundException ex){
            logger.error("Could not find an employee :: {}",ex.getMessage());
            return null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteEmployeeById(Integer id) {
        logger.info("===> Deleting employee with emp id :: {} <===",id);
        try {
            final Optional<Employee> optional = employeeRepository.findById(id);
            if (optional.isPresent()) {
                employeeRepository.delete(optional.get());
            }
            else {
                throw new EmployeeNotFoundException("Employee to be removed does not exist");
            }
            return true;
        }
        catch (EmployeeNotFoundException ex){
            logger.error("Exception occurred while deleting an employee :: {}",ex.getMessage());
            return false;
        }

    }
}
