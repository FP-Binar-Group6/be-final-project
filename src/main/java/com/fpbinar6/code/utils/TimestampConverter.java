package com.fpbinar6.code.utils;

import org.springframework.core.convert.converter.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampConverter implements Converter<String, Timestamp> {
    @Override
    public Timestamp convert(String source) {
        LocalDateTime dateTime = LocalDateTime.parse(source, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return Timestamp.valueOf(dateTime);
    }
}

