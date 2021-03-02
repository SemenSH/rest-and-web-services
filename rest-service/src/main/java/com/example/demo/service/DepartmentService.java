package com.example.demo.service;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentDto save (DepartmentDto departmentDto) {
        Department department = mapToEntity(departmentDto);
        Department savedEntity = departmentRepository.save(department);
        return mapToDto(savedEntity);
    }
    public DepartmentDto findById (Long id){
        return mapToDto(departmentRepository.findById(id).orElse(new Department()));
    }

    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void deleteById (Long id) {
        departmentRepository.deleteById(id);
    }

    public Department mapToEntity(DepartmentDto departmentDto) {
        return new Department(departmentDto.getName());
    }

    public DepartmentDto mapToDto(Department department) {
        return new DepartmentDto(department.getId(), department.getName());
    }
}
