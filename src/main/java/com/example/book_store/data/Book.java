package com.example.book_store.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Book {
    private int id;
    private String title;
    private int cost;
    private int count;

    private String author;
    private String language;
    private int year;
    private Genre genre;

    private String description;
    private String isbn;
    private int pagesCnt;
    private double rate;
    private boolean recency;

}
