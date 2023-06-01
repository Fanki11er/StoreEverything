package com.example.storeeverything.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LinkedItems")
public class LinkedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false,  cascade = CascadeType.REMOVE)
    @JoinColumn(name = "linkedItem", nullable = false)
    private Item source;
}
