package com.example.demo.controller;


import com.example.demo.dto.DepartmentDto;
import com.example.demo.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/add")
    public DepartmentDto save(@RequestBody DepartmentDto departmentDto) {
        return departmentService.save(departmentDto);
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentDto> findAll() {
        return departmentService.findAll();
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentDto findById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.deleteById(id);
    }
}
