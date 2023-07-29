package com.rahul.projects.EmployeeRESTAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployeeRestapiApplication {

	public static void main(String[] args) {
		//Other way of setting context path
		//It will override the application.properties file configuration for context-path
		//System.setProperty("server.servlet.context-path", "/baeldung");
		SpringApplication.run(EmployeeRestapiApplication.class, args);

	}

}
