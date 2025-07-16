package com.example.book_store.controllers;

import com.example.book_store.entities.BookOrder;
import com.example.book_store.entities.ClientEntity;
import com.example.book_store.entities.Employee;
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
@RequestMapping(value="/client", produces="application/json; charset=UTF-8")
public class ClientController {
    private BookStoreService service;

    @Autowired
    public ClientController(BookStoreService service){
        this.service = service;
    }

    @ModelAttribute("cart")
    public BookOrder getClientCart(@AuthenticationPrincipal UserEntity user){
        return service.getCart(user);
    }

    @GetMapping("/cart")
    public String getCart(){
        return "client/cart";
    }

    @PostMapping("/cart")
    public String makeOrder(@AuthenticationPrincipal UserEntity user){
        service.doneCart(user);
        return "redirect:/";
    }

    @ModelAttribute("ordersByClient")
    public List<BookOrder> getOrdersByClient(@AuthenticationPrincipal UserEntity user){
        return service.getOrders(user);
    }

    @GetMapping("/shopping-history")
    public String getHistory(){
        return "client/shopping-history";
    }

    @ModelAttribute("orderById")
    public BookOrder getOrderById(@PathVariable(required = false) String id){
        if(id == null){
            return new BookOrder();
        }else {
            return service.getOrder(Integer.valueOf(id));
        }
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id){
        return "client/order";
    }

    @ModelAttribute("client")
    public ClientEntity getAdmin(@AuthenticationPrincipal UserEntity user){
        return service.getClientEntity(user);
    }

    @GetMapping("/my-profile")
    public String getProfile(){
        return "client/profile";
    }

    @PostMapping("/my-profile")
    public String updateProfile(ClientEntity client){
        log.info("client: {}", client);
        service.updateClient(client);
        return "redirect:/client/my-profile";
    }

    @GetMapping("/add/{id}")
    public String addBook(@PathVariable(required = false) String id, @AuthenticationPrincipal UserEntity user){
        if(id != null){
            service.addToCart(user, Integer.valueOf(id));
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable(required = false) String id, @AuthenticationPrincipal UserEntity user){
        if(id != null){
            service.deleteFromCart(user, Integer.valueOf(id));
        }
        return "redirect:/";
    }
}
