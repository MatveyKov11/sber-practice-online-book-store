package com.example.book_store.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateByStringConverter implements Converter<String, Date> {
    @Override
    public Date convert(String str){
        System.out.println(str);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(str);
        }catch (Exception e){
        }
        return new Date();
    }
}
