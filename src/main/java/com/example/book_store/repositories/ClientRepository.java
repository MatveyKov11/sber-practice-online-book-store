package com.example.book_store.repositories;

import com.example.book_store.entities.ClientEntity;
import com.example.book_store.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    @Modifying
    @Transactional
    @Query("update ClientEntity c set c.login = :login, c.password = :password, c.firstName = :firstName, " +
            "c.middleName = :middleName, c.lastName = :lastName, c.email = :email, " +
            "c.phoneNumber = :phoneNumber, c.dateOfBirth = :dateOfBirth where c.id = :id")
    void updateFull(@Param("id") int id, @Param("login") String login, @Param("password") String password,
                    @Param("firstName") String firstName, @Param("middleName") String middleName, @Param("lastName") String lastName,
                    @Param("email") String email, @Param("phoneNumber") String phoneNumber, @Param("dateOfBirth") Date dateOfBirth);

    @Modifying
    @Transactional
    @Query("update ClientEntity c set c.ordersCnt = :ordersCnt, c.idLastOrder = :idLastOrder where c.id = :id")
    void updateOrder(@Param("id") int id, @Param("ordersCnt") int ordersCnt, @Param("idLastOrder") int idLastOrder);

    ClientEntity findByLastName(String lastName);

    ClientEntity findByUser(UserEntity user);
}
