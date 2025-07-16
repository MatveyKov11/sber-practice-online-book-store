package com.example.book_store.controllers;

import com.example.book_store.entities.Book;
import com.example.book_store.data.Genre;
import com.example.book_store.data.Language;
import com.example.book_store.entities.Employee;
import com.example.book_store.entities.UserEntity;
import com.example.book_store.services.BookStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value="/employee", produces="application/json; charset=UTF-8")
public class EmployeeController {
    private BookStoreService service;

    @Autowired
    public EmployeeController(BookStoreService service){
        this.service = service;
    }

    private List<Language> languages = new ArrayList<>();

    @ModelAttribute(name = "languages")
    public List<Language> newLanguages(){
        if(languages.isEmpty()){
            int i = 1;
            languages.add(Language.NONE);
            while (Language.fromId(i) != Language.NONE){
                languages.add(Language.fromId(i));
                i++;
            }
        }
        return languages;
    }

    private List<Genre> genres = new ArrayList<>();

    @ModelAttribute(name = "genres")
    public List<Genre> newGenres(){
        if(genres.isEmpty()){
            int i = 1;
            genres.add(Genre.NONE);
            while (Genre.fromId(i) != Genre.NONE){
                genres.add(Genre.fromId(i));
                i++;
            }
        }
        return genres;
    }

    @ModelAttribute(name = "book")
    public Book newBook(){
        return new Book();
    }

    @GetMapping("/add-book")
    public String getAddBook(){
        return "employee/add-book";
    }

    @PostMapping("/add-book")
    public String addBook(Book book){
        log.info("add book: {}", book);
        service.saveBook(book);
        return "redirect:/employee/add-book";
    }

    @ModelAttribute(name = "bookById")
    public Book getBookById(@PathVariable(required = false) String id){
        if(id == null){
            return new Book();
        }else{
            return service.getBook(Integer.valueOf(id));
        }
    }

    @GetMapping("/redact-book/{id}")
    public String getRedactBook(@PathVariable String id){
        log.info("id {}", id);
        return "employee/redact-book";
    }

    @PostMapping("/redact-book/{id}")
    public String changeBook(@PathVariable String id, Book book){
        book.setId(Integer.valueOf(id));
        log.info("change book {}", book);
        service.updateBook(book);
        return "redirect:/book/{id}";
    }

    @ModelAttribute("user")
    public UserEntity getAdmin(@AuthenticationPrincipal UserEntity user){
        return user;
    }

    @GetMapping("/my-profile")
    public String getProfile(){
        return "employee/profile";
    }

    @PostMapping("/my-profile")
    public String updateProfile(@AuthenticationPrincipal UserEntity userOld, UserEntity userNew){
        log.info("employee: {}", userNew);
        service.updateEmployee(new Employee(0, userOld.getUsername(), userNew.getPassword(), userOld));
        return "redirect:/";
    }
}
