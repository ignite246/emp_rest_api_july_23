package com.rahul.projects.EmployeeRESTAPI.util;

import com.rahul.projects.EmployeeRESTAPI.dtos.ValidationResponseDto;
import com.rahul.projects.EmployeeRESTAPI.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ClientRequestValidation {

    static Logger logger = LoggerFactory.getLogger(ClientRequestValidation.class);


    public static ValidationResponseDto isRequestBodyValid(Employee employee) {

        logger.info("=== Client Request Data for Validation == {}",employee);

        ValidationResponseDto validationResponseDto = new ValidationResponseDto();

        boolean isValidationPassed = true;
        Map<String, String> validationResultMap = new HashMap<>();
        if (employee.getEmpId() == null || employee.getEmpId().trim().equals("")) {
            isValidationPassed = false;
            validationResultMap.put("EmpId","INVALID");
        }
        if (employee.getFirstName() == null || employee.getFirstName().trim().equals("") || employee.getFirstName().length()<3) {
            isValidationPassed = false;
            validationResultMap.put("Firstname","INVALID");
        }
        if (employee.getLastName() == null || employee.getLastName().trim().equals("") || employee.getLastName().length()<3) {
            isValidationPassed = false;
            validationResultMap.put("Lastname","INVALID");
        }
        if (employee.getEmail() == null || employee.getEmail().trim().equals("") || !employee.getEmail().contains("@")) {
            isValidationPassed = false;
            validationResultMap.put("Email","INVALID");
        }

        validationResponseDto.setValidationPassed(isValidationPassed);
        validationResponseDto.setValidationResult(validationResultMap);
        return validationResponseDto;


    }
}
