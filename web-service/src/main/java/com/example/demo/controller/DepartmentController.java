package com.example.demo.controller;


import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.SearchRequestDto;
import com.example.demo.service.interfaces.DepartmentRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRestService departmentRestService;

    @GetMapping("")
    public String departmentPage(Model model) {
        List<DepartmentDto> departmentDtoList = departmentRestService.getAll();

        model.addAttribute("departments", departmentDtoList);
       // model.addAttribute("searchRequest", new SearchRequestDto());

        return "department-page";
    }

    @GetMapping("/remove/{id}")
    public String removeDepartment(@PathVariable(name = "id") Long id, Model model) {
        departmentRestService.remove(id);
//        List<EmployeeDto> employeeDtoList = employeeRestService.getAll();
//        model.addAttribute("employees", employeeDtoList);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addDepartmentPage(Model model) {
        List<DepartmentDto> departmentDtoList = departmentRestService.getAll();

        model.addAttribute("departmentList", departmentDtoList);
        model.addAttribute("department", new DepartmentDto());

        return "add-dep-page";
    }

//    @PostMapping("/add")
//    public String addDepartment (@ModelAttribute("department") DepartmentDto departmentDto) {
//        DepartmentDto departmentDto = departmentRestService.getById(departmentDto.getDepartmentId());
//        departmentDto.setName(departmentDto);
//        departmentRestService.save(departmentDto);
//        return "redirect:/";
//    }

}
