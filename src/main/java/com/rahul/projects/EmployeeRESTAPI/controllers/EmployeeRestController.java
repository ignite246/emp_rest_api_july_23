package com.rahul.projects.EmployeeRESTAPI.controllers;

import com.rahul.projects.EmployeeRESTAPI.customproperties.CustomProperties;
import com.rahul.projects.EmployeeRESTAPI.dtos.ClientResponse;
import com.rahul.projects.EmployeeRESTAPI.dtos.ValidationResponseDto;
import com.rahul.projects.EmployeeRESTAPI.entities.Employee;
import com.rahul.projects.EmployeeRESTAPI.services.EmployeeService;
import com.rahul.projects.EmployeeRESTAPI.util.ClientRequestValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee-api")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomProperties customProperties;

    @PostMapping("/employees")
    public ResponseEntity<ClientResponse> createEmployee(@RequestBody Employee employee) {

        final ValidationResponseDto requestBodyValidationResult = ClientRequestValidation.isRequestBodyValid(employee);
        if (requestBodyValidationResult.isValidationPassed()) {

            final Employee createdEmployee = employeeService.create(employee);

            ClientResponse<Employee> response = new ClientResponse<>();
            response.setMessage("Employee Created Successfully");
            response.setData(createdEmployee);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            ClientResponse<Map<String, String>> response = new ClientResponse<>();
            response.setMessage("Unable to create employee - Invalid Request Body");
            response.setData(requestBodyValidationResult.getValidationResult());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<ClientResponse> fetchAllEmployees(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        final List<Employee> employees = employeeService.fetchAll(pageNumber, pageSize);
        ClientResponse<List<Employee>> response = new ClientResponse<>();
        response.setMessage(employees.size() + " employees fetched");
        response.setData(employees);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    @Transactional(readOnly = true)
    @Cacheable("employee-cache")
    public ClientResponse<Employee> fetchEmployeeById(@PathVariable("id") Integer id) {

        final Employee employee = employeeService.fetchEmployeeByEmpId(id);

        ClientResponse<Employee> response = new ClientResponse<>();
        if (employee != null) {
            response.setMessage("Employee Found");
            response.setData(employee);
        } else {
            response.setMessage("Employee Not Found");
            response.setData(null);
        }
        return response;
        //return new ResponseEntity<>(response,HttpStatus.FOUND);

    }

    @DeleteMapping("/employees/{id}")
    @CacheEvict("employee-cache")
    public ClientResponse<String> deleteEmployeeById(@PathVariable("id") Integer id) {
        final boolean isDeleted = employeeService.deleteEmployeeById(id);
        ClientResponse<String> response = new ClientResponse<>();
        if (isDeleted) {
            response.setMessage("Deletion Successful");
            response.setData("Employee with id " + id + " deleted Successfully");
            //return new ResponseEntity<>(response,HttpStatus.FOUND);
        } else {
            response.setMessage("Deletion Failed");
            response.setData("Employee with id " + id + " could not be deleted as it does not exist");
            //return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/employees/delete-all")
    @CacheEvict("employee-cache")
    public boolean deleteAllRecords() {
        return true;
    }

    @GetMapping("/employees/courses")
    public Map<String,String> getCourses(){
       return  customProperties.getDx();
    }

}
