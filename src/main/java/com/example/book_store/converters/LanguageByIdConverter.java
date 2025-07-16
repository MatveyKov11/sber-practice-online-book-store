package com.example.book_store.converters;

import com.example.book_store.data.Language;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LanguageByIdConverter implements Converter<String, Language> {
    @Override
    public Language convert(String str){
        return Language.fromString(str);
    }
}
