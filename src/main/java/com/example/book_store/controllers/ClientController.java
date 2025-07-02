package com.example.book_store.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/client")
public class ClientController {
    @GetMapping("/cart")
    public String getCart(HttpServletResponse resp){
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        return "client/cart";
    }

    @GetMapping("/shopping-history")
    public String getHistory(HttpServletResponse resp){
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        return "client/shopping-history";
    }
}
