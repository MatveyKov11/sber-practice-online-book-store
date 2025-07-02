package com.example.book_store.controllers;

import com.example.book_store.BookStoreService;
import com.example.book_store.data.Book;
import com.example.book_store.data.Client;
import com.example.book_store.data.Employee;
import com.example.book_store.data.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/rest")
public class RestController {
    @GetMapping("/clients")
    public List<Client> getClients(){
        return BookStoreService.getClients();
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return BookStoreService.getEmployees();
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return BookStoreService.getBooks();
    }

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return BookStoreService.getOrders();
    }
}
