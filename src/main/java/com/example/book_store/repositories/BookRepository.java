package com.example.book_store.repositories;

import com.example.book_store.entities.Book;
import com.example.book_store.data.Genre;
import com.example.book_store.data.Language;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Optional<Book> findById(int id);

    Book findByTitle(String title);

    @Modifying
    @Transactional
    @Query("update Book b set b.title = :title, b.cost = :cost, " +
            "b.count = :count, b.urlPicture = :urlPicture, b.author = :author, " +
            "b.language = :language, b.year_ = :year_, b.genre = :genre, " +
            "b.description = :description, b.isbn = :isbn, b.pageCnt = :pageCnt, " +
            "b.rate = :rate, b.recency = :recency " +
            "where b.id = :id")
    void update(@Param("id") int id, @Param("title") String title, @Param("cost") int cost,
                @Param("count") int count, @Param("urlPicture") String urlPicture, @Param("author") String author,
                @Param("language") Language language, @Param("year_") int year, @Param("genre") Genre genre,
                @Param("description") String description, @Param("isbn") String isbn, @Param("pageCnt") int pageCnt,
                @Param("rate") int rate, @Param("recency") boolean recency);
}
