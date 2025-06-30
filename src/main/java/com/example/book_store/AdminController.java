package com.example.book_store;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
    @GetMapping("/clients")
    public String getClients(HttpServletResponse resp){
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        return "admin/clients";
    }

    @GetMapping("/employees")
    public String getEmployees(HttpServletResponse resp){
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        return "admin/employees";
    }
}
