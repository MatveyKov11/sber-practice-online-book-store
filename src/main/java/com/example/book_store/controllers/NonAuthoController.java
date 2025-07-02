package com.example.book_store.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NonAuthoController {
    @GetMapping("/")
    public String getShop(HttpServletResponse resp){
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        return "shop";
    }

    @GetMapping("/book")
    public String getBook(HttpServletResponse resp){
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        return "book";
    }

    @GetMapping("/register")
    public String getReg(HttpServletResponse resp){
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        return "register";
    }
}
