package com.example.book_store.controllers;

import com.example.book_store.data.Client;
import com.example.book_store.entities.Employee;
import com.example.book_store.entities.BookOrder;
import com.example.book_store.entities.UserEntity;
import com.example.book_store.services.BookStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value="/admin", produces="application/json; charset=UTF-8")
public class AdminController {
    private BookStoreService service;

    public class Filter{
        public String str;

        public Filter(String str){
            this.str = str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public String getStr() {
            return str;
        }
    }

    private Filter filterC = new Filter("");
    private Filter filterO = new Filter("");

    @ModelAttribute("filterC")
    public Filter getFilterC(){
        return filterC;
    }

    @ModelAttribute("filterO")
    public Filter getFilterO(){
        return filterO;
    }

    @Autowired
    public AdminController(BookStoreService service){
        this.service = service;
    }

    @ModelAttribute("clients")
    public List<Client> filterClients(){
        return service.getClients(filterC);
    }

    @GetMapping("/clients")
    public String getClients(){
        return "admin/clients";
    }

    @PostMapping("/clients")
    public String getFilterC(Filter filter){
        filterC = filter;
        return "redirect:/admin/clients";
    }

    @ModelAttribute("employee")
    public Employee newEmployee(){
        return new Employee();
    }

    @ModelAttribute("employees")
    public List<Employee> filterEmployees(){
        return service.getEmployees();
    }

    @GetMapping("/employees")
    public String getEmployees(){
        return "admin/employees";
    }

    @PostMapping("/employees")
    public String processEmployee(Employee employee){
        int id = employee.getId();
        switch (id){
            case 0: return addEmployee(employee);
            case 1: return changeEmployee(employee);
            default: return deleteEmployee(employee);
        }
    }

    public String addEmployee(Employee employee) {
        log.info("add employee: {}", employee);
        service.saveEmployee(employee);
        return "redirect:/admin/employees";
    }

    public String changeEmployee(Employee employee) {
        log.info("change employee: {}", employee);
        service.updateEmployee(employee);
        return "redirect:/admin/employees";
    }

    public String deleteEmployee(Employee employee) {
        log.info("delete employee: {}", employee);
        service.deleteEmployee(employee);
        return "redirect:/admin/employees";
    }

    @ModelAttribute("orders")
    public List<BookOrder> filterOrders(){
        return service.getOrders(filterO);
    }

    @GetMapping("/orders")
    public String getOrders(){
        return "admin/orders";
    }

    @PostMapping("/orders")
    public String getFilterO(Filter filter){
        filterO = filter;
        return "redirect:/admin/orders";
    }

    @ModelAttribute("orderById")
    public BookOrder getOrderById(@PathVariable(required = false) String id){
        if(id == null){
            return new BookOrder();
        }else{
            return service.getOrder(Integer.valueOf(id));
        }
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id){
        return "admin/order";
    }

    @ModelAttribute("user")
    public UserEntity getAdmin(@AuthenticationPrincipal UserEntity user){
        return user;
    }

    @GetMapping("/my-profile")
    public String getProfile(){
        return "admin/profile";
    }

    @PostMapping("/my-profile")
    public String updateProfile(UserEntity user){
        log.info("admin: {}", user);
        service.updateAdmin(user);
        return "redirect:/admin/";
    }

    @GetMapping("/")
    public String getHome(){
        return "admin/home";
    }
}
