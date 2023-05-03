package com.example.storeeverything.Repositories;

import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Entities.Item;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByCategory(Category category);
    List<Item> findAllByUserId(Long id);
}
