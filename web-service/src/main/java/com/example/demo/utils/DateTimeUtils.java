package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
public class DateTimeUtils {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";


    public static Date convertToDate(String value) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
            return simpleDateFormat.parse(value);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public static String convertToString(Date date) {
        if (date == null)
            return "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return simpleDateFormat.format(date);
    }
}
