package com.example.book_store.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Client {
    private int id;
    private String first_name;
    private String last_name;
    private String password;

    private int ordersCnt;
}
