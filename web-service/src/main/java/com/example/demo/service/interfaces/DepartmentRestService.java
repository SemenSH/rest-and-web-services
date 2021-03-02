package com.example.demo.service.interfaces;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeDto;

import java.util.List;


public interface DepartmentRestService {

    List<DepartmentDto> getAll();

    DepartmentDto getById(Long id);

    void remove(Long id);

    DepartmentDto save(DepartmentDto departmentDto);

    DepartmentDto update(DepartmentDto departmentDto);

}
