package com.example.demo.service;


import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.interfaces.EmployeeRestService;
import com.example.demo.utils.DateTimeUtils;
import com.example.demo.utils.HttpEntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class EmployeeRestServiceImpl implements EmployeeRestService {

    @Value("${rest-service.request-url}")
    private String requestUrl;


    @Override
    public List<EmployeeDto> getAll() {
        final String urlRequest = String.format(requestUrl, "employee/getAll");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeDto[]> employeeDtoResponse = restTemplate.exchange(urlRequest, HttpMethod.GET,
                HttpEntityUtils.getPreparedHttpEntityForRequestQuery(), EmployeeDto[].class);

        EmployeeDto[] employeeDtos = employeeDtoResponse.getBody();
        if (employeeDtos == null || employeeDtos.length == 0) {
            log.error("ResponseEntity is null. Request url: " + urlRequest);
            return Collections.emptyList();
        }
        return Arrays.asList(employeeDtos);
    }

    @Override
    public List<EmployeeDto> getAllByBirthDate(Date birthDate) {
        return getAll().stream().filter(e -> e.getDateOfBirthAsString().equals(DateTimeUtils.convertToString(birthDate))).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getAllByBirthDateBetween(Date startDate, Date endDate) {
        return getAll().stream().filter(e -> (e.getDateOfBirth().after(startDate) && e.getDateOfBirth().before(endDate))).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getById(Long id) {
        final String urlRequest = String.format(requestUrl, "employee/get/" + id);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeDto> employeeDtoResponse = restTemplate.exchange(urlRequest, HttpMethod.GET,
                HttpEntityUtils.getPreparedHttpEntityForRequestQuery(), EmployeeDto.class);

        EmployeeDto employeeDto = employeeDtoResponse.getBody();
        if (employeeDto == null) {
            log.error("ResponseEntity is null. Request url: " + urlRequest);
            return null;
        }
        return employeeDto;
    }

    @Override
    public void remove(Long id) {
        final String urlRequest = String.format(requestUrl, "employee/remove/" + id);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(urlRequest);
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        final String urlRequest = String.format(requestUrl, "employee/add");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeDto> employeeDtoResponse = restTemplate.postForEntity(urlRequest, employeeDto, EmployeeDto.class);

        EmployeeDto employeeDtoResponseBody = employeeDtoResponse.getBody();
        if (employeeDtoResponseBody == null) {
            log.error("ResponseEntity is null. Request url: " + urlRequest);
            return null;
        }
        return employeeDtoResponseBody;
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) {
        final String urlRequest = String.format(requestUrl, "employee/update");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeDto> employeeDtoResponse = restTemplate.postForEntity(urlRequest, employeeDto, EmployeeDto.class);

        EmployeeDto employeeDtoResponseBody = employeeDtoResponse.getBody();
        if (employeeDtoResponseBody == null) {
            log.error("ResponseEntity is null. Request url: " + urlRequest);
            return null;
        }
        return employeeDtoResponseBody;
    }
}
