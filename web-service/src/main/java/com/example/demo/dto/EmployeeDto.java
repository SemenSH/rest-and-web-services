package com.example.demo.dto;


import com.example.demo.utils.DateTimeUtils;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private DepartmentDto departmentDto;
    private Long departmentId;

    @DateTimeFormat(pattern = DateTimeUtils.DEFAULT_DATE_PATTERN)
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

    public String getDateOfBirthAsString() {
        return DateTimeUtils.convertToString(dateOfBirth);
    }
}
