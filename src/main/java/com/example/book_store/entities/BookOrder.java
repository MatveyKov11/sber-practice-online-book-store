package com.example.book_store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private ClientEntity client;
    @ManyToMany
    private List<Book> books;
    private Date date;
    private int totalCost;
    private boolean done;
}
