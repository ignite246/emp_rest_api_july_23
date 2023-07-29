package com.rahul.projects.EmployeeRESTAPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees_details")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@JsonIgnore
    private Integer id;
    private String empId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String designation;
    private String address;

}
