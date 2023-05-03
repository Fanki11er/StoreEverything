package com.example.storeeverything.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "firstName",  length = 20, nullable = false)
    private String firstName;
    @Column(name = "surname",  length = 50, nullable = false)
    private String surname;
    @Column(name = "login",  length = 20, nullable = false)
    private String login;
    @Column(name = "password",  length = 20, nullable = false)
    private String password;
    @Column(name = "age",  length = 3, nullable = false)
    private Integer age;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    List<Item> items;


}
