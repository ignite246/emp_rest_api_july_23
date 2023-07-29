package com.rahul.projects.EmployeeRESTAPI.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientResponse<T> implements Serializable {
    private String message;
    private T data;
}
