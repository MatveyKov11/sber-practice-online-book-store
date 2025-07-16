package com.example.book_store.controllers;

import com.example.book_store.data.BookContainer;
import com.example.book_store.entities.Book;
import com.example.book_store.entities.BookOrder;
import com.example.book_store.entities.ClientEntity;
import com.example.book_store.entities.UserEntity;
import com.example.book_store.services.BookStoreService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(produces="application/json; charset=UTF-8")
public class NonAuthoController {
    private static final Logger log = LoggerFactory.getLogger(NonAuthoController.class);
    private BookStoreService service;

    @Autowired
    public NonAuthoController(BookStoreService service){
        this.service = service;
    }

    @ModelAttribute("books")
    public List<BookContainer> getBooks(@ModelAttribute("cart") BookOrder cart){
        List<Book> b = service.getBooks();
        List<BookContainer> bc = new ArrayList<>();
        b.forEach(item -> {
            bc.add(new BookContainer(item, item.isInOrder(cart)));
        });
        return bc;
    }

    @ModelAttribute("cart")
    public BookOrder getCart(@AuthenticationPrincipal UserEntity user, HttpServletRequest request){
        if(request.isUserInRole("ROLE_CLIENT")){
            return service.getCart(user);
        }
        return new BookOrder();
    }

    @GetMapping("/")
    public String getShop(){
        return "shop";
    }

    @PostMapping("/")
    public String addBookToCart(Book book){
        return "redirect:/";
    }

    @ModelAttribute("bookById")
    public BookContainer getBookById(@PathVariable(required = false) String id, @ModelAttribute("cart") BookOrder cart){
        if(id == null){
            return new BookContainer();
        }else{
            Book b = service.getBook(Integer.valueOf(id));
            if(b == null){
                return new BookContainer();
            }
            return new BookContainer(b, b.isInOrder(cart));
        }
    }

    @ModelAttribute("user")
    public UserEntity getUser(@AuthenticationPrincipal UserEntity user){
        return user;
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable String id){
        return "book";
    }

    @GetMapping("/login")
    public String getAuth(){
        return "login";
    }

    @ModelAttribute("client")
    public ClientEntity newClientEntity(){
        return new ClientEntity();
    }

    @GetMapping("/register")
    public String getReg(){
        return "register";
    }

    @PostMapping("/register")
    public String regClient(ClientEntity client) {
        service.saveClient(client);
        return "redirect:/login";
    }
}
