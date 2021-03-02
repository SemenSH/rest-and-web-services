package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;


    public EmployeeDto save (EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToDto(savedEmployee);
    }

    public EmployeeDto update(EmployeeDto srcEmployeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(srcEmployeeDto.getId());

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            employee.setLastName(srcEmployeeDto.getLastName());
            employee.setFirstName(srcEmployeeDto.getFirstName());
            employee.setMiddleName(srcEmployeeDto.getMiddleName());
            employee.setDepartment(departmentRepository.findById(srcEmployeeDto.getDepartmentDto().getId()).get());
            employee.setDateOfBirth(srcEmployeeDto.getDateOfBirth());
            employee.setSalary(srcEmployeeDto.getSalary());

            employeeRepository.save(employee);
            return mapToDto(employee);
        }
        return null;
    }

    public EmployeeDto findById (Long id){
        return mapToDto(employeeRepository.findById(id).orElse(new Employee()));
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public void deleteById (Long id) {
        employeeRepository.deleteById(id);
    }

    private Employee mapToEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .firstName(employeeDto.getFirstName())
                .middleName(employeeDto.getMiddleName())
                .lastName(employeeDto.getLastName())
                .department(departmentRepository.findById(employeeDto.getDepartmentDto().getId()).get())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .salary(employeeDto.getSalary())
            .build();
    }

    private EmployeeDto mapToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .middleName(employee.getMiddleName())
                .lastName(employee.getLastName())
                .departmentDto(departmentService.findById(employee.getDepartment().getId()))
                .dateOfBirth(employee.getDateOfBirth())
                .salary(employee.getSalary())
            .build();
    }
}
