package com.example.book_store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;

    private String email;
    private String phoneNumber;
    private Date dateOfBirth;

    private int ordersCnt;
    private int idLastOrder;

    @OneToOne
    private UserEntity user;
}
