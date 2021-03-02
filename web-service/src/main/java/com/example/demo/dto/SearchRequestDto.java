package com.example.demo.dto;

import com.example.demo.utils.DateTimeUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class SearchRequestDto {

    @DateTimeFormat(pattern = DateTimeUtils.DEFAULT_DATE_PATTERN)
    private Date singleDate;

    @DateTimeFormat(pattern = DateTimeUtils.DEFAULT_DATE_PATTERN)
    private Date firstDate;

    @DateTimeFormat(pattern = DateTimeUtils.DEFAULT_DATE_PATTERN)
    private Date secondDate;

}
