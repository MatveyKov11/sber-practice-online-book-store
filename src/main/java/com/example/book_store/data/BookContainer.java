package com.example.book_store.data;

import com.example.book_store.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookContainer {
    public Book book;
    public boolean isInCart;
}
