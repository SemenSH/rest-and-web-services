package com.example.demo.controller;


import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.SearchRequestDto;
import com.example.demo.service.interfaces.DepartmentRestService;
import com.example.demo.service.interfaces.EmployeeRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class EmployeeController {
    private final EmployeeRestService employeeRestService;
    private final DepartmentRestService departmentRestService;


    @GetMapping("/")
    public String employeePage(Model model) {
        List<EmployeeDto> employeeDtoList = employeeRestService.getAll();

        model.addAttribute("employees", employeeDtoList);
        model.addAttribute("searchRequest", new SearchRequestDto());

        return "employee-page";
    }

    @GetMapping("/remove/{id}")
    public String removeEmployee(@PathVariable(name = "id") Long id, Model model) {
        employeeRestService.remove(id);
//        List<EmployeeDto> employeeDtoList = employeeRestService.getAll();
//        model.addAttribute("employees", employeeDtoList);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addEmployeePage(Model model) {
        List<DepartmentDto> departmentDtoList = departmentRestService.getAll();

        model.addAttribute("departmentList", departmentDtoList);
        model.addAttribute("employee", new EmployeeDto());

        return "add-emp-page";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") EmployeeDto employeeDto) {
        DepartmentDto departmentDto = departmentRestService.getById(employeeDto.getDepartmentId());
        employeeDto.setDepartmentDto(departmentDto);
        employeeRestService.save(employeeDto);
        return "redirect:/";
    }

    @GetMapping("/change/{id}")
    public String editEmployeePage(@PathVariable(name = "id") Long id, Model model) {
        EmployeeDto employeeDto = employeeRestService.getById(id);
        List<DepartmentDto> departmentDtoList = departmentRestService.getAll();

        model.addAttribute("employee", employeeDto);
        model.addAttribute("departmentList", departmentDtoList);

        return "edit-emp-page";
    }

    @PostMapping("/change/{id}")
    public String changeEmployee(@ModelAttribute("employee") EmployeeDto employeeDto, @PathVariable(name = "id") Long id) {
        DepartmentDto departmentDto = departmentRestService.getById(employeeDto.getDepartmentId());
        employeeDto.setId(id);
        employeeDto.setDepartmentDto(departmentDto);

        employeeRestService.update(employeeDto);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchEmployee(@ModelAttribute("searchRequest") SearchRequestDto searchRequestDto, Model model) {
        List<EmployeeDto> employeeDtoList;
        if (searchRequestDto.getSingleDate() != null) {
            employeeDtoList = employeeRestService.getAllByBirthDate(searchRequestDto.getSingleDate());
        } else {
            employeeDtoList = employeeRestService.getAllByBirthDateBetween(searchRequestDto.getFirstDate(), searchRequestDto.getSecondDate());
        }

        model.addAttribute("employees", employeeDtoList);
        model.addAttribute("searchRequest", new SearchRequestDto());
        return "employee-page";
    }
}
