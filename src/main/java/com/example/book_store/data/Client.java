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
    private String login;
    private String name;

    private String email;
    private int age;

    private int ordersCnt;
    private int idLastOrder;
}
