package com.rahul.projects.EmployeeRESTAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeRestapiApplication {

	public static void main(String[] args) {
		//Other way of setting context path
		//It will override the application.properties file configuration for context-path
		//System.setProperty("server.servlet.context-path", "/baeldung");
		SpringApplication.run(EmployeeRestapiApplication.class, args);

	}

}
