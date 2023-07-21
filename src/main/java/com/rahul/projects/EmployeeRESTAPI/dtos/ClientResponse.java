package com.rahul.projects.EmployeeRESTAPI.dtos;

import lombok.Data;

@Data
public class ClientResponse<T> {

    private String message;
    private T data;
}
