package com.example.demo.service;


import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.interfaces.DepartmentRestService;
import com.example.demo.utils.HttpEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class DepartmentRestServiceImpl implements DepartmentRestService {

    @Value("${rest-service.request-url}")
    private String requestUrl;


    @Override
    public List<DepartmentDto> getAll() {
        final String urlRequest = String.format(requestUrl, "departments/getAll");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DepartmentDto[]> employeeDtoResponse = restTemplate.exchange(urlRequest, HttpMethod.GET,
                HttpEntityUtils.getPreparedHttpEntityForRequestQuery(), DepartmentDto[].class);

        DepartmentDto[] departmentDtos = employeeDtoResponse.getBody();
        if (departmentDtos == null || departmentDtos.length == 0) {
            log.error("ResponseEntity is null. Request url: " + urlRequest);
            return Collections.emptyList();
        }
        return Arrays.asList(departmentDtos);
    }

    @Override
    public DepartmentDto getById(Long id) {
        final String urlRequest = String.format(requestUrl, "departments/get/" + id);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DepartmentDto> departmentDtoResponse = restTemplate.exchange(urlRequest, HttpMethod.GET,
                HttpEntityUtils.getPreparedHttpEntityForRequestQuery(), DepartmentDto.class);

        DepartmentDto departmentDto = departmentDtoResponse.getBody();
        if (departmentDto == null) {
            log.error("ResponseEntity is null. Request url: " + urlRequest);
            return null;
        }
        return departmentDto;
    }

    @Override
    public void remove(Long id) {
        final String urlRequest = String.format(requestUrl, "departments/delete/" + id);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(urlRequest);
    }
    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        final String urlRequest = String.format(requestUrl, "departments/add");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DepartmentDto> departmentDtoResponse = restTemplate.postForEntity(urlRequest, departmentDto, DepartmentDto.class);

        DepartmentDto departmentDtoResponseBody = departmentDtoResponse.getBody();
        if (departmentDtoResponseBody == null) {
            log.error("ResponseEntity is null. Request url: " + urlRequest);
            return null;
        }
        return departmentDtoResponseBody;
    }

    @Override
    public DepartmentDto update(DepartmentDto departmentDto) {
        final String urlRequest = String.format(requestUrl, "departments/update");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DepartmentDto> departmentDtoResponse = restTemplate.postForEntity(urlRequest, departmentDto, DepartmentDto.class);

        DepartmentDto departmentDtoResponseBody = departmentDtoResponse.getBody();
        if (departmentDtoResponseBody == null) {
            log.error("ResponseEntity is null. Request url: " + urlRequest);
            return null;
        }
        return departmentDtoResponseBody;
    }
}
