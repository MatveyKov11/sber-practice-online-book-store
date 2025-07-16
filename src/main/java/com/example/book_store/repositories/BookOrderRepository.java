package com.example.book_store.repositories;

import com.example.book_store.entities.Book;
import com.example.book_store.entities.BookOrder;
import com.example.book_store.entities.ClientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookOrderRepository extends CrudRepository<BookOrder, Integer> {
    @Modifying
    @Transactional
    @Query("update BookOrder b set b.books = :books where b.id = :id")
    void updateList(@Param("id") int id, @Param("books") List<Book> books);

    @Modifying
    @Transactional
    @Query("update BookOrder b set b.totalCost = :totalcost where b.id = :id")
    void updateCost(@Param("id") int id, @Param("totalcost") int totalCost);

    @Modifying
    @Transactional
    @Query("update BookOrder b set b.date = :date, b.done = true where b.id = :id")
    void done(@Param("id") int id, @Param("date") Date date);

    List<BookOrder> findAllByClient_Id(int clientId);

    List<BookOrder> findAllByClient_Login(String clientLogin);

    List<BookOrder> findAllByClient(ClientEntity client);

    BookOrder findByClientAndDone(ClientEntity client, boolean done);

    //BookOrder findByDoneAndClient
}
