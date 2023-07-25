package com.rahul.projects.EmployeeRESTAPI.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-sensitive")
public class TestController {

    @PostMapping("/news")
    public String createNews(){
        return "news created";
    }

    @DeleteMapping("/news")
    public String deleteNews(){
        return "news deleted";
    }
}
