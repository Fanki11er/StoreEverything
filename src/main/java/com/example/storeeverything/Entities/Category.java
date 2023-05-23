package com.example.storeeverything.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Category")
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private long id;
        @Column(name = "name", nullable = false)
        @Size(min = 3, max = 20, message = "Nazwa karegorii powinna mieć długość od {min} do {max} znaków.")
        @Pattern(regexp = "^[a-z]+$", message = "Nazwa kategorii powinna składać się wyłącznie z małych liter.")
        private String name;

}
