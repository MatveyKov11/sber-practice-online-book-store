package com.example.book_store.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Order {
    private int id;
    private int clientId;
    private ArrayList<Integer> bookIds;
    private Date date;
    private int totalCost;
    private boolean done;
}
