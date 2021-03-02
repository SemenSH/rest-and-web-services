package com.example.demo.controller;


import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/add")
    public EmployeeDto save(@RequestBody EmployeeDto employeeDto) {
        return employeeService.save(employeeDto);
    }

    @PostMapping("/update")
    public EmployeeDto update(@RequestBody EmployeeDto employeeDto) {
        return employeeService.update(employeeDto);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @DeleteMapping("/remove/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
