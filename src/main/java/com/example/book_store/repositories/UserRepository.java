package com.example.book_store.repositories;

import com.example.book_store.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update UserEntity u set u.password = :password where u.id = :id")
    void update(@Param("id") Long id, @Param("password") String password);

    @Modifying
    @Transactional
    @Query("update UserEntity u set u.username = :username where u.id = :id")
    void updateLogin(@Param("id") Long id, @Param("username") String username);
}
