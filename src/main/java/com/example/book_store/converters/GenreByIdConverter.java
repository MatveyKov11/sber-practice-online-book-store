package com.example.book_store.converters;

import com.example.book_store.data.Genre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenreByIdConverter implements Converter<String, Genre> {
    @Override
    public Genre convert(String str){
        return Genre.fromString(str);
    }
}
