package com.example.storeeverything.Entities;

import com.example.storeeverything.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class User {

    public User(String firstName, String surname, String login, String password, Integer age, Role role) {
        this.firstName = firstName;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.age = age;
        this.role = role;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    @Column(name = "role",  length = 10, nullable = false)
    private Role role;
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    List<Item> items;



}
