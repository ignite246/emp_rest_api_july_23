package com.rahul.projects.EmployeeRESTAPI.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class ValidationResponseDto {

    private boolean validationPassed;
    private Map<String,String> validationResult;
}
