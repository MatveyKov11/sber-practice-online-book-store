package com.example.book_store;

import com.example.book_store.data.Book;
import com.example.book_store.data.Client;
import com.example.book_store.data.Employee;
import com.example.book_store.data.Order;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class BookStoreService {
    public static List<Client> getClients(){
        return new ArrayList<>();
    }

    public static List<Employee> getEmployees(){
        return new ArrayList<>();
    }

    public static List<Book> getBooks(){
        return new ArrayList<>();
    }

    public static List<Order> getOrders(){
        return new ArrayList<>();
    }
}
