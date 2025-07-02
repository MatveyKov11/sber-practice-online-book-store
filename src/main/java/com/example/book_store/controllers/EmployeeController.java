package com.example.book_store.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController {
    @GetMapping("/add-book")
    public String getAddBook(HttpServletResponse resp){
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        return "emloyee/add-book";
    }
}
