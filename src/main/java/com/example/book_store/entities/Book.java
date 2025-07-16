package com.example.book_store.entities;

import com.example.book_store.data.Genre;
import com.example.book_store.data.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int cost;
    private int count;

    private String urlPicture;
    private String author;
    private Language language;
    private int year_;
    private Genre genre;

    private String description;
    private String isbn;
    private int pageCnt;
    private int rate;
    private boolean recency;

    public String getEnd(){
        int cnt = this.count;
        if (cnt%10 == 1 && (cnt/10)%10 != 1){
            return "а";
        }
        if (cnt%10 >= 2 && cnt%10 <= 4 && (cnt/10)%10 != 1){
            return "и";
        }
        return "";
    }

    public boolean isInOrder(BookOrder bo){
        List<Book> books = bo.getBooks();
        if(books == null){
            return false;
        }
        AtomicBoolean ans = new AtomicBoolean(false);
        books.forEach(book ->{
            if(book.id == this.id){
                ans.set(true);
            }
        });
        return ans.get();
    }
}
