package com.example.demo.service.interfaces;


import com.example.demo.dto.EmployeeDto;

import java.util.Date;
import java.util.List;

public interface EmployeeRestService {

    List<EmployeeDto> getAll();

    List<EmployeeDto> getAllByBirthDate(Date birthDate);

    List<EmployeeDto> getAllByBirthDateBetween(Date startDate, Date endDate);

    EmployeeDto getById(Long id);

    void remove(Long id);

    EmployeeDto save(EmployeeDto employeeDto);

    EmployeeDto update(EmployeeDto employeeDto);
}
