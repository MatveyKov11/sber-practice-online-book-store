package com.example.book_store.repositories;

import com.example.book_store.entities.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Modifying
    @Transactional
    @Query("update Employee e set e.password = :pass where e.login = :login")
    void update(@Param("login") String login, @Param("pass") String pass);

    @Modifying
    @Transactional
    void deleteByLogin(String login);

    List<Employee> findAllByLogin(String login);

    Employee findByLogin(String login);
}
