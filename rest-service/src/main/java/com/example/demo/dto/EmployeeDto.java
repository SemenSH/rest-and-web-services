package com.example.demo.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private DepartmentDto departmentDto;
    private Date dateOfBirth;
    private double salary;


    @Builder
    public EmployeeDto(Long id, String firstName, String middleName, String lastName, DepartmentDto departmentDto, Date dateOfBirth, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.departmentDto = departmentDto;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
    }
}
